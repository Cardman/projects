package aiki.beans;

import aiki.facade.FacadeGame;
import code.bean.Bean;

public abstract class CommonSingleBean extends Bean implements WithFacade {

    private FacadeGame dataBase;

    public FacadeGame getDataBase() {
        return db();
    }

    @Override
    public FacadeGame db() {
        return dataBase;
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        dataBase = _dataBase;
    }
}
