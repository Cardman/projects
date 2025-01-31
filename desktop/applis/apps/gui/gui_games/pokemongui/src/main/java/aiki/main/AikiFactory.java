package aiki.main;

import aiki.db.DataBase;
import aiki.facade.IntGamePkStream;
import aiki.game.fight.BallNumberRate;
import aiki.game.fight.FighterPosition;
import aiki.map.pokemon.UsablePokemon;
import aiki.sml.*;
import code.gui.*;
import code.gui.events.AbsActionListenerAct;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class AikiFactory {
    private final AbstractBaseExecutorServiceParam<AikiNatLgNamesNavigation> navigation;
    private final EnabledMenu generalHelp;
    private AbstractFutureParam<AikiNatLgNamesNavigation> taskNavData;
    private final AbstractBaseExecutorServiceParam<DataBase> geneDb;
    private AbstractFutureParam<DataBase> taskLoad;
    private IntDataBaseStream dataBaseStream;
    private IntGamePkStream gamePkStream;
    private IntConfPkStream confPkStream;
    public AikiFactory(AbstractProgramInfos _p, AbstractBaseExecutorServiceParam<AikiNatLgNamesNavigation> _n, AbstractBaseExecutorServiceParam<DataBase> _g) {
        generalHelp = _p.getCompoFactory().newMenuItem();
        navigation = _n;
        _n.shutdown();
        geneDb = _g;
        setDataBaseStream(new DefDataBaseStream());
        setGamePkStream(new DefGamePkStream(_p));
        setConfPkStream(new DefConfPkStream(_p));
    }

    public AbstractFutureParam<DataBase> submit(IntCallable<DataBase> _i) {
        AbstractFutureParam<DataBase> res_ = geneDb.submitWrCallable(_i);
        taskLoad = res_;
        geneDb.shutdown();
        return res_;
    }

    public AbstractFutureParam<DataBase> getTaskLoad() {
        return taskLoad;
    }
    public EnabledMenu getGeneralHelp() {
        return generalHelp;
    }

    public void submitNavData(IntCallable<AikiNatLgNamesNavigation> _n) {
        AbstractBaseExecutorServiceParam<AikiNatLgNamesNavigation> n_ = navigation.copy();
        taskNavData = n_.submitWrCallable(_n);
        n_.shutdown();
    }

    public AbstractFutureParam<AikiNatLgNamesNavigation> getTaskNavData() {
        return taskNavData;
    }

    public IntDataBaseStream getDataBaseStream() {
        return dataBaseStream;
    }

    public void setDataBaseStream(IntDataBaseStream _d) {
        this.dataBaseStream = _d;
    }

    public IntGamePkStream getGamePkStream() {
        return gamePkStream;
    }

    public void setGamePkStream(IntGamePkStream _d) {
        this.gamePkStream = _d;
    }

    public IntConfPkStream getConfPkStream() {
        return confPkStream;
    }

    public void setConfPkStream(IntConfPkStream _d) {
        this.confPkStream = _d;
    }
    public static ScrollCustomGraphicList<BallNumberRate> ballPanel(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<BallNumberRate> _rend, AbsActionListenerAct _act) {
        return new DefScrollCustomGraphicList<BallNumberRate>(_compo,_img,_rend,true, _act);
    }

    public static ScrollCustomGraphicList<FighterPosition> fighter(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<FighterPosition> _rend, AbsActionListenerAct _act) {
        return new DefScrollCustomGraphicList<FighterPosition>(_compo,_img,_rend,true, _act);
    }

    public static ScrollCustomGraphicList<UsablePokemon> usable(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<UsablePokemon> _rend, AbsActionListenerAct _act) {
        return new DefScrollCustomGraphicList<UsablePokemon>(_compo,_img,_rend,true, _act);
    }

    public static ScrollCustomGraphicList<String> str(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<String> _rend, AbsActionListenerAct _act) {
        return new DefScrollCustomGraphicList<String>(_compo,_img,_rend,true, _act);
    }

}
