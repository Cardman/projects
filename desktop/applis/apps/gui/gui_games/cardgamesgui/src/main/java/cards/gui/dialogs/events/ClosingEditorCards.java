package cards.gui.dialogs.events;

import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsCloseableDialog;

public final class ClosingEditorCards implements AbsCloseableDialog {
    private SetterSelectedCardList editor;

    public ClosingEditorCards() {
        this(null);
    }

    public ClosingEditorCards(SetterSelectedCardList _e) {
        setEditor(_e);
    }

    public SetterSelectedCardList getEditor() {
        return editor;
    }

    public void setEditor(SetterSelectedCardList _e) {
        this.editor = _e;
    }

    @Override
    public void closeWindow() {
        SetterSelectedCardList ed_ = getEditor();
        if (ed_ == null) {
            return;
        }
        if (ed_.isSetToNullGame()) {
            ed_.cancelDeal();
        }
    }
}
