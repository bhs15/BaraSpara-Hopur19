package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {

    //Þráður sem sér um að keyra post fallið og skila gildinu sem það á
    class PostThread implements Runnable{
        private volatile String data;
        public volatile JSONObject postData;
        @Override
        public void run() {
            try {
                this.data = post("http://10.0.2.2:8080/register",postData.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getData(){return this.data;}
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button cancel = (Button) findViewById(R.id.signup_cancel);
        Button submit = (Button) findViewById(R.id.signup_submit);
        TextView username = (TextView) findViewById(R.id.signup_Username);
        TextView password = (TextView) findViewById(R.id.signup_Password);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    User user = new User();
                    user.setId((long) 1);
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());

                    SignUpActivity.PostThread pt = new SignUpActivity.PostThread();
                    JSONObject dat = new JSONObject();
                    dat.put("id",(long) 1);
                    dat.put("savingTypes", null);
                    dat.put("username",user.getUsername());
                    dat.put("password",user.getPassword());
                    pt.postData = dat;
                    Thread th = new Thread(pt);
                    th.start();
                    th.join();

                    user.setUser(new JSONObject(pt.getData()));
                    Intent data = new Intent();
                    data.putExtra("USER", SerializationUtils.serialize(user));
                    setResult(Activity.RESULT_OK,data);
                    finish();

                } catch (InterruptedException | JSONException e){
                    finish();
                }
            }
        });

    }


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