package aiki.beans;

public final class WelcomeBeanClickAbilities implements IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanClickAbilities(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.clickAbilities();
    }

}
