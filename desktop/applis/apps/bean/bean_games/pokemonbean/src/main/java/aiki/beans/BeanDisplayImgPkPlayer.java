package aiki.beans;

import aiki.beans.game.*;

public final class BeanDisplayImgPkPlayer implements BeanDisplayElt<ImgPkPlayer> {

    @Override
    public int displayElt(CommonBean _rend, ImgPkPlayer _info) {
        _rend.addImg(_info.getImage());
        _rend.formatMessageDir(_info.getKey().getTranslation());
        return 1;
    }
}
