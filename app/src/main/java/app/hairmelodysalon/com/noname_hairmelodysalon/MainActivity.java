package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String[] categories;
    Button btnViewEdit1, btnViewEdit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = new String[]{"Hairdressing","Shampoo & Conditioner","Spa Supplies","Treatment Supplies"};
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btnViewEdit1 = findViewById(R.id.main_button_viewedit1);
        btnViewEdit2 = findViewById(R.id.main_button_viewedit2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, NewItemActivity.class);
                startActivity(intent1);
            }
        });
        btnViewEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ItemDetailsActivity.class);
                startActivity(intent1);
            }
        });
        btnViewEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ItemDetailsActivity.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_main_search:
                Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_main_home:
                return true;
            case R.id.menu_main_filter:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Filter by");
                dialogBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialogObj = dialogBuilder.create();
                dialogObj.show();
                return true;
            case R.id.menu_main_qrcode:
                Intent intent2 = new Intent(MainActivity.this, QrcodeActivity.class);
                startActivity(intent2);
                return true;
            case R.id.menu_main_settings:
                Intent intent3 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.menu_main_credits:
                Intent intent4 = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent4);
                return true;
            default:
                return true;
        }
    }
}
