package aiki.map.util;

import code.util.AbsComparerTreeMap;
import code.util.ints.Comparing;

public final class MiniMapCoordsTileInts extends AbsComparerTreeMap<MiniMapCoords, int[][]> {

    public MiniMapCoordsTileInts(Comparing<MiniMapCoords> _cmp) {
        super(_cmp);
    }

}
