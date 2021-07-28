package com.example.imagechooserusingintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
Button btn;
ImageView imageView;
Uri  uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            //startActivityForResult(intent,1);
            resultLauncher.launch(intent);
        });
    }
ActivityResultLauncher<Intent> resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
    @Override
    public void onActivityResult(ActivityResult result) {

            Intent intent=result.getData();
            uri=intent.getData();
            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);

            }catch (FileNotFoundException fnfe){
                fnfe.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

    }
});
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
         uri=data.getData();
         try{
             Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
             imageView.setImageBitmap(bitmap);

         }catch (FileNotFoundException fnfe){
             fnfe.printStackTrace();

         } catch (IOException e) {
             e.printStackTrace();
         }
        }
    }*/
}