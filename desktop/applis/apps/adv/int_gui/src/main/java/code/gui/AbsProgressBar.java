package code.gui;

public interface AbsProgressBar extends AbsCustComponent {
    boolean isHorizontal();

    int getValue();

    int getMinimum();

    int getMaximum();

    void setValue(int _value);

    void setMinimum(int _value);

    void setMaximum(int _value);

    void setHorizontal(boolean _value);
    void setHorizontal();
    void setVertical();
}
