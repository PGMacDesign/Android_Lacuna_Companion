package com.pgmacdesign.lacunacompanion.module.inbox;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.pgmacdesign.lacunacompanion.R;
import com.pgmacdesign.lacunacompanion.client.ClientSide;
import com.pgmacdesign.lacunacompanion.client.JsonParser;

public class ViewMail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mail);
        
        final Bundle EXTRAS = getIntent().getExtras();
        String mailId = "";
        if (EXTRAS != null) {
        	mailId = EXTRAS.getString("mailId");
        }
        
        ClientSide.setContext(ViewMail.this);
        JSONObject result = ClientSide.send(new String[]{mailId}, "/inbox", "read_message");
        
        if (result != null) {
        	JSONObject message = JsonParser.getJO(result, "message");
        	String headingText = JsonParser.getS(message, "from");
        	String messageText = JsonParser.getS(message, "body");
        	
        	TextView mailHeading = (TextView) findViewById(R.id.mailHeading);
        	TextView messageBody = (TextView) findViewById(R.id.messageBody);
        	
        	// Remove some easy stuff that I find to be annoying.
        	messageText = messageText.replaceAll("\\*", "");
        	
        	mailHeading.setText(headingText);
        	messageBody.setText(messageText);
        } 
    }
}
