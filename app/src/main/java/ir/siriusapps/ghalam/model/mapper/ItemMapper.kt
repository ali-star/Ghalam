package ir.siriusapps.ghalam.model.mapper

import ir.sitiusapps.ghalam.domain.model.Model

interface ItemMapper<M: Model, IM: ItemModel> {

    fun mapToPresentation(model: M): IM

    fun mapToDomain(itemModel: IM): M

}