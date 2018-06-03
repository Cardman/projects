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
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class SemiAffectationOperation extends AbstractUnaryOperation {
    private SettableElResult settable;
    private boolean post;
    private String oper;

    public SemiAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op, boolean _post) {
        super(_index, _indexChild, _m, _op);
        post = _post;
        oper = _op.getOperators().firstValue();
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationNode leftEl_ = getFirstChild();
        LgNames stds_ = _conf.getStandards();
        if (leftEl_ == null) {
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        settable = AffectationOperation.tryGetSettable(this);
        if (settable == null) {
            leftEl_.setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _conf);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setResultClass(settable.getResultClass());
        settable.setVariable(false);
        ClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchLeft_.getName());
            mapping_.setParam(_conf.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(cast_);
            return;
        }
        clMatchLeft_.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(clMatchLeft_, true, _conf).getName());
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        OperationNode realFirstChild_ = getFirstChild();
        if (realFirstChild_ == null) {
            CustList<StringMap<AssignmentBefore>> variablesAfterLast_ = vars_.getVariablesRootBefore();
            for (StringMap<AssignmentBefore> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    SimpleAssignment s_ = new SimpleAssignment();
                    s_.setAssignedAfter(true);
                    s_.setUnassignedAfter(true);
                    sm_.put(e.getKey(), s_);
                }
                variablesAfter_.add(sm_);
            }
            vars_.getVariables().put(this, variablesAfter_);
            ObjectMap<ClassField,AssignmentBefore> fieldsAfterLast_ = vars_.getFieldsRootBefore();
            for (EntryCust<ClassField, AssignmentBefore> e: fieldsAfterLast_.entryList()) {
                SimpleAssignment s_ = new SimpleAssignment();
                s_.setAssignedAfter(true);
                s_.setUnassignedAfter(true);
                fieldsAfter_.put(e.getKey(), s_);
            }
            vars_.getFields().put(this, fieldsAfter_);
            return;
        }
        OperationNode firstChild_ = (OperationNode) settable;
        if (firstChild_ instanceof VariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
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
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
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
        if (firstChild_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            if (cl_ != null) {
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
        if (procField_) {
            ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                if (!e.getValue().isUnassignedAfter()) {
                    ClassMetaInfo meta_ = _conf.getClassMetaInfo(cl_.getClassName());
                    if (meta_.getFieldsInfos().getVal(cl_.getFieldName()).isFinalField()) {
                        //error if final field
                        cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().getErrorsDet().add(un_);
                    }
                }
                if (cl_.eq(e.getKey()) || e.getValue().isAssignedAfter()) {
                    fieldsAfter_.put(e.getKey(), e.getValue().assignChange(isBool_, true, false));
                } else if (!cl_.eq(e.getKey()) && e.getValue().isUnassignedAfter()) {
                    fieldsAfter_.put(e.getKey(), e.getValue().assignChange(isBool_, false, true));
                } else {
                    fieldsAfter_.put(e.getKey(), e.getValue().assign(isBool_));
                }
            }
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), e.getValue().assign(isBool_));
            }
        }
        vars_.getFields().put(this, fieldsAfter_);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getOperationPageEl().setRightArgument(right_.getArgument());
        settable.calculateSetting(_conf, oper, post);
        OperationNode op_ = (OperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
        _conf.getOperationPageEl().setRightArgument(null);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        OperationNode right_ = getChildrenNodes().last();
        _conf.getLastPage().setRightArgument(_nodes.getVal(right_).getArgument());
        Argument arg_ = settable.calculateSetting(_nodes, _conf, oper, post);
        setSimpleArgument(arg_, _conf, _nodes);
        _conf.getLastPage().setRightArgument(null);
        return arg_;
    }

}
