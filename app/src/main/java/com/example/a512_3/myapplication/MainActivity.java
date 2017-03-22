package com.example.a512_3.myapplication;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener  {

    protected Button btSpeech,button2;
    protected TextView TxSpeech;
    private static final int SPEECH_CODE = 1234;
    protected TextToSpeech tts;

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.KOREAN);//한국말 설정
            tts.setPitch(0.5f);
            tts.setSpeechRate(1.0f);
        }
    }


 protected void onActivityResult(int requestCode, int resultCode, Intent data){
     super.onActivityResult(requestCode, resultCode, data);
     if(requestCode == SPEECH_CODE){
         if(resultCode == RESULT_OK && data != null){
             ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
             String strSpeech = arrayList.get(0);
             TxSpeech.setText(strSpeech);
         }

     }


 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxSpeech = (TextView)findViewById(R.id.TxSpeech);
        btSpeech = (Button)findViewById(R.id.btSpeech);
        button2=(Button)findViewById(R.id.button2);


        btSpeech.setOnClickListener(new View.OnClickListener() {




            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

               // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
               //         RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.KOREAN);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Recognizeing...");
                startActivityForResult(intent, SPEECH_CODE);


            }
           });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String text = TxSpeech.getText().toString();
                  tts.speak(text,TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });


        tts = new TextToSpeech(this, this);

        }


        }

