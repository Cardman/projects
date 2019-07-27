package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;

public abstract class RendCondition extends RendParentBlock implements RendWithEl, RendReducableOperations, RendBuildableElMethod {

    private String condition;

    private int conditionOffset;

    private CustList<RendDynOperationNode> opCondition;
    RendCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(conditionOffset);
        page_.setOffset(0);
        _cont.getAnalyzingDoc().setAttribute(ATTRIBUTE_CONDITION);
        opCondition = ElRenderUtil.getAnalyzedOperations(condition,0, _cont, Calculation.staticCalculation(_doc.isStaticContext()));
        RendDynOperationNode elCondition_ = opCondition.last();
        LgNames stds_ = _cont.getStandards();
        if (!elCondition_.getResultClass().isBoolType(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(conditionOffset);
            un_.setType(opCondition.last().getResultClass());
            _cont.getClasses().addError(un_);
        }
        elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opCondition.last();
        opCondition = ElRenderUtil.getReducedNodes(r_);
    }

    public final String getCondition() {
        return condition;
    }

    final Boolean evaluateCondition(Configuration _context) {
        ImportingPage last_ = _context.getLastPage();
        last_.setOffset(conditionOffset);
        last_.setProcessingAttribute(ATTRIBUTE_CONDITION);
        Argument arg_ = ElRenderUtil.calculateReuse(opCondition,_context);
        if (_context.getContext().hasExceptionOrFailInit()) {
            return null;
        }
        return ((BooleanStruct) arg_.getStruct()).getInstance();
    }
}
