package dk.lemonmelon.drunkify.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mathies on 21-04-2016.
 */
public class PlayerDatabaseHandler extends SQLiteOpenHelper {


    private static final int DatabaseVersion = 6;
    private static final String DatabaseName = "players.db";
    public static final String TablePlayers = "playerInfo";

    public static final String columnPlayerID = "id";
    public static final String columnPlayerName = "playerName";
    public static final String columnPlayerDrinkCount = "drinkCount";

    String playerName = "";
    Integer playerDrinkCount = 1;

    public PlayerDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatabaseName, factory, DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TablePlayers + "(" +
                columnPlayerID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                columnPlayerName + " TEXT," +
                columnPlayerDrinkCount + " INTEGER" +
                ");";

        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TablePlayers);
        onCreate(db);

    }
    public void addPlayer (Integer playerID, String playerName) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnPlayerID, playerID);
        values.put(columnPlayerName, playerName);
        values.put(columnPlayerDrinkCount,0);
        db.insert(TablePlayers, null, values);
        db.close();

    }
    public void dropPlayerTable (){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TablePlayers);

    }
    public void createPlayerTable(){

        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);

    }
    public void loadPlayerInfo(Integer playerID) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TablePlayers + " WHERE " + columnPlayerID + "=" + playerID + ";";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        playerName = cursor.getString(cursor.getColumnIndex(columnPlayerName));
        playerDrinkCount = cursor.getInt(cursor.getColumnIndex(columnPlayerDrinkCount));

        cursor.close();
        db.close();

    }
    public String getPlayerName(){

        return playerName;

    }
    public Integer getPlayerDrinkCount() {

        return playerDrinkCount;

    }
    public Integer getPlayerCount(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorCount = db.rawQuery("SELECT * FROM " + TablePlayers, null);
        Integer playerCount = cursorCount.getCount();
        cursorCount.close();
        return playerCount;

    }



}
