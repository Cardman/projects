package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.options.KeyWords;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;

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
        InvokingOperation ins_ = (InvokingOperation) getParent();
        KeyWords keyWords_ = _conf.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String className_ = methodName.trim().substring(newKeyWord_.length());
        if (ins_.isIntermediateDottedOperation() || className_.trim().startsWith("..")) {
            possibleInitClass = false;
            Argument a_ = new Argument();
            String argClName_ = _conf.getStandards().getAliasObject();
            setArguments(a_);
            setStaticResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        className_ = _conf.resolveCorrectType(className_);
        String argClName_;
        String type_ = Templates.getIdFromAllTypes(className_);
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        if (classes_.isCustomType(type_)) {
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = Templates.getIdFromAllTypes(glClass_);
            }
            if (!Classes.canAccessClass(curClassBase_, type_, _conf)) {
                BadAccessClass badAccess_ = new BadAccessClass();
                badAccess_.setId(type_);
                badAccess_.setRc(_conf.getCurrentLocation());
                badAccess_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(badAccess_);
            }
            possibleInitClass = true;
        }
        Argument a_ = new Argument();
        argClName_ = type_;
        setArguments(a_);
        setStaticResultClass(new ClassArgumentMatching(argClName_));
    }
    @Override
    public final void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        Argument current_ = getArgument();
        Argument argres_ = getCommonArgument(current_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getCommonArgument(current_, _conf);
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        Argument arg_ = argres_;
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument current_ = _nodes.getVal(this).getArgument();
        Argument arg_ = getCommonArgument(current_, _conf);
        if (_conf.callsOrException()) {
            return arg_;
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    Argument getCommonArgument(Argument _argument, ExecutableCode _conf) {
        if (possibleInitClass) {
            String className_ = getResultClass().getNames().first();
            if (InvokingOperation.hasToExit(_conf, className_)) {
                return Argument.createVoid();
            }
        }
        Argument cur_ = _argument;
        return cur_;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
