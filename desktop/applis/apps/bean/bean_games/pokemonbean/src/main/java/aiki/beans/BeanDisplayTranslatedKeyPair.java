package aiki.beans;

import aiki.beans.abilities.*;

public final class BeanDisplayTranslatedKeyPair implements BeanDisplayElt<TranslatedKeyPair>,BeanDisplay<TranslatedKeyPair> {

    private final String file;
    private final String key;
    public BeanDisplayTranslatedKeyPair() {
        this("","");
    }

    public BeanDisplayTranslatedKeyPair(String _f, String _k) {
        file = _f;
        key = _k;
    }
    @Override
    public int displayElt(CommonBean _rend, TranslatedKeyPair _info) {
        diplay(_rend, _info);
        return 2;
    }

    @Override
    public int display(CommonBean _rend, TranslatedKeyPair _info, int _index) {
        diplay(_rend, _info);
        return 2;
    }

    private void diplay(CommonBean _rend, TranslatedKeyPair _info) {
        if (_info.getFirst().getTranslation().isEmpty()) {
            _rend.formatMessageCts(file,key);
        } else {
            _rend.formatMessageDirCts(_info.getFirst());
        }
        _rend.formatMessageDirCts(_info.getSecond());
    }

}
