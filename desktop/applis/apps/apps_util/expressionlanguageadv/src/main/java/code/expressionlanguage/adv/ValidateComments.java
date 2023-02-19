package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ValidateComments implements AbsActionListener {
    private final OutputDialogComments output;
    private final CustList<EditCommentRow> comments;

    public ValidateComments(OutputDialogComments _w, CustList<EditCommentRow> _com) {
        this.output = _w;
        this.comments = _com;
    }

    @Override
    public void action() {
        output.getWindowCdmEditor().getDialogComments().setVisible(false);
        output.getComments().clear();
        int len_ = comments.size();
        for (int i = 0; i < len_; i++) {
            EditCommentRow e_ = comments.get(i);
            e_.updateComment();
            output.getComments().add(e_.getComment());
        }
        output.getWindowCdmEditor().updateComments(output.getComments());
        output.getWindowCdmEditor().afterChangingSyntaxPreferences();
    }
}
