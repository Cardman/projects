package code.gui;

import code.gui.events.AbsAutoCompleteListener;
import code.gui.events.AbsCaretListener;
import code.gui.images.MetaPoint;
import code.gui.images.MetaRect;
import code.util.CustList;

public interface AbsTxtComponent extends AbsCustComponent{

    void moveCaretPosition(int _pos);
    int getSelectionStart();
    int getSelectionEnd();
    int getCaretPosition();
    void setCaretPosition(int _position);
    void replaceSelection(String _content);
    int getCaretColor();

    void setCaretColor(int _c);

    int getSelectedTextColor();

    void setSelectedTextColor(int _c);

    int getSelectionColor();

    void setSelectionColor(int _c);

    void setSelectionStart(int _selectionStart);

    void setSelectionEnd(int _selectionEnd);

    void select(int _selectionStart, int _selectionEnd);

    void selectAll();

    void setText(String _t);

    String getText();

    String getSelectedText();
    void setEnabled(boolean _enabled);
    boolean isEditable();
    void setEditable(boolean _enabled);
    void visibleCaret();
    boolean isEnabled();
    int viewToModel(MetaPoint _point);
    MetaRect modelToView(int _index);
    void addCaretListener(AbsCaretListener _listener);
    void removeCaretListener(AbsCaretListener _listener);
    CustList<AbsCaretListener> getCaretListeners();

    void addAutoComplete(AbsAutoCompleteListener _autoCompleteDocument);
}
