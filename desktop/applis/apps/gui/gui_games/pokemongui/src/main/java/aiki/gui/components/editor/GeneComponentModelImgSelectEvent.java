package aiki.gui.components.editor;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class GeneComponentModelImgSelectEvent implements ListSelection {
    private final GeneComponentModelImgSelect geneComponentModelImgSelect;

    public GeneComponentModelImgSelectEvent(GeneComponentModelImgSelect _g) {
        this.geneComponentModelImgSelect = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        geneComponentModelImgSelect.updateImg();
    }
}
