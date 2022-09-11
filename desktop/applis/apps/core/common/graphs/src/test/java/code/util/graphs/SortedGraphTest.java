package code.util.graphs;

import code.util.CustList;
import org.junit.Test;

public final class SortedGraphTest extends EquallableExGraph {

    @Test
    public void process1Test() {
        SortedGraph g_ = new SortedGraph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        SortedNumberedNode four_ = new SortedNumberedNode(4);
        SortedNumberedNode five_ = new SortedNumberedNode(5);
        SortedNumberedNode six_ = new SortedNumberedNode(6);
        SortedNumberedNode seven_ = new SortedNumberedNode(7);
        g_.addSegment(one_, two_);
        g_.addSegment(three_, four_);
        g_.addSegment(two_, three_);
        g_.addSegment(one_, seven_);
        g_.addSegment(five_, one_);
        g_.addSegment(seven_, three_);
        g_.addSegment(one_, four_);
        g_.addSegment(three_, six_);
        assertTrue(!g_.hasCycle());
        assertTrue(g_.elementsCycle().isEmpty());
        assertTrue(g_.hasPseudoRoots());
        CustList<SortedNumberedNode> out_ = g_.process();
        assertEq(7, out_.size());
        assertEq(4, out_.get(0).getNumber());
        assertEq(0, out_.get(0).getOrder());
        assertEq(6, out_.get(1).getNumber());
        assertEq(1, out_.get(1).getOrder());
        assertEq(3, out_.get(2).getNumber());
        assertEq(2, out_.get(2).getOrder());
        assertEq(2, out_.get(3).getNumber());
        assertEq(3, out_.get(3).getOrder());
        assertEq(7, out_.get(4).getNumber());
        assertEq(4, out_.get(4).getOrder());
        assertEq(1, out_.get(5).getNumber());
        assertEq(5, out_.get(5).getOrder());
        assertEq(5, out_.get(6).getNumber());
        assertEq(6, out_.get(6).getOrder());
    }
    @Test
    public void getTreeFrom1Test() {
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
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

    @Test
    public void hasCycleTest() {
        SortedGraph g_ = new SortedGraph();
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
        assertTrue(!g_.hasPseudoRoots());
        CustList<SortedNumberedNode> es_ = g_.elementsCycle();
        assertEq(5, es_.size());
        assertTrue(contains(es_, one_));
        assertTrue(contains(es_, two_));
        assertTrue(contains(es_, three_));
        assertTrue(contains(es_, four_));
        assertTrue(contains(es_, seven_));
    }

    @Test
    public void isDirectTrees1Test() {
        SortedGraph g_ = new SortedGraph();
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees2Test() {
        SortedGraph g_ = new SortedGraph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(g_.isDirectTrees());
    }

    @Test
    public void isDirectTrees3Test() {
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(!g_.isDirectTrees());
    }

    @Test
    public void isTrees1Test() {
        SortedGraph g_ = new SortedGraph();
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees2Test() {
        SortedGraph g_ = new SortedGraph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(one_, two_);
        g_.addSegment(one_, three_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void isTrees3Test() {
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
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
        SortedGraph g_ = new SortedGraph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addSegment(two_, one_);
        g_.addSegment(three_, one_);
        assertTrue(g_.isTrees());
    }

    @Test
    public void test() {
        SortedGraph g_ = new SortedGraph();
        SortedNumberedNode one_ = new SortedNumberedNode(1);
        SortedNumberedNode two_ = new SortedNumberedNode(2);
        SortedNumberedNode three_ = new SortedNumberedNode(3);
        g_.addReversedSegment(new ArrowedSegment(one_,two_));
        g_.addSegment(new ArrowedSegment(three_, one_));
        assertTrue(g_.isTrees());
        assertEq(1,g_.getLines(two_).size());
        assertEq(1,g_.getChildren(two_).size());
        assertEq(1,g_.getChildrenSegments(two_).size());
        assertNotNull(g_.getReverse());
        assertEq(0,g_.getDynamicSeparations().size());
        assertEq(3,g_.getElementsListCopy().size());
    }
}
