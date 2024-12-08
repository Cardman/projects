package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class StringSubscribeBuilderUtil extends AbsSubscribeBuilderUtil<String,StringList> {

    public StringSubscribeBuilderUtil(SubscribedTranslationMessagesFactoryCoreMessages<String> _f) {
        super(_f, new IntListConvertString());
    }

    @Override
    public GeneComponentModelEltEnumSub<String> merge(AbstractProgramInfos _api, FacadeGame _sub, CustList<String> _excluded, AbsMap<String,String> _withEmptyStr) {
        return merge(_api, _sub, _excluded, _withEmptyStr, new EmptyDefValue());
    }

    @Override
    protected AbEqList<String> copy(CustList<String> _ls) {
        return new StringList(_ls);
    }


}
