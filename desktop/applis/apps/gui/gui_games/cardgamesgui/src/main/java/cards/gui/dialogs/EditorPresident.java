package cards.gui.dialogs;


import cards.consts.GameType;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.containers.ContainerSinglePresident;
import cards.gui.dialogs.events.*;
import cards.gui.panels.PresidentCardsScrollableList;
import cards.president.*;
import cards.president.sml.DocumentWriterPresidentUtil;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.MonteCarloUtil;
import code.scripts.messages.cards.MessagesEditorCards;
import code.stream.StreamTextFile;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class EditorPresident extends DialogPresident implements SetterSelectedCardList {
    private final EditorCards editorCards;
    private GamePresident partie;
    private PresidentCardsScrollableList stack;
    private final CustList<PresidentCardsScrollableList> hands = new CustList<PresidentCardsScrollableList>();
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private WindowCards window;

    public EditorPresident(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, new ClosingEditorCards());
        editorCards = new EditorCards(_frameFactory);
        getClos().setEditor(this);
    }

    public static void initEditorPresident(WindowCards _fenetre) {
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorPresident().setMain(_fenetre);
        _fenetre.getEditorPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getEditorPresident().getCardDialog().setTitle(GameEnum.PRESIDENT.toString(lg_));
        _fenetre.getEditorPresident().setReglesPresident(_fenetre.getReglesPresident());
        _fenetre.getEditorPresident().partie = null;
        _fenetre.getEditorPresident().editorCards.setSetToNullGame(true);
        _fenetre.getEditorPresident().window = _fenetre;
        _fenetre.getEditorPresident().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getEditorPresident().displayingPresident = _fenetre.getDisplayingPresident();
        _fenetre.getEditorPresident().setDialogue(true, 0, _fenetre);
    }

//    @Override
//    public void closeWindow() {
//        super.closeWindow();
//        if (setToNullGame) {
//            partie = null;
//        }
//    }

    @Override
    public int stackSize() {
        return stack.taille();
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window) {
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
//        AbsPanel container_=_window.getCompoFactory().newBorder();
        //Panneau Distribution
        initJt(getCompoFactory().newSpinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),_enabledChangingNbPlayers,_nbPlayers, _window, jt_);
        ValidateRulesDealEvent.addButton(jt_,_window,this,this);
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
        getCardDialog().setTitle(editorCards.translate(_parent,MessagesEditorCards.DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        byte nbCartesPJ_;

        int nbCards_ = getReglesPresident().getNbStacks() * HandPresident.pileBase().total();
        int nbPlayers_ = getReglesPresident().getNbPlayers();
        int rem_ = nbCards_ % nbPlayers_;
        boolean noRem_ = rem_ == 0;
        int nbCardsPerPlayer_ = nbCards_ / nbPlayers_;
        if (noRem_) {
            nbCartesPJ_ = (byte) nbCardsPerPlayer_;
        } else {
            nbCartesPJ_ = (byte) (nbCardsPerPlayer_ + 1);
        }

        int nbStacks_ = getReglesPresident().getNbStacks();
        HandPresident pile_= HandPresident.stack(nbStacks_);
//        for (int i = List.FIRST_INDEX; i < nbStacks_; i++) {
//            pile_.ajouterCartes(HandPresident.pileBase());
//        }
        panneau_.add(getCompoFactory().newPlainLabel(editorCards.translate(_parent,MessagesEditorCards.DEALER)));
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
        panneau_.add(editorCards.buildDealer(window.getPseudosJoueurs().getPseudo(),_parent.getImageFactory(), _parent.getCompoFactory(), _parent.getLanguageKey(), window.getPseudosJoueurs().getPseudosPresident(), nbPlayers_).self());
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_NORTH);
        pile_.sortCards(displayingPresident.getDisplaying().isDecreasing(), false);
        PresidentCardsScrollableList plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,pile_.total(),editorCards.translate(_parent,MessagesEditorCards.DEALING_STACK),displayingPresident);
        plc_.iniPile(pile_.getCards());
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.setPanelsCards(_parent.getCompoFactory().newLineBox());
        editorCards.clear();
        stack = plc_;
        editorCards.addPanel(plc_);
        editorCards.getPanelsCards().add(plc_.getContainer());
        plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,editorCards.translate(_parent,MessagesEditorCards.USER_HAND),displayingPresident);
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.getPanelsCards().add(plc_.getContainer());
        hands.clear();
        hands.add(plc_);
        editorCards.addPanel(plc_);
//        int i_=0;
        int h_ = 10*(nbCartesPJ_+6);
        StringList pseudos_ = window.getPseudosJoueurs().getPseudosPresident();
        int count_ = NumberUtil.min(nbPlayers_-1,pseudos_.size());
        for (int i = 0; i < count_; i++) {
            String message_ = editorCards.translate(_parent,MessagesEditorCards.PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, pseudos_.get(i));
            plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,message_,displayingPresident);
            plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
            editorCards.getPanelsCards().add(plc_.getContainer());
            hands.add(plc_);
            editorCards.addPanel(plc_);
        }
        AbsScrollPane scroll_ = _parent.getCompoFactory().newAbsScrollPane(editorCards.getPanelsCards());
        scroll_.setPreferredSize(new MetaDimension(500, h_));
        panneau_=_parent.getCompoFactory().newBorder();
        panneau_.add(scroll_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel sousPanneau_=editorCards.buildMoveCards(_parent,this);
        StringComboBox handPl_ = editorCards.beginCombo(_parent.getImageFactory(), _parent.getCompoFactory(), _parent.getLanguageKey(), window.getPseudosJoueurs().getPseudosPresident(), getReglesPresident().getNbPlayers());
//        listeTwo=new StringComboBox(GuiBaseUtil.combo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
//        listeTwo.addItem(editorCards.translate(_parent,MessagesEditorCards.DEALING_STACK));
//        listeTwo.addItem(editorCards.translate(_parent,MessagesEditorCards.USER_HAND));
//        for(String n: nickNames.getPseudosPresident()) {
//            if (listeTwo.getItemCount() == getReglesPresident().getNbPlayers() + 1) {
//                break;
//            }
//            String message_ = editorCards.translate(_parent,MessagesEditorCards.PLAYER_HAND);
//            message_ = StringUtil.simpleStringsFormat(message_, n);
//            listeTwo.addItem(message_);
//        }
        handPl_.getCombo().repaint();
        sousPanneau_.add(handPl_.self());
        sousPanneau_.add(editorCards.buildLabelSelectCard(getCompoFactory(), _parent.getLanguageKey()));
        panneau_.add(sousPanneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);
        editorCards.buildPanelDeal(c,window,this);

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
        getCardDialog().setContentPane(editorCards.getPanelDeal());
//        getCardDialog().setContentPane(c);
        getCardDialog().pack();

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
        int nombreDeJoueurs_;

        CustList<HandPresident> mains_=new CustList<HandPresident>();
        for(PresidentCardsScrollableList l: hands) {
            HandPresident m=new HandPresident();
            m.getCards().addAllElts(l.valMain());
            m.sortCards(displayingPresident.getDisplaying().isDecreasing(), false);
            mains_.add(m);
        }
        nombreDeJoueurs_=getReglesPresident().getNbPlayers();
        byte donneur_ = (byte) editorCards.getListe().getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)MonteCarloUtil.randomLong(nombreDeJoueurs_,getMain().getGenerator());
        }
        DealPresident donne_=new DealPresident(mains_,donneur_);
        partie = new GamePresident(GameType.EDIT,donne_,getReglesPresident(), new Bytes());
    }

    /**Lorsqu'on veut sauvegarder une partie*/
    public void validerSauvegarde(String _s) {
        StreamTextFile.saveTextFile(_s, DocumentWriterPresidentUtil.setGamePresident(partie), window.getStreams());
    }

    @Override
    public void deplacerCartes() {
        editorCards.getErrors().setText("");
        String lg_ = getMain().getLanguageKey();
        HandPresident m=new HandPresident();
        for (PresidentCardsScrollableList l: stackHands()) {
            m.getCards().addAllElts(l.getCartesSelectionnees());
        }
        int numero_= editorCards.getListeTwo().getSelectedIndex();
        PresidentCardsScrollableList panneauSelectionne_= stackHands().get(numero_);
        int taille_=panneauSelectionne_.taille();
        int max_=panneauSelectionne_.getMax();
        if(taille_+m.total()<max_+1) {
            for (PresidentCardsScrollableList l: stackHands()) {
                l.supprimerCartes(l.getCartesSelectionnees());
                l.getListe().deselectAll();
                l.getListe().forceRefresh();
            }
            panneauSelectionne_.ajouterCartes(m.getCards());
            panneauSelectionne_.getListe().forceRefresh();
            getEditorCards().getLabelSelectCards().setText(StringUtil.simpleNumberFormat(editorCards.translate(lg_,MessagesEditorCards.SELECTED_CARDS),0));
            getCardDialog().pack();
        } else {
            String mes_ = editorCards.translate(lg_,MessagesEditorCards.ERROR_MOVE);
            mes_ = StringUtil.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString((long)max_-taille_), editorCards.getListeTwo().getSelectedComboItem());
            editorCards.getErrors().setText(mes_);
        }
    }
    @Override
    public void playGame() {
        window.getCore().setContainerGame(new ContainerSinglePresident(window));
        ((ContainerSinglePresident) window.getCore().getContainerGame()).editerPresident(partie);
        MenuItemUtils.setEnabledMenu(window.getChange(),true);
    }

    public static GamePresident getPartie(EditorPresident _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.partie;
    }

    public EditorCards getEditorCards() {
        return editorCards;
    }

    public CustList<PresidentCardsScrollableList> stackHands() {
        CustList<PresidentCardsScrollableList> hands_ = new CustList<PresidentCardsScrollableList>();
        hands_.add(stack);
        hands_.addAllElts(hands);
        return hands_;
    }
}
