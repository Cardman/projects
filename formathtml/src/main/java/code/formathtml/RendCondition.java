package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConditionReturn;
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
        opCondition = RenderExpUtil.getAnalyzedOperations(condition,0, _cont, Calculation.staticCalculation(_doc.getStaticContext()));
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
        opCondition = RenderExpUtil.getReducedNodes(r_);
    }

    final ConditionReturn evaluateCondition(Configuration _context) {
        ImportingPage last_ = _context.getLastPage();
        last_.setOffset(conditionOffset);
        last_.setProcessingAttribute(ATTRIBUTE_CONDITION);
        Argument arg_ = RenderExpUtil.calculateReuse(opCondition,_context);
        if (_context.getContext().hasException()) {
            return ConditionReturn.CALL_EX;
        }
        if (((BooleanStruct) arg_.getStruct()).getInstance()) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
