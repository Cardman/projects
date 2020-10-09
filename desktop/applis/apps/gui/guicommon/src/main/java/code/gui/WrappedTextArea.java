package code.gui;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import code.util.core.StringUtil;

public class WrappedTextArea extends CustComponent {

    private JTextArea textArea;

    public WrappedTextArea() {
        textArea = new JTextArea();
    }

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
    protected JComponent getComponent() {
        return textArea;
    }
}
