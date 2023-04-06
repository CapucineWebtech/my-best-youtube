package com.example.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybestyoutube.db.YoutubeVideoDatabase;
import com.example.mybestyoutube.pojos.YoutubeVideo;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvUrl;
    private TextView tvCategorie;
    private Context context;

    private Button btnWatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // récupérer le context
        context = getApplicationContext();

        btnWatch = findViewById(R.id.btnWatch);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvUrl = findViewById(R.id.tvUrl);
        tvCategorie = findViewById(R.id.tvCategorie);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Long id = (Long) intent.getSerializableExtra(MainActivity.KEY_INDEX_VIDEO);
        YoutubeVideo youtubeVideo = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().find(id);

        tvTitle.setText(youtubeVideo.getTitle());
        tvDescription.setText(youtubeVideo.getDescription());
        tvUrl.setText(youtubeVideo.getUrl());
        tvCategorie.setText(youtubeVideo.getCategorie());


        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String youtubeUrl = youtubeVideo.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                intent.setPackage("com.google.android.youtube");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                    startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}