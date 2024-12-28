package com.example.myphotoalbum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateImageActivity extends AppCompatActivity {
    ImageView imageViewUpdateImage;
    EditText editTextUpdateTitle,editTextUpdateDescription;
    Button buttonUpdate;
    Toolbar toolbar;

    private  String title,description;
    private int id;
    private  byte[] image;

    private ActivityResultLauncher<Intent> photoPickerResultLauncher;
    private Bitmap selectedImage;

    private  Bitmap scaleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update);

        imageViewUpdateImage=findViewById(R.id.imageViewUpdateImage);
        editTextUpdateTitle=findViewById(R.id.editTextUpdateTitle);
        editTextUpdateDescription=findViewById(R.id.editTextUpdateDescription);
        buttonUpdate=findViewById(R.id.buttonUpdate);
        toolbar =findViewById(R.id.toolbarUpdateImage);

        id=getIntent().getIntExtra("id",-1);
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");
        image=getIntent().getByteArrayExtra("image");

        editTextUpdateTitle.setText(title);
        editTextUpdateDescription.setText(description);
        imageViewUpdateImage.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));
        registerActivityForPhotoPicker();

        imageViewUpdateImage.setOnClickListener(v->{
            Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            photoPickerResultLauncher.launch(intent);

        });

        buttonUpdate.setOnClickListener(v->{
            updateData();

        });

        toolbar.setNavigationOnClickListener(v->{

        });

    }

    public  void updateData(){
        if(id==-1){
            Toast.makeText(this, "there is a problem with id ", Toast.LENGTH_SHORT).show();
        }
        else {
            String updateTitle = editTextUpdateTitle.getText().toString();
            String updateDescription = editTextUpdateDescription.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("updateTitle", updateTitle);
            intent.putExtra("updateDescription", updateDescription);

            intent.putExtra("id",id);

            if (selectedImage == null) {
                intent.putExtra("image",image);
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            } else {


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

                intent.putExtra("image", image);


            }
            setResult(RESULT_OK, intent); // Send result back to MainActivity
            finish(); // Close AddImageActivity

        }


    }

    public  void registerActivityForPhotoPicker(){

        photoPickerResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result->{
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

                        imageViewUpdateImage.setImageBitmap(selectedImage);

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