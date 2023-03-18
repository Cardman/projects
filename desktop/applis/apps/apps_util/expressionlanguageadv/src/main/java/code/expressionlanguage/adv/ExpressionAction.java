package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ExpressionAction implements AbsActionListener {
    private final TabEditor current;

    public ExpressionAction(TabEditor _c) {
        this.current = _c;
    }

    @Override
    public void action() {
        current.getExpSpli().setVisible(true);
        current.getMainSplitter().setDividerLocation(current.getMainSplitter().getHeight()/2);
    }
}
