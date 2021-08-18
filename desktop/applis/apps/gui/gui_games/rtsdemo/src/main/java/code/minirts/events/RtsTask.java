package code.minirts.events;

import code.gui.CustComponent;
import code.maths.geo.CustPoint;
import code.minirts.WindowRts;
import code.minirts.PanelBattle;
import code.minirts.rts.RtsDirection;
import code.minirts.rts.Facade;

import java.awt.*;

public class RtsTask implements Runnable {

    private final Facade facade;

    private RtsDirection dir;

    private final PanelBattle scene;

    private final WindowRts window;

    public RtsTask(PanelBattle _scene, WindowRts _window, Facade _facade) {
        scene = _scene;
        window = _window;
        facade = _facade;
    }

    @Override
    public void run() {
        if (window.isDragged()) {
            return;
        }
        CustComponent par_ = scene.getContainer().getParent();
        CustPoint loc_ = facade.getTopLeftPoint();
        Point rel_ = new Point();
        rel_.x = loc_.getXcoords();
        rel_.y = loc_.getYcoords();
//        rel_.x = -loc_.x;
//        rel_.y = -loc_.y;
//        rel_.x = loc_.x;
//        rel_.y = loc_.y;
        int w_ = par_.getWidth();
        int h_ = par_.getHeight();
        if (dir == RtsDirection.UP) {
            rel_.y--;
            window.moveCamera(rel_);
        } else if (dir == RtsDirection.DOWN) {
            rel_.y+=h_+1;
            window.moveCamera(rel_);
        } else if (dir == RtsDirection.LEFT) {
            rel_.x--;
            window.moveCamera(rel_);
        } else if (dir == RtsDirection.RIGHT) {
            rel_.x+=w_+1;
            window.moveCamera(rel_);
        }
    }

    public RtsDirection getDir() {
        return dir;
    }

    public void setDir(RtsDirection _dir) {
        dir = _dir;
    }
}
