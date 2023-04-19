package ls2.efrei.projet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class NewMatchSettingsActivity extends AppCompatActivity {
    Spinner spinnerMatchTypes;
    EditText editTextPlayer1, editTextPlayer2;
    Button btnStartMatch, btnTakePhoto;
    ImageView mImageView;
    byte[] imageBytes;
    private Handler handler;
    private DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_match_settings);
        spinnerMatchTypes = findViewById(R.id.spinner_match_format);
        ArrayAdapter<CharSequence> adapterMatchTypes = ArrayAdapter.createFromResource(this, R.array.match_formats, android.R.layout.simple_spinner_item);
        adapterMatchTypes.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMatchTypes.setAdapter(adapterMatchTypes);
        db = new DataBaseHelper(this);
        handler = new Handler();
        editTextPlayer1 = findViewById(R.id.edit_player1);
        editTextPlayer2 = findViewById(R.id.edit_player2);
        btnStartMatch = findViewById(R.id.startMatch_btn);
        btnTakePhoto = findViewById(R.id.button_take_picture);
        mImageView = findViewById(R.id.match_picture);
    }
    public void takePicture(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            imageBytes = getBytes(imageBitmap);
        }
    }
    private byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    /**
     * This method is called when the user clicks on the "New Match" button
     * To Save asynchronously a new match in the sqlite database
     * Then it starts the NewMatchActivity viewing the match statistics
     * @param view
     */
    public void newMatch(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    handler.post(new Runnable() {
                        // This method will be executed on the UI thread
                        //to add the match to the database
                        @Override
                        public void run() {
                            String matchFormat = spinnerMatchTypes.getSelectedItem().toString();
                            String player1 = editTextPlayer1.getText().toString();
                            String player2 = editTextPlayer2.getText().toString();
                            db.addMatches(matchFormat, imageBytes, "200N","100W","HL","Kribi",0,0, player1, player2);
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        new Thread(runnable).start();
        Intent intent = new Intent(this, NewMatchActivity.class);
        intent.putExtra("matchType", spinnerMatchTypes.getSelectedItem().toString());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
