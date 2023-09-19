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
        c_.newTreeGui(r_);
        AbsTreePaths e_ = init_.getCompoFactory().emptyList();
        e_.add(new MockTreePath(ch_));
        CustList<AbstractMutableTreeNodeCore<String>> nodes_ = MutableTreeNodeUtil.list(r_,e_);
        assertEq(1,nodes_.size());
        assertSame(ch_,nodes_.get(0));
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
}
