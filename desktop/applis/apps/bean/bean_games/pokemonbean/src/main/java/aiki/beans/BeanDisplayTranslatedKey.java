package aiki.beans;

public final class BeanDisplayTranslatedKey implements BeanDisplayElt<TranslatedKey> {

    @Override
    public int displayElt(CommonBean _rend, TranslatedKey _info) {
        _rend.formatMessageDir(_info);
        return 1;
    }
}
