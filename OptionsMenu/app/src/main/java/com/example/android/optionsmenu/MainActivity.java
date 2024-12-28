package com.example.android.optionsmenu;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button);
        registerForContextMenu(button); //attach a context menu to the button using context_menu.xml


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_red) {
            Toast.makeText(getApplicationContext(), "Red Colour Selected",
                    Toast.LENGTH_LONG).show();
            return true;
        }else  if (id == R.id.action_green) {
            Toast.makeText(getApplicationContext(), "Green Colour Selected",
                    Toast.LENGTH_LONG).show();
            return true;
        } else  if (id == R.id.action_blue) {
            Toast.makeText(getApplicationContext(), "Blue Colour Selected",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.context_menu, menu);
//    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item){
//
//        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int id = item.getItemId();
//
//        if (id == R.id.action_one) {
//            Toast.makeText(getApplicationContext(), "You chose the first option",
//                    Toast.LENGTH_LONG).show();
//            return true;
//        }else  if (id == R.id.action_two) {
//            Toast.makeText(getApplicationContext(), "You chose the second option",
//                    Toast.LENGTH_LONG).show();
//            return true;
//        }
//        return true;
//    }
}
