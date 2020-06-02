package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.walk.Scene;
import aiki.map.enums.Direction;
import code.gui.CustComponent;
import code.threads.ThreadUtil;
import code.util.CustList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class Painting implements Runnable {

    private Scene scene;

    private FacadeGame facade;

    private Direction dir;

    private MainWindow window;

    private int side;

    private int pause;

    /**This class thread is independant from EDT*/
    public Painting(Scene _scene, FacadeGame _facade, Direction _dir, MainWindow _window) {
        scene = _scene;
        facade = _facade;
        dir = _dir;
        window = _window;
        side = facade.getMap().getSideLength();
        if (window.isAnimateMovingHero()) {
            pause = 20;
        } else {
            pause = 128;
        }
    }

    @Override
    public void run() {
        if (window.isPaintingScene()) {
            return;
        }
        window.setPaintingScene(true);
        facade.move(dir);
        window.setSavedGame(false);
        if (!window.isAnimateMovingHero()) {
            scene.setAnimated(false);
            scene.keepTiles();
            facade.changeCamera();
            scene.load(facade, false);
            ThreadUtil.sleep(pause);
            scene.repaintLabel();
            if (facade.isChangeToFightScene()) {
                CustComponent.invokeLater(new SetFightPanel(window));
                return;
            }
            CustComponent.invokeLater(new SetInteractionScene(window));
            return;
        }
        scene.setAnimated(true);
        if (facade.isChangeToFightScene()) {
            if (facade.getGame().isPlaceChanged()) {
                scene.keepTiles();
                facade.changeCamera();
                scene.load(facade, false);
                ThreadUtil.sleep(pause * 5);
                scene.repaintLabel();
            } else {
                facade.changeCamera(dir);
                scene.load(facade, false);
                for (int i = CustList.FIRST_INDEX; i <= side; i++) {
                    scene.setDelta(i - side, true);
                    ThreadUtil.sleep(pause);
                    scene.repaintLabel();
                }
            }
            CustComponent.invokeLater(new SetFightPanel(window));
            return;
        }
        if (facade.getGame().getNbSteps() == 0) {
            facade.changeCamera();
            scene.load(facade, false);
            scene.setDelta(0, false);
            ThreadUtil.sleep(pause);
            scene.repaintLabel();
            CustComponent.invokeLater(new SetInteractionScene(window));
            return;
        }
        if (facade.getGame().isPlaceChanged()) {
            scene.keepTiles();
            facade.changeCamera();
            scene.load(facade, false);
            ThreadUtil.sleep(pause);
            scene.repaintLabel();
            CustComponent.invokeLater(new SetInteractionScene(window));
            return;
        }
        facade.changeCamera(dir);
        scene.load(facade, false);
        for (int i = CustList.FIRST_INDEX; i <= side; i++) {
            scene.setDelta(i - side, true);
            ThreadUtil.sleep(pause);
            scene.repaintLabel();
        }
        CustComponent.invokeLater(new SetInteractionScene(window));
    }
}
