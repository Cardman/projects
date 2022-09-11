package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.util.AbsComparerTreeMap;
import code.util.ints.Comparing;

final class TreeTypeMethodOverrides extends AbsComparerTreeMap<RootBlock, GeneStringOverridable> {
    TreeTypeMethodOverrides(Comparing<RootBlock> _cmp) {
        super(_cmp);
    }
}
