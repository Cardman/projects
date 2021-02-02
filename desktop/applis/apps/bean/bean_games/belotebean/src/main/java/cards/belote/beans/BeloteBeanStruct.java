package cards.belote.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class BeloteBeanStruct extends WithoutParentIdStruct {

    private final BeloteBean bean;

    public BeloteBeanStruct(BeloteBean _bean) {
        bean = _bean;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return bean.getClassName();
    }

    public BeloteBean getInstance() {
        return getBean();
    }

    public BeloteBean getBean() {
        return bean;
    }

}
