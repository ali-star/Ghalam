package ir.siriusapps.ghalam.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Note(
    val localId: String? = null,
    @SerializedName("id") val serverId: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("create_date") val createDate: Date? = null,
    @SerializedName("update_date") val updateDate: Date? = null)