package code.minirts;

import code.gui.*;
import code.maths.*;
import code.maths.geo.*;
import code.minirts.rts.*;
import code.threads.*;
import code.util.*;


/**AnimationBalle permet de deplacer
un petit carre de 10 pixels noir en haut vers la droite*/
public final class AnimationUnitSoldier implements Runnable {

    private final PanelBattle battleground;

    private final WindowRts window;

    private final AbsCustCheckBox pause;
    private final AbstractAtomicBoolean paused;

    private final AbsButton stop;
    private final AbstractAtomicBoolean stopped;
    private final AbsButton animate;

    public AnimationUnitSoldier(AbsButton _animate, AbsCustCheckBox _pause, AbsButton _stop, WindowRts _window) {
        window = _window;
        animate = _animate;
        pause = _pause;
        stop = _stop;
        battleground = _window.getBattleground();
        stopped = _window.getStopped();
        paused = _window.getPaused();
    }

    public void selectOrDeselect(RatePoint _first, RatePoint _last) {
        battleground.setRectangle(_first, _last);
        battleground.setPaintSelection(true);
        repaintBattleground();
    }

    public void selectOrDeselect(Rate _x, Rate _y) {
        battleground.setRectangle(_x, _y);
        repaintBattleground();
    }

    public void addNewSoldier(int _x, int _y, long _next) {
        battleground.addNewSoldier(window, _x, _y,_next);
    }

    public void setNewLocation(int _x, int _y) {
        battleground.setNewLocation(_x, _y);
    }

    public void moveCamera(Rate _x, Rate _y) {
        battleground.moveCamera(window.getImageFactory(), _x, _y);
    }

    public void pause() {
        if (!paused.get()) {
            window.setEnabledPause(false);
        }
        paused.set(!paused.get());
    }

    public void stopGame() {
        stopped.set(true);
        window.cancel();
        afterStop();
    }

    public void reset() {
        stopped.set(false);
    }

    @Override
    public void run() {
        if (pause(100)){
            return;
        }
        loop();
        moving();
    }

    private void afterStop() {
        animate.setEnabled(true);
        pause.setEnabled(false);
        stop.setEnabled(false);
    }

    public boolean isStopped() {
        return stopped.get();
    }

    void loop() {
        Facade f_ = battleground.getFacade();
        f_.loop();
        battleground.selectOrDeselectMany(window.getImageFactory());
    }

    void moving() {
        Facade f_ = battleground.getFacade();
        AbsCustComponent parent_ = battleground.getContainerParent();
        Rate w_ = new Rate(parent_.getWidth());
        Rate h_ = new Rate(parent_.getHeight());
        for (EntryCust<Long, Soldier> u: f_.getVisibleSoldiers(w_, h_)) {
            Soldier s_ = u.getValue();
            UnitSoldier u_ = battleground.getSoldierLabel(u.getKey());
            PanelBattle.setSoldierLocation(u_, s_.getContent().getLocx(), s_.getContent().getLocy());
        }
        if (!window.isDragged()) {
            battleground.setPaintSelection(false);
        }
        repaintBattleground();
    }

    private void repaintBattleground() {
        Facade f_ = battleground.getFacade();
        AbsCustComponent parent_ = battleground.getContainerParent();
//        int w_ = parent_.getWidth();
//        int h_ = parent_.getHeight();
        RatePoint curTopLeft_ = f_.getTopLeftPoint();
//        battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        battleground.repaint(window.getImageFactory(), curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), parent_);
    }

    /**La methode pause est utilisee pour permettre de voir l'avancement a l'oeil nu*/
    private boolean pause(long _tempsMillis) {
        ThreadUtil.sleep(window.getThreadFactory(), _tempsMillis);
        window.setEnabledPause(true);
        if (paused.get()) {
            battleground.paintSelection(window.getImageFactory());
            return true;
        }
        return false;
    }
}
