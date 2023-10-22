package com.rinha.backend.KotlinRinhaBackend.datasource.repository.rowmapper

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException

class StackRowMapper : RowMapper<Collection<String>> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Collection<String> {
        return listOf()
    }
}