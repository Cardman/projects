package cards.gui.dialogs;
import cards.gui.WindowCards;
import code.gui.Dialog;
import code.gui.TabbedPane;
/**
    */

abstract class DialogCards extends Dialog {

    private TabbedPane jt=new TabbedPane();
    private WindowCards main;
    protected DialogCards() {
    }
    public void setMain(WindowCards _main) {
        main = _main;
    }
    public WindowCards getMain() {
        return main;
    }
    @Override
    public void closeWindow() {
        super.closeWindow();
        getPane().removeAll();
    }
    TabbedPane getJt() {
        return jt;
    }
}
