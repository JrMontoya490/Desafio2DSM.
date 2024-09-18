package ajolote.taquero.desafio_2

import Pedido
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Obtener los elementos seleccionados desde el intent
        val selectedItems = intent.getParcelableArrayListExtra<Producto>("selectedItems") ?: arrayListOf()

        // Calcular el total
        val total = selectedItems.sumOf { it.precio }

        // Mostrar los productos en un ListView
        val listViewCart = findViewById<ListView>(R.id.listViewCart)
        listViewCart.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedItems.map { "${it.nombre} - \$${String.format("%.2f", it.precio)}" })

        // Mostrar el total en un TextView
        val textViewTotal = findViewById<TextView>(R.id.textViewTotal)
        textViewTotal.text = "Total: \$${String.format("%.2f", total)}"

        // Configurar el botón para pagar
        val buttonPay = findViewById<Button>(R.id.Buttonpay)
        buttonPay.setOnClickListener {
            // Guardar el pedido en SharedPreferences
            saveOrder(selectedItems, total)

            // Crear un intent para regresar a MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Opcional: para cerrar la actividad actual y evitar que el usuario regrese a ella con el botón de atrás
        }

        // Configurar el botón para regresar al menú
        val buttonBackToMenu = findViewById<Button>(R.id.buttonBackToMenu)
        buttonBackToMenu.setOnClickListener {
            // Crear un intent para regresar a MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Opcional: para cerrar la actividad actual y evitar que el usuario regrese a ella con el botón de atrás
        }
    }

    private fun saveOrder(productos: List<Producto>, total: Double) {
        val sharedPreferences = getSharedPreferences("historial_pedidos", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val toast = Toast.makeText(this, "Pago Efectuado", Toast.LENGTH_LONG)

        // Obtener el historial actual de pedidos
        val pedidosJson = sharedPreferences.getString("pedidos", "[]")
        val pedidosList = mutableListOf<Pedido>()
        if (pedidosJson != null) {
            pedidosList.addAll(Pedido.fromJson(pedidosJson) as Collection<Pedido>)


            val view = toast.view
            if (view != null) {
                view.setBackgroundColor(Color.GREEN)
            }
            val textView = view?.findViewById<TextView>(android.R.id.message)
            if (textView != null) {
                textView.setTextColor(Color.WHITE)
            }
            toast.show()
        }

        // Agregar el nuevo pedido al historial
        pedidosList.add(Pedido(productos, total))

        // Guardar el historial actualizado en SharedPreferences
        editor.putString("pedidos", Pedido.toJson(pedidosList))
        editor.apply()
    }
}
