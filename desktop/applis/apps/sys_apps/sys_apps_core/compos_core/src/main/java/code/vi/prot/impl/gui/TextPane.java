package code.vi.prot.impl.gui;

import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;
import code.gui.images.MetaFont;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

public final class TextPane extends TxtComponent implements AbsTextPane {
    private final JTextPane pane = new JTextPane();
    public TextPane() {
        pane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
        pane.addFocusListener(new FocusKeepEvent(pane));
    }

    @Override
    public void clearCharacterAttributes(int _begin, int _length) {
        setCharacterAttributes(_begin,_length,new DefAttrSet(),true);
    }

    @Override
    public void setCharacterAttributes(int _begin, int _length, AbsAttrSet _attrs, boolean _replace) {
        pane.getStyledDocument().setCharacterAttributes(_begin,_length,((DefAttrSet)_attrs).getAttributes(),_replace);
    }

    @Override
    public void setParagraphAttributes(AbsAttrSet _attrs) {
        pane.getStyledDocument().setParagraphAttributes(0, pane.getDocument().getLength(), ((DefAttrSet)_attrs).getAttributes(), false);
    }

    @Override
    public JComponent getNatComponent() {
        return getTextComponent();
    }

    @Override
    public JTextComponent getTextComponent() {
        return pane;
    }

    @Override
    public void setFontSize(int _size) {
        MetaFont m_ = getMetaFont();
        setFont(new MetaFont(m_.getFontFamily(),m_.getFont(),_size));
    }
}
