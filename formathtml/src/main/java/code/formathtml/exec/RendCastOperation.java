package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.CastOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;
import code.util.StringList;

public final class RendCastOperation extends RendAbstractUnaryOperation {

    private String className;
    private int offset;
    public RendCastOperation(CastOperation _c) {
        super(_c);
        className = _c.getClassName();
        offset = _c.getOffset();
    }


    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (RendDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf);
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
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
