package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class CastOperation extends AbstractUnaryOperation {

    private String className;
    private int offset;
    public CastOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        String ext_ = getOperations().getExtractType();
        if (!ext_.isEmpty()) {
            className = ext_;
        } else {
            String res_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
            res_ = _conf.resolveCorrectType(res_);
            className = res_;
        }
        setResultClass(new ClassArgumentMatching(className));
        if (PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            getFirstChild().getResultClass().setUnwrapObject(className);
            Argument arg_ = getFirstChild().getArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
        }
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        LgNames stds_ = _conf.getStandards();
        Argument objArg_ = arguments_.first();
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
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), _conf));
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
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(argres_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument argres_ = getArgument( arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
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
                _conf.setException(new ErrorStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,paramName_,RETURN_LINE,_conf.joinPages())),cast_));
                Argument a_ = new Argument();
                return a_;
            }
            arg_.setStruct(objArg_.getStruct());
        } else {
            if (PrimitiveTypeUtil.getOrderClass(paramName_, _conf) > 0) {
                if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new ErrorStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,className,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), _conf));
            } else {
                if (!StringList.quickEq(argClassName_, stds_.getAliasBoolean())) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new ErrorStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,className,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                arg_.setStruct(objArg_.getStruct());
            }
        }
        return arg_;
    }
}
