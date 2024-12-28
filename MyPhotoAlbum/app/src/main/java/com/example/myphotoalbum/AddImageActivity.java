package com.example.myphotoalbum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AddImageActivity extends AppCompatActivity {

    private ImageView imageViewAddImage;
    private EditText editTextAddTitle,editTextAddDescription;
    private Button buttonSave;
    private Toolbar toolbar;
    private ActivityResultLauncher<String[]> permissionsResultLauncher;

    private  int deniedPermissionCount=0;

    private  ArrayList<String> permissionsList=new ArrayList<>();

    private ConstraintLayout constraintLayout;

    private ActivityResultLauncher<Intent> photoPickerResultLauncher;
    private Bitmap selectedImage;

    private  Bitmap scaleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        imageViewAddImage=findViewById(R.id.imageViewAddImage);
        editTextAddTitle=findViewById(R.id.editTextAddTitle);
        editTextAddDescription=findViewById(R.id.editTextAddDescription);

        buttonSave=findViewById(R.id.buttonAdd);
        toolbar=findViewById(R.id.toolbarAddImage);
        constraintLayout=findViewById(R.id.mainAddImage);

        if(Build.VERSION.SDK_INT>33){
            permissionsList.add(Manifest.permission.READ_MEDIA_IMAGES);
            permissionsList.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED);

        } else if (Build.VERSION.SDK_INT>32) {
            permissionsList.add(Manifest.permission.READ_MEDIA_IMAGES);
        }else{
            permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        registerActivityForMultiplePermissions();
         registerActivityForPhotoPicker();

        imageViewAddImage.setOnClickListener(v->{

            if(hasPermission()){
                openPhotoPicker();
            }else {
                shouldShowPermissionRelationalNeeded();
            }

        });


        buttonSave.setOnClickListener(v -> {
            if (selectedImage == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            } else {
                String title = editTextAddTitle.getText().toString();
                String description = editTextAddDescription.getText().toString();

                // Compress the image
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                scaleImage = makeSmall(selectedImage, 300); // Reduce dimensions
                scaleImage.compress(Bitmap.CompressFormat.JPEG, 30, outputStream); // Aggressive compression
                byte[] image = outputStream.toByteArray();

                // Check the size of the compressed image
                if (image.length > 1024 * 1024) { // If still larger than 1MB
                    Toast.makeText(this, "Image too large, please select a smaller image", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Pass the compressed image via Intent
                Intent intent = new Intent();
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("image", image);

                setResult(RESULT_OK, intent); // Send result back to MainActivity
                finish(); // Close AddImageActivity
            }
        });

    /*    buttonSave.setOnClickListener(v->{
            if(selectedImage ==null){
                Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show();
            }

            else{
                String title =editTextAddTitle.getText().toString();
                String description=editTextAddDescription.getText().toString();

                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                scaleImage=makeSmall(selectedImage,300);

                selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);



                byte[] image=outputStream.toByteArray();

                if (title.isEmpty()) {
                    Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (description.isEmpty()) {
                    Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedImage == null) {
                    Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent=new Intent();
                intent.putExtra("title",title);
                intent.putExtra("description",description);
                intent.putExtra("image",image);

                setResult(RESULT_OK,intent);
                finish();

            }



        });*/

        toolbar.setNavigationOnClickListener(v->{
            finish();
        });

    }

    public void registerActivityForMultiplePermissions(){
        permissionsResultLauncher =registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),result->{


            boolean allGranted =true;
            for(Boolean isAllowed :result.values()){
                if(!isAllowed){
                    allGranted=false;
                    break;
                }
            }

            if(allGranted){
                //openGallary
                openPhotoPicker();
            }
            else {
                deniedPermissionCount++;
                if(deniedPermissionCount<2){
                    shouldShowPermissionRelationalNeeded();

                }
                   else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(AddImageActivity.this);
                    builder.setTitle("Photo Album");
                    builder.setMessage("You should grant the nesserary permissions to acess to photos from the application settings");
                    builder.setPositiveButton("Go App Setting",(dialog, which) -> {
                        Intent intent= new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri= Uri.parse("package:"+getPackageName());
                        intent.setData(uri);
                        startActivity(intent);
                        dialog.dismiss();

                    });
                    builder.setNegativeButton("Dismiss",(dialog, which) -> {
                        dialog.dismiss();
                    });
                    builder.create().show();

                    }

            }


        });
    }

    public  void shouldShowPermissionRelationalNeeded(){
        ArrayList<String> deniedPermission =new ArrayList<>();

        for(String permission: permissionsList){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                deniedPermission.add(permission);
            }
        }

        if(!deniedPermission.isEmpty()){
            Snackbar.make(constraintLayout,"Please grant necessary permission to add a profil photo",Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK",v->{
                        permissionsResultLauncher.launch(deniedPermission.toArray(new String[0]));
                    }).show();
        }else {
            permissionsResultLauncher.launch(permissionsList.toArray(new String[0]));
        }

    }

    public boolean hasPermission(){
        for(String permission :permissionsList){
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }

        return true;
    }

    public void openPhotoPicker(){
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerResultLauncher.launch(intent);

    }

    public  void registerActivityForPhotoPicker(){

        photoPickerResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result->{
            int resultCode=result.getResultCode();
            Intent data= result.getData();

            if(resultCode==RESULT_OK &&data!=null){

                Uri selectedImageUri=data.getData();
                if(selectedImageUri !=null){
                    try {

                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                        ImageDecoder.Source imageSource=    ImageDecoder.createSource(getContentResolver(),selectedImageUri);
                            selectedImage= ImageDecoder.decodeBitmap(imageSource);

                        }else{
                            selectedImage= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImageUri);

                        }

                        imageViewAddImage.setImageBitmap(selectedImage);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }


            }
        });

    }

    public Bitmap makeSmall(Bitmap image,int maxSize){
        int width=image.getWidth();
        int height=image.getHeight();

        float ratio=(float) width/ (float)  height;

        if(ratio>1){
            width=maxSize;
            height =(int) (width/ratio);
        }
        else{
            height=maxSize;
            width=(int) (height*ratio);
        }
        return Bitmap.createScaledBitmap(image,width,height,true);

    }
}





















