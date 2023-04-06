package com.example.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mybestyoutube.adapter.YoutubeVideoAdapter;
import com.example.mybestyoutube.db.YoutubeVideoDatabase;
import com.example.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    public static final String KEY_YOUTUBE_VIDEO = "";
    public static final String KEY_INDEX_VIDEO = "key_index_video";
    private RecyclerView rvYoutubeVideos;

    @Override
    protected void onStart() {
        super.onStart();

        YoutubeVideoAsyncTask youtubeVideoAsyncTask = new YoutubeVideoAsyncTask();
        youtubeVideoAsyncTask.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupérer le context
        context = getApplicationContext();

        rvYoutubeVideos = findViewById(R.id.rvYoutubeVideo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvYoutubeVideos.setHasFixedSize(true);
        rvYoutubeVideos.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // créé le menu à partir de la resource
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addyoutubevideo:
                Intent intent = new Intent(context, AddYoutubeVideoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class YoutubeVideoAsyncTask extends AsyncTask<Nullable, Nullable, List<YoutubeVideo>> {

        @Override
        protected List<YoutubeVideo> doInBackground(Nullable... nullables) {
            List<YoutubeVideo> youtubeVideos = YoutubeVideoDatabase.getDb(context).youtubeVideoDAO().list();
            return youtubeVideos;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> youtubeVideos) {
            rvYoutubeVideos.setAdapter(new YoutubeVideoAdapter(youtubeVideos, new YoutubeVideoAdapter.OnItemClickListener() {
                @Override public void onItemClick(YoutubeVideo youtubeVideo) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(KEY_INDEX_VIDEO, youtubeVideo.getId());
                    startActivity(intent);
                }
            }));
        }
    }
}