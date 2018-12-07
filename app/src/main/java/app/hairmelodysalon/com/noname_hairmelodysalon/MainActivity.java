package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> items;
    private List<Item> displayedItems;
    String[] categories;
    EditText searchText;
    Button btnViewEdit1, btnViewEdit2;
    private IntentIntegrator intentIntegrator;
    private static final String PREFERENCES_FILENAME = "search";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final int REQUEST_CODE_SEARCH =200;;
    private Button buttonSearch;
    String pickedCategoryOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        prePopulateDummy();
        displayedItems=items;
        refreshViewAdapter();
        categories = new String[]{"Hairdressing","Shampoo & Conditioner","Spa Supplies","Treatment Supplies"};
        searchText = findViewById(R.id.searchText);
        buttonSearch = findViewById(R.id.btnSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayedItems = searchForItems();
                refreshViewAdapter();
            }
        });
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
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_main_home:
                displayedItems = items;
                refreshViewAdapter();
                return true;
            case R.id.menu_main_filter:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Filter by");
                dialogBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pickedCategoryOption = categories[which];
                        List <Item> categoryResult = new ArrayList<>();
                        for(Item item : items) {
                            if(item.getName().contains(pickedCategoryOption)) {
                                categoryResult.add(item);
                            }
                        }
                        displayedItems = categoryResult;
                        refreshViewAdapter();
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
                intentIntegrator = new IntentIntegrator(this);
                intentIntegrator.initiateScan();
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
    public List<Item> searchForItems() {

        String search = searchText.getText().toString();
        List<Item> itemResults = new ArrayList<>();
        for(Item item : items) {
            if(item.getName().toLowerCase().contains(search)) {
                itemResults.add(item);
            }
        }
        return itemResults;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult Result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (Result != null) {
            if (Result.getContents() == null) {
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject object = new JSONObject(Result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, Result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode== REQUEST_CODE_SEARCH && resultCode==RESULT_OK) {
                displayedItems = searchForItems();
                refreshViewAdapter();
            }
        }
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
