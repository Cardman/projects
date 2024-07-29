package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.walk.Scene;
import aiki.gui.listeners.Task;
import aiki.map.enums.Direction;
import code.threads.AbstractAtomicInteger;
import code.threads.ThreadUtil;
import code.util.core.IndexConstants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class Painting implements Runnable {

    private final Scene scene;

    private final FacadeGame facade;

    private final Direction dir;

    private final WindowAiki window;

    private final int side;

    private final int pause;
    private final AbstractAtomicInteger aa;

    /**This class thread is independant from EDT*/
    public Painting(Scene _scene, FacadeGame _facade, Direction _dir, WindowAiki _window, AbstractAtomicInteger _a) {
        scene = _scene;
        facade = _facade;
        dir = _dir;
        window = _window;
        side = facade.getMap().getSideLength();
        aa = _a;
        if (window.isAnimateMovingHero()) {
            pause = 20;
        } else {
            pause = 128;
        }
    }

    @Override
    public void run() {
        if (!facade.isEnabledMovingHero()) {
            return;
        }
        if (window.getScenePanel().getPaintingScene().getAndSet(true)) {
            return;
        }
//        if (window.isPaintingScene()) {
//            return;
//        }
        window.setPaintingScene();
        facade.move(dir);
        window.setSavedGame(false);
        if (!window.isAnimateMovingHero()) {
            scene.setAnimated(false);
            scene.keepTiles();
            facade.changeCamera();
            scene.load(window.getImageFactory(),facade, false);
            ThreadUtil.sleep(window.getThreadFactory(),pause);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            if (isChangeToFightScene()) {
                window.getFrames().getCompoFactory().invokeNow(new SetFightPanel(window));
                return;
            }
            window.getFrames().getCompoFactory().invokeNow(new SetInteractionScene(window));
            return;
        }
        scene.setAnimated(true);
        if (isChangeToFightScene()) {
            moveAnim();
            window.getFrames().getCompoFactory().invokeNow(new SetFightPanel(window));
            return;
        }
        if (facade.getGame().getNbSteps() == 0) {
            facade.changeCamera();
            scene.load(window.getImageFactory(),facade, false);
            scene.setDelta(0, false);
            ThreadUtil.sleep(window.getThreadFactory(),pause);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            window.getFrames().getCompoFactory().invokeNow(new SetInteractionScene(window));
            return;
        }
        moveAnim();
        window.getFrames().getCompoFactory().invokeNow(new SetInteractionScene(window));
    }

    private boolean isChangeToFightScene() {
        boolean ch_ = facade.isChangeToFightScene();
        if (ch_ && aa != null) {
            aa.set(Task.STOPPED_TASK);
        }
        return ch_;
    }

    public void moveAnim() {
        if (facade.getGame().isPlaceChanged()) {
            scene.keepTiles();
            facade.changeCamera();
            scene.load(window.getImageFactory(),facade, false);
            ThreadUtil.sleep(window.getThreadFactory(),pause * 5L);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
        } else {
            facade.changeCamera(dir);
            scene.load(window.getImageFactory(), facade, false);
            for (int i = IndexConstants.FIRST_INDEX; i <= side; i++) {
                scene.setDelta(i - side, true);
                ThreadUtil.sleep(window.getThreadFactory(), pause);
                AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            }
        }
    }
}
