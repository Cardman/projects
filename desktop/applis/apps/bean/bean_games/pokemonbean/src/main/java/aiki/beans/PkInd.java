package aiki.beans;

import aiki.beans.facade.game.dto.AikiBeansFacadeGameDtoStd;
import aiki.beans.game.AikiBeansGameStd;
import aiki.beans.game.PokemonPlayerBean;
import code.formathtml.Configuration;

public final class PkInd extends PokemonStandards{
    public void buildAddon() {
        AikiBeansFacadeGameDtoStd.build(this);
        AikiBeansGameStd.buildPokemonPlayerBean(this);
        PkFight.buildUsesOfMove(this);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0, beanPk(_language));
    }

    public PokemonBeanStruct beanPk(String _language) {
        return bean(new PokemonPlayerBean(), AikiBeansGameStd.TYPE_POKEMON_PLAYER_BEAN, _language);
    }
}
