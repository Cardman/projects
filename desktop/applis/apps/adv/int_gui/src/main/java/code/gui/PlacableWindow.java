package code.gui;

public interface PlacableWindow {


    void setLocationRelativeTo(AbsCustComponent _c);

    void setLocationRelativeTo(AbsOtherDialog _c);
    void setLocationRelativeTo(AbsOtherFrame _c);
    void setLocationRelativeToNull();
}
