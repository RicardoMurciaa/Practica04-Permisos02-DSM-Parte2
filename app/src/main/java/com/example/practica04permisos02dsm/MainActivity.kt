package com.example.practica04permisos02dsm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "Practica 04 - Permisos"
    private val CODIFOSOLICITUDGRABAR = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarPermisos()
        }

    private fun configurarPermisos(){
        val permission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
        if (permission != PackageManager.PERMISSION_GRANTED){
            Log.i(TAG, "Permiso denegado para grabar")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.RECORD_AUDIO)) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Permiso para acceder al microfono es requerido por esta App para grabar audio.")
                    .setTitle("Permission required")
                builder.setPositiveButton("OK") { dialog, id -> Log.i(TAG, "Seleccionado")
                    hacerSolicitudPermiso()
                }
                val dialog = builder.create()
                dialog.show()
            } else {
                hacerSolicitudPermiso()
            }
        }
    }

    private fun hacerSolicitudPermiso() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO), CODIFOSOLICITUDGRABAR)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions,grantResults)
        when (requestCode) {
            CODIFOSOLICITUDGRABAR -> {
                if (grantResults.isEmpty()||grantResults[0] !=
                    PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permiso a sido denegado por el usuario")

                    Toast.makeText(applicationContext,"Permiso a sido denegado por el usuario",
                        Toast.LENGTH_SHORT).show()
                    /* Escribir Codigo de permiso Denegado, segun se requiera */
                } else {
                    Log.i(TAG, "Permiso concedido por el usuario")
                    Toast.makeText(applicationContext, "Permiso concedido por el usuario", Toast.LENGTH_SHORT).show()
                    /* Escribir Codigo de Permiso Concedido, segun se requiera */
                }
            }
        }
    }
}