package code.sys.impl.gui;

import code.gui.AbsTextArea;
import code.gui.FrameUtil;

import javax.swing.*;
import java.awt.*;

public final class TextArea extends CustComponent implements AbsTextArea {

    private JTextArea textArea;

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

    public void setTabSize(int _size) {
        textArea.setTabSize(_size);
    }

    public int getTabSize() {
        return textArea.getTabSize();
    }

    public void setLineWrap(boolean _wrap) {
        textArea.setLineWrap(_wrap);
    }

    public boolean getLineWrap() {
        return textArea.getLineWrap();
    }

    public void setWrapStyleWord(boolean _word) {
        textArea.setWrapStyleWord(_word);
    }

    public boolean getWrapStyleWord() {
        return textArea.getWrapStyleWord();
    }

    public int getLineCount() {
        return textArea.getLineCount();
    }

    public void insert(String _str, int _pos) {
        FrameUtil.ins(this,_str, _pos);
    }

    public void forceInsert(String _str, int _pos) {
        textArea.insert(_str, _pos);
    }

    public void append(String _str) {
        textArea.append(_str);
    }

    public void replaceRange(String _str, int _start, int _end) {
        FrameUtil.replRange(this, _str, _start, _end);
    }

    public void forceReplaceRange(String _str, int _start, int _end) {
        textArea.replaceRange(_str, _start, _end);
    }

    public int getRows() {
        return textArea.getRows();
    }

    public void setRows(int _rows) {
        textArea.setRows(_rows);
    }

    public int getColumns() {
        return textArea.getColumns();
    }

    public void setColumns(int _columns) {
        textArea.setColumns(_columns);
    }

    public boolean getScrollableTracksViewportWidth() {
        return textArea.getScrollableTracksViewportWidth();
    }

    /*public void addCaretListener(CaretListener _listener) {
        textArea.addCaretListener(_listener);
    }

    public void removeCaretListener(CaretListener _listener) {
        textArea.removeCaretListener(_listener);
    }

    public CaretListener[] getCaretListeners() {
        return textArea.getCaretListeners();
    }*/

    public void setDragEnabled(boolean _b) {
        textArea.setDragEnabled(_b);
    }

    public boolean getDragEnabled() {
        return textArea.getDragEnabled();
    }

    public Color getCaretColor() {
        return textArea.getCaretColor();
    }

    public void setCaretColor(Color _c) {
        textArea.setCaretColor(_c);
    }

    public Color getSelectionColor() {
        return textArea.getSelectionColor();
    }

    public void setSelectionColor(Color _c) {
        textArea.setSelectionColor(_c);
    }

    public Color getSelectedTextColor() {
        return textArea.getSelectedTextColor();
    }

    public void setSelectedTextColor(Color _c) {
        textArea.setSelectedTextColor(_c);
    }

    public Color getDisabledTextColor() {
        return textArea.getDisabledTextColor();
    }

    public void setDisabledTextColor(Color _c) {
        textArea.setDisabledTextColor(_c);
    }

    public void replaceSelection(String _content) {
        textArea.replaceSelection(_content);
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

    public void moveCaretPosition(int _pos) {
        textArea.moveCaretPosition(_pos);
    }

    public void setFocusAccelerator(char _aKey) {
        textArea.setFocusAccelerator(_aKey);
    }

    public char getFocusAccelerator() {
        return textArea.getFocusAccelerator();
    }

    public void setCaretPosition(int _position) {
        textArea.setCaretPosition(_position);
    }

    public void setText(String _t) {
        textArea.setText(_t);
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

    public void setEditable(boolean _b) {
        textArea.setEditable(_b);
    }

    public void setSelectionStart(int _selectionStart) {
        textArea.setSelectionStart(_selectionStart);
    }

    public void setSelectionEnd(int _selectionEnd) {
        textArea.setSelectionEnd(_selectionEnd);
    }

    public void select(int _selectionStart, int _selectionEnd) {
        textArea.select(_selectionStart, _selectionEnd);
    }

    public void selectAll() {
        textArea.selectAll();
    }

    public boolean getScrollableTracksViewportHeight() {
        return textArea.getScrollableTracksViewportHeight();
    }

    public void setInheritsPopupMenu(boolean _value) {
        textArea.setInheritsPopupMenu(_value);
    }

    public boolean getInheritsPopupMenu() {
        return textArea.getInheritsPopupMenu();
    }

    public boolean isPaintingTile() {
        return textArea.isPaintingTile();
    }

    public boolean isPaintingForPrint() {
        return textArea.isPaintingForPrint();
    }

    public void setRequestFocusEnabled(boolean _requestFocusEnabled) {
        textArea.setRequestFocusEnabled(_requestFocusEnabled);
    }

    public boolean isRequestFocusEnabled() {
        return textArea.isRequestFocusEnabled();
    }

    public boolean requestFocus(boolean _temporary) {
        return textArea.requestFocus(_temporary);
    }

    public boolean requestFocusInWindow() {
        return textArea.requestFocusInWindow();
    }

    public void grabFocus() {
        textArea.grabFocus();
    }

    public void setVerifyInputWhenFocusTarget(boolean _verifyInputWhenFocusTarget) {
        textArea.setVerifyInputWhenFocusTarget(_verifyInputWhenFocusTarget);
    }

    public boolean getVerifyInputWhenFocusTarget() {
        return textArea.getVerifyInputWhenFocusTarget();
    }

    public boolean contains(int _x, int _y) {
        return textArea.contains(_x, _y);
    }

    public void resetKeyboardActions() {
        textArea.resetKeyboardActions();
    }

    public int getBaseline(int _width, int _height) {
        return textArea.getBaseline(_width, _height);
    }

    public void setEnabled(boolean _enabled) {
        textArea.setEnabled(_enabled);
    }

    public int getX() {
        return textArea.getX();
    }

    public int getY() {
        return textArea.getY();
    }

    @Override
    public JComponent getNatComponent() {
        return textArea;
    }

    public int getCaretPosition() {
        return textArea.getCaretPosition();
    }

    public boolean isEnabled() {
        return textArea.isEnabled();
    }
}
