package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbsPlainLabel;
import code.gui.AbsTreeGui;
import code.gui.AbstractMutableTreeNode;
import org.junit.Test;

public final class MockTreeGuiTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockMutableTreeNode t_ = new MockMutableTreeNode("");
        assertEq(-1,t_.getIndex());
        assertNull(t_.getParent());
        assertNull(t_.getChildAt(0));
    }
    @Test
    public void t2() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        assertFalse(t1_.add(t2_));
        assertEq(0,t2_.getIndex());
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t3() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_,t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t4() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t3_));
        assertEq(0,t1_.insert(t2_,0));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_,t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t5() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertEq(1,t1_.insert(t3_,1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_,t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t6() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertEq(-1,t1_.insert(t3_,2));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t7() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t2_,t1_.remove(0));
        assertEq(-1,t2_.getIndex());
        assertNull(t2_.getParent());
        assertSame(t3_,t1_.getChildAt(0));
        assertEq(-1,t1_.getAntiIndex(t2_));
        assertEq(0,t1_.getAntiIndex(t3_));
        assertNull(t3_.getNextSibling());
        assertNull(t3_.getPreviousSibling());
    }
    @Test
    public void t8() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t3_,t1_.remove(1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t9() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(0,t1_.remove(t2_));
        assertEq(-1,t2_.getIndex());
        assertNull(t2_.getParent());
        assertSame(t3_,t1_.getChildAt(0));
        assertEq(-1,t1_.getAntiIndex(t2_));
        assertEq(0,t1_.getAntiIndex(t3_));
        assertNull(t3_.getNextSibling());
        assertNull(t3_.getPreviousSibling());
    }
    @Test
    public void t10() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(1,t1_.remove(t3_));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t11() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertEq(-1,t1_.insert(t3_,-1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t12() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertNull(t1_.remove(2));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_,t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t13() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertNull(t1_.remove(-1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_,t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t14() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        MockMutableTreeNode t4_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(-1,t1_.remove(t4_));
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_,t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t15() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t1_,t2_.removeFromParent());
        assertEq(-1,t2_.getIndex());
        assertNull(t2_.getParent());
        assertSame(t3_,t1_.getChildAt(0));
        assertEq(-1,t1_.getAntiIndex(t2_));
        assertEq(0,t1_.getAntiIndex(t3_));
        assertNull(t3_.getNextSibling());
        assertNull(t3_.getPreviousSibling());
    }
    @Test
    public void t16() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t1_,t3_.removeFromParent());
        assertEq(0,t2_.getIndex());
        assertSame(t1_,t2_.getParent());
        assertSame(t2_,t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t17() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode t2_ = new MockMutableTreeNode("2");
        MockMutableTreeNode t3_ = new MockMutableTreeNode("3");
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(2,t1_.removeAllChildren());
        assertNull(t1_.getChildAt(0));
        assertNull(t2_.getParent());
        assertNull(t3_.getParent());
        assertNull(t2_.getPreviousSibling());
        assertNull(t3_.getPreviousSibling());
        assertNull(t2_.getNextSibling());
        assertNull(t3_.getNextSibling());
    }
    @Test
    public void t18() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        AbstractMutableTreeNode c_ = t1_.add("0");
        assertEq("0", c_.getUserObject());
        assertEq(1,t1_.getChildCount());
        assertSame(c_, t1_.getChildAt(0));
        assertSame(t1_, t1_.original());
        t1_.setAccessible(true);
        assertTrue(t1_.isAccessible());
    }
    @Test
    public void t19() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        r_.add("1").add("3");
        r_.add("2").add("4");
        MockTreeGui t_ = new MockTreeGui(r_);
        AbstractMutableTreeNode c_ = r_.getChildAt(0).getChildAt(0);
        t_.select(c_);
        assertSame(c_,t_.selectEvt());
    }
    @Test
    public void t20() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        r_.add("1").add("3");
        r_.add("2").add("4");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.select(null);
        assertNull(t_.selectEvt());
    }
    @Test
    public void t21() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNode f_ = r_.add("1");
        f_.add("3");
        f_.add("5");
        AbstractMutableTreeNode s_ = r_.add("2");
        s_.add("4");
        s_.add("6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.reload(r_);
        assertTrue(r_.isAccessible());
        assertTrue(((MockMutableTreeNode)f_).isAccessible());
        assertTrue(((MockMutableTreeNode)s_).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)f_).getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)f_).getChildAt(1)).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)s_).getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)s_).getChildAt(1)).isAccessible());
    }
    @Test
    public void t22() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.reload(r_);
        assertTrue(r_.isAccessible());
    }
    @Test
    public void t23() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNode f_ = r_.add("1");
        f_.add("3");
        f_.add("5");
        AbstractMutableTreeNode s_ = r_.add("2");
        s_.add("4");
        s_.add("6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.select(r_);
        assertSame(r_,t_.getSelected());
        t_.reloadRoot();
        assertTrue(r_.isAccessible());
        assertTrue(((MockMutableTreeNode)f_).isAccessible());
        assertTrue(((MockMutableTreeNode)s_).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)f_).getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)f_).getChildAt(1)).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)s_).getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode)((MockMutableTreeNode)s_).getChildAt(1)).isAccessible());
    }
    @Test
    public void t26() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNode f_ = r_.add("1");
        f_.add("3");
        f_.add("5");
        AbstractMutableTreeNode s_ = r_.add("2");
        s_.add("4");
        s_.add("6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.setRootVisible(true);
        assertTrue(t_.isRootVisible());
    }
    @Test
    public void t27() {
        MockCommonFrameTreeSample mock_ = new MockCommonFrameTreeSample(init());
        MockTreeGui tree_ = (MockTreeGui) mock_.getContentPane().getComponent(0);
        tree_.getTreeSelectionListeners().first().valueChanged(tree_.getSelected().getChildAt(0));
        AbsPlainLabel lab_ = (AbsPlainLabel)  mock_.getContentPane().getComponent(1);
        assertEq("0/1",lab_.getText());
        assertNotNull(tree_.getTree());
    }
    @Test
    public void t28() {
        MockCommonFrameTreeSample mock_ = new MockCommonFrameTreeSample(init());
        MockTreeGui tree_ = (MockTreeGui) mock_.getContentPane().getComponent(0);
        tree_.getTreeSelectionListeners().first().valueChanged(tree_.getSelected().getChildAt(0));
        tree_.removeTreeSelectionListener(tree_.getTreeSelectionListeners().first());
        assertEq(0,tree_.getTreeSelectionListeners().size());
    }
    @Test
    public void t29() {
        MockCommonFrameTreeSample mock_ = new MockCommonFrameTreeSample(init());
        MockTreeGui tree_ = (MockTreeGui) mock_.getContentPane().getComponent(0);
        AbstractMutableTreeNode root_ = tree_.getSelected();
        tree_.select(root_.getChildAt(0));
        tree_.getTreeSelectionListeners().first().valueChanged(root_.getChildAt(0));
        tree_.removeFromParent();
        assertNull(root_.getChildAt(0));
    }
    @Test
    public void t30() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNode f_ = r_.add("1");
        f_.add("3");
        f_.add("5");
        AbstractMutableTreeNode s_ = r_.add("2");
        s_.add("4");
        s_.add("6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.removeAllChildren();
        assertNull(r_.getChildAt(0));
    }
    @Test
    public void t31() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.add("1");
        t_.add("2");
        t_.removeAllChildren();
        assertNull(r_.getChildAt(0));
    }
}
