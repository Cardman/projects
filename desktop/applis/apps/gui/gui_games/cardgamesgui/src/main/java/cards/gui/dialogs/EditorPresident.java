package cards.gui.dialogs;


import cards.consts.GameType;
import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.dialogs.enums.SaveDealMode;
import cards.gui.dialogs.events.*;
import cards.gui.panels.CardsScrollableList;
import cards.gui.panels.PresidentCardsScrollableList;
import cards.president.DealPresident;
import cards.president.DisplayingPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.MonteCarloUtil;
import code.stream.StreamTextFile;
import code.util.Bytes;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class EditorPresident extends DialogPresident implements SetterSelectedCardList {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.editorpresident";

    private static final String BACK = "back";
    private static final String DEALER = "dealer";
    private static final String DEALING_CARDS = "dealingCards";
    private static final String DEALING_STACK = "dealingStack";
    private static final String ERROR_MOVE = "errorMove";
    private static final String ERROR_MOVE_TITLE = "errorMoveTitle";
    private static final String ERROR_REPARTITION = "errorRepartition";
    private static final String ERROR_REPARTITION_TITLE = "errorRepartitionTitle";
    private static final String ERROR_SAVE_FILE = "errorSaveFile";
    private static final String ERROR_SAVE_FILE_TITLE = "errorSaveFileTitle";
    private static final String MOVE_CARDS = "moveCards";
    private static final String NEXT = "next";
    private static final String PLAY_WITHOUT_SAVING = "playWithoutSaving";
    private static final String PLAYER_HAND = "playerHand";
    private static final String RANDOM = "random";
//    private static final String REMAINING = "remaining";
    private static final String SAVE_THEN_CLOSE = "saveThenClose";
    private static final String SAVE_THEN_PLAY = "saveThenPlay";
    private static final String SAVE_WITHOUT_CLOSING = "saveWithoutClosing";
    private static final String SELECTED_CARDS = "selectedCards";
    private static final String USER_HAND = "userHand";
    private static final String EMPTY_STRING = "";
    private boolean partieSauvegardee;
    private GamePresident partie;
    private int nombreCartesSelectionnees;
    private AbsPanel panelsCards;
    private PresidentCardsScrollableList stack;
    private final CustList<PresidentCardsScrollableList> hands = new CustList<PresidentCardsScrollableList>();
    private AbsPlainLabel labelSelectCards;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private WindowCards window;
    private boolean setToNullGame;

    public EditorPresident(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }

    public static void initEditorPresident(WindowCards _fenetre) {
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorPresident().setMain(_fenetre);
        _fenetre.getEditorPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getEditorPresident().getCardDialog().setTitle(GameEnum.PRESIDENT.toString(lg_));
        _fenetre.getEditorPresident().setReglesPresident(_fenetre.getReglesPresident());
        _fenetre.getEditorPresident().partie = null;
        _fenetre.getEditorPresident().setToNullGame = true;
        _fenetre.getEditorPresident().nombreCartesSelectionneesPrecedent = 0;
        _fenetre.getEditorPresident().nombreCartesSelectionnees = 0;
        _fenetre.getEditorPresident().partieSauvegardee = false;
        _fenetre.getEditorPresident().window = _fenetre;
        _fenetre.getEditorPresident().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getEditorPresident().nickNames = _fenetre.getPseudosJoueurs();
        _fenetre.getEditorPresident().displayingPresident = _fenetre.getDisplayingPresident();
        _fenetre.getEditorPresident().setDialogue(true, 0, _fenetre);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        if (setToNullGame) {
            partie = null;
        }
    }

    @Override
    public String sauvegarder() {
        if(stack.taille()==0) {
            return validerEgalite();
        }
        return null;
    }

    @Override
    public void releverErreurs() {
        erreur(stack);
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCards _window) {
        getJt().removeAll();
        AbsPanel container_=_window.getCompoFactory().newBorder();
        initMessageName(_window);
        //Panneau Distribution
        initJt(getCompoFactory().newSpinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),_enabledChangingNbPlayers,_nbPlayers, _window);
        container_.add(getJt(),GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=_window.getCompoFactory().newLineBox();
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(getMessages().getVal(NEXT));
        bouton_.addActionListener(new ValidateRulesDealEvent(this, window));
        panneau_.add(bouton_);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }

    @Override
    public void validateRulesDeal(WindowCards _parent) {
        validateRules();
        getReglesPresident().setNbDeals(getNbGames().getValue());
        distribuer(_parent);
    }

    private void distribuer(WindowCards _parent) {
        getCardDialog().setTitle(getMessages().getVal(DEALING_CARDS));
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
        panneau_.add(getCompoFactory().newPlainLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
        liste.addItem(nickNames.getPseudo());
        for(String n: nickNames.getPseudosPresident()) {
            if (liste.getItemCount() == nbPlayers_) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(getMessages().getVal(RANDOM));
        panneau_.add(liste.self());
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_NORTH);
        pile_.sortCards(displayingPresident.isDecreasing(), false);
        PresidentCardsScrollableList plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,pile_.total(),getMessages().getVal(DEALING_STACK));
        plc_.setTriPresident(displayingPresident.getSuits(), displayingPresident.isDecreasing());
        plc_.iniPilePresident(pile_);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards=_parent.getCompoFactory().newLineBox();
        stack = plc_;
        panelsCards.add(plc_.getContainer());
        plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,getMessages().getVal(USER_HAND));
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriPresident(displayingPresident.getSuits(), displayingPresident.isDecreasing());
        panelsCards.add(plc_.getContainer());
        hands.clear();
        hands.add(plc_);
//        int i_=0;
        int h_ = 10*(nbCartesPJ_+6);
        for(String n: nickNames.getPseudosPresident()) {
            if (hands.size() == nbPlayers_) {
                break;
            }
//            if (i_ == nbJ_ - 1) {
//                break;
//            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            plc_=new PresidentCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,message_);
            plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriPresident(displayingPresident.getSuits(), displayingPresident.isDecreasing());
            panelsCards.add(plc_.getContainer());
            hands.add(plc_);
//            i_++;
        }
        AbsScrollPane scroll_ = _parent.getCompoFactory().newAbsScrollPane(panelsCards);
        scroll_.setPreferredSize(new MetaDimension(500, h_));
        panneau_=_parent.getCompoFactory().newBorder();
        panneau_.add(scroll_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel sousPanneau_=_parent.getCompoFactory().newLineBox();
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addActionListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
        listeTwo.addItem(getMessages().getVal(DEALING_STACK));
        listeTwo.addItem(getMessages().getVal(USER_HAND));
        for(String n: nickNames.getPseudosPresident()) {
            if (listeTwo.getItemCount() == getReglesPresident().getNbPlayers() + 1) {
                break;
            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        sousPanneau_.add(listeTwo.self());
        labelSelectCards = getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(getMessages().getVal(SELECTED_CARDS),nombreCartesSelectionnees));
        sousPanneau_.add(labelSelectCards);
        panneau_.add(sousPanneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);

        panneau_=_parent.getCompoFactory().newLineBox();
        bouton_=getCompoFactory().newPlainButton(getMessages().getVal(BACK));
        bouton_.addActionListener(new BackToRulesEvent(this, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(getMessages().getVal(SAVE_WITHOUT_CLOSING));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_WITHOUT_CLOSING, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(getMessages().getVal(SAVE_THEN_PLAY));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_PLAY, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(getMessages().getVal(PLAY_WITHOUT_SAVING));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.PLAY_WITHOUT_SAVING, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(getMessages().getVal(SAVE_THEN_CLOSE));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_CLOSE, _parent));
        panneau_.add(bouton_);
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(c);
        getCardDialog().pack();

    }

    @Override
    public void backToRules(WindowCards _parent) {
        nombreCartesSelectionneesPrecedent=0;
        nombreCartesSelectionnees = 0;
        partieSauvegardee=false;
        setDialogue(true,0, _parent);
    }

    private void erreur(PresidentCardsScrollableList _plc) {
        String lg_ = getMain().getLanguageKey();
        String mes_ = getMessages().getVal(ERROR_REPARTITION);
        mes_ = StringUtil.simpleNumberFormat(mes_, _plc.taille());
        getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), mes_, getMessages().getVal(ERROR_REPARTITION_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
        //JOptionPane.showMessageDialog(this,mes_,getMessages().getVal(ERROR_REPARTITION_TITLE), JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void cancelDeal() {
        partie = null;
    }

    @Override
    public void setPartie() {
        int nombreDeJoueurs_;

        CustList<HandPresident> mains_=new CustList<HandPresident>();
        CustList<CardsScrollableList> hands_ = getHands(false);
        for(CardsScrollableList l: hands_) {
            HandPresident m=new HandPresident();
            m.ajouterCartes(((PresidentCardsScrollableList) l).valMainPresident());
            m.sortCards(displayingPresident.isDecreasing(), false);
            mains_.add(m);
        }
        nombreDeJoueurs_=hands_.size();
        byte donneur_ = (byte) liste.getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)MonteCarloUtil.randomLong(nombreDeJoueurs_,getMain().getGenerator());
        }
        DealPresident donne_=new DealPresident(mains_,donneur_);
        partie = new GamePresident(GameType.EDIT,donne_,getReglesPresident(), new Bytes());
    }

    private String validerEgalite() {
        String fichier_=window.save(getCardDialog());
        if(!fichier_.isEmpty()) {
            validerSauvegarde(fichier_);
        }
        return fichier_;
    }

    /**Lorsqu'on veut sauvegarder une partie*/
    private void validerSauvegarde(String _s) {
        StreamTextFile.saveTextFile(_s, DocumentWriterPresidentUtil.setGamePresident(partie), window.getStreams());
    }

    @Override
    public void deplacerCartes() {
        String lg_ = getMain().getLanguageKey();
        HandPresident m=new HandPresident();
        for (CardsScrollableList l: getHands(true)) {
            PresidentCardsScrollableList c_ = (PresidentCardsScrollableList) l;
            HandPresident cartesSelectionnees_= c_.getCartesPresidentSelectionnees();
            m.ajouterCartes(cartesSelectionnees_);
        }
        int numero_= listeTwo.getSelectedIndex();
        PresidentCardsScrollableList panneauSelectionne_=(PresidentCardsScrollableList) getHands(true).get(numero_);
        int taille_=panneauSelectionne_.taille();
        int max_=panneauSelectionne_.getMax();
        if(taille_+m.total()<max_+1) {
            for (CardsScrollableList l: getHands(true)) {
                PresidentCardsScrollableList c_ = (PresidentCardsScrollableList) l;
                HandPresident cartesSelectionnees_= c_.getCartesPresidentSelectionnees();
                c_.supprimerCartesPresident(cartesSelectionnees_);
            }
            panneauSelectionne_.ajouterCartesPresident(m);
            nombreCartesSelectionnees=0;
        } else {
            String mes_ = getMessages().getVal(ERROR_MOVE);
            mes_ = StringUtil.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString((long)max_-taille_), listeTwo.getSelectedComboItem());
            getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), mes_, getMessages().getVal(ERROR_MOVE_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
        }
    }

    public static GamePresident getPartie(EditorPresident _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.partie;
    }

    @Override
    public void doNotSetToNullGame() {
        setToNullGame = false;
    }

    @Override
    public String getErrorSaveMessage() {
        return getMessages().getVal(ERROR_SAVE_FILE);
    }

    @Override
    public String getErrorSaveTitle() {
        return getMessages().getVal(ERROR_SAVE_FILE_TITLE);
    }

    @Override
    public boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }

    @Override
    public void setPartieSauvegardee(boolean _partieSauvegardee) {
        partieSauvegardee = _partieSauvegardee;
    }

    @Override
    public int getNombreCartesSelectionnees() {
        return nombreCartesSelectionnees;
    }

    @Override
    public void setNombreCartesSelectionnees(int _nombreCartesSelectionnees) {
        nombreCartesSelectionnees = _nombreCartesSelectionnees;
    }

    @Override
    public int getNombreCartesSelectionneesPrecedent() {
        return nombreCartesSelectionneesPrecedent;
    }

    @Override
    public void setNombreCartesSelectionneesPrecedent(
            int _nombreCartesSelectionneesPrecedent) {
        nombreCartesSelectionneesPrecedent = _nombreCartesSelectionneesPrecedent;
    }

    @Override
    public AbsPanel getPanelsCards() {
        return panelsCards;
    }

    @Override
    public AbsPlainLabel getLabelSelectCards() {
        return labelSelectCards;
    }

    @Override
    public CustList<CardsScrollableList> getHands(boolean _addStack) {
        CustList<CardsScrollableList> hands_;
        hands_ = new CustList<CardsScrollableList>();
        if (_addStack) {
            hands_.add(stack);
        }
        for (CardsScrollableList c: hands) {
            hands_.add(c);
        }
        return hands_;
    }
}
