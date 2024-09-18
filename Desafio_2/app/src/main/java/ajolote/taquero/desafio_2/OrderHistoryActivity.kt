package ajolote.taquero.desafio_2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        // Obtener el historial de pedidos desde SharedPreferences
        val sharedPreferences = getSharedPreferences("historial_pedidos", MODE_PRIVATE)
        val pedidosJson = sharedPreferences.getString("pedidos", "[]") ?: "[]"
        val pedidosList = Pedido.fromJson(pedidosJson)

        // Mostrar los pedidos en un ListView
        val listViewHistory = findViewById<ListView>(R.id.listViewHistory)

        if (pedidosList.isEmpty()) {
            // Si no hay pedidos, mostrar un mensaje indicativo
            val pedidosDesplegados = listOf("No hay pedidos")
            listViewHistory.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidosDesplegados)
        } else {
            // Crear una lista de strings para mostrar los detalles de cada pedido
            val pedidosDesplegados = pedidosList.map { pedido ->
                val productos = pedido.productos.joinToString("\n") { producto -> "${producto.nombre} - \$${producto.precio}" }
                "Pedido total: \$${pedido.total}\nProductos:\n$productos"
            }

            // Asignar el adaptador con los pedidos desplegados
            listViewHistory.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pedidosDesplegados)
        }
    }
}

