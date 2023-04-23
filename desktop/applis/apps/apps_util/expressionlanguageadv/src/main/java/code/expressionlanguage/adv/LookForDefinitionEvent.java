package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class LookForDefinitionEvent implements AbsActionListener {
    private final TabEditor tabEditor;

    public LookForDefinitionEvent(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        tabEditor.getWindowSecEditor().getFinderSymbol().submit(new LookForDefinitionTask(tabEditor));
    }
}
