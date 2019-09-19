package code.gui;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.Document;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;

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

    void setDocument(Document doc) {
        textField.setDocument(doc);
    }

    public void addActionListener(ActionListener l) {
        textField.addActionListener(l);
    }

    Document getDocument() {
        return textField.getDocument();
    }

    public void moveCaretPosition(int pos) {
        textField.moveCaretPosition(pos);
    }

    public void setCaretPosition(int position) {
        textField.setCaretPosition(position);
    }

    public void setText(String t) {
        textField.setText(t);
    }

    public String getText() {
        return textField.getText();
    }

    public void setEditable(boolean b) {
        textField.setEditable(b);
    }

    public void select(int selectionStart, int selectionEnd) {
        textField.select(selectionStart, selectionEnd);
    }

    public boolean contains(int x, int y) {
        return textField.contains(x, y);
    }

    public void setEnabled(boolean enabled) {
        textField.setEnabled(enabled);
    }

    public int getX() {
        return textField.getX();
    }

    public int getY() {
        return textField.getY();
    }

    public Cursor getCursor() {
        return textField.getCursor();
    }

    public void add(PopupMenu popup) {
        textField.add(popup.getComponent());
        textField.setComponentPopupMenu(popup.getPopupMenu());
    }


    @Override
    protected JComponent getComponent() {
        return textField;
    }

    public boolean isEnabled() {
        return textField.isEnabled();
    }
}
