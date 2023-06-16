package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.opers.SwitchOperation;
import code.util.CustList;

public final class FirstForwardGenerator implements AbsForwardGenerator {
    @Override
    public CustList<OperatorBlock> getAllOperators(AnalyzedPageEl _page) {
        return _page.getAllOperators();
    }

    @Override
    public CustList<RootBlock> getAllFoundTypes(AnalyzedPageEl _page) {
        return _page.getAllFoundTypes();
    }

    @Override
    public CustList<AnonymousLambdaOperation> getAllAnonymousLambda(AnalyzedPageEl _page) {
        return _page.getAllAnonymousLambda();
    }

    @Override
    public CustList<SwitchOperation> getAllSwitchMethods(AnalyzedPageEl _page) {
        return _page.getAllSwitchMethods();
    }
}
