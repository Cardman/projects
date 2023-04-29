package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.util.CustList;

public interface AbsIndexRefRetriever {
    NamedArgIndex retrieve(CustList<NamedArgIndex> _nameParametersFilterIndexes, int _i);
    void update(NamedArgIndex _pair, NamedFunctionBlock _custMethod);
}
