package com.ainadia.lab09

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ainadia.lab09.data.PlaceListAdapter
import com.ainadia.lab09.databinding.ActivityMainBinding
import com.ainadia.lab09.model.Place

class MainActivity : AppCompatActivity() {
    private val REQUEST_CALL_PERMISSION = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val countryList = ArrayList<Place>();
        var kualaLumpur = Place(countryName = "Malaysia", cityName = "KL")
        countryList.add(kualaLumpur)
        var bangkok = Place(countryName = "Thailand", cityName = "Bangkok")
        countryList.add(bangkok)
        var jakarta = Place(countryName = "Indonesia", cityName = "Jakarta")
        countryList.add(jakarta)

        val adapter = PlaceListAdapter(list =  countryList, context = this)
        binding.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    // request to call - guna function onrequestpermission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CALL_PERMISSION) {
            // Check if the permission was granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall()  // Proceed with making the call, invoke function make()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()  // Show denial message
            }
        }
    }

    private fun makeCall() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:013545444")
        startActivity(intent)
    }


    @SuppressLint("QueryPermissionsNeeded")
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){

                R.id.about ->{
                // to open  chrome browser or other website
                //    val intent   = Intent(Intent.ACTION_MAIN)
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://www.google.com")
                    startActivity(intent)
                }
                R.id.email_us ->{
                // to open emel and set email = feedback,  to: "contact@pepoplelogy.com,my"

                    // Open email client with predefined recipient and subject
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:ain.nadia.hanapi@gmail.com") // Email address
                        putExtra(Intent.EXTRA_SUBJECT, "Feedback") // Subject
                        // Optional: Add a body to the email
                        putExtra(Intent.EXTRA_TEXT, "Hello, I would like to provide feedback...")
                    }
                    // Ensure there's an app to handle the intent
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.call_us ->{
                    // call via dialer with preset fon number
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel: 0135454544")
                    startActivity(intent)
                }

                R.id.call_us_now -> {
                    // add second option to call directly  the number

                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                        {
                            makeCall()
                        } else
                        {
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PERMISSION)
                        }


                }



                R.id.exit -> finish()


            }

            return super.onOptionsItemSelected(item)

        }

}