package aiki.gui.listeners;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;
import code.gui.AbsCtrlKeyState;
import code.gui.GuiConstants;
import code.gui.events.AbsKeyListener;

public class KeyPadListener implements AbsKeyListener {

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
    public void keyPressed(AbsCtrlKeyState _e, char _keyChar, int _keyCode) {
//        if (_e.getKeyCode() == KeyEvent.VK_F1) {
//            //data web open
//            if (facade.getData() == null) {
//                return;
//            }
//            window.showDataWeb();
//            return;
//        }
        if (_keyCode == GuiConstants.VK_F2) {
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
        if (_keyCode == GuiConstants.VK_UP) {
            dir_ = Direction.UP;
        } else if (_keyCode == GuiConstants.VK_DOWN) {
            dir_ = Direction.DOWN;
        } else if (_keyCode == GuiConstants.VK_LEFT) {
            dir_ = Direction.LEFT;
        } else if (_keyCode == GuiConstants.VK_RIGHT) {
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
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        //
    }

    @Override
    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
        //
    }
}
