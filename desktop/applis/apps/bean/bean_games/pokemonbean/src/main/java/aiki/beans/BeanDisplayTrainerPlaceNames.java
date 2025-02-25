package aiki.beans;

import aiki.fight.pokemon.*;

public final class BeanDisplayTrainerPlaceNames implements BeanDisplayElt<TrainerPlaceNames> {
    @Override
    public int displayElt(CommonBean _rend, TrainerPlaceNames _info) {
        _rend.formatMessageDir(_info.getTrainer()+" - "+_info.getPlace());
        return 1;
    }
}
