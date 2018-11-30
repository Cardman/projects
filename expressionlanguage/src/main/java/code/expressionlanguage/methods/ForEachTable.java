package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public class ForEachTable extends BracedStack implements Loop {

    private String label;
    private int labelOffset;

    private final String classNameFirst;

    private String importedClassNameFirst;

    private int classNameOffsetFirst;

    private final String classNameSecond;

    private String importedClassNameSecond;

    private int classNameOffsetSecond;

    private final String classIndexName;

    private int classIndexNameOffset;

    private final String variableNameFirst;

    private int variableNameOffsetFirst;

    private final String variableNameSecond;

    private int variableNameOffsetSecond;

    private final String expression;

    private int expressionOffset;

    private CustList<OperationNode> opList;

    public ForEachTable(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _classNameSec, OffsetStringInfo _variableSec,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        classNameFirst = _className.getInfo();
        classNameOffsetFirst = _className.getOffset();
        variableNameFirst = _variable.getInfo();
        variableNameOffsetFirst = _variable.getOffset();
        classNameSecond = _classNameSec.getInfo();
        classNameOffsetSecond = _classNameSec.getOffset();
        variableNameSecond = _variableSec.getInfo();
        variableNameOffsetSecond = _variableSec.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        label = _label.getInfo();
        labelOffset = _label.getOffset();
        classIndexNameOffset = _classIndex.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    public int getClassNameOffsetFirst() {
        return classNameOffsetFirst;
    }

    public int getVariableNameOffsetFirst() {
        return variableNameOffsetFirst;
    }

    public int getClassNameOffsetSecond() {
        return classNameOffsetSecond;
    }

    public int getVariableNameOffsetSecond() {
        return variableNameOffsetSecond;
    }
    public int getExpressionOffset() {
        return expressionOffset;
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
    }

    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<String, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            SimpleAssignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        for (StringMap<SimpleAssignment> s: parAss_.getMutableLoopRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getMutableLoopRootBefore().add(sm_);
        }
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }
    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEl(_cont);
        OperationNode el_ = opList.last();
        Argument arg_ = el_.getArgument();
        if (Argument.isNullValue(arg_)) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_cont.getCurrentFileName());
            static_.setRc(_cont.getCurrentLocation());
            _cont.getClasses().addError(static_);
        } else {
            StringList names_ = el_.getResultClass().getNames();
            StringList out_ = getCustomType(names_, _cont);
            checkIterableCandidates(out_, _cont);
        }
        putVariable(_cont);
    }
    private StringList getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        LgNames stds_ = _context.getStandards();
        for (String f: _names) {
            String iterable_ = stds_.getAliasIterableTable();
            String type_ = Templates.getFullTypeByBases(f, iterable_, _context);
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return out_;
    }
    public void buildEl(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(classIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(classIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, classIndexNameOffset));
            _cont.getClasses().addError(cast_);
        }
        if (_cont.getAnalyzing().containsVar(variableNameFirst)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameFirst);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffsetFirst));
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameFirst)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameFirst);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffsetFirst));
            _cont.getClasses().addError(d_);
        }
        if (!StringList.isWord(variableNameFirst)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetFirst));
            b_.setVarName(variableNameFirst);
            _cont.getClasses().addError(b_);
        }
        if (_cont.getKeyWords().isKeyWordNotVar(variableNameFirst)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetFirst));
            b_.setVarName(variableNameFirst);
            _cont.getClasses().addError(b_);
        }
        if (PrimitiveTypeUtil.isPrimitive(variableNameFirst, _cont)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetFirst));
            b_.setVarName(variableNameFirst);
            _cont.getClasses().addError(b_);
        }
        if (StringList.quickEq(variableNameFirst, _cont.getStandards().getAliasVoid())) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetFirst));
            b_.setVarName(variableNameFirst);
            _cont.getClasses().addError(b_);
        }
        Options opt_ = _cont.getOptions();
        if (opt_.getSuffixVar() == VariableSuffix.NONE) {
            if (!variableNameFirst.isEmpty() && ContextEl.isDigit(variableNameFirst.charAt(0))) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(getFile().getFileName());
                b_.setRc(getRowCol(0, variableNameOffsetFirst));
                b_.setVarName(variableNameFirst);
                _cont.getClasses().addError(b_);
            }
        }
        if (opt_.getSuffixVar() != VariableSuffix.DISTINCT) {
            if (_cont.getAnalyzing().containsCatchVar(variableNameFirst)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableNameFirst);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffsetFirst));
                _cont.getClasses().addError(d_);
            }
            if (_cont.getAnalyzing().containsLocalVar(variableNameFirst)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableNameFirst);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffsetFirst));
                _cont.getClasses().addError(d_);
            }
            if (_cont.getParameters().contains(variableNameFirst)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableNameFirst);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffsetFirst));
                _cont.getClasses().addError(d_);
            }
        }
        if (_cont.getAnalyzing().containsVar(variableNameSecond)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameSecond);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffsetSecond));
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableNameSecond)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameSecond);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffsetSecond));
            _cont.getClasses().addError(d_);
        }
        if (!StringList.isWord(variableNameSecond)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetSecond));
            b_.setVarName(variableNameSecond);
            _cont.getClasses().addError(b_);
        }
        if (_cont.getKeyWords().isKeyWordNotVar(variableNameSecond)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetSecond));
            b_.setVarName(variableNameSecond);
            _cont.getClasses().addError(b_);
        }
        if (PrimitiveTypeUtil.isPrimitive(variableNameSecond, _cont)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetSecond));
            b_.setVarName(variableNameSecond);
            _cont.getClasses().addError(b_);
        }
        if (StringList.quickEq(variableNameSecond, _cont.getStandards().getAliasVoid())) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffsetSecond));
            b_.setVarName(variableNameSecond);
            _cont.getClasses().addError(b_);
        }
        if (opt_.getSuffixVar() == VariableSuffix.NONE) {
            if (!variableNameSecond.isEmpty() && ContextEl.isDigit(variableNameSecond.charAt(0))) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(getFile().getFileName());
                b_.setRc(getRowCol(0, variableNameOffsetSecond));
                b_.setVarName(variableNameSecond);
                _cont.getClasses().addError(b_);
            }
        }
        if (opt_.getSuffixVar() != VariableSuffix.DISTINCT) {
            if (_cont.getAnalyzing().containsCatchVar(variableNameSecond)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableNameSecond);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffsetSecond));
                _cont.getClasses().addError(d_);
            }
            if (_cont.getAnalyzing().containsLocalVar(variableNameSecond)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableNameSecond);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffsetSecond));
                _cont.getClasses().addError(d_);
            }
            if (_cont.getParameters().contains(variableNameSecond)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(variableNameSecond);
                d_.setFileName(getFile().getFileName());
                d_.setRc(getRowCol(0, variableNameOffsetSecond));
                _cont.getClasses().addError(d_);
            }
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffsetFirst);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
            importedClassNameFirst = _cont.resolveCorrectType(classNameFirst);
        } else {
            importedClassNameFirst = "";
        }
        page_.setGlobalOffset(classNameOffsetSecond);
        page_.setOffset(0);
        if (!StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
            importedClassNameSecond = _cont.resolveCorrectType(classNameSecond);
        } else {
            importedClassNameSecond = "";
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opList.isEmpty()) {
            return;
        }
        OperationNode el_ = opList.last();
        el_.getResultClass().setCheckOnlyNullPe(true);
    }
    public void checkIterableCandidates(StringList _types,ContextEl _cont) {
        if (opList.isEmpty()) {
            return;
        }
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (_types.size() == 1) {
            KeyWords keyWords_ = _cont.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            String type_ = _types.first();
            Mapping mapping_ = new Mapping();
            String paramArg_ = Templates.getAllTypes(type_).get(1);
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameFirst.trim(), keyWordVar_)) {
                importedClassNameFirst = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameFirst);
                StringMap<StringList> vars_ = new StringMap<StringList>();
                if (!f_.isStaticContext()) {
                    String globalClass_ = page_.getGlobalClass();
                    String curClassBase_ = Templates.getIdFromAllTypes(globalClass_);
                    for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypesMapValues()) {
                        vars_.put(t.getName(), t.getConstraints());
                    }
                }
                mapping_.setMapping(vars_);
                if (!Templates.isGenericCorrect(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setRc(getRowCol(0, expressionOffset));
                    _cont.getClasses().addError(cast_);
                }
            }
            mapping_ = new Mapping();
            paramArg_ = Templates.getAllTypes(type_).last();
            if (StringList.quickEq(paramArg_, Templates.SUB_TYPE)) {
                paramArg_ = _cont.getStandards().getAliasObject();
            } else if (paramArg_.startsWith(Templates.SUB_TYPE)) {
                paramArg_ = paramArg_.substring(Templates.SUB_TYPE.length());
            } else if (paramArg_.startsWith(Templates.SUP_TYPE)){
                paramArg_ = _cont.getStandards().getAliasObject();
            }
            if (StringList.quickEq(classNameSecond.trim(), keyWordVar_)) {
                importedClassNameSecond = paramArg_;
            } else {
                mapping_.setArg(paramArg_);
                mapping_.setParam(importedClassNameSecond);
                StringMap<StringList> vars_ = new StringMap<StringList>();
                if (!f_.isStaticContext()) {
                    String globalClass_ = page_.getGlobalClass();
                    String curClassBase_ = Templates.getIdFromAllTypes(globalClass_);
                    for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypesMapValues()) {
                        vars_.put(t.getName(), t.getConstraints());
                    }
                }
                mapping_.setMapping(vars_);
                if (!Templates.isGenericCorrect(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setRc(getRowCol(0, expressionOffset));
                    _cont.getClasses().addError(cast_);
                }
            }
        } else {
            if (_types.isEmpty()) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(_cont.getStandards().getAliasObject());
                mapping_.setParam(_cont.getStandards().getAliasIterableTable());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setRc(getRowCol(0, expressionOffset));
                _cont.getClasses().addError(cast_);
            }
            String iterable_ = _cont.getStandards().getAliasIterableTable();
            for (String e: _types) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(e);
                mapping_.setParam(iterable_);
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setRc(getRowCol(0, expressionOffset));
                _cont.getClasses().addError(cast_);
            }
        }
    }
    public void putVariable(ContextEl _cont) {
        if (StringList.quickEq(variableNameFirst, variableNameSecond)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableNameSecond);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffsetSecond));
            _cont.getClasses().addError(d_);
        }
        LoopVariable lv_ = new LoopVariable();
        if (!importedClassNameFirst.isEmpty()) {
            lv_.setClassName(importedClassNameFirst);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(classIndexName);
        _cont.getAnalyzing().putVar(variableNameFirst, lv_);
        lv_ = new LoopVariable();
        if (!importedClassNameSecond.isEmpty()) {
            lv_.setClassName(importedClassNameSecond);
        } else {
            lv_.setClassName(_cont.getStandards().getAliasObject());
        }
        lv_.setIndexClassName(classIndexName);
        _cont.getAnalyzing().putVar(variableNameSecond, lv_);
        buildConditions(_cont);
    }
    public CustList<OperationNode> getOpList() {
        return opList;
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        AssignedBooleanVariables varsWhile_ = null;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
                varsWhile_ = (AssignedBooleanVariables) e.getValue();
            } else if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        if (firstChild_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
            StringMap<SimpleAssignment> fieldsAfter_;
            fieldsAfter_ = buildAssListFieldAfterLoop(_an, _anEl);
            varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
            CustList<StringMap<SimpleAssignment>> varsAfter_;
            varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
            varsWhile_.getVariablesRoot().clear();
            varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
            CustList<StringMap<SimpleAssignment>> mutableAfter_;
            mutableAfter_ = buildAssListMutableLoopAfterLoop(_an, _anEl);
            varsWhile_.getMutableLoopRoot().clear();
            varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
            return;
        }
        StringMap<AssignmentBefore> fieldsHypot_;
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        CustList<StringMap<AssignmentBefore>> mutableHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_an, _anEl);
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_an, _anEl);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
        mutableHypot_ = buildAssListMutableLoopInvalHypot(_an, _anEl);
        varsWhile_.getMutableLoopRootBefore().clear();
        varsWhile_.getMutableLoopRootBefore().addAllElts(mutableHypot_);
        processFinalFields(_an, _anEl, allDesc_, varsWhile_, fieldsHypot_);
        processFinalVars(_an, _anEl, allDesc_, varsWhile_, varsHypot_);
        processFinalMutableLoop(_an, _anEl, allDesc_, varsWhile_, mutableHypot_);
        StringMap<SimpleAssignment> fieldsAfter_;
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        CustList<StringMap<SimpleAssignment>> mutableAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfterLoop(_an, _anEl);
        varsWhile_.getMutableLoopRoot().clear();
        varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
    }
    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block first_ = getFirstChild();
        Block last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisFields(_an);
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            ContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        if (_anEl.canCompleteNormallyGroup(last_)) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getFieldsRoot();
            return invalidateHypothesis(list_, v_, breakAss_);
        }
        return invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block first_ = getFirstChild();
        Block last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = first_.makeHypothesisVars(_an);
        list_ = list_.mid(0, list_.size() - 1);
        int contLen_ = continues_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < contLen_; j++) {
                ContinueBlock br_ = continues_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getVariablesRootBefore();
                if (!vars_.isValidIndex(i)) {
                    continue;
                }
                breakAss_.add(vars_.get(i));
            }
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getVariablesRoot();
                if (v_.isValidIndex(i)) {
                    varsList_.add(invalidateHypothesis(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(invalidateHypothesis(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(Analyzable _an, AnalyzingEl _anEl) {
        Block first_ = getFirstChild();
        Block last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<ContinueBlock> continues_ = getContinuables(_anEl);
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = first_.makeHypothesisMutableLoop(_an);
        list_ = list_.mid(0, list_.size() - 1);
        int contLen_ = continues_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < contLen_; j++) {
                ContinueBlock br_ = continues_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getMutableLoopRootBefore();
                if (!vars_.isValidIndex(i)) {
                    continue;
                }
                breakAss_.add(vars_.get(i));
            }
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (_anEl.canCompleteNormallyGroup(last_)) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getMutableLoopRoot();
                if (v_.isValidIndex(i)) {
                    varsList_.add(invalidateHypothesis(cond_, v_.get(i), breakAss_));
                }
            } else {
                varsList_.add(invalidateHypothesis(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }
        
        return varsList_;
    }
    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
            CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (StringMap<AssignmentBefore> c: _continuable) {
                if (!c.contains(key_)) {
                    continue;
                }
                if (!c.getVal(key_).isUnassignedBefore()) {
                    ass_.setUnassignedBefore(false);
                }
            }
            if (_last.contains(key_)) {
                if (!_last.getVal(key_).isUnassignedAfter()) {
                    ass_.setUnassignedBefore(false);
                }
            }
            out_.put(key_, ass_);
        }
        return out_;
    }
    @Override
    protected AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }

    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isEvaluatingKeepLoop()) {
                processLastElementLoop(_cont);
                return;
            }
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        LgNames stds_ = _cont.getStandards();
        Struct its_ = processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        Classes cls_ = _cont.getClasses();
        String locName_ = cls_.getIteratorTableVarCust();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(its_, _cont));
        locVar_.setStruct(its_);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, CustList.SECOND_INDEX,CustList.SECOND_INDEX);
        Argument arg_ = dyn_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        _cont.getLastPage().clearCurrentEls();
        iterStr_ = arg_.getStruct();
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassNameFirst);
        lv_.setIndexClassName(classIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameFirst, lv_);
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setClassName(importedClassNameSecond);
        lv_.setIndexClassName(classIndexName);
        lv_.setContainer(its_);
        varsLoop_.put(variableNameSecond, lv_);
        if (iterStr_ != null) {
            Boolean has_ = iteratorHasNext(_cont);
            if (has_ == null) {
                return;
            }
            finished_ = !has_;
            if (_cont.callsOrException()) {
                return;
            }
        }
        if (finished_) {
            removeVarAndLoop(ip_);
            _cont.getLastPage().clearCurrentEls();
            l_.setEvaluatingKeepLoop(false);
            processBlock(_cont);
            return;
        }
        StringMap<LoopVariable> vars_ = ip_.getVars();
        incrementLoop(_cont, l_, vars_);
        if (_cont.callsOrException()) {
            return;
        }
        l_.setEvaluatingKeepLoop(false);
        ip_.getReadWrite().setBlock(getFirstChild());
    }
    Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument arg_ = el_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        return ito_;
        
    }
    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeVarAndLoop(AbstractPageEl _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        l_.setEvaluatingKeepLoop(true);
        boolean hasNext_;
        if (l_.getStructIterator() != null) {
            Boolean has_ = iteratorHasNext(_conf);
            if (has_ == null) {
                return;
            }
            hasNext_ = has_;
        } else {
            hasNext_ = l_.hasNext();
        }
        
        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
            if (_conf.callsOrException()) {
                if (_conf.calls()) {
                    return;
                }
                l_.setEvaluatingKeepLoop(false);
                return;
            }
        } else {
            _conf.getLastPage().clearCurrentEls();
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        Classes cls_ = _conf.getClasses();
        LgNames stds_ = _conf.getStandards();
        Struct iterator_ = _l.getStructIterator();
        AbstractPageEl call_ = _conf.getLastPage();
        if (call_.sizeEl() < 2) {
            String locName_ = cls_.getNextPairVarCust();
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(iterator_, _conf));
            locVar_.setStruct(iterator_);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage nextEl_ = call_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, 3);
        nextEl_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        String classNameFirst_ = _conf.getLastPage().formatVarType(importedClassNameFirst, _conf);
        if (call_.sizeEl() < 3) {
            String locName_ = cls_.getFirstVarCust();
            LocalVariable locVar_ = new LocalVariable();
            Struct value_ = call_.getValue(1).getStruct();
            locVar_.setClassName(stds_.getStructClassName(value_, _conf));
            locVar_.setStruct(value_);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage firstEl_ = call_.getCurrentEl(_conf,this, 2, 4);
        Argument arg_ = firstEl_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        if (!Templates.checkObject(classNameFirst_, arg_, _conf)) {
            return;
        }
        String classNameSecond_ = _conf.getLastPage().formatVarType(importedClassNameSecond, _conf);
        if (call_.sizeEl() < 4) {
        	LoopVariable lv_ = _vars.getVal(variableNameFirst);
            lv_.setStruct(arg_.getStruct());
            lv_.setIndex(lv_.getIndex() + 1);
            String locName_ = cls_.getSecondVarCust();
            LocalVariable locVar_ = new LocalVariable();
            Struct value_ = call_.getValue(1).getStruct();
            locVar_.setClassName(stds_.getStructClassName(value_, _conf));
            locVar_.setStruct(value_);
            _conf.getLastPage().getInternVars().put(locName_, locVar_);
        }
        ExpressionLanguage secondEl_ = call_.getCurrentEl(_conf,this, 3, 5);
        arg_ = secondEl_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return;
        }
        if (!Templates.checkObject(classNameSecond_, arg_, _conf)) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameSecond);
        lv_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        call_.clearCurrentEls();
    }
    private Boolean iteratorHasNext(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LgNames stds_ = _conf.getStandards();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        Classes cls_ = _conf.getClasses();
        String locName_ = cls_.getHasNextPairVarCust();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(strIter_, _conf));
        locVar_.setStruct(strIter_);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
        Argument arg_ = dyn_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return null;
        }
        boolean hasNext_ = (Boolean) arg_.getObject();
        return hasNext_;
    }
    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
        }
        Classes cls_ = _context.getClasses();
        if (_indexProcess == 1) {
            return new ExpressionLanguage(cls_.getExpsIteratorTableCust());
        }
        if (_indexProcess == 2) {
            return new ExpressionLanguage(cls_.getExpsHasNextPairCust());
        }
        if (_indexProcess == 3) {
            return new ExpressionLanguage(cls_.getExpsNextPairCust());
        }
        if (_indexProcess == 4) {
            return new ExpressionLanguage(cls_.getExpsFirstCust());
        }
        return new ExpressionLanguage(cls_.getExpsSecondCust());
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

}
