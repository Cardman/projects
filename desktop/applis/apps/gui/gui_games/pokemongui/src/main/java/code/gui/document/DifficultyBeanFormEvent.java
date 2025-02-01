package code.gui.document;

import aiki.beans.*;
import aiki.beans.game.*;

public final class DifficultyBeanFormEvent implements IntBeanAction {
    private final DifficultyBean bean;
    private final DifficultyBeanForm form;
    public DifficultyBeanFormEvent(DifficultyBean _b, DifficultyBeanForm _f) {
        this.bean = _b;
        this.form = _f;
    }

    @Override
    public String actionBean() {
        form.update(bean.getDifficultyCommon());
        bean.change();
        return "";
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
