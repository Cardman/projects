package minirts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import minirts.rts.CustPoint;
import minirts.rts.Facade;
import minirts.rts.Rect;
import minirts.rts.Soldier;
import minirts.rts.SoldierPattern;
import minirts.rts.UnitMapKey;
import code.util.ObjectMap;

public class PanelBattle extends JPanel {

    private ObjectMap<UnitMapKey,UnitSoldier> soldierLabels = new ObjectMap<UnitMapKey,UnitSoldier>();
    private final Facade facade;

    private boolean paintSelection;

    public PanelBattle(Facade _facade) {
        facade = _facade;
        setBackground(Color.WHITE);
        setLayout(null);
    }

    public void addNewSoldier(int _x, int _y) {
        facade.addNewSoldier(_x, _y);
        if (!facade.isAdded()) {
            return;
        }
        SoldierPattern p_ = facade.getSoldierPattern();
        Soldier s_ = facade.getLastSoldier();
        UnitSoldier soldierLabel_ = new UnitSoldier(s_);
        soldierLabel_.setSize(new Dimension(p_.getWidth(), p_.getHeight()));
        add(soldierLabel_);
        soldierLabel_.setLocation(_x, _y);
        soldierLabels.put(facade.getLastSoldierKey(),soldierLabel_);
        CustPoint curTopLeft_ = facade.getTopLeftPoint();
        Component parent_ = getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(curTopLeft_.getX(), curTopLeft_.getY(), w_, h_);
    }

    public UnitSoldier getSoldierLabel(UnitMapKey _key) {
        return soldierLabels.getVal(_key);
    }

    public static void setSoldierLocation(UnitSoldier _u, int _x, int _y) {
        _u.setLocation(_x, _y);
    }

//    public void selectOrDeselect(Point _first, Point _last) {
//        facade.selectOrDeselect(_first, _last);
//        Point curTopLeft_ = facade.getTopLeftPoint();
//        Component parent_ = getParent();
//        int w_ = parent_.getWidth();
//        int h_ = parent_.getHeight();
//        paintSelection = true;
////        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
//        repaint(curTopLeft_.x, curTopLeft_.y, w_, h_);
//    }
//
//    public void selectOrDeselect(int _x, int _y) {
//        facade.selectOrDeselect(_x, _y);
//        Point curTopLeft_ = facade.getTopLeftPoint();
//        Component parent_ = getParent();
//        int w_ = parent_.getWidth();
//        int h_ = parent_.getHeight();
//        paintSelection = false;
////        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
//        repaint(curTopLeft_.x, curTopLeft_.y, w_, h_);
//    }

    public void setRectangle(CustPoint _first, CustPoint _last) {
        facade.setRectangle(_first, _last);
    }

    public void setRectangle(int _x, int _y) {
        facade.setRectangle(_x, _y);
    }

    public void selectOrDeselectMany() {
        facade.selectOrDeselectMany();
        paintSelection = true;
        CustPoint curTopLeft_ = facade.getTopLeftPoint();
        Component parent_ = getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
//        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(curTopLeft_.getX(), curTopLeft_.getY(), w_, h_);
    }

    public void setNewLocation(int _x, int _y) {
        facade.setNewLocation(_x, _y);
    }

    public void moveCamera(Point _pt) {
        Component parent_ = getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        facade.moveCamera(_pt.x, _pt.y, w_, h_);
        CustPoint curTopLeft_ = facade.getTopLeftPoint();
        Point pt_ = new Point();
        pt_.x = -curTopLeft_.getX();
        pt_.y = -curTopLeft_.getY();
        setLocation(pt_);
//        setLocation(curTopLeft_);
        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(curTopLeft_.getX(), curTopLeft_.getY(), w_, h_);
    }
    public void setPaintSelection(boolean _paintSelection) {
        paintSelection = _paintSelection;
    }

    public boolean isPaintSelection() {
        return paintSelection;
    }

    public Facade getFacade() {
        return facade;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        super.paintComponent(_g);
        if (paintSelection) {
            Rect r_ = facade.getSelection();
            _g.setColor(Color.BLUE);
            _g.drawRect(r_.getLeft(), r_.getTop(), r_.getWidth(), r_.getHeight());
        }
    }
}
