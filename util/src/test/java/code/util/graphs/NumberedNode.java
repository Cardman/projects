package code.util.graphs;
import code.util.ints.GraphElement;

public final class NumberedNode implements GraphElement<NumberedNode> {

    private final int number;

    public NumberedNode(int _number) {
        number = _number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean eq(NumberedNode _g) {
        return number == _g.number;
    }

}
