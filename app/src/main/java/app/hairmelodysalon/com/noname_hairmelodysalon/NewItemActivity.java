package app.hairmelodysalon.com.noname_hairmelodysalon;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewItemActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private ImageView imageView;
    private boolean imageAdded=false;
    Button btnCreateItem, btnCamera;
    EditText txtId, txtName, txtStock;
    RadioGroup groupCategory;
    Bitmap takenImage,defaultImg;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            takenImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(takenImage);
            imageAdded=true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        defaultImg = (BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.boxicon));
        groupCategory = findViewById(R.id.new_item_category_radiogroup);
        txtId = findViewById(R.id.new_item_id);
        txtName = findViewById(R.id.new_item_name);
        txtStock = findViewById(R.id.new_item_stock);
        imageView = findViewById(R.id.imageView3);
        btnCreateItem = findViewById(R.id.new_item_btnCreate);
        btnCamera = findViewById(R.id.btnCamera);
        if (getIntent().hasExtra("QRCODE")) {
            Item item = getIntent().getExtras().getParcelable("QRCODE");
            imageView.setImageBitmap(item.getPic());
            txtId.setText(item.getId());
            txtName.setText(item.getName());
            txtStock.setText(String.valueOf(item.getStock()));
            if(item.getCategory().equals("Hairdressing")) ;
            else if(item.getCategory().equals("Shampoo & Conditioner")) groupCategory.check(R.id.new_item_shampoo_radio);
            else if(item.getCategory().equals("Spa Supplies")) groupCategory.check(R.id.new_item_spa_radio);
            else if(item.getCategory().equals("Treatment Supplies")) groupCategory.check(R.id.new_item_treatment_radio);
        }
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NewItemActivity.this,new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        btnCreateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isIdFilled=false,isNameFilled=false;
                Intent returnIntent = new Intent();
                Item item;
                if(TextUtils.isEmpty(txtId.getText().toString())) {
                    isIdFilled=false;
                    txtId.setError("The ID field cannot be empty");
                }
                else isIdFilled=true;
                if(TextUtils.isEmpty(txtName.getText().toString())) {
                    isNameFilled=false;
                    txtName.setError("The item name cannot be empty");
                }
                else isNameFilled=true;
                if(TextUtils.isEmpty(txtStock.getText().toString())) {
                    txtStock.setText(String.valueOf(0));
                }
                if(isIdFilled && isNameFilled) {
                    int radioButtonID = groupCategory.getCheckedRadioButtonId();
                    View radioButton = groupCategory.findViewById(radioButtonID);
                    int idx = groupCategory.indexOfChild(radioButton);
                    RadioButton rb = (RadioButton) groupCategory.getChildAt(idx);
                    String categoryText = rb.getText().toString();
                    if(imageAdded) {
                        item = new Item(takenImage,txtId.getText().toString(),txtName.getText().toString(),categoryText,Integer.valueOf(txtStock.getText().toString()));
                        returnIntent.putExtra("NEWITEM",item);
                        setResult(RESULT_OK,returnIntent);
                    }
                    else {
                        item = new Item(defaultImg,txtId.getText().toString(),txtName.getText().toString(),categoryText,Integer.valueOf(txtStock.getText().toString()));
                        returnIntent.putExtra("NEWITEM",item);
                        setResult(RESULT_OK,returnIntent);
                    }
                    finish();
                }

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }
}
