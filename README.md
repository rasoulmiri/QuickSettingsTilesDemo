# Quick Settings Tiles Demo
Demo quick settings tiles in android 7+

![alt text](http://rmiri.io/wp-content/uploads/2017/03/device-2017-03-03-005536.gif "final project")

#Usage

comment and uncomment code in onClick function in MyTileService.class

```java
 @Override
    public void onClick() {
        super.onClick();
        Log.i("TileService", "onClick");

        //Start main activity but Quick Settings Panel still open
        startActivity(new Intent(this, MainActivity.class));

        //Start main activity and close Quick Settings Panel
        startActivityAndCollapse(new Intent(this, MainActivity.class));

        //update tile
        updateTileForOnOrOff();

        //update tile by Animation
        updateTileByAnimation();

        //show dialog
        generateDialogAndShow();

        //checking lock and if user enter password, run runnableForUnlock
        unlockAndRun(runnableForUnlock);
    }
```
