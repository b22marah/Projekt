package com.example.projekt;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=b22marah";
    private final String JSON_FILE = "user_details.json";
    private RecyclerView recyclerView;
    private ArrayList<MythicalCreatures> listOfCreatures;
    private MyAdapter adapter;

    @SuppressWarnings("SameParameterValue")
    private String readFile(String fileName) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return new Scanner(getApplicationContext().getAssets().open(fileName), Charset.forName("UTF-8").name()).useDelimiter("\\A").next();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JsonFile(this, this).execute(JSON_FILE);
        RecyclerView view = findViewById(R.id.recyclerview);
        listOfCreatures = new ArrayList<MythicalCreatures>();
        adapter = new MyAdapter(listOfCreatures);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        String s = readFile("mountains.json");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MythicalCreatures>>() {}.getType();
        ArrayList<MythicalCreatures> data = gson.fromJson(s, type);
        listOfCreatures.addAll(data);
        adapter.notifyDataSetChanged();
        Log.d("MainActivity","The following text was found in textfile:\n\n"+s);

        for (int i = 0; i < listOfCreatures.size(); i++) {
            Log.d("==>", String.valueOf(listOfCreatures.get(i)));
        }
    }


    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }
}