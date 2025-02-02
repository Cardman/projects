package aiki.beans;


public final class BeanAnchorCstEvent implements IntBeanAction{
    private final String constant;
    private final CommonBean bean;
    public BeanAnchorCstEvent(String _cst, CommonBean _b) {
        constant = _cst;
        bean = _b;
    }

    @Override
    public String actionBean() {
        return constant;
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
