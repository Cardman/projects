package aiki.beans;

public final class WelcomeBeanClickItems implements IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickItems(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.clickItems();
    }

}
