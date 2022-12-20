package code.gui;

import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.gui.initialize.AbstractProgramInfos;

public final class CdmFactory {
    private final AbstractProgramInfos programInfos;
    private final AbstractInterceptor interceptor;
    private final AbstractAdvGraphicListGenerator fact;

    public CdmFactory(AbstractProgramInfos _p, AbstractInterceptor _i, AbstractAdvGraphicListGenerator _f) {
        this.programInfos = _p;
        this.interceptor = _i;
        fact = _f;
    }

    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public AbstractAdvGraphicListGenerator getFact() {
        return fact;
    }
}
