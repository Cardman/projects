package code.minirts.events;

import code.gui.CustComponent;
import code.maths.geo.CustPoint;
import code.minirts.MainWindow;
import code.minirts.PanelBattle;
import code.minirts.rts.Direction;
import code.minirts.rts.Facade;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task implements ActionListener {

    private Facade facade;

    private Direction dir;

    private PanelBattle scene;

    private MainWindow window;

    public Task(PanelBattle _scene, MainWindow _window, Facade _facade) {
        scene = _scene;
        window = _window;
        facade = _facade;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
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
        if (dir == Direction.UP) {
            rel_.y--;
            window.moveCamera(rel_);
        } else if (dir == Direction.DOWN) {
            rel_.y+=h_+1;
            window.moveCamera(rel_);
        } else if (dir == Direction.LEFT) {
            rel_.x--;
            window.moveCamera(rel_);
        } else if (dir == Direction.RIGHT) {
            rel_.x+=w_+1;
            window.moveCamera(rel_);
        }
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction _dir) {
        dir = _dir;
    }
}
