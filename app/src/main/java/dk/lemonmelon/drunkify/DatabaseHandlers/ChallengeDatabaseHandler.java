package dk.lemonmelon.drunkify.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dk.lemonmelon.drunkify.Challenge;
import dk.lemonmelon.drunkify.Extra;

/**
 * Created by Mathies on 31-03-2016.
 */
public class ChallengeDatabaseHandler extends SQLiteOpenHelper {

    private static final int DatabaseVersion = 14;
    private static final String DatabaseName = "challenges.db";
    public static final String TableGlobalChallenges = "globalChallenges";

    public static final String ColumnChallengeID = "id";
    public static final String ColumnChallengeText = "text";
    public static final String ColumnChallengePunishment = "punishment";
    public static final String ColumnChallengeExtra = "extra";

    public ChallengeDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatabaseName, factory, DatabaseVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TableGlobalChallenges + "(" +
                ColumnChallengeID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ColumnChallengeText + " TEXT," +
                ColumnChallengePunishment + " INTEGER," +
                ColumnChallengeExtra + " TEXT" +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TableGlobalChallenges);
        onCreate(db);

    }

    public void addChallenge(String text, Integer punishment, String extra) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnChallengeText, text);
        values.put(ColumnChallengePunishment,punishment);
        values.put(ColumnChallengeExtra,extra);
        db.insert(TableGlobalChallenges, null, values);
        db.close();

    }

    public Challenge getChallengeInfo(Integer challengeID) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TableGlobalChallenges + " WHERE " + ColumnChallengeID + "=" + challengeID + ";";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        String challengeText = cursor.getString(cursor.getColumnIndex(ColumnChallengeText));
        Integer challengePunishment = cursor.getInt(cursor.getColumnIndex(ColumnChallengePunishment));
        String challengeExtra = cursor.getString(cursor.getColumnIndex(ColumnChallengeExtra));

        cursor.close();
        db.close();

        Challenge challenge =  new Challenge();

        challenge.setChallengeInfo(challengeID,challengeText,challengePunishment,challengeExtra);

        return challenge;

    }
    public Integer getChallengeCount(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorCount = db.rawQuery("SELECT * FROM " + TableGlobalChallenges, null);
        Integer challengeCount = cursorCount.getCount();
        cursorCount.close();
        return challengeCount;

    }

}
