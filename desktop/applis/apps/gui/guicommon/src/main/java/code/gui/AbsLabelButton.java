package code.gui;

public interface AbsLabelButton extends AbsCustComponent,AbsEnabled {
    void setTextDefaultColors(String _text,
                              boolean _enabled);
    String getText();
}
