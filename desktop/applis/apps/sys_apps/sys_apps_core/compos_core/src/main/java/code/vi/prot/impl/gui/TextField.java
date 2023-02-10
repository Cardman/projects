package code.vi.prot.impl.gui;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsAutoCompleteListener;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.vi.prot.impl.gui.events.WrAdvActionListener;
import code.vi.prot.impl.gui.events.WrAutoCompleteListener;

import javax.swing.JComponent;
import javax.swing.JTextField;
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

    public void addAutoComplete(AbsAutoCompleteListener _auto){
        WrAutoCompleteListener wr_ = new WrAutoCompleteListener(_auto);
        field.getDocument().addDocumentListener(wr_);
    }

    public void setEditable(boolean _b) {
        field.setEditable(_b);
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
