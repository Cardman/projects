package cards.gui.dialogs;
import code.gui.Dialog;
import code.gui.TabbedPane;
/**
    */

abstract class DialogCards extends Dialog {

    private TabbedPane jt=new TabbedPane();
    protected DialogCards() {
    }
//    DialogCards(String _titre,MainWindow _fenetre,boolean _mod)
//    {
//        //super(_fenetre,_titre,_mod);
//        setDialogIcon(_fenetre);
//        setTitle(_titre);
//        setModal(_mod);
//        setLocationRelativeTo(_fenetre);
//        setResizable(false);
//    }
//    DialogCards(MainWindow _fenetre,boolean _mod)
//    {
//        //super(_fenetre,_mod);
//        setDialogIcon(_fenetre);
//        setModal(_mod);
//        setLocationRelativeTo(_fenetre);
//        setResizable(false);
//    }
    @Override
    public void closeWindow() {
        super.closeWindow();
        getPane().removeAll();
    }
    TabbedPane getJt() {
        return jt;
    }
}
