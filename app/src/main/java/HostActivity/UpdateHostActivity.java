package HostActivity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.apprr.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HostAdapter.AddHostAdapter;
import HostAdapter.StyleAdapter;
import HostModel.Host;
import HostModel.Location;
import HostModel.Style;
import util.server;

public class UpdateHostActivity extends AppCompatActivity {
    TextInputLayout textNameUpdateLayout, textPriceUpdateLayout, textPersonUpdateLayout, textPhoneUpdateLayout, textDescribeUpdateLayout,textImgUpdateLayout;
    TextInputEditText textNameUpdate, textPriceUpdate, textPersonUpdate, textPhoneUpdate, textDescribeUpdate, textImgUpdate;
    Button btnUpdate, btnCancel;
    TextView textViewLocateID, textViewStyleID;

    RecyclerView recyclerView;
    RecyclerView recyclerViewStyle;
    AddHostAdapter adapter;
    StyleAdapter styleAdapter;


    Location location;
    Host host;

    List<Location> data;
    List<Style> datas;

    int locateID, StyleID;
    int id= 0;
    String ID, StyleId;
    Toolbar toolbar;

    Style style;


    // String urlgettLocation="http://192.168.1.6/final_host/getlocation.php";
    //String urlUpdateData="http://192.168.1.6/final_host/update.php";

    String urlgettLocation= server.urllocation;

    String urlUpdateData=server.urlupdate;

    String urlgetStyle= server.urlstyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_host);
        toolbar= findViewById(R.id.toobar);
        setSupportActionBar(toolbar);


        Intent intent= getIntent();
        Host host = (Host) intent.getSerializableExtra("dataHome");
        Toast.makeText(this, host.getName(), Toast.LENGTH_SHORT).show();

        Anhxa();
        actionBar();
        recyclerView= findViewById(R.id.recyclerViewupdate);
        recyclerViewStyle= findViewById(R.id.recyclerViewStyleUpadate);

        data= new ArrayList<>();
        datas= new ArrayList<>();

        adapter= new AddHostAdapter(UpdateHostActivity.this, data);
        styleAdapter= new StyleAdapter(UpdateHostActivity.this, datas);

        recyclerView.setLayoutManager(new LinearLayoutManager(UpdateHostActivity.this));
        recyclerViewStyle.setLayoutManager(new LinearLayoutManager(UpdateHostActivity.this));


        recyclerView.addItemDecoration(new DividerItemDecoration(UpdateHostActivity.this,DividerItemDecoration.VERTICAL));
        recyclerViewStyle.addItemDecoration(new DividerItemDecoration(UpdateHostActivity.this,DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);
        recyclerViewStyle.setAdapter(styleAdapter);


        getLocation(urlgettLocation);
        getStyle(urlgetStyle);


        recyclerView.addOnItemTouchListener(
                new AddHostActivity.RecyclerItemClickListener(UpdateHostActivity.this, recyclerView ,new AddHostActivity.RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //Toast.makeText(UpdateHostActivity.this, "YOu choose"+ data.set(position,).getLocation(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(UpdateHostActivity.this, "Your Location for update: "+ data.get(position).getLocation(), Toast.LENGTH_SHORT).show();
                        locateID=data.get(position).getId();

                        ID= Integer.toString(locateID);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        recyclerViewStyle.addOnItemTouchListener(
                new AddHostActivity.RecyclerItemClickListener(UpdateHostActivity.this, recyclerViewStyle ,new AddHostActivity.RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //Toast.makeText(UpdateHostActivity.this, "YOu choose"+ data.set(position,).getLocation(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(UpdateHostActivity.this, "Your Style for update: "+ datas.get(position).getStyleHome(), Toast.LENGTH_SHORT).show();
                        StyleID=datas.get(position).getId();

                        StyleId = Integer.toString(StyleID);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );






        id= host.getId();
        textNameUpdate.setText(host.getName());
        textPriceUpdate.setText(host.getPrice()+ "");
        textPersonUpdate.setText(host.getPerson());
        textPhoneUpdate.setText(host.getPhone());
        textDescribeUpdate.setText(host.getDescribe());
        textImgUpdate.setText(host.getImg());
        //textViewLocateID.setText(host.getLocateId()+"");
        //textViewStyleID.setText(host.getStyleId()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUpdate= textNameUpdate.getText().toString().trim();
                String priceUpdate= textPriceUpdate.getText().toString().trim();
                String personUpdate= textPersonUpdate.getText().toString().trim();
                String phoneUpdate= textPhoneUpdate.getText().toString().trim();
                String describeUpdate= textDescribeUpdate.getText().toString().trim();
                String imgUpdate= textImgUpdate.getText().toString().trim();

                if(nameUpdate.isEmpty() || priceUpdate.isEmpty() || personUpdate.isEmpty() || phoneUpdate.isEmpty() || describeUpdate.isEmpty() || imgUpdate.isEmpty()){
                    Toast.makeText(UpdateHostActivity.this, "Please! Enter full information", Toast.LENGTH_SHORT).show();

                }else {
                    UpdateData(urlUpdateData);

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        textDescribeUpdateLayout= findViewById(R.id.text_describe_layout_update);
        textNameUpdateLayout= findViewById(R.id.text_name_layout_update);
        textPriceUpdateLayout= findViewById(R.id.text_price_layout_update);
        textPersonUpdateLayout= findViewById(R.id.text_person_layout_update);
        textPhoneUpdateLayout= findViewById(R.id.text_phone_layout_update);
        textImgUpdateLayout= findViewById(R.id.text_img_layout_update);

        textNameUpdate= findViewById(R.id.text_name_update);
        textPriceUpdate= findViewById(R.id.text_price_update);
        textPersonUpdate= findViewById(R.id.text_person_update);
        textPhoneUpdate= findViewById(R.id.text_phone_update);
        textDescribeUpdate= findViewById(R.id.text_describe_update);
        textImgUpdate= findViewById(R.id.text_img_update);

        btnUpdate= findViewById(R.id.btnUpdate);
        btnCancel= findViewById(R.id.btncancel_update);

        //textViewLocateID= findViewById(R.id.textViewId);
        //textViewStyleID= findViewById(R.id.textViewStyleID);



    }

    // LAY DU LIEU O LOCATION
    private void getLocation(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(HostActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object= response.getJSONObject(i);
                        data.add(new Location(
                                object.getInt("Id"),
                                object.getString("Location")

                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateHostActivity.this,"error",Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    //LAY DU LIEU O STYLE
    private void getStyle(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(UpdateHostActivity.this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i< response.length(); i++){
                    try {
                        JSONObject object= response.getJSONObject(i);
                        datas.add(new Style(
                                object.getInt("Id"),
                                object.getString("Stylehome"),
                                object.getString("Styleimg")
                        ));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateHostActivity.this,"error",Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    //UPDATE DATA
    private void UpdateData(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(UpdateHostActivity.this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("sucess")){
                    Toast.makeText(UpdateHostActivity.this, "Update sucessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateHostActivity.this, HostActivity.class));

                }else{
                    Toast.makeText(UpdateHostActivity.this, " error Update", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateHostActivity.this, "Error system", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(id));
                params.put("home_name",textNameUpdate.getText().toString().trim());
                params.put("home_price",textPriceUpdate.getText().toString().trim());
                params.put("home_person",textPersonUpdate.getText().toString().trim());
                params.put("home_phone",textPhoneUpdate.getText().toString().trim());
                params.put("home_describe",textDescribeUpdate.getText().toString().trim());
                params.put("home_img",textImgUpdate.getText().toString().trim());
                params.put("locate_id",ID.trim());
                params.put("style_id",StyleId.trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    //BAT SU KIEN CUA NHAN CHO RECYCLERVIEW
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private AddHostActivity.RecyclerItemClickListener.OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, AddHostActivity.RecyclerItemClickListener.OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }
}
