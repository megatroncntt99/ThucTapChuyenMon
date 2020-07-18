package com.example.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Interface.Playable;
import com.example.Model.Song;
import com.example.Notification.CreateNotification;
import com.example.Notification.OnClearFromRecentService;
import com.example.Service.APIService;
import com.example.Service.DataService;
import com.example.appnhacvuive.MainActivity;
import com.example.appnhacvuive.PlaySongActivity;
import com.example.appnhacvuive.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentPlaySong extends Fragment implements Playable {

    View viewPlaySong;
    CircleImageView circleImageViewDiscSong;
    ObjectAnimator objectAnimator;
    ProgressBar seekBarPlaySong;
    ImageView imgPlay, imgNext;
    ImageView DislikeSong, LikeSong;
    TextView txtNameFragmentPlaySong, txtSingerFragmentPlaySong;
    boolean next = false;
    public ArrayList<Song> songArrayList = new ArrayList<>();
    public int position = 0;
    NotificationManager notificationManager;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewPlaySong = inflater.inflate(R.layout.fragment_play_song, container, false);
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        remap();
        addEvent();

        return viewPlaySong;
    }

    @Override
    public void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.songArrayList.size() > 0) {
                    songArrayList = MainActivity.songArrayList;
                    position = MainActivity.position;

                    getLike(songArrayList.get(position));
                    getDataSongPlaying(songArrayList.get(position));

                    if (MainActivity.mediaPlaySong != null) {
                        if (MainActivity.mediaPlaySong.isPlaying()) {
                            getStartObjectAnimator();
                            TotalTime();
                            imgPlay.setImageResource(R.drawable.ic_pause_fragment);
                            UpdateTimeSong();
                            CreateNotification.createNotification(getActivity(),
                                    songArrayList.get(position),
                                    R.drawable.ic_pause,
                                    position,
                                    songArrayList.size());

                        } else {
                            getStopObjectAnimator();
                            UpdateTimeSong();
                            imgPlay.setImageResource(R.drawable.ic_play_fragment);
                            CreateNotification.createNotification(getActivity(),
                                    songArrayList.get(position),
                                    R.drawable.ic_play,
                                    position,
                                    songArrayList.size());
                        }
                    } else {
                        seekBarPlaySong.setProgress(0);
                        getStopObjectAnimator();
                        imgPlay.setImageResource(R.drawable.ic_play_fragment);
                        CreateNotification.createNotification(getActivity(),
                                songArrayList.get(position),
                                R.drawable.ic_play,
                                position,
                                songArrayList.size());

                    }
                }
            }
        }, 500);

    }


    private void remap() {
        progressBar = viewPlaySong.findViewById(R.id.progressBarFragmentPlaySong);
        txtNameFragmentPlaySong = viewPlaySong.findViewById(R.id.txtNameFragmentPlaySong);
        txtSingerFragmentPlaySong = viewPlaySong.findViewById(R.id.txtSingerFragmentPlaySong);

        circleImageViewDiscSong = viewPlaySong.findViewById(R.id.imgFragmentPlaySong);
        objectAnimator = ObjectAnimator.ofFloat(circleImageViewDiscSong, "rotation", 0f, 360f);
        objectAnimator.setDuration(15000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setStartDelay(2000);
        objectAnimator.setInterpolator(new LinearInterpolator());

        seekBarPlaySong = viewPlaySong.findViewById(R.id.seekBarFragmentPlaySong);
        imgPlay = viewPlaySong.findViewById(R.id.imgPlayFragment);
        imgNext = viewPlaySong.findViewById(R.id.imgNextFragment);
        DislikeSong = viewPlaySong.findViewById(R.id.DislikeSongFragment);
        LikeSong = viewPlaySong.findViewById(R.id.LikeSongFragment);
    }

    private void addEvent() {
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.mediaPlaySong != null) {
                    if (MainActivity.mediaPlaySong.isPlaying() && songArrayList.size() > 0) {
                        onSongPause();
                    } else {
                        onSongPlay();
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotification();
                        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("TRACKS_TRACKS"));
                        getActivity().startService(new Intent(getContext(), OnClearFromRecentService.class));
                    }
                    UpdateTimeSong();
                } else {

                    imgPlay.setImageResource(R.drawable.ic_pause_fragment);

                    loadSongFromInternet(songArrayList.get(position).getLinkSong());
                    CreateNotification.createNotification(getActivity(), songArrayList.get(position),
                            R.drawable.ic_pause,
                            position,
                            songArrayList.size() - 1);

                }


            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSongNext();
            }
        });

        LikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService = APIService.getService();
                Call<String> callBack = dataService.SetUpdateLikeSong(songArrayList.get(position).getIdSong(), -1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if (result.equals("success")) {
                            LikeSong.setVisibility(View.INVISIBLE);
                            DislikeSong.setVisibility(View.VISIBLE);
                            MainActivity.database.SetData("DELETE FROM SongLike WHERE IdSong='" + songArrayList.get(position).getIdSong() + "' ");
                            Toast t = Toast.makeText(getActivity(), "Đã gỡ " + songArrayList.get(position).getNameSong() + " khỏi thư viện ", Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Toast t1 = Toast.makeText(getActivity(), "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                        t1.setGravity(Gravity.CENTER, 0, 0);
                        t1.show();
                    }
                });

            }
        });


        DislikeSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService dataService = APIService.getService();
                Call<String> callBack = dataService.SetUpdateLikeSong(songArrayList.get(position).getIdSong(), 1);
                callBack.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if (result.equals("success")) {
                            LikeSong.setVisibility(View.VISIBLE);
                            DislikeSong.setVisibility(View.INVISIBLE);
                            MainActivity.database.InsertDataSongLike("SongLike", songArrayList.get(position).getIdSong(), songArrayList.get(position).getNameSong(), songArrayList.get(position).getImgSong(), songArrayList.get(position).getSinger(), songArrayList.get(position).getLinkSong(), songArrayList.get(position).getLikeSong());
                            Toast t = Toast.makeText(getActivity(), "Đã thêm " + songArrayList.get(position).getNameSong() + " vào thư viện ", Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast t1 = Toast.makeText(getActivity(), "Kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT);
                        t1.setGravity(Gravity.CENTER, 0, 0);
                        t1.show();
                    }
                });

            }
        });
    }


    public void getStartObjectAnimator() {

        objectAnimator.start();

    }

    public void getPauseObjectAnimator() {

        objectAnimator.pause();
    }

    public void getResumeObjectAnimator() {

        objectAnimator.resume();

    }

    public void getStopObjectAnimator() {
        objectAnimator.start();
        objectAnimator.pause();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null) {
                notificationManager.cancelAll();
                getActivity().unregisterReceiver(broadcastReceiver);
                Intent intent = new Intent(getActivity(), OnClearFromRecentService.class);
                getActivity().stopService(intent);
            }
        }

    }


    public void loadSongFromInternet(String linkSong) {
        progressBar.setVisibility(View.VISIBLE);
        getStopObjectAnimator();

        try {
            MainActivity.mediaPlaySong = new MediaPlayer();
            MainActivity.mediaPlaySong.setAudioStreamType(AudioManager.STREAM_MUSIC);
            MainActivity.mediaPlaySong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MainActivity.mediaPlaySong = null;

                }
            });

            MainActivity.mediaPlaySong.setDataSource(linkSong);
            MainActivity.mediaPlaySong.prepareAsync();
            MainActivity.mediaPlaySong.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                    getStartObjectAnimator();
                    progressBar.setVisibility(View.GONE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotification();
                        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("TRACKS_TRACKS"));
                        getActivity().startService(new Intent(getContext(), OnClearFromRecentService.class));
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
        seekBarPlaySong.setMax(MainActivity.mediaPlaySong.getDuration());
    }

    private void UpdateTimeSong() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.mediaPlaySong != null) {
                    seekBarPlaySong.setProgress(MainActivity.mediaPlaySong.getCurrentPosition());


                    handler.postDelayed(this, 300);
                    MainActivity.mediaPlaySong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
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

                    onSongNext();
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);


    }

    public void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID,
                    "Nhạc vui vẻ", NotificationManager.IMPORTANCE_LOW);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            channel.enableLights(true);
            if (channel != null) {
                notificationManager = getActivity().getSystemService(NotificationManager.class);
            }
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = "";
            if (intent != null && intent.hasExtra("action_name")) {
                action = intent.getExtras().getString("action_name");
            }
            switch (action) {
                case CreateNotification.ACTION_PREVIOUS:
                    onSongPrevious();
                    break;
                case CreateNotification.ACTION_PLAY:
                    if (MainActivity.mediaPlaySong.isPlaying() && songArrayList.size() > 0) {
                        onSongPause();
                    } else {
                        onSongPlay();
                    }
                    UpdateTimeSong();
                    break;
                case CreateNotification.ACTION_NEXT:
                    onSongNext();
                    break;
                case CreateNotification.ACTION_CLOSE:
                    onClearNotification();
                    break;
            }

        }
    };


    @Override
    public void onSongPrevious() {

        if (songArrayList.size() > 0) {
            if (MainActivity.mediaPlaySong != null && MainActivity.mediaPlaySong.isPlaying()) {
                MainActivity.mediaPlaySong.stop();
                MainActivity.mediaPlaySong.release();
                MainActivity.mediaPlaySong = null;
            }
            if (position < songArrayList.size()) {
                imgPlay.setImageResource(R.drawable.ic_pause_fragment);
                position--;
                if (position < 0) {
                    position = songArrayList.size() - 1;
                }
                loadSongFromInternet(songArrayList.get(position).getLinkSong());
                getDataSongPlaying(songArrayList.get(position));
                MainActivity.position = position;
                getLike(songArrayList.get(position));
                CreateNotification.createNotification(getActivity(),
                        songArrayList.get(position),
                        R.drawable.ic_pause, position,
                        songArrayList.size());
            }
        }

        imgNext.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgNext.setClickable(true);
            }
        }, 3000);

    }

    @Override
    public void onSongNext() {
        if (songArrayList.size() > 0) {
            if (MainActivity.mediaPlaySong != null && MainActivity.mediaPlaySong.isPlaying()) {
                MainActivity.mediaPlaySong.stop();
                MainActivity.mediaPlaySong.release();
                MainActivity.mediaPlaySong = null;
            }
            if (position < songArrayList.size()) {
                imgPlay.setImageResource(R.drawable.ic_pause_fragment);
                position++;

                if (position > songArrayList.size() - 1) {
                    final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                    bottomSheetDialog.setCanceledOnTouchOutside(false);
                    bottomSheetDialog.setContentView(R.layout.dialog_playlist_resume);
                    final TextView txtYes = bottomSheetDialog.findViewById(R.id.txtYes);
                    TextView txtNo = bottomSheetDialog.findViewById(R.id.txtNo);
                    txtYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bottomSheetDialog.dismiss();
                            position = 0;
                            loadSongFromInternet(songArrayList.get(position).getLinkSong());
                            getDataSongPlaying(songArrayList.get(position));
                            getLike(songArrayList.get(position));
                            MainActivity.position = position;
                            CreateNotification.createNotification(getActivity(),
                                    songArrayList.get(position),
                                    R.drawable.ic_pause,
                                    position,
                                    songArrayList.size());
                        }
                    });
                    txtNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            position = songArrayList.size() - 1;
                            MainActivity.position = position;
                            bottomSheetDialog.dismiss();
                            seekBarPlaySong.setProgress(0);
                            getStopObjectAnimator();
                            imgPlay.setImageResource(R.drawable.ic_play_fragment);
                            CreateNotification.createNotification(getActivity(),
                                    songArrayList.get(position),
                                    R.drawable.ic_play,
                                    position,
                                    songArrayList.size());
                        }
                    });

                    bottomSheetDialog.show();
                } else {
                    loadSongFromInternet(songArrayList.get(position).getLinkSong());
                    getDataSongPlaying(songArrayList.get(position));
                    getLike(songArrayList.get(position));
                    MainActivity.position = position;
                    CreateNotification.createNotification(getActivity(),
                            songArrayList.get(position),
                            R.drawable.ic_pause,
                            position,
                            songArrayList.size());
                }

            }
        }

        imgNext.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgNext.setClickable(true);
            }
        }, 3000);

    }

    private void getLike(Song song) {
        String sql = "select * from SongLike";
        boolean like = false;
        Cursor cursor = MainActivity.database.GetData(sql);
        while (cursor.moveToNext()) {
            if (song.getIdSong().trim().equals(cursor.getString(0))) {
                like = true;
                break;
            }
        }
        if (like) {
            LikeSong.setVisibility(View.VISIBLE);
            DislikeSong.setVisibility(View.INVISIBLE);
        } else {
            LikeSong.setVisibility(View.INVISIBLE);
            DislikeSong.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSongPlay() {
        getActivity().startService(new Intent(getActivity(), OnClearFromRecentService.class));
        MainActivity.mediaPlaySong.start();
        imgPlay.setImageResource(R.drawable.ic_pause_fragment);
        getResumeObjectAnimator();

        CreateNotification.createNotification(getActivity(),
                songArrayList.get(position),
                R.drawable.ic_pause,
                position,
                songArrayList.size());
    }

    @Override
    public void onSongPause() {
        MainActivity.mediaPlaySong.pause();
        imgPlay.setImageResource(R.drawable.ic_play_fragment);
        getPauseObjectAnimator();
        CreateNotification.createNotification(getActivity(),
                songArrayList.get(position),
                R.drawable.ic_play,
                position,
                songArrayList.size());


    }

    @Override
    public void onClearNotification() {
        if (MainActivity.mediaPlaySong != null && !MainActivity.mediaPlaySong.isPlaying()) {
            Intent intent = new Intent(getActivity(), OnClearFromRecentService.class);
            getActivity().stopService(intent);
        }
    }

    public void getDataSongPlaying(Song song) {
        Picasso.get().load(song.getImgSong())
                .error(R.drawable.ic_error_outline_black_24dp)
                .placeholder(R.drawable.custom_load_image)
                .into(circleImageViewDiscSong);
        txtNameFragmentPlaySong.setText(song.getNameSong().trim());
        txtSingerFragmentPlaySong.setText(song.getSinger());
    }


}
