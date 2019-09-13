package ir.siriusapps.ghalam.di.module

import com.google.gson.*
import dagger.Module
import dagger.Provides
import ir.siriusapps.ghalam.data.*
import java.lang.reflect.Type
import java.util.*

@Module
class GsonModule {

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Content::class.java,
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
                        return context.deserialize(json, PhotoContent::class.java)
                    ContentType.RECORDING.name.toLowerCase(Locale.ROOT) ->
                        return context.deserialize(json, RecordingContent::class.java)
                    ContentType.MUSIC.name.toLowerCase(Locale.ROOT) ->
                        return context.deserialize(json, MusicContent::class.java)
                }
            }
            return null
        }

    }

}