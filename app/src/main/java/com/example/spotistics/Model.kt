package com.example.spotistics

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.gson.annotations.SerializedName

const val refreshToken = "AQAsDs2M5-1DsPGC44smQiegjj_l6rb_ix40Iook78oo2mk-PhCsOqTeE5TwO-3yQBs8D-0dTB17pVaCrH8zbmEMOqfVxoQKawgvQDotHFk19i7CSc1ZO53ooRW_Gp8AvKM"

val genres = listOf(
    "Alternative", "Blues", "Classical", "Country", "Disco", "Folk",
    "Indie", "Jazz", "Metal", "Pop", "Punk", "Rock", "Electronic", "Soul"
)

object Screens {
    const val Home = "home"
    const val Search = "search"
    const val SearchResults = "results"
    const val Recommendations = "recs"
    const val Throwbacks = "throwbacks"
    const val Statistics = "stats"
    const val History = "history"
    const val Settings = "settings"
}

data class NavigationItem (
    val id: String,
    val title: String,
    val icon: ImageVector,
    var data: Map<String, Any> = mapOf()
)

data class Song(
    val musicId: Int = 0,
    val coverResourceId: Int = 0,
    val title: String,
    val artist: String,
    val image: String = ""
)

data class SongStat(
    val timeStamp: String,
    val coverResource: String,
    val title: String,
    val artist: String
)

data class Preference(
    val name: String,
    val value: Int
)

val preferences = listOf(
    Preference("Acousticness", 7),
    Preference("Danceability", 8),
    Preference("Energy", 6),
    Preference("Instrumentalness", 4),
    Preference("Liveness", 5),
    Preference("Popularity", 7),
    Preference("Speechiness", 5)
)

data class AccessToken(
    @field:SerializedName("access_token") var accessToken: String,
)

data class User(
    @field:SerializedName("display_name") val displayName: String,
)

data class SearchResult(
    val artists: Artists
)

data class Artists(
    val results: List<Artist>
)

data class Artist(
    @field:SerializedName("type_details") val typeDetails: TypeDetails
)

data class TypeDetails(
    val artist: ArtistDetails,
)

data class ArtistDetails(
    val id: String,
    val name: String
)

data class ExternalUrls(
    val spotify: String
)

data class RecArtist(
    val name: String,
)

data class AlbumImage(
    val url: String
)

data class Album(
    val name: String,
    val images: List<AlbumImage>,
    @field:SerializedName("external_urls") val externalUrls: ExternalUrls
)

data class Track(
    val id: String,
    val name: String,
    @field:SerializedName("external_urls") val externalUrls: ExternalUrls,
)

data class RecTypeDetails(
    val album: Album,
    val artists: List<RecArtist>,
    val track: Track
)

data class Recommendation(
    @field:SerializedName("type_details") val typeDetails: RecTypeDetails
)

data class RecommendationResult(
    val recommendations: List<Recommendation>
)

data class SpotifyEmbedResponse(
    @field:SerializedName("thumbnail_url") val thumbnailUrl: String?
)

data class Stats(
    val duration: Int,
    val count: Int
)

data class TrackHistory(
    val trackId: String,
    val timestamp: String
)

data class History(
    val history: List<TrackHistory>
)

val emptySearchResult = SearchResult(
    Artists(listOf(Artist(TypeDetails(ArtistDetails("", ""))))))

val emptyRecommendation = RecommendationResult(
    listOf(Recommendation(RecTypeDetails(
        Album("", listOf(AlbumImage("")), ExternalUrls("")),
        listOf(RecArtist("")),
        Track("", "", ExternalUrls(""))))))
