package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.AnalyzingDoc;
import code.util.CustList;

public final class RendField extends RendParentBlock {
    private String prepare;
    private int prepareOffset;
    private CustList<RendDynOperationNode> exps;
    RendField(OffsetStringInfo _prepare, OffsetsBlock _offset) {
        super(_offset);
        prepare = _prepare.getInfo();
        prepareOffset = _prepare.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (!(getParent() instanceof RendClass)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _cont.getRendKeyWords().getKeyWordField(),
                    _cont.getRendKeyWords().getKeyWordClass());
            Configuration.addError(un_, _anaDoc, _page);
        } else {
            RendClass cl_ = (RendClass) getParent();
            String intern_ = cl_.getFullName();
            _anaDoc.setInternGlobalClass(intern_);
            _page.setGlobalOffset(prepareOffset);
            _page.setOffset(0);
            _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrPrepare());
            exps = RenderExpUtil.getAnalyzedOperations(prepare,prepareOffset,0,_cont, _anaDoc, _page);
            _anaDoc.setInternGlobalClass(EMPTY_STRING);
        }
    }

    public int getPrepareOffset() {
        return prepareOffset;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }
}
