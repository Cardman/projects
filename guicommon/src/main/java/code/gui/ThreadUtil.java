package code.gui;

import code.util.CustList;

public final class ThreadUtil {

    private ThreadUtil() {
    }

    /**@see System#exit(int)*/
    public static void exit() {
        System.exit(CustList.SIZE_EMPTY);
    }

    public static void sleep(long _time) {
        long millis_ = System.currentTimeMillis();
        while (millis_ + _time > System.currentTimeMillis()) {
            continue;
        }
    }

}
