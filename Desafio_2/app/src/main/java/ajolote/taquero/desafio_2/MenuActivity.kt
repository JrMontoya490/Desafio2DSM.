package ajolote.taquero.desafio_2

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {


    private val selectedItems = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val alimentos = listOf(
            Producto("Tacos", 5.00), Producto("Burritos", 7.00), Producto("Enchiladas", 6.00),
            Producto("Quesadillas", 4.00), Producto("Guacamole", 3.00), Producto("Tostadas", 5.00),
            Producto("Nachos", 6.00)
        )

        val bebidas = listOf(
            Producto("Agua", 1.00), Producto("Refresco", 2.00), Producto("Margarita", 5.00)
        )


        val listViewAlimentos = findViewById<ListView>(R.id.listViewAlimentos)
        val listViewBebidas = findViewById<ListView>(R.id.listViewBebidas)
        val buttonViewHistory = findViewById<Button>(R.id.buttonViewHistory)
        val buttonOrdenar = findViewById<Button>(R.id.buttonOrdenar)

        listViewAlimentos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, alimentos.map { "${it.nombre} - \$${it.precio}" })
        listViewAlimentos.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        listViewBebidas.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, bebidas.map { "${it.nombre} - \$${it.precio}" })
        listViewBebidas.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        buttonViewHistory.setOnClickListener {
            val intent = Intent(this, OrderHistoryActivity::class.java)
            startActivity(intent)
        }

        buttonOrdenar.setOnClickListener {
            selectedItems.clear()

            val selectedIndicesAlimentos = listViewAlimentos.checkedItemPositions
            (0 until selectedIndicesAlimentos.size()).forEach { i ->
                if (selectedIndicesAlimentos.valueAt(i)) {
                    selectedItems.add(alimentos[selectedIndicesAlimentos.keyAt(i)])
                }
            }

            val selectedIndicesBebidas = listViewBebidas.checkedItemPositions
            (0 until selectedIndicesBebidas.size()).forEach { i ->
                if (selectedIndicesBebidas.valueAt(i)) {
                    selectedItems.add(bebidas[selectedIndicesBebidas.keyAt(i)])
                }
            }

            val intent = Intent(this, CartActivity::class.java)
            intent.putParcelableArrayListExtra("selectedItems", ArrayList(selectedItems))
            startActivity(intent)
        }
    }
}
