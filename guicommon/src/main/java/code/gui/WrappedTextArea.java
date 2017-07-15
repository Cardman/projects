package code.gui;
import javax.swing.JTextArea;

import code.util.StringList;

public class WrappedTextArea extends JTextArea {

    public WrappedTextArea() {
    }

    public WrappedTextArea(int _rows, int _columns) {
        super(_rows, _columns);
    }

    @Override
    public void setText(String _t) {
        super.setText(StringList.wrapContent(_t, getColumns(), false));
    }

    @Override
    public void append(String _str) {
        super.append(StringList.wrapContent(_str, getColumns(), false));
    }
}
