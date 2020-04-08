package HostActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apprr.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HostAdapter.HostAdapter;
import HostModel.Host;
import util.server;

public class HostActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Host> data;
    HostAdapter adapter;
    int a;
    String name;
    String ID;
    Toolbar toolbar;

    //String urlGetData="http://192.168.1.6/final_host/gethost.php";
    //String urlDeleteData="http://192.168.1.6/final_host/delete.php";

    String urlGetData= server.urlget;
    String urlDeleteData= server.urldelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        toolbar= findViewById(R.id.toobar);
        setSupportActionBar(toolbar);


        recyclerView= findViewById(R.id.recyclerView);
        data= new ArrayList<>();
        adapter= new HostAdapter(HostActivity.this, data);

        recyclerView.setLayoutManager(new LinearLayoutManager(HostActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(HostActivity.this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        registerForContextMenu(recyclerView);

        getData(urlGetData);

        actionBar();


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

    //lay du lieu
    private void getData(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(HostActivity.this);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(HostActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                data.clear();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object= response.getJSONObject(i);
                        data.add(new Host(
                                object.getInt("Id"),
                                object.getString("Home_name"),
                                object.getString("Home_price"),
                                object.getString("Home_person"),
                                object.getString("Home_phone"),
                                object.getString("Home_describe"),
                                object.getInt("Locate_id"),
                                object.getInt("Style_id"),
                                object.getString("Home_img")

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
                Toast.makeText(HostActivity.this,"error",Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    //DELETE DATA
    public void DeleteHome(final int idHome){
        RequestQueue requestQueue= Volley.newRequestQueue(HostActivity.this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, urlDeleteData, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("sucess")){
                    Toast.makeText(HostActivity.this, "Delete sucessfully", Toast.LENGTH_SHORT).show();
                    getData(urlGetData);

                }else {
                    Toast.makeText(HostActivity.this, "Error delete", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HostActivity.this, "Error system", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("id", String.valueOf(idHome));
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }


    //add option menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_host_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                Intent intent= new Intent(HostActivity.this, AddHostActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }






    ///////////

    ////


}
