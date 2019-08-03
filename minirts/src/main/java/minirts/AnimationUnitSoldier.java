package minirts;

import java.awt.Point;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import minirts.rts.CustPoint;
import minirts.rts.Facade;
import minirts.rts.Soldier;
import minirts.rts.UnitMapKey;
import code.gui.CustComponent;



/**AnimationBalle permet de deplacer
un petit carre de 10 pixels noir en haut vers la droite*/
public final class AnimationUnitSoldier extends Thread {

    private static final ReentrantLock MOVING_LOCK = new ReentrantLock();

    private static final ReentrantLock MAIN_LOCK = new ReentrantLock();

    private static PanelBattle battleground;

    private static MainWindow window;

    private static AtomicBoolean paused = new AtomicBoolean();

    private static boolean stop;

    public AnimationUnitSoldier(PanelBattle _conteneur) {
        battleground = _conteneur;
        stop = false;
    }

    public static void setWindow(MainWindow _window) {
        window = _window;
    }

    public static void selectOrDeselect(CustPoint _first, CustPoint _last) {
        battleground.setRectangle(_first, _last);
        repaintBattleground();
    }

    public static void selectOrDeselect(int _x, int _y) {
        battleground.setRectangle(_x, _y);
        repaintBattleground();
    }

    public static void addNewSoldier(int _x, int _y) {
        MAIN_LOCK.lock();
        battleground.addNewSoldier(_x, _y);
        MAIN_LOCK.unlock();
    }

    public static void setNewLocation(int _x, int _y) {
        MAIN_LOCK.lock();
        battleground.setNewLocation(_x, _y);
        MAIN_LOCK.unlock();
    }

    public static void moveCamera(Point _pt) {
        MOVING_LOCK.lock();
        battleground.moveCamera(_pt);
        MOVING_LOCK.unlock();
    }

    public static void pause() {
        if (!paused.get()) {
            window.setEnabledPause(false);
        }
        paused.set(!paused.get());
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
        }
    }

    static void loop() {
        MAIN_LOCK.lock();
        Facade f_ = battleground.getFacade();
        f_.loop();
        MAIN_LOCK.unlock();
        battleground.selectOrDeselectMany();
    }

    static void moving() {
        MOVING_LOCK.lock();
        Facade f_ = battleground.getFacade();
        CustComponent parent_ = battleground.getContainer().getParent();
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
        MOVING_LOCK.unlock();
    }

    private static void repaintBattleground() {
        Facade f_ = battleground.getFacade();
        CustComponent parent_ = battleground.getContainer().getParent();
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
        while (paused.get()) {
            battleground.paintSelection();
        }
    }
}
