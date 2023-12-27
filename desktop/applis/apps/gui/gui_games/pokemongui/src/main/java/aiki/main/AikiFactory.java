package aiki.main;

import aiki.db.DataBase;
import aiki.game.fight.BallNumberRate;
import aiki.game.fight.Fighter;
import aiki.map.pokemon.UsablePokemon;
import code.gui.AbsCustCellRenderGene;
import code.gui.DefScrollCustomGraphicList;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class AikiFactory {
    private final AbstractBaseExecutorServiceParam<DataBase> geneDb;
    private AbstractFutureParam<DataBase> taskLoad;
    public AikiFactory(AbstractBaseExecutorServiceParam<DataBase> _g) {
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
