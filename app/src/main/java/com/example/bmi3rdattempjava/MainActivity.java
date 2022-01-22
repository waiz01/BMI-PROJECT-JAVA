package com.example.bmi3rdattempjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME1 = "BMI_RECORD(WEIGHT).txt";
    private static final String FILE_NAME2 = "BMI_RECORD(HEIGHT).txt";
EditText weightText,heightText;
Button calcButton,Aboutbtn;
float weightValue,heightValue,Bmi;
String weight,height,BMIresult,healthtext,calculation,S1,S2,text,BMIrange;
TextView resulttext,BMIrangetv;
    TextView category;
    TextView healthrisk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightText = findViewById (R.id.etWeight) ;
                heightText = findViewById (R.id.etHeight);
                calcButton = findViewById (R.id.btnCalculate);
                resulttext = findViewById(R.id.tvIndex);
                category = findViewById(R.id.tvResult);
                healthrisk = findViewById(R.id.HealthRisk);
                Aboutbtn = findViewById (R.id.aboutUs);
                BMIrangetv = findViewById (R.id.tvInfo);
                Aboutbtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent =new Intent(MainActivity.this,AboutPage.class);
                       startActivity(intent);


                   }
               });
calcButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        weight = weightText.getText().toString();
        height = heightText.getText().toString();




            if(weight.isEmpty() ) {
                Toast.makeText(MainActivity.this, "Weight is empty", Toast.LENGTH_LONG).show();
                return;
            }
            if(height.isEmpty() ){
                Toast.makeText(MainActivity.this, "Height is empty", Toast.LENGTH_LONG).show();
                return;
            }weightValue = Float.parseFloat(weight);
        heightValue = Float.parseFloat(height) ;
            if(heightValue> 5) {
                Bmi = weightValue / ((heightValue / 100) * (heightValue  / 100));
            }

            else
            {  Bmi = weightValue/ ((heightValue) * (heightValue));
            }

        if(Bmi <= 18.4){
            BMIresult = "UnderWeight";
            healthtext = "Malnutrition Risk";
            BMIrange = "(Underweight range is is below 18.4)";
        }else if(Bmi > 18.4 && Bmi <= 24.9){
            BMIresult = "Normal Weight";
            healthtext = "Low Risk";
            BMIrange ="(Normal Range is 18.5 - 24.9)";
        }else if (Bmi > 24.9 && Bmi <= 29.9){
            BMIresult = "Overweight";
            healthtext = "Enchanced Risk";
            BMIrange ="(Overweight Range is 24.9 - 29.9)";
        }else if (Bmi > 29.9 && Bmi <= 34.9){
            BMIresult = "Moderately Obese";
            healthtext = "Medium Risk";
            BMIrange ="(Moderately Obese Range is 29.9 - 34.9)";
        }else if (Bmi > 34.9 && Bmi <= 39.9){
            BMIresult = "Severely Obese";
            healthtext = "High Risk";
            BMIrange ="(Severely Obese Range is 34.9 - 39.9)";
        }else if (Bmi > 39.9){
            BMIresult = "Very Severely Obese";
            healthtext = "Very High Risk";
            BMIrange ="(Very Severely Obese Range is 39.9 > ...)";
        }
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        calculation = df.format(Bmi) + " kg/m²";
        resulttext.setText(calculation);
        category.setText(BMIresult);
        healthrisk.setText(healthtext);
        BMIrangetv.setText(BMIrange);
        String txt1 = weightText.getText().toString();
        String txt2 = heightText.getText().toString();

        FileOutputStream fos1 = null;
        try {
            fos1 = openFileOutput(FILE_NAME1, MODE_PRIVATE);
            fos1.write(txt1.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos1!=null){
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileOutputStream fos2 = null;
        try {
            fos2 = openFileOutput(FILE_NAME2, MODE_PRIVATE);
            fos2.write(txt2.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos2!=null){
                try {
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
});
        FileInputStream fis1 = null;

        try {
            fis1 = openFileInput(FILE_NAME1);
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                weightText.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis2 = null;

        try {
            fis2 = openFileInput(FILE_NAME2);
            InputStreamReader isr = new InputStreamReader(fis2);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                heightText.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis2 != null){
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}

}