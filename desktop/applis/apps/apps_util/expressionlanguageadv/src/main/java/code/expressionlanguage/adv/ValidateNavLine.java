package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateNavLine implements AbsActionListener {
    private final TabEditor output;

    public ValidateNavLine(TabEditor _w) {
        this.output = _w;
    }

    @Override
    public void action() {
        int index_ = output.getIndex();
        output.afterValidate(index_);
        output.setIndex(-1);
    }
}
