package aiki.beans;

public final class MapBeanClickLevelBeanAction implements IntBeanAction{
    private final CommonBean bean;
    private final int place;
    private final int level;

    public MapBeanClickLevelBeanAction(CommonBean _b, int _p, int _l) {
        this.bean = _b;
        this.place = _p;
        this.level = _l;
    }

    @Override
    public String actionBean() {
        CommonBean.feedForms(place, level, bean.getForms());
        return CommonBean.REN_ADD_WEB_HTML_MAP_LEVEL_HTML;
    }

}
