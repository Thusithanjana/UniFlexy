package mad.sliit.uniflexy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private JSONArray array;
    private Context context;
    private static final String TAG = "RecyclerViewAdapter";

    public RecyclerViewAdapter(JSONArray array, Context context){
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_list,
                parent,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = array.getJSONObject(position);

            Log.i(TAG, "onBindViewHolder: "+object);
            holder.type.setText(object.get("type").toString());
            holder.description.setText(object.get("description").toString());
            holder.priotity.setText(object.get("priority").toString());
            holder.name.setText(object.get("name").toString());
            byte[] byteArray = (byte[]) object.get("image");
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
            holder.photograph.setImageBitmap(bm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "onBindViewHolder: "+position);


    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText type, description,priotity,name;
        ImageView photograph;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.edtType1);
            description = itemView.findViewById(R.id.edtDes1);
            priotity = itemView.findViewById(R.id.edtPrio1);
            name = itemView.findViewById(R.id.edtName1);
            photograph = itemView.findViewById(R.id.imgComp);


        }
    }
}