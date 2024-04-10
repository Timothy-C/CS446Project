import com.example.spotistics.R
import com.example.spotistics.Song

val recSongs = listOf(
    listOf(
        "2024-02-29T23:54:01.071Z",
        "You Don't Know Me",
        "https://i.scdn.co/image/ab67616d0000b2731d883d3f10af481faa3c7e04",
        "Jax Jones"
    ),
    listOf(
        "2024-02-29T23:50:07.475Z",
        "Love Lies (with Normani)",
        "https://i.scdn.co/image/ab67616d0000b273dd7eaf261543814a6d715d67",
        "Khalid"
    ),
    listOf(
        "2024-02-29T23:53:29.252Z",
        "Runnin' (Lose It All)",
        "https://i.scdn.co/image/ab67616d0000b273c24dcf24b68739638e3231bd",
        "Naughty Boy"
    ),
    listOf(
        "2024-02-29T23:46:42.157Z",
        "Nothing Is Promised (with Rihanna)",
        "https://i.scdn.co/image/ab67616d0000b27387f32427f479a260cd94f8e5",
        "Mike WiLL Made-It"
    ),
    listOf(
        "2024-02-29T23:43:48.446Z",
        "No Enemiesz",
        "https://i.scdn.co/image/ab67616d0000b273409c576a374c0e7dc31f1dd3",
        "Kiesza"
    ),
    listOf(
        "2024-02-29T23:40:40.562Z",
        "In The Dark",
        "https://i.scdn.co/image/ab67616d0000b273719079569ed19e63384d2720",
        "DEV"
    ),
    listOf(
        "2024-02-29T23:37:53.328Z",
        "Infinity - Magic Mitch Club Mix",
        "https://i.scdn.co/image/ab67616d0000b27365acc9331ae365ffe5ddd94d",
        "Guru Josh Project"
    ),
    listOf(
        "2024-02-29T23:34:28.013Z",
        "Drowning (feat. Kodak Black)",
        "https://i.scdn.co/image/ab67616d0000b273cdba7ee22968991250725ce1",
        "A Boogie Wit da Hoodie"
    ),
    listOf(
        "2024-02-29T22:01:48.185Z",
        "Drinkee",
        "https://i.scdn.co/image/ab67616d0000b273855fa1465372d955fc0fb38a",
        "Sofi Tukker"
    ),
    listOf(
        "2024-02-29T22:00:16.116Z",
        "Hot N Cold",
        "https://i.scdn.co/image/ab67616d0000b27331cc26681b0164332fa26634",
        "Katy Perry"
    ),
)

var history = mutableListOf(listOf(
    "2024-02-29T23:50:07.475Z",
    "Radioactive",
    "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
    "Imagine Dragons"
),)

var historydata = mutableListOf(
    listOf(
        "2024-02-29T23:54:01.071Z",
        "Natural",
        "https://i.scdn.co/image/ab67616d0000b273da6f73a25f4c79d0e6b4a8bd",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:53:29.252Z",
        "Whatever It Takes",
        "https://i.scdn.co/image/ab67616d0000b2735675e83f707f1d7271e5cf8a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:50:07.475Z",
        "Radioactive",
        "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:46:42.157Z",
        "Enemy (with JID) - from the series Arcane League of Legends",
        "https://i.scdn.co/image/ab67616d0000b273fc915b69600dce2991a61f13",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:43:48.446Z",
        "Thunder",
        "https://i.scdn.co/image/ab67616d0000b2735675e83f707f1d7271e5cf8a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:40:40.562Z",
        "Bones",
        "https://i.scdn.co/image/ab67616d0000b273fc915b69600dce2991a61f13",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:37:53.328Z",
        "Believer",
        "https://i.scdn.co/image/ab67616d0000b2735675e83f707f1d7271e5cf8a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T23:34:28.013Z",
        "Demons",
        "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-02-29T22:01:48.185Z",
        "Anti-Hero",
        "https://i.scdn.co/image/ab67616d0000b273bb54dde68cd23e2a268ae0f5",
        "Taylor Swift"
    ),
    listOf(
        "2024-02-29T22:00:16.116Z",
        "Lover",
        "https://i.scdn.co/image/ab67616d0000b273e787cffec20aa2a396a61647",
        "Taylor Swift"
    ),
    listOf(
        "2024-02-29T21:34:34.167Z",
        "Cruel Summer",
        "https://i.scdn.co/image/ab67616d0000b273e787cffec20aa2a396a61647",
        "Taylor Swift"
    ),
    listOf(
        "2024-02-14T21:31:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:28:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:24:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:20:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:16:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:12:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:09:34.167Z",
        "Glimpse of Us",
        "https://i.scdn.co/image/ab67616d00001e0208596cc28b9f5b00bfe08ae7",
        "Joji"
    ),
    listOf(
        "2024-02-14T21:06:34.167Z",
        "SLOW DANCING IN THE DARK",
        "https://i.scdn.co/image/ab67616d00001e024cc52cd7a712842234e4fce2",
        "Joji"
    ),
    listOf(
        "2024-01-21T01:28:50.475Z",
        "Radioactive",
        "https://i.scdn.co/image/ab67616d0000b273b2b2747c89d2157b0b29fb6a",
        "Imagine Dragons"
    ),
    listOf(
        "2024-01-20T06:08:56.406Z",
        "Lover",
        "https://i.scdn.co/image/ab67616d0000b273e787cffec20aa2a396a61647",
        "Taylor Swift"
    )
)

val searchSongs = listOf(
    listOf(
        "2024-02-29T23:54:01.071Z",
        "Brothers in Arms",
        "https://i.scdn.co/image/ab67616d0000b273995239a0e35a898037ec4b29",
        "Dire Straits"
    ),
    listOf(
        "2024-02-29T23:54:01.071Z",
        "I am Human",
        "https://i.scdn.co/image/ab67616d0000b2737500f3035770bae907c47814",
        "Escape the Fate"
    ),
    listOf(
        "2024-02-29T23:54:01.071Z",
        "Broken Heart",
        "https://i.scdn.co/image/ab67616d0000b273a6c59919331a92f96c4a7444",
        "Mosaic"
    ),
    listOf(
        "2024-02-29T23:54:01.071Z",
        "Guitar Gangsters & Cadillac Blood",
        "https://i.scdn.co/image/ab67616d0000b2739daf215ff64034b5f9e02593",
        "Volbeat"
    ),
    listOf(
        "2024-02-29T23:54:01.071Z",
        "Dark Void",
        "https://i.scdn.co/image/ab67616d0000b2733b1788ec687d88f3d48d027a",
        "Asking Alexandria"
    ),
)

val dummySongs = listOf(
    Song(1, R.drawable.sufjan, "Will Anybody Ever Love Me?", "Sufjan Stevens"),
    Song(2, R.drawable.as_it_was, "As it was", "Harry Styles"),
    Song(3, R.drawable.get_him_back, "Get him back!", "Olivia Rodrigo"),
    Song(4, R.drawable.lana, "A&W", "Lana Del Rey"),
    Song(5, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(6, R.drawable.kill_bill, "Kill Bill", "SZA"),
    Song(7, R.drawable.supershy, "Super Shy", "NewJeans"),
    Song(8, R.drawable.wednesday, "Chosen to Deserve", "Wednesday"),
    Song(9, R.drawable.vampire, "Vampire", "Olivia Rodrigo"),
    Song(10, R.drawable.taylor_swift, "Lover", "Taylor Swift"),
)

val dummySongs2 = listOf(
    Song(1, R.drawable.taylor_swift, "Lover", "Taylor Swift"),
    Song(2, R.drawable.vampire, "Vampire", "Olivia Rodrigo"),
    Song(3, R.drawable.get_him_back, "Get him back!", "Olivia Rodrigo"),
    Song(4, R.drawable.supershy, "Super Shy", "NewJeans"),
    Song(5, R.drawable.wednesday, "Chosen to Deserve", "Wednesday"),
    Song(6, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(7, R.drawable.sufjan, "Will Anybody Ever Love Me?", "Sufjan Stevens"),
    Song(8, R.drawable.kill_bill, "Kill Bill", "SZA"),
    Song(9, R.drawable.bohemian_rhapsody, "Bohemian Rhapsody", "Queen"),
    Song(10, R.drawable.lana, "A&W", "Lana Del Rey"),
)

val dummySongs3 = listOf(
    Song(1, R.drawable.black_midi, "Welcome to Hell", "Black Midi"),
    Song(2, R.drawable.phonex, "Tonight", "Phoenix"),
    Song(3, R.drawable.tomberlin, "happy accident", "Tomberlin"),
    Song(4, R.drawable.horse_lords, "May Brigade", "Horse Lords"),
    Song(5, R.drawable.julia_jack, "Lydia Wears a Cross", "Julia Jacklin"),
    Song(6, R.drawable.pods, "Pods", "Two Shell"),
    Song(7, R.drawable.joe, "bezhigo", "Joe Rainey"),
    Song(8, R.drawable.pusha, "Diet Coke", "Pusha T")
)