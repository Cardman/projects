package code.formathtml.fwd;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class DefaultInputBuilder implements AbstractInputBuilder {
    @Override
    public void tryBuildInputResult(String _name, ResultInput _resultInput, AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _resultInput.tryBuildInputResult(_name, _bl, _anaDoc, _page);
    }

    @Override
    public CustList<RendDynOperationNode> buildWritePart(ResultInput _resultInput, Forwards _forwards) {
        return RendForwardInfos.buildWritePart(_resultInput, _forwards);
    }
}
