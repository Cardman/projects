package code.vi.prot.impl.gui;

import code.gui.AbsTextField;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.vi.prot.impl.gui.events.WrAbstractAction;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public final class TextField extends TxtComponent implements AbsTextField {

    private final JTextField field;

    public TextField() {
        field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
//        field.addFocusListener(new FocusKeepEvent(field));
    }
    public TextField(int _nbCols) {
        field = new JTextField(_nbCols);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
//        field.addFocusListener(new FocusKeepEvent(field));
    }
    public TextField(String _txt) {
        field = new JTextField(_txt);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
//        field.addFocusListener(new FocusKeepEvent(field));
    }
    public TextField(String _txt,int _nbCols) {
        field = new JTextField(_txt,_nbCols);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
//        field.addFocusListener(new FocusKeepEvent(field));
    }

    public void addActionListener(AbsActionListener _l) {
        registerKeyboardAction(new WrAbstractAction(new WrActionListener(_l)), GuiConstants.VK_ENTER,0);
    }

    @Override
    public void addActionListener(AbsAdvActionListener _list) {
        registerKeyboardAction(new WrAbstractAction(new WrAdvActionListener(_list)), GuiConstants.VK_ENTER,0);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _list) {
        registerKeyboardActionMap(new WrAbstractAction(new WrAdvActionListener(_list)), GuiConstants.VK_ENTER,0);
    }

    @Override
    public JComponent getNatComponent() {
        return getTextComponent();
    }

    @Override
    public JTextComponent getTextComponent() {
        return field;
    }
}
