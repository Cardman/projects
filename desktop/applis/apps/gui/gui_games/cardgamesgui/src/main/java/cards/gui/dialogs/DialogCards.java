package cards.gui.dialogs;
import cards.gui.WindowCards;
import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;

/**
    */

abstract class DialogCards implements AbsCloseableDialog {

    private final AbsTabbedPane jt;
    private WindowCards main;
    private final AbsDialog cardDialog;
    protected DialogCards(AbstractProgramInfos _frameFactory) {
        cardDialog = _frameFactory.getFrameFactory().newDialog(this);
        jt = _frameFactory.getCompoFactory().newAbsTabbedPane();
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
    @Override
    public void closeWindow() {
        cardDialog.closeWindow();
        cardDialog.getPane().removeAll();
    }
    AbsTabbedPane getJt() {
        return jt;
    }
}
