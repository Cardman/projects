package code.minirts;

import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsMouseMotionListener;
import code.gui.events.AbsMouseWheelListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.maths.*;
import code.maths.geo.*;
import code.minirts.events.InteractClick;
import code.minirts.rts.*;
import code.util.EntryCust;
import code.util.LongMap;


public class PanelBattle {

    private final LongMap<UnitSoldier> soldierLabels = new LongMap<UnitSoldier>();
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
        Soldier soldier_ = facade.addNewSoldier(new Rate(_x), new Rate(_y), _next);
        if (soldier_ == null) {
            return;
        }
        Rect p_ = facade.getSoldierPattern();
        UnitSoldier soldierLabel_ = new UnitSoldier(soldier_, _fact.getCompoFactory());
        soldierLabel_.setSize(new MetaDimension((int)p_.getWidth().ll(), (int)p_.getHeight().ll()));
        content.add(soldierLabel_.getPaintableLabel());
        soldierLabel_.setLocation(_x, _y);
        soldierLabels.put(_next,soldierLabel_);
        RatePoint curTopLeft_ = facade.getTopLeftPoint();
        AbsCustComponent parent_ = container.getParent();
//        int w_ = parent_.getWidth();
//        int h_ = parent_.getHeight();
        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(_fact.getImageFactory(),curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), parent_);
    }

    public void repaint(AbstractImageFactory _fact,Rate _x, Rate _y, AbsCustComponent _parent) {
        Rect gl_ = RtsGame.newRect(_x,_y,new Rate(_parent.getWidth()),new Rate(_parent.getHeight()));
        for (EntryCust<Long, UnitSoldier> e: soldierLabels.entryList()) {
            UnitSoldier u_ = e.getValue();
            int w_ = u_.getWidth();
            Rate wr_ = new Rate(w_);
            int h_ = u_.getHeight();
            Rate hr_ = new Rate(h_);
            Soldier soldier_ = u_.getSoldier();
            Rect loc_ = RtsGame.newRect(soldier_.getContent().getLocx(),soldier_.getContent().getLocy(),wr_,hr_);
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

    public UnitSoldier getSoldierLabel(long _key) {
        return soldierLabels.getVal(_key);
    }

    public static void setSoldierLocation(UnitSoldier _u, Rate _x, Rate _y) {
        _u.setLocation((int)_x.ll(), (int)_y.ll());
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

    public void setRectangle(RatePoint _first, RatePoint _last) {
        facade.setRectangle(_first, _last);
    }

    public void setRectangle(Rate _x, Rate _y) {
        facade.setRectangle(_x, _y);
    }

    public void selectOrDeselectMany(AbstractImageFactory _fact) {
        facade.selectOrDeselectMany();
        RatePoint curTopLeft_ = facade.getTopLeftPoint();
        AbsCustComponent parent_ = container.getParent();
//        int w_ = parent_.getWidth();
//        int h_ = parent_.getHeight();
//        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(_fact,curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), parent_);
    }

    public void setNewLocation(int _x, int _y) {
        facade.setNewLocation(new Rate(_x), new Rate(_y));
    }

    public void moveCamera(AbstractImageFactory _fact, Rate _x, Rate _y) {
        AbsCustComponent parent_ = container.getParent();
        int w_ = parent_.getWidth();
        int h_ = parent_.getHeight();
        facade.moveCamera(_x, _y, new Rate(w_), new Rate(h_));
        RatePoint curTopLeft_ = facade.getTopLeftPoint();
        setLocation(curTopLeft_);
//        container.setLocation(-curTopLeft_.getXcoords(),-curTopLeft_.getYcoords());
//        setLocation(curTopLeft_);
        paintSelection = false;
//        repaint(-curTopLeft_.x, -curTopLeft_.y, w_, h_);
        repaint(_fact,curTopLeft_.getXcoords(), curTopLeft_.getYcoords(), parent_);
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

    public void setLocation(RatePoint _pt) {
        container.setLocation((int)_pt.getXcoords().opposNb().ll(),(int)_pt.getYcoords().opposNb().ll());
    }
}
