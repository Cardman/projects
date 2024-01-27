package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.gui.dialogs.events.*;
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
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogTarot;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;

public abstract class DialogTarot extends DialogCards implements DialogVaryingPlayerNumber {
    private RulesTarot reglesTarot=new RulesTarot();

    private AbsSpinner nbGames;
    private ComboBox<MixCardsChoice> listeChoix;
    private final IdMap<BidTarot,AbsCustCheckBox> bids = new IdMap<BidTarot,AbsCustCheckBox>();
    private final IdMap<Miseres,AbsCustCheckBox> miseres = new IdMap<Miseres,AbsCustCheckBox>();
    private ComboBoxEnumCards<EndDealTarot> listeChoixTwo;
    private ComboBoxEnumCards<ModeTarot> listeChoixThree;
    private AbsCustCheckBox discardAfterCall;
    private AbsCustCheckBox allowPlayCalledSuit;
    private ComboBoxEnumCards<DealingTarot> listeChoixFour;
    private ComboBoxEnumCards<Handfuls> listeChoixFive;

    private AbsSpinner nbAtoutsPoignee;
    private AbsButton boutonPoignees;
    private AbsSpinner nbJoueurs;
    private IdMap<Handfuls,Integer> poigneesAutorisees = new IdMap<Handfuls,Integer>();

    protected DialogTarot(AbstractProgramInfos _frameFactory, ClosingEditorCards _ch) {
        super(_frameFactory, _ch);
    }
//    public DialogTarot(String _titre, MainWindow _fenetre, RulesTarot _rules) {
//        super(_titre, _fenetre, true);
//        setReglesTarot(_rules);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected void initJt(AbsSpinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window, AbsTabbedPane _jt) {
        setMain(_window);
        TranslationsLg lg_ = getFrames().currentLg();
        setNbGames(_nbGames);
        AbsPanel dealing_=_window.getCompoFactory().newGrid(0,2);
        //Panneau Battre les cartes
        dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.MIX_CARDS)));
        listeChoix=build(_window,getReglesTarot().getCommon().getMixedCards());
        dealing_.add(listeChoix.self());
        if (getNbGames() != null) {
            dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }
        //Panneau Distribution
        _jt.add(translate(MessagesDialogTarot.DEALING),dealing_);
        AbsPanel declaring_=_window.getCompoFactory().newPageBox();
        declaring_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.CST_BIDS)));
        AbsPanel biddingPanel_ = _window.getCompoFactory().newLineBox();
        bids.clear();
        for (BidTarot enchere_:BidTarot.getValidBids()) {
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,lg_));
            caseCroix_.setSelected(getReglesTarot().getAllowedBids().getVal(enchere_) == BoolVal.TRUE);
            caseCroix_.setEnabled(
                    enchere_.getPossibiliteAnnonce()!=AllowedBiddingTarot.ALWAYS);
            biddingPanel_.add(caseCroix_);
            bids.addEntry(enchere_,caseCroix_);
        }
        declaring_.add(biddingPanel_);
        //Panneau Poignees
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid(3,2);
        sousPanneau_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.HANDFUL)));
        getReglesTarot().reorgHandfules();
        listeChoixFive = new ComboBoxEnumCards<Handfuls>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        for (Handfuls p: Handfuls.getDeclarableHandFuls()) {
            listeChoixFive.addItem(p, Games.toString(p,lg_));
        }
        listeChoixFive.getCombo().repaint();
        listeChoixFive.setListener(new ListenerHandfulName(this));
        sousPanneau_.add(listeChoixFive.self());
        sousPanneau_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.NUMBER_TRUMPS)));
        int nbCartesJoueur_ = getReglesTarot().getDealing().getNombreCartesParJoueur();
        int nbTrumps_ = HandTarot.trumpsPlusExcuse().total();
        nbCartesJoueur_ = NumberUtil.min(nbCartesJoueur_, nbTrumps_);
        poigneesAutorisees = new IdMap<Handfuls,Integer>(getReglesTarot().getAllowedHandfuls());
        int valeur_ = poigneesAutorisees.getVal(listeChoixFive.getCurrentElement());
        nbAtoutsPoignee = getCompoFactory().newSpinner(valeur_,0,nbCartesJoueur_,1);
        sousPanneau_.add(nbAtoutsPoignee);
        boutonPoignees = getCompoFactory().newPlainButton(translate(MessagesDialogTarot.VALIDATE_HANDFUL));
        boutonPoignees.addActionListener(new ListenerHandful(this));
        sousPanneau_.add(boutonPoignees);
        declaring_.add(sousPanneau_);
        //Panneau Miseres
        AbsPanel declaringMiseres_ = _window.getCompoFactory().newLineBox();
        miseres.clear();
        declaringMiseres_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.ALLOWED_MISERES)));
        for (Miseres annonce_:allMiseres()) {
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(annonce_,lg_));
            caseCroix_.setSelected(getReglesTarot().getMiseres().containsObj(annonce_));
            declaringMiseres_.add(caseCroix_);
            miseres.addEntry(annonce_,caseCroix_);
        }
        declaring_.add(declaringMiseres_);
        _jt.add(translate(MessagesDialogTarot.DECLARING),declaring_);

        //Panneau Chelem
        AbsPanel bidding_ =_window.getCompoFactory().newGrid(0,3);

        //Panneau Regle du demi-point
        sousPanneau_=_window.getCompoFactory().newGrid(0,1);
        AbsPlainLabel endDeal_ = getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.END_DEAL));
        endDeal_.setToolTipText(translate(MessagesDialogTarot.END_DEAL_RULE));
        sousPanneau_.add(endDeal_);
        listeChoixTwo=new ComboBoxEnumCards<EndDealTarot>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        EndDealTarot curOne_ = getReglesTarot().getEndDealTarot();
        int index_ = 0;
        for (EndDealTarot mode_:allEndDealTarot()) {
            listeChoixTwo.addItem(mode_, Games.toString(mode_,lg_));
            if (mode_ == curOne_) {
                listeChoixTwo.selectItem(index_);
            }
            index_++;
        }
        listeChoixTwo.getCombo().repaint();
        sousPanneau_.add(listeChoixTwo.self());
        bidding_.add(sousPanneau_);
        sousPanneau_=_window.getCompoFactory().newLineBox();
        sousPanneau_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.MODE_GAME)));
        listeChoixThree=new ComboBoxEnumCards<ModeTarot>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        ModeTarot curTwo_ = getReglesTarot().getMode();
        index_ = 0;
        for (ModeTarot mode_:allModeTarot()) {
            listeChoixThree.addItem(mode_, Games.toString(mode_,lg_));
            if (mode_ == curTwo_) {
                listeChoixThree.selectItem(index_);
            }
            index_++;
        }
        listeChoixThree.getCombo().repaint();
        sousPanneau_.add(listeChoixThree.self());
        bidding_.add(sousPanneau_);
        discardAfterCall = getCompoFactory().newCustCheckBox(translate(MessagesDialogTarot.DISCARDING));
        discardAfterCall.setSelected(getReglesTarot().getDiscardAfterCall());
        bidding_.add(discardAfterCall);
        allowPlayCalledSuit = getCompoFactory().newCustCheckBox(translate(MessagesDialogTarot.ALLOW_PLAYING_CALLED_SUIT));
        allowPlayCalledSuit.setSelected(getReglesTarot().isAllowPlayCalledSuit());
        bidding_.add(allowPlayCalledSuit);
        _jt.add(translate(MessagesDialogTarot.RULES),bidding_);
        //Panneau 4-5 joueurs
        AbsPanel players_ = _window.getCompoFactory().newGrid(2, 0);
        sousPanneau_=_window.getCompoFactory().newGrid(2,0);

        sousPanneau_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.NUMBER_PLAYERS)));
        sousPanneau_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogTarot.REPARTITION_PLAYERS)));

        IdList<DealingTarot> repValides_ = new IdList<DealingTarot>(DealingTarot.getRepartitionsValides());
        int minJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
        int maxJoueurs_= repValides_.get(0).getId().getNombreJoueurs();
        for(DealingTarot r: repValides_) {
            minJoueurs_= NumberUtil.min(minJoueurs_,r.getId().getNombreJoueurs());
            maxJoueurs_= NumberUtil.max(maxJoueurs_,r.getId().getNombreJoueurs());
        }
        int value_;
        if (_nbPlayers != 0) {
            value_ = _nbPlayers;
        } else {
            value_ = getReglesTarot().getDealing().getId().getNombreJoueurs();
        }
        nbJoueurs=getCompoFactory().newSpinner(value_,minJoueurs_,maxJoueurs_,1);
        if (_enabledChangingNbPlayers) {
            nbJoueurs.addChangeListener(new ListenerPlayers(this, _window));
        } else {
            nbJoueurs.setEnabled(false);
        }
        sousPanneau_.add(nbJoueurs);
        dealing(_window, lg_);
        sousPanneau_.add(listeChoixFour.self());
        players_.add(sousPanneau_);
        _jt.add(translate(MessagesDialogTarot.REPARTITION), players_);
    }

    private void dealing(WindowCardsInt _window, TranslationsLg _lg) {
        int index_;
        int valeur_;
        valeur_= nbJoueurs.getValue();
        listeChoixFour=new ComboBoxEnumCards<DealingTarot>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        DealingTarot curThree_ = getReglesTarot().getDealing();
        index_ = 0;
        for(DealingTarot r:DealingTarot.getRepartitionsValides()) {
            if(r.getId().getNombreJoueurs() !=valeur_) {
                continue;
            }
            listeChoixFour.addItem(r, Games.toString(r, _lg));
            if (r == curThree_) {
                listeChoixFour.selectItem(index_);
            }
            index_++;
        }
        listeChoixFour.getCombo().repaint();
        listeChoixFour.setListener(new ListenerDealing(this));
    }

    public ComboBox<MixCardsChoice> getListeChoix() {
        return listeChoix;
    }

    public ComboBoxEnumCards<EndDealTarot> getListeChoixTwo() {
        return listeChoixTwo;
    }

    public ComboBoxEnumCards<ModeTarot> getListeChoixThree() {
        return listeChoixThree;
    }

    public ComboBoxEnumCards<DealingTarot> getListeChoixFour() {
        return listeChoixFour;
    }

    public ComboBoxEnumCards<Handfuls> getListeChoixFive() {
        return listeChoixFive;
    }

    public AbsSpinner getNbJoueurs() {
        return nbJoueurs;
    }

    public AbsCustCheckBox getAllowPlayCalledSuit() {
        return allowPlayCalledSuit;
    }

    public AbsCustCheckBox getDiscardAfterCall() {
        return discardAfterCall;
    }

    public AbsSpinner getNbAtoutsPoignee() {
        return nbAtoutsPoignee;
    }

    public IdMap<BidTarot,AbsCustCheckBox> getBids() {
        return bids;
    }

    public IdMap<Miseres,AbsCustCheckBox> getMiseres() {
        return miseres;
    }

    public AbsButton getBoutonPoignees() {
        return boutonPoignees;
    }

    public String translate(String _k) {
        return translates().getVal(_k);
    }
    public StringMap<String> translates() {
        return Games.getDialogTarotTr(Games.getAppliTr(getFrames().currentLg())).getMapping();
    }
    public static EndDealTarot[] allEndDealTarot() {
        return new EndDealTarot[]{EndDealTarot.ATTACK_LOOSE,EndDealTarot.ATTACK_WIN,EndDealTarot.ZERO};
    }
    public static ModeTarot[] allModeTarot() {
        return new ModeTarot[]{ModeTarot.NORMAL,ModeTarot.NORMAL_WITH_MISERE,ModeTarot.NORMAL_WITH_ONE_FOR_ONE,ModeTarot.MISERE,ModeTarot.ONE_FOR_ONE};
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window);

    public void validateHandful() {
        int valeur_ = poigneesAutorisees.getVal(listeChoixFive.getCurrentElement());
        nbAtoutsPoignee.setValue(valeur_);
    }

    @Override
    public void validateNbPlayers(WindowCardsInt _window) {
        TranslationsLg lg_ = getFrames().currentLg();
        int nombreJoueursSel_= nbJoueurs.getValue();
        listeChoixFour.getReal().clear();
        listeChoixFour.removeAllItems();
        IdList<DealingTarot> repartitions_ = new IdList<DealingTarot>();
        for(DealingTarot r:DealingTarot.getRepartitionsValides()) {
            if(r.getId().getNombreJoueurs() !=nombreJoueursSel_) {
                continue;
            }
            repartitions_.add(r);
        }
        DealingTarot curThree_ = repartitions_.first();
        int index_ = 0;
        for(DealingTarot r:DealingTarot.getRepartitionsValides()) {
            if(r.getId().getNombreJoueurs() !=nombreJoueursSel_) {
                continue;
            }
            listeChoixFour.addItem(r,Games.toString(r, lg_));
            if (r == curThree_) {
                listeChoixFour.selectItem(index_);
            }
            index_++;
        }
        listeChoixFour.getCombo().repaint();
        handfuls(curThree_);
    }

    private void handfuls(DealingTarot _deal) {
        int nbCartesJoueur_ = _deal.getNombreCartesParJoueur();
        int max_ = NumberUtil.min(HandTarot.trumpsPlusExcuse().total(), nbCartesJoueur_);
        poigneesAutorisees.clear();
        for(Handfuls p: Handfuls.getDeclarableHandFuls()) {
            poigneesAutorisees.addEntry(p, Handfuls.getConfigurationParDefautAnnoncePoignee(p).getVal(nbCartesJoueur_));
        }
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        int min_ = nbAtoutsPoignee.getMin();
        nbAtoutsPoignee.setRangeValue(Handfuls.getConfigurationParDefautAnnoncePoignee(poignee_).getVal(nbCartesJoueur_),min_,max_);
    }

    public void validateDealingRules() {
        DealingTarot repartition_ = listeChoixFour.getCurrentElement();
//        if (repartition_ == null) {
//            return;
//        }
        handfuls(repartition_);
    }

    public void validateHandfulTrumps() {
        Handfuls poignee_ = listeChoixFive.getCurrentElement();
        int valeur_ = nbAtoutsPoignee.getValue();
        poigneesAutorisees.put(poignee_, valeur_);
        validatingButton().setEnabled(RulesTarot.sortedHandfuls(poigneesAutorisees));
    }

    protected abstract AbsButton validatingButton();

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    public void validateRules() {
//        getReglesTarot().setCartesBattues((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesTarot().getCommon().setMixedCards(listeChoix.getCurrent());
        getReglesTarot().setAllowedHandfuls(poigneesAutorisees);
        IdList<Miseres> miseres_=new IdList<Miseres>();
        int mc_ = miseres.size();
        for (int i = 0; i < mc_; i++) {
            AbsCustCheckBox jcb_= this.miseres.getValue(i);
            if(jcb_.isSelected()) {
                miseres_.add(this.miseres.getKey(i));
            }
        }
        getReglesTarot().setMiseres(miseres_);
        IdMap<BidTarot,BoolVal> contrats_ = new IdMap<BidTarot,BoolVal>();
        int bc_ = bids.size();
        for (int i = 0; i < bc_; i++) {
            AbsCustCheckBox jcb_= bids.getValue(i);
            contrats_.put(bids.getKey(i), ComparatorBoolean.of(jcb_.isSelected()));
        }
        getReglesTarot().setAllowedBids(contrats_);
        getReglesTarot().setEndDealTarot(listeChoixTwo.getCurrentElement());
        getReglesTarot().setMode(listeChoixThree.getCurrentElement());
        getReglesTarot().setDiscardAfterCall(discardAfterCall.isSelected());
        getReglesTarot().setAllowPlayCalledSuit(allowPlayCalledSuit.isSelected());
        getReglesTarot().setDealing(listeChoixFour.getCurrentElement());


    }
    public static Miseres[] allMiseres() {
        return new Miseres[]{Miseres.TRUMP,Miseres.POINT,Miseres.CHARACTER,Miseres.SUIT,Miseres.LOW_CARDS};
    }

    public RulesTarot getReglesTarot() {
        return reglesTarot;
    }

    protected void setReglesTarot(RulesTarot _reglesTarot) {
        reglesTarot = _reglesTarot;
    }

    public AbsSpinner getNbGames() {
        return nbGames;
    }

    protected void setNbGames(AbsSpinner _nbGames) {
        nbGames = _nbGames;
    }

}
