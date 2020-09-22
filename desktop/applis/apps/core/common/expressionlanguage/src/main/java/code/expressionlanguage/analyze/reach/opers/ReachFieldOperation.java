package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.CaseCondition;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class ReachFieldOperation extends ReachMethodOperation implements ReachCalculable,ReachPossibleIntermediateDotted {
    private FieldInfo fieldMetaInfo;
    private Argument previous;
    ReachFieldOperation(SettableAbstractFieldOperation _info) {
        super(_info);
        fieldMetaInfo = _info.getFieldMetaInfo();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (fieldMetaInfo == null) {
            return;
        }
        if (!fieldMetaInfo.isStaticField()) {
            checkNull(previous,_page);
            return;
        }
        if (!fieldMetaInfo.isFinalField()) {
            return;
        }
        ClassField fieldId_ = fieldMetaInfo.getClassField();
        StringMap<Struct> map_ = Classes.getStaticFieldMap(fieldId_.getClassName(), _page.getStaticFields());
        StringMap<StringMap<Struct>> staticFields_ = _page.getStaticFields();
        Struct str_ = Classes.getStaticField(fieldId_, staticFields_);
        if (map_.isEmpty()) {
            LgNames stds_ = _page.getStandards();
            ResultErrorStd res_ = stds_.getSimpleResult(fieldId_);
            Argument arg_ = new Argument(res_.getResult());
            setSimpleArgumentAna(arg_);
            trySetDotParent(this, arg_, _page);
            return;
        }
        if (ElUtil.isDeclaringField(getInfo(), _page)) {
            Argument arg_ = Argument.createVoid();
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
        Block bl_ = _page.getCurrentBlock();
        if (!(bl_ instanceof CaseCondition)) {
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
