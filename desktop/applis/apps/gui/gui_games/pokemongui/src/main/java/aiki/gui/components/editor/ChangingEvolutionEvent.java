package aiki.gui.components.editor;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class ChangingEvolutionEvent implements ListSelection {
    private final GeneComponentModelEvolution form;
    public ChangingEvolutionEvent(GeneComponentModelEvolution _g) {
        form = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applyChange();
    }
}
