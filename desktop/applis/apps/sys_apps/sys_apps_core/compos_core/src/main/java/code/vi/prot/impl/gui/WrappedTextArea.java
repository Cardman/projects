package code.vi.prot.impl.gui;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import code.gui.AbsWrappedTextArea;
import code.util.core.StringUtil;

public final class WrappedTextArea extends CustComponent implements AbsWrappedTextArea {

    private final JTextArea textArea;

    public WrappedTextArea(int _rows, int _columns) {
        textArea = new JTextArea(_rows, _columns);
    }

    public void setEditable(boolean _b) {
        textArea.setEditable(_b);
    }

    public void setText(String _t) {
        textArea.setText(StringUtil.wrapContent(_t, textArea.getColumns(), false));
    }

    public void append(String _str) {
        textArea.append(StringUtil.wrapContent(_str, textArea.getColumns(), false));
    }

    @Override
    public JComponent getNatComponent() {
        return textArea;
    }
}
