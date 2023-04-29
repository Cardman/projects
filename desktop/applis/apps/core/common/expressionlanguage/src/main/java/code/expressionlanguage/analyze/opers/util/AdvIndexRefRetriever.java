package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.util.CustList;

public final class AdvIndexRefRetriever implements AbsIndexRefRetriever {

    private final int delta;

    public AdvIndexRefRetriever(int _d) {
        this.delta = _d;
    }
    @Override
    public NamedArgIndex retrieve(CustList<NamedArgIndex> _nameParametersFilterIndexes, int _i) {
        return _nameParametersFilterIndexes.get(_i);
    }
    @Override
    public void update(NamedArgIndex _pair, NamedFunctionBlock _custMethod) {
        NamedArgumentOperation namedArgument_ = _pair.getArg();
        int index_ = namedArgument_.getIndexChild() - delta;
        namedArgument_.setRefSet(_custMethod.getParametersNamesOffset().get(index_));
        namedArgument_.getCustomMethod().add(_custMethod);
    }
}
