package cards.tarot.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class TarotBeanStruct extends WithoutParentIdStruct {

    private final TarotBean bean;

    public TarotBeanStruct(TarotBean _bean) {
        bean = _bean;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return bean.getClassName();
    }

    public TarotBean getInstance() {
        return getBean();
    }

    public TarotBean getBean() {
        return bean;
    }

}