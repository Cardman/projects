package code.expressionlanguage.adv;

import code.gui.AbsMenuItem;

public final class ExpMenuFrameInteract implements AbsOpenFrameInteract {
    private final AbsMenuItem menu;

    public ExpMenuFrameInteract(AbsMenuItem _m) {
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
