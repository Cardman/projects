package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class DefaultTokenValidation implements AbstractTokenValidation {
    private final AnalyzedPageEl page;

    public DefaultTokenValidation(AnalyzedPageEl _page) {
        this.page = _page;
    }

    @Override
    public boolean isStaticAccess() {
        return isStaticAcc();
    }


    private boolean isStaticAcc() {
        if (page.isAnnotAnalysis()) {
            return true;
        }
        AbsBk bl_ = page.getCurrentBlock();
        if (bl_ instanceof InfoBlock) {
            return ((InfoBlock)bl_).isStaticField();
        }
        if (bl_ instanceof RootBlock) {
            return ((RootBlock)bl_).withoutInstance();
        }
        MemberCallingsBlock fct_ = page.getCurrentFct();
        return fct_.getStaticContext() == MethodAccessKind.STATIC;
    }

}
