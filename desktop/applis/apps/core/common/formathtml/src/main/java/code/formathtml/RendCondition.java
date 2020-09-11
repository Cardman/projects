package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

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
        _cont.getAnalyzingDoc().setAttribute(_cont.getRendKeyWords().getAttrCondition());
        opCondition = RenderExpUtil.getAnalyzedOperations(condition,conditionOffset,0, _cont);
        RendDynOperationNode elCondition_ = opCondition.last();
        LgNames stds_ = page_.getStandards();
        ClassArgumentMatching exp_ = elCondition_.getResultClass();
        if (!exp_.isBoolType(page_)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_cont.getContext(), _cont.getStandards().getAliasPrimBoolean(), exp_);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                exp_.getImplicits().add(cl_);
                exp_.setRootNumber(res_.getRootNumber());
                exp_.setMemberNumber(res_.getMemberNumber());
            } else {
                ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(_cont.getContext(), exp_);
                if (trueOp_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                    exp_.getImplicitsTest().add(cl_);
                    exp_.setRootNumberTest(trueOp_.getRootNumber());
                    exp_.setMemberNumberTest(trueOp_.getMemberNumber());
                } else {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(conditionOffset);
                    un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedType(),
                            StringList.join(exp_.getNames(),AND_ERR));
                    _cont.addError(un_);
                }
            }
        }
        exp_.setUnwrapObject(stds_.getAliasPrimBoolean());
        RenderExpUtil.setImplicits(elCondition_,_cont.getContext());
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode r_ = opCondition.last();
        opCondition = RenderExpUtil.getReducedNodes(r_);
    }

    final ConditionReturn evaluateCondition(Configuration _context) {
        ImportingPage last_ = _context.getLastPage();
        last_.setOffset(conditionOffset);
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(opCondition,_context);
        if (_context.getContext().hasException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
