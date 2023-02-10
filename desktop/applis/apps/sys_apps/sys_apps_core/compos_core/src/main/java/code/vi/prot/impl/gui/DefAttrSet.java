package code.vi.prot.impl.gui;

import code.gui.AbsAttrSet;
import code.vi.prot.impl.DefImage;

import javax.swing.text.*;

public final class DefAttrSet implements AbsAttrSet {
    private final SimpleAttributeSet attributes = new SimpleAttributeSet();
    @Override
    public void addBackground(int _color) {
        StyleConstants.setBackground(attributes,DefImage.fullColor(_color));
    }

    @Override
    public void addForeground(int _color) {
        StyleConstants.setForeground(attributes,DefImage.fullColor(_color));
    }

    @Override
    public void addFontSize(int _color) {
        StyleConstants.setFontSize(attributes,_color);
    }

    public AttributeSet getAttributes() {
        return attributes;
    }
}
