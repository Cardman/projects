package aiki.gui.listeners;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;

public class Task implements Runnable {

    private Painting painting;

    private Direction dir;

    private Scene scene;

    private FacadeGame facade;

    private MainWindow window;

    public Task(Scene _scene, FacadeGame _facade, MainWindow _window) {
        scene = _scene;
        facade = _facade;
        window = _window;
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
        painting = new Painting(scene, facade, dir, window);
        window.getThreadFactory().newStartedThread(painting);
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
}
