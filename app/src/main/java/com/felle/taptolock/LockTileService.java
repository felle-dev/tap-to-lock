package com.felle.taptolock;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;

public class LockTileService extends TileService {
    private static final String TAG = "LockTileService";

    @SuppressLint("StartActivityAndCollapseDeprecated")
    @Override
    public void onClick() {
        super.onClick();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            unlockAndRun(() -> {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    int flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            this,
                            0,
                            intent,
                            flags
                    );
                    startActivityAndCollapse(pendingIntent);
                } else {
                    startActivityAndCollapse(intent);
                }
            });
        }
    }
}