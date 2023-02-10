package code.gui;

public interface AbsTextArea extends AbsTxtComponent {
    void insert(String _text, int _offset);

    void replaceRange(String _text, int _start, int _end);

    void append(String _text);

    void setTabSize(int _tabSize);

    int getTabSize();

    void setEditable(boolean _value);

}
