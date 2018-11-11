package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewItemActivity extends AppCompatActivity {
    Button btnCreateItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        btnCreateItem = findViewById(R.id.new_item_btnCreate);
        btnCreateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
