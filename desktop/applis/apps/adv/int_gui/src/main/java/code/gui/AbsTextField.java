package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsAutoCompleteListener;

public interface AbsTextField extends AbsCustComponent {
    void setText(String _text);

    String getText();

    boolean isEnabled();

    void setEnabled(boolean _value);

    void addActionListener(AbsActionListener _list);

    void addActionListener(AbsAdvActionListener _list);

    void addAutoComplete(AbsAutoCompleteListener _autoCompleteDocument);

    void setCaretPosition(int _offset);

    void setEditable(boolean _value);
}
