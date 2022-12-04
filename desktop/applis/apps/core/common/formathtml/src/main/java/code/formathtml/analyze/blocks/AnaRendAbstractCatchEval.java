package code.formathtml.analyze.blocks;


import code.sml.NavigationCore;

public abstract class AnaRendAbstractCatchEval extends AnaRendParentBlock implements AnaRendBreakableBlock {
    AnaRendAbstractCatchEval(int _offset) {
        super(_offset);
    }

    public String getRealLabel() {
        AnaRendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof AnaRendTryEval)) {
            if (p_ == null) {
                return NavigationCore.EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((AnaRendTryEval)p_).getLabel();
    }

}
