package ajolote.taquero.desafio_2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        Handler().postDelayed({
            // Lanzar la actividad de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Evitar que el usuario vuelva al splash screen con el botón "atrás"
        }, 3000)
    }
}