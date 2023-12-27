package aiki.sml;

import aiki.db.DataBase;
import code.threads.*;
import org.junit.Test;

import aiki.facade.*;
import code.maths.montecarlo.*;
import code.util.consts.Constants;

public final class LoadResTest extends EquallableAikiScriptsUtil {
    @Test
    public void load(){
        FacadeGame facade_ = new FacadeGame();
        facade_.setLanguages(Constants.getAvailableLanguages());
        facade_.setDisplayLanguages(LoadRes.dis());
        DataBase data_ = new DefLoadingData(DefaultGenerator.oneElt(), facade_.getLanguages(), facade_.getDisplayLanguages(), facade_.getSexList()).call();
        LoadRes.postLoad(facade_, data_);
        assertNotNull(facade_.getData());
        assertNotNull(new CstIgameImpl().self());
        LoadRes.postLoad(facade_,facade_.getData());
    }
}