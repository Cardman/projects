package code.vi.prot.impl.gui;

import code.gui.AbsTxtComponent;
import code.gui.events.AbsCaretListener;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.gui.events.WrCaretListener;

import javax.swing.text.JTextComponent;
import java.awt.*;

public abstract class TxtComponent extends CustComponent implements AbsTxtComponent {
    private final IdMap<AbsCaretListener, WrCaretListener> mapCaret = new IdMap<AbsCaretListener, WrCaretListener>();
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
        getTextComponent().setText(_t);
    }

    public String getText() {
        return getTextComponent().getText();
    }

    public String getSelectedText() {
        return getTextComponent().getSelectedText();
    }

    public void setEnabled(boolean _enabled) {
        getTextComponent().setEnabled(_enabled);
    }

    public boolean isEnabled() {
        return getTextComponent().isEnabled();
    }

    public int viewToModel(MetaPoint _point) {
        return getTextComponent().viewToModel(new Point(_point.getXcoord(), _point.getYcoord()));
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

    @Override
    public CustList<AbsCaretListener> getCaretListeners() {
        return mapCaret.getKeys();
    }

    public abstract JTextComponent getTextComponent();
}
