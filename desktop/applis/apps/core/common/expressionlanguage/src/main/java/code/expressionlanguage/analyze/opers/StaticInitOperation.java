package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.inherits.ClassArgumentMatching;
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
    public void analyze(ContextEl _conf) {
        setStaticResultClass(new ClassArgumentMatching(EMPTY_STRING));
    }

    void setInit(ContextEl _conf, String _base, boolean _staticType) {
        if (!_staticType) {
            possibleInitClass = false;
            String argClName_ = _conf.getStandards().getAliasObject();
            setStaticResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        Classes classes_ = _conf.getClasses();
        if (classes_.getClassBody(_base) != null) {
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
