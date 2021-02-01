package aiki.beans;

import aiki.facade.FacadeGame;

public interface WithFacade {
    FacadeGame db();
    void setDataBase(FacadeGame _dataBase);
}
