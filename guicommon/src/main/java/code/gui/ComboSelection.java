package code.gui;

import javax.swing.JPopupMenu;

public final class ComboSelection extends ListSelection {

    private PopupMenu popup;

    private GraphicCombo grList;

    public ComboSelection(PopupMenu _popup, GraphicCombo _grList) {
        popup = _popup;
        grList = _grList;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        popup.setVisible(false);
        grList.selectItem(_e.getFirstIndex());
    }

}
