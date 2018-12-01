package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.errors.custom.UndefinedVariableError;
import code.expressionlanguage.errors.custom.UnexpectedOperationAffect;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class VariableOperation extends LeafOperation implements
        SettableElResult {

    private boolean variable;

    private boolean excVar;

    private boolean catString;

    private String variableName = EMPTY_STRING;

    public VariableOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    @Override
    public void setCatenizeStrings() {
        catString = true;
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        if (ElUtil.isDeclaringVariable(this, _conf)) {
            AnalyzedPageEl page_ = _conf.getAnalyzing();
            if (_conf.containsLocalVar(str_)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(str_);
                d_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                d_.setRc(page_.getTrace());
                _conf.getClasses().addError(d_);
                setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                return;
            }
            if (!StringList.isWord(str_)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                b_.setRc(page_.getTrace());
                b_.setVarName(str_);
                _conf.getClasses().addError(b_);
            }
            if (_conf.getKeyWords().isKeyWordNotVar(str_)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                b_.setRc(page_.getTrace());
                b_.setVarName(str_);
                _conf.getClasses().addError(b_);
            }
            if (PrimitiveTypeUtil.isPrimitive(str_, _conf)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                b_.setRc(page_.getTrace());
                b_.setVarName(str_);
                _conf.getClasses().addError(b_);
            }
            if (StringList.quickEq(str_, _conf.getStandards().getAliasVoid())) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                b_.setRc(page_.getTrace());
                b_.setVarName(str_);
                _conf.getClasses().addError(b_);
            }
            Options opt_ = _conf.getOptions();
            if (opt_.getSuffixVar() == VariableSuffix.NONE) {
                if (!str_.isEmpty() && ContextEl.isDigit(str_.charAt(0))) {
                    BadVariableName b_ = new BadVariableName();
                    b_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                    b_.setRc(page_.getTrace());
                    b_.setVarName(str_);
                    _conf.getClasses().addError(b_);
                }
            }
            if (opt_.getSuffixVar() != VariableSuffix.DISTINCT) {
                if (_conf.getAnalyzing().containsCatchVar(variableName)) {
                    DuplicateVariable d_ = new DuplicateVariable();
                    d_.setId(str_);
                    d_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                    d_.setRc(page_.getTrace());
                    _conf.getClasses().addError(d_);
                    setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                    return;
                }
                if (_conf.getAnalyzing().containsMutableLoopVar(str_)) {
                    DuplicateVariable d_ = new DuplicateVariable();
                    d_.setId(str_);
                    d_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                    d_.setRc(page_.getTrace());
                    _conf.getClasses().addError(d_);
                    setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                    return;
                }
                if (_conf.getAnalyzing().containsVar(str_)) {
                    DuplicateVariable d_ = new DuplicateVariable();
                    d_.setId(str_);
                    d_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                    d_.setRc(page_.getTrace());
                    _conf.getClasses().addError(d_);
                    setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                    return;
                }
                if (_conf.getParameters().contains(str_)) {
                    DuplicateVariable d_ = new DuplicateVariable();
                    d_.setId(str_);
                    d_.setFileName(page_.getCurrentBlock().getFile().getFileName());
                    d_.setRc(page_.getTrace());
                    _conf.getClasses().addError(d_);
                    setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
                    return;
                }
            }
            String c_ = _conf.getCurrentVarSetting();
            KeyWords keyWords_ = _conf.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(c_, keyWordVar_)) {
                _conf.putLocalVar(str_);
            }
            LocalVariable lv_ = new LocalVariable();
            if (StringList.quickEq(c_, keyWordVar_)) {
                lv_.setClassName(_conf.getStandards().getAliasObject());
            } else {
                lv_.setClassName(c_);
            }
            lv_.setFinalVariable(_conf.isFinalVariable());
            _conf.putLocalVar(str_, lv_);
            _conf.getVariablesNames().add(str_);
            excVar = true;
        }
        variableName = str_;
        if (excVar) {
            setResultClass(new ClassArgumentMatching(_conf.getCurrentVarSetting()));
            return;
        }
        LocalVariable locVar_ = _conf.getLocalVar(variableName);
        if (locVar_ != null) {
            String c_ = locVar_.getClassName();
            setResultClass(new ClassArgumentMatching(c_));
            return;
        }
        UndefinedVariableError und_ = new UndefinedVariableError();
        und_.setId(variableName);
        und_.setFileName(_conf.getCurrentFileName());
        und_.setRc(_conf.getCurrentLocation());
        _conf.getClasses().addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(this);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();
        if (ElUtil.isDeclaringVariable(this, _conf)) {
            if (variableName.isEmpty()) {
                analyzeNotBoolAssignmentAfter(_conf);
                return;
            }
            boolean isBool_;
            isBool_ = getResultClass().isBoolType(_conf);
            for (StringMap<AssignmentBefore> s: assB_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    sm_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
                ass_.add(sm_);
            }
            AssignmentBefore asBe_ = new AssignmentBefore();
            asBe_.setUnassignedBefore(true);
            ass_.last().put(variableName, asBe_.assignAfter(isBool_));
            for (StringMap<AssignmentBefore> s: assM_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    sm_.put(e.getKey(), bf_.assignAfter(isBool_));
                }
                assAfM_.add(sm_);
            }
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                assA_.put(e.getKey(), bf_.assignAfter(isBool_));
            }
            vars_.getVariables().put(this, ass_);
            vars_.getMutableLoop().put(this, assAfM_);
            vars_.getFields().put(this, assA_);
            return;
        }

        boolean isBool_;
        isBool_ = getResultClass().isBoolType(_conf);
        String varName_ = variableName;
        if (getParent() instanceof AffectationOperation && getParent().getFirstChild() == this) {
            varName_ = EMPTY_STRING;
        }
        
        for (StringMap<AssignmentBefore> s: assB_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                if (StringList.quickEq(e.getKey(), varName_)) {
                    if (!e.getValue().isAssignedBefore()) {
                        //errors
                        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
                        UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setRc(_conf.getCurrentLocation());
                        _conf.getClasses().addError(un_);
                    }
                }
                AssignmentBefore bf_ = e.getValue();
                sm_.put(e.getKey(), bf_.assignAfter(isBool_));
            }
            ass_.add(sm_);
        }
        for (StringMap<AssignmentBefore> s: assM_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                sm_.put(e.getKey(), bf_.assignAfter(isBool_));
            }
            assAfM_.add(sm_);
        }
        for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
            AssignmentBefore bf_ = e.getValue();
            assA_.put(e.getKey(), bf_.assignAfter(isBool_));
        }
        vars_.getMutableLoop().put(this, assAfM_);
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        if (resultCanBeSet()) {
            setQuickSimpleArgument(arg_, _conf);
        } else {
            setSimpleArgument(arg_, _conf);
        }
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.hasExceptionOrFailInit()) {
            return arg_;
        }
        if (resultCanBeSet()) {
            setQuickSimpleArgument(arg_, _conf, _nodes);
        } else {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }
    Argument getCommonArgument(ExecutableCode _conf) {
        Argument a_ = new Argument();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }

    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, boolean _convert) {
        Argument arg_ = getCommonSetting(_conf, _right, _convert);
        if (!_conf.hasExceptionOrFailInit()) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right, boolean _convert) {
        Argument arg_ = getCommonSetting(_conf, _right, _convert);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right);
        if (!_conf.hasExceptionOrFailInit()) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        if (!_conf.hasExceptionOrFailInit()) {
            setSimpleArgument(arg_, _conf, _nodes);
        }
        return arg_;
    }

    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    Argument getCommonSetting(ExecutableCode _conf, Argument _right, boolean _convert) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(locVar_.getStruct());
        if (!Templates.checkObject(formattedClassVar_, _right, _convert, _conf)) {
            return Argument.createVoid();
        }
        locVar_.setStruct(_right.getStruct());
        return _right;
    }
    Argument getCommonCompoundSetting(ExecutableCode _conf, Struct _store, String _op, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        if (res_.getStruct() instanceof NumberStruct) {
            res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
        }
        locVar_.setStruct(res_.getStruct());
        return res_;
    }
    Argument getCommonSemiSetting(ExecutableCode _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = NumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        if (res_.getStruct() instanceof NumberStruct) {
            res_.setStruct(PrimitiveTypeUtil.convertObject(cl_, res_.getStruct(), _conf));
        }
        locVar_.setStruct(res_.getStruct());
        if (_post) {
            return left_;
        }
        return res_;
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<OperationNode, ArgumentsPair> _nodes, Argument _right) {
        return endCalculate(_conf, _nodes, false, null, _right);
    }

    @Override
    public Argument endCalculate(ExecutableCode _conf, Argument _right) {
        return endCalculate(_conf, false, null, _right);
    }
    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<OperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        locVar_.setStruct(_right.getStruct());
        Argument out_ = _right;
        if (_post) {
            out_ = _stored;
        }
        setSimpleArgument(out_, _conf, _nodes);
        return out_;
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, boolean _post,
            Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        int relativeOff_ = getOperations().getOffset();
        String originalStr_ = getOperations().getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        locVar_.setStruct(_right.getStruct());
        Argument out_ = _right;
        if (_post) {
            out_ = _stored;
        }
        setSimpleArgument(out_, _conf);
        return out_;
    }
}
