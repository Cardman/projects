package aiki.beans;

import aiki.beans.fight.*;
import aiki.beans.game.*;
import code.scripts.pages.aiki.*;

public final class BeanDisplayImgMovesListTeamPositionsList implements BeanDisplayEltSec<ImgMovesListTeamPositionsList> {
    @Override
    public int displayEltSecHeader(CommonBean _rend, ImgMovesListTeamPositionsList _info) {
        _rend.display(_info.getKeyPks(), MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_KEY);
        return 0;
    }

    @Override
    public int displayEltSec(CommonBean _rend, ImgMovesListTeamPositionsList _info) {
//        _rend.display(_info.getKeyPks(), MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_KEY);
        _rend.initPage();
        new BeanDisplayList<ImgPkPlayer>(new BeanDisplayImgPkPlayer()).display(_rend,_info.getKeyPks());
        _rend.displayStringList(_info.getNamesPk(), MessagesPkBean.FIGHT, MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_VALUE);
        _rend.feedParents();
        return 1;
    }
}
