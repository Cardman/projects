package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendField extends AnaRendParentBlock {
    private final String prepare;
    private final int prepareOffset;
    private OperationNode root;
    AnaRendField(OffsetStringInfo _prepare, int _offset) {
        super(_offset);
        prepare = _prepare.getInfo();
        prepareOffset = _prepare.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaRendClass)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset());
            un_.buildError(_anaDoc.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _anaDoc.getRendKeyWords().getKeyWordField(),
                    _anaDoc.getRendKeyWords().getKeyWordClass());
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        } else {
            AnaRendClass cl_ = (AnaRendClass) getParent();
            String intern_ = cl_.getFullName();
            _anaDoc.setInternGlobalClass(intern_);
            _page.setGlobalOffset(prepareOffset);
            _page.zeroOffset();
            _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrPrepare());
            root = RenderAnalysis.getRootAnalyzedOperations(prepare, 0, _anaDoc, _page);
            _anaDoc.setInternGlobalClass(EMPTY_STRING);
        }
    }

    public int getPrepareOffset() {
        return prepareOffset;
    }

    public OperationNode getRoot() {
        return root;
    }
}
