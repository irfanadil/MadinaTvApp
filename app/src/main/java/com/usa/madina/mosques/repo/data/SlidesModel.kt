package com.usa.madina.mosques.repo.data

data class SlidesModel(
    val audit: Audit,
    val isActive: Boolean,
    val slideCount: Int,
    val slideShowName: String,
    val slides: List<Slide>,
    val slideshowId: String,
    val updatedTime: String
)


data class Slide(
    val audit: AuditX,
    val buttonText: String,
    val description: String,
    val imagePath: String,
    val isActive: Boolean,
    val isVideo: String,
    val slideId: String,
    val slideType: String,
    val startDate: String,
    val title: String,
    val url: String,
    val urlType: String
)


data class AuditX(
    val createdBy: CreatedBy,
    val createdDate: String,
    val lastModifiedBy: LastModifiedBy,
    val lastModifiedDate: String
)

data class CreatedBy(
    val email: String,
    val name: String,
    val userId: String
)

data class LastModifiedBy(
    val email: String,
    val name: String,
    val userId: String
)



data class Audit(
    val createdDate: String,
    val lastModifiedDate: String
)



