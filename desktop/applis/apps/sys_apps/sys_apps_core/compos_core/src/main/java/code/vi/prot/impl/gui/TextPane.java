package code.vi.prot.impl.gui;

import code.gui.AbsAttrSet;
import code.gui.AbsTextPane;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaFont;
import code.util.StringMap;
import code.vi.prot.impl.gui.events.WrAbstractAction;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

public final class TextPane extends TxtComponent implements AbsTextPane {
    private final JTextPane pane = new JTextPane();
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();
    public TextPane() {
        pane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
    }

    @Override
    public void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b) {
        WrAbstractAction w_ = (WrAbstractAction)_action;
        pane.getActionMap().put(_a+","+_b,w_);
        pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(_a,_b),_a+","+_b);
        actions.put(_a+","+_b,w_);
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
    public StringMap<AbsEnabledAction> getActionsMap() {
        return actions;
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
