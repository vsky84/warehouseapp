package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class QrcodeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnScanQR;
    private TextView textViewID, textViewStock, textViewItem;
    private IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        btnScanQR = findViewById(R.id.qrcode_btnScan);
        textViewID = findViewById(R.id.qrcode_textViewID);
        textViewItem = findViewById(R.id.qrcode_textViewItem);
        textViewStock = findViewById(R.id.qrcode_textViewStock);
        btnScanQR.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult Result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (Result != null) {
            if (Result.getContents() == null) {
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            } else {
                Bitmap defaultImg = (BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.boxicon));
                String[] tokens = Result.getContents().split("\\+");
                Item item = new Item(defaultImg,tokens[0],tokens[1],tokens[2],Integer.valueOf(tokens[3]));
                Intent intent = new Intent();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }
}
