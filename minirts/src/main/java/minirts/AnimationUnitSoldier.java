package minirts;

import java.awt.Component;
import java.awt.Point;

import minirts.rts.CustPoint;
import minirts.rts.Facade;
import minirts.rts.Soldier;
import minirts.rts.UnitMapKey;



/**AnimationBalle permet de deplacer
un petit carre de 10 pixels noir en haut vers la droite*/
public final class AnimationUnitSoldier extends Thread {

    private static final Object MOVING_LOCK = new Object();

//    private static final Object SELECT_LOCK = new Object();

    private static final Object MAIN_LOCK = new Object();

    private static PanelBattle battleground;

    private static MainWindow window;

    private static volatile boolean paused;

    private static boolean stop;

    public AnimationUnitSoldier(PanelBattle _conteneur) {
        battleground = _conteneur;
        stop = false;
    }

    public static void setWindow(MainWindow _window) {
        window = _window;
    }

    public static void selectOrDeselect(CustPoint _first, CustPoint _last) {
//        synchronized (SELECT_LOCK) {
        battleground.setRectangle(_first, _last);
//            battleground.selectOrDeselect(_first, _last);
//        }
        repaintBattleground();
    }

    public static void selectOrDeselect(int _x, int _y) {
//        synchronized (SELECT_LOCK) {
        battleground.setRectangle(_x, _y);
        repaintBattleground();
    }

    public static void addNewSoldier(int _x, int _y) {
        synchronized (MAIN_LOCK) {
            battleground.addNewSoldier(_x, _y);
        }
    }

    public static void setNewLocation(int _x, int _y) {
        synchronized (MAIN_LOCK) {
            battleground.setNewLocation(_x, _y);
        }
    }

    public static void moveCamera(Point _pt) {
        synchronized (MOVING_LOCK) {
            battleground.moveCamera(_pt);
        }
    }

    public static void pause() {
        if (!paused) {
            window.setEnabledPause(false);
        }
        paused = !paused;
    }

    public static void stopGame() {
        stop = true;
    }

    @Override
    public void run() {
        while (true) {
            if (stop) {
                break;
            }
            pause(100);
            loop();
            moving();
//            Facade f_ = battleground.getFacade();
//            Component parent_ = battleground.getParent();
//            int w_ = parent_.getWidth();
//            int h_ = parent_.getHeight();
//            Point curTopLeft_ = f_.getTopLeftPoint();
////            battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
//            battleground.repaint(curTopLeft_.x, curTopLeft_.y, w_, h_);
        }
    }

    static void loop() {
        synchronized (MAIN_LOCK) {
            Facade f_ = battleground.getFacade();
            f_.loop();
        }
//        synchronized (SELECT_LOCK) {
        battleground.selectOrDeselectMany();
//        }
//        synchronized (MOVING_LOCK) {
//            Component parent_ = battleground.getParent();
//            int w_ = parent_.getWidth();
//            int h_ = parent_.getHeight();
//            for (UnitMapKey u: f_.getVisibleSoldiers(w_, h_)) {
//                Soldier s_ = f_.getSoldier(u);
//                UnitSoldier u_ = battleground.getSoldierLabel(u);
//                battleground.setSoldierLocation(u_, s_.getX(), s_.getY());
//            }
//            if (!window.isDragged()) {
//                battleground.setPaintSelection(false);
//            }
//        }
//        int w_ = parent_.getWidth();
//        int h_ = parent_.getHeight();
//        Point curTopLeft_ = f_.getTopLeftPoint();
////        battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
//        battleground.repaint(curTopLeft_.x, curTopLeft_.y, w_, h_);
    }

    static void moving() {
        synchronized (MOVING_LOCK) {
            Facade f_ = battleground.getFacade();
            Component parent_ = battleground.getParent();
            int w_ = parent_.getWidth();
            int h_ = parent_.getHeight();
            for (UnitMapKey u: f_.getVisibleSoldiers(w_, h_)) {
                Soldier s_ = f_.getSoldier(u);
                UnitSoldier u_ = battleground.getSoldierLabel(u);
                PanelBattle.setSoldierLocation(u_, s_.getX(), s_.getY());
            }
            if (!window.isDragged()) {
                battleground.setPaintSelection(false);
            }
            repaintBattleground();
//            Point curTopLeft_ = f_.getTopLeftPoint();
//            battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
//            battleground.repaint(curTopLeft_.x, curTopLeft_.y, w_, h_);
        }
    }

    private static void repaintBattleground() {
        Facade f_ = battleground.getFacade();
        Component parent_ = battleground.getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        CustPoint curTopLeft_ = f_.getTopLeftPoint();
//        battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        battleground.repaint(curTopLeft_.getX(), curTopLeft_.getY(), w_, h_);
    }

    /**La methode pause est utilisee pour permettre de voir l'avancement a l'oeil nu*/
    static void pause(long _tempsMillis) {
        long dateMillis_ = System.currentTimeMillis();
        while (dateMillis_ + _tempsMillis > System.currentTimeMillis()) {
            continue;
        }
        window.setEnabledPause(true);
        while (paused) {
            continue;
        }
    }
}
