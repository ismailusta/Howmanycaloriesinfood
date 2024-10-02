package com.example.besinkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Besin (

    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val besin_adi : String?,

    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val besin_kalori : String?,

    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val besin_karbonhidrat : String?,

    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val besin_protein : String?,

    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val besin_yag : String?,

    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val besin_gorsel : String?

)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}