package code.vi.prot.impl.gui;

import code.gui.AbsTextArea;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public final class TextArea extends TxtComponent implements AbsTextArea {

    private final JTextArea area;

    public TextArea() {
        area = new JTextArea();
//        area.addFocusListener(new FocusKeepEvent(area));
    }
    public TextArea(int _r,int _c) {
        area = new JTextArea(_r,_c);
//        area.addFocusListener(new FocusKeepEvent(area));
    }
    public TextArea(String _txt) {
        area = new JTextArea(_txt);
//        area.addFocusListener(new FocusKeepEvent(area));
    }
    public TextArea(String _txt,int _r,int _c) {
        area = new JTextArea(_txt,_r,_c);
//        area.addFocusListener(new FocusKeepEvent(area));
    }

    public void setTabSize(int _size) {
        area.setTabSize(_size);
    }

    public int getTabSize() {
        return area.getTabSize();
    }

    public void setLineWrap(boolean _wrap) {
        area.setLineWrap(_wrap);
    }

    public boolean getLineWrap() {
        return area.getLineWrap();
    }

    public void setWrapStyleWord(boolean _word) {
        area.setWrapStyleWord(_word);
    }

    public boolean getWrapStyleWord() {
        return area.getWrapStyleWord();
    }

    public int getLineCount() {
        return area.getLineCount();
    }

    public void insert(String _str, int _pos) {
        area.insert(_str, _pos);
    }

    public void append(String _str) {
        area.append(_str);
    }

    public void replaceRange(String _str, int _start, int _end) {
        area.replaceRange(_str, _start, _end);
    }

    public int getRows() {
        return area.getRows();
    }

    public void setRows(int _rows) {
        area.setRows(_rows);
    }

    public int getColumns() {
        return area.getColumns();
    }

    public void setColumns(int _columns) {
        area.setColumns(_columns);
    }

    public boolean getScrollableTracksViewportWidth() {
        return area.getScrollableTracksViewportWidth();
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
        area.setDragEnabled(_b);
    }

    public boolean getDragEnabled() {
        return area.getDragEnabled();
    }

    public Color getDisabledTextColor() {
        return area.getDisabledTextColor();
    }

    public void setDisabledTextColor(Color _c) {
        area.setDisabledTextColor(_c);
    }


    public void cut() {
        area.cut();
    }

    public void copy() {
        area.copy();
    }

    public void paste() {
        area.paste();
    }


    public void setFocusAccelerator(char _aKey) {
        area.setFocusAccelerator(_aKey);
    }

    public char getFocusAccelerator() {
        return area.getFocusAccelerator();
    }


    public boolean getScrollableTracksViewportHeight() {
        return area.getScrollableTracksViewportHeight();
    }

    public void setInheritsPopupMenu(boolean _value) {
        area.setInheritsPopupMenu(_value);
    }

    public boolean getInheritsPopupMenu() {
        return area.getInheritsPopupMenu();
    }

    public boolean isPaintingTile() {
        return area.isPaintingTile();
    }

    public boolean isPaintingForPrint() {
        return area.isPaintingForPrint();
    }

    public void setRequestFocusEnabled(boolean _requestFocusEnabled) {
        area.setRequestFocusEnabled(_requestFocusEnabled);
    }

    public boolean isRequestFocusEnabled() {
        return area.isRequestFocusEnabled();
    }

    public boolean requestFocus(boolean _temporary) {
        return area.requestFocus(_temporary);
    }

    public boolean requestFocusInWindow() {
        return area.requestFocusInWindow();
    }

    public void grabFocus() {
        area.grabFocus();
    }

    public void setVerifyInputWhenFocusTarget(boolean _verifyInputWhenFocusTarget) {
        area.setVerifyInputWhenFocusTarget(_verifyInputWhenFocusTarget);
    }

    public boolean getVerifyInputWhenFocusTarget() {
        return area.getVerifyInputWhenFocusTarget();
    }

    public boolean contains(int _x, int _y) {
        return area.contains(_x, _y);
    }

    public void resetKeyboardActions() {
        area.resetKeyboardActions();
    }

    public int getBaseline(int _width, int _height) {
        return area.getBaseline(_width, _height);
    }

    public int getX() {
        return area.getX();
    }

    public int getY() {
        return area.getY();
    }

    @Override
    public JComponent getNatComponent() {
        return getTextComponent();
    }

    @Override
    public JTextComponent getTextComponent() {
        return area;
    }


}
