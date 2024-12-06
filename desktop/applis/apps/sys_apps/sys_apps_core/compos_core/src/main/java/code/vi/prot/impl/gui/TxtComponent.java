package code.vi.prot.impl.gui;

import code.gui.AbsTxtComponent;
import code.gui.GuiConstants;
import code.gui.events.AbsAutoCompleteListener;
import code.gui.events.AbsCaretListener;
import code.gui.images.MetaPoint;
import code.gui.images.MetaRect;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.gui.events.WrAutoCompleteListener;
import code.vi.prot.impl.gui.events.WrCaretListener;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

public abstract class TxtComponent extends CustComponent implements AbsTxtComponent {
    private final IdMap<AbsCaretListener, WrCaretListener> mapCaret = new IdMap<AbsCaretListener, WrCaretListener>();
    private ActionListener enterAction;
    private ActionListener upAction;
    private ActionListener downAction;
    private ActionListener leftAction;
    private ActionListener rightAction;
    protected void buildStdActions() {
        enterAction = getTextComponent().getActionForKeyStroke(KeyStroke.getKeyStroke(GuiConstants.VK_ENTER, 0));
        upAction = getTextComponent().getActionForKeyStroke(KeyStroke.getKeyStroke(GuiConstants.VK_UP, 0));
        downAction = getTextComponent().getActionForKeyStroke(KeyStroke.getKeyStroke(GuiConstants.VK_DOWN, 0));
        leftAction = getTextComponent().getActionForKeyStroke(KeyStroke.getKeyStroke(GuiConstants.VK_LEFT, 0));
        rightAction = getTextComponent().getActionForKeyStroke(KeyStroke.getKeyStroke(GuiConstants.VK_RIGHT, 0));
    }
    public void moveCaretPosition(int _pos) {
        getTextComponent().moveCaretPosition(_pos);
    }
    public int getSelectionStart() {
        return getTextComponent().getSelectionStart();
    }
    public int getSelectionEnd() {
        return getTextComponent().getSelectionEnd();
    }
    public int getCaretPosition() {
        return getTextComponent().getCaretPosition();
    }
    public void setCaretPosition(int _position) {
        getTextComponent().setCaretPosition(_position);
    }

    @Override
    public int enterAction() {
        return action(enterAction, getTextComponent(), 0);
    }

    @Override
    public int upAction() {
        return action(upAction, getTextComponent(), 0);
    }

    @Override
    public int downAction() {
        return action(downAction, getTextComponent(), 0);
    }

    @Override
    public int leftAction() {
        return action(leftAction, getTextComponent(), 0);
    }

    @Override
    public int rightAction() {
        return action(rightAction, getTextComponent(), 0);
    }

    public static int action(ActionListener _act, JTextComponent _compo, int _modifiers) {
        try {
            _act.actionPerformed(new ActionEvent(_compo, ActionEvent.ACTION_PERFORMED, "", EventQueue.getMostRecentEventTime(), _modifiers));
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public void replaceSelection(String _content) {
        getTextComponent().replaceSelection(_content);
    }
    public int getCaretColor() {
        return getTextComponent().getCaretColor().getRGB();
    }

    public void setCaretColor(int _c) {
        getTextComponent().setCaretColor(DefImage.fullColor(_c));
    }

    public int getSelectedTextColor() {
        return getTextComponent().getSelectedTextColor().getRGB();
    }

    public void setSelectedTextColor(int _c) {
        getTextComponent().setSelectedTextColor(DefImage.fullColor(_c));
    }

    public int getSelectionColor() {
        return getTextComponent().getSelectionColor().getRGB();
    }

    public void setSelectionColor(int _c) {
        getTextComponent().setSelectionColor(DefImage.fullColor(_c));
    }

    public void setSelectionStart(int _selectionStart) {
        getTextComponent().setSelectionStart(_selectionStart);
    }

    public void setSelectionEnd(int _selectionEnd) {
        getTextComponent().setSelectionEnd(_selectionEnd);
    }

    public void select(int _selectionStart, int _selectionEnd) {
        getTextComponent().select(_selectionStart, _selectionEnd);
    }

    public void selectAll() {
        getTextComponent().selectAll();
    }

    public void setText(String _t) {
        try {
            AbstractDocument doc_ = (AbstractDocument) getTextComponent().getDocument();
            doc_.replace(0, doc_.getLength(), _t,null);
        } catch (Exception e) {
            getTextComponent().setText(_t);
        }
    }

    public int insert(String _text, int _offset) {
        try {
            AbstractDocument doc_ = (AbstractDocument) getTextComponent().getDocument();
            doc_.insertString(_offset,_text,null);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int remove(int _off, int _len) {
        try {
            AbstractDocument doc_ = (AbstractDocument) getTextComponent().getDocument();
            doc_.remove(_off, _len);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public String getText() {
        try {
            AbstractDocument doc_ = (AbstractDocument) getTextComponent().getDocument();
            return doc_.getText(0, doc_.getLength());
        } catch (Exception e) {
            return getTextComponent().getText();
        }
    }

    public String getSelectedText() {
        return getTextComponent().getSelectedText();
    }

    public int viewToModel(MetaPoint _point) {
        return getTextComponent().viewToModel(new Point(_point.getXcoord(), _point.getYcoord()));
    }

    @Override
    public MetaRect modelToView(int _index) {
        try {
            Rectangle r_ = getTextComponent().modelToView(_index);
            return new MetaRect(r_.x,r_.y,r_.width,r_.height);
        } catch (Exception e) {
            return new MetaRect(0,0,0,0);
        }
    }

    @Override
    public void addCaretListener(AbsCaretListener _listener) {
        WrCaretListener wr_ = new WrCaretListener(_listener);
        getTextComponent().addCaretListener(wr_);
        mapCaret.addEntry(_listener,wr_);
    }

    @Override
    public void removeCaretListener(AbsCaretListener _listener) {
        WrCaretListener wr_ = mapCaret.getVal(_listener);
        getTextComponent().removeCaretListener(wr_);
        mapCaret.removeKey(_listener);
    }

    public void addAutoComplete(AbsAutoCompleteListener _auto){
        WrAutoCompleteListener wr_ = new WrAutoCompleteListener(_auto);
        getTextComponent().getDocument().addDocumentListener(wr_);
    }

    @Override
    public CustList<AbsCaretListener> getCaretListeners() {
        return mapCaret.getKeys();
    }

    @Override
    public boolean isEditable() {
        return getTextComponent().isEditable();
    }

    @Override
    public void setEditable(boolean _ed) {
        getTextComponent().setEditable(_ed);
    }

    public void visibleCaret() {
        getTextComponent().setFocusable(true);
        getTextComponent().getCaret().setVisible(true);
    }
    public abstract JTextComponent getTextComponent();
}
