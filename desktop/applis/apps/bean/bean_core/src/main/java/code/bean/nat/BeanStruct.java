package code.bean.nat;

import code.bean.Bean;

public abstract class BeanStruct extends CommNatStruct {
    private final Bean bean;

    protected BeanStruct(Bean _bean) {
        super(_bean.getClassName());
        bean = _bean;
    }

    public StringMapObjectBase getForms() {
        return getBean().getBaseForms();
    }
    public Bean getBean() {
        return bean;
    }

    public void beforeDisplaying() {
        getBean().beforeDisplaying();
    }
}
