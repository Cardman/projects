package aiki.beans;

import aiki.facade.*;

public interface BeanRenderWithAppName {
    void build(FacadeGame _facade);
    void setBuilder(IntBeanBuilderHelper _b);
    FacadeGame getFacade();
    void setFacade(FacadeGame _f);
    IntBeanBuilderHelper getBuilder();

    void setLanguage(String _lg);
}
