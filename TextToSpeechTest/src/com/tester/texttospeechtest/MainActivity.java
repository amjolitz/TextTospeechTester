package com.tester.texttospeechtest;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Trivial update

public class MainActivity extends Activity{
   
   TextToSpeech tts;
   String temp;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      final Button theButton = (Button) findViewById(R.id.theButton);
      final EditText words = (EditText) findViewById(R.id.words);
      
      tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
         
         @Override
         public void onInit(int status) {
            if(status != TextToSpeech.ERROR){
               tts.setLanguage(Locale.UK);
            }
            
         }
      });
      
      theButton.setOnClickListener(new View.OnClickListener() {        
         @Override
         public void onClick(View v) {
            temp = words.getText().toString();
            tts.speak(temp, TextToSpeech.QUEUE_FLUSH, null);
         }
      });
   }

   @Override
   protected void onPause() {
      if(tts != null){
         tts.stop();
         tts.shutdown();
      }
      super.onPause();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
