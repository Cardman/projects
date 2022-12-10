package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.walk.Scene;
import aiki.map.enums.Direction;
import code.gui.GuiBaseUtil;
import code.threads.ThreadUtil;
import code.util.core.IndexConstants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class Painting implements Runnable {

    private Scene scene;

    private FacadeGame facade;

    private Direction dir;

    private WindowAiki window;

    private int side;

    private int pause;

    /**This class thread is independant from EDT*/
    public Painting(Scene _scene, FacadeGame _facade, Direction _dir, WindowAiki _window) {
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
            scene.load(window.getImageFactory(),facade, false);
            ThreadUtil.sleep(window.getThreadFactory(),pause);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            if (facade.isChangeToFightScene()) {
                GuiBaseUtil.invokeLater(new SetFightPanel(window), window.getFrames());
                return;
            }
            GuiBaseUtil.invokeLater(new SetInteractionScene(window), window.getFrames());
            return;
        }
        scene.setAnimated(true);
        if (facade.isChangeToFightScene()) {
            if (facade.getGame().isPlaceChanged()) {
                scene.keepTiles();
                facade.changeCamera();
                scene.load(window.getImageFactory(),facade, false);
                ThreadUtil.sleep(window.getThreadFactory(),pause * 5L);
                AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            } else {
                facade.changeCamera(dir);
                scene.load(window.getImageFactory(),facade, false);
                for (int i = IndexConstants.FIRST_INDEX; i <= side; i++) {
                    scene.setDelta(i - side, true);
                    ThreadUtil.sleep(window.getThreadFactory(),pause);
                    AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
                }
            }
            GuiBaseUtil.invokeLater(new SetFightPanel(window), window.getFrames());
            return;
        }
        if (facade.getGame().getNbSteps() == 0) {
            facade.changeCamera();
            scene.load(window.getImageFactory(),facade, false);
            scene.setDelta(0, false);
            ThreadUtil.sleep(window.getThreadFactory(),pause);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            GuiBaseUtil.invokeLater(new SetInteractionScene(window), window.getFrames());
            return;
        }
        if (facade.getGame().isPlaceChanged()) {
            scene.keepTiles();
            facade.changeCamera();
            scene.load(window.getImageFactory(),facade, false);
            ThreadUtil.sleep(window.getThreadFactory(),pause);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
            GuiBaseUtil.invokeLater(new SetInteractionScene(window), window.getFrames());
            return;
        }
        facade.changeCamera(dir);
        scene.load(window.getImageFactory(),facade, false);
        for (int i = IndexConstants.FIRST_INDEX; i <= side; i++) {
            scene.setDelta(i - side, true);
            ThreadUtil.sleep(window.getThreadFactory(),pause);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
        }
        GuiBaseUtil.invokeLater(new SetInteractionScene(window), window.getFrames());
    }
}
