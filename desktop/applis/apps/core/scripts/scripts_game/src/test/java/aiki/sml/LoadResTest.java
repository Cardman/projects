package aiki.sml;

import code.threads.ConcreteBoolean;
import code.threads.ConcreteInteger;
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
        LoadRes.loadResources(DefaultGenerator.oneElt(),facade_,new ConcreteInteger(),new ConcreteBoolean(), new DefLoadingData(facade_.getLanguages(), facade_.getDisplayLanguages(),facade_.getSexList()));
        assertNotNull(facade_.getData());
        assertNotNull(new CstIgameImpl().self());
        LoadRes.postLoad(facade_,facade_.getData());
    }
}