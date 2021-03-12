package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddSavingType extends AppCompatActivity {

    User currentUser;
    EditText name;
    EditText desc;
    EditText amount;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saving_type);

        currentUser = SerializationUtils.deserialize(getIntent().getByteArrayExtra("USER"));


        name = (EditText) findViewById(R.id.SavingTypeN);
        desc = (EditText) findViewById(R.id.SavingTypeD);
        amount = (EditText) findViewById(R.id.SavingTypeA);




        Button cancel = (Button) findViewById(R.id.cancelSavingType);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button submit = (Button) findViewById(R.id.submitSavingType);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("id",128);
                    postData.put("amount", Integer.parseInt(amount.getText().toString()));
                    postData.put("name", name.getText().toString());
                    postData.put("desc",desc.getText().toString());
                    postData.put("date","2021-03-12T00:54:04.720+00:00");

                    PostThread pt = new PostThread();
                    pt.setPostData(postData);
                    Thread t = new Thread(pt);
                    t.start();
                    t.join();

                    User nextUser = new User();
                    nextUser.setUser(new JSONObject(pt.getData()));

                    System.out.println(postData.toString());

                    currentUser =  nextUser;
                    Intent data = new Intent();
                    data.putExtra("USER",SerializationUtils.serialize(currentUser));
                    setResult(Activity.RESULT_OK,data);
                    finish();

                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }



            }
        });




    }


    //Þráður sem sér um að keyra post fallið og skila gildinu sem það á
    class PostThread implements Runnable{
        private volatile String data;
        public volatile JSONObject postData;
        @Override
        public void run() {
            try {
                this.data = post(
                        "http://10.0.2.2:8080/savingTypes/addSavingType/"
                        +currentUser.getId(),
                        postData.toString()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getData(){return this.data;}
        public void setPostData(JSONObject postData){
            this.postData = postData;
        }
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