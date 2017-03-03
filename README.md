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

* [Training Quick Settings Tiles](http://rmiri.io/2017/03/03/%D8%A7%D9%81%D8%B2%D9%88%D8%AF%D9%86-tile-%D8%AF%D8%B1-quick-settings-%D8%AF%D8%B1-%D8%A7%D9%86%D8%AF%D8%B1%D9%88%DB%8C%D8%AF-%DB%B7/)
