package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
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
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        if (!(getParent() instanceof RendClass)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getRendAnalysisMessages().getUnexpectedChildTag(),
                    _cont.getRendKeyWords().getKeyWordField(),
                    _cont.getRendKeyWords().getKeyWordClass());
            _cont.addError(un_);
        } else {
            RendClass cl_ = (RendClass) getParent();
            String intern_ = cl_.getFullName();
            _cont.getAnalyzingDoc().setInternGlobalClass(intern_);
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(prepareOffset);
            page_.setOffset(0);
            _cont.getAnalyzingDoc().setAttribute(_cont.getRendKeyWords().getAttrPrepare());
            exps = RenderExpUtil.getAnalyzedOperations(prepare,prepareOffset,0,_cont);
            _cont.getAnalyzingDoc().setInternGlobalClass(EMPTY_STRING);
        }
    }

    public int getPrepareOffset() {
        return prepareOffset;
    }

    public CustList<RendDynOperationNode> getExps() {
        return exps;
    }
}
