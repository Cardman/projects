package code.gui.document;

import code.gui.AbsButton;

public final class DefBeanChgSubmit implements IntBeanChgSubmit{
    private final AbsButton button;
    private final BeanBuilderHelper helper;
    public DefBeanChgSubmit(AbsButton _c, BeanBuilderHelper _h) {
        button = _c;
        this.helper = _h;
    }

    @Override
    public void addEvt(IntBeanAction _action) {
        getButton().addActionListener(new BeanAnchorEvent(helper,_action));
    }

    public AbsButton getButton() {
        return button;
    }
}
