package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class AnaRendAbstractCatchEval extends AnaRendParentBlock implements AnaRendEval {
    AnaRendAbstractCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    public String getRealLabel() {
        AnaRendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof AnaRendTryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((AnaRendTryEval)p_).getLabel();
    }

}
