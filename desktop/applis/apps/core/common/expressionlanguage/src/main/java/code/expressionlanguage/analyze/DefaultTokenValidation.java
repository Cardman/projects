package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class DefaultTokenValidation implements AbstractTokenValidation {
    private final AnalyzedPageEl page;

    public DefaultTokenValidation(AnalyzedPageEl page) {
        this.page = page;
    }

    @Override
    public boolean isStaticAccess() {
        return isStaticAcc();
    }


    private boolean isStaticAcc() {
        if (page.isAnnotAnalysis()) {
            return true;
        }
        Block bl_ = page.getCurrentBlock();
        if (bl_ instanceof InfoBlock) {
            return ((InfoBlock)bl_).isStaticField();
        }
        if (bl_ instanceof RootBlock) {
            return ((RootBlock)bl_).isStaticType();
        }
        MemberCallingsBlock fct_ = page.getCurrentFct();
        return fct_.getStaticContext() == MethodAccessKind.STATIC;
    }
    @Override
    public TokenErrorMessage isValidSingleToken(String _id) {
        return ManageTokens.partVar(page).checkTokenVar(_id, page);
    }

}
