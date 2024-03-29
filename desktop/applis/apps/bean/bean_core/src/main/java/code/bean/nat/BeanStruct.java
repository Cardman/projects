package code.bean.nat;

import code.bean.Bean;

public abstract class BeanStruct extends NaNuSt {
    private final Bean bean;

    protected BeanStruct(Bean _bean) {
        bean = _bean;
    }

    public Bean getBean() {
        return bean;
    }

    public void beforeDisplaying() {
        getBean().beforeDisplaying();
    }
}
