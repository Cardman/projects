package aiki.gui.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;
import code.gui.CustComponent;

public class Task implements ActionListener {

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
    public void actionPerformed(ActionEvent _e) {
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
        CustComponent.newThread(painting).start();
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
