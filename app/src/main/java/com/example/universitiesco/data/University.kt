package com.example.universitiesco.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class University(
//    @SerialName("name") val name: String,
//    @SerialName("alpha_two_code") val alpha_two_code: String,
//    @SerialName("domains") val domains: List<String>,
//    @SerialName("state-province") val stateProvince: String?,
//    @SerialName("country") val country: String,
//    @SerialName("web_pages") val web_pages: List<String>

    val name: String,
    val alpha_two_code: String,
    val domains: String,
    val state_province: String,
    val country: String,
    val web_pages: String

)