package aiki.beans.game;

import aiki.fight.pokemon.TrainerPlaceNames;
import code.bean.nat.NaNuSt;

public final class TrainerPlaceNamesStruct extends NaNuSt {
    private final TrainerPlaceNames inst;
    public TrainerPlaceNamesStruct(TrainerPlaceNames _instance) {
        inst=(_instance);
    }
    public TrainerPlaceNames getTrainerPlaceNames() {
        return inst;
    }
}
