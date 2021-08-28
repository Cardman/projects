package cards.gui.dialogs;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.comboboxes.ComboBoxEnumCards;
import code.gui.*;
import code.gui.initialize.AbsFrameFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;
import code.util.ints.Listable;

public abstract class DialogBelote extends DialogCards {

    private static final String ALL_POINTS_FOR_DEFENDER_TEAM = "allPointsForDefenderTeam";
    private static final String ALLOWED_DECLARING = "allowedDeclaring";
    private static final String CST_BIDS = "bids";
    private static final String DEALING = "dealing";
    private static final String DEALING_MODE = "dealingMode";
    private static final String DECLARING = "declaring";
    private static final String END_DEAL = "endDeal";
    private static final String MIX_CARDS = "mixCards";
    private static final String NUMBER_DEALS = "numberDeals";
    private static final String RULES_TRUMPS = "rulesTrumps";
    private static final String SCORING = "scoring";
    private static final String TRUMPING = "trumping";
    private static final String TRUMPING_DESCRIPTION = "trumpingDescription";
    private static final String UNDER_TRUMPING_FOE = "underTrumpingFoe";
    private RulesBelote reglesBelote=new RulesBelote();
    private AbsSpinner nbGames;
    private StringMap<String> messages = new StringMap<String>();
    private final EnumMap<DeclaresBelote,Integer> indicesAnnoncesValides = new EnumMap<DeclaresBelote,Integer>();
    private ComboBox<MixCardsChoice> listeChoix;
    private AbsCustCheckBox dealAll;

    private AbsPanel bidding;
    private final CustList<AbsCustCheckBox> bids = new CustList<AbsCustCheckBox>();
    private AbsPanel declaresFirstRound;
    private final CustList<AbsCustCheckBox> declares = new CustList<AbsCustCheckBox>();
    private AbsCustCheckBox underTrumpingFoe;
    private ComboBoxEnumCards<BeloteTrumpPartner> listChoiceTwo;
    private AbsCustCheckBox classic;

    protected DialogBelote(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
    }
//    public DialogBelote(String _titre, MainWindow _fenetre, RulesBelote _rulesBelote) {
//        super(_titre, _fenetre, true);
//        setReglesBelote(_rulesBelote);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected void initJt(WindowCards _window, AbsSpinner _nbGames, String _lg) {
        setNbGames(_nbGames);
        AbsPanel dealing_=_window.getCompoFactory().newGrid(0,2);
        //Sous - panneau Battre les cartes
        dealing_.add(new TextLabel(getMessages().getVal(MIX_CARDS)));
        listeChoix=new ComboBox<MixCardsChoice>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(), new StringList(), -1, _window.getCompoFactory()));
        Listable<MixCardsChoice> mix_;
        mix_ = new EnumList<MixCardsChoice>(MixCardsChoice.values());
        EnumMap<MixCardsChoice, String> trMix_;
        trMix_ = new EnumMap<MixCardsChoice, String>();
        for (MixCardsChoice choix_: mix_) {
            trMix_.put(choix_, Games.toString(choix_,_lg));
        }
        listeChoix.refresh(mix_, trMix_);
//        for (MixCardsChoice choix_:MixCardsChoice.values()) {
//            listeChoix.addItem(choix_);
//        }
        listeChoix.setSelectedItem(getReglesBelote().getCartesBattues());
        dealing_.add(listeChoix.self());
        dealAll = getCompoFactory().newCustCheckBox(getMessages().getVal(DEALING_MODE));
        dealAll.setSelected(getReglesBelote().dealAll());
        dealing_.add(dealAll);
        dealing_.add(new TextLabel(""));
        if (getNbGames() != null) {
            dealing_.add(new TextLabel(getMessages().getVal(NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }

        //Panneau Distribution
        getJt().add(getMessages().getVal(DEALING),dealing_);
        AbsPanel bidding_=_window.getCompoFactory().newPageBox();
        //Panneau Annonces autorisees
        bidding_.add(new TextLabel(getMessages().getVal(CST_BIDS)));
        bids.clear();
        bidding=_window.getCompoFactory().newGrid(1,0);
        for (BidBelote enchere_:BidBelote.values()) {
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,_lg));
            caseCroix_.setSelected(getReglesBelote().getEncheresAutorisees().getVal(enchere_));
            caseCroix_.setEnabled(!enchere_.getToujoursPossibleAnnoncer());
            bidding.add(caseCroix_);
            bids.add(caseCroix_);
        }


        bidding_.add(bidding);

        bidding_.add(new TextLabel(getMessages().getVal(ALLOWED_DECLARING)));
        declaresFirstRound=_window.getCompoFactory().newGrid(0,3);
        declares.clear();
        int indice_ = 0;
        for (DeclaresBelote enchere_:DeclaresBelote.annoncesValides()) {
            indicesAnnoncesValides.put(enchere_, indice_);
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,_lg));
            caseCroix_.setSelected(getReglesBelote().getAnnoncesAutorisees().getVal(enchere_));
            declaresFirstRound.add(caseCroix_);
            declares.add(caseCroix_);
            indice_++;
        }
        bidding_.add(declaresFirstRound);

        getJt().add(getMessages().getVal(DECLARING),bidding_);
        AbsPanel trumping_ = _window.getCompoFactory().newGrid(0,1);
        //Panneau gestion des coupes
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid(0,2);
        TextLabel trumpingLabel_ = new TextLabel(getMessages().getVal(TRUMPING));
        trumpingLabel_.setToolTipText(getMessages().getVal(TRUMPING_DESCRIPTION));
        sousPanneau_.add(trumpingLabel_);
        listChoiceTwo=new ComboBoxEnumCards<BeloteTrumpPartner>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _window.getCompoFactory()));
        BeloteTrumpPartner curOne_ = getReglesBelote().getGestionCoupePartenaire();
        int index_ = 0;
        int i_ = -1;
        for(BeloteTrumpPartner choix_:BeloteTrumpPartner.values()) {
            if (choix_ == curOne_) {
                i_ = index_;
            }
            listChoiceTwo.addItem(choix_, Games.toString(choix_,_lg));
            index_++;
        }
        if (i_ > -1) {
            listChoiceTwo.selectItem(i_);
        }
        sousPanneau_.add(listChoiceTwo.self());
        underTrumpingFoe=getCompoFactory().newCustCheckBox(getMessages().getVal(UNDER_TRUMPING_FOE));
        underTrumpingFoe.setSelected(getReglesBelote().getSousCoupeAdv());
        sousPanneau_.add(underTrumpingFoe);
        trumping_.add(sousPanneau_);
        getJt().add(getMessages().getVal(RULES_TRUMPS),trumping_);
        //Panneau Calcul des scores
        AbsPanel endOfGame_=_window.getCompoFactory().newGrid(0,1);
        endOfGame_.add(new TextLabel(getMessages().getVal(SCORING)));
        classic=getCompoFactory().newCustCheckBox(getMessages().getVal(ALL_POINTS_FOR_DEFENDER_TEAM));
        classic.setSelected(getReglesBelote().getComptePointsClassique());
        endOfGame_.add(classic);
        getJt().add(getMessages().getVal(END_DEAL),endOfGame_);
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(WindowCards _parent);
    protected void initMessageName(WindowCards _parent) {
        setMessages(WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), getCardDialog().getAccessFile()));
    }
    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    protected void validateRules() {


//        getReglesBelote().setCartesBattues((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesBelote().setCartesBattues(listeChoix.getCurrent());
        EnumMap<BidBelote,Boolean> contrats_ = new EnumMap<BidBelote,Boolean>();
        for (BidBelote enchere_: BidBelote.values()) {
            AbsCustCheckBox jcb_= bids.get(enchere_.ordinal());
            contrats_.put(enchere_, jcb_.isSelected());
        }
        getReglesBelote().setEncheresAutorisees(contrats_);

        EnumMap<DeclaresBelote,Boolean> annonces_ = new EnumMap<DeclaresBelote,Boolean>();
        for (DeclaresBelote enchere_: indicesAnnoncesValides.getKeys()) {
            AbsCustCheckBox jcb_= declares.get(indicesAnnoncesValides.getVal(enchere_));
            annonces_.put(enchere_, jcb_.isSelected());
        }
        getReglesBelote().setAnnoncesAutorisees(annonces_);

        BeloteTrumpPartner gestionCoupe_= listChoiceTwo.getCurrentElement();
        getReglesBelote().setGestionCoupePartenaire(gestionCoupe_);
        getReglesBelote().setSousCoupeAdv(underTrumpingFoe.isSelected());
        getReglesBelote().setComptePointsClassique(classic.isSelected());
        if (dealAll.isSelected()) {
            getReglesBelote().setRepartition(DealingBelote.COINCHE_2_VS_2);
        } else {
            getReglesBelote().setRepartition(DealingBelote.CLASSIC_2_VS_2);
        }
    }

    protected StringMap<String> getMessages() {
        return messages;
    }

    protected void setMessages(StringMap<String> _messages) {
        messages = _messages;
    }

    protected RulesBelote getReglesBelote() {
        return reglesBelote;
    }

    protected void setReglesBelote(RulesBelote _reglesBelote) {
        reglesBelote = _reglesBelote;
    }

    protected AbsSpinner getNbGames() {
        return nbGames;
    }

    protected void setNbGames(AbsSpinner _nbGames) {
        nbGames = _nbGames;
    }

}
