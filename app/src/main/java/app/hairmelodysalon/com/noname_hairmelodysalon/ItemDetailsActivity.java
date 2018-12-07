package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {
    Button btnAdd, btnMinus, btnClose;
    EditText txtStock;
    int currentStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        btnAdd = findViewById(R.id.item_details_btnAdd);
        btnMinus = findViewById(R.id.item_details_btnMinus);
        btnClose = findViewById(R.id.item_details_btnClose);
        txtStock = findViewById(R.id.item_details_txtStock);
        currentStock = 0;
        txtStock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtStock.getText().toString().matches("")) return;
                currentStock = Integer.valueOf(txtStock.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStock=currentStock+1;
                txtStock.setText(String.valueOf(currentStock));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentStock>0) {
                    currentStock=currentStock-1;
                    txtStock.setText(String.valueOf(currentStock));
                }

            }
        });
    }
}
