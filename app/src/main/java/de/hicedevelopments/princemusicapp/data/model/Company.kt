package de.hicedevelopments.princemusicapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("catno")
    @Expose
    var catno: String? = null,
    @SerializedName("entity_type")
    @Expose
    var entityType: String? = null,
    @SerializedName("entity_type_name")
    @Expose
    var entityTypeName: String? = null,
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("resource_url")
    @Expose
    var resourceUrl: String? = null
)