package com.rinha.backend.KotlinRinhaBackend.datasource.mock

import com.rinha.backend.KotlinRinhaBackend.datasource.PersonDataSourceInterface
import com.rinha.backend.KotlinRinhaBackend.model.PersonModel
import org.springframework.stereotype.Repository

@Repository
class MockPersonDataSource : PersonDataSourceInterface {

    private val dataModel: MutableCollection<PersonModel> =
        mutableListOf(
            PersonModel(1, "p1", "nick1", "04/08/2002", listOf("C++")),
            PersonModel(2, "p2", "nick2", "04/08/2002", listOf("C++")),
            PersonModel(3, "p3", "nick2", "04/08/2002", listOf("C++")),
            PersonModel(4, "p4", "nick2", "04/08/2002", listOf("C++"))
        )

    override fun getPersonById(id: Long): PersonModel {
        return dataModel.first(predicate = { id == it.id })
    }

    override fun getPersonByFilter(filter: String): Collection<PersonModel> {
        return dataModel.filter {
            filter == it.name ||
                    filter == it.nickname ||
                    filter == it.bornDate ||
                    it.stack.contains(filter)
        }
    }

    override fun insertPerson(people: Collection<PersonModel>) {
        dataModel.addAll(people.toMutableList())
        return
    }

    override fun getCountPeople(): Int {
        return dataModel.count()
    }
}
