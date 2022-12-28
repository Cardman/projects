package code.gui;

import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.gui.initialize.AbstractLightProgramInfos;

public final class CdmFactory {
    private final AbstractLightProgramInfos programInfos;
    private final AbstractInterceptor interceptor;
    private final AbstractAdvGraphicListGenerator fact;
    private final AbstractAdvGraphicListGeneratorStruct grFact;

    public CdmFactory(AbstractLightProgramInfos _p, AbstractInterceptor _i, AbstractAdvGraphicListGenerator _f, AbstractAdvGraphicListGeneratorStruct _cr) {
        this.programInfos = _p;
        this.interceptor = _i;
        fact = _f;
        grFact = _cr;
    }

    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    public AbstractLightProgramInfos getProgramInfos() {
        return programInfos;
    }

    public AbstractAdvGraphicListGenerator getFact() {
        return fact;
    }

    public AbstractAdvGraphicListGeneratorStruct getGrFact() {
        return grFact;
    }
}
