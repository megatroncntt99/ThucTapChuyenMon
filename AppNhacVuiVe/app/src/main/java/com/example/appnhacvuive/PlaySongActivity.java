package com.example.appnhacvuive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.Adapter.PlaySongAdapter;
import com.example.Fragment.FragmentListSong;
import com.example.Fragment.FragmentLyric;
import com.example.Fragment.FragmentMusicDisc;
import com.example.Interface.GetDataSong;
import com.example.Interface.Playable;
import com.example.Model.Song;
import com.example.Notification.CreateNotification;
import com.example.Notification.OnClearFromRecentService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;

public class PlaySongActivity extends AppCompatActivity implements GetDataSong, Playable {


    public static ArrayList<Song> songArrayList = new ArrayList<>();
    public static String nameList = "";
    public static Song song = null;
    TextView txtTimeSong, txtTotalTimeSong;
    SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

    public static PlaySongAdapter playSongAdapter;

    FragmentListSong fragmentListSong;
    FragmentLyric fragmentLyric;
    FragmentMusicDisc fragmentMusicDisc;
    int i=5000;

    Toolbar toolbarPlaySong;
     ViewPager viewPagerPlaySong;
    SeekBar seekBarPlaySong;
     CircleIndicator circleIndicatorPlaySong;
    ImageView imgRandom, imgPrevious, imgPlay, imgNext, imgRepeat;

    MediaPlayer mediaPlaySong;

    ProgressBar progressBar;

    public int position = 0;
    boolean repeat = false, checkRandom = false, next = false;

    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        getDataIntent();
        remap();
        init();
        addViewPager();
        eventClick();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            if(notificationManager!=null){
                notificationManager.cancelAll();

                unregisterReceiver(broadcastReceiver);
            }

        }

    }

    private void getDataIntent() {
        songArrayList.clear();

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("nameList")) {
                nameList = intent.getStringExtra("nameList");
            }

            if (intent.hasExtra("idSong")) {
                Song songNew = intent.getParcelableExtra("idSong");
                songArrayList.add(songNew);
            } else if (intent.hasExtra("arraySong")) {
                ArrayList<Song> songArrayListNew = intent.getParcelableArrayListExtra("arraySong");
                songArrayList = songArrayListNew;
            }
            song = songArrayList.get(0);


        }

    }


    private void remap() {
        circleIndicatorPlaySong = findViewById(R.id.circleIndicatorPlaySong);
        toolbarPlaySong = findViewById(R.id.toolbarPlaySong);
        viewPagerPlaySong = findViewById(R.id.viewPagerPlaySong);
        seekBarPlaySong = findViewById(R.id.seekBarPlaySong);
        imgRandom = findViewById(R.id.imgRandom);
        imgPrevious = findViewById(R.id.imgPrevious);
        imgPlay = findViewById(R.id.imgPlay);
        imgNext = findViewById(R.id.imgNext);
        imgRepeat = findViewById(R.id.imgRepeat);

        fragmentListSong = new FragmentListSong();
        fragmentMusicDisc = new FragmentMusicDisc();
        fragmentLyric = new FragmentLyric();
        progressBar = findViewById(R.id.progressBarPlaySong);


        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTotalTimeSong = findViewById(R.id.txtTotalTimeSong);
    }

    private void init() {
        setSupportActionBar(toolbarPlaySong);
        if (getSupportActionBar() != null && songArrayList.size() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarPlaySong.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlaySong.stop();
                    mediaPlaySong.release();;
                    mediaPlaySong=null;
                    finish();
                }
            });

            toolbarPlaySong.setTitleTextColor(Color.WHITE);


        }


    }

    private void addViewPager() {
        playSongAdapter = new PlaySongAdapter(getSupportFragmentManager(), 3);

        playSongAdapter.addFragment(fragmentListSong);
        playSongAdapter.addFragment(fragmentMusicDisc);
        playSongAdapter.addFragment(fragmentLyric);
        viewPagerPlaySong.setAdapter(playSongAdapter);
        circleIndicatorPlaySong.setViewPager(viewPagerPlaySong);
        viewPagerPlaySong.setCurrentItem(1);


    }

    private void PlayMusic() {
        if (songArrayList.size() > 0) {
            getSupportActionBar().setTitle(songArrayList.get(position).getNameSong() + "-" + songArrayList.get(position).getSinger());
            loadSongFromInternet(songArrayList.get(0).getLinkSong());
            imgPlay.setImageResource(R.drawable.ic_pause);
            if (playSongAdapter.getItem(1) != null && playSongAdapter.getItem(0) != null) {
                fragmentListSong = (FragmentListSong) playSongAdapter.getItem(0);
                fragmentMusicDisc = (FragmentMusicDisc) playSongAdapter.getItem(1);
            }
            CreateNotification.createNotification(PlaySongActivity.this,songArrayList.get(position),
                    R.drawable.ic_pause,
                    position,
                    songArrayList.size()-1);

        }
    }

    public void loadSongFromInternet(String linkSong) {
        progressBar.setVisibility(View.VISIBLE);

        try {
            mediaPlaySong = new MediaPlayer();
            mediaPlaySong.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlaySong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlaySong = null;

                }
            });

            mediaPlaySong.setDataSource(linkSong);
            mediaPlaySong.prepareAsync();
            mediaPlaySong.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    progressBar.setVisibility(View.GONE);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        createNotification();
                        registerReceiver(broadcastReceiver,new IntentFilter("TRACKS_TRACKS"));
                        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
                    }
                    TotalTime();
                    UpdateTimeSong();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void TotalTime() {

        txtTotalTimeSong.setText(dateFormat.format(mediaPlaySong.getDuration()));
        seekBarPlaySong.setMax(mediaPlaySong.getDuration());
    }

    private void UpdateTimeSong() {
       i=1000;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlaySong != null) {
                    seekBarPlaySong.setProgress(mediaPlaySong.getCurrentPosition());
                    i+=new Random().nextInt(10000);

                    seekBarPlaySong.setSecondaryProgress(seekBarPlaySong.getProgress()+i);


                    txtTimeSong.setText(dateFormat.format(seekBarPlaySong.getProgress()));
                    handler.postDelayed(this, 300);
                    mediaPlaySong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);

        final Handler handler1 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (songArrayList.size() > 0) {
                        if (mediaPlaySong.isPlaying() && mediaPlaySong != null) {
                            mediaPlaySong.stop();
                            mediaPlaySong.release();
                            mediaPlaySong = null;
                        }
                        if (position < songArrayList.size()) {
                            imgPlay.setImageResource(R.drawable.ic_pause);
                            position++;
                            if (repeat == true) {
                                if (position == songArrayList.size()) {
                                    position = 0;
                                }
                            }
                            if (checkRandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(songArrayList.size() - 1);
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > songArrayList.size() - 1) {
                                position = 0;
                            }
                            song = songArrayList.get(position);
                            viewPagerPlaySong.setCurrentItem(1);

                            loadSongFromInternet(songArrayList.get(position).getLinkSong());
                            fragmentMusicDisc.getImageMusicDisc(songArrayList.get(position).getImgSong());
                            getSupportActionBar().setTitle(songArrayList.get(position).getNameSong() + "-" + songArrayList.get(position).getSinger());

                        }
                    }
                    imgRepeat.setClickable(false);
                    imgNext.setClickable(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgRepeat.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);


    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (playSongAdapter.getItem(1) != null) {
                    if (songArrayList.size() > 0) {
                        PlayMusic();
                        fragmentMusicDisc.getStopObjectAnimator();
                        fragmentMusicDisc.getImageMusicDisc(songArrayList.get(0).getImgSong());

                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 200);
                    }
                }

            }
        }, 500);

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlaySong.isPlaying() && playSongAdapter.getItem(0) != null && playSongAdapter.getItem(1) != null) {
                   onSongPause();
                } else {
                    onSongPlay();
                }
                UpdateTimeSong();
            }
        });

        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkRandom == true) {
                        checkRandom = false;
                        imgRandom.setImageResource(R.drawable.ic_randrom_false);
                    }
                    repeat = true;
                    imgRepeat.setImageResource(R.drawable.ic_repeat_true);

                } else {
                    repeat = false;
                    imgRepeat.setImageResource(R.drawable.ic_repeat_false);
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgRepeat.setImageResource(R.drawable.ic_repeat_false);
                    }
                    checkRandom = true;
                    imgRandom.setImageResource(R.drawable.ic_randrom_true);
                } else {
                    checkRandom = false;
                    imgRandom.setImageResource(R.drawable.ic_randrom_false);
                }
            }
        });

        seekBarPlaySong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtTimeSong.setText(dateFormat.format(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlaySong.seekTo(seekBar.getProgress());


            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSongNext();
            }
        });

        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSongPrevious();
            }
        });


    }


    @Override
    public void getSong(int index) {
        position=index;
        if (mediaPlaySong.isPlaying() && mediaPlaySong != null) {
            mediaPlaySong.stop();
            mediaPlaySong.release();
            mediaPlaySong = null;
        }
        song = songArrayList.get(position);
        viewPagerPlaySong.setCurrentItem(1);
        loadSongFromInternet(songArrayList.get(position).getLinkSong());
        fragmentMusicDisc.getImageMusicDisc(songArrayList.get(position).getImgSong());
        getSupportActionBar().setTitle(songArrayList.get(position).getNameSong() + "-" + songArrayList.get(position).getSinger());
    }



    public void createNotification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CreateNotification.CHANNEL_ID,
                    "Nhạc vui vẻ", NotificationManager.IMPORTANCE_HIGH);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            channel.enableLights(true);
            notificationManager=getSystemService(NotificationManager.class);
            if(notificationManager !=null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action="";
            if(intent!=null && intent.hasExtra("action_name") ){
                action=intent.getExtras().getString("action_name");
            }
            switch (action){
                case CreateNotification.ACTION_PREVIOUS:
                    onSongPrevious();
                    break;
                case CreateNotification.ACTION_PLAY:
                    if(mediaPlaySong.isPlaying() && playSongAdapter.getItem(0) != null && playSongAdapter.getItem(1) != null){
                        onSongPause();
                    }
                    else {
                        onSongPlay();
                    }
                    UpdateTimeSong();
                    break;
                case CreateNotification.ACTION_NEXT:
                    onSongNext();
                    break;
            }

        }
    };
    @Override
    public void onSongPrevious() {

        if (songArrayList.size() > 0) {
            if (mediaPlaySong.isPlaying() && mediaPlaySong != null) {
                mediaPlaySong.stop();
                mediaPlaySong.release();
                mediaPlaySong = null;
            }
            if (position < songArrayList.size()) {
                imgPlay.setImageResource(R.drawable.ic_pause);
                position--;
                if (position < 0) {
                    position = songArrayList.size() - 1;
                }
                if (repeat == true) {
                    if (position == 0) {
                        position = songArrayList.size() - 1;
                    }
                }
                if (checkRandom == true) {
                    Random random = new Random();
                    int index = random.nextInt(songArrayList.size() - 1);
                    if (index == position) {
                        position = index - 1;
                    }
                    position = index;
                }
                song = songArrayList.get(position);
                viewPagerPlaySong.setCurrentItem(1);
                loadSongFromInternet(songArrayList.get(position).getLinkSong());
                fragmentMusicDisc.getImageMusicDisc(songArrayList.get(position).getImgSong());
                getSupportActionBar().setTitle(songArrayList.get(position).getNameSong() + "-" + songArrayList.get(position).getSinger());

                CreateNotification.createNotification(PlaySongActivity.this,
                        songArrayList.get(position),
                        R.drawable.ic_pause,position,
                        songArrayList.size());
            }
        }
        imgRepeat.setClickable(false);
        imgNext.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgRepeat.setClickable(true);
                imgNext.setClickable(true);
            }
        }, 3000);

    }

    @Override
    public void onSongNext() {
        if (songArrayList.size() > 0) {
            if (mediaPlaySong.isPlaying() && mediaPlaySong != null) {
                mediaPlaySong.stop();
                mediaPlaySong.release();
                mediaPlaySong = null;
            }
            if (position < songArrayList.size()) {
                imgPlay.setImageResource(R.drawable.ic_pause);
                position++;
                if (repeat == true) {
                    if (position == songArrayList.size()) {
                        position = 0;
                    }
                }
                if (checkRandom == true) {
                    Random random = new Random();
                    int index = random.nextInt(songArrayList.size() - 1);
                    if (index == position) {
                        position = index - 1;
                    }
                    position = index;
                }
                if (position > songArrayList.size() - 1) {
                    position = 0;
                }
                song = songArrayList.get(position);
                viewPagerPlaySong.setCurrentItem(1);

                loadSongFromInternet(songArrayList.get(position).getLinkSong());
                fragmentMusicDisc.getImageMusicDisc(songArrayList.get(position).getImgSong());
                getSupportActionBar().setTitle(songArrayList.get(position).getNameSong() + "-" + songArrayList.get(position).getSinger());

                CreateNotification.createNotification(PlaySongActivity.this,
                        songArrayList.get(position),
                        R.drawable.ic_pause,
                        position,
                        songArrayList.size());
            }
        }
        imgRepeat.setClickable(false);
        imgNext.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgRepeat.setClickable(true);
                imgNext.setClickable(true);
            }
        }, 3000);

    }

    @Override
    public void onSongPlay() {
        mediaPlaySong.start();
        imgPlay.setImageResource(R.drawable.ic_pause);
        fragmentMusicDisc.getStartObjectAnimator();
        fragmentListSong.setImagePause();

        CreateNotification.createNotification(PlaySongActivity.this,
                songArrayList.get(position),
                R.drawable.ic_pause,
                position,
                songArrayList.size());
    }

    @Override
    public void onSongPause() {
        mediaPlaySong.pause();
        imgPlay.setImageResource(R.drawable.ic_play);
        fragmentMusicDisc.getPauseObjectAnimator();
        fragmentListSong.setImagePlaying();

        CreateNotification.createNotification(PlaySongActivity.this,
                songArrayList.get(position),
                R.drawable.ic_play,
                position,
                songArrayList.size());

    }
}


