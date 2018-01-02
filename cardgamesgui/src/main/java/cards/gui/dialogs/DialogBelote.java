package cards.gui.dialogs;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.gui.comboboxes.ComboBoxMixCards;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.ints.Listable;

public abstract class DialogBelote extends DialogCards {

    private static final String ALL_POINTS_FOR_DEFENDER_TEAM = "allPointsForDefenderTeam";
    private static final String ALLOWED_DECLARING = "allowedDeclaring";
    private static final String BIDS = "bids";
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
    private JSpinner nbGames;
    private StringMap<String> messages = new StringMap<String>();
    private EnumMap<DeclaresBelote,Integer> indicesAnnoncesValides = new EnumMap<DeclaresBelote,Integer>();
    private ComboBoxMixCards listeChoix;
    private JCheckBox dealAll;

    private JPanel bidding;
    private CustList<JCheckBox> bids = new CustList<JCheckBox>();
    private JPanel declaresFirstRound;
    private CustList<JCheckBox> declares = new CustList<JCheckBox>();
    private JCheckBox underTrumpingFoe;
    private ComboBoxEnumCards<BeloteTrumpPartner> listChoiceTwo;
    private JCheckBox classic;

    protected DialogBelote() {
    }
//    public DialogBelote(String _titre, MainWindow _fenetre, RulesBelote _rulesBelote) {
//        super(_titre, _fenetre, true);
//        setReglesBelote(_rulesBelote);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected void initJt(JSpinner _nbGames) {
        setNbGames(_nbGames);
        JPanel dealing_=new JPanel();
        dealing_.setLayout(new GridLayout(0,2));
        //Sous - panneau Battre les cartes
        dealing_.add(new JLabel(getMessages().getVal(MIX_CARDS)));
        listeChoix=new ComboBoxMixCards();
        Listable<MixCardsChoice> mix_;
        mix_ = new EnumList<MixCardsChoice>(MixCardsChoice.values());
        EnumMap<MixCardsChoice, String> trMix_;
        trMix_ = new EnumMap<MixCardsChoice, String>();
        for (MixCardsChoice choix_: mix_) {
            trMix_.put(choix_, choix_.display());
        }
        listeChoix.refresh(mix_, trMix_);
//        for (MixCardsChoice choix_:MixCardsChoice.values()) {
//            listeChoix.addItem(choix_);
//        }
        listeChoix.setSelectedItem(getReglesBelote().getCartesBattues());
        dealing_.add(listeChoix);
        dealAll = new JCheckBox(getMessages().getVal(DEALING_MODE));
        dealAll.setSelected(getReglesBelote().dealAll());
        dealing_.add(dealAll);
        dealing_.add(new JLabel());
        if (getNbGames() != null) {
            dealing_.add(new JLabel(getMessages().getVal(NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }

        //Panneau Distribution
        getJt().add(getMessages().getVal(DEALING),dealing_);
        JPanel bidding_=new JPanel();
        bidding_.setLayout(new BoxLayout(bidding_, BoxLayout.PAGE_AXIS));
        //Panneau Annonces autorisees
        bidding_.add(new JLabel(getMessages().getVal(BIDS)));
        bids.clear();
        bidding=new JPanel();
        bidding.setLayout(new GridLayout(1,0));
        for (BidBelote enchere_:BidBelote.values()) {
            JCheckBox caseCroix_=new JCheckBox(enchere_.display());
            caseCroix_.setSelected(getReglesBelote().getEncheresAutorisees().getVal(enchere_));
            caseCroix_.setEnabled(!enchere_.getToujoursPossibleAnnoncer());
            bidding.add(caseCroix_);
            bids.add(caseCroix_);
        }


        bidding_.add(bidding);

        bidding_.add(new JLabel(getMessages().getVal(ALLOWED_DECLARING)));
        declaresFirstRound=new JPanel();
        declares.clear();
        declaresFirstRound.setLayout(new GridLayout(0,3));
        int indice_ = 0;
        for (DeclaresBelote enchere_:DeclaresBelote.annoncesValides()) {
            indicesAnnoncesValides.put(enchere_, indice_);
            JCheckBox caseCroix_=new JCheckBox(enchere_.display());
            caseCroix_.setSelected(getReglesBelote().getAnnoncesAutorisees().getVal(enchere_));
            declaresFirstRound.add(caseCroix_);
            declares.add(caseCroix_);
            indice_++;
        }
        bidding_.add(declaresFirstRound);

        getJt().add(getMessages().getVal(DECLARING),bidding_);
        JPanel trumping_ = new JPanel(new GridLayout(0,1));
        //Panneau gestion des coupes
        JPanel sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new GridLayout(0,2));
        JLabel trumpingLabel_ = new JLabel(getMessages().getVal(TRUMPING));
        trumpingLabel_.setToolTipText(getMessages().getVal(TRUMPING_DESCRIPTION));
        sousPanneau_.add(trumpingLabel_);
        listChoiceTwo=new ComboBoxEnumCards<BeloteTrumpPartner>();
        for(BeloteTrumpPartner choix_:BeloteTrumpPartner.values()) {
            listChoiceTwo.addItem(choix_);
        }
        listChoiceTwo.setSelectedItem(getReglesBelote().getGestionCoupePartenaire());
        sousPanneau_.add(listChoiceTwo);
        underTrumpingFoe=new JCheckBox(getMessages().getVal(UNDER_TRUMPING_FOE));
        underTrumpingFoe.setSelected(getReglesBelote().getSousCoupeAdv());
        sousPanneau_.add(underTrumpingFoe);
        trumping_.add(sousPanneau_);
        getJt().add(getMessages().getVal(RULES_TRUMPS),trumping_);
        //Panneau Calcul des scores
        JPanel endOfGame_=new JPanel();
        endOfGame_.setLayout(new GridLayout(0,1));
        endOfGame_.add(new JLabel(getMessages().getVal(SCORING)));
        classic=new JCheckBox(getMessages().getVal(ALL_POINTS_FOR_DEFENDER_TEAM));
        classic.setSelected(getReglesBelote().getComptePointsClassique());
        endOfGame_.add(classic);
        getJt().add(getMessages().getVal(END_DEAL),endOfGame_);
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue();
    protected void initMessageName() {
        setMessages(getMessages(FileConst.FOLDER_MESSAGES_GUI));
    }
    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    protected void validateRules() {


//        getReglesBelote().setCartesBattues((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesBelote().setCartesBattues(listeChoix.getCurrent());
        EnumMap<BidBelote,Boolean> contrats_ = new EnumMap<BidBelote,Boolean>();
        for (BidBelote enchere_: BidBelote.values()) {
            JCheckBox jcb_= bids.get(enchere_.ordinal());
            contrats_.put(enchere_, jcb_.isSelected());
        }
        getReglesBelote().setEncheresAutorisees(contrats_);

        EnumMap<DeclaresBelote,Boolean> annonces_ = new EnumMap<DeclaresBelote,Boolean>();
        for (DeclaresBelote enchere_: indicesAnnoncesValides.getKeys()) {
            JCheckBox jcb_= declares.get(indicesAnnoncesValides.getVal(enchere_));
            annonces_.put(enchere_, jcb_.isSelected());
        }
        getReglesBelote().setAnnoncesAutorisees(annonces_);

        BeloteTrumpPartner gestionCoupe_= (BeloteTrumpPartner) listChoiceTwo.getSelectedItem();
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

    protected JSpinner getNbGames() {
        return nbGames;
    }

    protected void setNbGames(JSpinner _nbGames) {
        nbGames = _nbGames;
    }

}
