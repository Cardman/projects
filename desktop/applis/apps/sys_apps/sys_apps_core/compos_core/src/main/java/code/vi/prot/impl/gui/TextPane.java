package code.vi.prot.impl.gui;

import code.gui.AbsTextPane;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.StringMap;
import code.vi.prot.impl.gui.events.WrAbstractAction;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;

public final class TextPane extends CustComponent implements AbsTextPane {
    private final JTextPane pane = new JTextPane();
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();
    public TextPane() {
        pane.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
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
    public CustList<String> getKeysAction() {
        return actions.getKeys();
    }

    @Override
    public AbsEnabledAction getAction(int _a, int _b) {
        return actions.getVal(_a+","+_b);
    }

    @Override
    public CustList<AbsEnabledAction> getActions() {
        return actions.values();
    }

    @Override
    public JComponent getNatComponent() {
        return pane;
    }

    @Override
    public void setFontSize(int _size) {
        MetaFont m_ = getMetaFont();
        setFont(new MetaFont(m_.getFontFamily(),m_.getFont(),_size));
    }
}
