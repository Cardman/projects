package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.stds.IterableAnalysisResult;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class RendForEachLoop extends RendAbstractForEachLoop {
    RendForEachLoop(Configuration _importingPage, OffsetStringInfo _className, OffsetStringInfo _variable, OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _className, _variable, _expression, _classIndex, _label, _offset);
    }

    @Override
    public StringList getInferredIterable(StringList _types, Configuration _cont) {
        IterableAnalysisResult it_ = _cont.getStandards().getCustomType(_types, _cont.getContext());
        return it_.getClassName();
    }

    @Override
    public String getIteratorVar(Configuration _an) {
        return _an.getAdvStandards().getIteratorVar();
    }

    @Override
    public String getHasNextVar(Configuration _an) {
        return _an.getAdvStandards().getHasNextVar();
    }

    @Override
    public String getNextVar(Configuration _an) {
        return _an.getAdvStandards().getNextVar();
    }

    @Override
    public CustList<RendDynOperationNode> getEqIterator(Configuration _an) {
        return _an.getAdvStandards().getExpsIterator();
    }

    @Override
    public CustList<RendDynOperationNode> getEqHasNext(Configuration _an) {
        return _an.getAdvStandards().getExpsHasNext();
    }

    @Override
    public CustList<RendDynOperationNode> getEqNext(Configuration _an) {
        return _an.getAdvStandards().getExpsNext();
    }
}
