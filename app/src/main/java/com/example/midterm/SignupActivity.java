package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    TextView login;
    TextView errorMessage;

    EditText txtEmail;
    EditText txtName;
    EditText txtPwd;
    EditText txtPwdRep;

    DatePickerDialog picker;
    EditText txtDate;

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtName = (EditText) findViewById(R.id.txtName);
        txtPwd = (EditText) findViewById(R.id.txtPwd);
        txtPwdRep = (EditText) findViewById(R.id.txtPwdRep);

        errorMessage = (TextView) findViewById(R.id.errorMessage);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtEmail.getText())
                        || TextUtils.isEmpty(txtName.getText())
                        || TextUtils.isEmpty(txtPwd.getText())
                        || TextUtils.isEmpty(txtPwdRep.getText())) {
                    errorMessage.setText("All Fields are mandatory");
                } else if (!String.valueOf(txtPwd.getText()).equals(String.valueOf(txtPwdRep.getText()))) {
                    errorMessage.setText("Passwords should match");
                } else if (!isEmailValid(String.valueOf(txtEmail.getText()))) {
                    errorMessage.setText("");
                    txtEmail.setError("Invalid Email");
                } else if (String.valueOf(txtName.getText()).length()<6 || String.valueOf(txtName.getText()).length()>20) {
                    txtName.setError("Name should consist from 6-20 characters");
                } else if (validPassword(String.valueOf(txtPwd.getText()))) {
                    txtPwd.setError("8-20 characters, should contain digit and one capital letter");
                } else {
                    Toast.makeText(getApplicationContext(),"Successfully signed up",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtDate=(EditText) findViewById(R.id.txtDate);
        txtDate.setInputType(InputType.TYPE_NULL);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(SignupActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        login = (TextView)findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validPassword(String pass) {
        if (pass.length()<8 || pass.length()>20 ) {
            return false;
        }
        boolean uppercase = false;
        boolean digit = false;
        for (int i=0; i<pass.length(); i++) {
            if (Character.isUpperCase(pass.charAt(i))) {
                uppercase = true;
            }
            if (Character.isDigit(pass.charAt(i))) {
                uppercase = true;
            }
        }
        if (uppercase && digit) {
            return true;
        } else {
            return false;
        }
    }


}
