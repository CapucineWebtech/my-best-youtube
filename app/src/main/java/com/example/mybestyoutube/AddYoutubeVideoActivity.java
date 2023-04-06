package com.example.mybestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mybestyoutube.db.YoutubeVideoDatabase;
import com.example.mybestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddYoutubeVideoActivity extends AppCompatActivity {

    private Context context;
    private Resources resources;
    private EditText inYoutubeVideoTitle;
    private EditText inYoutubeVideoDescription;
    private EditText inYoutubeVideoUrl;
    private Spinner spYoutubeVideoCategorie;
    private Button btnConfirm;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube_video);

        // récupérer le context
        context = getApplicationContext();

        // récupérer les ressources
        resources = getResources();

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        //initialisation
        inYoutubeVideoTitle = findViewById(R.id.inYoutubeVideoTitle);
        inYoutubeVideoDescription = findViewById(R.id.inYoutubeVideoDescription);
        inYoutubeVideoUrl = findViewById(R.id.inYoutubeVideoUrl);
        spYoutubeVideoCategorie = findViewById(R.id.spYoutubeVideoCategorie);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnReturn = findViewById(R.id.btnReturn);

        // Initializing a String Array
        String[] categories = new String[]{
                "Sport",
                "Music",
                "Comédie"
        };
        // Convert array to a list
        List<String> categoriesList = new ArrayList<>(Arrays.asList(categories));
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categoriesList
        );
        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );
        // Finally, data bind the spinner object with adapter
        spYoutubeVideoCategorie.setAdapter(spinnerArrayAdapter);

        //Btn validation
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inYoutubeVideoTitle.getText().length() > 2 && inYoutubeVideoDescription.getText().length() > 2 && inYoutubeVideoUrl.getText().length() > 2){
                    YoutubeVideo youtubeVideo = new YoutubeVideo();
                    youtubeVideo.setTitle(inYoutubeVideoTitle.getText().toString());
                    youtubeVideo.setDescription(inYoutubeVideoDescription.getText().toString());
                    youtubeVideo.setUrl(inYoutubeVideoUrl.getText().toString());
                    youtubeVideo.setCategorie(spYoutubeVideoCategorie.getSelectedItem().toString());
                    YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().add(youtubeVideo);
                    finish();
                }else{
                    Toast toast = Toast.makeText(context, resources.getString(R.string.error_form), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Btn retour
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}