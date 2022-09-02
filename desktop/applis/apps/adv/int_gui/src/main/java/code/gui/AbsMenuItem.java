package code.gui;

public interface AbsMenuItem extends EnabledMenu,AbsPlainButton {

    void setAccelerator(char _a);

    void setAccelerator(String _a);

    void setAccelerator(int _a, int _b);

}
