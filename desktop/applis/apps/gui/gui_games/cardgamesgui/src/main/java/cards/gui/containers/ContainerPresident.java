package cards.gui.containers;



import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.events.ListenerCardPresidentDiscard;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.Carpet;
import cards.gui.panels.CarpetPresident;
import cards.main.CardNatLgNamesNavigation;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.gui.AbsPanel;
import code.gui.AbsButton;
import code.gui.AbsPlainLabel;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.threads.AbstractFutureParam;
import code.util.*;
import code.util.core.IndexConstants;

public abstract class ContainerPresident extends ContainerSingleImpl {

    public static final String EMPTY="";
    public static final String TAB="\t";

    private AbsPanel panelReceivedCards;
    private AbsPanel panelGivenCards;

    private final AbstractAtomicInteger arretDemo;

    private AbsButton noPlay;
    private AbsButton givingCardsOk;

    private CardPresident carteSurvoleePresident;

    private byte indexCard;

    private HandPresident givenCards = new HandPresident();

    private HandPresident receivedCards = new HandPresident();

    private HandPresident virtualHand = new HandPresident();

    ContainerPresident(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicInteger();
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
            GraphicPresidentCard carte_=new GraphicPresidentCard(_fact.getFrames(),_lg, c, !entered_);
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

    public static void updateCardsInPanelPresidentDiscard(ContainerPlayablePresident _cpp) {
        updateCardsInPanelPresidentDiscard(_cpp,_cpp.getPanelHand(), _cpp.getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(_cpp,_cpp.getPanelGivenCards(), _cpp.getGivenCards(), false);
    }
    public static void updateCardsInPanelPresidentDiscard(ContainerPlayablePresident _cpp,AbsPanel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        byte index_ = IndexConstants.FIRST_INDEX;
        TranslationsLg lg_ = _cpp.getOwner().getFrames().currentLg();
        for (GraphicPresidentCard c: getGraphicCards(_cpp.getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardPresidentDiscard(_cpp,c.getCard(),index_,_inHand));
            _panel.add(c.getPaintableLabel());
            index_++;
        }
        if (!_inHand) {
            int rec_ = _cpp.getReceivedCards().total();
            while (index_ < rec_) {
                AbsPlainLabel l_ = _cpp.getOwner().getCompoFactory().newPlainLabel("");
                if (index_ > IndexConstants.FIRST_INDEX) {
                    l_.setPreferredSize(Carpet.getDimension(true));
                } else {
                    l_.setPreferredSize(Carpet.getDimension(false));
                }
                l_.setBackground(_panel);
                l_.setForeground(_panel);
                _panel.add(l_);
                index_++;
            }
        }
        _panel.validate();
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
    public HandPresident chargerPilePresident(int _nbStacks) {
        return getOwner().baseWindow().getFacadeCards().getNicknamesCrud().getCardGamesCrud().president(_nbStacks);
    }

    public void noPlayText(Playing _status) {
        if (_status == Playing.HAS_TO_EQUAL) {
            getNoPlay().setText(file().getVal(MessagesGuiCards.MAIN_NO_PLAY_NOW));
        } else {
            getNoPlay().setText(file().getVal(MessagesGuiCards.MAIN_PASS_TRICK));
        }
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

    public static void fetchLooser(ContainerPresident _current,GamePresident _g) {
        Bytes l_ = _g.getLoosers(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        if (!l_.isEmpty()) {
            _current.getReceivedCards().supprimerCartes();
            _current.getReceivedCards().ajouterCartes(_g.getSwitchedCards().get(_g.getMatchingWinner(DealPresident.NUMERO_UTILISATEUR)));
            _current.updateCardsInPanelPresidentReceived();
            _current.getGivenCards().supprimerCartes();
            _current.getGivenCards().ajouterCartes(_g.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
            _current.updateCardsInPanelPresidentGiven();
        }
    }
    public static void fetchWinner(ContainerPresident _current,GamePresident _g) {
        Bytes w_ = _g.getWinners(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
        if (!w_.isEmpty()) {
            _current.getReceivedCards().supprimerCartes();
            _current.getReceivedCards().ajouterCartes(_g.getSwitchedCards().get(_g.getMatchingLoser(DealPresident.NUMERO_UTILISATEUR)));
            _current.updateCardsInPanelPresidentReceived();
            _current.getGivenCards().supprimerCartes();
            _current.getGivenCards().ajouterCartes(_g.getSwitchedCards().get(DealPresident.NUMERO_UTILISATEUR));
            _current.updateCardsInPanelPresidentGiven();
        }
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
//    public boolean isArretDemo() {
//        return arretDemo.get();
//    }
//    public void setArretDemo(boolean _arretDemo) {
//        arretDemo.set(_arretDemo);
//    }

    public AbstractAtomicInteger getArretDemo() {
        return arretDemo;
    }

    public AbsButton getNoPlay() {
        return noPlay;
    }

    protected void setNoPlay(AbsButton _noPlay) {
        noPlay = _noPlay;
    }

    public AbsButton getGivingCardsOk() {
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

    public AbsPanel getPanelGivenCards() {
        return panelGivenCards;
    }

    public void setPanelGivenCards(AbsPanel _panelGivenCards) {
        panelGivenCards = _panelGivenCards;
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

    public HandPresident getVirtualHand() {
        return virtualHand;
    }

    protected void setVirtualHand(HandPresident _virtualHand) {
        virtualHand = _virtualHand;
    }

    public StringMap<String> readResource() {
        return Games.getCommonPresidentTr(Games.getAppliTr(getOwner().getFrames().currentLg())).getMapping();
//        return MessagesPresidentPresident.ms().getVal(StringUtil.concat(PresidentResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", PresidentResoucesAccess.NOM_FICHIER));
    }
    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }
}
