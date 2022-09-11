package code.util.graphs;

public final class SortedNumberedNode {

    private final int number;
    private int order = -1;

    public SortedNumberedNode(int _number) {
        number = _number;
    }

    public int getNumber() {
        return number;
    }

    public boolean eq(SortedNumberedNode _g) {
        return number == _g.number;
    }

    public void setOrder(int _o) {
        order = _o;
    }

    public int getOrder() {
        return order;
    }
}
