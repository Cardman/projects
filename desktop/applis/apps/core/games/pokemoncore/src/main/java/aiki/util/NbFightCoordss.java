package aiki.util;

import aiki.game.NbFightCoords;
import code.util.*;
import code.util.core.BoolVal;

public final class NbFightCoordss extends AbsBasicMap<NbFightCoords, BoolVal> {
    public NbFightCoordss(){
    }
    public NbFightCoordss(CollCapacity _cap){
        super(_cap);
    }
    @Override
    protected BoolVal def() {
        return BoolVal.FALSE;
    }

    @Override
    protected boolean matchKeys(NbFightCoords _one, NbFightCoords _two) {
        return _one.eq(_two);
    }
}
