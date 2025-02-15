package aiki.beans;

public final class BeanDisplayTranslatedKey implements BeanDisplayElt<TranslatedKey>, BeanDisplay<TranslatedKey> {

    private final String file;
    private final String key;
    public BeanDisplayTranslatedKey() {
        this("","");
    }

    public BeanDisplayTranslatedKey(String _f, String _k) {
        file = _f;
        key = _k;
    }
    @Override
    public int display(CommonBean _rend, TranslatedKey _info, int _index) {
        if (_info.getTranslation().isEmpty()) {
            _rend.formatMessageCts(file,key);
            return 1;
        }
        _rend.formatMessageDirCts(_info);
        return 1;
    }

    @Override
    public int displayElt(CommonBean _rend, TranslatedKey _info) {
        _rend.formatMessageDir(_info);
        return 1;
    }
}
