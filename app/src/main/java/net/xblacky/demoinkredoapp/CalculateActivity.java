package net.xblacky.demoinkredoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.truecaller.android.sdk.TrueButton;
import com.truecaller.android.sdk.TrueClient;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by xBlacky on 5/14/2017.
 */



public class CalculateActivity extends AppCompatActivity {

    private TextView name,phoneNumber;
    private EditText principle,duration;
    private float principleValue, rate;
    private int durationValue;
    private float rPower[];
    private Button calculate;
    private ArrayList<InterestData> ar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_information);
        name= (TextView) findViewById(R.id.name);
        phoneNumber=(TextView) findViewById(R.id.phoneNumber);

        name.setText(getIntent().getStringExtra("personName"));
        phoneNumber.setText(getIntent().getStringExtra("phoneNumber"));

        calculate= (Button) findViewById(R.id.calculate);

        principle= (EditText) findViewById(R.id.principle);
        duration= (EditText) findViewById(R.id.duration);
        ListView  list= (ListView) findViewById(R.id.list_view);
        ar=new ArrayList<>();
        final ListAdapter adapter=new ListAdapter(this,ar);
        list.setAdapter(adapter);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(principle.getText().toString().isEmpty())
                {
                    Toast.makeText(CalculateActivity.this,"Please Enter Principle Value",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(duration.getText().toString().isEmpty()){
                    Toast.makeText(CalculateActivity.this,"Please Enter Duration",Toast.LENGTH_SHORT).show();
                    return;
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                principleValue= Float.parseFloat(principle.getText().toString());
                durationValue= Integer.parseInt(duration.getText().toString());

                adapter.clear();
                rate=36/12;
                rate=rate/100;
                int startN,endN;
                if((durationValue-3)>0)
                {
                    startN=durationValue-3;
                }
                else{
                    startN=1;
                }
                Log.e("Rate Value",""+rate);

                endN=durationValue+3;

                rPower=new float[7];
                float sum=1;
                for(int i=1; i<=endN; i++)
                {
                    sum=sum*(1+rate);
                    if(i>=startN) {
                        rPower[i-startN] = sum;
                        Log.e("Rate ", "" + rPower[i-startN]);
                    }
                }
                Log.e("PrincipleValue ",""+principleValue);
                float Emi=principleValue*rate;
                ar.clear();
                for(int i=startN; i<=endN; i++)
                {
                    float x=(rPower[i-startN])/(rPower[i-startN]-1);
                    float finalEMI= Emi*x;
                    float totalvalue=finalEMI*i;
                    Log.e("EMI",""+finalEMI);
                    Log.e("TOTal Payment",""+totalvalue);
                    InterestData id= new InterestData();
                    id.setEmi(finalEMI);
                    id.setTotalPayment(totalvalue);
                    id.setMonth(i);
                    ar.add(id);
                }

                adapter.notifyDataSetChanged();

            }
        });





    }
}
