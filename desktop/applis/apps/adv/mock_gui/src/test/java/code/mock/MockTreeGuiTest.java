package code.mock;

import code.gui.*;
import code.util.CustList;
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
        assertNull(t3_.getParentReal());
        assertNull(t2_.getPreviousSibling());
        assertNull(t3_.getPreviousSibling());
        assertNull(t2_.getNextSibling());
        assertNull(t3_.getNextSibling());
    }
    @Test
    public void t18() {
        MockMutableTreeNode t1_ = new MockMutableTreeNode("1");
        MockMutableTreeNode n_ = new MockMutableTreeNode("0");
        t1_.add(n_);
        assertEq("0", n_.info());
        assertEq(1,t1_.getChildCount());
        assertSame(n_, t1_.getChildAt(0));
        t1_.setAccessible(true);
        assertTrue(t1_.isAccessible());
        n_.info("1");
        assertEq("1", n_.info());
    }
    @Test
    public void t19() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        geneTwo(r_, "1", "3");
        geneTwo(r_, "2", "4");
        MockTreeGui t_ = new MockTreeGui(r_);
        AbstractMutableTreeNodeCore<String> c_ = r_.getChildAt(0).getChildAt(0);
        t_.select(c_);
        assertSame(c_,t_.selectEvt());
    }
    @Test
    public void t20() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        geneTwo(r_, "1", "3");
        geneTwo(r_, "2", "4");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.select(null);
        assertNull(t_.selectEvt());
    }
    @Test
    public void t21() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> f_ = gene(r_, "1");
        add(f_, "3");
        add(f_, "5");
        AbstractMutableTreeNodeCore<String> s_ = gene(r_, "2");
        add(s_, "4");
        add(s_, "6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.reload(r_);
        assertTrue(r_.isAccessible());
        assertTrue(((MockMutableTreeNode)f_).isAccessible());
        assertTrue(((MockMutableTreeNode)s_).isAccessible());
        assertTrue(((MockMutableTreeNode) f_.getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode) f_.getChildAt(1)).isAccessible());
        assertTrue(((MockMutableTreeNode) s_.getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode) s_.getChildAt(1)).isAccessible());
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
        AbstractMutableTreeNodeCore<String> f_ = gene(r_, "1");
        add(f_, "3");
        add(f_, "5");
        AbstractMutableTreeNodeCore<String> s_ = gene(r_, "2");
        add(s_, "4");
        add(s_, "6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.select(r_);
        assertSame(r_,t_.selectEvt());
        t_.reloadRoot();
        assertTrue(r_.isAccessible());
        assertTrue(((MockMutableTreeNode)f_).isAccessible());
        assertTrue(((MockMutableTreeNode)s_).isAccessible());
        assertTrue(((MockMutableTreeNode) f_.getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode) f_.getChildAt(1)).isAccessible());
        assertTrue(((MockMutableTreeNode) s_.getChildAt(0)).isAccessible());
        assertTrue(((MockMutableTreeNode) s_.getChildAt(1)).isAccessible());
    }
    @Test
    public void t26() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> f_ = gene(r_, "1");
        add(f_, "3");
        add(f_, "5");
        AbstractMutableTreeNodeCore<String> s_ = gene(r_, "2");
        add(s_, "4");
        add(s_, "6");
        MockTreeGui t_ = new MockTreeGui(r_);
        t_.setRootVisible(true);
        assertTrue(t_.isRootVisible());
    }
    @Test
    public void t27() {
        MockCommonFrameTreeSample mock_ = new MockCommonFrameTreeSample(init());
        MockTreeGui tree_ = (MockTreeGui) mock_.getContentPane().getComponent(0);
        tree_.select(tree_.getRoot());
        tree_.getTreeSelectionListeners().first().valueChanged(tree_.selectEvt().getChildAt(0));
        AbsPlainLabel lab_ = (AbsPlainLabel)  mock_.getContentPane().getComponent(1);
        assertEq("0/1",lab_.getText());
    }
    @Test
    public void t28() {
        MockCommonFrameTreeSample mock_ = new MockCommonFrameTreeSample(init());
        MockTreeGui tree_ = (MockTreeGui) mock_.getContentPane().getComponent(0);
        tree_.select(tree_.getRoot());
        tree_.getTreeSelectionListeners().first().valueChanged(tree_.selectEvt().getChildAt(0));
        tree_.removeTreeSelectionListener(tree_.getTreeSelectionListeners().first());
        assertEq(0,tree_.getTreeSelectionListeners().size());
    }
    @Test
    public void t29() {
        MockCommonFrameTreeSample mock_ = new MockCommonFrameTreeSample(init());
        MockTreeGui tree_ = (MockTreeGui) mock_.getContentPane().getComponent(0);
        tree_.select(tree_.getRoot());
        AbstractMutableTreeNodeCore<String> root_ = tree_.selectEvt();
        tree_.select(root_.getChildAt(0));
        tree_.selectEvt().removeFromParent();
        assertNull(root_.getChildAt(0));
    }
    @Test
    public void t30() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> f_ = gene(r_, "1");
        add(f_, "3");
        add(f_, "5");
        AbstractMutableTreeNodeCore<String> s_ = gene(r_, "2");
        add(s_, "4");
        add(s_, "6");
        r_.removeAllChildren();
        assertNull(r_.getChildAt(0));
    }
    @Test
    public void t31() {
        MockMutableTreeNode r_ = new MockMutableTreeNode("0");
        gene(r_, "1");
        gene(r_, "2");
        r_.removeAllChildren();
        assertNull(r_.getChildAt(0));
    }
    @Test
    public void t32() {
        MockTreeGui tr_ = new MockTreeGui(new MockMutableTreeNode("0"));
        assertEq(0,init().getCompoFactory().emptyList().getLength());
        CustList<AbsTreePath> paths_ = new CustList<AbsTreePath>();
        paths_.add(new MockTreePath(tr_.getRoot()));
        tr_.selectedPaths(new MockTreePaths(paths_));
        assertEq(1,tr_.selectedPaths().getLength());
        assertSame(tr_.getRoot(),tr_.translate(new MockTreePath(tr_.getRoot())));
        assertSame(tr_.getRoot(),tr_.translate(tr_.translate(tr_.getRoot())));
    }
    @Test
    public void t33() {
        MockTreeGui tr_ = new MockTreeGui(new MockMutableTreeNode("0"));
        assertEq(0,init().getCompoFactory().emptyList().getLength());
        CustList<AbsTreePath> paths_ = new CustList<AbsTreePath>();
        tr_.selectedPaths(new MockTreePaths(paths_));
        assertSame(null,tr_.selectEvt());
    }

    private void geneTwo(MockMutableTreeNode _r, String _i, String _j) {
        add(gene(_r, _i), _j);
    }

    private void add(AbstractMutableTreeNodeCore<String> _f, String _i) {
        _f.add(new MockMutableTreeNode(_i));
    }

    private AbstractMutableTreeNodeCore<String> gene(MockMutableTreeNode _r, String _i) {
        MockMutableTreeNode add_ = new MockMutableTreeNode(_i);
        _r.add(add_);
        return add_;
    }

}
