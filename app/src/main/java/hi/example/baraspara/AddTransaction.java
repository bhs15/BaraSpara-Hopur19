package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddTransaction extends AppCompatActivity {

    //Þráður sem sér um að keyra post fallið og skila gildinu sem það á
    class PostThread implements Runnable{
        private volatile String data;
        @Override
        public void run() {
            try {
                this.data = post("http://10.0.2.2:8080/transactions/saveTransaction", jason.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getData(){return this.data;}
    }



    JSONObject jason;
    String desc[];
    User currentUser;


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        Spinner savingTypes = (Spinner) findViewById(R.id.savingTypeSpinner);
        Intent intent = getIntent();
        currentUser = SerializationUtils.deserialize(intent.getByteArrayExtra("USER"));
        List<SavingType> sT = currentUser.getSt();
        desc = new String[sT.size()];
        for(int i=0;i<sT.size();i++){
            desc[i] = sT.get(i).getName();
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                desc
        );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        savingTypes.setAdapter(spinnerArrayAdapter);

        EditText amount = (EditText) findViewById(R.id.amountInput);



        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = Integer.parseInt(amount.getText().toString());
                Long id = sT.get((int) savingTypes.getSelectedItemId()).getId();

                jason = new JSONObject();
                try {
                    jason.put("amount",total);
                    jason.put("ids",new JSONArray().put(id));

                    PostThread pt = new PostThread();
                    Thread t = new Thread(pt);
                    t.start();
                    t.join();
                    finish();
                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

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