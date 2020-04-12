package mad.sliit.uniflexy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import mad.sliit.uniflexy.Database.ComplaintDB;

public class ViewComplaints extends AppCompatActivity {
    private static final String TAG = "ViewComplaints";
    JSONArray data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);


        ComplaintDB complaintDB = new ComplaintDB(this);
        try {
            data = complaintDB.viewComplaints();
            Log.i(TAG, "onCreate: "+data);
            initRecyclerView();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private  void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
