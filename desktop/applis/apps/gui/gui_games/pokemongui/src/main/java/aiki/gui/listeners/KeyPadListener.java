package aiki.gui.listeners;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;

public class KeyPadListener implements KeyListener {

    private final WindowAiki window;

    private final FacadeGame facade;

    private Scene scene;

    private Painting thread;

    public KeyPadListener(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    public void setSceneKepPad(Scene _scene) {
        scene = _scene;
    }

    @Override
    public void keyPressed(KeyEvent _e) {
//        if (_e.getKeyCode() == KeyEvent.VK_F1) {
//            //data web open
//            if (facade.getData() == null) {
//                return;
//            }
//            window.showDataWeb();
//            return;
//        }
        if (_e.getKeyCode() == KeyEvent.VK_F2) {
            if (!window.isInBattle()) {
                return;
            }
            window.showFightData();
            return;
        }
        if (!window.isEnabledKeyPad()) {
            return;
        }
        if (scene == null) {
            return;
        }
        if (!facade.isEnabledMovingHero()) {
            return;
        }
        Direction dir_;
        if (_e.getKeyCode() == KeyEvent.VK_UP) {
            dir_ = Direction.UP;
        } else if (_e.getKeyCode() == KeyEvent.VK_DOWN) {
            dir_ = Direction.DOWN;
        } else if (_e.getKeyCode() == KeyEvent.VK_LEFT) {
            dir_ = Direction.LEFT;
        } else if (_e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dir_ = Direction.RIGHT;
        } else {
            return;
        }
        if (window.isPaintingScene()) {
            return;
        }
        thread = new Painting(scene, facade, dir_, window);
        window.getThreadFactory().newStartedThread(thread);
    }

    @Override
    public void keyReleased(KeyEvent _e) {
        //
    }

    @Override
    public void keyTyped(KeyEvent _e) {
        //
    }
}
