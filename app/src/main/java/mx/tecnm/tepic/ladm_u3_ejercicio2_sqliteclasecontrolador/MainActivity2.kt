package mx.tecnm.tepic.ladm_u3_ejercicio2_sqliteclasecontrolador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.tecnm.tepic.ladm_u3_ejercicio2_sqliteclasecontrolador.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    var noControl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        noControl = this.intent.extras!!.getString("nocontrol")!!
        val alumno = Alumno(this).mostrarAlumno(noControl)

        binding.nombre.setText(alumno.nombre)
        binding.carrera.setText(alumno.carrera)

        binding.actualizar.setOnClickListener {
            var alumnoo = Alumno(this)

            alumno.noControl = noControl
            alumno.nombre = binding.nombre.text.toString()
            alumno.carrera = binding.carrera.text.toString()

            val respuesta = alumno.actualizar()

            if (respuesta){
                Toast.makeText(this,"SE ACTUALIZO CORRECTAMENTE",Toast.LENGTH_LONG)
                    .show()
                binding.nombre.setText("")
                binding.carrera.setText("")
            }else{
                AlertDialog.Builder(this)
                    .setTitle("ATENCIÓN")
                    .setMessage("ERROR NO SE ACTUALIZO")
                    .show()
            }
        }
        binding.regresar.setOnClickListener {
            finish()
        }
    }
}