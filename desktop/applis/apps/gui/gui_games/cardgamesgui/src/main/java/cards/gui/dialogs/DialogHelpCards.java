package cards.gui.dialogs;

import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import code.gui.AbsCommonFrame;
import code.gui.files.ClosingFileFrameEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public abstract class DialogHelpCards {

    private final AbsCommonFrame absDialog;
    private final AbsCompoFactory compo;

    protected DialogHelpCards(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        absDialog = WindowCards.frame(_fact);
        absDialog.addWindowListener(new ClosingFileFrameEvent(absDialog,_modal));
        compo = _fact.getCompoFactory();
    }

    public void setTitleDialog(WindowCardsInt _fenetre, String _title) {
        absDialog.setIconImage(_fenetre.getCommonFrame().getImageIconFrame());
        absDialog.setLocationRelativeTo(_fenetre.getCommonFrame());
        absDialog.setTitle(_title);
    }

    public AbsCommonFrame getAbsDialog() {
        return absDialog;
    }

    public AbsCompoFactory getCompo() {
        return compo;
    }
}
