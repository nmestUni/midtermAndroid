package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallActivity extends AppCompatActivity {

    Button btnCall;
    Button btnMessage;
    EditText mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        btnCall = (Button) findViewById(R.id.btnCall);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        mobile = (EditText) findViewById(R.id.mobile);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidE123(String.valueOf(mobile.getText()))) {
                    String mobileNumber = String.valueOf(mobile.getText());
                    Intent callIntent = new Intent();
                    callIntent.setAction(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + mobileNumber));
                    startActivity(callIntent);
                } else {
                    Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidE123(String.valueOf(mobile.getText()))) {
                    Intent messageIntent = new Intent();
                    messageIntent.setAction(Intent.ACTION_SEND);
                    messageIntent.putExtra(Intent.EXTRA_TEXT, "Default Value");
                    messageIntent.setType("text/plain");
                    startActivity(messageIntent);
                } else {
                    Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidE123(String s)
    {
        boolean result = true;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)!='+' && !Character.isDigit(s.charAt(i))) {
                result = false;
            }
        }
        return result;
    }
}
