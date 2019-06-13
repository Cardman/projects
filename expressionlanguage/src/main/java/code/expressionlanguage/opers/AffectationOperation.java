package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.DeclareVariable;
import code.expressionlanguage.methods.ForMutableIterativeLoop;
import code.expressionlanguage.opers.exec.*;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.*;

public final class AffectationOperation extends ReflectableOpering implements AffectationOperable {

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
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        settable = elt_;
        if (settable instanceof VariableOperation) {
            VariableOperation v_ = (VariableOperation)settable;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringVariable(v_, _conf) && _conf.getInfersLocalVars().containsStr(inf_)) {
                ClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    ClassArgumentMatching n_ = new ClassArgumentMatching(type_);
                    LocalVariable lv_ = _conf.getLocalVar(inf_);
                    lv_.setClassName(type_);
                    DeclareVariable d_ = (DeclareVariable) _conf.getCurrentBlock().getPreviousSibling();
                    d_.setImportedClassName(type_);
                    _conf.setCurrentVarSetting(type_);
                    v_.setResultClass(n_);
                }
            }
        }
        if (settable instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation v_ = (MutableLoopVariableOperation)settable;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringLoopVariable(v_, _conf) && _conf.getInfersMutableLocalVars().containsStr(inf_)) {
                ClassArgumentMatching clMatchRight_ = right_.getResultClass();
                String type_ = clMatchRight_.getSingleNameOrEmpty();
                if (!type_.isEmpty()) {
                    ClassArgumentMatching n_ = new ClassArgumentMatching(type_);
                    LoopVariable lv_ = _conf.getMutableLoopVar(inf_);
                    lv_.setClassName(type_);
                    ForMutableIterativeLoop d_ = (ForMutableIterativeLoop) _conf.getCurrentBlock();
                    d_.setImportedClassName(type_);
                    _conf.setCurrentVarSetting(type_);
                    v_.setResultClass(n_);
                }
            }
        }
        setResultClass(elt_.getResultClass());
        elt_.setVariable(true);
        ClassArgumentMatching clMatchRight_ = right_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = elt_.getResultClass();
        root_.setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);

        if (clMatchRight_.isVariable()) {
            if (!clMatchLeft_.isPrimitive(_conf)) {
                return;
            }
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchRight_);
            mapping_.setParam(clMatchLeft_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
            return;
        }
        StringMap<StringList> vars_ = _conf.getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(clMatchRight_);
        mapping_.setParam(clMatchLeft_);
        if (clMatchLeft_.isNumericInt(_conf)) {
            String primInt_ = stds_.getAliasPrimInteger();
            String primShort_ = stds_.getAliasPrimShort();
            String primChar_ = stds_.getAliasPrimChar();
            String primByte_ = stds_.getAliasPrimByte();
            String int_ = stds_.getAliasInteger();
            String short_ = stds_.getAliasShort();
            String char_ = stds_.getAliasCharacter();
            String byte_ = stds_.getAliasByte();
            Argument rightArg_ = right_.getArgument();
            if (rightArg_ != null && rightArg_.getStruct() instanceof NumberStruct) {
                StringList first_ = clMatchLeft_.getNames();
                long valueUnwrapped_ = ((NumberStruct) rightArg_.getStruct()).longStruct();
                if ((first_.containsStr(primByte_) || first_.containsStr(byte_)) && valueUnwrapped_ >= Byte.MIN_VALUE && valueUnwrapped_ <= Byte.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    return;
                }
                if ((first_.containsStr(primChar_) || first_.containsStr(char_)) && valueUnwrapped_ >= Character.MIN_VALUE && valueUnwrapped_ <= Character.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    return;
                }
                if ((first_.containsStr(primShort_) || first_.containsStr(short_))&& valueUnwrapped_ >= Short.MIN_VALUE && valueUnwrapped_ <= Short.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    return;
                }
                if ((first_.containsStr(primInt_) || first_.containsStr(int_)) && valueUnwrapped_ >= Integer.MIN_VALUE && valueUnwrapped_ <= Integer.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    return;
                }
            }
        }
        if (!Templates.isCorrectOrNumbers(mapping_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
        }
        if (PrimitiveTypeUtil.isPrimitive(clMatchLeft_, _conf)) {
            right_.getResultClass().setUnwrapObject(clMatchLeft_);
            right_.cancelArgument();
        }
    }

    static SettableElResult tryGetSettable(MethodOperation _operation) {
        Operable root_ = getFirstToBeAnalyzed(_operation);
        SettableElResult elt_;
        if (!(root_ instanceof DotOperation)) {
            elt_ = castTo(root_);
        } else {
            OperationNode beforeLast_ = ((MethodOperation)root_).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }
    public static Operable getFirstToBeAnalyzed(ParentOperable _operation) {
        Operable root_ = _operation.getFirstChild();
        while (root_ instanceof IdOperable) {
            root_ = ((IdOperable)root_).getFirstChild();
        }
        return root_;
    }
    private static SettableElResult castTo(Operable _op) {
        if (_op instanceof SettableElResult) {
            return (SettableElResult) _op;
        }
        return null;
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        if (vars_ instanceof AssignedBooleanLoopVariables) {
            ((AssignedBooleanLoopVariables)vars_).add(this, _conf);
        }
        OperationNode firstChild_ = (OperationNode) settable;
        OperationNode lastChild_ = getChildrenNodes().last();
        StringMap<Assignment> fieldsAfter_ = new StringMap<Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> mutableAfter_ = new CustList<StringMap<Assignment>>();
        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        if (firstChild_ instanceof VariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            String str_ = ((VariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = variablesAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey()) && ElUtil.checkFinalVar(_conf, e.getValue())) {
                        if (_conf.isFinalLocalVar(str_,index_)) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().addError(un_);
                        }
                    }
                    sm_.put(e.getKey(),Assignment.assign(str_,e.getKey(),isBool_, e.getValue()));
                }
                variablesAfter_.add(sm_);
            }
            
        } else {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(lastChild_);
            variablesAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getVariables().put(this, variablesAfter_);
        if (firstChild_ instanceof MutableLoopVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(lastChild_);
            String str_ = ((MutableLoopVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey()) && ElUtil.checkFinalVar(_conf, e.getValue())) {
                        if (_conf.isFinalMutableLoopVar(str_,index_)) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().addError(un_);
                        }
                    }
                    sm_.put(e.getKey(), Assignment.assign(str_,e.getKey(),isBool_, e.getValue()));
                }
                mutableAfter_.add(sm_);
            }
            
        } else {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(lastChild_);
            mutableAfter_.addAllElts(AssignmentsUtil.assignGene(isBool_,variablesAfterLast_));
        }
        vars_.getMutableLoop().put(this, mutableAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_conf);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            ClassField cl_ = cst_.getFieldId();
            if (ElUtil.checkFinalField(_conf, cst_, fieldsAfterLast_)) {
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
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            ClassField cl_ = cst_.getFieldId();
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), Assignment.assign(cl_.getFieldName(),e.getKey(),isBool_, e.getValue()));
            }
        } else {
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            fieldsAfter_.putAllMap(AssignmentsUtil.assignGene(isBool_,fieldsAfterLast_));
        }
        vars_.getFields().put(this, fieldsAfter_);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    public SettableElResult getSettable() {
        return settable;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        setArg(_conf,this,settable);
    }

    public static void setArg(Analyzable _conf, ParentOperable _current, Operable _settable) {
        if (!ElUtil.isInlineDeclaringField(_settable, _conf)) {
            return;
        }
        StandardFieldOperable fieldRef_ = (StandardFieldOperable) _settable;
        Operable lastChild_ = _current.getFirstChild().getNextSibling();
        Argument value_ = lastChild_.getArgument();
        ClassField id_ = fieldRef_.getFieldId();
        FieldInfo fm_ = _conf.getFieldInfo(id_);
        Struct str_ = value_.getStruct();
        LgNames stds_ = _conf.getStandards();
        String to_ = fm_.getType();
        str_ = PrimitiveTypeUtil.unwrapObject(to_, str_, stds_);
        _conf.getClasses().initializeStaticField(id_, str_);
        _current.setSimpleArgument(value_);
    }
}