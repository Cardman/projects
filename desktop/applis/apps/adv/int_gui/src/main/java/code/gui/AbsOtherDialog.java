package code.gui;

public interface AbsOtherDialog extends WithListener {


    void pack();

    void setModal(boolean _aTrue);

    AbsPanel getContentPane();
    boolean isModal();
}
