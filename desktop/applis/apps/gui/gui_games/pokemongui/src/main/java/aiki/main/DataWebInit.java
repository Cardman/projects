package aiki.main;

import aiki.gui.WindowAiki;

public final class DataWebInit implements Runnable {
    private final WindowAiki windowAiki;

    public DataWebInit(WindowAiki _w) {
        this.windowAiki = _w;
    }

    @Override
    public void run() {
        windowAiki.getPreparedDataWebTask().run();
        windowAiki.getDataWeb().setEnabled(true);
    }
}
