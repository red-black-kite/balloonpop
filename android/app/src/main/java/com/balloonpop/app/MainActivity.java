package com.balloonpop.app;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.webkit.JavascriptInterface;
import com.getcapacitor.BridgeActivity;
import java.util.Locale;

public class MainActivity extends BridgeActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        // Register the interface. Capacitor fires 'deviceready' in JS after
        // its bridge initialises, at which point window.AndroidTTS is checked.
        getBridge().getWebView().addJavascriptInterface(new TTSBridge(tts), "AndroidTTS");
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    static class TTSBridge {
        private final TextToSpeech tts;
        TTSBridge(TextToSpeech tts) { this.tts = tts; }

        @JavascriptInterface
        public void speak(String text, float pitch, float rate) {
            if (tts == null) return;
            tts.stop();
            tts.setPitch(pitch);
            tts.setSpeechRate(rate);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "bp_" + text);
        }

        @JavascriptInterface
        public void stop() {
            if (tts != null) tts.stop();
        }
    }
}
