package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AffectationOperation extends MethodOperation {

    private SettableElResult settable;

    public AffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode root_ = chidren_.first();
        setResultClass(root_.getResultClass());
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = tryGetSettable(this);
        boolean ok_ = elt_ != null;
        if (!ok_) {
            root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(un_);
            return;
        }
        settable = elt_;
        String oper_ = getOperations().getOperators().firstValue();
        boolean var_ = true;
        if (elt_ instanceof ArrOperation) {
            if (oper_.length() == 2) {
                var_ = false;
            }
        }
        elt_.setVariable(var_);
        LgNames stds_ = _conf.getStandards();
        String stringType_ = stds_.getAliasString();
        String res_ = elt_.getResultClass().getName();
        if (settable.resultCanBeSet() && StringList.quickEq(res_, stringType_)) {
            settable.setCatenizeStrings();
        }
        if (elt_ instanceof ConstantOperation) {
            if (((ConstantOperation)elt_).isImmutablePart()) {
                root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_conf.getCurrentFileName());
                un_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(un_);
                return;
            }
            if (((ConstantOperation)elt_).isFinalField()) {
                root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_conf.getCurrentFileName());
                un_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(un_);
                return;
            }
        }
        ClassArgumentMatching clMatchRight_ = right_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = root_.getResultClass();
        root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
        if (oper_.length() == 2) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            if (StringList.quickEq(oper_, Block.EQ_PLUS) || StringList.quickEq(oper_, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                    if (!clMatchLeft_.matchClass(_conf.getStandards().getAliasString())) {
                        _conf.getClasses().getErrorsDet().add(cast_);
                        return;
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                    _conf.getClasses().getErrorsDet().add(cast_);
                    return;
                }
            } else if (StringList.quickEq(oper_, Block.AND_EQ) || StringList.quickEq(oper_, Block.OR_EQ)) {
                if (!StringList.quickEq(clMatchLeft_.getName(), stds_.getAliasBoolean())) {
                    if (!StringList.quickEq(clMatchLeft_.getName(), stds_.getAliasPrimBoolean())) {
                        _conf.getClasses().getErrorsDet().add(cast_);
                        return;
                    }
                }
                if (!StringList.quickEq(clMatchRight_.getName(), stds_.getAliasBoolean())) {
                    if (!StringList.quickEq(clMatchRight_.getName(), stds_.getAliasPrimBoolean())) {
                        _conf.getClasses().getErrorsDet().add(cast_);
                        return;
                    }
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                _conf.getClasses().getErrorsDet().add(cast_);
                return;
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                _conf.getClasses().getErrorsDet().add(cast_);
                return;
            }
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive(_conf)) {
                    return;
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchRight_.getName());
                mapping_.setParam(clMatchLeft_.getName());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
                return;
            }
            StringMap<StringList> vars_ = new StringMap<StringList>();
            boolean buildMap_ = true;
            if (_conf.isStaticContext()) {
                buildMap_ = false;
            } else if (_conf.getGlobalClass() == null) {
                buildMap_ = false;
            }
            if (buildMap_) {
                for (TypeVar t: Templates.getConstraints(_conf.getGlobalClass(), _conf)) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            if (!Templates.isCorrect(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
            }
        }
    }

    static SettableElResult tryGetSettable(MethodOperation _operation) {
        CustList<OperationNode> chidren_ = _operation.getChildrenNodes();
        OperationNode root_ = chidren_.first();
        SettableElResult elt_ = null;
        if (!(root_ instanceof DotOperation)) {
            if (root_ instanceof SettableElResult) {
                elt_ = (SettableElResult) root_;
            }
        } else {
            OperationNode beforeLast_ = ((MethodOperation)root_).getChildrenNodes().last();
            if (beforeLast_ instanceof SettableElResult) {
                elt_ = (SettableElResult) beforeLast_;
            }
        }
        return elt_;
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        OperationNode firstChild_ = (OperationNode) settable;
        OperationNode lastChild_ = getChildrenNodes().last();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
        OperationsSequence op_ = firstChild_.getOperations();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        if (op_.getConstType() == ConstType.LOC_VAR) {
            String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey()) || e.getValue().isAssignedAfter()) {
                        sm_.put(e.getKey(), e.getValue().assignChange(isBool_, true, false));
                    } else if (!StringList.quickEq(str_, e.getKey()) && e.getValue().isUnassignedAfter()) {
                        sm_.put(e.getKey(), e.getValue().assignChange(isBool_, false, true));
                    } else {
                        sm_.put(e.getKey(), e.getValue().assign(isBool_));
                    }
                }
                variablesAfter_.add(sm_);
            }
            
        } else {
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assign(isBool_));
                }
                variablesAfter_.add(sm_);
            }
        }
        vars_.getVariables().put(this, variablesAfter_);
        boolean procField_ = false;
        if (firstChild_ instanceof ConstantOperation) {
            ConstantOperation cst_ = (ConstantOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            if (cl_ != null) {
                if (cst_.isFirstChild()) {
                    procField_ = true;
                } else {
                    int index_ = cst_.getIndexChild() - 1;
                    OperationNode opPr_ = cst_.getParent().getChildrenNodes().get(index_);
                    OperationsSequence opPrev_ = opPr_.getOperations();
                    if (opPrev_.getConstType() == ConstType.THIS_KEYWORD) {
                        if (StringList.quickEq(opPr_.getResultClass().getName(), _conf.getGlobalClass())) {
                            procField_ = true;
                        }
                    }
                    if (!procField_) {
                        if (opPr_ instanceof StaticAccessOperation) {
                            if (StringList.quickEq(opPr_.getResultClass().getName(), _conf.getGlobalClass())) {
                                procField_ = true;
                            }
                        }
                    }
                }
            }
        }
        if (procField_) {
            ConstantOperation cst_ = (ConstantOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                if (cl_.eq(e.getKey()) || e.getValue().isAssignedAfter()) {
                    fieldsAfter_.put(e.getKey(), e.getValue().assignChange(isBool_, true, false));
                } else if (!cl_.eq(e.getKey()) && e.getValue().isUnassignedAfter()) {
                    fieldsAfter_.put(e.getKey(), e.getValue().assignChange(isBool_, false, true));
                } else {
                    fieldsAfter_.put(e.getKey(), e.getValue().assign(isBool_));
                }
            }
        } else {
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), e.getValue().assign(isBool_));
            }
        }
        vars_.getFields().put(this, fieldsAfter_);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _firstChild, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_firstChild, fieldsBefore_);
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_firstChild, variablesBefore_);
    }
    public SettableElResult getSettable() {
        return settable;
    }
    @Override
    public void calculate(ContextEl _conf) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getLastPage().setRightArgument(right_.getArgument());
        String oper_ = getOperations().getOperators().firstValue();
        settable.calculateSetting(_conf, oper_, false);
        OperationNode op_ = (OperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
        _conf.getLastPage().setRightArgument(null);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getLastPage().setRightArgument(_nodes.getVal(right_).getArgument());
        String oper_ = getOperations().getOperators().firstValue();
        Argument arg_ = settable.calculateSetting(_nodes, _conf, oper_, false);
        _conf.getLastPage().setRightArgument(null);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
