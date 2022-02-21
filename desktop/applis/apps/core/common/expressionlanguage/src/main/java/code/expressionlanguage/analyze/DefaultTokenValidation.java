package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class DefaultTokenValidation {

    private DefaultTokenValidation() {
    }

    public static boolean isStaticAcc(AnalyzedPageEl _page) {
        if (_page.isAnnotAnalysis()) {
            return true;
        }
        AbsBk bl_ = _page.getCurrentBlock();
        if (bl_ instanceof InfoBlock) {
            return ((InfoBlock)bl_).isStaticField();
        }
        if (bl_ instanceof RootBlock) {
            return ((RootBlock)bl_).withoutInstance();
        }
        WithContext fct_ = _page.getCurrentCtx();
        return fct_.getStaticContext() == MethodAccessKind.STATIC;
    }

}
