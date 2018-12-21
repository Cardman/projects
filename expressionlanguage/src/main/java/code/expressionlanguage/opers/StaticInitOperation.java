package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.text.OperationsSequence;
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
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
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

    public boolean isPossibleInitClass() {
        return possibleInitClass;
    }
}
