package ir.siriusapps.ghalam.di.module

import com.google.gson.*
import dagger.Module
import dagger.Provides
import ir.sitiusapps.ghalam.domain.model.Content
import ir.sitiusapps.ghalam.domain.model.ContentType
import ir.sitiusapps.ghalam.domain.model.FileContent
import ir.sitiusapps.ghalam.domain.model.TextContent
import java.lang.reflect.Type
import java.util.*

@Module
class GsonModule {

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(
            Content::class.java,
            ContentDeserializer()
        )
        return gsonBuilder.create()
    }

    class ContentDeserializer : JsonDeserializer<Content> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext
        ): Content? {
            if (json == null)
                return null
            val jsonObject = json.asJsonObject
            if (jsonObject.has("type")) {
                when(jsonObject.get("type").toString().toLowerCase(Locale.ROOT)) {
                    ContentType.TEXT.name.toLowerCase(Locale.ROOT) ->
                        return context.deserialize(json, TextContent::class.java)
                    ContentType.PHOTO.name.toLowerCase(Locale.ROOT) ->
                        return context.deserialize(json, FileContent::class.java)
                }
            }
            return null
        }

    }

}