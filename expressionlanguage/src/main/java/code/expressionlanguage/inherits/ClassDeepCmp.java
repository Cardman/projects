package code.expressionlanguage.inherits;

import code.expressionlanguage.methods.RootBlock;
import code.util.Numbers;
import code.util.ints.Comparing;

public class ClassDeepCmp implements Comparing<RootBlock> {

    @Override
    public int compare(RootBlock _one, RootBlock _two) {
        int dOne_ = _one.getSelfAndParentTypes().size();
        int dTwo_ = _two.getSelfAndParentTypes().size();
        return Numbers.compareLg(dOne_, dTwo_);
    }

}
