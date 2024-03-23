package aiki.main;

import aiki.db.DataBase;
import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.map.pokemon.UsablePokemon;
import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class AikiFactory {
    private final AbstractBaseExecutorServiceParam<AikiNatLgNamesNavigation> navigation;
    private final EnabledMenu generalHelp;
    private AbstractFutureParam<AikiNatLgNamesNavigation> taskNav;
    private final AbstractBaseExecutorServiceParam<DataBase> geneDb;
    private AbstractFutureParam<DataBase> taskLoad;
    private AikiNatLgNamesNavigation preparedFightTask;
    private AikiNatLgNamesNavigation preparedPkTask;
    private AikiNatLgNamesNavigation preparedPkNetTask;
    private AikiNatLgNamesNavigation preparedDiffTask;
    private AikiNatLgNamesNavigation preparedProgTask;
    public AikiFactory(AbstractProgramInfos _p, AbstractBaseExecutorServiceParam<AikiNatLgNamesNavigation> _n, AbstractBaseExecutorServiceParam<DataBase> _g) {
        generalHelp = _p.getCompoFactory().newMenuItem();
        MenuItemUtils.setEnabledMenu(generalHelp,false);
        navigation = _n;
        _n.shutdown();
        geneDb = _g;
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

    public void submitNav(IntCallable<AikiNatLgNamesNavigation> _n) {
        AbstractBaseExecutorServiceParam<AikiNatLgNamesNavigation> n_ = navigation.copy();
        taskNav = n_.submitWrCallable(_n);
        n_.shutdown();
    }

    public AbstractFutureParam<AikiNatLgNamesNavigation> getTaskNav() {
        return taskNav;
    }

    public AikiNatLgNamesNavigation getPreparedFightTask() {
        return preparedFightTask;
    }

    public void setPreparedFightTask(AikiNatLgNamesNavigation _p) {
        this.preparedFightTask = _p;
    }

    public AikiNatLgNamesNavigation getPreparedPkTask() {
        return preparedPkTask;
    }

    public void setPreparedPkTask(AikiNatLgNamesNavigation _p) {
        this.preparedPkTask = _p;
    }

    public AikiNatLgNamesNavigation getPreparedPkNetTask() {
        return preparedPkNetTask;
    }

    public void setPreparedPkNetTask(AikiNatLgNamesNavigation _p) {
        this.preparedPkNetTask = _p;
    }

    public AikiNatLgNamesNavigation getPreparedDiffTask() {
        return preparedDiffTask;
    }

    public void setPreparedDiffTask(AikiNatLgNamesNavigation _p) {
        this.preparedDiffTask = _p;
    }

    public AikiNatLgNamesNavigation getPreparedProgTask() {
        return preparedProgTask;
    }

    public void setPreparedProgTask(AikiNatLgNamesNavigation _p) {
        this.preparedProgTask = _p;
    }

    public static ScrollCustomGraphicList<BallNumberRate> ballPanel(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<BallNumberRate> _rend) {
        return new DefScrollCustomGraphicList<BallNumberRate>(_compo,_img,_rend,true);
    }

    public static ScrollCustomGraphicList<Fighter> fighter(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<Fighter> _rend) {
        return new DefScrollCustomGraphicList<Fighter>(_compo,_img,_rend,true);
    }

    public static ScrollCustomGraphicList<UsablePokemon> usable(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<UsablePokemon> _rend) {
        return new DefScrollCustomGraphicList<UsablePokemon>(_compo,_img,_rend,true);
    }

    public static ScrollCustomGraphicList<String> str(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<String> _rend) {
        return new DefScrollCustomGraphicList<String>(_compo,_img,_rend,true);
    }

}
