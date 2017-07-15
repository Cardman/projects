package cards.gui.dialogs;
import javax.swing.JTabbedPane;

import code.gui.Dialog;
/**
    */

abstract class DialogCards extends Dialog {

    private JTabbedPane jt=new JTabbedPane();
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
        getContentPane().removeAll();
    }
    JTabbedPane getJt() {
        return jt;
    }
}
