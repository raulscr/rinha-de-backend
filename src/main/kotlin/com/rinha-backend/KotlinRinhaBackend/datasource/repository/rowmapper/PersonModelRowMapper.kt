package com.rinha.backend.KotlinRinhaBackend.datasource.repository.rowmapper

import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException

class PersonModelRowMapper : RowMapper<PersonModel> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): PersonModel {
        return PersonModel(
            id = rs.getLong("id"),
            name = rs.getString("name"),
            nickName = rs.getString("nick_name"),
            bornDate = rs.getString("born_date"),
            stack = stackRowMapper.mapRow(rs, rowNum)
        )
    }

    private val stackRowMapper: StackRowMapper = StackRowMapper()
}