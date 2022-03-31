package aiki.beans;

import aiki.facade.FacadeGame;
import code.bean.Bean;
import code.bean.nat.BeanStruct;

public final class PokemonBeanStruct extends BeanStruct {

    private final WithFacade withFacade;

    public PokemonBeanStruct(Bean _bean) {
        super(_bean);
        withFacade = (WithFacade) _bean;
    }

    public void setDataBase(FacadeGame _dataBase) {
        withFacade.setDataBase(_dataBase);
    }
    public Bean getInstance() {
        return getBean();
    }


}
