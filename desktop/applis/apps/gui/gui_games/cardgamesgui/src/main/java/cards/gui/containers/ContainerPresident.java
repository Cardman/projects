package cards.gui.containers;



import cards.facade.FacadeCards;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.CarpetPresident;
import cards.main.CardNatLgNamesNavigation;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.sml.DocumentReaderPresidentUtil;
import code.gui.AbsPanel;
import code.gui.AbsButton;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.stream.StreamTextFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractFutureParam;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

public abstract class ContainerPresident extends ContainerSingleImpl {

    public static final String EMPTY="";
    public static final String TAB="\t";

    private AbsPanel panelReceivedCards;
    private AbsPanel panelGivenCards;

    /**Renvoie tous les scores de toutes les parties non solitaires*/
    private CustList<Longs> scores=new CustList<Longs>();
    /**Maximum des valeurs absolues des scores centr&eacute;s par rapport &agrave; la moyenne*/
    private long maxAbsoluScore;
    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean partieSauvegardee;
    /**Vrai si et seulement si au moins une partie aleatoire a ete jouee depuis le dernier passage dans le menu principal*/
    private boolean partieAleatoireJouee;

    private final AbstractAtomicBoolean arretDemo;

    private boolean canDiscard;
    private boolean canPlay;

    private AbsButton noPlay;
    private AbsButton givingCardsOk;

    private CardPresident carteSurvoleePresident;

    private byte indexCard;

    private int nbGivenCards;

    private HandPresident givenCards = new HandPresident();

    private HandPresident receivedCards = new HandPresident();

    private HandPresident virtualHand = new HandPresident();

    private int nbStacks = 1;

    ContainerPresident(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicBoolean();
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    public static CustList<GraphicPresidentCard> getGraphicCards(WindowCardsInt _fact, TranslationsLg _lg, CustList<CardPresident> _hand) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        CustList<GraphicPresidentCard> list_;
        list_ = new CustList<GraphicPresidentCard>();
        boolean entered_ = false;
        for(CardPresident c: _hand) {
            GraphicPresidentCard carte_=new GraphicPresidentCard(imageFactory_,_lg, c, GuiConstants.RIGHT,!entered_, _fact.getCompoFactory());
            carte_.setPreferredSize(entered_);
            int w_ = carte_.getWidth();
            int h_ = carte_.getHeight();
            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
            img_.setFont(carte_.getPaintableLabel());
            carte_.paintComponent(img_);
            carte_.setIcon(imageFactory_,img_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
    }

    public StringList pseudosPresident(byte _nbPlayers) {
        StringList pseudosTwo_=new StringList();
        pseudosTwo_.add(pseudo());
        StringList pseudos_ = getPseudosJoueurs().getPseudosPresident();
        pseudosTwo_.addAllElts(pseudos_.left(_nbPlayers - 1));
        return pseudosTwo_;
    }

    /**Permet de charger une main de distribution
    a partir d'un fichier*/
    protected static HandPresident chargerPilePresident(int _nbStacks, AbstractProgramInfos _tmpUserFolderSl) {
        return DocumentReaderPresidentUtil.getHandPresident(StreamTextFile.contentsOfFile(
                FacadeCards.presidentStack(WindowCards.getTempFolderSl(_tmpUserFolderSl),_nbStacks),_tmpUserFolderSl.getFileCoreStream(), _tmpUserFolderSl.getStreams()));
    }

    public void discard(byte _index) {
        CardPresident c_ = virtualHand.carte(_index);
        virtualHand.supprimerCarte(_index);
        givenCards.ajouter(c_);
    }

    public void cancelDiscard(byte _index) {
        CardPresident c_ = givenCards.carte(_index);
        givenCards.supprimerCarte(_index);
        virtualHand.ajouter(c_);
    }

    protected AbsPanel assemble() {
        AbsPanel panelCards_ = getOwner().getCompoFactory().newLineBox();
        panelCards_.add(getPanelGivenCards());
        panelCards_.add(getPanelReceivedCards());
        return panelCards_;
    }

    public void updateCardsInPanelPresidentReceived() {
        getPanelReceivedCards().removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicPresidentCard c: getGraphicCards(getWindow(),lg_, getReceivedCards().getCards())) {
            getPanelReceivedCards().add(c.getPaintableLabel());
        }
        getPanelReceivedCards().validate();
    }

    public void updateCardsInPanelPresidentGiven() {
        getPanelGivenCards().removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicPresidentCard c: getGraphicCards(getWindow(),lg_,getGivenCards().getCards())) {
            getPanelGivenCards().add(c.getPaintableLabel());
        }
        getPanelGivenCards().validate();
    }

    public String pseudo() {
        return getPseudosJoueurs().getPseudo();
    }
    public CarpetPresident tapisPresident() {
        return getTapis().getTapisPresident();
    }
    public boolean isArretDemo() {
        return arretDemo.get();
    }
    public void setArretDemo(boolean _arretDemo) {
        arretDemo.set(_arretDemo);
    }
    public CustList<Longs> getScores() {
        return scores;
    }
    protected void setScores(CustList<Longs> _scores) {
        scores = _scores;
    }

    protected AbsButton getNoPlay() {
        return noPlay;
    }

    protected void setNoPlay(AbsButton _noPlay) {
        noPlay = _noPlay;
    }

    protected AbsButton getGivingCardsOk() {
        return givingCardsOk;
    }

    protected void setGivingCardsOk(AbsButton _givingCardsOk) {
        givingCardsOk = _givingCardsOk;
    }

    protected AbsPanel getPanelReceivedCards() {
        return panelReceivedCards;
    }

    public void setPanelReceivedCards(AbsPanel _panelReceivedCards) {
        panelReceivedCards = _panelReceivedCards;
    }

    protected AbsPanel getPanelGivenCards() {
        return panelGivenCards;
    }

    public void setPanelGivenCards(AbsPanel _panelGivenCards) {
        panelGivenCards = _panelGivenCards;
    }

    public boolean isCanDiscard() {
        return canDiscard;
    }

    public void setCanDiscard(boolean _canDiscard) {
        canDiscard = _canDiscard;
    }

    public boolean isCanPlay() {
        return canPlay;
    }
    public void setCanPlay(boolean _canPlay) {
        canPlay = _canPlay;
    }

    public CardPresident getCarteSurvoleePresident() {
        return carteSurvoleePresident;
    }

    public void setCarteSurvoleePresident(CardPresident _carteSurvoleePresident) {
        carteSurvoleePresident = _carteSurvoleePresident;
    }

    public byte getIndexCard() {
        return indexCard;
    }

    public void setIndexCard(byte _indexCard) {
        indexCard = _indexCard;
    }
    protected long getMaxAbsoluScore() {
        return maxAbsoluScore;
    }
    protected void setMaxAbsoluScore(long _maxAbsoluScore) {
        maxAbsoluScore = _maxAbsoluScore;
    }
    protected boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }
    protected void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
    }
    protected boolean isPartieAleatoireJouee() {
        return partieAleatoireJouee;
    }
    protected void setPartieAleatoireJouee(boolean _partieAleatoireJouee) {
        partieAleatoireJouee = _partieAleatoireJouee;
    }

    public HandPresident getGivenCards() {
        return givenCards;
    }

    public void setGivenCards(HandPresident _givenCards) {
        givenCards = _givenCards;
    }

    public HandPresident getReceivedCards() {
        return receivedCards;
    }

    public void setReceivedCards(HandPresident _receivedCards) {
        receivedCards = _receivedCards;
    }

    protected HandPresident getVirtualHand() {
        return virtualHand;
    }

    protected void setVirtualHand(HandPresident _virtualHand) {
        virtualHand = _virtualHand;
    }

    public int getNbGivenCards() {
        return nbGivenCards;
    }

    public void setNbGivenCards(int _nbGivenCards) {
        nbGivenCards = _nbGivenCards;
    }

    protected int getNbStacks() {
        return nbStacks;
    }

    protected void setNbStacks(int _nbStacks) {
        nbStacks = _nbStacks;
    }

    public StringMap<String> readResource() {
        return Games.getCommonPresidentTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
//        return MessagesPresidentPresident.ms().getVal(StringUtil.concat(PresidentResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", PresidentResoucesAccess.NOM_FICHIER));
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }
}
