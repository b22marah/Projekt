package com.example.projekt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.WorkerThread;
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
        new JsonTask(this).execute(JSON_URL);
        RecyclerView view = findViewById(R.id.recyclerview);
        listOfCreatures = new ArrayList<MythicalCreatures>();
        adapter = new MyAdapter(listOfCreatures);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MythicalCreatures>>() {}.getType();
        ArrayList<MythicalCreatures> data = gson.fromJson(json, type);
        listOfCreatures.addAll(data);

        adapter.notifyDataSetChanged();
    }
    public void goToAboutPage(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}