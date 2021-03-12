package hi.example.baraspara;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeScreen extends AppCompatActivity {
    public User currentUser;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static final int ADD_SAVING_TYPE_CODE = 97;
    public static final int REMOVE_SAVING_TYPE_CODE = 2020;

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


        Button addSavingType = (Button) findViewById(R.id.addST);
        addSavingType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,AddSavingType.class);
                intent.putExtra("USER",SerializationUtils.serialize(currentUser));
                //97 er þá custom request code
                startActivityForResult(intent, ADD_SAVING_TYPE_CODE);

            }
        });

        Button removeSavingType = (Button) findViewById(R.id.removeSavingType);
        removeSavingType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,RemoveSavingType.class);
                intent.putExtra("USER",SerializationUtils.serialize(currentUser));
                //97 er þá custom request code
                startActivityForResult(intent, REMOVE_SAVING_TYPE_CODE);

            }
        });

        Button addTransaction = (Button) findViewById(R.id.addTransaction);
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLogin(v);
            }
        });



        Intent intent = getIntent();
        currentUser = SerializationUtils.deserialize(intent.getByteArrayExtra("USER"));
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

        RecyclerView rv = (RecyclerView) findViewById(R.id.transactionViewer);

        SavingTypeAdapter stAdapter = new SavingTypeAdapter(
                this,
                savingTypeNames,
                savingTypeAmounts,
                savingTypeIds
        );

        rv.setAdapter(stAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_SAVING_TYPE_CODE | requestCode==REMOVE_SAVING_TYPE_CODE){
            if (resultCode == Activity.RESULT_OK){
                currentUser = SerializationUtils.deserialize(data.getByteArrayExtra("USER"));
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

                RecyclerView rv = (RecyclerView) findViewById(R.id.transactionViewer);

                SavingTypeAdapter stAdapter = new SavingTypeAdapter(
                        this,
                        savingTypeNames,
                        savingTypeAmounts,
                        savingTypeIds
                );

                rv.setAdapter(stAdapter);
                rv.setLayoutManager(new LinearLayoutManager(this));

            }
        }
    }



    void logout(View view){
        finish();
    }


    public void sendLogin(View view){
        byte[] userbytes = SerializationUtils.serialize(currentUser);
        Intent intent = new Intent(this, AddTransaction.class);
        intent.putExtra("USER",userbytes);
        startActivity(intent);
    }

}