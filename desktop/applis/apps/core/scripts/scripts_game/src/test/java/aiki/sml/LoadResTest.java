package aiki.sml;

import aiki.db.*;
import aiki.sml.trs.*;
import code.sml.util.*;
import code.util.*;
import org.junit.Test;

import aiki.facade.*;
import code.maths.montecarlo.*;

public final class LoadResTest extends EquallableAikiScriptsUtil {
    @Test
    public void load(){
        FacadeGame facade_ = new FacadeGame();
        facade_.setLanguages(indexesAll());
        facade_.setDisplayLanguages(new StringMap<String>());
        StringMap<TranslationsAppli> apps_ = new StringMap<TranslationsAppli>();
        apps_.addEntry(MessagesTrs.EN,MessagesTrs.enPart());
        apps_.addEntry(MessagesTrs.FR,MessagesTrs.frPart());
        DataBase data_ = new DefLoadingData(DefaultGenerator.oneElt(), facade_.getLanguages(), facade_.getDisplayLanguages(), facade_.getSexList(), "", apps_).call();
        FacadeGame.postLoad(facade_, data_);
        assertNotNull(facade_.getData());
        assertNotNull(new CstIgameImpl().self());
        FacadeGame.postLoad(facade_,facade_.getData());
    }

    public static StringList indexesAll(){
        return new StringList(MessagesTrs.EN,MessagesTrs.FR);
    }
}