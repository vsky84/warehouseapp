package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {
    Item item;
    Button btnAdd, btnMinus, btnClose;
    TextView textId, textName;
    EditText txtStock;
    int currentStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        item = (Item) getIntent().getExtras().getParcelable("ITEMCHOICE");
        btnAdd = findViewById(R.id.item_details_btnAdd);
        btnMinus = findViewById(R.id.item_details_btnMinus);
        btnClose = findViewById(R.id.item_details_btnClose);
        txtStock = findViewById(R.id.item_details_txtStock);
        textId = findViewById(R.id.textView_item_details_id);
        textName = findViewById(R.id.textView_item_details_name);
        textId.setText("ID: " + item.getId());
        textName.setText("Name: " + item.getName());
        txtStock.setText(String.valueOf(item.getStock()));
        currentStock = item.getStock();
        txtStock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtStock.getText().toString().matches("")) return;
                item.setStock(currentStock);
                currentStock = Integer.valueOf(txtStock.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("UPDATEDITEM",item);
                returnIntent.putExtra("ITEMPOS",getIntent().getExtras().getInt("ITEMPOS"));
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStock=currentStock+1;
                item.setStock(currentStock);
                txtStock.setText(String.valueOf(currentStock));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentStock>0) {
                    currentStock=currentStock-1;
                    item.setStock(currentStock);
                    txtStock.setText(String.valueOf(currentStock));
                }

            }
        });
    }
}
