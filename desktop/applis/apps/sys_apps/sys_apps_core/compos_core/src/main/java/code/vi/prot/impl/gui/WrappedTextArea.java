package code.vi.prot.impl.gui;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import code.gui.AbsWrappedTextArea;
import code.util.core.StringUtil;

public final class WrappedTextArea extends TxtComponent implements AbsWrappedTextArea {

    private final JTextArea textArea;

    public WrappedTextArea(int _rows, int _columns) {
        textArea = new JTextArea(_rows, _columns);
    }

    @Override
    public void setText(String _t) {
        textArea.setText(StringUtil.wrapContent(_t, textArea.getColumns(), false));
    }

    public void append(String _str) {
        textArea.append(StringUtil.wrapContent(_str, textArea.getColumns(), false));
    }

    @Override
    public JComponent getNatComponent() {
        return getTextComponent();
    }

    @Override
    public JTextComponent getTextComponent() {
        return textArea;
    }
}
