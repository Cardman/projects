package aiki.beans;

public class BeanChgInt implements IntBeanChgInt {

    private int value;
    @Override
    public int valueInt() {
        return value;
    }

    @Override
    public void valueInt(int _v) {
        value = _v;
    }
}
