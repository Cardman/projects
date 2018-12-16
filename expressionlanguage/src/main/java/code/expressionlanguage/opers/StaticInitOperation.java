package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.IdMap;
import code.util.StringList;

public final class StaticInitOperation extends VariableLeafOperation {

    private boolean possibleInitClass;
    private String methodName;

    public StaticInitOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setStaticResultClass(new ClassArgumentMatching(EMPTY_STRING));
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
        if (arg_ == null) {
            setQuickSimpleArgument(Argument.createVoid(), _conf);
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument current_ = _nodes.getVal(this).getArgument();
        Argument arg_ = getCommonArgument(current_, _conf);
        if (arg_ == null) {
            _nodes.getVal(this).setArgument(Argument.createVoid());
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
        return null;
    }

    void setInit(Analyzable _conf, String _base, boolean _staticType) {
        if (!_staticType) {
            possibleInitClass = false;
            String argClName_ = _conf.getStandards().getAliasObject();
            setStaticResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        Classes classes_ = _conf.getClasses();
        if (classes_.isCustomType(_base)) {
            possibleInitClass = true;
        } else {
            setSimpleArgument(new Argument());
        }
        setStaticResultClass(new ClassArgumentMatching(_base));
    }

}
