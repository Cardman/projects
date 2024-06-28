package code.expressionlanguage.adv;

import code.gui.EnabledMenu;

public final class ExpMenuFrameInteract implements AbsOpenFrameInteract {
    private final EnabledMenu menu;

    public ExpMenuFrameInteract(EnabledMenu _m) {
        this.menu = _m;
    }

    @Override
    public AbsOpenFrameInteract open() {
        menu.setEnabled(false);
        return this;
    }

    @Override
    public AbsOpenFrameInteract close() {
        menu.setEnabled(true);
        return this;
    }
}
