package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.util.IdList;
import code.util.ints.Comparing;

public final class ComparingByTypeList implements Comparing<RootBlock> {

    private final IdList<RootBlock> types;
    public ComparingByTypeList(IdList<RootBlock> _types) {
        types = _types;
    }

    @Override
    public int compare(RootBlock _o1, RootBlock _o2) {
        return types.indexOfObj(_o1) - types.indexOfObj(_o2);
    }

}
