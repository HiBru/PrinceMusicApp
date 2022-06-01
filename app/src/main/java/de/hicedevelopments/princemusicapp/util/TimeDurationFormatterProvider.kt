package de.hicedevelopments.princemusicapp.util

object TimeDurationFormatterProvider {
    fun transformMsToDurationAsString(ms: Int?): String? {
        ms?.let {
            val minutes = String.format("%d", ms.div(60))
            val seconds = String.format("%02d", ms.mod(60))

            return "$minutes : $seconds"
        } ?: return null
    }
}