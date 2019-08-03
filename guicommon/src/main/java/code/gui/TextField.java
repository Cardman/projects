package code.gui;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

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

    public void setDocument(Document doc) {
        textField.setDocument(doc);
    }

    public void setFont(Font f) {
        textField.setFont(f);
    }

    public void addActionListener(ActionListener l) {
        textField.addActionListener(l);
    }

    public Document getDocument() {
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

    public FontMetrics getFontMetrics(Font font) {
        return textField.getFontMetrics(font);
    }

    public boolean contains(int x, int y) {
        return textField.contains(x, y);
    }

    public Border getBorder() {
        return textField.getBorder();
    }

    public void setVisible(boolean aFlag) {
        textField.setVisible(aFlag);
    }

    public void setEnabled(boolean enabled) {
        textField.setEnabled(enabled);
    }

    public void setForeground(Color fg) {
        textField.setForeground(fg);
    }

    public void setBackground(Color bg) {
        textField.setBackground(bg);
    }

    public int getX() {
        return textField.getX();
    }

    public int getY() {
        return textField.getY();
    }

    public void setOpaque(boolean isOpaque) {
        textField.setOpaque(isOpaque);
    }

    public void revalidate() {
        textField.revalidate();
    }

    public void validate() {
        textField.validate();
    }

    public void setLocation(int x, int y) {
        textField.setLocation(x, y);
    }

    public void setLocation(Point p) {
        textField.setLocation(p);
    }

    public Cursor getCursor() {
        return textField.getCursor();
    }

    public boolean contains(Point p) {
        return textField.contains(p);
    }

    public void addMouseListener(MouseListener l) {
        textField.addMouseListener(l);
    }

    public void add(PopupMenu popup) {
        textField.add(popup.getComponent());
    }


    @Override
    public JComponent getComponent() {
        return textField;
    }
}
