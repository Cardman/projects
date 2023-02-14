package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;

public final class OutputDialogCommentsResult {
    private final CustList<CommentDelimiters> comments;
    private final AbstractAtomicBoolean valid;

    public OutputDialogCommentsResult(CustList<CommentDelimiters> _c, AbstractAtomicBoolean _v) {
        this.comments = _c;
        this.valid = _v;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public AbstractAtomicBoolean getValid() {
        return valid;
    }
}
