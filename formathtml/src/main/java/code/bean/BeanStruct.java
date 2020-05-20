package code.bean;

import code.expressionlanguage.ContextEl;
import code.formathtml.structs.AbstractStruct;

public final class BeanStruct extends AbstractStruct implements RealInstanceStruct {

    private Bean bean;

    public BeanStruct(Bean _bean) {
        bean = _bean;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return bean.getClassName();
    }

    @Override
    public Object getInstance() {
        return getBean();
    }

    public Bean getBean() {
        return bean;
    }
}
