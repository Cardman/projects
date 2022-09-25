package code.util.graphs;

import org.junit.Test;

import code.util.CustList;


public final class GraphTest extends EquallableExGraph {

    @Test
    public void isDirectTrees1Test() {
        Graph g_ = new Graph();
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees2Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees3Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(four_, five_);
        g_.addSegment(four_, six_);
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees4Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(two_, four_);
        g_.addSegment(three_, four_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees5Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees6Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isTrees1Test() {
        Graph g_ = new Graph();
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees2Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees3Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(four_, five_);
        g_.addSegment(four_, six_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees4Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(two_, four_);
        g_.addSegment(three_, four_);
        assertTrue(!g_.isTrees());
    }

    @Test
    public void isTrees5Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(!g_.isTrees());
    }

    @Test
    public void isTrees6Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void hasCycle1Test() {
        Graph g_ = new Graph();
        assertTrue(!g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle2Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(!g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle3Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(four_, five_);
        g_.addSegment(four_, six_);
        assertTrue(!g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle4Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        g_.addSegment(two_, four_);
        g_.addSegment(three_, four_);
        assertTrue(!g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle5Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
    }

    @Test
    public void hasCycle6Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(!g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }

    @Test
    public void hasCycle7Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        g_.addSegment(five_, one_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
    }

    @Test
    public void hasCycle8Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
    }

    @Test
    public void hasCycle9Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(5, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
        assertTrue(contains(es_, seven_));
    }

    @Test
    public void hasCycle10Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(one_, four_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        assertTrue(!g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(0, es_.size());
    }


    @Test
    public void hasCycle11Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(four_, one_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(5, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
        assertTrue(contains(es_, seven_));
    }

    @Test
    public void hasCycle12Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        g_.addSegment(one_, one_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(1, es_.size());
        assertTrue(contains(es_, one_));
    }

    @Test
    public void hasCycle13Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(three_, four_);
        g_.addSegment(two_, three_);
        g_.addSegment(seven_, three_);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, seven_);
        g_.addSegment(four_, one_);
        assertTrue(g_.hasCycle());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(4, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
//        assertTrue(es_.containsObj(seven_));
    }
    @Test
    public void getTreeFrom1Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(one_, four_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        CustList<SortedNumberedNode> nodes_ = g_.getTreeFrom(one_);
        assertEq(6,nodes_.size());
        assertTrue(contains(nodes_, one_));
        assertTrue(contains(nodes_, two_));
        assertTrue(contains(nodes_, three_));
        assertTrue(contains(nodes_, four_));
        assertTrue(contains(nodes_, six_));
        assertTrue(contains(nodes_, seven_));
    }
    @Test
    public void pseudoRoots1Test() {
        Graph g_ = new Graph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(seven_, three_);
        g_.addSegment(three_, four_);
        g_.addSegment(one_, four_);
        g_.addSegment(five_, one_);
        g_.addSegment(three_, six_);
        CustList<SortedNumberedNode> nodes_ = g_.pseudoRoots();
        assertEq(3,nodes_.size());
        assertTrue(contains(nodes_, four_));
        assertTrue(contains(nodes_, five_));
        assertTrue(contains(nodes_, six_));
    }

}
