package code.gui.document;

import code.util.*;

public final class DifficultyBeanFormEvent extends BeanAnchorEvent {
    private final DifficultyBeanRender bean;
    public DifficultyBeanFormEvent(StringMap<AbsBeanRender> _r, DifficultyBeanRender _b) {
        super(_r);
        this.bean = _b;
    }

    @Override
    protected void action(StringMap<AbsBeanRender> _r) {
        bean.apply();
    }
}
