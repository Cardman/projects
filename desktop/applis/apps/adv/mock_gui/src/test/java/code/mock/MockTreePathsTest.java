package code.mock;

import code.gui.AbsTreePath;
import code.util.CustList;
import org.junit.Test;

public final class MockTreePathsTest extends EquallableMockGuiUtil {
    @Test
    public void p1() {
        MockTreePaths m_ = new MockTreePaths(new CustList<AbsTreePath>());
        m_.add(new MockTreePath(new MockMutableTreeNode("element")));
        assertEq(1, m_.getLength());
        assertEq("element",m_.elt(0).data().info());
    }
    @Test
    public void p2() {
        MockTreePaths m_ = new MockTreePaths(new CustList<AbsTreePath>());
        m_.add(new MockTreePath(new MockMutableTreeNode("element")));
        m_.remove(0);
        assertEq(0, m_.getLength());
    }
    @Test
    public void p3() {
        MockTreePaths m_ = new MockTreePaths(new CustList<AbsTreePath>());
        m_.add(new MockTreePath(new MockMutableTreeNode("element")));
        m_.set(0,new MockTreePath(new MockMutableTreeNode("element2")));
        assertEq("element2",m_.elt(0).data().info());
    }
    @Test
    public void p4() {
        MockMutableTreeNode root_ = new MockMutableTreeNode("element");
        MockMutableTreeNode ch_ = new MockMutableTreeNode("element2");
        root_.add(ch_);
        assertEq(2,new MockTreePath(ch_).getLength());
        assertEq(1,new MockTreePath(root_).getLength());
        assertSame(root_,new MockTreePath(ch_).parent(null).data());
        assertNull(new MockTreePath(root_).parent(null));
    }
}
