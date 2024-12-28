package com.example.sendsms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText message,number;
    Button send;
    String userMesssage;
    String userNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send=findViewById(R.id.buttonSend);
        message=findViewById(R.id.editTextMessage);
        number=findViewById(R.id.editTextNumber);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userMesssage= message.getText().toString();
              userNumber=  number.getText().toString();

              sendSMS(userMesssage,userNumber);
            }
        });

    }

    public  void sendSMS(String userMessage,String userNumber){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        }

        else{
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(userNumber,null,userMessage,null,null);
            Toast.makeText(this, "Message sent successfully !", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==1 && grantResults.length>1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(userNumber,null,userMesssage,null,null);
            Toast.makeText(this, "Permission denied to send SMS!", Toast.LENGTH_SHORT).show();
        }
    }
}