package aiki.gui.components.editor;

import code.gui.*;

public final class DisplayKeyOnlyInt implements DisplayEntryCust<Integer, EditedCrudPair<Integer,String>> {

    @Override
    public String display(Integer _k, EditedCrudPair<Integer, String> _v) {
        return _v.getKey()+ConverterCommonMapUtil.K_V+_v.getValue();
    }
}
