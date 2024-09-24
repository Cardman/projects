package cards.main;

import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.gui.animations.HelpInitializer;
import cards.gui.dialogs.help.HelpIndexesTree;
import cards.president.enumerations.CardPresident;
import cards.solitaire.CardSolitaire;
import cards.tarot.enumerations.CardTarot;
import code.gui.*;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorServiceParam;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;
import code.util.StringMap;

public final class CardFactories {
    private final AbstractBaseExecutorServiceParam<CardNatLgNamesNavigation> navigation;
    private final AbstractBaseExecutorServiceParam<HelpIndexesTree> help;
    private AbstractFutureParam<HelpIndexesTree> helpTask;
    private final EnabledMenu generalHelp;
    private final StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> taskNav = new StringMap<AbstractFutureParam<CardNatLgNamesNavigation>>();
    public CardFactories(AbstractProgramInfos _p, AbstractBaseExecutorServiceParam<CardNatLgNamesNavigation> _n, AbstractBaseExecutorServiceParam<HelpIndexesTree> _h) {
        generalHelp = _p.getCompoFactory().newMenuItem();
        MenuItemUtils.setEnabledMenu(generalHelp,false);
        navigation = _n;
        help = _h;
        _n.shutdown();
    }

    public EnabledMenu getGeneralHelp() {
        return generalHelp;
    }

    public AbstractFutureParam<HelpIndexesTree> getHelpTask() {
        return helpTask;
    }

    public void submitHelp() {
        helpTask = help.submitWrCallable(new HelpInitializer(generalHelp));
        help.shutdown();
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

    public static ScrollCustomGraphicList<CardBelote> belote(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardBelote> _rend) {
        return new DefScrollCustomGraphicList<CardBelote>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<CardPresident> president(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardPresident> _rend) {
        return new DefScrollCustomGraphicList<CardPresident>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<CardTarot> tarot(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardTarot> _rend) {
        return new DefScrollCustomGraphicList<CardTarot>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<CardSolitaire> solitaire(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<CardSolitaire> _rend) {
        return new DefScrollCustomGraphicList<CardSolitaire>(_compo,_img,_rend,false);
    }
    public static ScrollCustomGraphicList<Suit> suit(AbsCompoFactory _compo, AbstractImageFactory _img, AbsCustCellRenderGene<Suit> _rend) {
        return new DefScrollCustomGraphicList<Suit>(_compo,_img,_rend,false);
    }

}
