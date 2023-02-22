package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.AbsTextArea;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class EditMessageRow {
    private final String key;
    private final AbsPanel line;
    private final AbsTextArea content;

    public EditMessageRow(AbstractProgramInfos _pr, String _k, String _value) {
        key = _k;
        AbsCompoFactory comp_ = _pr.getCompoFactory();
        AbsPanel line_ = comp_.newLineBox();
        line_.add(comp_.newPlainLabel(_k));
        AbsTextArea begin_ = comp_.newTextArea(_value,1,32);
        begin_.setLineBorder(GuiConstants.BLACK);
        line_.add(begin_);
        content = begin_;
        line = line_;
    }

    public String getKey() {
        return key;
    }

    public AbsTextArea getContent() {
        return content;
    }

    public AbsPanel getLine() {
        return line;
    }

}
