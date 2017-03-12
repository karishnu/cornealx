package com.camtech.corneatopo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ProcessActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        imageView = (ImageView) findViewById(R.id.image);

        String path1 = Environment.getExternalStorageDirectory() + "/photo.jpg";
        Bitmap bitmap1 = BitmapFactory.decodeFile(path1);
        imageView.setImageBitmap(bitmap1);
    }
}
