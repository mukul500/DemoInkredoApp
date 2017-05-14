package net.xblacky.demoinkredoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.truecaller.android.sdk.ITrueCallback;
import com.truecaller.android.sdk.TrueButton;
import com.truecaller.android.sdk.TrueClient;
import com.truecaller.android.sdk.TrueError;
import com.truecaller.android.sdk.TrueProfile;

public class MainActivity extends AppCompatActivity implements ITrueCallback{
    private TrueClient mTrueClient=null;
    private EditText principle,duration;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mTrueClient= new TrueClient(getApplicationContext(), this);

        ((TrueButton) findViewById(R.id.com_truecaller_android_sdk_truebutton)).setTrueClient(mTrueClient);
    }

    @Override
    public void onSuccesProfileShared(@NonNull TrueProfile trueProfile) {
        Log.e("Name",trueProfile.firstName);
        Log.e("Email",trueProfile.email);
        Log.e("PhoneNumber",trueProfile.phoneNumber);
        Intent i=new Intent(MainActivity.this,CalculateActivity.class);
        i.putExtra("personName",trueProfile.firstName);
        i.putExtra("phoneNumber",trueProfile.phoneNumber);
        startActivity(i);
    }

    @Override
    public void onFailureProfileShared(@NonNull TrueError trueError) {
        Log.e("Error", ""+trueError);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mTrueClient.onActivityResult(requestCode, resultCode, data)) {
            return;
        }

    }
}
