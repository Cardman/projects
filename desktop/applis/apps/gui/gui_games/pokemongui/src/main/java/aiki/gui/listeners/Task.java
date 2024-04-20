package aiki.gui.listeners;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;
import code.threads.AbstractAtomicBoolean;

public class Task implements Runnable {

    private final AbstractAtomicBoolean enabled;

    private Direction dir;

    private final Scene scene;

    private final FacadeGame facade;

    private final WindowAiki window;

    public Task(Scene _scene, FacadeGame _facade, WindowAiki _window) {
        scene = _scene;
        facade = _facade;
        window = _window;
        enabled = _window.getThreadFactory().newAtomicBoolean();
    }

    @Override
    public void run() {
        if (!window.isEnabledMove()) {
            return;
        }
        if (isPainting()) {
            return;
        }
        if (!facade.isEnabledMovingHero()) {
            return;
        }
        window.getThreadFactory().newStartedThread(new Painting(scene, facade, dir, window));
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction _dir) {
        dir = _dir;
    }

    public boolean isPainting() {
//        return painting != null && painting.isAlive();
        return window.isPaintingScene();
    }

    public AbstractAtomicBoolean getEnabled() {
        return enabled;
    }
}
