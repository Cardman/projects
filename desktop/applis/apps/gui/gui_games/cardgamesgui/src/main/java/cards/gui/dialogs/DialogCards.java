package cards.gui.dialogs;
import cards.gui.WindowCards;
import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;
import code.gui.TabbedPane;
import code.gui.initialize.AbsFrameFactory;

/**
    */

abstract class DialogCards implements AbsCloseableDialog {

    private TabbedPane jt=new TabbedPane();
    private WindowCards main;
    private final AbsDialog cardDialog;
    protected DialogCards(AbsFrameFactory _frameFactory) {
        cardDialog = _frameFactory.newDialog(this);
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
    TabbedPane getJt() {
        return jt;
    }
}
