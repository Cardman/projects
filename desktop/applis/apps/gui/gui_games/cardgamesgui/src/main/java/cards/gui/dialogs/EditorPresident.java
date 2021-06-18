package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import cards.consts.GameType;
import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.dialogs.enums.SaveDealMode;
import cards.gui.dialogs.events.BackToRulesEvent;
import cards.gui.dialogs.events.ListenerClickCardsList;
import cards.gui.dialogs.events.MoveCardsEvent;
import cards.gui.dialogs.events.SavingDealEvent;
import cards.gui.dialogs.events.ValidateRulesDealEvent;
import cards.gui.panels.CardsScrollableList;
import cards.gui.panels.PresidentCardsScrollableList;
import cards.president.DealPresident;
import cards.president.DisplayingPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import code.gui.*;
import code.maths.montecarlo.MonteCarloUtil;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.*;
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
    private Panel panelsCards;
    private PresidentCardsScrollableList stack;
    private final CustList<PresidentCardsScrollableList> hands = new CustList<PresidentCardsScrollableList>();
    private TextLabel labelSelectCards;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private MainWindow window;
    private boolean setToNullGame;

    public EditorPresident() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void initEditorPresident(MainWindow _fenetre) {
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorPresident().setMain(_fenetre);
        _fenetre.getEditorPresident().setDialogIcon(_fenetre);
        _fenetre.getEditorPresident().setTitle(GameEnum.PRESIDENT.toString(lg_));
        _fenetre.getEditorPresident().setReglesPresident(_fenetre.getReglesPresident());
        _fenetre.getEditorPresident().partie = null;
        _fenetre.getEditorPresident().setToNullGame = true;
        _fenetre.getEditorPresident().nombreCartesSelectionneesPrecedent = 0;
        _fenetre.getEditorPresident().nombreCartesSelectionnees = 0;
        _fenetre.getEditorPresident().partieSauvegardee = false;
        _fenetre.getEditorPresident().window = _fenetre;
        _fenetre.getEditorPresident().setLocationRelativeTo(_fenetre);
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
    public void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, MainWindow _window) {
        getJt().removeAll();
        Panel container_=Panel.newBorder();
        initMessageName(_window);
        //Panneau Distribution
        initJt(new Spinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),_enabledChangingNbPlayers,_nbPlayers, _window);
        container_.add(getJt(),BorderLayout.CENTER);
        Panel panneau_=Panel.newLineBox();
        LabelButton bouton_=new LabelButton(getMessages().getVal(NEXT));
        bouton_.addMouseListener(new ValidateRulesDealEvent(this, window));
        panneau_.add(bouton_);
        container_.add(panneau_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
    }

    @Override
    public void validateRulesDeal(MainWindow _parent) {
        validateRules();
        getReglesPresident().setNbDeals(getNbGames().getValue());
        distribuer(_parent);
    }

    private void distribuer(MainWindow _parent) {
        setTitle(getMessages().getVal(DEALING_CARDS));
        Panel c=Panel.newBorder();
        Panel panneau_=Panel.newLineBox();
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
        panneau_.add(new TextLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(new StringList(new IntTreeMap<String>().values()), 0));
        liste.addItem(nickNames.getPseudo());
        for(String n: nickNames.getPseudosPresident()) {
            if (liste.getCombo().getItemCount() == nbPlayers_) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(getMessages().getVal(RANDOM));
        panneau_.add(liste.getCombo().self());
        c.add(panneau_,BorderLayout.NORTH);
        pile_.sortCards(displayingPresident.isDecreasing(), false);
        PresidentCardsScrollableList plc_=new PresidentCardsScrollableList(nbCartesPJ_,pile_.total(),getMessages().getVal(DEALING_STACK), _parent.getCardFactories().getGenePresident().create(false));
        plc_.initSelectionCartePresident(_parent);
        plc_.setTriPresident(displayingPresident.getSuits(), displayingPresident.isDecreasing());
        plc_.iniPilePresident(pile_);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards=Panel.newLineBox();
        stack = plc_;
        panelsCards.add(plc_.getContainer());
        plc_=new PresidentCardsScrollableList(nbCartesPJ_,nbCartesPJ_,getMessages().getVal(USER_HAND), _parent.getCardFactories().getGenePresident().create(false));
        plc_.initSelectionCartePresident(_parent);
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
            plc_=new PresidentCardsScrollableList(nbCartesPJ_,nbCartesPJ_,message_, _parent.getCardFactories().getGenePresident().create(false));
            plc_.initSelectionCartePresident(_parent);
            plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriPresident(displayingPresident.getSuits(), displayingPresident.isDecreasing());
            panelsCards.add(plc_.getContainer());
            hands.add(plc_);
//            i_++;
        }
        ScrollPane scroll_ = new ScrollPane(panelsCards);
        scroll_.setPreferredSize(new Dimension(500, h_));
        panneau_=Panel.newBorder();
        panneau_.add(scroll_,BorderLayout.CENTER);
        Panel sousPanneau_=Panel.newLineBox();
        LabelButton bouton_=new LabelButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addMouseListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(new StringList(new IntTreeMap<String>().values()), 0));
        listeTwo.addItem(getMessages().getVal(DEALING_STACK));
        listeTwo.addItem(getMessages().getVal(USER_HAND));
        for(String n: nickNames.getPseudosPresident()) {
            if (listeTwo.getCombo().getItemCount() == getReglesPresident().getNbPlayers() + 1) {
                break;
            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        sousPanneau_.add(listeTwo.getCombo().self());
        labelSelectCards = new TextLabel(StringUtil.simpleNumberFormat(getMessages().getVal(SELECTED_CARDS),nombreCartesSelectionnees));
        sousPanneau_.add(labelSelectCards);
        panneau_.add(sousPanneau_,BorderLayout.SOUTH);
        c.add(panneau_,BorderLayout.CENTER);

        panneau_=Panel.newLineBox();
        bouton_=new LabelButton(getMessages().getVal(BACK));
        bouton_.addMouseListener(new BackToRulesEvent(this, _parent));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(SAVE_WITHOUT_CLOSING));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.SAVE_WITHOUT_CLOSING, _parent));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(SAVE_THEN_PLAY));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_PLAY, _parent));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(PLAY_WITHOUT_SAVING));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.PLAY_WITHOUT_SAVING, _parent));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(SAVE_THEN_CLOSE));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_CLOSE, _parent));
        panneau_.add(bouton_);
        c.add(panneau_,BorderLayout.SOUTH);
        setContentPane(c);
        pack();

    }

    @Override
    public void backToRules(MainWindow _parent) {
        nombreCartesSelectionneesPrecedent=0;
        nombreCartesSelectionnees = 0;
        partieSauvegardee=false;
        setDialogue(true,0, _parent);
    }

    private void erreur(PresidentCardsScrollableList _plc) {
        String lg_ = getMain().getLanguageKey();
        String mes_ = getMessages().getVal(ERROR_REPARTITION);
        mes_ = StringUtil.simpleNumberFormat(mes_, _plc.taille());
        ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_REPARTITION_TITLE), lg_, JOptionPane.ERROR_MESSAGE, getMain().getConfirmDialog());
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
        byte donneur_ = (byte) liste.getCombo().getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)MonteCarloUtil.randomLong(nombreDeJoueurs_,getMain().getGenerator());
        }
        DealPresident donne_=new DealPresident(mains_,donneur_);
        partie = new GamePresident(GameType.EDIT,donne_,getReglesPresident(), new Bytes());
    }

    private String validerEgalite() {
        String lg_ = window.getLanguageKey();
        if (window.isSaveHomeFolder()) {
            FileSaveDialog.setFileSaveDialog(window,this, lg_, true, FileConst.GAME_EXT, window.getFrames().getHomePath(), window.getFrames().getHomePath(), window, FileConst.EXCLUDED);
        } else {
            FileSaveDialog.setFileSaveDialog(window,this, lg_, true, FileConst.GAME_EXT, EMPTY_STRING, window.getFrames().getHomePath(), window, FileConst.EXCLUDED);
        }
        String fichier_=FileSaveDialog.getStaticSelectedPath(window.getFileSaveDialog());
        if (fichier_ == null) {
            fichier_ = EMPTY_STRING;
        }
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
        int numero_= listeTwo.getCombo().getSelectedIndex();
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
            ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_MOVE_TITLE), lg_, JOptionPane.ERROR_MESSAGE, getMain().getConfirmDialog());
        }
    }

    public static GamePresident getPartie(EditorPresident _dialog) {
        _dialog.setVisible(true);
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
    public Panel getPanelsCards() {
        return panelsCards;
    }

    @Override
    public TextLabel getLabelSelectCards() {
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
