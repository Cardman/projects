package aiki.beans;

import aiki.beans.abilities.*;

public final class BeanDisplayTranslatedKeyPair implements BeanDisplay<TranslatedKeyPair> {

    @Override
    public int display(CommonBean _rend, TranslatedKeyPair _info, int _index) {
        _rend.formatMessageDirCts(_info.getFirst());
        _rend.formatMessageDirCts(_info.getSecond());
        return 2;
    }

}
