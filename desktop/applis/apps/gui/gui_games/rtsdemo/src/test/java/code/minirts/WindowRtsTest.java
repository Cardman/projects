package code.minirts;

import code.gui.*;
import code.gui.events.*;
import code.gui.images.*;
import code.maths.*;
import code.maths.geo.*;
import code.minirts.events.*;
import code.minirts.rts.*;
import code.mock.*;
import code.threads.*;
import org.junit.Test;

public final class WindowRtsTest extends EquallableRtsUtil {
    @Test
    public void defTask() {
        assertEq(1,new DefRtsTaskEnabled().status(new ConcreteInteger(1)));
    }
    @Test
    public void moveCamera1() {
        WindowRts w_ = window();
        RatePoint tl_ = w_.getFacade().getTopLeftPoint();
        tryPress(w_.getDown());
        assertEq(tl_,w_.getFacade().getTopLeftPoint());
    }
    @Test
    public void moveCamera2() {
        WindowRts w_ = window();
        RatePoint tl_ = w_.getFacade().getTopLeftPoint();
        tryPress(w_.getUp());
        assertEq(tl_,w_.getFacade().getTopLeftPoint());
    }
    @Test
    public void moveCamera3() {
        WindowRts w_ = window();
        RatePoint tl_ = w_.getFacade().getTopLeftPoint();
        tryPress(w_.getLeft());
        assertEq(tl_,w_.getFacade().getTopLeftPoint());
    }
    @Test
    public void moveCamera4() {
        WindowRts w_ = window();
        RatePoint tl_ = w_.getFacade().getTopLeftPoint();
        tryPress(w_.getRight());
        assertEq(tl_,w_.getFacade().getTopLeftPoint());
    }
    @Test
    public void moveCamera5() {
        WindowRts w_ = window();
        Facade facade_ = w_.getFacade();
        RatePoint tl_ = facade_.getTopLeftPoint();
        tryClick(w_.getAnimate());
        tryPress(w_.getDown());
        assertEq(new RatePoint(tl_.getXcoords(), Rate.plus(tl_.getYcoords(),facade_.getData().getMultiple().getHeight())), facade_.getTopLeftPoint());
    }
    @Test
    public void moveCamera6() {
        WindowRts w_ = window();
        Facade facade_ = w_.getFacade();
        RatePoint tl_ = facade_.getTopLeftPoint();
        tryClick(w_.getAnimate());
        tryPress(w_.getUp());
        assertEq(new RatePoint(tl_.getXcoords(), Rate.minus(tl_.getYcoords(),facade_.getData().getMultiple().getHeight())), facade_.getTopLeftPoint());
    }
    @Test
    public void moveCamera7() {
        WindowRts w_ = window();
        Facade facade_ = w_.getFacade();
        RatePoint tl_ = facade_.getTopLeftPoint();
        tryClick(w_.getAnimate());
        tryPress(w_.getLeft());
        assertEq(new RatePoint(Rate.minus(tl_.getXcoords(),facade_.getData().getMultiple().getWidth()), tl_.getYcoords()), facade_.getTopLeftPoint());
    }
    @Test
    public void moveCamera8() {
        WindowRts w_ = window();
        Facade facade_ = w_.getFacade();
        RatePoint tl_ = facade_.getTopLeftPoint();
        tryClick(w_.getAnimate());
        tryPress(w_.getRight());
        assertEq(new RatePoint(Rate.plus(tl_.getXcoords(),facade_.getData().getMultiple().getWidth()), tl_.getYcoords()), facade_.getTopLeftPoint());
    }
    @Test
    public void addNewSoldier1() {
        WindowRts w_ = window();
        tryToggle(w_.getAddSoldier());
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        Facade facade_ = w_.getFacade();
        assertEq(0,facade_.getSoldierKeys().size());
    }
    @Test
    public void addNewSoldier2() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        tryToggle(w_.getAddSoldier());
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        Facade facade_ = w_.getFacade();
        assertEq(1,facade_.getSoldierKeys().size());
        assertEq(new Rate(110),facade_.getSoldier(0).getContent().getLocx());
        assertEq(new Rate(120),facade_.getSoldier(0).getContent().getLocy());
    }
    @Test
    public void addNewSoldier3() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        tryToggle(w_.getAddSoldier());
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        Facade facade_ = w_.getFacade();
        assertEq(1,facade_.getSoldierKeys().size());
        assertEq(new Rate(110),facade_.getSoldier(0).getContent().getLocx());
        assertEq(new Rate(120),facade_.getSoldier(0).getContent().getLocy());
    }
    @Test
    public void select1() {
        WindowRts w_ = window();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        Facade facade_ = w_.getFacade();
        assertEq(0,facade_.getSoldierKeys().size());
    }
    @Test
    public void select2() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseMotionListener motion_ = w_.getBattleground().getContent().getMouseMotionListeners().get(0);
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        motion_.mouseDragged(new CoreMouseLocation(120,125),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        assertEq(new RatePoint(new Rate(120),new Rate(130)), w_.getFirst());
        assertEq(new RatePoint(new Rate(120),new Rate(125)), w_.getLast());
        motion_.mouseDragged(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        Facade facade_ = w_.getFacade();
        assertEq(2,facade_.getSoldierKeys().size());
        assertTrue(facade_.getSoldier(0).getContent().isSelected());
        assertFalse(facade_.getSoldier(1).getContent().isSelected());
    }
    @Test
    public void select3() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        tryClick(w_.getStop());
        th_.run();
        AbsMouseMotionListener motion_ = w_.getBattleground().getContent().getMouseMotionListeners().get(0);
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        motion_.mouseDragged(new CoreMouseLocation(120,125),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        motion_.mouseDragged(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        Facade facade_ = w_.getFacade();
        assertEq(2,facade_.getSoldierKeys().size());
        assertFalse(facade_.getSoldier(0).getContent().isSelected());
        assertFalse(facade_.getSoldier(1).getContent().isSelected());
    }
    @Test
    public void move1() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(160,170),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        Facade facade_ = w_.getFacade();
        assertEq(new Rate(160),facade_.getSoldier(0).getContent().getDestx());
        assertEq(new Rate(170),facade_.getSoldier(0).getContent().getDesty());
        assertEq(new Rate(111),facade_.getSoldier(0).getContent().getLocx());
        assertEq(new Rate(121),facade_.getSoldier(0).getContent().getLocy());
    }
    @Test
    public void move2() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        tryClick(w_.getStop());
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(160,170),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        Facade facade_ = w_.getFacade();
        assertEq(new Rate(110),facade_.getSoldier(0).getContent().getDestx());
        assertEq(new Rate(120),facade_.getSoldier(0).getContent().getDesty());
    }
    @Test
    public void drag1() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseMotionListener motion_ = w_.getBattleground().getContent().getMouseMotionListeners().get(0);
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        motion_.mouseDragged(new CoreMouseLocation(120,125),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        motion_.mouseMoved(new CoreMouseLocation(120,125),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        assertFalse(w_.isDragged());
    }
    @Test
    public void pause1() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(160,170),null,new CoreMouseButtons(false,false,true,1));
        tryToggle(w_.getPause());
        th_.run();
        Facade facade_ = w_.getFacade();
        assertEq(new Rate(160),facade_.getSoldier(0).getContent().getDestx());
        assertEq(new Rate(170),facade_.getSoldier(0).getContent().getDesty());
        assertEq(new Rate(110),facade_.getSoldier(0).getContent().getLocx());
        assertEq(new Rate(120),facade_.getSoldier(0).getContent().getLocy());
    }
    @Test
    public void pause2() {
        WindowRts w_ = window();
        tryClick(w_.getAnimate());
        w_.getBattleground().getContainer().getParent().setSize(new MetaDimension(200,200));
        AnimationUnitSoldier th_ = w_.getThread();
        tryToggle(w_.getAddSoldier());
        th_.run();
        AbsMouseListenerPresRel rel_ = ((MockCustComponent) w_.getBattleground().getContent()).getMousePresRelListeners().get(0);
        rel_.mouseReleased(new CoreMouseLocation(110,120),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(350,370),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        tryToggle(w_.getAddSoldier());
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(120,130),null,new CoreMouseButtons(true,false,false,1));
        th_.run();
        rel_.mousePressed(new CoreMouseLocation(120,130),null,new CoreMouseButtons(false,false,true,1));
        th_.run();
        rel_.mouseReleased(new CoreMouseLocation(160,170),null,new CoreMouseButtons(false,false,true,1));
        tryToggle(w_.getPause());
        th_.run();
        tryToggle(w_.getPause());
        th_.run();
        Facade facade_ = w_.getFacade();
        assertEq(new Rate(160),facade_.getSoldier(0).getContent().getDestx());
        assertEq(new Rate(170),facade_.getSoldier(0).getContent().getDesty());
        assertEq(new Rate(111),facade_.getSoldier(0).getContent().getLocx());
        assertEq(new Rate(121),facade_.getSoldier(0).getContent().getLocy());
    }
    @Test
    public void quit() {
        MockProgramInfos pr_ = build();
        CreateMainWindowRts c_ = new CreateMainWindowRts(pr_, new LanguagesButtonsPair(null,null,null));
        c_.run();
        WindowRts w_ = c_.getWindowRts();
        w_.changeLanguage("");
//        w_.getCommonFrame().getFrames().getCounts().addEntry(w_.getApplicationName(),new ConcreteInteger());
        w_.quit();
        w_.setVisible(false);
        assertFalse(w_.getCommonFrame().isVisible());
        GuiBaseUtil.tryToReopen(w_.getApplicationName(),w_.getFrames());
    }
}
