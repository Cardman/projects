package code.formathtml.classes;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.AbstractForEachLoop;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.stds.NativeIterableAnalysisResult;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class NativeForEach extends AbstractForEachLoop {

    private Boolean nativeCmp;

    public NativeForEach(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _className, _variable, _expression, _classIndex, _label, _offset);
    }

    @Override
    public StringList getInferredIterable(StringList _types, ContextEl _cont) {
        NativeIterableAnalysisResult it_ = (NativeIterableAnalysisResult) _cont.getStandards().getCustomType(_types, _cont);
        StringList out_ = it_.getClassName();
        nativeCmp = it_.getNativeIterator();
        return out_;
    }

    @Override
    public ExpressionLanguage getEqIterator(Analyzable _an) {
        if (nativeCmp) {
            return new ExpressionLanguage(((BeanLgNames) _an.getStandards()).getExpsIterator());
        }
        return new ExpressionLanguage(_an.getClasses().getExpsIteratorCust());
    }

    @Override
    public ExpressionLanguage getEqHasNext(Analyzable _an) {
        if (nativeCmp) {
            return new ExpressionLanguage(((BeanLgNames) _an.getStandards()).getExpsHasNext());
        }
        return new ExpressionLanguage(_an.getClasses().getExpsHasNextCust());
    }

    @Override
    public ExpressionLanguage getEqNext(Analyzable _an) {
        if (nativeCmp) {
            return new ExpressionLanguage(((BeanLgNames) _an.getStandards()).getExpsNext());
        }
        return new ExpressionLanguage(_an.getClasses().getExpsNextCust());
    }

    @Override
    public String getIteratorVar(Analyzable _an) {
        if (nativeCmp) {
            return ((BeanLgNames) _an.getStandards()).getIteratorVar();
        }
        return _an.getClasses().getIteratorVarCust();
    }

    @Override
    public String getHasNextVar(Analyzable _an) {
        if (nativeCmp) {
            return ((BeanLgNames) _an.getStandards()).getHasNextVar();
        }
        return _an.getClasses().getHasNextVarCust();
    }

    @Override
    public String getNextVar(Analyzable _an) {
        if (nativeCmp) {
            return ((BeanLgNames) _an.getStandards()).getNextVar();
        }
        return _an.getClasses().getNextVarCust();
    }
}
