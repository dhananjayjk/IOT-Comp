package com.limeparallelogram.socketclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    public String message;
    public static String str="empty";
    public String er;
    public ServerSocket ss=null;
    //public
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText("Messages...");
        Toast.makeText(this, "Hi There", Toast.LENGTH_SHORT).show();
        int i=0;
        try{
            ss = new ServerSocket(5555);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Thread th= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = ss.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    while(true) {
                        final String incomingMessage = in.readLine();// + System.getProperty("line.separator");
                        if (incomingMessage != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(incomingMessage);
                                }
                            });
                        }
                        str += incomingMessage;
                    }

                } catch (Exception ex) {
                    //:TODO Handle exceptions
                    //Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                    er=ex.toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(er);
                        }
                    });
                }

            }
        });
        th.start();
        textView.setText(str);
        //System.out.print(incomingMessage);
        Toast.makeText(this, "IM"+str, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, er, Toast.LENGTH_SHORT).show();

    }
}
