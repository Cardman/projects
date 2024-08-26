package aiki.sml;

import aiki.db.*;
import aiki.facade.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class DefDataBaseStreamTest extends EquallablePkFileUtil {
    @Test
    public void v1() {
        MockProgramInfos pr_ = pr(0, 1);
        GamesPkMiniDb db_ = new GamesPkMiniDb();
        MockFutureCallableParam<DataBase> task_ = new MockFutureCallableParam<DataBase>(db_, new IntMap<AbstractFuture>(), 0);
        DataBase res_ = task_.attendreResultat();
        FacadeGame f_ = new FacadeGame();
        f_.setData(res_);
        f_.setLanguages(new StringList(StringUtil.EN));
        f_.setSimplyLanguage(StringUtil.EN);
        f_.setSexList(new MockLSexList());
        DefDataBaseStream ins_ = new DefDataBaseStream();
        LoadingGame loadingGame_ = new LoadingGame();
        loadingGame_.setExport("_");
        assertFalse(ins_.exportRom(pr_,f_, loadingGame_).isEmpty());
        AbstractAtomicBooleanCore result_ = ins_.loadRomAndCheck(pr_,task_,f_,"_",new ConcreteInteger(), new ConcreteBoolean(true));
        assertTrue(result_.get());
    }
    @Test
    public void v2() {
        MockProgramInfos pr_ = pr(0, 1);
        GamesPkMiniDb db_ = new GamesPkMiniDb();
        FacadeGame f_ = new FacadeGame();
        f_.setLanguages(new StringList(StringUtil.EN));
        f_.setSimplyLanguage(StringUtil.EN);
        f_.setSexList(new MockLSexList());
        AbstractAtomicBooleanCore result_ = new DefDataBaseStream().loadRomAndCheck(pr_,new MockFutureCallableParam<DataBase>(db_, new IntMap<AbstractFuture>(), 0),f_,"_",new ConcreteInteger(), new ConcreteBoolean(true));
        assertTrue(result_.get());
    }
    @Test
    public void v3() {
        MockProgramInfos pr_ = pr(0, 1);
        DefDataBaseStream ins_ = new DefDataBaseStream();
        FacadeGame f_ = new FacadeGame();
        assertTrue(ins_.exportRom(pr_,f_, new LoadingGame()).isEmpty());
    }
}
