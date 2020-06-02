package code.gui;

import javax.swing.*;
import javax.swing.event.CaretListener;
import javax.swing.text.*;
import java.awt.*;

public final class TextArea extends CustComponent {

    private JTextArea textArea;
    private PopupMenu popupMenu;
    public TextArea() {
        textArea = new JTextArea();
    }
    public TextArea(int _r,int _c) {
        textArea = new JTextArea(_r,_c);
    }
    public TextArea(String _txt) {
        textArea = new JTextArea(_txt);
    }
    public TextArea(String _txt,int _r,int _c) {
        textArea = new JTextArea(_txt,_r,_c);
    }

    public void setTabSize(int size) {
        textArea.setTabSize(size);
    }

    public int getTabSize() {
        return textArea.getTabSize();
    }

    public void setLineWrap(boolean wrap) {
        textArea.setLineWrap(wrap);
    }

    public boolean getLineWrap() {
        return textArea.getLineWrap();
    }

    public void setWrapStyleWord(boolean word) {
        textArea.setWrapStyleWord(word);
    }

    public boolean getWrapStyleWord() {
        return textArea.getWrapStyleWord();
    }

    public int getLineCount() {
        return textArea.getLineCount();
    }

    public void insert(String str, int pos) {
        if (pos < 0) {
            return;
        }
        textArea.insert(str, pos);
    }

    public void append(String str) {
        textArea.append(str);
    }

    public void replaceRange(String str, int start, int end) {
        if (start < 0) {
            return;
        }
        if (end < start) {
            return;
        }
        textArea.replaceRange(str, start, end);
    }

    public int getRows() {
        return textArea.getRows();
    }

    public void setRows(int rows) {
        textArea.setRows(rows);
    }

    public int getColumns() {
        return textArea.getColumns();
    }

    public void setColumns(int columns) {
        textArea.setColumns(columns);
    }

    public boolean getScrollableTracksViewportWidth() {
        return textArea.getScrollableTracksViewportWidth();
    }

    public void addCaretListener(CaretListener listener) {
        textArea.addCaretListener(listener);
    }

    public void removeCaretListener(CaretListener listener) {
        textArea.removeCaretListener(listener);
    }

    public CaretListener[] getCaretListeners() {
        return textArea.getCaretListeners();
    }

    public Document getDocument() {
        return textArea.getDocument();
    }

    public void setDragEnabled(boolean b) {
        textArea.setDragEnabled(b);
    }

    public boolean getDragEnabled() {
        return textArea.getDragEnabled();
    }

    public Color getCaretColor() {
        return textArea.getCaretColor();
    }

    public void setCaretColor(Color c) {
        textArea.setCaretColor(c);
    }

    public Color getSelectionColor() {
        return textArea.getSelectionColor();
    }

    public void setSelectionColor(Color c) {
        textArea.setSelectionColor(c);
    }

    public Color getSelectedTextColor() {
        return textArea.getSelectedTextColor();
    }

    public void setSelectedTextColor(Color c) {
        textArea.setSelectedTextColor(c);
    }

    public Color getDisabledTextColor() {
        return textArea.getDisabledTextColor();
    }

    public void setDisabledTextColor(Color c) {
        textArea.setDisabledTextColor(c);
    }

    public void replaceSelection(String content) {
        textArea.replaceSelection(content);
    }

    public void cut() {
        textArea.cut();
    }

    public void copy() {
        textArea.copy();
    }

    public void paste() {
        textArea.paste();
    }

    public void moveCaretPosition(int pos) {
        textArea.moveCaretPosition(pos);
    }

    public void setFocusAccelerator(char aKey) {
        textArea.setFocusAccelerator(aKey);
    }

    public char getFocusAccelerator() {
        return textArea.getFocusAccelerator();
    }

    public void setCaretPosition(int position) {
        textArea.setCaretPosition(position);
    }

    public void setText(String t) {
        textArea.setText(t);
    }

    public String getText() {
        return textArea.getText();
    }

    public String getSelectedText() {
        return textArea.getSelectedText();
    }

    public boolean isEditable() {
        return textArea.isEditable();
    }

    public void setEditable(boolean b) {
        textArea.setEditable(b);
    }

    public void setSelectionStart(int selectionStart) {
        textArea.setSelectionStart(selectionStart);
    }

    public void setSelectionEnd(int selectionEnd) {
        textArea.setSelectionEnd(selectionEnd);
    }

    public void select(int selectionStart, int selectionEnd) {
        textArea.select(selectionStart, selectionEnd);
    }

    public void selectAll() {
        textArea.selectAll();
    }

    public boolean getScrollableTracksViewportHeight() {
        return textArea.getScrollableTracksViewportHeight();
    }

    public void setInheritsPopupMenu(boolean value) {
        textArea.setInheritsPopupMenu(value);
    }

    public boolean getInheritsPopupMenu() {
        return textArea.getInheritsPopupMenu();
    }

    public void setComponentPopupMenu(PopupMenu _popup) {
        textArea.setComponentPopupMenu(_popup.getPopupMenu());
        popupMenu = _popup;
    }

    public PopupMenu getComponentPopupMenu() {
        return popupMenu;
    }

    public boolean isPaintingTile() {
        return textArea.isPaintingTile();
    }

    public boolean isPaintingForPrint() {
        return textArea.isPaintingForPrint();
    }

    public void setRequestFocusEnabled(boolean requestFocusEnabled) {
        textArea.setRequestFocusEnabled(requestFocusEnabled);
    }

    public boolean isRequestFocusEnabled() {
        return textArea.isRequestFocusEnabled();
    }

    public boolean requestFocus(boolean temporary) {
        return textArea.requestFocus(temporary);
    }

    public boolean requestFocusInWindow() {
        return textArea.requestFocusInWindow();
    }

    public void grabFocus() {
        textArea.grabFocus();
    }

    public void setVerifyInputWhenFocusTarget(boolean verifyInputWhenFocusTarget) {
        textArea.setVerifyInputWhenFocusTarget(verifyInputWhenFocusTarget);
    }

    public boolean getVerifyInputWhenFocusTarget() {
        return textArea.getVerifyInputWhenFocusTarget();
    }

    public boolean contains(int x, int y) {
        return textArea.contains(x, y);
    }

    public void resetKeyboardActions() {
        textArea.resetKeyboardActions();
    }

    public void setInputMap(int condition, InputMap map) {
        textArea.setInputMap(condition, map);
    }

    public InputMap getInputMap(int condition) {
        return textArea.getInputMap(condition);
    }

    public ActionMap getActionMap() {
        return textArea.getActionMap();
    }
    public int getBaseline(int width, int height) {
        return textArea.getBaseline(width, height);
    }

    public void setEnabled(boolean enabled) {
        textArea.setEnabled(enabled);
    }

    public int getX() {
        return textArea.getX();
    }

    public int getY() {
        return textArea.getY();
    }

    @Override
    protected JComponent getComponent() {
        return textArea;
    }

    public int getCaretPosition() {
        return textArea.getCaretPosition();
    }

    public boolean isEnabled() {
        return textArea.isEnabled();
    }
}
