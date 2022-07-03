package code.gui;

public interface AbsOtherDialog extends WithListener {
    void setJMenuBar(AbsMenuBar _menuBar);

    void pack();

    void setModal(boolean _aTrue);

    AbsPanel getContentPane();
    boolean isModal();
}
