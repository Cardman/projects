package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.util.*;
import code.util.core.*;

public final class DisplayEntryCustTypeDuo implements DisplayEntryCust<Integer, EditedCrudPair<TypesDuo, Rate>> {
    private final AbsMap<String, String> types;

    public DisplayEntryCustTypeDuo(AbsMap<String, String> _t) {
        this.types = _t;
    }


    @Override
    public String display(Integer _k, EditedCrudPair<TypesDuo, Rate> _v) {
        return StringUtil.nullToEmpty(types.getVal(_v.getKey().getDamageType()))+ConverterCommonMapUtil.K_V+ StringUtil.nullToEmpty(types.getVal(_v.getKey().getPokemonType()));
    }
}
