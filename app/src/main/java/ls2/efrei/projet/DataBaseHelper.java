package ls2.efrei.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "matches.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "matches";

    //To view the data of the database, download the SQLite Browser from https://sqlitebrowser.org/dl/
    //go to toolbar of android studio, click on view, tool windows, then device file explorer
    //then go to data/data/ls2.efrei.projet/databases/matches.db
    //right click on ata/data/ls2.efrei.projet and synchronize then copy the matches.db (db.shm, db-wal) file to your computer
    //open the SQLite Browser and open the matches.db file
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS matches (id INTEGER PRIMARY KEY AUTOINCREMENT, matchFormat TEXT, imagePath BLOB, latitude TEXT, longitude TEXT, geocoding TEXT,  streetname TEXT, score1 INTEGER, score2 INTEGER, player1 TEXT, player2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS matches");
        onCreate(db);
    }

    public void addMatches(String matchFormat, byte[] imagePath, String latitude, String longitude, String geocoding, String streetName, int score1, int score2, String player1, String player2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("matchFormat", matchFormat);
        cv.put("imagePath", imagePath);
        cv.put("latitude", latitude);
        cv.put("longitude", longitude);
        cv.put("geocoding", geocoding);
        cv.put("streetName", streetName);
        cv.put("score1", score1);
        cv.put("score2", score2);
        cv.put("player1", player1);
        cv.put("player2", player2);
        long res = db.insert(TABLE_NAME, null, cv);
        if(res == -1){
            Toast.makeText(context, R.string.add_match_failed, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.add_match_success, Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
