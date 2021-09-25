package code.vi.sys.impl.gui;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAutoCompleteListener;
import code.vi.sys.impl.gui.events.WrActionListener;
import code.vi.sys.impl.gui.events.WrAutoCompleteListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

public final class TextField extends CustComponent implements AbsTextField {

    private final JTextField field;

    public TextField() {
        field = new JTextField();
    }
    public TextField(int _nbCols) {
        field = new JTextField(_nbCols);
    }
    public TextField(String _txt) {
        field = new JTextField(_txt);
    }
    public TextField(String _txt,int _nbCols) {
        field = new JTextField(_txt,_nbCols);
    }

    public void addActionListener(AbsActionListener _l) {
        field.addActionListener(new WrActionListener(_l));
    }

    public void addAutoComplete(AbsAutoCompleteListener _auto){
        WrAutoCompleteListener wr_ = new WrAutoCompleteListener(_auto);
        field.addFocusListener(wr_);
        field.getDocument().addDocumentListener(wr_);
    }

    public void setCaretPosition(int _position) {
        field.setCaretPosition(_position);
    }

    public void setText(String _t) {
        field.setText(_t);
    }

    public String getText() {
        return field.getText();
    }

    public void setEditable(boolean _b) {
        field.setEditable(_b);
    }

    public void select(int _selectionStart, int _selectionEnd) {
        field.select(_selectionStart, _selectionEnd);
    }

    public void setEnabled(boolean _enabled) {
        field.setEnabled(_enabled);
    }


    @Override
    public JComponent getNatComponent() {
        return field;
    }

    public boolean isEnabled() {
        return field.isEnabled();
    }
}
