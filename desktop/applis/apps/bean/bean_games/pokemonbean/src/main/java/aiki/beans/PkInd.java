package aiki.beans;

import aiki.beans.facade.game.dto.AikiBeansFacadeGameDtoStd;
import aiki.beans.game.AikiBeansGameStd;

public final class PkInd extends PokemonStandards{
    public void buildAddon() {
        AikiBeansFacadeGameDtoStd.build(this);
        AikiBeansGameStd.buildPokemonPlayerBean(this);
        PkFight.buildUsesOfMove(this);
    }
}
