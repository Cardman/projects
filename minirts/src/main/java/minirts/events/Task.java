package minirts.events;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import minirts.MainWindow;
import minirts.PanelBattle;
import minirts.rts.CustPoint;
import minirts.rts.Direction;
import minirts.rts.Facade;
import code.gui.CustComponent;

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
        CustComponent par_ = scene.getParent();
        CustPoint loc_ = facade.getTopLeftPoint();
        Point rel_ = new Point();
        rel_.x = loc_.getX();
        rel_.y = loc_.getY();
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
