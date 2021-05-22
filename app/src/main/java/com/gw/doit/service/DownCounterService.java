package com.gw.doit.service;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gw.doit.InitialActivity;
import com.gw.doit.R;

import java.util.stream.IntStream;

public class DownCounterService extends IntentService {

    public DownCounterService() {
        super("DownCounterService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Notification.Builder notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker("TESTANDO");
        Intent notificationIntent = new Intent(this, InitialActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this, "Titulo Teste",
//                "Mensagem teste", pendingIntent);
        notification.setContentIntent(pendingIntent);
        notification.setContentText("Executando service durante 20s");
        notification.setContentTitle("Doit");
        startForeground(1, notification.build());

        IntStream.range(1,21)
                .forEach( i -> {
                    Log.i("teste_service", "Msg: "+i);
                    notification.setContentTitle("Doit "+(i*5)+"%");
                    notification.setContentText("Executando service durante 20s: "+i);
                    startForeground(1, notification.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
