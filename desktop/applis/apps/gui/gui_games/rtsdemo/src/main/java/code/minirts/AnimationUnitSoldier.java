package code.minirts;

import code.gui.CustComponent;
import code.maths.geo.CustPoint;
import code.minirts.rts.Facade;
import code.minirts.rts.Soldier;
import code.minirts.rts.UnitMapKey;
import code.threads.ThreadUtil;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;


/**AnimationBalle permet de deplacer
un petit carre de 10 pixels noir en haut vers la droite*/
public final class AnimationUnitSoldier implements Runnable {

    private PanelBattle battleground;

    private MainWindow window;

    private AtomicBoolean paused;

    private AtomicBoolean stop;

    public AnimationUnitSoldier(PanelBattle _conteneur,MainWindow _window) {
        window = _window;
        battleground = _window.getBattleground();
        stop = _window.getStopped();
        paused = _window.getPaused();
    }

    public void selectOrDeselect(CustPoint _first, CustPoint _last) {
        battleground.setRectangle(_first, _last);
        battleground.setPaintSelection(true);
        repaintBattleground();
    }

    public void selectOrDeselect(int _x, int _y) {
        battleground.setRectangle(_x, _y);
        repaintBattleground();
    }

    public void addNewSoldier(int _x, int _y, long _next) {
        battleground.addNewSoldier(_x, _y,_next);
    }

    public void setNewLocation(int _x, int _y) {
        battleground.setNewLocation(_x, _y);
    }

    public void moveCamera(Point _pt) {
        battleground.moveCamera(_pt);
    }

    public void pause() {
        if (!paused.get()) {
            window.setEnabledPause(false);
        }
        paused.set(!paused.get());
    }

    public void stopGame() {
        stop.set(true);
    }

    @Override
    public void run() {
        while (true) {
            if (stop.get()) {
                break;
            }
            pause(100);
            loop();
            moving();
        }
    }

    void loop() {
        Facade f_ = battleground.getFacade();
        f_.loop();
        battleground.selectOrDeselectMany();
    }

    void moving() {
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

    private void repaintBattleground() {
        Facade f_ = battleground.getFacade();
        CustComponent parent_ = battleground.getContainer().getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        CustPoint curTopLeft_ = f_.getTopLeftPoint();
//        battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        battleground.repaint(curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), w_, h_);
    }

    /**La methode pause est utilisee pour permettre de voir l'avancement a l'oeil nu*/
    private void pause(long _tempsMillis) {
        ThreadUtil.sleep(_tempsMillis);
        window.setEnabledPause(true);
        while (paused.get()) {
            battleground.paintSelection();
        }
    }
}
