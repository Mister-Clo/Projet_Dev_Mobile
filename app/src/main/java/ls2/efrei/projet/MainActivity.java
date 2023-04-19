package ls2.efrei.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void newMatchSettings(View view) {
        Intent intent = new Intent(this, NewMatchSettingsActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_display) {
            // Start the DisplayActivity and pass the values entered by the user
//            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
//            intent.putExtra("value1", lastOperation);
//            intent.putExtra("value2", result.getText().toString());
//            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}