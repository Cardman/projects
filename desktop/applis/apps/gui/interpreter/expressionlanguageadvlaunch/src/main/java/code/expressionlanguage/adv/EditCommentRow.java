package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class EditCommentRow {
    private final AbsPanel line;
    private final AbsTextField beginArea;
    private final AbsTextField endArea;
    private final AbsCustCheckBox selectForDelete;
    private int index;
    private CommentDelimiters comment;

    public EditCommentRow(AbstractProgramInfos _pr, CommentDelimiters _c, int _i) {
        comment = _c;
        AbsCompoFactory comp_ = _pr.getCompoFactory();
        AbsPanel line_ = comp_.newLineBox();
        AbsTextField begin_ = comp_.newTextField(_c.getBegin().trim(),32);
        begin_.setLineBorder(GuiConstants.BLACK);
        line_.add(begin_);
        beginArea = begin_;
        AbsTextField end_ = comp_.newTextField(_c.getEnd().get(0).trim(),32);
        end_.setLineBorder(GuiConstants.BLACK);
        line_.add(end_);
        selectForDelete = comp_.newCustCheckBox();
        line_.add(selectForDelete);
        endArea = end_;
        line = line_;
        index = _i;
    }
    public void updateComment() {
        comment = new CommentDelimiters(beginArea.getText(),new StringList(endArea.getText()));
    }

    public AbsTextField getBeginArea() {
        return beginArea;
    }

    public AbsTextField getEndArea() {
        return endArea;
    }

    public CommentDelimiters getComment() {
        return comment;
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
