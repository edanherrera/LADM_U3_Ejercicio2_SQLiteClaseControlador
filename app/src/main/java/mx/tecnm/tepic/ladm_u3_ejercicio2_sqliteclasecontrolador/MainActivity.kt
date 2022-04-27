package mx.tecnm.tepic.ladm_u3_ejercicio2_sqliteclasecontrolador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.tecnm.tepic.ladm_u3_ejercicio2_sqliteclasecontrolador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.insertar.setOnClickListener{
            var alumno = Alumno(this) //ALUMNO ES CLASE CONTROLADOR = ADMON DE DATOS

            alumno.noControl = binding.nocontrol.text.toString()
            alumno.nombre = binding.nombre.text.toString()
            alumno.carrera = binding.carrera.text.toString()

            val resultado = alumno.insertar() //PARA EL MAIN ACTIVITY LA INSERCIÓN ES ABSTRACTA
            if(resultado){
                Toast.makeText(this,"SE INSERTO CON EXITO",Toast.LENGTH_LONG)
                    .show()
            }else{
                AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("NO SE PUDO INSERTAR")
                    .show()
            }

        }//LA CLASE MAIN ACTIVITY ES VISTA, ES DECIR INTERFAZ   GRÁFICA
    }
    fun mostrarDatosEnListView(){
        var listaAlumnos = Alumno(this).mostrarTodos()
        var nombreAlumnos = ArrayList<String>()
        (0..listaAlumnos.size-1).forEach{
            val al = listaAlumnos.get(it)

            nombreAlumnos.add(al.nombre)
        }
        binding.lista.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombreAlumnos)

    }
}