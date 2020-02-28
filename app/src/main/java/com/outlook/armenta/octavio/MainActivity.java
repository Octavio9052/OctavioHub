package com.outlook.armenta.octavio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    private MyNotificationListener notificationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(getApplicationContext(), MyNotificationListener.class);
        startService(intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(i);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_container);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SimpleDateFormat formatter
                = new SimpleDateFormat ("yyyy.MM.dd h:mm a", Locale.US);




        String dateString = formatter.format(new Date());



        // specify an adapter (see also next example)
        String[] test = {dateString,"Test t asdioljasdklaso pi djasdkln asklnda klnsdkl nasdklna sdasda sdasdasdasd asdasdasd aslkdakln asldkasd alksdlkas klnasdklnasd lkn "};
        MyAdapter mAdapter = new MyAdapter(test);
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent= new Intent(this, MyNotificationListener.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
        Toast.makeText(this, "bind", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
        Toast.makeText(this, "unbind", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyNotificationListener.MyBinder b = (MyNotificationListener.MyBinder) service;
        notificationListener = b.getService();
        Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        notificationListener = null;
    }
}
