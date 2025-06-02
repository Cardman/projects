package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustTypeDuoElt implements DisplayEntryCust<Integer, TypesDuo> {
    private final AbsMap<String, String> types;

    public DisplayEntryCustTypeDuoElt(AbsMap<String, String> _t) {
        this.types = _t;
    }


    @Override
    public String display(Integer _k, TypesDuo _v) {
        return StringUtil.nullToEmpty(types.getVal(_v.getDamageType()))+ConverterCommonMapUtil.K_V+ StringUtil.nullToEmpty(types.getVal(_v.getPokemonType()));
    }
}
