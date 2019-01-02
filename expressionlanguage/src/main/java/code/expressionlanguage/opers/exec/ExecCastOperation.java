package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CastOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCastOperation extends ExecAbstractUnaryOperation {

    private String className;
    private int offset;
    public ExecCastOperation(CastOperation _c) {
        super(_c);
        className = _c.getClassName();
        offset = _c.getOffset();
    }


    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        LgNames stds_ = _conf.getStandards();
        Argument objArg_ = arguments_.first();
        if (className.startsWith("#")) {
            return;
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(className, objArg_.getStruct(), _conf.getStandards())) {
            return;
        }
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            setSimpleArgumentAna(arg_, _conf);
            return;
        }
        if (!PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            if (!StringList.quickEq(className, _conf.getStandards().getAliasString())) {
                return;
            }
        }
        Struct s_ = objArg_.getStruct();
        String argClassName_ = stds_.getStructClassName(s_, _conf.getContextEl());
        ClassArgumentMatching resCl_ = getResultClass();
        Argument arg_ = new Argument();
        if (!PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(argClassName_);
            mapping_.setParam(className);
            if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
                return;
            }
            arg_.setStruct(objArg_.getStruct());
        } else {
            if (PrimitiveTypeUtil.getOrderClass(className, _conf) > 0) {
                if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                    return;
                }
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), stds_));
            } else {
                if (!StringList.quickEq(argClassName_, stds_.getAliasBoolean())) {
                    return;
                }
                arg_.setStruct(objArg_.getStruct());
            }
        }
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = _arguments.first();
        String paramName_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        Templates.checkObject(paramName_, objArg_, _conf);
        return objArg_;
    }
}
