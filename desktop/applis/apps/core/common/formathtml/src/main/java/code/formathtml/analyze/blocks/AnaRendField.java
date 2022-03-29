package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendField extends AnaRendParentBlock implements AnaRendBuildEl {
    private final String prepare;
    private final int prepareOffset;
    private OperationNode root;
    private final ResultExpression resultExpression = new ResultExpression();

    AnaRendField(OffsetStringInfo _prepare, int _offset) {
        super(_offset);
        prepare = _prepare.getInfo();
        prepareOffset = _prepare.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaRendClass)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _anaDoc.getRendKeyWords().getKeyWordField(),
                    _anaDoc.getRendKeyWords().getKeyWordClass());
            AnalyzingDoc.addError(un_, _page);
        } else {
            AnaRendClass cl_ = (AnaRendClass) getParent();
            String intern_ = cl_.getFullName();
            _anaDoc.setInternGlobalClass(intern_);
            _page.setGlobalOffset(prepareOffset);
            _page.zeroOffset();
            root = RenderAnalysis.getRootAnalyzedOperations(prepare, 0, _anaDoc, _page,resultExpression);
            _anaDoc.setInternGlobalClass(EMPTY_STRING);
        }
    }

    public int getPrepareOffset() {
        return prepareOffset;
    }

    public String getPrepare() {
        return prepare;
    }

    public ResultExpression getRes() {
        return resultExpression;
    }

    public OperationNode getRoot() {
        return root;
    }
}
