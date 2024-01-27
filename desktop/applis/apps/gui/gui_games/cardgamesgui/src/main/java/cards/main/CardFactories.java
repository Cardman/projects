package cards.main;

import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.president.enumerations.CardPresident;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsCustCellRenderGene;
import code.gui.DefScrollCustomGraphicList;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;
import code.util.StringMap;

public final class CardFactories {
    private final AbstractBaseExecutorServiceParam<StringMap<StringMap<int[][]>>> geneImgs;
    private final AbstractBaseExecutorServiceParam<CardNatLgNamesNavigation> navigation;
    private AbstractFutureParam<StringMap<StringMap<int[][]>>> taskLoad;
    private AbstractFutureParam<StringMap<StringMap<int[][]>>> taskLoadMiniDef;
    private AbstractFutureParam<StringMap<StringMap<int[][]>>> taskLoadMiniSel;
    private final StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> taskNav = new StringMap<AbstractFutureParam<CardNatLgNamesNavigation>>();
    public CardFactories(AbstractBaseExecutorServiceParam<StringMap<StringMap<int[][]>>> _g, AbstractBaseExecutorServiceParam<CardNatLgNamesNavigation> _n) {
        geneImgs = _g;
        navigation = _n;
        _n.shutdown();
    }
    public AbstractFutureParam<StringMap<StringMap<int[][]>>> submit(IntCallable<StringMap<StringMap<int[][]>>> _i, IntCallable<StringMap<StringMap<int[][]>>> _md, IntCallable<StringMap<StringMap<int[][]>>> _ms) {
        AbstractFutureParam<StringMap<StringMap<int[][]>>> res_ = geneImgs.submitWrCallable(_i);
        taskLoad = res_;
        taskLoadMiniDef = geneImgs.submitWrCallable(_md);
        taskLoadMiniSel = geneImgs.submitWrCallable(_ms);
        geneImgs.shutdown();
        return res_;
    }
    public void submitNav(String _key,IntCallable<CardNatLgNamesNavigation> _n) {
        AbstractBaseExecutorServiceParam<CardNatLgNamesNavigation> n_ = navigation.copy();
        AbstractFutureParam<CardNatLgNamesNavigation> res_ = n_.submitWrCallable(_n);
        getTaskNav().addEntry(_key,res_);
        n_.shutdown();
    }

    public StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> getTaskNav() {
        return taskNav;
    }

    public AbstractFutureParam<StringMap<StringMap<int[][]>>> getTaskLoad() {
        return taskLoad;
    }

    public AbstractFutureParam<StringMap<StringMap<int[][]>>> getTaskLoadMiniDef() {
        return taskLoadMiniDef;
    }

    public AbstractFutureParam<StringMap<StringMap<int[][]>>> getTaskLoadMiniSel() {
        return taskLoadMiniSel;
    }

    public static ScrollCustomGraphicList<CardBelote> belote(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardBelote> _rend) {
        return new DefScrollCustomGraphicList<CardBelote>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<CardPresident> president(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardPresident> _rend) {
        return new DefScrollCustomGraphicList<CardPresident>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<CardTarot> tarot(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardTarot> _rend) {
        return new DefScrollCustomGraphicList<CardTarot>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<Suit> suit(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<Suit> _rend) {
        return new DefScrollCustomGraphicList<Suit>(_compo,_img,_rend,false);
    }

}
