package code.gui;

import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.gui.initialize.AbstractLightProgramInfos;

public final class CdmFactory {
    private final AbstractLightProgramInfos programInfos;
    private final AbstractInterceptor interceptor;

    public CdmFactory(AbstractLightProgramInfos _p, AbstractInterceptor _i) {
        this.programInfos = _p;
        this.interceptor = _i;
    }

    public AbstractInterceptor getInterceptor() {
        return interceptor;
    }

    public AbstractLightProgramInfos getProgramInfos() {
        return programInfos;
    }

}
