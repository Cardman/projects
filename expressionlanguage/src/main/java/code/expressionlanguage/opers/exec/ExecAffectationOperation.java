package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecAffectationOperation extends ExecReflectableOpering {

    private ExecSettableElResult settable;

    public ExecAffectationOperation(AffectationOperation _a) {
        super(_a);
    }

    public void setup() {
        settable = tryGetSettable(this);
    }
    static ExecSettableElResult tryGetSettable(ExecMethodOperation _operation) {
        ExecOperationNode root_ = _operation.getFirstChild();
        ExecSettableElResult elt_ = null;
        while (root_ instanceof ExecIdOperation) {
            root_ = ((ExecIdOperation)root_).getFirstChild();
        }
        if (!(root_ instanceof ExecDotOperation)) {
            if (root_ instanceof ExecSettableElResult) {
                elt_ = (ExecSettableElResult) root_;
            }
        } else {
            ExecOperationNode beforeLast_ = ((ExecMethodOperation)root_).getChildrenNodes().last();
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
        ExecOperationNode lastChild_ = getFirstChild().getNextSibling();
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
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        ExecOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = getArgument(_nodes, right_);
        Argument arg_ = settable.calculateSetting(_nodes, _conf, rightArg_);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

}
