package minirts;

import java.awt.Point;
import java.util.concurrent.atomic.AtomicBoolean;

import minirts.rts.CustPoint;
import minirts.rts.Facade;
import minirts.rts.Soldier;
import minirts.rts.UnitMapKey;
import code.gui.CustComponent;



/**AnimationBalle permet de deplacer
un petit carre de 10 pixels noir en haut vers la droite*/
public final class AnimationUnitSoldier implements Runnable {

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
        battleground.setPaintSelection(true);
        repaintBattleground();
    }

    public static void selectOrDeselect(int _x, int _y) {
        battleground.setRectangle(_x, _y);
        repaintBattleground();
    }

    public static void addNewSoldier(int _x, int _y) {
        battleground.addNewSoldier(_x, _y);
    }

    public static void setNewLocation(int _x, int _y) {
        battleground.setNewLocation(_x, _y);
    }

    public static void moveCamera(Point _pt) {
        battleground.moveCamera(_pt);
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
        Facade f_ = battleground.getFacade();
        f_.loop();
        battleground.selectOrDeselectMany();
    }

    static void moving() {
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
