package com.sanxiang.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sanxiang.project.ScrollConflict.ConflictActivity;
import com.sanxiang.project.customView.CustomActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void conflict(View view){
        Intent intent = new Intent(this, ConflictActivity.class);
        startActivity(intent);
    }
    public void CustomView(View view){
        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }
}
