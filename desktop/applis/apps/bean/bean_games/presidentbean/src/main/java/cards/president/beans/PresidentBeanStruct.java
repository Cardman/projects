package cards.president.beans;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class PresidentBeanStruct extends WithoutParentIdStruct {

    private final Bean bean;

    public PresidentBeanStruct(Bean _bean) {
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
