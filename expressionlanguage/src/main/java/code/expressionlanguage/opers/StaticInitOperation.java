package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StaticInitOperation extends LeafOperation {

    private boolean possibleInitClass;
    private String methodName;

    public StaticInitOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }
    @Override
    public final boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = getParent();
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public final boolean isCalculated() {
        OperationNode op_ = getParent();
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public void analyze(Analyzable _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String className_ = methodName.trim().substring(INSTANCE.length()+1);
        className_ = StringList.removeAllSpaces(className_);
        String argClName_;
        String type_ = Templates.getIdFromAllTypes(className_);
        String base_ = Templates.getIdFromAllTypes(type_);
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        if (!checkExistBase(_conf, false, base_, false, 0)) {
            Argument a_ = new Argument();
            argClName_ = _conf.getStandards().getAliasObject();
            setArguments(a_);
            setStaticResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (classes_.isCustomType(base_)) {
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = Templates.getIdFromAllTypes(glClass_);
            }
            if (!Classes.canAccessClass(curClassBase_, base_, _conf)) {
                BadAccessClass badAccess_ = new BadAccessClass();
                badAccess_.setId(base_);
                badAccess_.setRc(_conf.getCurrentLocation());
                badAccess_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().getErrorsDet().add(badAccess_);
            }
            possibleInitClass = true;
        }
        Argument a_ = new Argument();
        argClName_ = base_;
        setArguments(a_);
        setStaticResultClass(new ClassArgumentMatching(argClName_));
        return;
    }
    @Override
    public final void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        ObjectMap<ClassField,Assignment> assA_ = new ObjectMap<ClassField,Assignment>();
        //simple assignment
        for (StringMap<AssignmentBefore> s: assB_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                sm_.put(e.getKey(), bf_.assignAfter(false));
            }
            ass_.add(sm_);
        }
        for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
            AssignmentBefore bf_ = e.getValue();
            assA_.put(e.getKey(), bf_.assignAfter(false));
        }
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        Argument current_ = getArgument();
        ArgumentCall argres_ = getCommonArgument(current_, _conf);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf.getContextEl());
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getCommonArgument(current_, _conf);
        }
        if (_conf.getException() != null) {
            return;
        }
        Argument arg_ = argres_.getArgument();
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument current_ = _nodes.getVal(this).getArgument();
        ArgumentCall argres_ = getCommonArgument(current_, _conf);
        Argument arg_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        }
        return arg_;
    }
    ArgumentCall getCommonArgument(Argument _argument, ExecutableCode _conf) {
        if (possibleInitClass) {
            String className_ = getResultClass().getName();
            InitClassState res_ = _conf.getClasses().getLocks().getState(_conf.getContextEl(), className_);
            if (res_ == InitClassState.NOT_YET) {
                InitializatingClass inv_ = new InitializatingClass(className_);
                return ArgumentCall.newCall(inv_);
            }
            if (res_ == InitClassState.ERROR) {
                CausingErrorStruct causing_ = new CausingErrorStruct(className_);
                _conf.setException(causing_);
                return ArgumentCall.newArgument(Argument.createVoid());
            }
        }
        Argument cur_ = _argument;
        return ArgumentCall.newArgument(cur_);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
