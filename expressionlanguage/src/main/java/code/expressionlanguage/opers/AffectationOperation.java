package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.DeclareVariable;
import code.expressionlanguage.methods.ForMutableIterativeLoop;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class AffectationOperation extends ReflectableOpering {

    private SettableElResult settable;

    private boolean convertNumber;
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
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (settable instanceof VariableOperation) {
            VariableOperation v_ = (VariableOperation)elt_;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringVariable(v_, _conf) && _conf.getInfersLocalVars().containsStr(inf_)) {
                String c_ = _conf.getCurrentVarSetting();
                if (StringList.quickEq(c_, keyWordVar_)) {
                    ClassArgumentMatching clMatchRight_ = right_.getResultClass();
                    StringList names_ = clMatchRight_.getNames();
                    if (names_.size() == 1) {
                        ClassArgumentMatching n_ = new ClassArgumentMatching(names_);
                        LocalVariable lv_ = _conf.getLocalVar(inf_);
                        if (lv_ != null) {
                            lv_.setClassName(names_.first());
                            DeclareVariable d_ = (DeclareVariable) _conf.getCurrentBlock().getPreviousSibling();
                            d_.setImportedClassName(names_.first());
                            _conf.setCurrentVarSetting(names_.first());
                        }
                        v_.setResultClass(n_);
                    }
                }
            }
        }
        if (settable instanceof MutableLoopVariableOperation) {
            MutableLoopVariableOperation v_ = (MutableLoopVariableOperation)elt_;
            String inf_ = v_.getVariableName();
            if (ElUtil.isDeclaringLoopVariable(v_, _conf) && _conf.getInfersMutableLocalVars().containsStr(inf_)) {
                String c_ = _conf.getCurrentVarSetting();
                if (StringList.quickEq(c_, keyWordVar_)) {
                    ClassArgumentMatching clMatchRight_ = right_.getResultClass();
                    StringList names_ = clMatchRight_.getNames();
                    if (names_.size() == 1) {
                        ClassArgumentMatching n_ = new ClassArgumentMatching(names_);
                        LoopVariable lv_ = _conf.getMutableLoopVar(inf_);
                        if (lv_ != null) {
                            lv_.setClassName(names_.first());
                            ForMutableIterativeLoop d_ = (ForMutableIterativeLoop) _conf.getCurrentBlock();
                            d_.setImportedClassName(names_.first());
                            _conf.setCurrentVarSetting(names_.first());
                        }
                        v_.setResultClass(n_);
                    }
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
                Number value_ = ((NumberStruct) rightArg_.getStruct()).getInstance();
                StringList first_ = clMatchLeft_.getNames();
                long valueUnwrapped_ = value_.longValue();
                if (first_.containsStr(primByte_) && valueUnwrapped_ >= Byte.MIN_VALUE && valueUnwrapped_ <= Byte.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(primChar_) && valueUnwrapped_ >= Character.MIN_VALUE && valueUnwrapped_ <= Character.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(primShort_) && valueUnwrapped_ >= Short.MIN_VALUE && valueUnwrapped_ <= Short.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(primInt_) && valueUnwrapped_ >= Integer.MIN_VALUE && valueUnwrapped_ <= Integer.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(byte_) && valueUnwrapped_ >= Byte.MIN_VALUE && valueUnwrapped_ <= Byte.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(char_) && valueUnwrapped_ >= Character.MIN_VALUE && valueUnwrapped_ <= Character.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(short_) && valueUnwrapped_ >= Short.MIN_VALUE && valueUnwrapped_ <= Short.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
                if (first_.containsStr(int_) && valueUnwrapped_ >= Integer.MIN_VALUE && valueUnwrapped_ <= Integer.MAX_VALUE) {
                    right_.getResultClass().setUnwrapObject(clMatchLeft_);
                    convertNumber = true;
                    return;
                }
            }
        }
        if (!Templates.isCorrect(mapping_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(cast_);
        }
        if (PrimitiveTypeUtil.isPrimitive(clMatchLeft_, _conf)) {
            right_.getResultClass().setUnwrapObject(clMatchLeft_);
        }
    }

    static SettableElResult tryGetSettable(MethodOperation _operation) {
        OperationNode root_ = _operation.getFirstChild();
        SettableElResult elt_ = null;
        while (root_ instanceof IdOperation) {
            root_ = ((IdOperation)root_).getFirstChild();
        }
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
                        LocalVariable locVar_ = _conf.getLocalVar(str_,index_);
                        if (locVar_ != null && locVar_.isFinalVariable()) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().addError(un_);
                        }
                    }
                    boolean ass_ = StringList.quickEq(str_, e.getKey()) || e.getValue().isAssignedAfter();
                    boolean unass_ = !StringList.quickEq(str_, e.getKey()) && e.getValue().isUnassignedAfter();
                    sm_.put(e.getKey(), Assignment.assign(isBool_, ass_, unass_));
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
        if (firstChild_ instanceof MutableLoopVariableOperation) {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(lastChild_);
            String str_ = ((MutableLoopVariableOperation)firstChild_).getVariableName();
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = mutableAfter_.size();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    if (StringList.quickEq(str_, e.getKey()) && ElUtil.checkFinalVar(_conf, e.getValue())) {
                        LoopVariable locVar_ = _conf.getMutableLoopVar(str_,index_);
                        if (locVar_ != null && locVar_.isFinalVariable()) {
                            //error
                            firstChild_.setRelativeOffsetPossibleAnalyzable(firstChild_.getIndexInEl(), _conf);
                            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().addError(un_);
                        }
                    }
                    boolean ass_ = StringList.quickEq(str_, e.getKey()) || e.getValue().isAssignedAfter();
                    boolean unass_ = !StringList.quickEq(str_, e.getKey()) && e.getValue().isUnassignedAfter();
                    sm_.put(e.getKey(), Assignment.assign(isBool_, ass_, unass_));
                }
                mutableAfter_.add(sm_);
            }
            
        } else {
            CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getMutableLoop().getVal(lastChild_);
            for (StringMap<Assignment> s: variablesAfterLast_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, Assignment> e: s.entryList()) {
                    sm_.put(e.getKey(), e.getValue().assign(isBool_));
                }
                mutableAfter_.add(sm_);
            }
        }
        vars_.getMutableLoop().put(this, mutableAfter_);
        boolean fromCurClass_ = false;
        if (firstChild_ instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)firstChild_;
            fromCurClass_ = cst_.isFromCurrentClass(_conf);
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            ClassField cl_ = cst_.getFieldId();
//            String fieldName_ = "";
//            if (cl_ != null) {
//                fieldName_ = cl_.getFieldName();
//            }
//            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
//                if (fromCurClass_ && StringList.quickEq(fieldName_, e.getKey()) && ElUtil.checkFinalField(_conf, cst_, fieldsAfterLast_)) {
//                    FieldInfo meta_ = _conf.getFieldInfo(cl_);
//                    if (meta_ != null && meta_.isFinalField()) {
//                        //error if final field
//                        cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
//                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
//                        un_.setFileName(_conf.getCurrentFileName());
//                        un_.setIndexFile(_conf.getCurrentLocationIndex());
//                        _conf.getClasses().addError(un_);
//                    }
//                }
//                boolean ass_ = StringList.quickEq(fieldName_, e.getKey()) || e.getValue().isAssignedAfter();
//                boolean unass_ = !StringList.quickEq(fieldName_,e.getKey()) && e.getValue().isUnassignedAfter();
//                fieldsAfter_.put(e.getKey(), e.getValue().assignChange(isBool_, ass_, unass_));
//            }
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
                boolean ass_ = StringList.quickEq(cl_.getFieldName(), e.getKey()) || e.getValue().isAssignedAfter();
                boolean unass_ = !StringList.quickEq(cl_.getFieldName(),e.getKey()) && e.getValue().isUnassignedAfter();
                fieldsAfter_.put(e.getKey(), Assignment.assign(isBool_, ass_, unass_));
            }
        } else {
            StringMap<Assignment> fieldsAfterLast_ = vars_.getFields().getVal(lastChild_);
            for (EntryCust<String, Assignment> e: fieldsAfterLast_.entryList()) {
                fieldsAfter_.put(e.getKey(), e.getValue().assign(isBool_));
            }
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
        if (!ElUtil.isDeclaringField(settable, _conf)) {
            return;
        }
        StandardFieldOperation fieldRef_ = (StandardFieldOperation) settable;
        OperationNode lastChild_ = getFirstChild().getNextSibling();
        Argument value_ = lastChild_.getArgument();
        ClassField id_ = fieldRef_.getFieldId();
        if (id_ == null) {
            return;
        }
        if (!_conf.isGearConst()) {
            return;
        }
        FieldInfo fm_ = _conf.getFieldInfo(id_);
        Struct str_ = value_.getStruct();
        LgNames stds_ = _conf.getStandards();
        String to_ = fm_.getType();
        str_ = PrimitiveTypeUtil.unwrapObject(to_, str_, stds_);
        _conf.getClasses().initializeStaticField(id_, str_);
        setSimpleArgument(value_);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        OperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = right_.getArgument();
        settable.calculateSetting(_conf, rightArg_, convertNumber);
        OperationNode op_ = (OperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        OperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = _nodes.getVal(right_).getArgument();
        Argument arg_ = settable.calculateSetting(_nodes, _conf, rightArg_, convertNumber);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

}
