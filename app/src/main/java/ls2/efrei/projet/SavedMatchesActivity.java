package ls2.efrei.projet;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedMatchesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> id, matchFormat, latitude, longitude, geocoding,  streetName, score1, score2, player1, player2;
    ArrayList<byte[]> imagePath;
    CustomAdapter customAdapter;
    private DataBaseHelper db;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_matches_settings);
        handler = new Handler();
        recyclerView = findViewById(R.id.saved_matches_list);
        db = new DataBaseHelper(this);
        id = new ArrayList<>();
        matchFormat = new ArrayList<>();
        imagePath = new ArrayList<>();
        latitude = new ArrayList<>();
        longitude = new ArrayList<>();
        geocoding = new ArrayList<>();
        streetName = new ArrayList<>();
        score1 = new ArrayList<>();
        score2 = new ArrayList<>();
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(SavedMatchesActivity.this, id, matchFormat, imagePath, latitude, longitude, geocoding, streetName, score1, score2, player1, player2);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    void storeDataInArrays(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    handler.post(new Runnable() {
                        // This method will be executed on the UI thread
                        //to add the match to the database
                        @Override
                        public void run() {
                            Cursor cursor = db.readAllData();
                            if(cursor.getCount() == 0){
                                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                while(cursor.moveToNext()){
                                    id.add(cursor.getString(0));
                                    matchFormat.add(cursor.getString(1));
                                    imagePath.add(cursor.getBlob(2));
                                    latitude.add(cursor.getString(3));
                                    longitude.add(cursor.getString(4));
                                    geocoding.add(cursor.getString(5));
                                    streetName.add(cursor.getString(6));
                                    score1.add(cursor.getString(7));
                                    score2.add(cursor.getString(8));
                                    player1.add(cursor.getString(9));
                                    player2.add(cursor.getString(10));
                                }
                            }
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        new Thread(runnable).start();
    }

}
