package aiki.beans;

public final class WelcomeBeanClickPokedex implements IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickPokedex(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.clickPokedex();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
