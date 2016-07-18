package com.example.hp.rec;
import com.example.hp.rec.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class settings extends Activity {

    EditText securityans;
    Button submit;
    TextView yourpassword;
    static String pass,security,secur,aabb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main);

        submit=(Button)findViewById(R.id.submit);
        securityans=(EditText)findViewById(R.id.securityans);

        yourpassword=(TextView)findViewById(R.id.yourpassword);

        Bundle gt=getIntent().getExtras();
        pass=gt.getString("abc");
        security=gt.getString("efg");
       // aabb=gt.getString("ijk");
        //Toast.makeText(getApplicationContext(),pass+security, Toast.LENGTH_SHORT).show();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                secur=securityans.getText().toString();
             //   Toast.makeText(getApplicationContext(),secur+security, Toast.LENGTH_SHORT).show();

                if(secur.equals(""))
                {
                    Toast.makeText(getApplication(),"enter security answer",Toast.LENGTH_SHORT).show();
                    yourpassword.setText("");
                }
           else if((secur.equals(security)))
            {
                yourpassword.setText(pass);
            }

                else
            {
                yourpassword.setText("wrong security answer");
            }}

        });



    }}
