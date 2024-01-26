package cards.gui.dialogs;


import cards.consts.GameType;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.dialogs.events.*;
import cards.gui.panels.TarotCardsScrollableList;
import cards.tarot.*;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.MonteCarloUtil;
import code.scripts.messages.cards.MessagesEditorCards;
import code.sml.util.TranslationsLg;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class EditorTarot extends DialogTarot implements SetterSelectedCardList {
    private final EditorCards editorCards;
    private GameTarot partie;
    private TarotCardsScrollableList stack;
    private final CustList<TarotCardsScrollableList> hands = new CustList<TarotCardsScrollableList>();
    private TarotCardsScrollableList dog;

    private DisplayingTarot displayingTarot = new DisplayingTarot();
    private WindowCards window;
    public EditorTarot(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, new ClosingEditorCards());
        editorCards = new EditorCards(_frameFactory);
        getClos().setEditor(this);
    }
    public static void initEditorTarot(WindowCards _fenetre) {
        TranslationsLg lg_ = _fenetre.getFrames().currentLg();
        _fenetre.getEditorTarot().setMain(_fenetre);
        _fenetre.getEditorTarot().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getEditorTarot().getCardDialog().setTitle(GameEnum.TAROT.toString(lg_));
        _fenetre.getEditorTarot().setReglesTarot(_fenetre.getReglesTarot());
        _fenetre.getEditorTarot().partie = null;
        _fenetre.getEditorTarot().editorCards.setSetToNullGame(true);
        _fenetre.getEditorTarot().window = _fenetre;
        _fenetre.getEditorTarot().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getEditorTarot().displayingTarot = _fenetre.getDisplayingTarot();
        _fenetre.getEditorTarot().setDialogue(true, 0, _fenetre);
//        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent _e) {
//                partie = null;
//                dispose();
//            }
//        });
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

    @Override
    public int stackSize() {
        return stack.taille();
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
//        AbsPanel container_=_window.getCompoFactory().newBorder();
        //Panneau Distribution
        initJt(getCompoFactory().newSpinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),_enabledChangingNbPlayers,_nbPlayers, _window, jt_);
        ValidateRulesDealEvent.addButton(jt_,_window,this,this);
//        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_=_window.getCompoFactory().newLineBox();
//        AbsButton bouton_=getCompoFactory().newPlainButton(editorCards.translate(_window,MessagesEditorCards.NEXT));
//        bouton_.addActionListener(new ValidateRulesDealEvent(this, window));
//        panneau_.add(bouton_);
//        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        getCardDialog().setContentPane(container_);
//        getCardDialog().pack();
    }
    @Override
    public void validateRulesDeal(WindowCardsInt _parent) {
        validateRules();
        getReglesTarot().getCommon().setNbDeals(getNbGames().getValue());
        distribuer(_parent);
    }
    private void distribuer(WindowCardsInt _parent) {

        getCardDialog().setTitle(editorCards.translate(MessagesEditorCards.DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_;
//        byte nbJ_=(byte) getReglesTarot().getRepartition().getNombreJoueurs();
        byte nbCartesPJ_ = (byte) getReglesTarot().getDealing().getNombreCartesParJoueur();
        byte nbCartesC_ = (byte) getReglesTarot().getDealing().getNombreCartesChien();

        HandTarot pile_=HandTarot.pileBase();
        panneau_=_parent.getCompoFactory().newLineBox();
        panneau_.add(getCompoFactory().newPlainLabel(editorCards.translate(MessagesEditorCards.DEALER)));
//        liste=new StringComboBox(GuiBaseUtil.combo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
//        liste.addItem(nickNames.getPseudo());
        int nbPlayers_ = getReglesTarot().getDealing().getId().getNombreJoueurs();
//        for(String n: nickNames.getPseudosTarot()) {
//            if (liste.getItemCount() == nbPlayers_) {
//                break;
//            }
//            liste.addItem(n);
//        }
//        liste.addItem(editorCards.translate(_parent,MessagesEditorCards.RANDOM));
//        liste.getCombo().repaint();
        panneau_.add(editorCards.buildDealer(window.getPseudosJoueurs().getPseudo(),_parent.getImageFactory(), _parent.getCompoFactory(), window.getPseudosJoueurs().getPseudosTarot(), nbPlayers_).self());
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_NORTH);
        pile_.trier(displayingTarot.getDisplaying().getSuits(), displayingTarot.getDisplaying().isDecreasing());
        TarotCardsScrollableList plc_=new TarotCardsScrollableList(_parent, nbCartesPJ_,pile_.total(),editorCards.translate(MessagesEditorCards.DEALING_STACK),displayingTarot);
        plc_.iniPile(pile_.getCards());
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.setPanelsCards(_parent.getCompoFactory().newLineBox());
        editorCards.clear();
        stack = plc_;
        editorCards.addPanel(plc_);
        editorCards.getPanelsCards().add(plc_.getContainer());
        plc_=new TarotCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,editorCards.translate(MessagesEditorCards.USER_HAND),displayingTarot);
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.getPanelsCards().add(plc_.getContainer());
        hands.clear();
        hands.add(plc_);
        editorCards.addPanel(plc_);
//        int i_=0;
        StringList pseudos_ = window.getPseudosJoueurs().getPseudosTarot();
        int count_ = NumberUtil.min(nbPlayers_-1,pseudos_.size());
        for (int i = 0; i < count_; i++) {
            String message_ = editorCards.translate(MessagesEditorCards.PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, pseudos_.get(i));
            plc_=new TarotCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,message_,displayingTarot);
            plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
            editorCards.getPanelsCards().add(plc_.getContainer());
            hands.add(plc_);
            editorCards.addPanel(plc_);
        }
        plc_=new TarotCardsScrollableList(_parent, nbCartesC_,nbCartesC_,editorCards.translate(MessagesEditorCards.REMAINING),displayingTarot);
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.getPanelsCards().add(plc_.getContainer());
        dog = plc_;
        editorCards.addPanel(plc_);
        panneau_=_parent.getCompoFactory().newBorder();
        panneau_.add(editorCards.getPanelsCards(),GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel sousPanneau_=editorCards.buildMoveCards(_parent,this);
        StringComboBox handPl_ = editorCards.beginCombo(_parent.getImageFactory(), _parent.getCompoFactory(), window.getPseudosJoueurs().getPseudosTarot(), getReglesTarot().getDealing().getId().getNombreJoueurs());
//        listeTwo=new StringComboBox(GuiBaseUtil.combo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
//        listeTwo.addItem(editorCards.translate(_parent,MessagesEditorCards.DEALING_STACK));
//        listeTwo.addItem(editorCards.translate(_parent,MessagesEditorCards.USER_HAND));
//        for(String n: nickNames.getPseudosTarot()) {
//            if (listeTwo.getItemCount() == getReglesTarot().getDealing().getId().getNombreJoueurs() + 1) {
//                break;
//            }
//            String message_ = editorCards.translate(_parent,MessagesEditorCards.PLAYER_HAND);
//            message_ = StringUtil.simpleStringsFormat(message_, n);
//            listeTwo.addItem(message_);
//        }
        handPl_.addItem(editorCards.translate(MessagesEditorCards.REMAINING));
        handPl_.getCombo().repaint();
        sousPanneau_.add(handPl_.self());
        sousPanneau_.add(editorCards.buildLabelSelectCard(getCompoFactory()));
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

//        TarotCardsScrollableList plc_;
//        int nombreDeMains_=panelsCards.getComponentCount();
        int nombreDeJoueurs_;

        CustList<HandTarot> mains_=new CustList<HandTarot>();
//        for(int i=1;i<nombreDeMains_;i++) {
//            plc_=(TarotCardsScrollableList)panelsCards.getComponent(i);
//            HandTarot m=new HandTarot();
//            m.ajouterCartes(plc_.valMainTarot());
//            m.trier(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
//            mains_.add(m);
//        }
        CustList<TarotCardsScrollableList> hands_ = new CustList<TarotCardsScrollableList>();
        hands_.addAllElts(hands);
        hands_.add(dog);
        for(TarotCardsScrollableList l: hands_) {
//            plc_=(TarotCardsScrollableList)panelsCards.getComponent(i);
            HandTarot m=new HandTarot();
            m.getCards().addAllElts(l.valMain());
            m.trier(displayingTarot.getDisplaying().getSuits(), displayingTarot.getDisplaying().isDecreasing());
            mains_.add(m);
        }
//        nombreDeJoueurs_=nombreDeMains_-1;
        nombreDeJoueurs_= getReglesTarot().getDealing().getId().getNombreJoueurs();
        byte donneur_ = (byte) editorCards.getListe().getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)MonteCarloUtil.randomLong(nombreDeJoueurs_,getMain().getGenerator());
        }
        DealTarot donne_=new DealTarot(mains_,donneur_);
        partie = new GameTarot(GameType.EDIT,donne_,getReglesTarot());
    }
    /**Lorsqu'on veut sauvegarder une partie*/
    public void validerSauvegarde(String _s) {
        StreamTextFile.saveTextFile(_s, DocumentWriterTarotUtil.setGameTarot(partie), window.getStreams());
    }
    @Override
    public void deplacerCartes() {
        editorCards.getErrors().setText("");
//        int nombreDeMains_=panelsCards.getComponentCount();

        HandTarot m=new HandTarot();
//        for (int i = List.FIRST_INDEX;i<nombreDeMains_;i++) {
//            HandTarot cartesSelectionnees_=((TarotCardsScrollableList)panelsCards.getComponent(i)).getCartesTarotSelectionnees();
//            m.ajouterCartes(cartesSelectionnees_);
//        }
        for (TarotCardsScrollableList l: stackHands()) {
            m.getCards().addAllElts(l.getCartesSelectionnees());
        }
        int numero_= editorCards.getListeTwo().getSelectedIndex();
        TarotCardsScrollableList panneauSelectionne_= stackHands().get(numero_);
        //(TarotCardsScrollableList)panelsCards.getComponent(numero_);
//        TarotCardsScrollableList panneau2_;
        int taille_=panneauSelectionne_.taille();
        int max_=panneauSelectionne_.getMax();
        if(taille_+m.total()<max_+1) {
//            for (int i = List.FIRST_INDEX;i<nombreDeMains_;i++) {
//                panneau2_=(TarotCardsScrollableList)panelsCards.getComponent(i);
////                HandTarot cartesSelectionnees_=((TarotCardsScrollableList)panelsCards.getComponent(i)).getCartesTarotSelectionnees();
//                HandTarot cartesSelectionnees_= panneau2_.getCartesTarotSelectionnees();
//                panneau2_.supprimerCartesTarot(cartesSelectionnees_);
//            }
            for (TarotCardsScrollableList l: stackHands()) {
                //                panneau2_= l;
//                HandTarot cartesSelectionnees_=((TarotCardsScrollableList)panelsCards.getComponent(i)).getCartesTarotSelectionnees();
                l.supprimerCartes();
                l.getListe().forceRefresh();
            }
            panneauSelectionne_.ajouterCartes(m.getCards());
            panneauSelectionne_.getListe().forceRefresh();
            getEditorCards().getLabelSelectCards().setText(StringUtil.simpleNumberFormat(editorCards.translate(MessagesEditorCards.SELECTED_CARDS),0));
            getCardDialog().pack();
        } else {
            String mes_ = editorCards.translate(MessagesEditorCards.ERROR_MOVE);
            mes_ = StringUtil.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString((long)max_-taille_), editorCards.getListeTwo().getSelectedComboItem());
            editorCards.getErrors().setText(mes_);
            //JOptionPane.showMessageDialog(this,mes_, getMessages().getVal(ERROR_MOVE_TITLE), JOptionPane.ERROR_MESSAGE);
        }


    }
    @Override
    public void playGame() {
        window.getCore().setContainerGame(new ContainerSingleTarot(window));
        ((ContainerSingleTarot) window.getCore().getContainerGame()).editerTarot(partie);
        MenuItemUtils.setEnabledMenu(window.getChange(),true);
    }
    public static GameTarot getPartie(EditorTarot _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.partie;
    }

    public EditorCards getEditorCards() {
        return editorCards;
    }

    public TarotCardsScrollableList getStack() {
        return stack;
    }

    @Override
    protected AbsButton validatingButton() {
        return getEditorCards().getValidateRules();
    }

    public CustList<TarotCardsScrollableList> stackHands() {
        CustList<TarotCardsScrollableList> hands_ = new CustList<TarotCardsScrollableList>();
        hands_.add(stack);
        hands_.addAllElts(hands);
        hands_.add(dog);
        return hands_;
    }
}
