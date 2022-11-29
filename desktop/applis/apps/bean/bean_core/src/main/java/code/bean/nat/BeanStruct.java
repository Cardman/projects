package code.bean.nat;

import code.bean.Bean;
import code.expressionlanguage.structs.AbNullStruct;

public abstract class BeanStruct extends AbNullStruct {
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
