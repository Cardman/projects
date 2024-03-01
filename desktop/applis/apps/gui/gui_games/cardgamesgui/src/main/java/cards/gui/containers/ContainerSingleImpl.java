package cards.gui.containers;

import cards.consts.GameType;
import cards.consts.ResultsGame;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.containers.events.KeepPlayingEditedEvent;
import cards.gui.containers.events.KeepPlayingRandomEvent;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.Graphic;
import cards.gui.labels.GraphicKey;
import cards.main.CardsNonModalEvent;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.maths.Rate;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloUtil;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public abstract class ContainerSingleImpl extends ContainerGame {
    public static final int PAUSE_ALIVE = 0;
    public static final int PAUSE_STOPPED = 1;
    private static final int ONE_QUATER = 64;
    private static final int ZERO_QUATER = 0;
    private static final int TWO_QUATER = 128;
    private static final int THREE_QUATER = 192;
    private static final int FOUR_QUATER = 255;
    private final WindowCardsInt window;
    private final AbstractAtomicInteger paused;
//    private final AbstractAtomicBoolean passe;
    private AbsButton nextDeal;
    /**Renvoie tous les scores de toutes les parties non solitaires*/
    private CustList<Longs> scores=new CustList<Longs>();
    /**Maximum des valeurs absolues des scores centr&eacute;s par rapport &agrave; la moyenne*/
    private long maxAbsoluScore;
//    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
//    private boolean partieSauvegardee;

    protected ContainerSingleImpl(WindowCardsInt _window) {
        super(_window.noGame());
        window = _window;
//        passe = _window.getThreadFactory().newAtomicBoolean();
        paused = _window.getThreadFactory().newAtomicInteger(PAUSE_ALIVE);
        setEvents(getOwner().getCompoFactory().newTextArea("",8, 30));
    }
    public static Ints couleursCourbes(AbstractGenerator _gene) {
        Ints colors_ = couleursCourbes();
        Ints edit_ = new Ints();
        while (!colors_.isEmpty()) {
            int v_ = (int) MonteCarloUtil.randomLong(colors_.size(), _gene);
            edit_.add(colors_.get(v_));
            colors_.remove(v_);
        }
        return edit_;
    }
    public static Ints couleursCourbes() {
        Ints couleurs_=new Ints();
        couleurs_.add(GuiConstants.RED);
        couleurs_.add(GuiConstants.GREEN);
        couleurs_.add(GuiConstants.BLUE);
        couleurs_.add(GuiConstants.YELLOW);
        couleurs_.add(GuiConstants.MAGENTA);
        couleurs_.add(GuiConstants.CYAN);
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, ONE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, ONE_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, ONE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, TWO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, TWO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, TWO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, THREE_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, THREE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, THREE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, FOUR_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, FOUR_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ZERO_QUATER, FOUR_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, ZERO_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, ZERO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, ZERO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, TWO_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, TWO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, TWO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, THREE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, THREE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, THREE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, FOUR_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, FOUR_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(ONE_QUATER, FOUR_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ZERO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ZERO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ZERO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ONE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ONE_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, ONE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, THREE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, THREE_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, THREE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, FOUR_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, FOUR_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(TWO_QUATER, FOUR_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ZERO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ZERO_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ZERO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ONE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ONE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, ONE_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, TWO_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, TWO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, TWO_QUATER, FOUR_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, FOUR_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, FOUR_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(THREE_QUATER, FOUR_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ZERO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ZERO_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ZERO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ONE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ONE_QUATER, TWO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, ONE_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, TWO_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, TWO_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, TWO_QUATER, THREE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, THREE_QUATER, ZERO_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, THREE_QUATER, ONE_QUATER));
        couleurs_.add(GuiConstants.newColor(FOUR_QUATER, THREE_QUATER, TWO_QUATER));
        return couleurs_;
    }
    public void updateGraphicLines(AbsTabbedPane _onglets, ResultsGame _res, int _nbPlayers, StringList _pseudos) {
        Ints couleurs_=couleursCourbes(getOwner().getGenerator());
        Graphic graphique_=new Graphic(getScores(),new Longs(_res.getSums()),new CustList<Rate>(_res.getSigmas()),couleurs_, getOwner().getCompoFactory());
        Rate derniereMoyenne_=new Rate(_res.getSums().last(), _nbPlayers);
        CustList<Rate> scoresCentresMoyenne_=new CustList<Rate>();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_< _nbPlayers; joueur_++) {
            scoresCentresMoyenne_.add(Rate.minus(new Rate(getScores().last().get(joueur_)), derniereMoyenne_));
        }
        scoresCentresMoyenne_.add(Rate.multiply(new Rate(3), _res.getSigmas().last()));
        Rate max_ = Rate.zero();
        for(Rate maximum_:scoresCentresMoyenne_) {
            if (Rate.strGreater(maximum_.absNb(), max_)) {
                max_ = maximum_.absNb();
            }
        }
        setMaxAbsoluScore(NumberUtil.max(max_.ll(),getMaxAbsoluScore()));
        int dimy_=(int)getMaxAbsoluScore();
        graphique_.setPreferredSize(new MetaDimension(2000,dimy_));
        AbsScrollPane locScroll_ = getOwner().getCompoFactory().newAbsScrollPane(graphique_.getPaintableLabel());
        graphique_.setLocation(0,(600-dimy_)/2);
        AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),graphique_);
        locScroll_.setPreferredSize(new MetaDimension(300,200));
        AbsPanel panneau_=getOwner().getCompoFactory().newBorder();
        panneau_.add(getOwner().getCompoFactory().newPlainLabel(file().getVal(MessagesGuiCards.MAIN_SCORES_EVOLUTION_DETAIL)),GuiConstants.BORDER_LAYOUT_NORTH);
        panneau_.add(locScroll_,GuiConstants.BORDER_LAYOUT_CENTER);
        GraphicKey legende_=new GraphicKey(_pseudos,couleurs_, getOwner().getFrames());
        legende_.setPreferredSize(new MetaDimension(300,15*(_nbPlayers +1)));
        AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),legende_);
        locScroll_=getOwner().getCompoFactory().newAbsScrollPane(legende_.getPaintableLabel());
        locScroll_.setPreferredSize(new MetaDimension(300,100));
        panneau_.add(locScroll_,GuiConstants.BORDER_LAYOUT_SOUTH);
        _onglets.add(file().getVal(MessagesGuiCards.MAIN_SCORES_EVOLUTION),panneau_);
    }
    public static void resultButtons(AbsPanel _buttons,ContainerSin _single) {
        GameType type_ = _single.getGameType();
        _single.setNextDeal(null);
        if(type_== GameType.EDIT&&_single.nombreParties()<_single.nombreTotalParties()) {
            AbsButton bouton_=_single.getOwner().getCompoFactory().newPlainButton(_single.file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_EDITED_DEAL));
            bouton_.addActionListener(new CardsNonModalEvent(_single),new KeepPlayingEditedEvent(_single));
            _buttons.add(bouton_);
            _single.setNextDeal(bouton_);
        } else if(type_==GameType.RANDOM) {
//        } else if(type_==GameType.EDIT&&nombreParties_==nombreTotalParties_&&_single.isPartieAleatoireJouee()||type_==GameType.RANDOM) {
            AbsButton bouton_=_single.getOwner().getCompoFactory().newPlainButton(_single.file().getVal(MessagesGuiCards.MAIN_KEEP_PLAYING_DEAL));
            bouton_.addActionListener(new CardsNonModalEvent(_single),new KeepPlayingRandomEvent(_single));
            _buttons.add(bouton_);
            _single.setNextDeal(bouton_);
        }
    }

    public AbsButton getNextDeal() {
        return nextDeal;
    }

    public void setNextDeal(AbsButton _n) {
        this.nextDeal = _n;
    }

    public String helpMenuTip() {
        return getWindow().getMenusMessages().getVal(MessagesGuiCards.CST_GO_HELP_MENU);
    }
    public StringMap<String> file() {
        return file(getOwner().getFrames().currentLg());
    }
    public static StringMap<String> file(TranslationsLg _lg) {
        return Games.getMainGame(Games.getAppliTr(_lg)).getMapping();
    }
    public StringMap<String> fileSimu() {
        return fileSimu(getOwner().getFrames().currentLg());
    }
    public static StringMap<String> fileSimu(TranslationsLg _lg) {
        return Games.getSimuGame(Games.getAppliTr(_lg)).getMapping();
    }
    protected long getMaxAbsoluScore() {
        return maxAbsoluScore;
    }
    protected void setMaxAbsoluScore(long _maxAbsoluScore) {
        maxAbsoluScore = _maxAbsoluScore;
    }
    public CustList<Longs> getScores() {
        return scores;
    }
    protected void setScores(CustList<Longs> _scores) {
        scores = _scores;
    }
//    public boolean isPartieSauvegardee() {
//        return partieSauvegardee;
//    }
//    protected void setPartieSauvegardee(boolean _partieSauvegardee) {
//        partieSauvegardee = _partieSauvegardee;
//    }

    public void revalidate() {
        getWindow().revalidateFrame();
    }

    public void pack() {
        getWindow().pack();
    }

    public TranslationsAppli readResourceAppli() {
        return Games.getAppliTr(getOwner().getFrames().currentLg());
    }

    public final WindowCardsInt getOwner() {
        return getWindow();
    }
    public AbsPanel getPane() {
        return getWindow().getPane();
    }
    public void setContentPane(AbsPanel _container) {
        getWindow().setContentPane(_container);
    }
//    public void saveCurrentGame(String _file) {
//        getPar().sauvegarderPartieEnCours(_file, getWindow().getStreams());
//    }

    public WindowCardsInt getWindow() {
        return window;
    }

    public EnabledMenu getChange() {
        return ((WindowCards)window).getChange();
    }

    public EnabledMenu getSave() {
        return ((WindowCards)window).getSave();
    }

    public EnabledMenu getConsulting() {
        return ((WindowCards)window).getConsulting();
    }

    public EnabledMenu getPause() {
        return ((WindowCards)window).getPause();
    }

    public EnabledMenu getHelpGame() {
        return ((WindowCards)window).getHelpGame();
    }

//    public AbsMenu getHelp() {
//        return window.getHelp();
//    }
//    public boolean isPasse() {
//        return passe.get();
//    }
//    public void setPasse(boolean _passe) {
//        passe.set(_passe);
//    }

    public AbstractAtomicInteger getPaused() {
        return paused;
    }
//    public AbsMenuItem getGeneralHelp() {
//        return window.getGeneralHelp();
//    }

//    public AbsMenu getEdit() {
//        return window.getEdit();
//    }

//    public IdMap<GameEnum,AbsMenuItem> getEditGames() {
//        return window.getEditGames();
//    }

//    public EnabledMenu getDemo() {
//        return ((WindowCards)window).getDemo();
//    }

//    public IdMap<GameEnum,AbsMenuItem> getDemoGames() {
//        return window.getDemoGames();
//    }

//    public AbsMenu getTraining() {
//        return window.getTraining();
//    }

//    public IdMap<ChoiceTarot,AbsMenuItem> getTrainingTarot() {
//        return window.getTrainingTarot();
//    }

    public StringMap<String> readCoreResourceSuit() {
        return Games.getCommonFileTr(readResourceAppli()).getMapping();
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

    public StringMap<String> readCoreResourceCards() {
        return Games.getCommonCardsTr(readResourceAppli()).getMapping();
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }

    public void thread(Runnable _animContratBelote) {
        ((WindowCards)window).changeStreamsMenusEnabled(false);
//        MenuItemUtils.setEnabledMenu(getPause(),true);
        getAllThreads().add(getOwner().getThreadFactory().newStartedThread(_animContratBelote));
    }

}
