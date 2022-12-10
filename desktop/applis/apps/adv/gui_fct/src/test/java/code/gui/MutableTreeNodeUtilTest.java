package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.gui.initialize.AbsCompoFactory;
import code.mock.MockMutableTreeNode;
import code.util.Ints;
import org.junit.Test;

public final class MutableTreeNodeUtilTest extends EquallableGuiFctUtil {
    @Test
    public void getIndexes() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNode r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNode ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        c_.newTreeGui(r_);
        Ints ls_ = MutableTreeNodeUtil.getIndexes(ch_);
        assertEq(1,ls_.size());
        assertEq(0,ls_.get(0));
    }
    @Test
    public void reload1() {
        MockProgramInfosSecSample init_ = init();
        AbsCompoFactory c_ = init_.getCompoFactory();
        AbstractMutableTreeNode r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNode ch_ = c_.newMutableTreeNode("1");
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
        AbstractMutableTreeNode r_ = c_.newMutableTreeNode("0");
        AbstractMutableTreeNode ch_ = c_.newMutableTreeNode("1");
        r_.add(ch_);
        AbsTreeGui t_ = c_.newTreeGui(r_);
        t_.select(ch_);
        MutableTreeNodeUtil.reload(t_);
        assertFalse(((MockMutableTreeNode)r_).isAccessible());
        assertTrue(((MockMutableTreeNode)ch_).isAccessible());
    }
}
