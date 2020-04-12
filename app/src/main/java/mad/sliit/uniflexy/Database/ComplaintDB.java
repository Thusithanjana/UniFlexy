package mad.sliit.uniflexy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;

public class ComplaintDB extends SQLiteOpenHelper {

    private static final String TAG = "ComplaintDB";

    public static final String DATABSE_NAME="ComplaintsInfo.db";

    //Table
    protected static class Complaint implements BaseColumns{
        public static final String TABLE_NAME ="complaint";
        public static final String COLUMN_NAME_COMPLAINT_TYPE = "type";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_IMAGE = "image";

    }

    //---


    public ComplaintDB(@Nullable Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: Table Created");
        String SQL_CREATE_ENTREES =
                "CREATE TABLE " + Complaint.TABLE_NAME + " ("+
                        Complaint._ID + " INTEGER PRIMARY KEY,"+
                        Complaint.COLUMN_NAME_COMPLAINT_TYPE + " TEXT,"+
                        Complaint.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        Complaint.COLUMN_NAME_NAME + " TEXT," +
                        Complaint.COLUMN_NAME_PRIORITY + " INTEGER,"+
                        Complaint.COLUMN_NAME_IMAGE + " BLOB)";

        db.execSQL(SQL_CREATE_ENTREES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addComplaint( Context context,String type, String description, String name, int priority, byte[] image){
        Log.e(TAG, "addComplaint: data inserted");
        //get database in write mode

        SQLiteDatabase db = getWritableDatabase();

        //Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(Complaint.COLUMN_NAME_COMPLAINT_TYPE,type);
        values.put(Complaint.COLUMN_NAME_DESCRIPTION,description);
        values.put(Complaint.COLUMN_NAME_NAME,name);
        values.put(Complaint.COLUMN_NAME_PRIORITY,priority);
        values.put(Complaint.COLUMN_NAME_IMAGE,image);

        //insert the row
        Long newRowID = db.insert(Complaint.TABLE_NAME,null,values);
        Toast.makeText(context, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


    }

    public JSONArray viewComplaints() throws JSONException {

        SQLiteDatabase db = getReadableDatabase();
        JSONArray objs= new JSONArray();


        String[] projection = {
                Complaint._ID,
                Complaint.COLUMN_NAME_COMPLAINT_TYPE,
                Complaint.COLUMN_NAME_DESCRIPTION,
                Complaint.COLUMN_NAME_NAME,
                Complaint.COLUMN_NAME_PRIORITY,
                Complaint.COLUMN_NAME_IMAGE
        };

        String sortOrder = Complaint.TABLE_NAME + " DESC";
        String selectQuery = "SELECT  * FROM " + Complaint.TABLE_NAME+ " ORDER BY "+ Complaint._ID+" DESC";

        Cursor cursor = db.rawQuery(selectQuery,null);

        while (cursor.moveToNext()){
            String type = cursor.getString(cursor.getColumnIndexOrThrow(Complaint.COLUMN_NAME_COMPLAINT_TYPE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(Complaint.COLUMN_NAME_DESCRIPTION));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Complaint.COLUMN_NAME_NAME));
            int priority = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(Complaint.COLUMN_NAME_PRIORITY)));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(Complaint.COLUMN_NAME_IMAGE));
            JSONObject obj = new JSONObject();
            obj.put("type",type);
            obj.put("description",description);
            obj.put("name",name);
            obj.put("priority",priority);
            obj.put("image",image);

            objs.put(obj);

            Log.i(TAG, "viewComplaints: "+obj);
            Log.i(TAG, "viewComplaints: objs =>"+objs);

        }



        return objs;

    }
}
