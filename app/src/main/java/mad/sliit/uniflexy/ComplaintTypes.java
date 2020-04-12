package mad.sliit.uniflexy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComplaintTypes extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_types);
        intent = new Intent(ComplaintTypes.this, AddComplaint.class);
    }

    public void complaintLab(View view){
        intent.putExtra("msg","LAB / LECTURE HALL");
        startActivity(intent);

    }

    public void complaintTime(View view){
        intent.putExtra("msg","TIMETABLE");
        startActivity(intent);

    }

    public void complaintOther(View view){
        intent.putExtra("msg","OTHER");
        startActivity(intent);

    }
}
