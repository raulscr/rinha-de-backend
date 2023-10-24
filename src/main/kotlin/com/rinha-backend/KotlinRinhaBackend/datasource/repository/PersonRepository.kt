package com.rinha.backend.KotlinRinhaBackend.datasource.repository

import com.rinha.backend.KotlinRinhaBackend.datasource.PersonDataSourceInterface
import com.rinha.backend.KotlinRinhaBackend.datasource.repository.rowmapper.PersonModelRowMapper
import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.data.repository.query.Param
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository("repository")
class PersonRepository(private val jdbcTemplate: JdbcTemplate) : PersonDataSourceInterface {

    override fun getPersonById(@Param("id") id: Long): PersonModel? {
        val sqlQuery: String = """
            SELECT
                p.id,
                p.name,
                p.nick_name,
                p.born_date
            FROM
                person p
            WHERE (
                p.id = ?
            );
        """
        return jdbcTemplate.query(sqlQuery, personRowMapper, id).firstOrNull();
    }

    override fun getPersonByFilter(filter: String): Collection<PersonModel> {
        TODO("NOT IMPLEMENTED")
    }

    override fun insertPerson(person: PersonModel) {
        val sqlQuery: String = """
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
        jdbcTemplate.update(sqlQuery, person.name, person.nickName, person.bornDate)
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

    private val personRowMapper: PersonModelRowMapper = PersonModelRowMapper()

}
