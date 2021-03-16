package com.example.tarea;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Seleccionamos el text view de la vista
        text = (TextView) findViewById(R.id.txt);
    }

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case RECOGNIZE_SPEECH_ACTIVITY:
                //Se transforma en texto lo que se grabo por voz
                    if(resultCode == RESULT_OK){
                        ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        String strSpeech2Text = speech.get(0);
                        text.setText(strSpeech2Text);
                    }
                    break;
            default:
                break;
        }
    }

    public void Talk(View view){
        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        );
        //Configuracion de lenguaje
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE, "es-MX"
        );
        try{
            startActivityForResult(
                    intentActionRecognizeSpeech, RECOGNIZE_SPEECH_ACTIVITY
            );
        }catch(ActivityNotFoundException exc){
            Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta texto por voz", Toast.LENGTH_SHORT).show();
        }
    }
}