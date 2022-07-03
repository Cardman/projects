package code.gui;

import code.gui.images.MetaPoint;

public interface AbsTextArea extends AbsCustComponent {
    void insert(String _text, int _offset);
    void forceInsert(String _str, int _pos);

    void replaceRange(String _text, int _start, int _end);
    void forceReplaceRange(String _str, int _start, int _end);

    void replaceSelection(String _text);

    void append(String _text);

    boolean isEnabled();

    void setEnabled(boolean _value);

    void setText(String _text);

    String getText();

    String getSelectedText();

    void setSelectionStart(int _start);

    void setSelectionEnd(int _end);

    void setTabSize(int _tabSize);

    int getTabSize();

    void select(int _start, int _end);

    void selectAll();

    void setEditable(boolean _value);

    int viewToModel(MetaPoint _point);
}
