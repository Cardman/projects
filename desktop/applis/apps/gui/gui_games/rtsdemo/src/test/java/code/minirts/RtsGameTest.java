package code.minirts;

import code.maths.*;
import code.maths.geo.RatePoint;
import code.maths.geo.Rect;
import code.minirts.rts.*;
import code.util.CustList;
import code.util.EntryCust;
import org.junit.Test;

public final class RtsGameTest extends EquallableRtsUtil {
    @Test
    public void addNewSoldier1() {
        Facade r_ = new Facade();
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        assertTrue(r_.isAdded());
    }
    @Test
    public void addNewSoldier2() {
        Facade r_ = new Facade();
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(2),new Rate(5),1);
        assertFalse(r_.isAdded());
    }
    @Test
    public void addNewSoldier3() {
        Facade r_ = new Facade();
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(42),new Rate(45),1);
        assertTrue(r_.isAdded());
    }
    @Test
    public void getVisibleSoldiers1() {
        Facade r_ = new Facade();
        r_.setxTopLeftScreen(new Rate(40));
        r_.setyTopLeftScreen(new Rate(40));
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(42),new Rate(45),1);
        r_.addNewSoldier(new Rate(242),new Rate(245),2);
        CustList<EntryCust<Long, Soldier>> ls_ = r_.getVisibleSoldiers(new Rate(100), new Rate(100));
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0).getKey());
        assertEq(new Rate(42),ls_.get(0).getValue().getContent().getLocx());
        assertEq(new Rate(45),ls_.get(0).getValue().getContent().getLocy());
    }
    @Test
    public void getVisibleSoldiers2() {
        Facade r_ = new Facade();
        r_.setxTopLeftScreen(new Rate(40));
        r_.setyTopLeftScreen(new Rate(40));
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(42),new Rate(45),1);
        r_.addNewSoldier(new Rate(242),new Rate(245),2);
        r_.moveCamera(new Rate(20),Rate.zero(),new Rate(500),new Rate(500));
        r_.moveCamera(Rate.zero(),new Rate(20),new Rate(500),new Rate(500));
        CustList<EntryCust<Long, Soldier>> ls_ = r_.getVisibleSoldiers(new Rate(100), new Rate(100));
        assertEq(1,ls_.size());
        assertEq(2,ls_.get(0).getKey());
        assertEq(new Rate(242),ls_.get(0).getValue().getContent().getLocx());
        assertEq(new Rate(245),ls_.get(0).getValue().getContent().getLocy());
    }
    @Test
    public void select() {
        Facade r_ = new Facade();
        r_.setxTopLeftScreen(new Rate(40));
        r_.setyTopLeftScreen(new Rate(40));
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(42),new Rate(45),1);
        r_.addNewSoldier(new Rate(142),new Rate(145),2);
        r_.setRectangle(new RatePoint(new Rate(40),new Rate(40)),new RatePoint(new Rate(150),new Rate(150)));
        Rect sel_ = r_.getSelection();
        assertEq(new RatePoint(new Rate(40),new Rate(40)),new RatePoint(sel_.getLeft(),sel_.getTop()));
        assertEq(new RatePoint(new Rate(150),new Rate(40)),new RatePoint(sel_.getRight(),sel_.getTop()));
        assertEq(new RatePoint(new Rate(40),new Rate(150)),new RatePoint(sel_.getLeft(),sel_.getBottom()));
        assertEq(new RatePoint(new Rate(150),new Rate(150)),new RatePoint(sel_.getRight(),sel_.getBottom()));
        r_.selectOrDeselectMany();
        CustList<Long> keys_ = r_.getSoldierKeys();
        assertFalse(r_.getSoldier(keys_.get(0)).getContent().isSelected());
        assertTrue(r_.getSoldier(keys_.get(1)).getContent().isSelected());
        assertTrue(r_.getSoldier(keys_.get(2)).getContent().isSelected());
    }
    @Test
    public void move1() {
        Facade r_ = new Facade();
        r_.setxTopLeftScreen(new Rate(40));
        r_.setyTopLeftScreen(new Rate(40));
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(42),new Rate(45),1);
        r_.addNewSoldier(new Rate(142),new Rate(145),2);
        r_.setRectangle(new RatePoint(new Rate(40),new Rate(40)),new RatePoint(new Rate(50),new Rate(50)));
        r_.selectOrDeselectMany();
        CustList<Long> keys_ = r_.getSoldierKeys();
        assertFalse(r_.getSoldier(keys_.get(0)).getContent().isSelected());
        assertTrue(r_.getSoldier(keys_.get(1)).getContent().isSelected());
        assertFalse(r_.getSoldier(keys_.get(2)).getContent().isSelected());
        r_.setNewLocation(new Rate(82),new Rate(85));
        r_.loop();
        assertEq(new Rate(2),r_.getSoldier(keys_.get(0)).getContent().getLocx());
        assertEq(new Rate(5),r_.getSoldier(keys_.get(0)).getContent().getLocy());
        assertEq(new Rate(2),r_.getSoldier(keys_.get(0)).getContent().getDestx());
        assertEq(new Rate(5),r_.getSoldier(keys_.get(0)).getContent().getDesty());
        assertEq(new Rate(43),r_.getSoldier(keys_.get(1)).getContent().getLocx());
        assertEq(new Rate(46),r_.getSoldier(keys_.get(1)).getContent().getLocy());
        assertEq(new Rate(82),r_.getSoldier(keys_.get(1)).getContent().getDestx());
        assertEq(new Rate(85),r_.getSoldier(keys_.get(1)).getContent().getDesty());
        assertEq(new Rate(142),r_.getSoldier(keys_.get(2)).getContent().getLocx());
        assertEq(new Rate(145),r_.getSoldier(keys_.get(2)).getContent().getLocy());
        assertEq(new Rate(142),r_.getSoldier(keys_.get(2)).getContent().getDestx());
        assertEq(new Rate(145),r_.getSoldier(keys_.get(2)).getContent().getDesty());
    }
    @Test
    public void move2() {
        Facade r_ = new Facade();
        r_.setxTopLeftScreen(new Rate(40));
        r_.setyTopLeftScreen(new Rate(40));
        r_.addNewSoldier(new Rate(2),new Rate(5),0);
        r_.addNewSoldier(new Rate(42),new Rate(45),1);
        r_.addNewSoldier(new Rate(74),new Rate(77),2);
        r_.setRectangle(new RatePoint(new Rate(40),new Rate(40)),new RatePoint(new Rate(43),new Rate(46)));
        r_.selectOrDeselectMany();
        CustList<Long> keys_ = r_.getSoldierKeys();
        assertFalse(r_.getSoldier(keys_.get(0)).getContent().isSelected());
        assertTrue(r_.getSoldier(keys_.get(1)).getContent().isSelected());
        assertFalse(r_.getSoldier(keys_.get(2)).getContent().isSelected());
        r_.setNewLocation(new Rate(75),new Rate(78));
        r_.loop();
        assertEq(new Rate(2),r_.getSoldier(keys_.get(0)).getContent().getLocx());
        assertEq(new Rate(5),r_.getSoldier(keys_.get(0)).getContent().getLocy());
        assertEq(new Rate(2),r_.getSoldier(keys_.get(0)).getContent().getDestx());
        assertEq(new Rate(5),r_.getSoldier(keys_.get(0)).getContent().getDesty());
        assertEq(new Rate(42),r_.getSoldier(keys_.get(1)).getContent().getLocx());
        assertEq(new Rate(45),r_.getSoldier(keys_.get(1)).getContent().getLocy());
        assertEq(new Rate(75),r_.getSoldier(keys_.get(1)).getContent().getDestx());
        assertEq(new Rate(78),r_.getSoldier(keys_.get(1)).getContent().getDesty());
        assertEq(new Rate(74),r_.getSoldier(keys_.get(2)).getContent().getLocx());
        assertEq(new Rate(77),r_.getSoldier(keys_.get(2)).getContent().getLocy());
        assertEq(new Rate(74),r_.getSoldier(keys_.get(2)).getContent().getDestx());
        assertEq(new Rate(77),r_.getSoldier(keys_.get(2)).getContent().getDesty());
    }
}
