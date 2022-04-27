package mx.tecnm.tepic.ladm_u3_ejercicio2_sqliteclasecontrolador

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Alumno(este:Context) {
    private val este = este
    var noControl = ""
    var nombre = ""
    var carrera = ""
    private var err = ""

    fun insertar() : Boolean{
        val baseDatos = BaseDatos(este,"escuela",null,1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("NOCONTROL",noControl)
            datos.put("NOMBRE",nombre)
            datos.put("CARRERA",carrera)

            val respuesta = tabla.insert("ALUMNO",null,datos)
            if (respuesta == -1L){
                return false
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }
    fun mostrarTodos() : ArrayList<Alumno>{
        val baseDatos = BaseDatos(este,"escuela",null,1)
        err = ""
        var arreglo = ArrayList<Alumno>()
        try {
            val tabla = baseDatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ALUMNO"

            var cursor = tabla.rawQuery(SQLSELECT,null)
            if (cursor.moveToFirst()){
                do {
                    val alumno = Alumno(este)
                    alumno.noControl = cursor.getString(0)
                    alumno.nombre = cursor.getString(1)
                    alumno.carrera = cursor.getString(2)
                    arreglo.add(alumno)
                }while (cursor.moveToNext())
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarAlumno(noControl:String):Alumno{
        val baseDatos = BaseDatos(este,"ejemplo1",null,1)
        var resultado =""
        err=""
        val alumno = Alumno(este)
        try {
            val tabla = baseDatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ALUMNO WHERE NOCONTROL=?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(noControl))
            if (cursor.moveToFirst()){
                do {
                    val alumno = Alumno(este)
                    alumno.noControl = cursor.getString(0)
                    alumno.nombre = cursor.getString(1)
                    alumno.carrera = cursor.getString(2)
                }while (cursor.moveToNext())
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return alumno
    }
    fun actualizar(): Boolean{
        val baseDatos = BaseDatos(este,"escuela",null,1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val datosAcualizados = ContentValues()

            datosAcualizados.put("NOMBRE", nombre)
            datosAcualizados.put("CARRERA", carrera)
            
            val respuesta = tabla.update("ALUMNO",datosAcualizados,"NOCONTROL=?", arrayOf(noControl))

            if(respuesta==0)return false


        }catch (err:SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

    fun eliminar(noControlEliminar : String): Boolean {
        val baseDatos = BaseDatos(este,"escuela",null,1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val resultado = tabla.delete("ALUMNO","NOCONTROL=?", arrayOf(noControlEliminar))

            if (resultado == 0) return false

        }catch (err:SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }
    fun eliminar() : Boolean {
        val baseDatos = BaseDatos(este,"escuela",null,1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val resultado = tabla.delete("ALUMNO","NOCONTROL=?", arrayOf(noControl))

            if (resultado == 0) return false

        }catch (err:SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

}

/*
 val baseDatos = BaseDatos(este,"escuela",null,1)
        err = ""
        try {

        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
 */