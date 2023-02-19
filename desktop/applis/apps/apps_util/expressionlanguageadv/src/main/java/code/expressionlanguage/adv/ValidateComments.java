package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class ValidateComments implements AbsActionListener {
    private final OutputDialogComments output;
    private final CustList<EditCommentRow> comments;
    private final WindowCdmEditor windowCdmEditor;

    public ValidateComments(OutputDialogComments _w, CustList<EditCommentRow> _com, WindowCdmEditor _ed) {
        this.output = _w;
        this.comments = _com;
        windowCdmEditor = _ed;
    }

    @Override
    public void action() {
        windowCdmEditor.getDialogComments().setVisible(false);
        output.getComments().clear();
        int len_ = comments.size();
        for (int i = 0; i < len_; i++) {
            EditCommentRow e_ = comments.get(i);
            e_.updateComment();
            output.getComments().add(e_.getComment());
        }
        windowCdmEditor.updateComments(output.getComments());
        windowCdmEditor.afterChangingSyntaxPreferences();
    }
}
