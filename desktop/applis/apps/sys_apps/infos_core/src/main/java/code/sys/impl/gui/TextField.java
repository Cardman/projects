package code.sys.impl.gui;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAutoCompleteListener;
import code.sys.impl.gui.events.WrActionListener;
import code.sys.impl.gui.events.WrAutoCompleteListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

public final class TextField extends CustComponent implements AbsTextField {

    private JTextField textField;

    public TextField() {
        textField = new JTextField();
    }
    public TextField(int _nbCols) {
        textField = new JTextField(_nbCols);
    }
    public TextField(String _txt) {
        textField = new JTextField(_txt);
    }
    public TextField(String _txt,int _nbCols) {
        textField = new JTextField(_txt,_nbCols);
    }

    public void addActionListener(AbsActionListener _l) {
        textField.addActionListener(new WrActionListener(_l));
    }

    public void addAutoComplete(AbsAutoCompleteListener _auto){
        WrAutoCompleteListener wr_ = new WrAutoCompleteListener(_auto);
        textField.addFocusListener(wr_);
        textField.getDocument().addDocumentListener(wr_);
    }

    public void setCaretPosition(int _position) {
        textField.setCaretPosition(_position);
    }

    public void setText(String _t) {
        textField.setText(_t);
    }

    public String getText() {
        return textField.getText();
    }

    public void setEditable(boolean _b) {
        textField.setEditable(_b);
    }

    public void select(int _selectionStart, int _selectionEnd) {
        textField.select(_selectionStart, _selectionEnd);
    }

    public void setEnabled(boolean _enabled) {
        textField.setEnabled(_enabled);
    }


    @Override
    public JComponent getNatComponent() {
        return textField;
    }

    public boolean isEnabled() {
        return textField.isEnabled();
    }
}
