package cards.gui.dialogs;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.gui.dialogs.events.ClosingEditorCards;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogBelote;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public abstract class DialogBelote extends DialogCards {

    private RulesBelote reglesBelote=new RulesBelote();
    private AbsSpinner nbGames;
    private final IdMap<DeclaresBelote,Integer> indicesAnnoncesValides = new IdMap<DeclaresBelote,Integer>();
    private ComboBox<MixCardsChoice> listeChoix;
    private AbsCustCheckBox dealAll;

    private final IdMap<BidBelote,AbsCustCheckBox> bids = new IdMap<BidBelote,AbsCustCheckBox>();
    private final IdMap<DeclaresBelote,AbsCustCheckBox> declares = new IdMap<DeclaresBelote,AbsCustCheckBox>();
    private AbsCustCheckBox underTrumpingFoe;
    private ComboBoxEnumCards<BeloteTrumpPartner> listChoiceTwo;
    private AbsCustCheckBox classic;

    protected DialogBelote(AbstractProgramInfos _frameFactory, ClosingEditorCards _ch) {
        super(_frameFactory, _ch);
    }
//    public DialogBelote(String _titre, MainWindow _fenetre, RulesBelote _rulesBelote) {
//        super(_titre, _fenetre, true);
//        setReglesBelote(_rulesBelote);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected void initJt(WindowCardsInt _window, AbsSpinner _nbGames, AbsTabbedPane _jt) {
        setNbGames(_nbGames);
        setMain(_window);
        String lg_ = _window.getLanguageKey();
        AbsPanel dealing_=_window.getCompoFactory().newGrid(0,2);
        //Sous - panneau Battre les cartes
        dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.MIX_CARDS)));
        listeChoix=build(_window,getReglesBelote().getCommon().getMixedCards());
        dealing_.add(listeChoix.self());
        dealAll = getCompoFactory().newCustCheckBox(translate(MessagesDialogBelote.DEALING_MODE));
        dealAll.setSelected(getReglesBelote().dealAll());
        dealing_.add(dealAll);
        dealing_.add(getCompoFactory().newPlainLabel(""));
        if (getNbGames() != null) {
            dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }

        //Panneau Distribution
        _jt.add(translate(MessagesDialogBelote.DEALING),dealing_);
        AbsPanel bidding_=_window.getCompoFactory().newPageBox();
        //Panneau Annonces autorisees
        bidding_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.CST_BIDS)));
        bids.clear();
        AbsPanel bids_ = _window.getCompoFactory().newGrid(1, 0);
        for (BidBelote enchere_:BidBelote.all()) {
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,lg_));
            caseCroix_.setSelected(getReglesBelote().getAllowedBids().getVal(enchere_) == BoolVal.TRUE);
            caseCroix_.setEnabled(!enchere_.getToujoursPossibleAnnoncer());
            bids_.add(caseCroix_);
            bids.addEntry(enchere_,caseCroix_);
        }


        bidding_.add(bids_);

        bidding_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.ALLOWED_DECLARING)));
        AbsPanel declaresFirstRound_ = _window.getCompoFactory().newGrid(0, 3);
        declares.clear();
        int indice_ = 0;
        for (DeclaresBelote enchere_:DeclaresBelote.annoncesValides()) {
            indicesAnnoncesValides.put(enchere_, indice_);
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,lg_));
            caseCroix_.setSelected(getReglesBelote().getAllowedDeclares().getVal(enchere_) == BoolVal.TRUE);
            declaresFirstRound_.add(caseCroix_);
            declares.addEntry(enchere_,caseCroix_);
            indice_++;
        }
        bidding_.add(declaresFirstRound_);

        _jt.add(translate(MessagesDialogBelote.DECLARING),bidding_);
        AbsPanel trumping_ = _window.getCompoFactory().newGrid(0,1);
        //Panneau gestion des coupes
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid(0,2);
        AbsPlainLabel trumpingLabel_ = getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.TRUMPING));
        trumpingLabel_.setToolTipText(translate(MessagesDialogBelote.TRUMPING_DESCRIPTION));
        sousPanneau_.add(trumpingLabel_);
        listChoiceTwo=new ComboBoxEnumCards<BeloteTrumpPartner>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        BeloteTrumpPartner curOne_ = getReglesBelote().getGestionCoupePartenaire();
        int index_ = 0;
        for(BeloteTrumpPartner choix_:allBeloteTrumpPartner()) {
            listChoiceTwo.addItem(choix_, Games.toString(choix_,lg_));
            if (choix_ == curOne_) {
                listChoiceTwo.selectItem(index_);
            }
            index_++;
        }
        listChoiceTwo.getCombo().repaint();
        sousPanneau_.add(listChoiceTwo.self());
        underTrumpingFoe=getCompoFactory().newCustCheckBox(translate(MessagesDialogBelote.UNDER_TRUMPING_FOE));
        underTrumpingFoe.setSelected(getReglesBelote().getSousCoupeAdv());
        sousPanneau_.add(underTrumpingFoe);
        trumping_.add(sousPanneau_);
        _jt.add(translate(MessagesDialogBelote.RULES_TRUMPS),trumping_);
        //Panneau Calcul des scores
        AbsPanel endOfGame_=_window.getCompoFactory().newGrid(0,1);
        endOfGame_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.SCORING)));
        classic=getCompoFactory().newCustCheckBox(translate(MessagesDialogBelote.ALL_POINTS_FOR_DEFENDER_TEAM));
        classic.setSelected(getReglesBelote().getComptePointsClassique());
        endOfGame_.add(classic);
        _jt.add(translate(MessagesDialogBelote.END_DEAL),endOfGame_);
    }
    public String translate(String _k) {
        return translates(getMain().getLanguageKey()).getVal(_k);
    }
    public StringMap<String> translates(String _win) {
        return getFrames().getTranslations().getMapping().getVal(_win).getMapping().getVal(Games.CARDS).getMapping().getVal(Games.DIALOG_BELOTE).getMapping();
    }
    public static BeloteTrumpPartner[] allBeloteTrumpPartner() {
        return new BeloteTrumpPartner[]{BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,BeloteTrumpPartner.OVERTRUMP_ONLY,BeloteTrumpPartner.UNDERTRUMP_ONLY,BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP};
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(WindowCardsInt _parent);
    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    public void validateRules() {


//        getReglesBelote().setCartesBattues((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesBelote().getCommon().setMixedCards(listeChoix.getCurrent());
        IdMap<BidBelote,BoolVal> contrats_ = new IdMap<BidBelote,BoolVal>();
        IdList<BidBelote> all_ = BidBelote.all();
        int s_ = all_.size();
        for (int i = 0; i < s_; i++) {
            AbsCustCheckBox jcb_= bids.getValue(i);
            contrats_.put(all_.get(i), ComparatorBoolean.of(jcb_.isSelected()));
        }
        getReglesBelote().setAllowedBids(contrats_);

        IdMap<DeclaresBelote,BoolVal> annonces_ = new IdMap<DeclaresBelote,BoolVal>();
        for (DeclaresBelote enchere_: indicesAnnoncesValides.getKeys()) {
            AbsCustCheckBox jcb_= declares.getValue(indicesAnnoncesValides.getVal(enchere_));
            annonces_.put(enchere_,ComparatorBoolean.of(jcb_.isSelected()));
        }
        getReglesBelote().setAllowedDeclares(annonces_);

        BeloteTrumpPartner gestionCoupe_= listChoiceTwo.getCurrentElement();
        getReglesBelote().setGestionCoupePartenaire(gestionCoupe_);
        getReglesBelote().setSousCoupeAdv(underTrumpingFoe.isSelected());
        getReglesBelote().setComptePointsClassique(classic.isSelected());
        if (dealAll.isSelected()) {
            getReglesBelote().setDealing(DealingBelote.COINCHE_2_VS_2);
        } else {
            getReglesBelote().setDealing(DealingBelote.CLASSIC_2_VS_2);
        }
    }

    public AbsCustCheckBox getDealAll() {
        return dealAll;
    }

    public IdMap<BidBelote, AbsCustCheckBox> getBids() {
        return bids;
    }

    public IdMap<DeclaresBelote, AbsCustCheckBox> getDeclares() {
        return declares;
    }

    public RulesBelote getReglesBelote() {
        return reglesBelote;
    }

    protected void setReglesBelote(RulesBelote _reglesBelote) {
        reglesBelote = _reglesBelote;
    }

    public AbsSpinner getNbGames() {
        return nbGames;
    }

    public void setNbGames(AbsSpinner _nbGames) {
        nbGames = _nbGames;
    }

}
