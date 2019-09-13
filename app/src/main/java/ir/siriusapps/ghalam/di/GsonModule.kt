package ir.siriusapps.ghalam.di

import com.google.gson.*
import dagger.Module
import dagger.Provides
import ir.siriusapps.ghalam.data.*
import java.lang.reflect.Type

@Module
class GsonModule {

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Content::class.java, ContentDeserializer())
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
                when(jsonObject.get("type").toString()) {
                    ContentType.TEXT.name -> return context.deserialize(json, TextContent::class.java)
                    ContentType.PHOTO.name -> return context.deserialize(json, PhotoContent::class.java)
                    ContentType.RECORDING.name -> return context.deserialize(json, RecordingContent::class.java)
                    ContentType.MUSIC.name -> return context.deserialize(json, MusicContent::class.java)
                }
            }
            return null
        }

    }

}