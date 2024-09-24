package cards.gui.dialogs;


import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.containers.ContainerSolitaire;
import cards.gui.dialogs.events.ListenerClickCardsList;
import cards.gui.panels.SolitaireCardsScrollableList;
import cards.solitaire.*;
import cards.solitaire.sml.DocumentReaderSolitaireUtil;
import code.gui.AbsPanel;
import code.gui.EnabledMenu;
import code.gui.MenuItemUtils;
import code.gui.files.MessagesGuiFct;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesEditorCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class EditorSolitaire extends DialogHelpCards implements SetterSelectedCardList {
    private final EditorCards editorCards;
    private AbsDealSolitaire partie;
    private SolitaireCardsScrollableList stack;
    private final CustList<SolitaireCardsScrollableList> hands = new CustList<SolitaireCardsScrollableList>();
    private WindowCards window;
    private final GameEnum type;

    public EditorSolitaire(AbstractProgramInfos _frameFactory, EnabledMenu _menu, GameEnum _type) {
        super(_frameFactory);
        editorCards = new EditorCards(_frameFactory, _menu);
        type = _type;
    }

    public static void initEditorSolitaire(WindowCards _fenetre, EditorSolitaire _solitaire) {
        _solitaire.getEditorCards().getAssociated().setEnabled(false);
        TranslationsLg lg_ = _fenetre.getFrames().currentLg();
//        _fenetre.getEditorPresident().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _solitaire.setTitleDialog(_fenetre, _solitaire.type.toString(lg_));
//        _fenetre.getEditorSolitaire().setReglesPresident(_fenetre.getReglesPresident());
        _solitaire.partie = null;
        _solitaire.editorCards.setSetToNullGame(true);
        _solitaire.window = _fenetre;
//        _fenetre.getEditorPresident().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getEditorSolitaire().displayingPresident = _fenetre.getDisplayingPresident();
        _solitaire.setDialogue(_fenetre);
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
        return stack.taille() == 0;
    }

//    @Override
    public void setDialogue(WindowCardsInt _window) {
//        AbsPanel container_=_window.getCompoFactory().newBorder();
        //Panneau Distribution
        distribuer(_window);
//        ValidateRulesDealEvent.addButton(initJt(getCompoFactory().newSpinner(EditorCards.MIN_DEALS, EditorCards.MIN_DEALS, EditorCards.MAX_DEALS,1),_enabledChangingNbPlayers,_nbPlayers, _window),_window,this,this);
//        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_=_window.getCompoFactory().newLineBox();
//        AbsButton bouton_=getCompoFactory().newPlainButton(editorCards.translate(_window.getLanguageKey(),MessagesEditorCards.NEXT));
//        bouton_.addActionListener(new ValidateRulesDealEvent(this, window));
//        panneau_.add(bouton_);
//        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        getCardDialog().setContentPane(container_);
//        getCardDialog().pack();
    }

//    @Override
//    public void validateRulesDeal(WindowCardsInt _parent) {
//        validateRules();
//        getReglesPresident().getCommon().setNbDeals(getNbGames().getValue());
//        distribuer(_parent);
//    }

    private void distribuer(WindowCardsInt _parent) {
        getAbsDialog().setTitle(editorCards.translate(MessagesEditorCards.DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
//        byte nbCartesPJ_;

//        int nbCards_ = getReglesPresident().getNbStacks() * HandPresident.pileBase().total();
//        int nbPlayers_ = getReglesPresident().getNbPlayers();
//        int rem_ = nbCards_ % nbPlayers_;
//        boolean noRem_ = rem_ == 0;
//        int nbCardsPerPlayer_ = nbCards_ / nbPlayers_;
//        if (noRem_) {
//            nbCartesPJ_ = (byte) nbCardsPerPlayer_;
//        } else {
//            nbCartesPJ_ = (byte) (nbCardsPerPlayer_ + 1);
//        }

        int nbStacks_;
        if (type.getSolitaireType() == SolitaireType.CLASSIC || type.getSolitaireType() == SolitaireType.FREECELL) {
            nbStacks_ = 1;
        } else {
            nbStacks_ = 2;
        }
        HandSolitaire pile_= HandSolitaire.stack(nbStacks_);
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
//        panneau_.add(editorCards.buildDealer(window.getPseudosJoueurs().getPseudo(),_parent.getImageFactory(), _parent.getCompoFactory(), window.getPseudosJoueurs().getPseudosPresident(), nbPlayers_).self());
        c.add(panneau_, MessagesGuiFct.BORDER_LAYOUT_NORTH);
//        pile_.sortCards(displayingPresident.getDisplaying().isDecreasing(), false);
        SolitaireCardsScrollableList plc_=new SolitaireCardsScrollableList(_parent, 16,pile_.total(),editorCards.translate(MessagesEditorCards.DEALING_STACK));
        plc_.iniPile(pile_.getCards());
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.setPanelsCards(_parent.getCompoFactory().newLineBox());
        editorCards.clear();
        stack = plc_;
        editorCards.addPanel(plc_);
        editorCards.getPanelsCards().add(plc_.getContainer());
//        plc_=new SolitaireCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,editorCards.translate(MessagesEditorCards.USER_HAND));
//        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
//        editorCards.getPanelsCards().add(plc_.getContainer());
//        hands.clear();
//        hands.add(plc_);
//        editorCards.addPanel(plc_);
//        int i_=0;
//        int h_ = 10*(nbCartesPJ_+6);
//        StringList pseudos_ = window.getPseudosJoueurs().getPseudosPresident();
//        int count_ = NumberUtil.min(nbPlayers_-1,pseudos_.size());

        int[] arr_;
        if (type.getSolitaireType() == SolitaireType.CLASSIC) {
            arr_ = new int[] {24,1,2,3,4,5,6,7};
        } else if (type.getSolitaireType() == SolitaireType.FREECELL) {
            arr_ = new int[] {7,7,7,7,6,6,6,6};
        } else {
            arr_ = new int[] {50,6,6,6,6,5,5,5,5,5,5};
        }
        int len_ = arr_.length;
        for (int i = 0; i < len_; i++) {
            plc_=new SolitaireCardsScrollableList(_parent, 16,arr_[i],Long.toString(i));
            plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
            editorCards.getPanelsCards().add(plc_.getContainer());
            hands.add(plc_);
            editorCards.addPanel(plc_);
        }
        editorCards.buildPanelDealAll(c,window,this,new StringList(), len_,"", false);
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
        setDialogue(_parent);
    }

    @Override
    public void cancelDeal() {
        partie = null;
    }

    @Override
    public void setPartie() {
        CustList<HandSolitaire> mains_=new CustList<HandSolitaire>();
        for(SolitaireCardsScrollableList l: hands) {
            HandSolitaire m=new HandSolitaire();
            m.getCards().addAllElts(l.valElts());
            mains_.add(m);
        }
        int supp_ = type.getSolitaireType().getSupp();
        for (int i = 0; i < supp_; i++) {
            mains_.add(new HandSolitaire());
        }
        partie = DocumentReaderSolitaireUtil.init(type.getSolitaireType());
        partie.setHandsBegin(mains_);
        partie.setActions(new CustList<ActionSolitaire>());
    }

    /**Lorsqu'on veut sauvegarder une partie*/
    public void validerSauvegarde(String _s) {
        window.getCore().getFacadeCards().getNicknamesCrud().getCardGamesCrud().solitaire(_s,partie);
    }

    @Override
    public void deplacerCartes() {
        editorCards.getErrors().setText("");
        HandSolitaire m=new HandSolitaire();
        for (SolitaireCardsScrollableList l: stackHands()) {
            m.getCards().addAllElts(l.elementsSelection());
        }
        int numero_= editorCards.getListeTwo().getSelectedIndex();
        SolitaireCardsScrollableList panneauSelectionne_= stackHands().get(numero_);
        int taille_=panneauSelectionne_.taille();
        int max_=panneauSelectionne_.getMax();
        if(taille_+m.total()<max_+1) {
            for (SolitaireCardsScrollableList p: stackHands()) {
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
        ContainerSolitaire cont_ = new ContainerSolitaire(window);
        window.getCore().setContainerGame(cont_);
        cont_.setSolitaireType(type.getSolitaireType());
        cont_.editerSolitaire(partie);
        MenuItemUtils.setEnabledMenu(window.getChange(),true);
    }

    public static AbsDealSolitaire getPartie(EditorSolitaire _dialog) {
        _dialog.getAbsDialog().setVisible(true);
        return _dialog.partie;
    }

    public EditorCards getEditorCards() {
        return editorCards;
    }

    public SolitaireCardsScrollableList getStack() {
        return stack;
    }

    public CustList<SolitaireCardsScrollableList> stackHands() {
        CustList<SolitaireCardsScrollableList> hands_ = new CustList<SolitaireCardsScrollableList>();
        hands_.add(stack);
        hands_.addAllElts(hands);
        return hands_;
    }
}
