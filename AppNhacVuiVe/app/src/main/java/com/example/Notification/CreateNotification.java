package com.example.Notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.Model.Song;
import com.example.appnhacvuive.R;
import com.squareup.picasso.Picasso;

public class CreateNotification {
    public static final String CHANNEL_ID = "channel_1";

    public static final String ACTION_PREVIOUS = "action_previous";
    public static final String ACTION_PLAY = "action_play";
    public static final String ACTION_NEXT = "action_next";
    public static final String ACTION_CLOSE = "action_close";

    public static Notification notification;

    public static void createNotification(Context context, Song song, int btnPlay, int position, int size) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

            final ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            final Bitmap[] icon = {null};
            if (!song.getImgSong().trim().equals("")) {

                Picasso.get().load(song.getImgSong())
                        .placeholder(R.drawable.iconapp)
                        .error(R.drawable.ic_error_outline_black_24dp)
                        .into(imageView);

                icon[0] = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            }

            PendingIntent pendingIntentPrevious;
            int drw_previous;
            if (position == 0) {
                pendingIntentPrevious = null;
                drw_previous = 0;
            } else {
                Intent intentPrevious = new Intent(context, NotificationActionService.class)
                        .setAction(ACTION_PREVIOUS);
                pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT);
                drw_previous = R.drawable.ic_skip_previous;
            }

            Intent intentPlay = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_PLAY);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);


            PendingIntent pendingIntentNext;
            int drw_next;
            if (position == size||position == size-1) {
                pendingIntentNext = null;
                drw_next = 0;
            } else {
                Intent intentNext = new Intent(context, NotificationActionService.class)
                        .setAction(ACTION_NEXT);
                pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
                drw_next = R.drawable.ic_skip_next;
            }


            PendingIntent pendingIntentClose;
            int drw_close=R.drawable.ic_clear_black_24dp;
            Intent intentClose = new Intent(context, NotificationActionService.class).setAction(ACTION_CLOSE);
            pendingIntentClose = PendingIntent.getBroadcast(context, 0, intentClose, PendingIntent.FLAG_UPDATE_CURRENT);


        notification = new NotificationCompat.Builder(context, CHANNEL_ID)

                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle(song.getNameSong())
                .setContentText(song.getSinger())
                .setLargeIcon(icon[0])
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setShowWhen(true)
                .addAction(drw_previous, "Previous", pendingIntentPrevious)
                .addAction(btnPlay, "Play", pendingIntentPlay)
                .addAction(drw_next, "Next", pendingIntentNext)
                .addAction(drw_close, "close", pendingIntentClose)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2, 3)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                .build();
        notificationManagerCompat.notify(1, notification);


    }
}


}
