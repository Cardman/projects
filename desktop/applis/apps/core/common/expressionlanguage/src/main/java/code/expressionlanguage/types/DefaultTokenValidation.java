package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class DefaultTokenValidation implements AbstractTokenValidation {
    private final ContextEl context;

    public DefaultTokenValidation(ContextEl context) {
        this.context = context;
    }

    @Override
    public boolean isStaticAccess() {
        return isStaticAcc();
    }


    private boolean isStaticAcc() {
        if (context.isAnnotAnalysis()) {
            return true;
        }
        Block bl_ = context.getAnalyzing().getCurrentBlock();
        if (bl_ instanceof InfoBlock) {
            return ((InfoBlock)bl_).isStaticField();
        }
        if (bl_ instanceof RootBlock) {
            return ((RootBlock)bl_).isStaticType();
        }
        MemberCallingsBlock fct_ = context.getAnalyzing().getCurrentFct();
        return fct_.getStaticContext() == MethodAccessKind.STATIC;
    }
    @Override
    public TokenErrorMessage isValidSingleToken(String _id) {
        return ManageTokens.partVar(context).checkStdTokenVar(context,_id);
    }

}
