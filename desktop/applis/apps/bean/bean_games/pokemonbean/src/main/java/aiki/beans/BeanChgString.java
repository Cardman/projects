package aiki.beans;

public class BeanChgString implements IntBeanChgString {
    private String value = "";

    @Override
    public String tryRet() {
        return value;
    }

    @Override
    public void setupValue(String _v) {
        value = _v;
    }
}
