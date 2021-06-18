package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.comboboxes.ComboBoxEnumCards;
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
import code.gui.*;
import code.util.*;
import code.util.ints.Listable;

public abstract class DialogTarot extends DialogCards implements DialogVaryingPlayerNumber {

    private static final String ALLOWED_MISERES = "allowedMiseres";
    private static final String CST_BIDS = "bids";
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

    private Spinner nbGames;
    private StringMap<String> messages = new StringMap<String>();
    private ComboBox<MixCardsChoice> listeChoix;
    private Panel bidding;
    private final CustList<CustCheckBox> bids = new CustList<CustCheckBox>();
    private Panel declaringMiseres;
    private final CustList<CustCheckBox> miseres = new CustList<CustCheckBox>();
    private ComboBoxEnumCards<EndDealTarot> listeChoixTwo;
    private ComboBoxEnumCards<ModeTarot> listeChoixThree;
    private CustCheckBox discardAfterCall;
    private ComboBoxEnumCards<DealingTarot> listeChoixFour;
    private ComboBoxEnumCards<Handfuls> listeChoixFive;

    private Spinner nbAtoutsPoignee;
    private Panel players;
    private Spinner nbJoueurs;
    private EnumMap<Handfuls,Integer> poigneesAutorisees = new EnumMap<Handfuls,Integer>();

    protected DialogTarot() {
    }
//    public DialogTarot(String _titre, MainWindow _fenetre, RulesTarot _rules) {
//        super(_titre, _fenetre, true);
//        setReglesTarot(_rules);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected void initJt(Spinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        setNbGames(_nbGames);
        Panel dealing_=Panel.newGrid(0,2);
        //Panneau Battre les cartes
        dealing_.add(new TextLabel(getMessages().getVal(MIX_CARDS)));
        listeChoix=new ComboBox<MixCardsChoice>(_window.getFrames().getGeneComboBox().createCombo(new StringList(), -1));
        Listable<MixCardsChoice> mix_;
        mix_ = new EnumList<MixCardsChoice>(MixCardsChoice.values());
        EnumMap<MixCardsChoice, String> trMix_;
        trMix_ = new EnumMap<MixCardsChoice, String>();
        for (MixCardsChoice choix_: mix_) {
            trMix_.put(choix_, Games.toString(choix_,lg_));
        }
        listeChoix.refresh(mix_, trMix_);
//        for (MixCardsChoice choix_:MixCardsChoice.values()) {
//            listeChoix.addItem(choix_);
//        }
        listeChoix.setSelectedItem(getReglesTarot().getCartesBattues());
        dealing_.add(listeChoix.getCombo().self());
        if (getNbGames() != null) {
            dealing_.add(new TextLabel(getMessages().getVal(NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }
        //Panneau Distribution
        getJt().add(getMessages().getVal(DEALING),dealing_);
        Panel declaring_=Panel.newPageBox();
        declaring_.add(new TextLabel(getMessages().getVal(CST_BIDS)));
        bidding=Panel.newLineBox();
        bids.clear();
        for (BidTarot enchere_:BidTarot.values()) {
            CustCheckBox caseCroix_=new CustCheckBox(Games.toString(enchere_,lg_));
            caseCroix_.setSelected(getReglesTarot().getContrats().getVal(enchere_));
            caseCroix_.setEnabled(
                    enchere_.getPossibiliteAnnonce()!=AllowedBiddingTarot.ALWAYS);
            bidding.add(caseCroix_);
            bids.add(caseCroix_);
        }
        declaring_.add(bidding);
        //Panneau Poignees
        Panel sousPanneau_=Panel.newGrid(3,2);
        sousPanneau_.add(new TextLabel(getMessages().getVal(HANDFUL)));
        listeChoixFive = new ComboBoxEnumCards<Handfuls>(_window.getFrames().getGeneComboBox().createCombo(new StringList(new IntTreeMap<String>().values()), 0));
        for (Handfuls p: Handfuls.getDeclarableHandFuls()) {
            listeChoixFive.addItem(p, Games.toString(p,lg_));
        }
        listeChoixFive.getCombo().setListener(new ListenerHandfulName(this));
        sousPanneau_.add(listeChoixFive.getCombo().self());
        sousPanneau_.add(new TextLabel(getMessages().getVal(NUMBER_TRUMPS)));
        int nbCartesJoueur_ = getReglesTarot().getRepartition().getNombreCartesParJoueur();
        int nbTrumps_ = HandTarot.trumpsPlusExcuse().total();
        nbCartesJoueur_ = Math.min(nbCartesJoueur_, nbTrumps_);
        poigneesAutorisees = new EnumMap<Handfuls,Integer>(getReglesTarot().getPoigneesAutorisees());
        int valeur_ = poigneesAutorisees.getVal(listeChoixFive.getCurrentElement());
        nbAtoutsPoignee = new Spinner(valeur_,0,nbCartesJoueur_,1);
        sousPanneau_.add(nbAtoutsPoignee);
        LabelButton boutonPoignees_ = new LabelButton(getMessages().getVal(VALIDATE_HANDFUL));
        boutonPoignees_.addMouseListener(new ListenerHandful(this));
        sousPanneau_.add(boutonPoignees_);
        declaring_.add(sousPanneau_);
        //Panneau Miseres
        declaringMiseres=Panel.newLineBox();
        miseres.clear();
        declaringMiseres.add(new TextLabel(getMessages().getVal(ALLOWED_MISERES)));
        for (Miseres annonce_:Miseres.values()) {
            CustCheckBox caseCroix_=new CustCheckBox(Games.toString(annonce_,lg_));
            caseCroix_.setSelected(getReglesTarot().getMiseres().containsObj(annonce_));
            declaringMiseres.add(caseCroix_);
            miseres.add(caseCroix_);
        }
        declaring_.add(declaringMiseres);
        getJt().add(getMessages().getVal(DECLARING),declaring_);

        //Panneau Chelem
        Panel bidding_ =Panel.newGrid(0,3);

        //Panneau Regle du demi-point
        sousPanneau_=Panel.newGrid(0,1);
        TextLabel endDeal_ = new TextLabel(getMessages().getVal(END_DEAL));
        endDeal_.setToolTipText(getMessages().getVal(END_DEAL_RULE));
        sousPanneau_.add(endDeal_);
        listeChoixTwo=new ComboBoxEnumCards<EndDealTarot>(_window.getFrames().getGeneComboBox().createCombo(new StringList(new IntTreeMap<String>().values()), 0));
        EndDealTarot curOne_ = getReglesTarot().getEndDealTarot();
        int index_ = 0;
        int i_ = -1;
        for (EndDealTarot mode_:EndDealTarot.values()) {
            if (mode_ == curOne_) {
                i_ = index_;
            }
            listeChoixTwo.addItem(mode_, Games.toString(mode_,lg_));
            index_++;
        }
        if (i_>-1) {
            listeChoixTwo.getCombo().selectItem(i_);
        }
        sousPanneau_.add(listeChoixTwo.getCombo().self());
        bidding_.add(sousPanneau_);
        sousPanneau_=Panel.newLineBox();
        sousPanneau_.add(new TextLabel(getMessages().getVal(MODE_GAME)));
        listeChoixThree=new ComboBoxEnumCards<ModeTarot>(_window.getFrames().getGeneComboBox().createCombo(new StringList(new IntTreeMap<String>().values()), 0));
        ModeTarot curTwo_ = getReglesTarot().getMode();
        index_ = 0;
        i_ = -1;
        for (ModeTarot mode_:ModeTarot.values()) {
            if (mode_ == curTwo_) {
                i_ = index_;
            }
            listeChoixThree.addItem(mode_, Games.toString(mode_,lg_));
            index_++;
        }
        if (i_>-1) {
            listeChoixThree.getCombo().selectItem(i_);
        }
        sousPanneau_.add(listeChoixThree.getCombo().self());
        bidding_.add(sousPanneau_);
        discardAfterCall = new CustCheckBox(getMessages().getVal(DISCARDING));
        discardAfterCall.setSelected(getReglesTarot().getDiscardAfterCall());
        bidding_.add(discardAfterCall);
        getJt().add(getMessages().getVal(RULES),bidding_);
        //Panneau 4-5 joueurs
        players=Panel.newGrid(2,0);
        sousPanneau_=Panel.newGrid(2,0);

        sousPanneau_.add(new TextLabel(getMessages().getVal(NUMBER_PLAYERS)));
        sousPanneau_.add(new TextLabel(getMessages().getVal(REPARTITION_PLAYERS)));

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
        }
        int value_;
        if (_nbPlayers != 0) {
            value_ = _nbPlayers;
        } else {
            value_ = getReglesTarot().getRepartition().getNombreJoueurs();
        }
        nbJoueurs=new Spinner(value_,minJoueurs_,maxJoueurs_,1);
        if (_enabledChangingNbPlayers) {
            nbJoueurs.addChangeListener(new ListenerPlayers(this, _window));
        } else {
            nbJoueurs.setEnabled(false);
        }
        sousPanneau_.add(nbJoueurs);
        valeur_= nbJoueurs.getValue();
        listeChoixFour=new ComboBoxEnumCards<DealingTarot>(_window.getFrames().getGeneComboBox().createCombo(new StringList(new IntTreeMap<String>().values()), 0));
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
            listeChoixFour.addItem(r, Games.toString(r,lg_));
            index_++;
        }
        if (i_ > -1) {
            listeChoixFour.getCombo().selectItem(i_);
        }
        listeChoixFour.getCombo().setListener(new ListenerDealing(this));
        sousPanneau_.add(listeChoixFour.getCombo().self());
        players.add(sousPanneau_);
        getJt().add(getMessages().getVal(REPARTITION),players);
    }

    protected void initMessageName(MainWindow _parent) {
        setMessages(MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), getAccessFile()));
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, MainWindow _window);

    public void validateHandful() {
        int valeur_ = poigneesAutorisees.getVal(listeChoixFive.getCurrentElement());
        nbAtoutsPoignee.setValue(valeur_);
    }

    @Override
    public void validateNbPlayers(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        int nombreJoueursSel_= nbJoueurs.getValue();
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
            listeChoixFour.addItem(r,Games.toString(r, lg_));
            index_++;
        }
        if (i_ > -1) {
            listeChoixFour.getCombo().selectItem(i_);
        }
        int nbCartesJoueur_ = curThree_.getNombreCartesParJoueur();
        poigneesAutorisees.clear();
        for(Handfuls p: Handfuls.values()) {
            poigneesAutorisees.put(p, Handfuls.getConfigurationParDefautAnnoncePoignee(p).getVal(nbCartesJoueur_));
        }
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        int min_ = nbAtoutsPoignee.getMin();
        nbAtoutsPoignee.setRangeValue(Handfuls.getConfigurationParDefautAnnoncePoignee(poignee_).getVal(nbCartesJoueur_),min_,nbCartesJoueur_);
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
        int min_ = nbAtoutsPoignee.getMin();
        nbAtoutsPoignee.setRangeValue(Handfuls.getConfigurationParDefautAnnoncePoignee(poignee_).getVal(nbCartesJoueur_),min_,nbCartesJoueur_);
    }

    public void validateHandfulTrumps() {
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        int valeur_ = nbAtoutsPoignee.getValue();
        poigneesAutorisees.put(poignee_, valeur_);
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    protected void validateRules() {
//        getReglesTarot().setCartesBattues((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesTarot().setCartesBattues(listeChoix.getCurrent());
        getReglesTarot().setPoigneesAutorisees(poigneesAutorisees);
        EnumList<Miseres> miseres_=new EnumList<Miseres>();
        for (Miseres misere_: Miseres.values()) {
            CustCheckBox jcb_= miseres.get(misere_.ordinal());
            if(jcb_.isSelected()) {
                miseres_.add(misere_);
            }
        }
        getReglesTarot().setMiseres(miseres_);
        EnumMap<BidTarot,Boolean> contrats_ = new EnumMap<BidTarot,Boolean>();
        for (BidTarot enchere_: BidTarot.values()) {
            CustCheckBox jcb_= bids.get(enchere_.ordinal());
            contrats_.put(enchere_, jcb_.isSelected());
        }
        getReglesTarot().setContrats(contrats_);
        getReglesTarot().setEndDealTarot(listeChoixTwo.getCurrentElement());
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

    protected Spinner getNbGames() {
        return nbGames;
    }

    protected void setNbGames(Spinner _nbGames) {
        nbGames = _nbGames;
    }

}
