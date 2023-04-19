package ls2.efrei.projet;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class NewMatchSettingsActivity extends AppCompatActivity {
    Spinner spinnerMatchTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_match_settings);
        spinnerMatchTypes = findViewById(R.id.spinner_match_format);
        ArrayAdapter<CharSequence> adapterMatchTypes = ArrayAdapter.createFromResource(this, R.array.match_formats, android.R.layout.simple_spinner_item);
        adapterMatchTypes.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMatchTypes.setAdapter(adapterMatchTypes);
    }

    public void newMatch(View view){

    }
}
