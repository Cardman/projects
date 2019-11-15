package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.MethodAccessKind;
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
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
        } else {
            RendClass cl_ = (RendClass) getParent();
            String intern_ = cl_.getFullName();
            _cont.getAnalyzingDoc().setInternGlobalClass(intern_);
            MethodAccessKind st_ = _doc.getStaticContext();
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(prepareOffset);
            page_.setOffset(0);
            _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_PREPARE_BEAN);
            exps = RenderExpUtil.getAnalyzedOperations(prepare,0,_cont, Calculation.staticCalculation(st_));
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
