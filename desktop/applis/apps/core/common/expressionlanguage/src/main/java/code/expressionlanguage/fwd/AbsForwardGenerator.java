package code.expressionlanguage.fwd;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.AnonymousLambdaOperation;
import code.expressionlanguage.analyze.opers.SwitchOperation;
import code.util.CustList;

public interface AbsForwardGenerator {
    CustList<OperatorBlock> getAllOperators(AnalyzedPageEl _page);
    CustList<RootBlock> getAllFoundTypes(AnalyzedPageEl _page);
    CustList<AnonymousLambdaOperation> getAllAnonymousLambda(AnalyzedPageEl _page);
    CustList<SwitchOperation> getAllSwitchMethods(AnalyzedPageEl _page);
}
