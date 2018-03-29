package code.gui;

import javax.swing.JPopupMenu;

public final class ComboSelection extends ListSelection {

    private JPopupMenu popup;

    private GraphicCombo grList;

    public ComboSelection(JPopupMenu _popup, GraphicCombo _grList) {
        popup = _popup;
        grList = _grList;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        popup.setVisible(false);
        grList.selectItem(_e.getFirstIndex());
    }

}
