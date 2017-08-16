package com.hridha.Database_Basic_Operation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by hrida on 7/18/2017.
 */

public class BackgroundWorker extends AsyncTask<String,String,String> {

    AlertDialog alertDialog;
    Context ctx;

    BackgroundWorker(Context context){
        ctx = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://websitebangladesh.com/login.php";
        String register_url = "http://websitebangladesh.com/register.php";
        if (type.equals("Login")){
                try {
                    String username = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader= new BufferedReader( new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line ="";
                    while ((line = bufferedReader.readLine())!= null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

        else if (type.equals("Register")){
            try {
                String name = params[1];
                String username = params[2];
                String phone = params[3];
                String password = params[4];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                + URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                + URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader( new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line ="";
                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Welcome to Here")) {
            new jsontask().execute("http://websitebangladesh.com/person.php");
        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
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
                JSONArray ParentArr = ParentObj.getJSONArray("employee");

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
            Intent i = new Intent(ctx, Welcome.class);
            ctx.startActivity(i);
        }

    }
}
