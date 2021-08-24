package code.gui;

import code.gui.events.AbsActionListener;
import code.gui.events.WrActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.Document;

public final class TextField extends CustComponent {

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

    void setDocument(Document _doc) {
        textField.setDocument(_doc);
    }

    public void addActionListener(AbsActionListener _l) {
        textField.addActionListener(new WrActionListener(_l));
    }

    Document getDocument() {
        return textField.getDocument();
    }

    public void moveCaretPosition(int _pos) {
        textField.moveCaretPosition(_pos);
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

    public boolean contains(int _x, int _y) {
        return textField.contains(_x, _y);
    }

    public void setEnabled(boolean _enabled) {
        textField.setEnabled(_enabled);
    }

    public int getX() {
        return textField.getX();
    }

    public int getY() {
        return textField.getY();
    }

    public void add(PopupMenu _popup) {
        textField.add(_popup.getNatComponent());
        textField.setComponentPopupMenu(_popup.getPopupMenu());
    }


    @Override
    protected JComponent getNatComponent() {
        return textField;
    }

    public boolean isEnabled() {
        return textField.isEnabled();
    }
}
