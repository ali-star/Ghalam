package ir.siriusapps.ghalam.data.base

import ir.sitiusapps.ghalam.domain.model.Model

interface EntityMapper <M: Model, EM: EntityModel> {

    fun mapToData(model: M): EM

    fun mapToDomain(entityModel: EM): M

}