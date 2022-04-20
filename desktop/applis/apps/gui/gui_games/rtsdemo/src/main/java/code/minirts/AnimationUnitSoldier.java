package code.minirts;

import code.gui.*;
import code.maths.geo.CustPoint;
import code.minirts.rts.Facade;
import code.minirts.rts.Soldier;
import code.minirts.rts.UnitMapKey;
import code.threads.AbstractAtomicBoolean;
import code.threads.ThreadUtil;


/**AnimationBalle permet de deplacer
un petit carre de 10 pixels noir en haut vers la droite*/
public final class AnimationUnitSoldier implements Runnable {

    private PanelBattle battleground;

    private WindowRts window;

    private AbsCustCheckBox pause;
    private AbstractAtomicBoolean paused;

    private AbsPlainButton stop;
    private AbstractAtomicBoolean stopped;
    private AbsPlainButton animate;

    public AnimationUnitSoldier(AbsPlainButton _animate, AbsCustCheckBox _pause, AbsPlainButton _stop, PanelBattle _conteneur, WindowRts _window) {
        window = _window;
        animate = _animate;
        pause = _pause;
        stop = _stop;
        battleground = _window.getBattleground();
        stopped = _window.getStopped();
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
        battleground.addNewSoldier(window, _x, _y,_next);
    }

    public void setNewLocation(int _x, int _y) {
        battleground.setNewLocation(_x, _y);
    }

    public void moveCamera(CustPoint _p, int _x, int _y) {
        battleground.moveCamera(_p,window.getImageFactory(), _x, _y);
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
        AbsCustComponent parent_ = battleground.getContainer().getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        for (UnitMapKey u: f_.getVisibleSoldiers(w_, h_)) {
            Soldier s_ = f_.getSoldier(u);
            UnitSoldier u_ = battleground.getSoldierLabel(u);
            PanelBattle.setSoldierLocation(u_, s_.getLocx(), s_.getLocy());
        }
        if (!window.isDragged()) {
            battleground.setPaintSelection(false);
        }
        repaintBattleground();
    }

    private void repaintBattleground() {
        Facade f_ = battleground.getFacade();
        AbsCustComponent parent_ = battleground.getContainer().getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        CustPoint curTopLeft_ = f_.getTopLeftPoint();
//        battleground.repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        battleground.repaint(window.getImageFactory(), curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), w_, h_);
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
