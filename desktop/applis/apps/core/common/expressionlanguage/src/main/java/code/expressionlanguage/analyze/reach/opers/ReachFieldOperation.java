package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.CaseCondition;
import code.expressionlanguage.analyze.opers.DeclaredFieldOperation;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class ReachFieldOperation extends ReachMethodOperation implements ReachCalculable,ReachPossibleIntermediateDotted {
    private final AnaSettableOperationContent fieldMetaInfo;
    private final boolean declare;
    private Argument previous;

    ReachFieldOperation(SettableAbstractFieldOperation _info) {
        super(_info);
        fieldMetaInfo = _info.getSettableFieldContent();
        declare = _info instanceof DeclaredFieldOperation;
    }

    public static boolean caseCst(AbsBk _block) {
        return _block instanceof CaseCondition && !((CaseCondition) _block).isCaseWhen();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        if (fieldId_ == null) {
            return;
        }
        if (!fieldMetaInfo.isStaticField()) {
            checkNull(previous,_page);
            return;
        }
        if (!fieldMetaInfo.isFinalField()) {
            return;
        }
        StringMap<Struct> map_ = NumParsers.getStaticFieldMap(fieldId_.getClassName(), _page.getStaticFields());
        StringMap<StringMap<Struct>> staticFields_ = _page.getStaticFields();
        Struct str_ = NumParsers.getStaticField(fieldId_, staticFields_);
        if (map_.isEmpty()) {
            Argument arg_ = new Argument(_page.getCalculator().getInnerSimpleResult(fieldId_));
            setSimpleArgumentAna(arg_);
            trySetDotParent(this, arg_, _page);
            return;
        }
        if (declare) {
            Argument arg_ = new Argument(NumParsers.convert(getInfo().getResultClass().getUnwrapObjectNb()));
            setSimpleArgument(arg_);
            return;
        }
        if (str_ != null) {
            Argument arg_ = new Argument(str_);
            setSimpleArgumentAna(arg_);
            trySetDotParent(this, arg_, _page);
        }
    }

    private static void trySetDotParent(ReachOperationNode _oper, Argument _arg, AnalyzedPageEl _page) {
        AbsBk bl_ = _page.getCurrentBlock();
        if (!caseCst(bl_)) {
            return;
        }
        if (_oper.getInfo().getIndexChild() > 0
                && _oper.getParent() instanceof ReachDotOperation) {
            _oper.getParent().setSimpleArgumentAna(_arg);
        }
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
        previous = _argument;
    }
}
