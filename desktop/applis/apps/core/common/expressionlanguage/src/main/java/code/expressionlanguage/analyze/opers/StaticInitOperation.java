package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
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
    public void analyze(AnalyzedPageEl _page) {
        setResultClass(new AnaClassArgumentMatching(EMPTY_STRING));
    }

    void setInit(String _base, boolean _staticType, AnalyzedPageEl _page) {
        if (!_staticType) {
            possibleInitClass = false;
            String argClName_ = _page.getStandards().getAliasObject();
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        if (_page.getAnaClassBody(_base) != null) {
            possibleInitClass = true;
        } else {
            setSimpleArgument(new Argument());
        }
        setResultClass(new AnaClassArgumentMatching(_base));
    }

    public boolean isPossibleInitClass() {
        return possibleInitClass;
    }
}
