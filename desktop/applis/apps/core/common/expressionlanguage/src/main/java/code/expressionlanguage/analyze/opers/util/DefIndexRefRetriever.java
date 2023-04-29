package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.util.CustList;

public final class DefIndexRefRetriever implements AbsIndexRefRetriever {
    @Override
    public NamedArgIndex retrieve(CustList<NamedArgIndex> _nameParametersFilterIndexes, int _i) {
        NamedArgIndex pair_ = _nameParametersFilterIndexes.get(_i);
        NamedArgumentOperation namedArgument_ = pair_.getArg();
        int index_ = pair_.getIndex();
        namedArgument_.setIndex(index_);
        return pair_;
    }

    @Override
    public void update(NamedArgIndex _pair, NamedFunctionBlock _custMethod) {
        NamedArgumentOperation namedArgument_ = _pair.getArg();
        int index_ = _pair.getIndex();
        namedArgument_.setRef(_custMethod.getParametersNamesOffset().get(index_));
        namedArgument_.getCustomMethod().add(_custMethod);
    }
}
