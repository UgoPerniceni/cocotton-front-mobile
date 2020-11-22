package fr.esgi.cocotton

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import fr.esgi.cocotton.model.Recipe


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
/*
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SettingFragment.newInstance())
                    .commitNow()
*/
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun login() {
        val name = findViewById<EditText>(R.id.new_recipe_form_name)
        val time = findViewById<EditText>(R.id.new_recipe_form_time)

        val newRecipe = Recipe("${name.text}", "${time.text}", "/path")

        Log.d("info","add new recipe to DB : $newRecipe")

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("Recipe")

        myRef.setValue("$newRecipe")
    }
}