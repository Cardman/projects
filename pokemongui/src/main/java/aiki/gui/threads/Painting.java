package aiki.gui.threads;
import javax.swing.SwingUtilities;

import code.util.CustList;
import code.util.consts.Constants;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.walk.Scene;
import aiki.map.enums.Direction;

/**This class thread is independant from EDT,
Thread safe class*/
public final class Painting extends Thread {

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
        window.setPaintingScene(true);
        facade.move(dir);
        window.setSavedGame(false);
        if (!window.isAnimateMovingHero()) {
            scene.setAnimated(false);
            scene.keepTiles();
            facade.changeCamera();
            scene.load(facade, false);
            Constants.sleep(pause);
            scene.repaint();
            if (facade.isChangeToFightScene()) {
//                facade.changeCamera(dir);
//                scene.load(facade, false, true);
//                facade.changeCamera();
//                scene.load(facade, false, false);
//                Constants.sleep(pause);
//                scene.repaint();
                SwingUtilities.invokeLater(new SetFightPanel(window));
                return;
            }
//            if (facade.getGame().getNbSteps() == 0) {
////                scene.setDir(facade, false);
//                Constants.sleep(pause);
//                scene.repaint();
//                SwingUtilities.invokeLater(new SetInteractionScene(window));
//                return;
//            }
//            facade.changeCamera(dir);
//            scene.load(facade, false, true);
//            facade.changeCamera();
//            scene.load(facade, false, false);
//            Constants.sleep(pause);
//            scene.repaint();
            SwingUtilities.invokeLater(new SetInteractionScene(window));
            return;
        }
        scene.setAnimated(true);
        if (facade.isChangeToFightScene()) {
            if (facade.getGame().isPlaceChanged()) {
                scene.keepTiles();
                facade.changeCamera();
                scene.load(facade, false);
                Constants.sleep(pause * 5);
                scene.repaint();
            } else {
                facade.changeCamera(dir);
                scene.load(facade, false);
                for (int i = CustList.FIRST_INDEX; i <= side; i++) {
                    scene.setDelta(i - side, true);
                    Constants.sleep(pause);
                    scene.repaint();
                }
//                facade.changeCamera();
//                scene.load(facade, false, false);
//                scene.setDelta(0, false);
//                scene.repaint();
            }
            SwingUtilities.invokeLater(new SetFightPanel(window));
            return;
        }
        if (facade.getGame().getNbSteps() == 0) {
            facade.changeCamera();
            scene.load(facade, false);
            scene.setDelta(0, false);
//            scene.setDir(facade, true);
            Constants.sleep(pause);
            scene.repaint();
            SwingUtilities.invokeLater(new SetInteractionScene(window));
            return;
        }
        if (facade.getGame().isPlaceChanged()) {
//            facade.changeCamera(dir);
//            scene.load(facade, false, false);
//            for (int i = CustList.FIRST_INDEX; i < side; i++) {
//                scene.setDelta(i - side, true);
//                Constants.sleep(pause);
//                scene.repaint();
//            }
//            facade.changeCamera();
//            scene.load(facade, false, false);
//            scene.setDelta(0, false);
//            Constants.sleep(10);
//            scene.repaint();
            scene.keepTiles();
            facade.changeCamera();
            scene.load(facade, false);
            Constants.sleep(pause);
            scene.repaint();
            SwingUtilities.invokeLater(new SetInteractionScene(window));
            return;
        }
        facade.changeCamera(dir);
//        scene.setNb();
        scene.load(facade, false);
        for (int i = CustList.FIRST_INDEX; i <= side; i++) {
            scene.setDelta(i - side, true);
            Constants.sleep(pause);
            scene.repaint();
        }
//        facade.changeCamera();
//        scene.load(facade, false, false);
//        scene.setDelta(0, false);
//        scene.setDelta(0, false);
//        scene.repaint();
//        scene.resetExport();
        //scene.complete(facade);//added
        SwingUtilities.invokeLater(new SetInteractionScene(window));
//        if (facade.isChangeToFightScene()) {
//            facade.changeCamera();
//            scene.load(facade, false);
//            scene.repaint();
//            int pause_ = 20;
//            //int side_ = facade.getMap().getSideLength();
//            Constants.sleep(pause_ * side_ / 2);
//            SwingUtilities.invokeLater(new SetFightPanel(window));
//            return;
//        }
        //int side_ = facade.getMap().getSideLength();
//        if (facade.getGame().isPlaceChanged()) {
//            facade.changeCamera();
//            scene.load(facade, false);
//            Constants.sleep(pause_ * side_);
//            scene.setDelta(0);
//            scene.setDir(facade);
//            scene.repaint();
//        } else if (facade.getGame().getNbSteps() == 0) {
//            //facade.changeCamera(dir);
//            scene.load(facade, false);
//            Constants.sleep(pause_ * side_ / 2);
//            scene.setDelta(0);
//            scene.setDir(facade);
//            scene.repaint();
//        } else {
//            facade.changeCamera(dir);
//            scene.load(facade, false);
//            Constants.sleep(pause_ * side_ / 2);
//            scene.setDelta(0);
//            scene.repaint();
//        }
//        SwingUtilities.invokeLater(new SetInteractionScene(window));
    }
}
