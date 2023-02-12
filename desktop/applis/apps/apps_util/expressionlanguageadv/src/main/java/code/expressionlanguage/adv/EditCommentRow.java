package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.gui.AbsCustCheckBox;
import code.gui.AbsPanel;
import code.gui.AbsTextArea;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class EditCommentRow {
    private final AbsPanel line;
    private final AbsTextArea beginArea;
    private final AbsTextArea endArea;
    private final AbsCustCheckBox selectForDelete;
    private int index;
    private CommentDelimiters comment;

    public EditCommentRow(AbstractProgramInfos _pr, CommentDelimiters _c, int _i) {
        comment = _c;
        AbsCompoFactory comp_ = _pr.getCompoFactory();
        AbsPanel line_ = comp_.newLineBox();
        AbsTextArea begin_ = comp_.newTextArea(_c.getBegin(),2,32);
        begin_.setLineBorder(GuiConstants.BLACK);
        line_.add(begin_);
        beginArea = begin_;
        AbsTextArea end_ = comp_.newTextArea(_c.getEnd().get(0),2,32);
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

    public AbsTextArea getBeginArea() {
        return beginArea;
    }

    public AbsTextArea getEndArea() {
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
