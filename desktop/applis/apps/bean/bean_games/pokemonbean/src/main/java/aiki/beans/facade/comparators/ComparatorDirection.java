package aiki.beans.facade.comparators;
import aiki.map.enums.Direction;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorDirection implements Comparing<String> {

    @Override
    public int compare(String _o1, String _o2) {
        int o1_ = Direction.getDirectionByName(_o1).ordinal();
        int o2_ = Direction.getDirectionByName(_o2).ordinal();
        return Numbers.compareLg(o1_, o2_);
    }

}