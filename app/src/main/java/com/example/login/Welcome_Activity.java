package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Welcome_Activity extends AppCompatActivity {
    String json = "{\"coord\": { \"lon\": 139,\"lat\": 35}, \"weather\": [ { \"id\": 800, \"main\": \"Clear\"," +
            " \"description\": \"clear sky\", \"icon\": \"01n\" } ], \"base\": \"stations\", " + "" + "\"" +
            "main\": { \"temp\": 289.92, \"pressure\": 1009, \"humidity\": 92, \"temp_min\": 288.71, \"temp_max\": 290.93 }, " +
            "\"wind\": { \"speed\": 0.47, \"deg\": 107.538 }, \"clouds\": { \"all\": 2 }, \"dt\": 1560350192, " +
            "\"sys\": { \"type\": 3, \"id\": 2019346, \"message\": 0.0065, \"country\": \"JP\", " +
            "\"sunrise\": 1560281377, \"sunset\": 1560333478 }, \"timezone\": 32400, \"id\": 1851632, " +
            "\"name\": \"Shuzenji\", \"cod\": 200 } ";
TextView tvtest,tvtest1;
    List<Product> litsProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);
              tvtest=findViewById(R.id.tvtest);
              tvtest1=findViewById(R.id.tvtest1);
        //Json Array lấy nhiều đối tượng
        litsProducts = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray jsonArray = object.getJSONArray("weather");
            for (int i = 1; i <= jsonArray.length(); i++) {

                String id = jsonArray.getJSONObject(0).getString("id");
                String main = jsonArray.getJSONObject(0).getString("main");
                String description = jsonArray.getJSONObject(0).getString("description");
                String icon = jsonArray.getJSONObject(0).getString("icon");

                Product product = new Product();
                product.setId(id);
                product.setDescription(description);
                product.setMain(main);
                product.setIcon(icon);

                litsProducts.add(product);
            }

            tvtest.setText(
                    "weather:" + "\n" + "\t id:" + litsProducts.get(0).getId()+ "\n"
                            + "\t main:" + litsProducts.get(0).getMain() + "\n"
                            + "\t Description:" + litsProducts.get(0).getDescription()+ "\n"
                            + "\t icon:" + litsProducts.get(0).getIcon());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // lấy dữ liệu đối tượng trong list
        try {
            JSONObject object = new JSONObject(json);
            JSONObject obmain=object.getJSONObject("main");

            String temp =obmain.getString("temp");
            String pressure =obmain.getString("pressure");
            String humidity=obmain.getString("humidity");
            String temp_min =obmain.getString("temp_min");
            String temp_max =obmain.getString("temp_max");

            tvtest1.setText("main:"+"\n"+"\t temp:" +temp+"\n"+"\t pressure:" +pressure+"\n"+"\t humidity:" +humidity
                    +"\n"+"\t temp_min:" +temp_min+"\n"+"\t temp_max:" +temp_max);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


