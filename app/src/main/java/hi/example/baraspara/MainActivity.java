package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    //Þráður sem sér um að keyra post fallið og skila gildinu sem það á
    class PostThread implements Runnable{
        private volatile String data;
        @Override
        public void run() {
            try {
                this.data = post("http://10.0.2.2:8080/login",postData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getData(){return this.data;}
    }

    public static JSONObject postData;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.loginButton);
        TextView loggedIn = (TextView) findViewById(R.id.loggedInStatus);
        EditText username = (EditText) findViewById(R.id.username);
        EditText passowrd = (EditText) findViewById(R.id.password);


        //Allt sem gerist eftir að þú stimplar inn login creds
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData = new JSONObject();
                try {
                    postData.put("id",1);
                    postData.put("savingTypes", null);
                    postData.put("username",username.getText().toString());
                    postData.put("password",passowrd.getText().toString());



                    PostThread pt = new PostThread();
                    Thread thread = new Thread(pt);
                    thread.start();
                    thread.join();

                    String data = pt.getData();
                    JSONObject user = new JSONObject(data);
                    if(user.get("username").equals("ERROR")){
                        loggedIn.setText(
                                getResources().getString(R.string.user_loggedin_headers)+"Invalid log in");
                        return;
                    }

                    loggedIn.setText(getResources().getString(R.string.user_loggedin_headers)+user.get("username").toString());

                    currentUser.setUser(user);
                    sendLogin(v);
                    currentUser = new User();

                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void sendLogin(View view){

        byte[] userbytes = SerializationUtils.serialize(currentUser);
        Intent intent = new Intent(this, HomeScreen.class);
        intent.putExtra("USER",userbytes);
        startActivity(intent);
    }

    //Method sem sér um að posta og skila gögnum frá netþjóni þarf að keyra í thread
    String post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

