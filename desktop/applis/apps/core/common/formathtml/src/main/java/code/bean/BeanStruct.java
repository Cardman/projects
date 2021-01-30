package code.bean;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class BeanStruct extends WithoutParentIdStruct {

    private final Bean bean;

    public BeanStruct(Bean _bean) {
        bean = _bean;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return bean.getClassName();
    }

    public Bean getInstance() {
        return getBean();
    }

    public Bean getBean() {
        return bean;
    }
}
