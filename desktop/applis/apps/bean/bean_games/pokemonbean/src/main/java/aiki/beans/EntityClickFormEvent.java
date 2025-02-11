package aiki.beans;

public final class EntityClickFormEvent implements IntBeanAction {
    private final CommonBean bean;
    private final TranslatedKey key;

    public EntityClickFormEvent(CommonBean _b, TranslatedKey _k) {
        this.bean = _b;
        this.key = _k;
    }

    @Override
    public String actionBean() {
        return bean.tryRedirect(key);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
