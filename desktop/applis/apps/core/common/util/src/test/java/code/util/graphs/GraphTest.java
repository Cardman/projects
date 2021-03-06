package code.util.graphs;

import code.util.EquallableExUtil;
import org.junit.Test;

import code.util.EqList;


public final class GraphTest extends EquallableExUtil {

    @Test
    public void isDirectTrees1Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees2Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees3Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(four_, five_);
        g_.addSegment(four_, six_);
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees4Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(two_, four_);
        g_.addSegment(three_, four_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees5Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees6Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isTrees1Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees2Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees3Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(four_, five_);
        g_.addSegment(four_, six_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees4Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(two_, four_);
        g_.addSegment(three_, four_);
        assertTrue(!g_.isTrees());
    }

    @Test
    public void isTrees5Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(!g_.isTrees());
    }

    @Test
    public void isTrees6Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void hasCycle1Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        assertTrue(!g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle2Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(!g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle3Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(four_, five_);
        g_.addSegment(four_, six_);
        assertTrue(!g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle4Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(two_, four_);
        g_.addSegment(three_, four_);
        assertTrue(!g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle5Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(es_.containsObj(one_));
        assertTrue(es_.containsObj(two_));
        assertTrue(es_.containsObj(three_));
        assertTrue(es_.containsObj(four_));
    }

    @Test
    public void hasCycle6Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(!g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle7Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        g_.addSegment(five_, one_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(es_.containsObj(one_));
        assertTrue(es_.containsObj(two_));
        assertTrue(es_.containsObj(three_));
        assertTrue(es_.containsObj(four_));
    }

    @Test
    public void hasCycle8Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(es_.containsObj(one_));
        assertTrue(es_.containsObj(two_));
        assertTrue(es_.containsObj(three_));
        assertTrue(es_.containsObj(four_));
    }

    @Test
    public void hasCycle9Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        NumberedNode seven_ = new NumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(5, es_.size());
        assertTrue(es_.containsObj(one_));
        assertTrue(es_.containsObj(two_));
        assertTrue(es_.containsObj(three_));
        assertTrue(es_.containsObj(four_));
        assertTrue(es_.containsObj(seven_));
    }

    @Test
    public void hasCycle10Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        NumberedNode seven_ = new NumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(one_, four_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        assertTrue(!g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }


    @Test
    public void hasCycle11Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode seven_ = new NumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(5, es_.size());
        assertTrue(es_.containsObj(one_));
        assertTrue(es_.containsObj(two_));
        assertTrue(es_.containsObj(three_));
        assertTrue(es_.containsObj(four_));
        assertTrue(es_.containsObj(seven_));
    }

    @Test
    public void hasCycle12Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        g_.addSegment(one_, one_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
        assertEq(1, es_.size());
        assertTrue(es_.containsObj(one_));
    }

    @Test
    public void hasCycle13Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode seven_ = new NumberedNode(7);
        g_.addSegment(three_, four_);
        g_.addSegment(two_, three_);
        g_.addSegment(seven_, three_);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, seven_);
        g_.addSegment(four_, one_);
        assertTrue(g_.hasCycle());
        EqList<NumberedNode> es_ = g_.elementsCycle();
//        assertEq(5, es_.size());
        assertTrue(es_.size() >= 4);
        assertTrue(es_.containsObj(one_));
        assertTrue(es_.containsObj(two_));
        assertTrue(es_.containsObj(three_));
        assertTrue(es_.containsObj(four_));
//        assertTrue(es_.containsObj(seven_));
    }
    @Test
    public void getTreeFrom1Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        NumberedNode seven_ = new NumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(one_, four_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        EqList<NumberedNode> nodes_ = g_.getTreeFrom(one_);
        assertEq(6,nodes_.size());
        assertTrue(nodes_.containsObj(one_));
        assertTrue(nodes_.containsObj(two_));
        assertTrue(nodes_.containsObj(three_));
        assertTrue(nodes_.containsObj(four_));
        assertTrue(nodes_.containsObj(six_));
        assertTrue(nodes_.containsObj(seven_));
    }
    @Test
    public void pseudoRoots1Test() {
        Graph<NumberedNode> g_ = new Graph<NumberedNode>();
        NumberedNode one_ = new NumberedNode(1);
        NumberedNode two_ = new NumberedNode(2);
        NumberedNode three_ = new NumberedNode(3);
        NumberedNode four_ = new NumberedNode(4);
        NumberedNode five_ = new NumberedNode(5);
        NumberedNode six_ = new NumberedNode(6);
        NumberedNode seven_ = new NumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(one_, four_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        EqList<NumberedNode> nodes_ = g_.pseudoRoots();
        assertEq(3,nodes_.size());
        assertTrue(nodes_.containsObj(four_));
        assertTrue(nodes_.containsObj(five_));
        assertTrue(nodes_.containsObj(six_));
    }
}
