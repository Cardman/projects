package code.gui;

public interface AbsWrappedTextArea extends AbsCustComponent {
    void setText(String _text);

    void append(String _text);

    void setEditable(boolean _value);
}
