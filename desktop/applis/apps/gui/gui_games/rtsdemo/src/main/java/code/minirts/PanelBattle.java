package code.minirts;

import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.TopLeftFrame;
import code.gui.events.AbsMouseMotionListener;
import code.gui.events.AbsMouseWheelListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.maths.geo.CustPoint;
import code.maths.geo.Rect;
import code.minirts.events.InteractClick;
import code.minirts.rts.*;
import code.util.EntryCust;




public class PanelBattle {

    private final UnitMapKeyMap<UnitSoldier> soldierLabels = new UnitMapKeyMap<UnitSoldier>();
    private final Facade facade;

    private boolean paintSelection;

    private final Selecting selecting;
    private final AbsPanel container;
    private final AbsPanel content;

    public PanelBattle(Facade _facade, AbsCompoFactory _compoFactory) {
        facade = _facade;
        container = _compoFactory.newAbsolute();
        content = _compoFactory.newAbsolute();
        content.setOpaque(true);
        content.setBackground(GuiConstants.WHITE);
        container.setOpaque(true);
        container.setBackground(GuiConstants.WHITE);
        selecting = new Selecting(_facade, _compoFactory);
        container.add(selecting.getPaintableLabel());
        container.add(content);
    }

    public void addMouseMotionListener(AbsMouseMotionListener _l) {
        content.addMouseMotionListener(_l);
    }

    public void addMouseWheelListener(AbsMouseWheelListener _l) {
        content.addMouseWheelListener(_l);
    }

    public void addNewSoldier(WindowRts _fact,int _x, int _y, long _next) {
        facade.addNewSoldier(_x, _y,_next);
        if (!facade.isAdded()) {
            return;
        }
        TopLeftFrame p_ = facade.getSoldierPattern();
        Soldier s_ = facade.getLastSoldier();
        UnitSoldier soldierLabel_ = new UnitSoldier(s_, _fact.getCompoFactory());
        soldierLabel_.setSize(new MetaDimension(p_.getWidth(), p_.getHeight()));
        content.add(soldierLabel_.getPaintableLabel());
        soldierLabel_.setLocation(_x, _y);
        soldierLabels.put(facade.getLastSoldierKey(),soldierLabel_);
        CustPoint curTopLeft_ = facade.getTopLeftPoint();
        AbsCustComponent parent_ = container.getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(_fact.getImageFactory(),curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), w_, h_);
    }

    public void repaint(AbstractImageFactory _fact,int _x, int _y, int _width, int _height) {
        Rect gl_ = RtsGame.newRect(_x,_y,_width,_height);
        for (EntryCust<UnitMapKey, UnitSoldier> e: soldierLabels.entryList()) {
            UnitSoldier u_ = e.getValue();
            int w_ = u_.getWidth();
            int h_ = u_.getHeight();
            Soldier soldier_ = u_.getSoldier();
            Rect loc_ = RtsGame.newRect(soldier_.getLocx(),soldier_.getLocy(),w_,h_);
            if (!gl_.intersects(loc_)) {
                continue;
            }
            AbstractImage img_ = _fact.newImageArgb(w_, h_);
//            CustGraphics gr_ = new CustGraphics(img_.getGraphics());
            img_.setFont(u_.getPaintableLabel());
            u_.paintComponent(img_);
            u_.setIcon(_fact,img_);
        }
        paintSelection(_fact);
    }

    public void paintSelection(AbstractImageFactory _fact) {
        int w_ = selecting.getWidth();
        int h_ = selecting.getHeight();
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
        if (paintSelection) {
            //            CustGraphics gr_ = new CustGraphics(img_.getGraphics());
            img_.setFont(selecting.getPaintableLabel());
            Rect r_ = facade.getSelection();
            img_.setColor(GuiConstants.BLUE);
            img_.drawRect((int)r_.getLeft().ll(),(int) r_.getTop().ll(),(int) r_.getWidth().ll(), (int)r_.getHeight().ll());
        } else {
            img_.setColor(GuiConstants.newColor(255,255,255,0));
            img_.fillRect(0, 0, w_, h_);
        }
        selecting.setIcon(_fact,img_);
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

    public void selectOrDeselectMany(AbstractImageFactory _fact) {
        facade.selectOrDeselectMany();
        CustPoint curTopLeft_ = facade.getTopLeftPoint();
        AbsCustComponent parent_ = container.getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
//        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(_fact,curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), w_, h_);
    }

    public void setNewLocation(int _x, int _y) {
        facade.setNewLocation(_x, _y);
    }

    public void moveCamera(CustPoint _p, AbstractImageFactory _fact, int _x, int _y) {
        AbsCustComponent parent_ = container.getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        facade.moveCamera(_p.getXcoords()+ _x, _p.getYcoords()+ _y, w_, h_);
        CustPoint curTopLeft_ = facade.getTopLeftPoint();
        container.setLocation(-curTopLeft_.getXcoords(),-curTopLeft_.getYcoords());
//        setLocation(curTopLeft_);
        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(_fact,curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), w_, h_);
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

    public void addMouseListener(InteractClick _i) {
        content.addMouseListener(_i);
    }

    public AbsPanel getContainer() {
        return container;
    }

    public void setSize(MetaDimension _dimension) {
        container.setSize(_dimension);
        content.setSize(_dimension);
    }

    public void setLocation(CustPoint _pt) {
        container.setLocation(-_pt.getXcoords(),-_pt.getYcoords());
    }
}
