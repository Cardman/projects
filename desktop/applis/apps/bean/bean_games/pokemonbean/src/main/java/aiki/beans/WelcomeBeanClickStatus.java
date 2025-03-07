package aiki.beans;

public final class WelcomeBeanClickStatus implements IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickStatus(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.clickStatus();
    }
    @Override
    public CommonBean getBean() {
        return bean;
    }
}
