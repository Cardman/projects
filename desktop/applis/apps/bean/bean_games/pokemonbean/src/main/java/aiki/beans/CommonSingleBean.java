package aiki.beans;

import aiki.facade.FacadeGame;

public abstract class CommonSingleBean extends CommonBean {

    public FacadeGame facade() {
        return db();
    }
}
