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
        assertEq("element",m_.elt(0).data().getUserObject());
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
        assertEq("element2",m_.elt(0).data().getUserObject());
    }
}
