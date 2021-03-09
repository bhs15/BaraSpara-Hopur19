package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData = new JSONObject();
                try {
                    postData.put("id",1);
                    postData.put("savingTypes", null);
                    postData.put("username","123");
                    postData.put("password","123");



                    PostThread pt = new PostThread();
                    Thread thread = new Thread(pt);
                    thread.start();
                    thread.join();

                    String data = pt.getData();
                    System.out.println(data);

                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
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

