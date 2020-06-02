package code.util.graphs;
import code.util.ints.SortedEdge;

public final class SortedNumberedNode implements SortedEdge<SortedNumberedNode> {

    private final int number;
    private int order = -1;

    public SortedNumberedNode(int _number) {
        number = _number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean eq(SortedNumberedNode _g) {
        return number == _g.number;
    }

    @Override
    public void setOrder(int _o) {
        order = _o;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
