package com.example.weatherapp.domain.model

import android.content.res.Resources

data class City(
    val en: String?,
    val ru: String?,
    val kk: String?
) {
    fun inLocaleLanguage(resources: Resources): String? =
        when (resources.configuration.locale.language) {
            "kk" -> this.kk
            "ru" -> this.ru
            else -> this.en
        }
}

val cities = listOf(
    City("Almaty", "Алматы", "Алматы"),
    City("Astana", "Астана", "Астана"),
    City("Shymkent", "Шымкент", "Шымкент"),
    City("Aktobe", "Актобе", "Ақтөбе"),
    City("Karaganda", "Караганда", "Қарағанды"),
    City("Taraz", "Тараз", "Тараз"),
    City("Oskemen", "Усть-Каменогорск", "Өскемен"),
    City("Pavlodar", "Павлодар", "Павлодар"),
    City("Semey", "Семей", "Семей"),
    City("Atyrau", "Атырау", "Атырау"),
    City("Kyzylorda", "Кызылорда", "Қызылорда"),
    City("Kostanay", "Костанай", "Қостанай"),
    City("Aktau", "Актау", "Ақтау"),
    City("Oral", "Уральск", "Орал"),
    City("Petropavl", "Петропавловск", "Петропавл"),
    City("Turkistan", "Туркестан", "Түркістан"),
    City("Kokshetau", "Кокшетау", "Көкшетау"),
    City("Temirtau", "Темиртау", "Теміртау"),
    City("Taldykorgan", "Талдыкорган", "Талдықорған"),
    City("Rudny", "Рудный", "Рудный")
)
