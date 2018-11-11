package cards.gui.dialogs;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import cards.consts.MixCardsChoice;
import cards.gui.MainWindow;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.gui.comboboxes.ComboBoxMixCards;
import cards.gui.dialogs.events.ListenerDealing;
import cards.gui.dialogs.events.ListenerHandful;
import cards.gui.dialogs.events.ListenerHandfulName;
import cards.gui.dialogs.events.ListenerPlayers;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.AllowedBiddingTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.EndDealTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.ModeTarot;
import code.gui.LabelButton;
import code.gui.Panel;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringMap;
import code.util.ints.Listable;

public abstract class DialogTarot extends DialogCards implements DialogVaryingPlayerNumber {

    private static final String ALLOWED_MISERES = "allowedMiseres";
    private static final String BIDS = "bids";
    private static final String DEALING = "dealing";
    private static final String DECLARING = "declaring";
    private static final String DISCARDING = "discarding";
    private static final String END_DEAL = "endDeal";
    private static final String END_DEAL_RULE = "endDealRule";
    private static final String HANDFUL = "handful";
    private static final String MIX_CARDS = "mixCards";
    private static final String MODE_GAME = "modeGame";
    private static final String NUMBER_DEALS = "numberDeals";
    private static final String NUMBER_PLAYERS = "numberPlayers";
    private static final String NUMBER_TRUMPS = "numberTrumps";
    private static final String REPARTITION = "repartition";
    private static final String REPARTITION_PLAYERS = "repartitionPlayers";
    private static final String RULES = "rules";
    private static final String VALIDATE_HANDFUL = "validateHandful";
    private RulesTarot reglesTarot=new RulesTarot();

    private JSpinner nbGames;
    private StringMap<String> messages = new StringMap<String>();
    private ComboBoxMixCards listeChoix;
    private Panel bidding;
    private CustList<JCheckBox> bids = new CustList<JCheckBox>();
    private Panel declaringMiseres;
    private CustList<JCheckBox> miseres = new CustList<JCheckBox>();
    private ComboBoxEnumCards<EndDealTarot> listeChoixTwo;
    private ComboBoxEnumCards<ModeTarot> listeChoixThree;
    private JCheckBox discardAfterCall;
    private ComboBoxEnumCards<DealingTarot> listeChoixFour;
    private ComboBoxEnumCards<Handfuls> listeChoixFive;

    private JSpinner nbAtoutsPoignee;
    private Panel players;
    private JSpinner nbJoueurs;
    private EnumMap<Handfuls,Integer> poigneesAutorisees = new EnumMap<Handfuls,Integer>();

    protected DialogTarot() {
    }
//    public DialogTarot(String _titre, MainWindow _fenetre, RulesTarot _rules) {
//        super(_titre, _fenetre, true);
//        setReglesTarot(_rules);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected void initJt(JSpinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        setNbGames(_nbGames);
        Panel dealing_=new Panel();
        dealing_.setLayout(new GridLayout(0,2));
        //Panneau Battre les cartes
        dealing_.add(new JLabel(getMessages().getVal(MIX_CARDS)));
        listeChoix=new ComboBoxMixCards();
        Listable<MixCardsChoice> mix_;
        mix_ = new EnumList<MixCardsChoice>(MixCardsChoice.values());
        EnumMap<MixCardsChoice, String> trMix_;
        trMix_ = new EnumMap<MixCardsChoice, String>();
        for (MixCardsChoice choix_: mix_) {
            trMix_.put(choix_, choix_.toString(lg_));
        }
        listeChoix.refresh(mix_, trMix_);
//        for (MixCardsChoice choix_:MixCardsChoice.values()) {
//            listeChoix.addItem(choix_);
//        }
        listeChoix.setSelectedItem(getReglesTarot().getCartesBattues());
        dealing_.add(listeChoix);
        if (getNbGames() != null) {
            dealing_.add(new JLabel(getMessages().getVal(NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }
        //Panneau Distribution
        getJt().add(getMessages().getVal(DEALING),dealing_);
        Panel declaring_=new Panel();
        declaring_.setLayout(new BoxLayout(declaring_.getComponent(), BoxLayout.PAGE_AXIS));
        declaring_.add(new JLabel(getMessages().getVal(BIDS)));
        bidding=new Panel();
        bids.clear();
        bidding.setLayout(new FlowLayout());
        for (BidTarot enchere_:BidTarot.values()) {
            JCheckBox caseCroix_=new JCheckBox(enchere_.toString(lg_));
            caseCroix_.setSelected(getReglesTarot().getContrats().getVal(enchere_));
            caseCroix_.setEnabled(
                    enchere_.getPossibiliteAnnonce()!=AllowedBiddingTarot.ALWAYS);
            bidding.add(caseCroix_);
            bids.add(caseCroix_);
        }
        declaring_.add(bidding);
        //Panneau Poignees
        Panel sousPanneau_=new Panel();
        sousPanneau_.setLayout(new GridLayout(3,2));
        sousPanneau_.add(new JLabel(getMessages().getVal(HANDFUL)));
        listeChoixFive = new ComboBoxEnumCards<Handfuls>();
        for (Handfuls p: Handfuls.getDeclarableHandFuls()) {
            listeChoixFive.addItem(p, lg_);
        }
        listeChoixFive.setListener(new ListenerHandfulName(this));
        sousPanneau_.add(listeChoixFive);
        sousPanneau_.add(new JLabel(getMessages().getVal(NUMBER_TRUMPS)));
        int nbCartesJoueur_ = getReglesTarot().getRepartition().getNombreCartesParJoueur();
        int nbTrumps_ = HandTarot.trumpsPlusExcuse().total();
        nbCartesJoueur_ = Math.min(nbCartesJoueur_, nbTrumps_);
        poigneesAutorisees = new EnumMap<Handfuls,Integer>(getReglesTarot().getPoigneesAutorisees());
        int valeur_ = poigneesAutorisees.getVal(listeChoixFive.getCurrentElement());
        nbAtoutsPoignee = new JSpinner(new SpinnerNumberModel(valeur_,0,nbCartesJoueur_,1));
        sousPanneau_.add(nbAtoutsPoignee);
        LabelButton boutonPoignees_ = new LabelButton(getMessages().getVal(VALIDATE_HANDFUL));
        boutonPoignees_.addMouseListener(new ListenerHandful(this));
        sousPanneau_.add(boutonPoignees_);
        declaring_.add(sousPanneau_);
        //Panneau Miseres
        declaringMiseres=new Panel();
        miseres.clear();
        declaringMiseres.setLayout(new FlowLayout());
        declaringMiseres.add(new JLabel(getMessages().getVal(ALLOWED_MISERES)));
        for (Miseres annonce_:Miseres.values()) {
            JCheckBox caseCroix_=new JCheckBox(annonce_.toString(lg_));
            caseCroix_.setSelected(getReglesTarot().getMiseres().containsObj(annonce_));
            declaringMiseres.add(caseCroix_);
            miseres.add(caseCroix_);
        }
        declaring_.add(declaringMiseres);
        getJt().add(getMessages().getVal(DECLARING),declaring_);

        //Panneau Chelem
        Panel bidding_ =new Panel();
        bidding_.setLayout(new GridLayout(0,3));

        //Panneau Regle du demi-point
        sousPanneau_=new Panel();
        sousPanneau_.setLayout(new GridLayout(0,1));
        JLabel endDeal_ = new JLabel(getMessages().getVal(END_DEAL));
        endDeal_.setToolTipText(getMessages().getVal(END_DEAL_RULE));
        sousPanneau_.add(endDeal_);
        listeChoixTwo=new ComboBoxEnumCards<EndDealTarot>();
        EndDealTarot curOne_ = getReglesTarot().getFinPartieTarot();
        int index_ = 0;
        int i_ = -1;
        for (EndDealTarot mode_:EndDealTarot.values()) {
            if (mode_ == curOne_) {
                i_ = index_;
            }
            listeChoixTwo.addItem(mode_, lg_);
            index_++;
        }
        if (i_>-1) {
            listeChoixTwo.selectItem(i_);
        }
        sousPanneau_.add(listeChoixTwo);
        bidding_.add(sousPanneau_);
        sousPanneau_=new Panel();
        sousPanneau_.add(new JLabel(getMessages().getVal(MODE_GAME)));
        listeChoixThree=new ComboBoxEnumCards<ModeTarot>();
        ModeTarot curTwo_ = getReglesTarot().getMode();
        index_ = 0;
        i_ = -1;
        for (ModeTarot mode_:ModeTarot.values()) {
            if (mode_ == curTwo_) {
                i_ = index_;
            }
            listeChoixThree.addItem(mode_, lg_);
            index_++;
        }
        if (i_>-1) {
            listeChoixThree.selectItem(i_);
        }
        sousPanneau_.add(listeChoixThree);
        bidding_.add(sousPanneau_);
        discardAfterCall = new JCheckBox(getMessages().getVal(DISCARDING));
        discardAfterCall.setSelected(getReglesTarot().getDiscardAfterCall());
        bidding_.add(discardAfterCall);
        getJt().add(getMessages().getVal(RULES),bidding_);
        //Panneau 4-5 joueurs
        players=new Panel();
        players.setLayout(new GridLayout(2,0));
        sousPanneau_=new Panel();
        sousPanneau_.setLayout(new GridLayout(2,0));

        sousPanneau_.add(new JLabel(getMessages().getVal(NUMBER_PLAYERS)));
        sousPanneau_.add(new JLabel(getMessages().getVal(REPARTITION_PLAYERS)));

        Numbers<Integer> nombreJoueursPossible_=new Numbers<Integer>();
        EnumList<DealingTarot> repValides_ = new EnumList<DealingTarot>(DealingTarot.getRepartitionsValides());
        int minJoueurs_=repValides_.get(0).getNombreJoueurs();
        int maxJoueurs_=repValides_.get(0).getNombreJoueurs();
        for(DealingTarot r: repValides_) {
            if(minJoueurs_>r.getNombreJoueurs()) {
                minJoueurs_=r.getNombreJoueurs();
            }
            if(maxJoueurs_<r.getNombreJoueurs()) {
                maxJoueurs_=r.getNombreJoueurs();
            }
            if (_nbPlayers != 0) {
                if (_nbPlayers != r.getNombreJoueurs()) {
                    continue;
                }
            }
            if(!nombreJoueursPossible_.containsObj(r.getNombreJoueurs())) {
                nombreJoueursPossible_.add(r.getNombreJoueurs());
            }
        }
        nombreJoueursPossible_.sort();
        SpinnerListModel spin_ = new SpinnerListModel(nombreJoueursPossible_.toArray());
        if (_nbPlayers != 0) {
            spin_.setValue(_nbPlayers);
        } else {
            spin_.setValue(getReglesTarot().getRepartition().getNombreJoueurs());
        }
        nbJoueurs=new JSpinner(spin_);
        if (_enabledChangingNbPlayers) {
            nbJoueurs.addChangeListener(new ListenerPlayers(this, _window));
        } else {
            nbJoueurs.setEnabled(false);
        }
        sousPanneau_.add(nbJoueurs);
        valeur_=(Integer) nbJoueurs.getValue();
        listeChoixFour=new ComboBoxEnumCards<DealingTarot>();
        DealingTarot curThree_ = getReglesTarot().getRepartition();
        index_ = 0;
        i_ = -1;
        for(DealingTarot r:DealingTarot.values()) {
            if(r.getNombreJoueurs()!=valeur_) {
                continue;
            }
            if (r == curThree_) {
                i_ = index_;
            }
            listeChoixFour.addItem(r, lg_);
            index_++;
        }
        if (i_ > -1) {
            listeChoixFour.selectItem(i_);
        }
        listeChoixFour.setListener(new ListenerDealing(this));
        sousPanneau_.add(listeChoixFour);
        players.add(sousPanneau_);
        getJt().add(getMessages().getVal(REPARTITION),players);
    }

    protected void initMessageName(MainWindow _parent) {
        setMessages(getMessages(_parent,FileConst.FOLDER_MESSAGES_GUI));
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, MainWindow _window);

    public void validateHandful() {
        int valeur_ = poigneesAutorisees.getVal(listeChoixFive.getCurrentElement());
        SpinnerNumberModel modele_ = (SpinnerNumberModel) nbAtoutsPoignee.getModel();
        modele_.setValue(valeur_);
    }

    @Override
    public void validateNbPlayers(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        int nombreJoueursSel_=(Integer) nbJoueurs.getValue();
        listeChoixFour.removeAllItems();
        EnumList<DealingTarot> repartitions_ = new EnumList<DealingTarot>();
        for(DealingTarot r:DealingTarot.getRepartitionsValides()) {
            if(r.getNombreJoueurs()!=nombreJoueursSel_) {
                continue;
            }
            repartitions_.add(r);
        }
        DealingTarot curThree_ = repartitions_.first();
        int index_ = 0;
        int i_ = -1;
        for(DealingTarot r:DealingTarot.getRepartitionsValides()) {
            if(r.getNombreJoueurs()!=nombreJoueursSel_) {
                continue;
            }
            if (r == curThree_) {
                i_ = index_;
            }
            listeChoixFour.addItem(r,lg_);
            index_++;
        }
        if (i_ > -1) {
            listeChoixFour.selectItem(i_);
        }
        int nbCartesJoueur_ = curThree_.getNombreCartesParJoueur();
        poigneesAutorisees.clear();
        for(Handfuls p: Handfuls.values()) {
            poigneesAutorisees.put(p, Handfuls.getConfigurationParDefautAnnoncePoignee(p).getVal(nbCartesJoueur_));
        }
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        SpinnerNumberModel modele_ = (SpinnerNumberModel) nbAtoutsPoignee.getModel();
        modele_.setMaximum(nbCartesJoueur_);
        modele_.setValue(Handfuls.getConfigurationParDefautAnnoncePoignee(poignee_).getVal(nbCartesJoueur_));
    }

    public void validateDealingRules() {
        DealingTarot repartition_ = listeChoixFour.getCurrentElement();
        if (repartition_ == null) {
            return;
        }
        int nbCartesJoueur_ = repartition_.getNombreCartesParJoueur();
        poigneesAutorisees.clear();
        for(Handfuls p: Handfuls.values()) {
            poigneesAutorisees.put(p, Handfuls.getConfigurationParDefautAnnoncePoignee(p).getVal(nbCartesJoueur_));
        }
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        SpinnerNumberModel modele_ = (SpinnerNumberModel) nbAtoutsPoignee.getModel();
        modele_.setMaximum(nbCartesJoueur_);
        modele_.setValue(Handfuls.getConfigurationParDefautAnnoncePoignee(poignee_).getVal(nbCartesJoueur_));
    }

    public void validateHandfulTrumps() {
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        SpinnerNumberModel modele_ = (SpinnerNumberModel) nbAtoutsPoignee.getModel();
        int valeur_ = (Integer) modele_.getValue();
        poigneesAutorisees.put(poignee_, valeur_);
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    protected void validateRules() {
//        getReglesTarot().setCartesBattues((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesTarot().setCartesBattues(listeChoix.getCurrent());
        getReglesTarot().setPoigneesAutorisees(poigneesAutorisees);
        EnumList<Miseres> miseres_=new EnumList<Miseres>();
        for (Miseres misere_: Miseres.values()) {
            JCheckBox jcb_= miseres.get(misere_.ordinal());
            if(jcb_.isSelected()) {
                miseres_.add(misere_);
            }
        }
        getReglesTarot().setMiseres(miseres_);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot enchere_: BidTarot.values()) {
            JCheckBox jcb_= bids.get(enchere_.ordinal());
            contrats_.put(enchere_, jcb_.isSelected());
        }
        getReglesTarot().setContrats(contrats_);
        getReglesTarot().setFinPartieTarot(listeChoixTwo.getCurrentElement());
        getReglesTarot().setMode(listeChoixThree.getCurrentElement());
        getReglesTarot().setDiscardAfterCall(discardAfterCall.isSelected());
        getReglesTarot().setRepartition(listeChoixFour.getCurrentElement());


    }

    protected StringMap<String> getMessages() {
        return messages;
    }

    protected void setMessages(StringMap<String> _messages) {
        messages = _messages;
    }

    protected RulesTarot getReglesTarot() {
        return reglesTarot;
    }

    protected void setReglesTarot(RulesTarot _reglesTarot) {
        reglesTarot = _reglesTarot;
    }

    protected JSpinner getNbGames() {
        return nbGames;
    }

    protected void setNbGames(JSpinner _nbGames) {
        nbGames = _nbGames;
    }

}
