package code.util.graphs;

import code.util.CustList;
import org.junit.Assert;

public abstract class EquallableExGraph {

    public static void assertNotNull(Graph _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static boolean contains(CustList<SortedNumberedNode> _list, SortedNumberedNode _node) {
        return Graph.containsNode(_list,_node);
    }

}
