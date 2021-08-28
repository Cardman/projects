package code.gui;

public interface AbsOtherFrame extends WithListener {
    void setJMenuBar(AbsMenuBar _menuBar);

    boolean isMainFrame();

    void setMainFrame(boolean _b);

    void setDefaultCloseOperation(int _doNothingOnClose);
}
