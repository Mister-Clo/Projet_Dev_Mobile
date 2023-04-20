package ls2.efrei.projet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_match);
        TextView matchTypeTitle = findViewById(R.id.match_type_title);
        TextView player1Name = findViewById(R.id.match_view_player1);
        TextView player2Name = findViewById(R.id.match_view_player2);
        TextView player1Score = findViewById(R.id.match_view_score1);
        TextView player2Score = findViewById(R.id.match_view_score2);

        String matchType = getIntent().getStringExtra("matchType");
        String player1 = getIntent().getStringExtra("player1");
        String player2 = getIntent().getStringExtra("player2");
        String score1 = getIntent().getStringExtra("score1");
        String score2 = getIntent().getStringExtra("score2");

        // Set the values in the TextViews got from the intent
        matchTypeTitle.setText(matchType);
        player1Name.setText(player1);
        player2Name.setText(player2);
        player1Score.setText(score1);
        player2Score.setText(score2);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_display) {
            Intent intent = new Intent(this, SavedMatchesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
