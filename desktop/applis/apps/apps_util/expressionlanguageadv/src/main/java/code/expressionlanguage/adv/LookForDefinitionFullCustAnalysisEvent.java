package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class LookForDefinitionFullCustAnalysisEvent implements AbsActionListener {
    private final TabEditor tabEditor;

    public LookForDefinitionFullCustAnalysisEvent(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        tabEditor.getWindowSecEditor().getFinderSymbol().submit(new LookForDefinitionFullCustAnalysisTask(tabEditor));
    }
}
