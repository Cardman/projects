package code.expressionlanguage.adv;

import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsTextField;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;

public final class EditValueRow {
    private final AbsPanel line;
    private final AbsTextField valueArea;
    private final AbsCustCheckBox selectForDelete;
    private int index;
    private String value;

    public EditValueRow(AbstractProgramInfos _pr, String _c, int _i) {
        value = _c;
        AbsCompoFactory comp_ = _pr.getCompoFactory();
        AbsPanel line_ = comp_.newLineBox();
        AbsTextField begin_ = comp_.newTextField(_c,32);
        begin_.setLineBorder(GuiConstants.BLACK);
        line_.add(begin_);
        valueArea = begin_;
        selectForDelete = comp_.newCustCheckBox();
        line_.add(selectForDelete);
        line = line_;
        index = _i;
    }
    public void updateComment() {
        value = valueArea.getText();
    }

    public AbsTextField getValueArea() {
        return valueArea;
    }

    public String getValue() {
        return value;
    }

    public AbsCustCheckBox getSelectForDelete() {
        return selectForDelete;
    }

    public AbsPanel getLine() {
        return line;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _i) {
        this.index = _i;
    }
}
