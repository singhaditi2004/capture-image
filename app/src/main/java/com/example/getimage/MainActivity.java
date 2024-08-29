package com.example.getimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    AppCompatButton saveBtn,gGallery;
    ImageView img;
    private final int RESULT_CODE=1;
    private final int GALLERY_REQUEST=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        saveBtn=findViewById(R.id.saveBtn);
        img=findViewById(R.id.img);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,RESULT_CODE);
            }
        });
        gGallery=findViewById(R.id.gallery);
        gGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent iG=new Intent(Intent.ACTION_PICK);
                 iG.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 startActivityForResult(iG,GALLERY_REQUEST);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==RESULT_CODE){
                Bitmap bit=(Bitmap)data.getExtras().get("data");
                img.setImageBitmap(bit);
            }
            if(requestCode==GALLERY_REQUEST){
                img.setImageURI(data.getData());
            }
        }
    }
}