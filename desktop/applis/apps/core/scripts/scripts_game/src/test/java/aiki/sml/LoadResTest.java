package aiki.sml;

import org.junit.Test;

import aiki.facade.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.consts.Constants;

public final class LoadResTest extends EquallableAikiScriptsUtil {
    @Test
    public void load(){
        FacadeGame facade_ = new FacadeGame();
        facade_.setLanguages(Constants.getAvailableLanguages());
        LoadRes.loadResources(new DefaultGenerator(),facade_,new PerCentQuick(),new LoadFlagQuick());
        assertNotNull(facade_.getData());
        assertNotNull(new CstIgameImpl().self());
    }
}