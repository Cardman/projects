package cards.gui.dialogs;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCardsCore;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogBelote;
import code.sml.util.TranslationsLg;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public final class DialogBeloteContent {
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
    private final AbstractProgramInfos frames;
    public DialogBeloteContent(AbstractProgramInfos _fr) {
        frames = _fr;
    }
    public AbsTabbedPane initJt(WindowCardsInt _window, AbsSpinner _nbGames) {
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
        setNbGames(_nbGames);
        TranslationsLg lg_ = getFrames().currentLg();
        AbsPanel dealing_=_window.getCompoFactory().newGrid();
        //Sous - panneau Battre les cartes
        dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.MIX_CARDS)), WindowCardsCore.cts(_window.getCompoFactory()));
        listeChoix=DialogHelpCards.build(_window,getReglesBelote().getCommon().getMixedCards());
        dealing_.add(listeChoix.self(), WindowCardsCore.ctsRem(_window.getCompoFactory()));
        dealAll = getCompoFactory().newCustCheckBox(translate(MessagesDialogBelote.DEALING_MODE));
        dealAll.setSelected(getReglesBelote().withBidPointsForAllPlayers());
        dealing_.add(dealAll, WindowCardsCore.cts(_window.getCompoFactory()));
        dealing_.add(getCompoFactory().newPlainLabel(""), WindowCardsCore.ctsRem(_window.getCompoFactory()));
        if (getNbGames() != null) {
            dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.NUMBER_DEALS)), WindowCardsCore.cts(_window.getCompoFactory()));
            dealing_.add(getNbGames(), WindowCardsCore.ctsRem(_window.getCompoFactory()));
        }

        //Panneau Distribution
        jt_.add(translate(MessagesDialogBelote.DEALING),dealing_);
        AbsPanel bidding_=_window.getCompoFactory().newPageBox();
        //Panneau Annonces autorisees
        bidding_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.CST_BIDS)));
        bids.clear();
        AbsPanel bids_ = _window.getCompoFactory().newLineBox();
        for (BidBelote enchere_:BidBelote.all()) {
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,lg_));
            caseCroix_.setSelected(getReglesBelote().getAllowedBids().getVal(enchere_) == BoolVal.TRUE);
            caseCroix_.setEnabled(!enchere_.getToujoursPossibleAnnoncer());
            bids_.add(caseCroix_);
            bids.addEntry(enchere_,caseCroix_);
        }


        bidding_.add(bids_);

        bidding_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.ALLOWED_DECLARING)));
        AbsPanel declaresFirstRound_ = _window.getCompoFactory().newGrid();
        declares.clear();
        int indice_ = 0;
        for (DeclaresBelote enchere_:DeclaresBelote.annoncesValides()) {
            indicesAnnoncesValides.put(enchere_, indice_);
            AbsCustCheckBox caseCroix_=getCompoFactory().newCustCheckBox(Games.toString(enchere_,lg_));
            caseCroix_.setSelected(getReglesBelote().getAllowedDeclares().getVal(enchere_) == BoolVal.TRUE);
            declaresFirstRound_.add(caseCroix_,WindowCardsCore.ctsRem(_window.getCompoFactory(),(indice_+1)%3==0));
            declares.addEntry(enchere_,caseCroix_);
            indice_++;
        }
        bidding_.add(declaresFirstRound_);

        jt_.add(translate(MessagesDialogBelote.DECLARING),bidding_);
        //Panneau gestion des coupes
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid();
        AbsPlainLabel trumpingLabel_ = getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.TRUMPING));
        trumpingLabel_.setToolTipText(translate(MessagesDialogBelote.TRUMPING_DESCRIPTION));
        sousPanneau_.add(trumpingLabel_,WindowCardsCore.cts(_window.getCompoFactory()));
        listChoiceTwo=new ComboBoxEnumCards<BeloteTrumpPartner>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        BeloteTrumpPartner curOne_ = getReglesBelote().getGestionCoupePartenaire();
        int index_ = 0;
        for(BeloteTrumpPartner choix_:DialogBelote.allBeloteTrumpPartner()) {
            listChoiceTwo.addItem(choix_, Games.toString(choix_,lg_));
            if (choix_ == curOne_) {
                listChoiceTwo.selectItem(index_);
            }
            index_++;
        }
        listChoiceTwo.getCombo().repaint();
        sousPanneau_.add(listChoiceTwo.self(),WindowCardsCore.ctsRem(_window.getCompoFactory()));
        underTrumpingFoe=getCompoFactory().newCustCheckBox(translate(MessagesDialogBelote.UNDER_TRUMPING_FOE));
        underTrumpingFoe.setSelected(getReglesBelote().getSousCoupeAdv());
        sousPanneau_.add(underTrumpingFoe,WindowCardsCore.cts(_window.getCompoFactory()));
        jt_.add(translate(MessagesDialogBelote.RULES_TRUMPS),sousPanneau_);
        //Panneau Calcul des scores
        AbsPanel endOfGame_=_window.getCompoFactory().newPageBox();
        endOfGame_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogBelote.SCORING)));
        classic=getCompoFactory().newCustCheckBox(translate(MessagesDialogBelote.ALL_POINTS_FOR_DEFENDER_TEAM));
        classic.setSelected(getReglesBelote().getComptePointsClassique());
        endOfGame_.add(classic);
        jt_.add(translate(MessagesDialogBelote.END_DEAL),endOfGame_);
        return jt_;
    }
    public String translate(String _k) {
        return translates().getVal(_k);
    }
    public StringMap<String> translates() {
        return Games.getDialogBeloteTr(Games.getAppliTr(getFrames().currentLg())).getMapping();
    }

    public AbsCompoFactory getCompoFactory() {
        return getFrames().getCompoFactory();
    }
    public AbstractProgramInfos getFrames() {
        return frames;
    }
    public void validateRules() {
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

    public ComboBox<MixCardsChoice> getListeChoix() {
        return listeChoix;
    }

    public ComboBoxEnumCards<BeloteTrumpPartner> getListChoiceTwo() {
        return listChoiceTwo;
    }

    public AbsCustCheckBox getClassic() {
        return classic;
    }

    public AbsCustCheckBox getUnderTrumpingFoe() {
        return underTrumpingFoe;
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

    public void setReglesBelote(RulesBelote _reglesBelote) {
        reglesBelote = _reglesBelote;
    }

    public AbsSpinner getNbGames() {
        return nbGames;
    }

    public void setNbGames(AbsSpinner _nbGames) {
        nbGames = _nbGames;
    }
}
