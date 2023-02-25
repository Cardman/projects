package code.vi.prot.impl.gui;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public final class TextField extends TxtComponent implements AbsTextField {

    private final JTextField field;

    public TextField() {
        field = new JTextField();
    }
    public TextField(int _nbCols) {
        field = new JTextField(_nbCols);
    }
    public TextField(String _txt) {
        field = new JTextField(_txt);
    }
    public TextField(String _txt,int _nbCols) {
        field = new JTextField(_txt,_nbCols);
    }

    public void addActionListener(AbsActionListener _l) {
        field.addActionListener(new WrActionListener(_l));
    }

    @Override
    public void addActionListener(AbsAdvActionListener _list) {
        field.addActionListener(new WrAdvActionListener(_list));
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
