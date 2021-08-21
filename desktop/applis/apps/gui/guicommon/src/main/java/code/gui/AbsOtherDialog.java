package code.gui;

public interface AbsOtherDialog extends WithListener {
    void setJMenuBar(MenuBar _menuBar);

    void pack();

    void setModal(boolean _aTrue);

    Panel getContentPane();
    boolean isModal();
}
