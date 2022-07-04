package cards.gui.dialogs;
import cards.gui.WindowCards;
import cards.gui.dialogs.events.ClosingEditorCards;
import code.gui.AbsDialog;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;

/**
    */

abstract class DialogCards {

    private final AbsTabbedPane jt;
    private final AbsCompoFactory compoFactory;
    private WindowCards main;
    private final AbsDialog cardDialog;
    private final ClosingEditorCards clos;

    protected DialogCards(AbstractProgramInfos _frameFactory, ClosingEditorCards _ch) {
        if (_ch != null) {
            clos = _ch;
            cardDialog = _frameFactory.getFrameFactory().newDialog(_ch);
        } else {
            clos = new ClosingEditorCards();
            cardDialog = _frameFactory.getFrameFactory().newDialog();
        }
        compoFactory = _frameFactory.getCompoFactory();
        jt = compoFactory.newAbsTabbedPane();
    }

    public ClosingEditorCards getClos() {
        return clos;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbsDialog getCardDialog() {
        return cardDialog;
    }

    public void setMain(WindowCards _main) {
        main = _main;
    }
    public WindowCards getMain() {
        return main;
    }
    public void closeWindow() {
        cardDialog.closeWindow();
        cardDialog.getPane().removeAll();
    }
    AbsTabbedPane getJt() {
        return jt;
    }
}
