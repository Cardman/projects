package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.*;

public final class GeneComponentModelEventBoolVal extends GeneComponentModelEvent<BoolVal> {
    private AbsCustCheckBox event;

    public GeneComponentModelEventBoolVal(AbstractProgramInfos _fact, String _file, String _k, String _v) {
        super(_fact, _file, _k, _v);
    }

    @Override
    protected AbsCustComponent geneKey(int _select) {
        event = getCore().getCompoFactory().newCustCheckBox(MessagesPkEditor.getMessagesEditorSelectButtonsTr(MessagesPkEditor.getAppliTr(getCore().currentLg())).getMapping().getVal(MessagesEditorSelect.EVENT_BOOL));
        return event;
    }

    @Override
    protected BoolVal valEvent() {
        return ComparatorBoolean.of(event.isSelected());
    }

    @Override
    protected void updateEvent(BoolVal _e) {
        event.setSelected(_e == BoolVal.TRUE);
    }

    public AbsCustCheckBox getEvent() {
        return event;
    }
}
