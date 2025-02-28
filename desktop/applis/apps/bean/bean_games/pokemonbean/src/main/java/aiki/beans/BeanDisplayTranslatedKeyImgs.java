package aiki.beans;

public final class BeanDisplayTranslatedKeyImgs implements BeanDisplay<TranslatedKeyImgs> {

    @Override
    public int display(CommonBean _rend, TranslatedKeyImgs _info, int _index) {
        _rend.initLine();
        _rend.addImg(_info.getImg());
        _rend.feedParentsCts();
        _rend.initLine();
        _rend.addImg(_info.getColor());
        _rend.feedParentsCts();
        return 2;
    }
}
