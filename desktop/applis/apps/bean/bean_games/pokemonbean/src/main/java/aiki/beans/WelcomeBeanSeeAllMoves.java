package aiki.beans;

public final class WelcomeBeanSeeAllMoves implements IntBeanAction{
    private final WelcomeBean bean;

    public WelcomeBeanSeeAllMoves(WelcomeBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        return bean.seeAllMoves();
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
