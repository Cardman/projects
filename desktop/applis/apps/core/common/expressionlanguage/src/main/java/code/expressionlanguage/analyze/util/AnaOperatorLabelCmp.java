package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class AnaOperatorLabelCmp implements Comparing<OperatorBlock> {
    @Override
    public int compare(OperatorBlock _one, OperatorBlock _two) {
        return StringUtil.compareStrings(_one.getLabelNumber(),_two.getLabelNumber());
    }
}
