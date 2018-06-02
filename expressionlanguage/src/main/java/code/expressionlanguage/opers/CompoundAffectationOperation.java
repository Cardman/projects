package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class CompoundAffectationOperation extends MethodOperation {

    private SettableElResult settable;

    private String oper;

    public CompoundAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = _op.getOperators().firstValue();
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode root_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = tryGetSettable(this);
        boolean ok_ = elt_ != null;
        LgNames stds_ = _conf.getStandards();
        if (!ok_) {
            root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        settable = elt_;
        setResultClass(elt_.getResultClass());
        elt_.setVariable(false);
        String stringType_ = stds_.getAliasString();
        String res_ = elt_.getResultClass().getName();
        if (StringList.quickEq(res_, stringType_)) {
            settable.setCatenizeStrings();
        }
        ClassArgumentMatching clMatchRight_ = right_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = elt_.getResultClass();
        root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);

        Mapping mapping_ = new Mapping();
        mapping_.setArg(clMatchRight_.getName());
        mapping_.setParam(clMatchLeft_.getName());
        BadImplicitCast cast_ = new BadImplicitCast();
        cast_.setMapping(mapping_);
        cast_.setFileName(_conf.getCurrentFileName());
        cast_.setRc(_conf.getCurrentLocation());
        if (StringList.quickEq(oper, Block.EQ_PLUS) || StringList.quickEq(oper, Block.PLUS_EQ)) {
            if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                if (!clMatchLeft_.matchClass(_conf.getStandards().getAliasString())) {
                    _conf.getClasses().getErrorsDet().add(cast_);
                    return;
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                _conf.getClasses().getErrorsDet().add(cast_);
                return;
            }
        } else if (StringList.quickEq(oper, Block.AND_EQ) || StringList.quickEq(oper, Block.OR_EQ)) {
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
        if (!StringList.quickEq(res_, stringType_)) {
            elt_.getResultClass().setUnwrapObject(clMatchLeft_.getName());
            right_.getResultClass().setUnwrapObject(clMatchLeft_.getName());
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
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        if (firstChild_ instanceof VariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            String str_ = ((VariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey())) {
                        LocalVariable locVar_ = _conf.getLocalVariables().get(index_).getVal(str_);
                        if (!e.getValue().isUnassignedAfter()) {
                            if (locVar_.isFinalVariable()) {
                                //error
                                firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                                un_.setFileName(_conf.getCurrentFileName());
                                un_.setRc(_conf.getCurrentLocation());
                                _conf.getClasses().getErrorsDet().add(un_);
                            }
                        }
                    }
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
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
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
        boolean procFinalField_ = false;
        if (firstChild_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            if (cl_ != null) {
                procFinalField_ = true;
                if (cst_.isFirstChild()) {
                    procField_ = true;
                } else {
                    int index_ = cst_.getIndexChild() - 1;
                    OperationNode opPr_ = cst_.getParent().getChildrenNodes().get(index_);
                    if (opPr_ instanceof ThisOperation) {
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
        if (procFinalField_) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                if (!e.getValue().isUnassignedAfter() && cl_.eq(e.getKey())) {
                    ClassMetaInfo meta_ = _conf.getClassMetaInfo(cl_.getClassName());
                    if (meta_.getFields().getVal(cl_.getFieldName()).isFinalField()) {
                        //error if final field
                        cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(un_);
                    }
                }
            }
        }
        if (procField_) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
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
            ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), e.getValue().assign(isBool_));
            }
        }
        vars_.getFields().put(this, fieldsAfter_);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
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
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }
    public SettableElResult getSettable() {
        return settable;
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getOperationPageEl().setRightArgument(right_.getArgument());
        settable.calculateSetting(_conf, oper, false);
        OperationNode op_ = (OperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
        _conf.getOperationPageEl().setRightArgument(null);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getLastPage().setRightArgument(_nodes.getVal(right_).getArgument());
        Argument arg_ = settable.calculateSetting(_nodes, _conf, oper, false);
        _conf.getLastPage().setRightArgument(null);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
