package cards.gui.containers;



import cards.gui.WindowCardsInt;
import cards.gui.events.ListenerCardPresidentDiscard;
import cards.gui.labels.GraphicCard;
import cards.gui.labels.IntCardConverter;
import cards.gui.labels.PresidentCardConverter;
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

//    private CardPresident carteSurvoleePresident;

    private final HandPresident givenCards = new HandPresident();

    private final HandPresident receivedCards = new HandPresident();

    private final HandPresident virtualHand = new HandPresident();

    ContainerPresident(WindowCardsInt _window) {
        super(_window);
        arretDemo = _window.getThreadFactory().newAtomicInteger();
    }
    public IntCardConverter<CardPresident> converter() {
        return new PresidentCardConverter();
    }
    public CustList<GraphicCard<CardPresident>> getGraphicCards(CustList<CardPresident> _hand) {
        return getGraphicCards(getOwner(),getOwner().getFrames().currentLg(),_hand, converter());
    }
    public static CustList<GraphicCard<CardPresident>> getGraphicCards(ContainerPlayablePresident _fact, CustList<CardPresident> _hand) {
        return getGraphicCards(_fact.getOwner(),_fact.getOwner().getFrames().currentLg(),_hand, _fact.converter());
    }
    public static CustList<GraphicCard<CardPresident>> getGraphicCards(WindowCardsInt _fact, TranslationsLg _lg, CustList<CardPresident> _hand, IntCardConverter<CardPresident> _conv) {
//        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
//        CustList<GraphicCard<CardPresident>> list_;
//        list_ = new CustList<GraphicCard<CardPresident>>();
//        boolean entered_ = false;
//        for(CardPresident c: _hand) {
//            GraphicCard<CardPresident> carte_=new GraphicCard<CardPresident>(new PresidentCardConverter(), GameEnum.PRESIDENT,c, !entered_, _fact.getFrames(), _lg);
//            carte_.setPreferredSize(entered_);
//            int w_ = carte_.getWidth();
//            int h_ = carte_.getHeight();
//            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
//            img_.setFont(carte_.getPaintableLabel());
//            carte_.paintComponent(img_);
//            carte_.setIcon(imageFactory_,img_);
//            list_.add(carte_);
//            entered_ = true;
//        }
        return new ContainerSingUtil<CardPresident>(_conv).getGraphicCardsGene(_fact.getFrames(),_lg,_hand);
//        return list_;
    }

    public static void updateCardsInPanelPresidentDiscard(ContainerPlayablePresident _cpp) {
        updateCardsInPanelPresidentDiscard(_cpp,_cpp.getPanelHand(), _cpp.getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(_cpp,_cpp.getPanelGivenCards(), _cpp.getGivenCards(), false);
    }
    public static void updateCardsInPanelPresidentDiscard(ContainerPlayablePresident _cpp,AbsPanel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        int index_ = IndexConstants.FIRST_INDEX;
        for (GraphicCard<CardPresident> c: getGraphicCards(_cpp,_hand.getCards())) {
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
                l_.setBackground(_panel.getBackgroundValue());
                l_.setForeground(_panel.getForegroundValue());
                _panel.add(l_);
                index_++;
            }
        }
        _panel.setSize(_panel.getPreferredSizeValue());
    }
    public StringList pseudosPresident(int _nbPlayers) {
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
    public void discard(int _index) {
        CardPresident c_ = virtualHand.carte(_index);
        virtualHand.supprimerCarte(_index);
        givenCards.ajouter(c_);
    }

    public void cancelDiscard(int _index) {
        CardPresident c_ = givenCards.carte(_index);
        givenCards.supprimerCarte(_index);
        virtualHand.ajouter(c_);
    }

    public static void fetchLooser(ContainerPresident _current,GamePresident _g) {
        Ints l_ = _g.getLoosers(Ints.newList(DealPresident.NUMERO_UTILISATEUR));
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
        Ints w_ = _g.getWinners(Ints.newList(DealPresident.NUMERO_UTILISATEUR));
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
        for (GraphicCard<CardPresident> c: getGraphicCards(getReceivedCards().getCards())) {
            getPanelReceivedCards().add(c.getPaintableLabel());
        }
        getPanelReceivedCards().validate();
    }

    public void updateCardsInPanelPresidentGiven() {
        getPanelGivenCards().removeAll();
        for (GraphicCard<CardPresident> c: getGraphicCards(getGivenCards().getCards())) {
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

//    public CardPresident getCarteSurvoleePresident() {
//        return carteSurvoleePresident;
//    }
//
//    public void setCarteSurvoleePresident(CardPresident _carteSurvoleePresident) {
//        carteSurvoleePresident = _carteSurvoleePresident;
//    }

    public HandPresident getGivenCards() {
        return givenCards;
    }

    public HandPresident getReceivedCards() {
        return receivedCards;
    }

    public HandPresident getVirtualHand() {
        return virtualHand;
    }

    public AbstractFutureParam<CardNatLgNamesNavigation> retrieve(String _conf) {
        return getOwner().getPrepared().getVal(_conf);
    }
}
