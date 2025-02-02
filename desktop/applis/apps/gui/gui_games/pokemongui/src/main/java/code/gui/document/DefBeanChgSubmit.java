package code.gui.document;

import aiki.beans.AbsBeanChgSubmit;
import aiki.beans.IntBeanAction;
import aiki.beans.IntBeanBuilderHelper;
import code.gui.AbsButton;

public final class DefBeanChgSubmit extends AbsBeanChgSubmit {
    private final AbsButton button;
    private final IntBeanBuilderHelper helper;
    public DefBeanChgSubmit(AbsButton _c, BeanBuilderHelper _h) {
        button = _c;
        helper = _h;
    }

    @Override
    public void addEvt(IntBeanAction _action) {
        super.addEvt(_action);
        getButton().addActionListener(new BeanAnchorEvent(getHelper(),_action));
    }

    public IntBeanBuilderHelper getHelper() {
        return helper;
    }

    public AbsButton getButton() {
        return button;
    }
}
