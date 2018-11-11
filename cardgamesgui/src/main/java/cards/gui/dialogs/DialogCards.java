package cards.gui.dialogs;
import cards.gui.MainWindow;
import code.gui.Dialog;
import code.gui.TabbedPane;
/**
    */

abstract class DialogCards extends Dialog {

    private TabbedPane jt=new TabbedPane();
    private MainWindow main;
    protected DialogCards() {
    }
    public void setMain(MainWindow _main) {
        main = _main;
    }
    public MainWindow getMain() {
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
