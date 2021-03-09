package hi.example.baraspara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button logoutButton = (Button) findViewById(R.id.logOut);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });


        Intent intent = getIntent();
        User currentUser = SerializationUtils.deserialize(intent.getByteArrayExtra("USER"));
        TextView textView = (TextView) findViewById(R.id.Usertest);
        textView.setText(currentUser.getUsername());

        SavingType savingTypes[] = currentUser.getSt().toArray(new SavingType[0]);
        String[] savingTypeNames = new String[savingTypes.length];
        String[] savingTypeAmounts = new String[savingTypes.length];
        String[] savingTypeIds = new String[savingTypes.length];

        System.out.println("Amount of savingTypes = " + savingTypes.length);

        for(int i=0;i<savingTypes.length;i++) {
            SavingType temp = savingTypes[i];

            savingTypeNames[i] = temp.getName();
            savingTypeAmounts[i] = String.valueOf(temp.getAmount());
            savingTypeIds[i] = temp.getId().toString();
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);

        SavingTypeAdapter stAdapter = new SavingTypeAdapter(
                this,
                savingTypeNames,
                savingTypeAmounts,
                savingTypeIds
        );

        rv.setAdapter(stAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    void logout(View view){
        finish();
    }

}