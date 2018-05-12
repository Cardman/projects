package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        String res_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
        res_ = StringList.removeAllSpaces(res_);
        if (!checkCorrect(_conf, res_, true, getIndexInEl()+1)) {
            res_ = stds_.getAliasObject();
        }
        className = res_;
        setResultClass(new ClassArgumentMatching(res_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);

        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
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
        Object o_ = objArg_.getObject();
        String argClassName_ = _conf.getStandards().getSimpleStructClassName(o_);
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
                String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(new ClassArgumentMatching(argClassName_), true, _conf).getName();
                if (!StringList.quickEq(typeNameArg_, stds_.getAliasPrimBoolean())) {
                    return;
                }
                arg_.setStruct(objArg_.getStruct());
            }
        }
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculate(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        if (_conf.getException() != null) {
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

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        String cast_;
        null_ = stds_.getAliasNullPe();
        cast_ = stds_.getAliasCast();
        Argument objArg_ = _arguments.first();
        if (PrimitiveTypeUtil.primitiveTypeNullObject(className, objArg_.getStruct(), _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            Argument a_ = new Argument();
            return a_;
        }
        if (objArg_.isNull()) {
            Argument arg_ = new Argument();
            return arg_;
        }
        String argClassName_ = objArg_.getObjectClassName(_conf);
        ClassArgumentMatching resCl_ = getResultClass();
        Argument arg_ = new Argument();
        if (!PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(argClassName_);
            String paramName_ = _conf.getLastPage().formatVarType(className, _conf);
            mapping_.setParam(paramName_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,paramName_,RETURN_LINE,_conf.joinPages())),cast_));
                Argument a_ = new Argument();
                return a_;
            }
            arg_.setStruct(objArg_.getStruct());
        } else {
            if (PrimitiveTypeUtil.getOrderClass(className, _conf) > 0) {
                if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf) == 0) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,className,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                arg_.setStruct(PrimitiveTypeUtil.convertObject(resCl_, objArg_.getStruct(), _conf));
            } else {
                String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(new ClassArgumentMatching(argClassName_), true, _conf).getName();
                if (!StringList.quickEq(typeNameArg_, stds_.getAliasPrimBoolean())) {
                    setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                    _conf.setException(new StdStruct(new CustomError(StringList.concat(argClassName_,RETURN_LINE,className,RETURN_LINE,_conf.joinPages())),cast_));
                    Argument a_ = new Argument();
                    return a_;
                }
                arg_.setStruct(objArg_.getStruct());
            }
        }
        return arg_;
    }
}
