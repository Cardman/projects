package cards.gui.dialogs;


import cards.consts.GameType;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.containers.ContainerSinglePresident;
import cards.gui.dialogs.events.ListenerClickCardsList;
import cards.gui.dialogs.events.ValidateRulesDealEvent;
import cards.gui.panels.PresidentCardsScrollableList;
import cards.president.DealPresident;
import cards.president.DisplayingPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import code.gui.AbsPanel;
import code.gui.EnabledMenu;
import code.gui.files.MessagesGuiFct;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.MonteCarloUtil;
import code.scripts.messages.cards.MessagesEditorCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class EditorPresident extends DialogPresident implements SetterSelectedCardList,SetterSelectedCardListStepRules {
    private final EditorCards editorCards;
    private GamePresident partie;
    private PresidentCardsScrollableList stack;
    private final CustList<PresidentCardsScrollableList> hands = new CustList<PresidentCardsScrollableList>();
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private WindowCards window;

    public EditorPresident(AbstractProgramInfos _frameFactory, EnabledMenu _menu) {
        super(_frameFactory);
        editorCards = new EditorCards(_frameFactory, _menu);
    }

    public static void initEditorPresident(WindowCards _fenetre) {
        _fenetre.getEditorPresident().getEditorCards().getAssociated().setEnabled(false);
        TranslationsLg lg_ = _fenetre.getFrames().currentLg();
//        _fenetre.getEditorPresident().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getEditorPresident().setTitleDialog(_fenetre,GameEnum.PRESIDENT.toString(lg_));
        _fenetre.getEditorPresident().setReglesPresident(_fenetre.getReglesPresident());
        _fenetre.getEditorPresident().partie = null;
        _fenetre.getEditorPresident().editorCards.setSetToNullGame(true);
        _fenetre.getEditorPresident().window = _fenetre;
//        _fenetre.getEditorPresident().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getEditorPresident().displayingPresident = _fenetre.getDisplayingPresident();
        _fenetre.getEditorPresident().setDialogue(true, 0, _fenetre);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        getEditorCards().getAssociated().setEnabled(true);
    }
//    @Override
//    public void closeWindow() {
//        super.closeWindow();
//        if (setToNullGame) {
//            partie = null;
//        }
//    }


    @Override
    public boolean okDeal() {
        if (stack.taille() != 0) {
            return false;
        }
        for (PresidentCardsScrollableList p: hands) {
            int s_ = p.taille();
            if (s_ < p.getMax() - 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window) {
//        AbsPanel container_=_window.getCompoFactory().newBorder();
        //Panneau Distribution
        ValidateRulesDealEvent.addButton(initJt(getCompoFactory().newSpinner(EditorCards.MIN_DEALS, EditorCards.MIN_DEALS, EditorCards.MAX_DEALS,1),_enabledChangingNbPlayers,_nbPlayers, _window),_window,this,this);
//        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_=_window.getCompoFactory().newLineBox();
//        AbsButton bouton_=getCompoFactory().newPlainButton(editorCards.translate(_window.getLanguageKey(),MessagesEditorCards.NEXT));
//        bouton_.addActionListener(new ValidateRulesDealEvent(this, window));
//        panneau_.add(bouton_);
//        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        getCardDialog().setContentPane(container_);
//        getCardDialog().pack();
    }

    @Override
    public void validateRulesDeal(WindowCardsInt _parent) {
        validateRules();
        getReglesPresident().getCommon().setNbDeals(getNbGames().getValue());
        distribuer(_parent);
    }

    private void distribuer(WindowCardsInt _parent) {
        getAbsDialog().setTitle(editorCards.translate(MessagesEditorCards.DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        int nbCartesPJ_;

        int nbCards_ = getReglesPresident().getNbStacks() * HandPresident.pileBase().total();
        int nbPlayers_ = getReglesPresident().getNbPlayers();
        int rem_ = nbCards_ % nbPlayers_;
        boolean noRem_ = rem_ == 0;
        int nbCardsPerPlayer_ = nbCards_ / nbPlayers_;
        if (noRem_) {
            nbCartesPJ_ = nbCardsPerPlayer_;
        } else {
            nbCartesPJ_ = nbCardsPerPlayer_ + 1;
        }

        int nbStacks_ = getReglesPresident().getNbStacks();
        HandPresident pile_= HandPresident.stack(nbStacks_);
//        for (int i = List.FIRST_INDEX; i < nbStacks_; i++) {
//            pile_.ajouterCartes(HandPresident.pileBase());
//        }
        panneau_.add(getCompoFactory().newPlainLabel(editorCards.translate(MessagesEditorCards.DEALER)));
//        liste=new StringComboBox(GuiBaseUtil.combo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
//        liste.addItem(nickNames.getPseudo());
//        for(String n: nickNames.getPseudosPresident()) {
//            if (liste.getItemCount() == nbPlayers_) {
//                break;
//            }
//            liste.addItem(n);
//        }
//        liste.addItem(editorCards.translate(_parent,MessagesEditorCards.RANDOM));
//        liste.getCombo().repaint();
        panneau_.add(editorCards.buildDealer(window.getPseudosJoueurs().getPseudo(),_parent.getImageFactory(), _parent.getCompoFactory(), window.getPseudosJoueurs().getPseudosPresident(), nbPlayers_).self());
        c.add(panneau_, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        pile_.sortCards(displayingPresident.getDisplaying().isDecreasing(), false);
        PresidentCardsScrollableList plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,pile_.total(),editorCards.translate(MessagesEditorCards.DEALING_STACK),displayingPresident);
        plc_.iniPile(pile_.getCards());
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.setPanelsCards(_parent.getCompoFactory().newLineBox());
        editorCards.clear();
        stack = plc_;
        editorCards.addPanel(plc_);
        editorCards.getPanelsCards().add(plc_.getContainer());
        plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,editorCards.translate(MessagesEditorCards.USER_HAND),displayingPresident);
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.getPanelsCards().add(plc_.getContainer());
        hands.clear();
        hands.add(plc_);
        editorCards.addPanel(plc_);
//        int i_=0;
//        int h_ = 10*(nbCartesPJ_+6);
        StringList pseudos_ = window.getPseudosJoueurs().getPseudosPresident();
        int count_ = NumberUtil.min(nbPlayers_-1,pseudos_.size());
        for (int i = 0; i < count_; i++) {
            String message_ = editorCards.translate(MessagesEditorCards.PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, pseudos_.get(i));
            plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,message_,displayingPresident);
            plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
            editorCards.getPanelsCards().add(plc_.getContainer());
            hands.add(plc_);
            editorCards.addPanel(plc_);
        }
        editorCards.buildPanelDealAll(c,window,this,window.getPseudosJoueurs().getPseudosPresident(), getReglesPresident().getNbPlayers(),"", false);
//        panneau_=_parent.getCompoFactory().newBorder();
//        panneau_.add(editorCards.getPanelsCards(),GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel sousPanneau_=editorCards.buildMoveCards(_parent,this);
//        StringComboBox handPl_ = editorCards.beginCombo(_parent.getImageFactory(), _parent.getCompoFactory(), window.getPseudosJoueurs().getPseudosPresident(), getReglesPresident().getNbPlayers());
//        handPl_.getCombo().repaint();
//        sousPanneau_.add(handPl_.self());
//        sousPanneau_.add(editorCards.buildLabelSelectCard(getCompoFactory()));
//        panneau_.add(sousPanneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        c.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);
//        editorCards.buildPanelDeal(c,window,this);

//        panneau_=_parent.getCompoFactory().newLineBox();
//        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.BACK));
//        bouton_.addActionListener(new BackToRulesEvent(this, _parent));
//        panneau_.add(bouton_);
//        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.SAVE_WITHOUT_CLOSING));
//        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_WITHOUT_CLOSING, _parent));
//        panneau_.add(bouton_);
//        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.SAVE_THEN_PLAY));
//        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_PLAY, _parent));
//        panneau_.add(bouton_);
//        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.PLAY_WITHOUT_SAVING));
//        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.PLAY_WITHOUT_SAVING, _parent));
//        panneau_.add(bouton_);
//        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.SAVE_THEN_CLOSE));
//        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_CLOSE, _parent));
//        panneau_.add(bouton_);
//        c.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        getCardDialog().setContentPane(editorCards.getPanelDeal());
//        getCardDialog().setContentPane(c);
//        getCardDialog().pack();

    }

    @Override
    public void backToRules(WindowCardsInt _parent) {
        setDialogue(true,0, _parent);
    }

    @Override
    public void cancelDeal() {
        partie = null;
    }

    @Override
    public void setPartie() {

        CustList<HandPresident> mains_=new CustList<HandPresident>();
        for(PresidentCardsScrollableList l: hands) {
            HandPresident m=new HandPresident();
            m.getCards().addAllElts(l.valElts());
            m.sortCards(displayingPresident.getDisplaying().isDecreasing(), false);
            mains_.add(m);
        }
        int nombreDeJoueurs_ = getReglesPresident().getNbPlayers();
        int donneur_ = editorCards.getListe().getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_= MonteCarloUtil.randomLong(nombreDeJoueurs_,getFrames().getGenerator());
        }
        DealPresident donne_=new DealPresident(mains_,donneur_);
        partie = new GamePresident(GameType.EDIT,donne_,getReglesPresident(), new Ints());
        partie.setNombre();
    }

    /**Lorsqu'on veut sauvegarder une partie*/
    public void validerSauvegarde(String _s) {
        window.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().president(_s,partie);
    }

    @Override
    public void deplacerCartes() {
        editorCards.getErrors().setText("");
        HandPresident m=new HandPresident();
        for (PresidentCardsScrollableList l: stackHands()) {
            m.getCards().addAllElts(l.elementsSelection());
        }
        int numero_= editorCards.getListeTwo().getSelectedIndex();
        PresidentCardsScrollableList panneauSelectionne_= stackHands().get(numero_);
        int taille_=panneauSelectionne_.taille();
        int max_=panneauSelectionne_.getMax();
        if(taille_+m.total()<max_+1) {
            for (PresidentCardsScrollableList p: stackHands()) {
                p.supprimerElts();
                p.getListe().forceRefresh();
            }
            panneauSelectionne_.ajouterCartes(m.getCards());
            panneauSelectionne_.getListe().forceRefresh();
            getEditorCards().getLabelSelectCards().setText(StringUtil.simpleNumberFormat(editorCards.translate(MessagesEditorCards.SELECTED_CARDS),0));
            getAbsDialog().pack();
        } else {
            editorCards.errs(m.total(),max_,taille_);
        }
    }
    @Override
    public void playGame() {
        window.getCore().setContainerGame(new ContainerSinglePresident(window));
        ((ContainerSinglePresident) window.getCore().getContainerGame()).editerPresident(partie);
        window.getChange().setEnabled(true);
    }

    public static GamePresident getPartie(EditorPresident _dialog) {
        _dialog.getAbsDialog().setVisible(true);
        return _dialog.partie;
    }

    public EditorCards getEditorCards() {
        return editorCards;
    }

    public PresidentCardsScrollableList getStack() {
        return stack;
    }

    public CustList<PresidentCardsScrollableList> stackHands() {
        CustList<PresidentCardsScrollableList> hands_ = new CustList<PresidentCardsScrollableList>();
        hands_.add(stack);
        hands_.addAllElts(hands);
        return hands_;
    }
}
