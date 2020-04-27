package HostActivity;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HostAdapter.AddHostAdapter;
import HostAdapter.StyleAdapter;
import HostModel.Location;
import HostModel.Style;
import util.server;

public class AddHostActivity extends AppCompatActivity {

    TextInputLayout textNameLayout, textPriceLayout, textPersonLayout, textPhoneLayout, textDescribeLayout,textImgLayout;
    TextInputEditText textName, textPrice, textPerson, textPhone, textDescribe, textImg;
    Button btnSave, btnCancel;
    Toolbar toolbar;

    //TextView textlocation;

    // String urlInsertData="http://192.168.1.6/final_host/insert.php";
    //String urlgettLocation="http://192.168.1.6/final_host/getlocation.php";

    String urlInsertData= server.urlinsert;

    String urlgettLocation= server.urllocation;

    String urlgetStyle= server.urlstyle;

    RecyclerView recyclerView, recyclerViewStyle;
    AddHostAdapter adapter;
    StyleAdapter styleAdapter;

    //upload img
    ImageView imageView;
    Button buttonChoose;
    final int CODE_GALLERY_REQUEST=999;
    Bitmap bitmap;


    List<Location> data;
    List<Style> datas;
    //List<Location> data1;
    //String  text;
    int locateID, StyleID;
    String ID, StyleId;

    String UserAdd;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_host);

        //final String[] locationList= new String[]{"C201","C222","C342","C213","C123"};

        Anhxa();
        toolbar= findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        UserAdd= Integer.toString(getIntent().getIntExtra("ADDuser",1));

        recyclerViewStyle= findViewById(R.id.recyclerViewStyle);
        recyclerView= findViewById(R.id.recyclerView);

        data= new ArrayList<>();
        datas= new ArrayList<>();

        adapter= new AddHostAdapter(AddHostActivity.this, data);
        styleAdapter= new StyleAdapter(AddHostActivity.this,datas);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewStyle.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewStyle.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        //recyclerView.addItemDecoration(new DividerItemDecoration(AddHostActivity.this,DividerItemDecoration.HORIZONTAL));
        //recyclerViewStyle.addItemDecoration(new DividerItemDecoration(AddHostActivity.this,DividerItemDecoration.HORIZONTAL));


        recyclerView.setAdapter(adapter);
        recyclerViewStyle.setAdapter(styleAdapter);


        getLocation(urlgettLocation);
        getStyle(urlgetStyle);

        actionBar();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(AddHostActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(AddHostActivity.this, "You choose "+ data.get(position).getLocation(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(AddHostActivity.this, "Your Location have ID: "+ data.get(position).getId(), Toast.LENGTH_SHORT).show();
                        locateID=data.get(position).getId();

                        ID= Integer.toString(locateID);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        // recyclerview cho lua chon loai nha
        recyclerViewStyle.addOnItemTouchListener(
                new RecyclerItemClickListener(AddHostActivity.this, recyclerViewStyle ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(AddHostActivity.this, "You choose "+ datas.get(position).getStyleHome(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(AddHostActivity.this, "Your Location have ID: "+ datas.get(position).getId(), Toast.LENGTH_SHORT).show();
                        StyleID=datas.get(position).getId();

                        StyleId= Integer.toString(StyleID);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //xu ly nut bam
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= textName.getText().toString().trim();
                String price= textPrice.getText().toString().trim();
                String person= textPerson.getText().toString().trim();
                String phone= textPhone.getText().toString().trim();
                String describe= textDescribe.getText().toString().trim();
                //String img= textImg.getText().toString().trim();

                if(name.isEmpty() || price.isEmpty() || person.isEmpty() || phone.isEmpty() || describe.isEmpty() ){
                    Toast.makeText(AddHostActivity.this,"Please! Enter full information",Toast.LENGTH_SHORT).show();

                }else{
                    AddHome(urlInsertData);

                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set single choice for location



        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddHostActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},CODE_GALLERY_REQUEST);
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

    //anh xa thuoc tinh
    private void Anhxa() {
        textNameLayout= findViewById(R.id.text_name_layout);
        textPriceLayout= findViewById(R.id.text_price_layout);
        textPersonLayout= findViewById(R.id.text_person_layout);
        textPhoneLayout= findViewById(R.id.text_phone_layout);
        textDescribeLayout= findViewById(R.id.text_describe_layout);
        //textImgLayout= findViewById(R.id.text_img_layout);

        //textLocationLayout= findViewById(R.id.text_locate_layout);
        buttonChoose =findViewById(R.id.button1);
        imageView = findViewById(R.id.imageViewKhac);

        textName= findViewById(R.id.text_name);
        textPrice= findViewById(R.id.text_price);
        textPerson= findViewById(R.id.text_person);
        textPhone= findViewById(R.id.text_phone);
        textDescribe= findViewById(R.id.text_describe);
        //textImg= findViewById(R.id.text_img);
        //textLocation= findViewById(R.id.text_locate);

        btnSave= findViewById(R.id.btnsave);
        btnCancel= findViewById(R.id.btncancel);

        // textLocation= findViewById(R.id.textlocate);

    }
    //add home

    private void AddHome(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(AddHostActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("sucess")){
                    Toast.makeText(AddHostActivity.this,"sucessfully",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent();
                    intent.putExtra("reply_ID",UserAdd);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                else {
                    Toast.makeText(AddHostActivity.this,"error",Toast.LENGTH_SHORT).show();

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddHostActivity.this,"error system",Toast.LENGTH_SHORT).show();
                Log.d("AAA","error\n"+error.toString());


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();

                String imageData= imageToString(bitmap);

                params.put("home_name",textName.getText().toString().trim());
                params.put("home_price",textPrice.getText().toString().trim());
                params.put("home_person",textPerson.getText().toString().trim());
                params.put("home_phone",textPhone.getText().toString().trim());
                params.put("home_describe",textDescribe.getText().toString().trim());
                params.put("locate_id",ID.trim());
                params.put("style_id",StyleId.trim());
                params.put("home_img",imageData);
                params.put("user_id",UserAdd.trim());

                /////////////


                //params.put("home_img","chua co hinh");



                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    //get Location
    private void getLocation(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(AddHostActivity.this);
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
                Toast.makeText(AddHostActivity.this,"error",Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(jsonArrayRequest);

    }
    //get Style
    private void getStyle(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(AddHostActivity.this);
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
                Toast.makeText(AddHostActivity.this,"error",Toast.LENGTH_SHORT).show();


            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //on click de recyclerview dung de co the hoat dong ten activity !!!!!!!!!!!!!!!!!!!!!!!!!

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
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

    ////////////////////////////////////////////////

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CODE_GALLERY_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"), CODE_GALLERY_REQUEST);

            }else {
                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    ///////////////////////////////////////////////

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();
            try {
                InputStream inputStream= getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return  encodedImage;
    }

}
