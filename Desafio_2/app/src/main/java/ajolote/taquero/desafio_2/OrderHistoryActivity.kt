package ajolote.taquero.desafio_2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val sharedPreferences = getSharedPreferences("historial_pedidos", MODE_PRIVATE)
        val pedidosJson = sharedPreferences.getString("pedidos", "[]") ?: "[]"
        val pedidosList = Pedido.fromJson(pedidosJson)

        val listViewHistory = findViewById<ListView>(R.id.listViewHistory)

        if (pedidosList.isEmpty()) {
            val pedidosDesplegados = listOf("No hay pedidos")
            listViewHistory.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidosDesplegados)
        } else {
            val pedidosDesplegados = pedidosList.map { pedido ->
                val productos = pedido.productos.joinToString("\n") { producto -> "${producto.nombre} - \$${producto.precio}" }
                "Pedido total: \$${pedido.total}\nProductos:\n$productos"
            }

            listViewHistory.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidosDesplegados)
        }
    }
}

