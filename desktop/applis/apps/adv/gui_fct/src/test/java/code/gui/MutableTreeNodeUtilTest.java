package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.gui.initialize.AbsCompoFactory;
import code.mock.MockMutableTreeNode;
import code.mock.MockTreePath;
import code.util.CustList;
import code.util.Ints;
import org.junit.Test;

public final class MutableTreeNodeUtilTest extends EquallableGuiFctUtil {
    @Test
    public void getIndexes() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        c_.newTreeGui(r_);
        Ints ls_ = ch_.getIndexes();
        assertEq(1,ls_.size());
        assertEq(0,ls_.get(0));
        assertSame(null,r_.simular(null));
        assertSame(ch_,r_.simular(ch_));
    }
    @Test
    public void reload1() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        AbsTreeGui t_ = c_.newTreeGui(r_);
        MutableTreeNodeUtil.reload(t_);
        assertTrue(((MockMutableTreeNode)r_).isAccessible());
        assertTrue(((MockMutableTreeNode)ch_).isAccessible());
    }
    @Test
    public void reload2() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        AbsTreeGui t_ = c_.newTreeGui(r_);
        t_.select(ch_);
        MutableTreeNodeUtil.reload(t_);
        assertFalse(((MockMutableTreeNode)r_).isAccessible());
        assertTrue(((MockMutableTreeNode)ch_).isAccessible());
    }
    @Test
    public void list1() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        AbsTreeGui tr_ = c_.newTreeGui(r_);
        AbsTreePaths e_ = init_.getCompoFactory().emptyList();
        e_.add(new MockTreePath(ch_));
        CustList<AbstractMutableTreeNodeCore<String>> nodes_ = MutableTreeNodeUtil.list(r_,e_);
        assertEq(1,nodes_.size());
        assertSame(ch_,nodes_.get(0));
        CustList<AbstractMutableTreeNodeCore<String>> anc_ = MutableTreeNodeUtil.listPaths(tr_, new MockTreePath(ch_));
        assertEq(2,anc_.size());
        assertSame(r_,anc_.get(0));
        assertSame(ch_,anc_.get(1));
    }
    @Test
    public void list2() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        AbsTreeGui tr_ = c_.newTreeGui(r_);
        CustList<AbstractMutableTreeNodeCore<String>> e_ = new CustList<AbstractMutableTreeNodeCore<String>>();
        e_.add(ch_);
        AbsTreePaths nodes_ = MutableTreeNodeUtil.list(init_.getCompoFactory(),tr_,e_);
        assertEq(1,nodes_.getLength());
        assertSame(ch_,nodes_.elt(0).data());
    }
    @Test
    public void parent1() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        AbsTreeGui tr_ = c_.newTreeGui(r_);
        tr_.select(ch_);
        assertSame(ch_,MutableTreeNodeUtil.parent(tr_));
    }
    @Test
    public void parent2() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNodeCore<String> r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNodeCore<String> ch_ = c_.newMutableTreeNode("1");
        AbstractMutableTreeNodeCore<String> chSub_ = c_.newMutableTreeNode("2");
        r_.add(ch_);
        ch_.add(chSub_);
        AbsTreeGui tr_ = c_.newTreeGui(r_);
        tr_.select(ch_);
        ch_.removeFromParent();
        assertSame(r_,MutableTreeNodeUtil.parent(tr_));
    }
}
