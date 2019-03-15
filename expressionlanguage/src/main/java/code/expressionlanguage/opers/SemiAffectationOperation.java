package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class SemiAffectationOperation extends AbstractUnaryOperation  {
    private SettableElResult settable;
    private boolean post;
    private String oper;
    private ClassMethodId classMethodId;

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
    public void analyzeUnary(Analyzable _conf) {
        OperationNode leftEl_ = getFirstChild();
        LgNames stds_ = _conf.getStandards();
        settable = AffectationOperation.tryGetSettable(this);
        if (settable == null) {
            leftEl_.setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _conf);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setResultClass(settable.getResultClass());
        settable.setVariable(false);
        NatTreeMap<Integer, String> ops_ = getOperations().getOperators();
        String op_ = ops_.firstValue();
        ClassMethodIdReturn cust_ = getOperator(_conf, op_, settable.getResultClass());
        if (cust_.isFoundMethod()) {
            ClassArgumentMatching out_ = new ClassArgumentMatching(cust_.getReturnType());
            setResultClass(out_);
            String foundClass_ = cust_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            MethodId id_ = cust_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = cust_.getRealId();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            firstArgs_.add(settable.getResultClass());
            CustList<OperationNode> chidren_ = new CustList<OperationNode>();
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _conf);
            Mapping map_ = new Mapping();
            map_.setArg(out_);
            map_.setParam(settable.getResultClass());
            if (!Templates.isCorrectOrNumbers(map_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(map_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().addError(cast_);
            }
            return;
        }
        ClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchLeft_);
            mapping_.setParam(_conf.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
            return;
        }
        clMatchLeft_.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(clMatchLeft_, _conf));
        leftEl_.cancelArgument();
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        OperationNode realFirstChild_ = getFirstChild();
        OperationNode firstChild_ = (OperationNode) settable;
        if (firstChild_ instanceof VariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
            String str_ = ((VariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey())) {
                        LocalVariable locVar_ = _conf.getLocalVar(str_,index_);
                        if (!e.getValue().isUnassignedAfter()) {
                            if (locVar_ != null && locVar_.isFinalVariable()) {
                                //error
                                firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                                un_.setFileName(_conf.getCurrentFileName());
                                un_.setIndexFile(_conf.getCurrentLocationIndex());
                                _conf.getClasses().addError(un_);
                            }
                        }
                    }
                    sm_.put(e.getKey(), Assignment.assign(str_, e.getKey(), isBool_, e.getValue()));
                }
                variablesAfter_.add(sm_);
            }
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(firstChild_);
            variablesAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        if (firstChild_ instanceof MutableLoopVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(firstChild_);
            String str_ = ((MutableLoopVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey())) {
                        LoopVariable locVar_ = _conf.getMutableLoopVar(str_,index_);
                        if (!e.getValue().isUnassignedAfter()) {
                            if (locVar_ != null && locVar_.isFinalVariable()) {
                                //error
                                firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                                un_.setFileName(_conf.getCurrentFileName());
                                un_.setIndexFile(_conf.getCurrentLocationIndex());
                                _conf.getClasses().addError(un_);
                            }
                        }
                    }
                    sm_.put(e.getKey(), Assignment.assign(str_, e.getKey(), isBool_, e.getValue()));
                }
                mutableAfter_.add(sm_);
            }
            
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(firstChild_);
            mutableAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getMutableLoop().put(this, mutableAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_conf);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            if (ElUtil.checkFinalField(_conf, cst_, fieldsAfterLast_)) {
                ClassField cl_ = cst_.getFieldId();
                FieldInfo meta_ = _conf.getFieldInfo(cl_);
                if (meta_.isFinalField()) {
                    //error if final field
                    cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                    UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(un_);
                }
            }
        }
        if (fromCurClass_) {
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), Assignment.assign(cl_.getFieldName(), e.getKey(), isBool_, e.getValue()));
            }
        } else {
            if (settable == null) {
                firstChild_ = realFirstChild_;
            }
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(firstChild_);
            fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        }
        vars_.getFields().put(this, fieldsAfter_);
    }

    public boolean isPost() {
        return post;
    }

    public String getOper() {
        return oper;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

}
