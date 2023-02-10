package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsAutoCompleteListener;

public interface AbsTextField extends AbsTxtComponent {

    void addActionListener(AbsActionListener _list);

    void addActionListener(AbsAdvActionListener _list);

    void addAutoComplete(AbsAutoCompleteListener _autoCompleteDocument);

    void setEditable(boolean _value);
}
