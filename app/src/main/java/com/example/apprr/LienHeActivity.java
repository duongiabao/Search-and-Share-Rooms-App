package com.example.apprr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LienHeActivity extends AppCompatActivity {
    TextView txtLienheName;
    TextView txtLienheEmail;
    TextView txtLienhePhone;

    private static final int REQUEST_CALL=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);

        txtLienheName= findViewById(R.id.textview_lienhe_name);
        txtLienheEmail= findViewById(R.id.text_lienhe_email);
        txtLienhePhone= findViewById(R.id.text_lienhe_phone);

        txtLienhePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        txtLienheName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LienHeActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });



    }

    private void makePhoneCall() {
        String number= "0913679239";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(LienHeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(LienHeActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            }
            else {
                String dial= "tel: " + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }

        }else {

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CALL){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
