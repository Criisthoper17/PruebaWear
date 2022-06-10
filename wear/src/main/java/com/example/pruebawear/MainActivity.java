package com.example.pruebawear;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Button wBotton = null;

    private TextView mTextView;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notofication;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;


    String idChannel = "Mi_Canal";
    int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;

    String longText = "with BigStyle, only a single line of text would be visible" +
            "Any additional text would not appear directly in the notification";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBotton = findViewById(R.id.wButton);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();
        bigTextStyle = new NotificationCompat.BigTextStyle();

        wBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notificacion";

                NotificationChannel notificationChannel =
                        new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                notofication = new NotificationCompat.Builder(MainActivity.this, idChannel)

                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Wear")
                        .setContentText(longText)
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                        .setStyle(bigTextStyle);

                nm.notify(idNotification, notofication.build());
            }

        });
    }
}