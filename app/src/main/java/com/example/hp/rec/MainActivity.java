package com.example.hp.rec;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends Activity  {
    Button set,clear,settings,change;
    static String pass,sec,passs;
    String oldpass,newpass;
    public String data="0";
    public String file="my data";
    String counter="0",counter1="0";
    EditText password,securityans,newpassword,oldpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change=(Button)findViewById(R.id.change);
        clear = (Button) findViewById(R.id.clear);
        set = (Button) findViewById(R.id.set);
        password = (EditText) findViewById(R.id.password);
        securityans=(EditText)findViewById(R.id.securityans);
        settings=(Button)findViewById(R.id.settings);
        newpassword=(EditText)findViewById(R.id.newpassword);
        oldpassword=(EditText)findViewById(R.id.oldpassword);


       // MainActivity.Receiver ob=new MainActivity.Receiver();
        //ob.loadSavedPreferences();
        loadSavedPreferences();

       // Toast.makeText(getApplicationContext(),counter, Toast.LENGTH_LONG).show();


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //              save(v);
                loadSavedPreferences();
                pass = password.getText().toString();
                sec = securityans.getText().toString();


                if(counter.equals("0")&&(pass.equals("")&&(sec.equals("")))) {

                   Toast.makeText(getApplicationContext(), "set password first", Toast.LENGTH_LONG).show();



                }

                else
                {
                    counter="1";

                    savePreferences("saks",counter);


                    loadSavedPreferences();
                pass = password.getText().toString();
                sec = securityans.getText().toString();
                if ((pass.equals(""))||(sec.equals("")))
                {if ((counter.equals("1")))
                {
                    Toast.makeText(getApplicationContext(), "enter proper information", Toast.LENGTH_SHORT).show();}

                }

                else
                {
                    if ((counter1.equals("0")))
                    {
                    savePreferences("saksham", pass);
                    savePreferences("agarwal", sec);

                    counter1 = "1";

                    savePreferences("aa", counter1);

                    Toast.makeText(getApplicationContext(), "password saved successfully", Toast.LENGTH_LONG).show();
                }
                    else
                {
                    Toast.makeText(getApplicationContext(), "password already saved", Toast.LENGTH_LONG).show();
                }
                }
                    //Toast.makeText(getApplicationContext(), "value of counter"+counter, Toast.LENGTH_LONG).show();

            }

            }
        });



change.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "entry", Toast.LENGTH_SHORT).show();
        oldpass=oldpassword.getText().toString();
        newpass=newpassword.getText().toString();
       // Toast.makeText(getApplicationContext(),oldpass+newpass, Toast.LENGTH_SHORT).show();

      loadSavedPreferences();
      if (counter.equals("0"))
      {
          Toast.makeText(getApplicationContext(),"set password first", Toast.LENGTH_SHORT).show();
      }

else{

       if (!(oldpass.equals(""))&&(!newpass.equals("")))
       {
        if(oldpass.equals(pass))
        {
            pass=newpass;
            //Toast.makeText(getApplicationContext(),pass, Toast.LENGTH_SHORT).show();
            savePreferences("saksham",pass);
            loadSavedPreferences();
           // password.setText(pass);
            Toast.makeText(getApplicationContext(),"password changed successfully", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(),"old password did not match", Toast.LENGTH_SHORT).show();
        }
    }
    else
       {
           Toast.makeText(getApplicationContext(),"enter both fields", Toast.LENGTH_SHORT).show();
       }
    }}
});



        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password.setText("");
                oldpassword.setText("");
                newpassword.setText("");
                securityans.setText("");
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle basket = new Bundle();
                loadSavedPreferences();
                if(counter1.equals("1"))
                {
                    basket.putString("abc", "" + pass);
                basket.putString("efg",""+sec);}
              //  basket.putString("ijk",""+counter1);

                Intent a = new Intent(v.getContext(),settings.class);
                a.putExtras(basket);
                startActivity(a);}



        });
    }


    private void savePreferences(String key, String value) {

        //Toast.makeText(getApplicationContext(),"value in save fun="+value, Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
      //  Toast.makeText(getApplicationContext(),"value+key"+value+key, Toast.LENGTH_SHORT).show();
        editor.commit();
    }


    public void loadSavedPreferences()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        pass=sharedPreferences.getString("saksham",null);
        sec=sharedPreferences.getString("agarwal",null);
            counter=sharedPreferences.getString("saks","0");
        counter1=sharedPreferences.getString("aa","0");
        //Toast.makeText(getApplicationContext(),"your password is saved already", Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"to know your password go to settings and help", Toast.LENGTH_LONG).show();


        //Toast.makeText(getApplicationContext(),"loadingg"+pass+sec, Toast.LENGTH_LONG).show();
        //password.setText(pass);
        //securityans.setText(sec);
    }



/*
@Override
public void onResume()
{
    super.onResume();
    loadSavedPreferences();
}

@Override
public void onStart()
{
    super.onStart();
    loadSavedPreferences();
}*/


    //broadcast receiver as inner class
     public static class Receiver extends BroadcastReceiver
{   //declaring variables just for checking if your phone is silent or not
    private AudioManager mAudioManager;
    private Boolean mPhoneIsSilent,mPhoneIsVibrate,mPhoneIsNormal;

    //string object reference variable
     public String me = "";
    public String last = "";
    public static final String SMS_BUNDLE = "pdus";

    @Override
    public void onReceive(Context context, Intent intent)
    {





    mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
   //Toast.makeText(context,"yes it is done",Toast.LENGTH_LONG).show();



          Bundle intentExtras = intent.getExtras();
          if (intentExtras != null)
          {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";

            //scanning full message and saving word by word that is why we use 1d array foe receiving
            for (int i = 0; i < sms.length; ++i)
            {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);


                String smsBody = smsMessage.getMessageBody();
                String address = smsMessage.getOriginatingAddress();
                me += smsBody;


                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }
            //toast the sender address and message it send
     //       Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();
       //     Toast.makeText(context, me, Toast.LENGTH_SHORT).show();

          }



             //now checking id phone is silent or not
             checkIfPhoneIsSilent();
             checkIfPhoneIsVibrate();
             checkIfPhoneIsNormal();

       //Toast.makeText(context,pass, Toast.LENGTH_LONG).show();
        if (me.equals("@general_"+pass))

        {if (!mPhoneIsNormal) {
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            mPhoneIsNormal = false;

        }

            Toast.makeText(context, "so your phone is now in general mode", Toast.LENGTH_LONG).show();
        }




        if (me.equals("@silent_"+pass))

        {
            if (!mPhoneIsSilent) {
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                mPhoneIsSilent = false;
                Toast.makeText(context, "so your phone is now in silent mode", Toast.LENGTH_LONG).show();
            }
        }



        if (me.equals("@vibrate_"+pass))

        {
            if (!mPhoneIsVibrate) {
                mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                mPhoneIsVibrate = false;
                Toast.makeText(context, "so your phone is now in silent mode", Toast.LENGTH_LONG).show();
            }
        }



        }//end of on recieve function




               private void checkIfPhoneIsSilent()
               {
               int ringermode = mAudioManager.getRingerMode();
               if (ringermode == AudioManager.RINGER_MODE_SILENT)
               {
               mPhoneIsSilent = true;
               }
               else
               {
               mPhoneIsSilent = false;
               }
               }


    private void checkIfPhoneIsVibrate()
    {
        int ringermode = mAudioManager.getRingerMode();
        if (ringermode == AudioManager.RINGER_MODE_VIBRATE)
        {
            mPhoneIsVibrate = true;
        }
        else
        {
            mPhoneIsVibrate = false;
        }
    }

    private void checkIfPhoneIsNormal()
    {
        int ringermode = mAudioManager.getRingerMode();
        if (ringermode == AudioManager.RINGER_MODE_NORMAL)
        {
            mPhoneIsNormal = true;
        }
        else
        {
            mPhoneIsNormal = false;
        }
    }

}//end of inner class

}//end of outer class
