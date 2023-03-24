package com.kingstek.companion.dummy_data

import com.kingstek.companion.R
import com.kingstek.companion.models.parish.*
import java.util.*
import kotlin.collections.ArrayList

class DummyData {


    private val dataone: NewsModel = NewsModel(
        "Something is happening, Just Watch out, Its Coming",
        "Details goes here, breif of what it actually entails, brief of what it actually entails. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tellus dolor, porttitor non nisl eu, ultricies sodales orci. Donec malesuada, massa quis maximus posuere, tortor arcu tincidunt felis, nec egestas ex tortor a orci. Sed mollis massa quis massa dapibus venenatis sit amet ut ligula. Morbi rhoncus eleifend mi, eget dapibus eros. Aenean consectetur, orci nec interdum posuere, velit ex vestibulum nulla, nec commodo turpis erat nec tortor. Suspendisse potenti. Quisque elementum lacus a sodales fermentum. Maecenas non fringilla turpis. Aenean ultricies accumsan nibh, sit amet suscipit magna efficitur in. Suspendisse malesuada iaculis hendrerit. Vestibulum a leo et risus ultricies posuere eget non felis. Cras justo nulla, convallis id volutpat id, elementum quis tellus. In convallis malesuada augue vel consectetur. Donec a augue magna.\n " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tellus dolor, porttitor non nisl eu, ultricies sodales orci. Donec malesuada, massa quis maximus posuere, tortor arcu tincidunt felis, nec egestas ex tortor a orci. Sed mollis massa quis massa dapibus venenatis sit amet ut ligula. Morbi rhoncus eleifend mi, eget dapibus eros. Aenean consectetur, orci nec interdum posuere, velit ex vestibulum nulla, nec commodo turpis erat nec tortor. Suspendisse potenti. Quisque elementum lacus a sodales fermentum. Maecenas non fringilla turpis. Aenean ultricies accumsan nibh, sit amet suscipit magna efficitur in. Suspendisse malesuada iaculis hendrerit. Vestibulum a leo et risus ultricies posuere eget non felis. Cras justo nulla, convallis id volutpat id, elementum quis tellus. In convallis malesuada augue vel consectetur. Donec a augue magna.\n",
        R.drawable.holy_cross_cathedral
    )
    private val datatwo: NewsModel = NewsModel(
        "Nothing is happening",
        "Details goes here, breif of what it actually entails, breif of what it actually entails",
        R.drawable.rosary_bible
    )
    private val datathree: NewsModel = NewsModel(
        "What is happening",
        "Details goes here, breif of what it actually entails, breif of what it actually entails",
        R.drawable.rosary_bible
    )
    private val datafour: NewsModel = NewsModel(
        "Who is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.image_cyril
    )
    private val datafive: NewsModel = NewsModel(
        "Something is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.rosary_bible
    )
    private val datasix: NewsModel = NewsModel(
        "Nothing is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.rosary_bible
    )
    private val dataseven: NewsModel = NewsModel(
        "What is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.st_cyril
    )
    private val dataeight: NewsModel = NewsModel(
        "Who is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.rosary_bible
    )
    private val datanine: NewsModel = NewsModel(
        "Something is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.st_cyril_church
    )
    private val dataten: NewsModel = NewsModel(
        "Nothing is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.rosary_bible
    )
    private val dataeleven: NewsModel = NewsModel(
        "What is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.image_cyril
    )
    private val datatwelve: NewsModel = NewsModel(
        "Who is happening",
        "Details goes here, breif of what it actually entails",
        R.drawable.rosary_bible
    )

    private val parishImage =
        arrayListOf<Int>(R.drawable.st_cyril_church, R.drawable.st_cyril, R.drawable.image_cyril)
    private val sundayMass = arrayListOf("6:30am, First Mass", "7:30am, Children mass", "6:00pm, Evening mass")
    private val weekdayMass = arrayListOf(
        WeekMasses("Monday", listOf("6:00am", "6:pm", "12:pm")),
        WeekMasses("Wednesday", listOf("6:30am", "6:pm", "12:pm")),
        WeekMasses("Thursday", listOf("6:00am", "6:pm", "12:pm")),
        WeekMasses("Friday", listOf("6:00am", "6:pm")),
        WeekMasses("Saturday", listOf("7:00am"))
    )
    private val pastoralTeam = arrayListOf(
        PastoralTeam("Parish Priest", "Rev. Fr", "Rev. Fr Martin Uwakwe", "08037120696"),
        PastoralTeam("Ass Priest", "Rev. Fr", "Rev. Fr MaASrtin Uwdddakwe", "08037120696"),
        PastoralTeam("Ass Priest", "Rev. Fr", "Rev. Fr MarWEtin Uwa   akwe", "08037120696"),
        PastoralTeam("Catechist ", "Mr", "Mr Martin Uwakwe", "08037120696")
    )
    private val churchActivities = arrayListOf(
        Activities("Confesssion", "Saturday: 8:30am"),
        Activities("Marriage", "Saturday: 8:30am"),
        Activities("Communion Rounds", "First Thursday/Friday"),
        Activities("Marian Devotion", "Wed, From 6:30am"),
        Activities("Adoration of the blessed Sacrament", "Thurs 6:30am"),
        Activities(
            "Weekly blessings", "1st Sunday: workers and tithe offering, \n" +
                    "2nd Sunday: children and babies\n" +
                    "3rd Sunday: youths and students\n" +
                    "4th Sunday: youths and students"
        ),
        Activities("Last Sunday", "Welcoming of new memebrs/visitors"),
        Activities("Benediction", "Sunday: 6:30pm"),
        Activities("Blessing of water and religious articles", "Saturday: 8:50am"),
        Activities("Quarterly vigils & prayers", "first week of the quarter"),
        Activities("Christian Wake", "Friday: 530pm")
    )
    private val announcement = arrayListOf(
        Announcement(
            "Confesssion",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tellus dolor, porttitor non nisl eu, ultricies sodales orci. Donec malesuada, massa quis maximus posuere, tortor arcu tincidunt felis, nec egestas ex tortor a orci.",
            true,
            Date(20122022)
        ),
        Announcement(
            "Marriage",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tellus dolor, porttitor non nisl eu, ultricies sodales orci. Donec malesuada, massa quis maximus posuere, tortor arcu tincidunt felis, nec egestas ex tortor a orci. ",
            false,
            Date(20122022)
        ),
        Announcement(
            "Communion Rounds",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tellus dolor, porttitor non nisl eu, ultricies sodales orci. Donec malesuada, massa quis maximus posuere, tortor arcu tincidunt felis, nec egestas ex tortor a orci. ",
            false,
            Date(20122022)
        ),
        Announcement(
            "Weekly blessings",
            "1st Sunday: workers and tithe offering",
            false,
            Date(20122022)
        )
    )
    private val evenImages1 = EvenImages("", R.drawable.image_cyril)
    private val evenImages2 = EvenImages("", R.drawable.st_cyril)
    private val evenImages3 = EvenImages("", R.drawable.st_cyril_church)
    private val evenImages4 = EvenImages("", R.drawable.holy_cross_cathedral)
    private val evenImages5 = EvenImages("", R.drawable.rosary_bible)

    private val eventImages: ArrayList<EvenImages> = arrayListOf (
        evenImages3,
        evenImages1,
        evenImages2,
        evenImages3,
        evenImages4,
        evenImages5)

    private val gallery1 = ParishGalleryModel(
        " Feast of Christ the king",
        "2022",
    "Holds every last sunday of the curhees calendar year",
        "Holds every last sunday of the curhees calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe ",
        eventImages,
        Date(20122022)
    )

    private val gallery2 = ParishGalleryModel(
        "Harvest of Divine favour 2022",
        "2022",
        "Holds every year in thanksgiving",
        "Holds every last sunday of the curhees calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe calendar year in commemoration of Jesus Christ as King of the Universe ",
        eventImages,
        Date(20122022)
    )

    private val p1: ParishModel = ParishModel(
        "Christ the king",
        "Okota State",
        "Festac Deanary",
        "Lagos Archdioces",
        parishImage,
        sundayMass,
        weekdayMass,
        pastoralTeam,
        churchActivities,
        announcement,
        arrayListOf(gallery1, gallery2)
    )

    private val p2: ParishModel = ParishModel(
        "St Cyril Catholic Church Okota",
        "Okota, Lagos State, Lagos, Nigeria",
        "Apapa Deanary",
        "Awka Dioces",
        parishImage,
        sundayMass,
        null,
        pastoralTeam,
        churchActivities,
        announcement,
        arrayListOf(gallery1, gallery2, gallery1, gallery2, gallery1, gallery2)
    )

    val newsList: ArrayList<NewsModel> = arrayListOf(
        dataone,
        datatwo,
        datathree,
        datafour,
        datafive,
        datasix,
        dataseven,
        dataeight,
        datanine,
        dataten,
        dataeleven,
        datatwelve
    )
    val parishList: ArrayList<ParishModel> = arrayListOf(
        p1,
        p2,
        p1,
        p1,
        p1,
        p1,
        p1,
        p1,
        p2,
        p2,
        p2,
        p2,
        p2,
        p1,
        p1,
        p1,
        p1,
        p1,
    )

    val parishListSpinner: ArrayList<String> = arrayListOf("Select Parish", "Parish One", "Parish Two", "Parish Four")

}