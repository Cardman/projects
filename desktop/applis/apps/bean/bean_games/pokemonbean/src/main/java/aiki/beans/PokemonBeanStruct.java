package aiki.beans;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class PokemonBeanStruct extends WithoutParentIdStruct {

    private final Bean bean;
    private final WithFacade withFacade;

    public PokemonBeanStruct(Bean _bean) {
        bean = _bean;
        withFacade = (WithFacade) _bean;
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
