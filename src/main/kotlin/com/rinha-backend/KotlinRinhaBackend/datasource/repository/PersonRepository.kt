package com.rinha.backend.KotlinRinhaBackend.datasource.repository

import com.rinha.backend.KotlinRinhaBackend.datasource.PersonDataSourceInterface
import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.data.repository.query.Param
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.ResultSet

@Repository("repository")
class PersonRepository(private val jdbcTemplate: JdbcTemplate) : PersonDataSourceInterface {

    override fun getPersonById(@Param("id") id: Long): PersonModel? {
        val stackQuery: String = """
            SELECT
                s.stack AS stack
            FROM
                person_stack s
            WHERE (
                s.person_id = ?
            );
        """
        val stack: Collection<String> = jdbcTemplate.query(stackQuery, RowMapper<String> { rs: ResultSet, _: Int ->
            rs.getString("stack")
        }, id);

        val personQuery: String = """
            SELECT
                p.id AS id,
                p.name AS name,
                p.nick_name AS nick_name,
                p.born_date AS born_date
            FROM
                person p
            WHERE (
                p.id = ?
            );
        """
        return jdbcTemplate.query(personQuery, RowMapper<PersonModel> { rs: ResultSet, _: Int ->
            PersonModel(
                id = rs.getLong("id"),
                name = rs.getString("name"),
                nickName = rs.getString("nick_name"),
                bornDate = rs.getString("born_date"),
                stack = stack
            )
        }, id).firstOrNull();
    }

    override fun getPersonByFilter(filter: String): Collection<PersonModel> {
        TODO("NOT IMPLEMENTED")
    }

    override fun insertPerson(person: PersonModel) {
        val personQuery: String = """
            INSERT INTO
                person (
                    name,
                    nick_name,
                    born_date
                ) VALUES 
                (
                    ?,
                    ?,
                    ?
                );
        """
        jdbcTemplate.update(personQuery, person.name, person.nickName, person.bornDate)

        if (person.stack.isNullOrEmpty()) {
            return
        }

        val lastInsertedPersonId: Int =
            jdbcTemplate.query("SELECT LAST_INSERT_ID() AS id", RowMapper<Int> { rs: ResultSet, _: Int ->
                rs.getInt("id")
            }).first()

        val stackQuery: String = """
            INSERT INTO
                person_stack (
                    person_id,
                    stack
                ) VALUES (?, ?);
        """
        jdbcTemplate.batchUpdate(stackQuery, object : BatchPreparedStatementSetter {
            override fun setValues(ps: PreparedStatement, i: Int) {
                ps.setInt(1, lastInsertedPersonId)
                ps.setString(2, person.stack.elementAt(i))
            }

            override fun getBatchSize(): Int {
                return person.stack.size
            }
        })
    }

    override fun getCountPeople(): Int {
        val sqlQuery: String = """
            SELECT 
                COUNT(*) AS count 
            FROM
                person;
        """
        return jdbcTemplate.query(sqlQuery, RowMapper<Int> { rs: ResultSet, _: Int ->
            rs.getInt("count")
        }).first()
    }

// private

    private fun getPersonStackById(ids: List<Long>): Map<Long, List<String>> {
        val stackQuery: String =
            """
            SELECT
                s.person_id AS id,
                s.stack AS stack
            FROM
                person_stack s
            WHERE (
                s.person_id IN (:ids)
            );
        """
        return NamedParameterJdbcTemplate(jdbcTemplate)
            .query(
                stackQuery,
                MapSqlParameterSource().addValue("ids", ids),
                ResultSetExtractor<Map<Long, List<String>>> { rs: ResultSet ->
                    val stacksById: MutableMap<Long, MutableList<String>> = mutableMapOf()
                    while (rs.next()) {
                        val id: Long = rs.getLong("id")
                        val stack: String = rs.getString("stack")
                        if (!stacksById.containsKey(id)) {
                            stacksById[id] = mutableListOf()
                        }
                        stacksById[id]?.add(stack)
                    }
                    stacksById
                }) ?: mapOf()
    }

}
