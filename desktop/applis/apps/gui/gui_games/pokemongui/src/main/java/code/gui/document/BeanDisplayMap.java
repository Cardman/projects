package code.gui.document;

import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class BeanDisplayMap<K,V> {
    private final BeanDisplay<K> displayKey;
    private final BeanDisplay<V> displayValue;

    public BeanDisplayMap(BeanDisplay<K> _k, BeanDisplay<V> _v) {
        this.displayKey = _k;
        this.displayValue = _v;
    }
    public void display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, AbsMap<K,V> _info, int _nbCols) {
        for (EntryCust<K,V> e: _info.entryList()) {
            int count_ = displayKey.display(_rend, _api, _form, e.getKey(), 0, _nbCols);
            displayValue.display(_rend,_api,_form,e.getValue(),count_,_nbCols);
        }
    }
}
