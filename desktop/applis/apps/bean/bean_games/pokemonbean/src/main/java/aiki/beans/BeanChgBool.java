package aiki.beans;

public class BeanChgBool implements IntBeanChgBool {
    private boolean selected;
    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean _v) {
        selected = _v;
    }
}
