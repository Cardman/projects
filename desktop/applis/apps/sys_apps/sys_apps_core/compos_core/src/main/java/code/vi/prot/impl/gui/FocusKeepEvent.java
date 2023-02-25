package code.vi.prot.impl.gui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class FocusKeepEvent implements FocusListener {
    private final JTextComponent component;

    public FocusKeepEvent(JTextComponent _c) {
        this.component = _c;
    }

    @Override
    public void focusGained(FocusEvent e) {
        component.getCaret().setVisible(true);
        component.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    public void focusLost(FocusEvent e) {
        component.getCaret().setVisible(true);
        component.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
