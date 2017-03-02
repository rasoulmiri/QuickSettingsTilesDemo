package io.rmiri.quicksettingstilesdemo;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;

/**
 * Created by Rasoul Miri on 3/2/17.
 */

@TargetApi(Build.VERSION_CODES.N)
public class MyTileService extends TileService {

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        Log.i("TileService", "onTileAdded");
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
        Log.i("TileService", "onTileRemoved");
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Log.i("TileService", "onStartListening");
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        Log.i("TileService", "onStopListening");
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.i("TileService", "onClick");

        //Start main activity but Quick Settings Panel still open
        //startActivity(new Intent(this, MainActivity.class));

        //Start main activity and close Quick Settings Panel
        //startActivityAndCollapse(new Intent(this, MainActivity.class));

        //update tile
        updateTileForOnOrOff();

        //update tile by Animation
        //updateTileByAnimation();

        //show dialog
        //generateDialogAndShow();

        //checking lock and if user enter password, run runnableForUnlock
        //unlockAndRun(runnableForUnlock);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TileService", "onDestroy");
    }

    private void updateTileForOnOrOff() {

        Tile tile = this.getQsTile();

        Icon newIcon;
        String newLabel;
        int newState;

        // Change the tile to match the service status.
        if (tile.getState() == tile.STATE_INACTIVE) {

            newLabel = "on ";

            newIcon = Icon.createWithResource(getApplicationContext(),
                    R.drawable.ic_world);

            newState = Tile.STATE_ACTIVE;

        } else {

            newLabel = "off";

            newIcon = Icon.createWithResource(getApplicationContext(),
                    android.R.drawable.ic_dialog_alert);

            newState = Tile.STATE_INACTIVE;
        }

        // Change the UI of the tile.
        tile.setLabel(newLabel);
        tile.setIcon(newIcon);
        tile.setState(newState);

        // Need to call updateTile for the tile to pick up changes.
        tile.updateTile();
    }

    private void updateTileByAnimation() {

        ValueAnimator rotationIcon = ValueAnimator.ofInt(0, 360);
        rotationIcon.setDuration(360 * 16);
        rotationIcon.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {

                Integer angle = (Integer) animation.getAnimatedValue();

                Bitmap iconBitmap = getBitmapFromVectorDrawable(getApplicationContext(),
                        R.drawable.ic_world);

                Matrix matrix = new Matrix();
                matrix.postRotate(angle);
                iconBitmap = Bitmap.createBitmap(iconBitmap, 0, 0, iconBitmap.getWidth(), iconBitmap.getHeight(), matrix, true);

                //get tile and change icon
                Tile tile = getQsTile();
                Icon icon = Icon.createWithBitmap(iconBitmap);
                tile.setIcon(icon);
                tile.setState(Tile.STATE_ACTIVE);
                tile.setContentDescription("ffffff");

                // Need to call updateTile for the tile to pick up changes.
                tile.updateTile();
            }
        });
        rotationIcon.start();
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void generateDialogAndShow() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Dialog");
        builder.setMessage("Hello...");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        showDialog(builder.create());
    }

    public Runnable runnableForUnlock = new Runnable() {
        @Override
        public void run() {
            Log.i("TileService", "runnableForUnlock");
        }
    };


}

