package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;

public final class ExecAffectationOperation extends ExecReflectableOpering {

    private ExecSettableElResult settable;

    public ExecAffectationOperation(AffectationOperation _a) {
        super(_a);
    }

    public void setup() {
        settable = tryGetSettable(this);
    }
    static ExecSettableElResult tryGetSettable(ExecMethodOperation _operation) {
        ExecDynOperationNode root_ = _operation.getFirstChild();
        ExecSettableElResult elt_ = null;
        while (root_ instanceof ExecIdOperation) {
            root_ = ((ExecIdOperation)root_).getFirstChild();
        }
        if (!(root_ instanceof ExecDotOperation)) {
            if (root_ instanceof ExecSettableElResult) {
                elt_ = (ExecSettableElResult) root_;
            }
        } else {
            ExecDynOperationNode beforeLast_ = ((ExecMethodOperation)root_).getChildrenNodes().last();
            if (beforeLast_ instanceof ExecSettableElResult) {
                elt_ = (ExecSettableElResult) beforeLast_;
            }
        }
        return elt_;
    }
    public ExecSettableElResult getSettable() {
        return settable;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!isDeclaringField(settable, _conf)) {
            return;
        }
        ExecSettableFieldOperation fieldRef_ = (ExecSettableFieldOperation) settable;
        ExecDynOperationNode lastChild_ = getFirstChild().getNextSibling();
        Argument value_ = lastChild_.getArgument();
        ClassField id_ = fieldRef_.getFieldId();
        if (id_ == null) {
            return;
        }
        if (!_conf.isGearConst()) {
            return;
        }
        FieldInfo fm_ = _conf.getFieldInfo(id_);
        Struct str_ = value_.getStruct();
        LgNames stds_ = _conf.getStandards();
        String to_ = fm_.getType();
        str_ = PrimitiveTypeUtil.unwrapObject(to_, str_, stds_);
        _conf.getClasses().initializeStaticField(id_, str_);
        setSimpleArgument(value_);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        ExecDynOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = right_.getArgument();
        settable.calculateSetting(_conf, rightArg_);
        ExecDynOperationNode op_ = (ExecDynOperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
    }

}
