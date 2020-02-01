package code.formathtml;

import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendReadWrite;

public final class RendNullCatchEval extends RendAbstractCatchEval {
    RendNullCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendAbstractCatchEval)) {
            if (!(pBlock_ instanceof RendTryEval)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _cont.getClasses().addError(un_);
                } else if (!(pBlock_.getPreviousSibling() instanceof RendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof RendTryEval)) {
                        UnexpectedTagName un_ = new UnexpectedTagName();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        _cont.getClasses().addError(un_);
                    }
                }
            }
        }
    }
}
