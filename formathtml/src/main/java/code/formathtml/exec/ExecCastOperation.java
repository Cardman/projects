package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.CastOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
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
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
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
        String argClassName_ = s_.getClassName(_conf.getContextEl());
        ClassArgumentMatching resCl_ = getResultClass();
        Argument arg_ = new Argument();
        if (!PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(argClassName_);
            mapping_.setParam(className);
            if (!Templates.isCorrect(mapping_, _conf)) {
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
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(argres_, _conf);
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            return arg_;
        }
        String argClassName_ = objArg_.getObjectClassName(_conf.getContextEl());
        Argument arg_ = new Argument();
        String paramName_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        ClassArgumentMatching resCl_ = new ClassArgumentMatching(paramName_);
        if (!PrimitiveTypeUtil.isPrimitive(paramName_, _conf)) {
            if (!Templates.isCorrectExecute(argClassName_, paramName_ , _conf)) {
                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                _conf.setException(new ErrorStruct(_conf, StringList.concat(argClassName_,RETURN_LINE,paramName_,RETURN_LINE),cast_));
                Argument a_ = new Argument();
                return a_;
            }
            arg_.setStruct(objArg_.getStruct());
        } else {
            if (PrimitiveTypeUtil.getOrderClass(paramName_, _conf) > 0) {
                if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new ErrorStruct(_conf,StringList.concat(argClassName_,RETURN_LINE,className,RETURN_LINE),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), stds_));
            } else {
                if (!StringList.quickEq(argClassName_, stds_.getAliasBoolean())) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new ErrorStruct(_conf, StringList.concat(argClassName_,RETURN_LINE,className,RETURN_LINE),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                arg_.setStruct(objArg_.getStruct());
            }
        }
        return arg_;
    }
}
