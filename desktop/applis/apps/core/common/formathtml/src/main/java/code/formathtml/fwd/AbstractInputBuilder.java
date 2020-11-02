package code.formathtml.fwd;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public interface AbstractInputBuilder {
    void tryBuildInputResult(String _name, ResultInput _resultInput, AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page);
    CustList<RendDynOperationNode> buildWritePart(ResultInput _resultInput, Forwards _forwards);
}
