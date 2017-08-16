package com.hridha.Database_Basic_Operation;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class YourProfile extends AppCompatActivity {

    Button btn;
    EditText edit;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new jsontask().execute("http://websitebangladesh.com/person.php");
            }
        });
    }

        public class jsontask extends AsyncTask<String,String,String> {

            @Override
            protected String doInBackground(String... params) {
                HttpURLConnection urlconnection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(params[0]);
                    urlconnection = (HttpURLConnection) url.openConnection();
                    urlconnection.connect();

                    InputStream stream = urlconnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String FinalJson = buffer.toString();
                    JSONObject ParentObj = new JSONObject(FinalJson);
                    JSONArray ParentArr = ParentObj.getJSONArray("student");

                    StringBuffer finalsting = new StringBuffer();
                    for (int i=0; i<ParentArr.length();i++) {
                        JSONObject finalobj = ParentArr.getJSONObject(i);

                        String id = finalobj.getString("id");
                        String name = finalobj.getString("name");
                        String username = finalobj.getString("username");
                        int phone = finalobj.getInt("phone");

                        finalsting.append("Person id:" + id + ", Name:" + name + ", Username:" + username + ", Phone:" + phone +"\n");
                    }

                    return finalsting.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (urlconnection != null) {
                        urlconnection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                text.setText(result);
            }

    }
}
