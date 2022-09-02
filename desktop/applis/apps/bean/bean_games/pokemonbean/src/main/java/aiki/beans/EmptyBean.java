package aiki.beans;

public final class EmptyBean extends CommonBean {
    @Override
    public void beforeDisplaying() {
        setBaseForms(getBaseForms());
    }
}
