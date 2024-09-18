import ajolote.taquero.desafio_2.Producto
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Pedido(val productos: List<Producto>, val total: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Producto.CREATOR) ?: emptyList(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(productos)
        parcel.writeDouble(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pedido> {
        override fun createFromParcel(parcel: Parcel): Pedido {
            return Pedido(parcel)
        }

        override fun newArray(size: Int): Array<Pedido?> {
            return arrayOfNulls(size)
        }

        // Implementación para convertir desde JSON a una lista de pedidos
        fun fromJson(json: String): List<Pedido> {
            val gson = Gson()
            val listType = object : TypeToken<List<Pedido>>() {}.type
            return gson.fromJson(json, listType)
        }

        // Implementación para convertir una lista de pedidos a JSON
        fun toJson(pedidos: List<Pedido>): String {
            val gson = Gson()
            return gson.toJson(pedidos)
        }
    }
}
