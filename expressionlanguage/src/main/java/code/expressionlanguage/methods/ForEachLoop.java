package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.util.StringList;

public final class ForEachLoop extends AbstractForEachLoop {

    public ForEachLoop(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _className, _variable, _expression, _classIndex, _label, _offset);
    }

    @Override
    public StringList getInferredIterable(StringList _types, ContextEl _cont) {
        IterableAnalysisResult it_ = _cont.getStandards().getCustomType(_types, _cont);
        return it_.getClassName();
    }

    @Override
    public ExpressionLanguage getEqIterator(Analyzable _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsIteratorCust());
    }

    @Override
    public ExpressionLanguage getEqHasNext(Analyzable _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsHasNextCust());
    }

    @Override
    public ExpressionLanguage getEqNext(Analyzable _an) {
        return new ExpressionLanguage(_an.getClasses().getExpsNextCust());
    }

    @Override
    public String getIteratorVar(Analyzable _an) {
        return _an.getClasses().getIteratorVarCust();
    }

    @Override
    public String getHasNextVar(Analyzable _an) {
        return _an.getClasses().getHasNextVarCust();
    }

    @Override
    public String getNextVar(Analyzable _an) {
        return _an.getClasses().getNextVarCust();
    }
}
