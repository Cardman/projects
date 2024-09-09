package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.WithFilterContent;
import code.expressionlanguage.analyze.opers.DeclaredFieldOperation;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class ReachFieldOperation extends ReachMethodOperation implements ReachCalculable,ReachPossibleIntermediateDotted {
    private final AnaSettableOperationContent fieldMetaInfo;
    private final boolean declare;
    private Struct previous;

    ReachFieldOperation(SettableAbstractFieldOperation _info) {
        super(_info);
        fieldMetaInfo = _info.getSettableFieldContent();
        declare = _info instanceof DeclaredFieldOperation;
    }

    public static boolean caseCst(AbsBk _block) {
        return _block instanceof WithFilterContent && ((WithFilterContent) _block).getFilterContent().getResValue().getRoot() != null;
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
        Struct str_ = NullStruct.def(NumParsers.getStaticField(fieldId_, staticFields_));
        if (map_.isEmpty()) {
            Struct arg_ = _page.getCalculator().getInnerSimpleResult(fieldId_);
            setSimpleArgumentAna(arg_);
            trySetDotParent(this, arg_, _page);
            return;
        }
        if (declare) {
            setSimpleArgument(NumParsers.convert(getInfo().getResultClass().getUnwrapObjectNb()));
            return;
        }
        if (str_ != NullStruct.DEF_VALUE) {
            setSimpleArgumentAna(str_);
            trySetDotParent(this, str_, _page);
        }
    }

    private static void trySetDotParent(ReachOperationNode _oper, Struct _arg, AnalyzedPageEl _page) {
        if (!_page.isEvaluatingCaseCondition()) {
            return;
        }
        if (_oper.getInfo().getIndexChild() > 0
                && _oper.getParent() instanceof ReachDotOperation) {
            _oper.getParent().setSimpleArgumentAna(_arg);
        }
    }

    @Override
    public void setPreviousArgument(Struct _argument) {
        previous = _argument;
    }
}
