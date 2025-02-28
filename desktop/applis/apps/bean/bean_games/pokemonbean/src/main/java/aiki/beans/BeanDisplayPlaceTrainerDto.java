package aiki.beans;

import aiki.beans.facade.solution.dto.*;

public final class BeanDisplayPlaceTrainerDto implements BeanDisplayElt<PlaceTrainerDto> {
    @Override
    public int displayElt(CommonBean _rend, PlaceTrainerDto _info) {
        _rend.formatMessageDir(_info.getTrainer()+" - "+_info.getPlace());
        return 1;
    }

}
