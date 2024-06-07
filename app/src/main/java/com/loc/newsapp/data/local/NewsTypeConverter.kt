package com.loc.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.loc.newsapp.domain.model.OgImage
import com.loc.newsapp.domain.model.TwitterMisc
import com.loc.newsapp.domain.model.YoastHeadJson

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun yoast_head_jsonToString(yoast_head_json: YoastHeadJson): String{

        // Saída personalizada (apenas URLs)
        val customJoinedStringDot = yoast_head_json.og_image.joinToString(separator = ".") { it.url }

        return "${yoast_head_json.title}," +
                "${yoast_head_json.author}," +
                "${yoast_head_json.og_description}," +
                "[$customJoinedStringDot],${yoast_head_json.twitter_misc.estimatedReadingTime}"
    }

    @TypeConverter
    fun stringToYoastHeadJson(yoast_head_json: String): YoastHeadJson{
        return yoast_head_json.split(",").let {
            YoastHeadJson(
                it[0],
                it[1],
                it[2],
                listOf(OgImage(it[3])),
                TwitterMisc(it[4])
            )
        }
    }
}