package aiki.gui.listeners;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.Scene;
import aiki.gui.threads.Painting;
import aiki.map.enums.Direction;
import code.gui.events.AbsActionListener;

public class KeyPadListener implements AbsActionListener {

    private final WindowAiki window;

    private final FacadeGame facade;

    private final Scene scene;

    private final Direction direction;

    public KeyPadListener(WindowAiki _window, FacadeGame _facade, Scene _sc, Direction _dir) {
        window = _window;
        facade = _facade;
        scene = _sc;
        direction = _dir;
    }

//    public void setSceneKepPad(Scene _scene) {
//        scene = _scene;
//    }

    @Override
    public void action() {
//        if (_e.getKeyCode() == KeyEvent.VK_F1) {
//            //data web open
//            if (facade.getData() == null) {
//                return;
//            }
//            window.showDataWeb();
//            return;
//        }
//        if (_keyCode == GuiConstants.VK_F2) {
//            if (!window.isInBattle()) {
//                return;
//            }
//            window.showFightData();
//            return;
//        }
//        if (!window.isEnabledKeyPad()) {
//            return;
//        }
//        if (scene == null) {
//            return;
//        }
//        if (!facade.isEnabledMovingHero()) {
//            return;
//        }
//        if (window.isPaintingScene()) {
//            return;
//        }
        window.getThreadFactory().newStartedThread(new Painting(scene, facade, direction, window));
    }

//    @Override
//    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
//        //
//    }
//
//    @Override
//    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
//        //
//    }
}
