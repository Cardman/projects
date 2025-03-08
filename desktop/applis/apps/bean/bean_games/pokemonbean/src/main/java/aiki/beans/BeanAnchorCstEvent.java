package aiki.beans;


public final class BeanAnchorCstEvent implements IntBeanAction{
    private final String constant;

    public BeanAnchorCstEvent(String _cst) {
        constant = _cst;
    }

    @Override
    public String actionBean() {
        return constant;
    }

}
