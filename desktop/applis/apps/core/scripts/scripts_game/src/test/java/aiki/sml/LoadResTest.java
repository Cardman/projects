package aiki.sml;

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
        LoadRes.loadResources(new DefaultGenerator(),facade_,new PerCentQuick(),new LoadFlagQuick(), new DefLoadingData(facade_.getLanguages(), facade_.getDisplayLanguages()));
        assertNotNull(facade_.getData());
        assertNotNull(new CstIgameImpl().self());
    }
}