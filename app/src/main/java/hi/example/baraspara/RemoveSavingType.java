package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RemoveSavingType extends AppCompatActivity {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    User currentUser;
    Long deleteID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_saving_type);

        currentUser = SerializationUtils.deserialize(getIntent().getByteArrayExtra("USER"));
        List<SavingType> st = currentUser.getSt();

        String names[] = new String[st.size()];
        Long ids[] = new Long[st.size()];

        for(int i=0;i<st.size();i++){
            names[i] = st.get(i).getName();
            ids[i] = st.get(i).getId();
        }

        Spinner deathrow = (Spinner) findViewById(R.id.removeSavingTypeSpinner);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                names
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deathrow.setAdapter(spinnerArrayAdapter);


        Button cancel = (Button) findViewById(R.id.removeSavingTypeCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button submit = (Button) findViewById(R.id.removeSavingTypeSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deleteID = st.get((int)deathrow.getSelectedItemId()).getId();

                    PostThread pt = new PostThread();
                    Thread th = new Thread(pt);
                    th.start();
                    th.join();

                    JSONObject newUser = new JSONObject(pt.getData().toString());
                    User nUser = new User();
                    nUser.setUser(newUser);
                    currentUser = nUser;

                    Intent data = new Intent();
                    data.putExtra("USER",SerializationUtils.serialize(currentUser));
                    setResult(Activity.RESULT_OK,data);
                    finish();

                } catch (InterruptedException | JSONException e) {
                    finish();
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
                        "http://10.0.2.2:8080/savingTypes/remove/"
                                +currentUser.getId()+"/"+deleteID
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
    String post(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }



}