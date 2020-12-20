package code.formathtml.sample;
import code.util.ints.Displayable;

public class CompositeSec implements Displayable {

    private int integer;

    public int getInteger() {
        return integer;
    }

    public void setInteger(int _integer) {
        integer = _integer;
    }

    public int summum(int _other) {
        return integer + _other;
    }

    public int sum(Long _other) {
        return integer + _other.intValue();
    }

    public int sum(Long _other, Long _otherTwo) {
        return integer + _other.intValue() + _otherTwo.intValue();
    }

    @Override
    public String display() {
        return new StringBuilder().append(integer).toString();
    }
}
