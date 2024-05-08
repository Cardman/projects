package aiki.gui.listeners;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;
import code.threads.AbstractAtomicInteger;
import code.threads.ThreadUtil;

public class Task implements Runnable {

    public static final int STOPPED_TASK = 0;
    public static final int ALIVE_TASK = 1;
    private final AbstractAtomicInteger enabled;

    private Direction dir;

    private final Scene scene;

    private final FacadeGame facade;

    private final WindowAiki window;

    public Task(Scene _scene, FacadeGame _facade, WindowAiki _window) {
        scene = _scene;
        facade = _facade;
        window = _window;
        enabled = _window.getThreadFactory().newAtomicInteger();
    }

    @Override
    public void run() {
//        if (!window.isEnabledMove()) {
//            return;
//        }
//        if (isPainting()) {
//            return;
//        }
        while (window.getTaskEnabled().status(enabled) == ALIVE_TASK) {
            new Painting(scene, facade, getDir(), window).run();
            ThreadUtil.sleep(window.getThreadFactory(), 100);
        }
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction _dir) {
        dir = _dir;
    }

//    public boolean isPainting() {
////        return painting != null && painting.isAlive();
//        return window.isPaintingScene();
//    }

    public AbstractAtomicInteger getEnabled() {
        return enabled;
    }
}
