package aiki.beans;

public final class BeanDisplayTranslatedKey implements BeanDisplayElt<TranslatedKey>, BeanDisplay<TranslatedKey> {

    @Override
    public int display(CommonBean _rend, TranslatedKey _info, int _index) {
        _rend.formatMessageDirCts(_info);
        return 1;
    }

    @Override
    public int displayElt(CommonBean _rend, TranslatedKey _info) {
        _rend.formatMessageDir(_info);
        return 1;
    }
}
