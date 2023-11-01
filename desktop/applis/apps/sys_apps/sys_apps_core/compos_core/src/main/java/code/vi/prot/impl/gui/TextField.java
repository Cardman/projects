package code.vi.prot.impl.gui;

import code.gui.AbsTextField;
import code.gui.events.AbsAdvActionListener;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public final class TextField extends TxtComponent implements AbsTextField {

    private final JTextField field;
    private final IdMap<AbsAdvActionListener, WrAdvActionListener> actionsField = new IdMap<AbsAdvActionListener, WrAdvActionListener>();

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

    @Override
    public void addActionListener(AbsAdvActionListener _list) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_list);
        actionsField.addEntry(_list,wr_);
        field.addActionListener(wr_);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _list) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_list);
        actionsField.addEntry(_list,wr_);
    }

    @Override
    public void removeActionListener(AbsAdvActionListener _list) {
        WrAdvActionListener wr_ = actionsField.getVal(_list);
        field.removeActionListener(wr_);
        actionsField.removeKey(_list);
    }

    @Override
    public void removeActionListenerMap(AbsAdvActionListener _list) {
        actionsField.removeKey(_list);
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
