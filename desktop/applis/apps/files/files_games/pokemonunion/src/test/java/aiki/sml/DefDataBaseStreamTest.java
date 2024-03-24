package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import code.mock.MockLSexList;
import code.mock.MockProgramInfos;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.ConcreteBoolean;
import code.threads.ConcreteInteger;
import code.util.StringList;
import org.junit.Test;

public final class DefDataBaseStreamTest extends EquallablePkFileUtil {
    @Test
    public void v1() {
        MockProgramInfos pr_ = pr(0, 1);
        GamesPkMiniDb db_ = new GamesPkMiniDb();
        DataBase res_ = db_.attendreResultat();
        FacadeGame f_ = new FacadeGame();
        f_.setLanguages(new StringList("en"));
        f_.setSimplyLanguage("en");
        f_.setSexList(new MockLSexList());
        AbstractAtomicBooleanCore result_ = new DefDataBaseStream().loadRomAndCheck(pr_,db_,f_,"_",new ConcreteInteger(), new ConcreteBoolean(true),DocumentWriterAikiCoreUtil.getTextFiles(res_));
        assertTrue(result_.get());
    }
    @Test
    public void v2() {
        MockProgramInfos pr_ = pr(0, 1);
        GamesPkMiniDb db_ = new GamesPkMiniDb();
        FacadeGame f_ = new FacadeGame();
        f_.setLanguages(new StringList("en"));
        f_.setSimplyLanguage("en");
        f_.setSexList(new MockLSexList());
        AbstractAtomicBooleanCore result_ = new DefDataBaseStream().loadRomAndCheck(pr_,db_,f_,"_",new ConcreteInteger(), new ConcreteBoolean(true));
        assertTrue(result_.get());
    }
}
