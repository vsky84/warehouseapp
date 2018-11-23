package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> items;
    private List<Item> displayedItems;
    String[] categories;
    Button btnViewEdit1, btnViewEdit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        prePopulateDummy();
        displayedItems=items;
        refreshViewAdapter();
        categories = new String[]{"Hairdressing","Shampoo & Conditioner","Spa Supplies","Treatment Supplies"};
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, NewItemActivity.class);
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
            case R.id.menu_main_credits:
                Intent intent4 = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent4);
                return true;
            default:
                return true;
        }
    }
    private void refreshViewAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        ItemAdapter itemAdapter= new ItemAdapter(this,R.layout.activity_item_card,displayedItems);

        RecyclerView rvwMain = findViewById(R.id.recView);

        rvwMain.setLayoutManager(layoutManager);
        rvwMain.setAdapter(itemAdapter);
    }
    private void prePopulateDummy() {
        items.add(new Item("01","Hairdressing1","Hairdressing",2));
        items.add(new Item("02","Hairdressing2","Hairdressing",4));
        items.add(new Item("03","Hairdressing3","Hairdressing",6));
        items.add(new Item("04","Shampoo & Conditioner1","Shampoo & Conditioner",6));
        items.add(new Item("05","Shampoo & Conditioner2","Shampoo & Conditioner",9));
        items.add(new Item("06","Shampoo & Conditioner3","Shampoo & Conditioner",1));
        items.add(new Item("07","Spa & Supplies1","Spa & Supplies",1));
        items.add(new Item("08","Spa & Supplies2","Spa & Supplies",2));
        items.add(new Item("09","Spa & Supplies3","Spa & Supplies",3));
        items.add(new Item("10","Treatment Supplies1","Treatment Supplies",2));
        items.add(new Item("11","Treatment Supplies2","Treatment Supplies",2));
        items.add(new Item("12","Treatment Supplies3","Treatment Supplies",2));
    }
}
