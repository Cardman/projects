package aiki.sml;

import aiki.db.*;
import code.util.*;
import org.junit.Test;

import aiki.facade.*;
import code.maths.montecarlo.*;

public final class LoadResTest extends EquallableAikiScriptsUtil {
    @Test
    public void load(){
        FacadeGame facade_ = new FacadeGame();
        facade_.setLanguages(new StringList("en","fr"));
        facade_.setDisplayLanguages(new StringMap<String>());
        DataBase data_ = new DefLoadingData(DefaultGenerator.oneElt(), facade_.getLanguages(), facade_.getDisplayLanguages(), facade_.getSexList()).call();
        LoadRes.postLoad(facade_, data_);
        assertNotNull(facade_.getData());
        assertNotNull(new CstIgameImpl().self());
        LoadRes.postLoad(facade_,facade_.getData());
    }
}