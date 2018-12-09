package cards.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

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
import cards.gui.panels.TarotCardsScrollableList;
import cards.tarot.DealTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.ConfirmDialog;
import code.gui.FileSaveDialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.maths.montecarlo.AbMonteCarlo;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.ConstFiles;

public final class EditorTarot extends DialogTarot implements SetterSelectedCardList {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.editortarot";

    private static final EditorTarot DIALOG = new EditorTarot();
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
    private static final String REMAINING = "remaining";
    private static final String SAVE_THEN_CLOSE = "saveThenClose";
    private static final String SAVE_THEN_PLAY = "saveThenPlay";
    private static final String SAVE_WITHOUT_CLOSING = "saveWithoutClosing";
    private static final String SELECTED_CARDS = "selectedCards";
    private static final String USER_HAND = "userHand";
    private static final String EMPTY_STRING = "";
    private boolean partieSauvegardee;
    private GameTarot partie;
    private Panel panelsCards;
    private TarotCardsScrollableList stack;
    private CustList<TarotCardsScrollableList> hands = new CustList<TarotCardsScrollableList>();
    private TarotCardsScrollableList dog;
    private JLabel labelSelectCards;
    private int nombreCartesSelectionnees;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private boolean setToNullGame;

    private DisplayingTarot displayingTarot = new DisplayingTarot();
    private MainWindow window;
    private EditorTarot() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initEditorTarot(MainWindow _fenetre) {
        String lg_ = _fenetre.getLanguageKey();
        DIALOG.setMain(_fenetre);
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setTitle(GameEnum.TAROT.toString(lg_));
        DIALOG.setReglesTarot(_fenetre.getReglesTarot());
        DIALOG.partie = null;
        DIALOG.setToNullGame = true;
        DIALOG.nombreCartesSelectionneesPrecedent = 0;
        DIALOG.nombreCartesSelectionnees = 0;
        DIALOG.partieSauvegardee = false;
        DIALOG.window = _fenetre;
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.nickNames = _fenetre.getPseudosJoueurs();
        DIALOG.displayingTarot = _fenetre.getDisplayingTarot();
        DIALOG.setDialogue(true, 0, _fenetre);
//        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent _e) {
//                partie = null;
//                dispose();
//            }
//        });
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
//        erreur((TarotCardsScrollableList)panelsCards.getComponent(0));
        erreur(stack);
    }
    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, MainWindow _window) {
        getJt().removeAll();
        Panel container_=new Panel();
        container_.setLayout(new BorderLayout());
        initMessageName(_window);
        Numbers<Integer> decks_ = new Numbers<Integer>();
        //Panneau Distribution
        for(int b=FileConst.MIN_DEALS;b<=FileConst.MAX_DEALS;b++) {
            decks_.add(b);
        }
        initJt(new JSpinner(new SpinnerListModel(decks_.toArray())),_enabledChangingNbPlayers,_nbPlayers, _window);
        container_.add(getJt(),BorderLayout.CENTER);
        Panel panneau_=new Panel();
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
        getReglesTarot().setNombreParties((Integer)getNbGames().getValue());
        distribuer(_parent);
    }
    private void distribuer(MainWindow _parent) {

        setTitle(getMessages().getVal(DEALING_CARDS));
        Panel c=new Panel();
        c.setLayout(new BorderLayout());
        Panel panneau_=new Panel();
//        byte nbJ_=(byte) getReglesTarot().getRepartition().getNombreJoueurs();
        byte nbCartesPJ_ = (byte) getReglesTarot().getRepartition().getNombreCartesParJoueur();
        byte nbCartesC_ = (byte) getReglesTarot().getRepartition().getNombreCartesChien();

        HandTarot pile_=HandTarot.pileBase();
        panneau_=new Panel();
        panneau_.add(new JLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox();
        liste.addItem(nickNames.getPseudo());
        int nbPlayers_ = getReglesTarot().getRepartition().getNombreJoueurs();
        for(String n: nickNames.getPseudosTarot()) {
            if (liste.getItemCount() == nbPlayers_) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(getMessages().getVal(RANDOM));
        panneau_.add(liste);
        c.add(panneau_,BorderLayout.NORTH);
        pile_.trier(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
        TarotCardsScrollableList plc_=new TarotCardsScrollableList(nbCartesPJ_,pile_.total(),getMessages().getVal(DEALING_STACK));
        plc_.setTriTarot(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
        plc_.iniPileTarot(pile_);
        plc_.initSelectionCarteTarot(_parent);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards=new Panel();
        stack = plc_;
        panelsCards.add(plc_);
        plc_=new TarotCardsScrollableList(nbCartesPJ_,nbCartesPJ_,getMessages().getVal(USER_HAND));
        plc_.initSelectionCarteTarot(_parent);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriTarot(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
        panelsCards.add(plc_);
        hands.clear();
        hands.add(plc_);
//        int i_=0;
        for(String n: nickNames.getPseudosTarot()) {
            if (hands.size() == nbPlayers_) {
                break;
            }
//            if (i_ == nbJ_ - 1) {
//                break;
//            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringList.simpleStringsFormat(message_, n);
            plc_=new TarotCardsScrollableList(nbCartesPJ_,nbCartesPJ_,message_);
            plc_.initSelectionCarteTarot(_parent);
            plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriTarot(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
            panelsCards.add(plc_);
            hands.add(plc_);
//            i_++;
        }
        plc_=new TarotCardsScrollableList(nbCartesC_,nbCartesC_,getMessages().getVal(REMAINING));
        plc_.initSelectionCarteTarot(_parent);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriTarot(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
        panelsCards.add(plc_);
        dog = plc_;
        panneau_=new Panel();
        panneau_.setLayout(new BorderLayout());
        panneau_.add(panelsCards,BorderLayout.CENTER);
        Panel sousPanneau_=new Panel();
        LabelButton bouton_=new LabelButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addMouseListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox();
        listeTwo.addItem(getMessages().getVal(DEALING_STACK));
        listeTwo.addItem(getMessages().getVal(USER_HAND));
        for(String n: nickNames.getPseudosTarot()) {
            if (listeTwo.getItemCount() == getReglesTarot().getRepartition().getNombreJoueurs() + 1) {
                break;
            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringList.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        listeTwo.addItem(getMessages().getVal(REMAINING));
        sousPanneau_.add(listeTwo);
        labelSelectCards = new JLabel(StringList.simpleNumberFormat(getMessages().getVal(SELECTED_CARDS),nombreCartesSelectionnees));
        sousPanneau_.add(labelSelectCards);
        panneau_.add(sousPanneau_,BorderLayout.SOUTH);
        c.add(panneau_,BorderLayout.CENTER);

        panneau_=new Panel();
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
    private void erreur(TarotCardsScrollableList _plc) {
        String lg_ = getMain().getLanguageKey();
        String mes_ = getMessages().getVal(ERROR_REPARTITION);
        mes_ = StringList.simpleNumberFormat(mes_, _plc.taille());
        ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_REPARTITION_TITLE), lg_, JOptionPane.ERROR_MESSAGE);
        //JOptionPane.showMessageDialog(this,mes_,getMessages().getVal(ERROR_REPARTITION_TITLE), JOptionPane.ERROR_MESSAGE);
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

        EqList<HandTarot> mains_=new EqList<HandTarot>();
//        for(int i=1;i<nombreDeMains_;i++) {
//            plc_=(TarotCardsScrollableList)panelsCards.getComponent(i);
//            HandTarot m=new HandTarot();
//            m.ajouterCartes(plc_.valMainTarot());
//            m.trier(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
//            mains_.add(m);
//        }
        CustList<CardsScrollableList> hands_ = getHands(false);
        for(CardsScrollableList l: hands_) {
//            plc_=(TarotCardsScrollableList)panelsCards.getComponent(i);
            HandTarot m=new HandTarot();
            m.ajouterCartes(((TarotCardsScrollableList) l).valMainTarot());
            m.trier(displayingTarot.getCouleurs(), displayingTarot.getDecroissant());
            mains_.add(m);
        }
//        nombreDeJoueurs_=nombreDeMains_-1;
        nombreDeJoueurs_=hands_.size();
        byte donneur_ = (byte) liste.getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)AbMonteCarlo.randomInt(nombreDeJoueurs_);
        }
        DealTarot donne_=new DealTarot(mains_,donneur_);
        partie = new GameTarot(GameType.EDIT,donne_,getReglesTarot());
    }
    private String validerEgalite() {
        String lg_ = window.getLanguageKey();
        if (window.isSaveHomeFolder()) {
            FileSaveDialog.setFileSaveDialog(window,this, lg_, true, FileConst.GAME_EXT, ConstFiles.getHomePath(), FileConst.EXCLUDED);
        } else {
            FileSaveDialog.setFileSaveDialog(window,this, lg_, true, FileConst.GAME_EXT, EMPTY_STRING, FileConst.EXCLUDED);
        }
        String fichier_=FileSaveDialog.getStaticSelectedPath();
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
        StreamTextFile.saveTextFile(_s, DocumentWriterTarotUtil.setGameTarot(partie));
    }
    @Override
    public void deplacerCartes() {
        String lg_ = getMain().getLanguageKey();
//        int nombreDeMains_=panelsCards.getComponentCount();

        HandTarot m=new HandTarot();
//        for (int i = List.FIRST_INDEX;i<nombreDeMains_;i++) {
//            HandTarot cartesSelectionnees_=((TarotCardsScrollableList)panelsCards.getComponent(i)).getCartesTarotSelectionnees();
//            m.ajouterCartes(cartesSelectionnees_);
//        }
        for (CardsScrollableList l: getHands(true)) {
            TarotCardsScrollableList c_ = (TarotCardsScrollableList) l;
            HandTarot cartesSelectionnees_= c_.getCartesTarotSelectionnees();
            m.ajouterCartes(cartesSelectionnees_);
        }
        int numero_= listeTwo.getSelectedIndex();
        TarotCardsScrollableList panneauSelectionne_=(TarotCardsScrollableList)getHands(true).get(numero_);
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
            for (CardsScrollableList l: getHands(true)) {
                TarotCardsScrollableList c_ = (TarotCardsScrollableList) l;
//                panneau2_= l;
//                HandTarot cartesSelectionnees_=((TarotCardsScrollableList)panelsCards.getComponent(i)).getCartesTarotSelectionnees();
                HandTarot cartesSelectionnees_= c_.getCartesTarotSelectionnees();
                c_.supprimerCartesTarot(cartesSelectionnees_);
            }
            panneauSelectionne_.ajouterCartesTarot(m);
            nombreCartesSelectionnees=0;
        } else {
            String mes_ = getMessages().getVal(ERROR_MOVE);
            mes_ = StringList.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString(max_-taille_), listeTwo.getSelectedComboItem());
            ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_MOVE_TITLE), lg_, JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,mes_, getMessages().getVal(ERROR_MOVE_TITLE), JOptionPane.ERROR_MESSAGE);
        }


    }
    public static GameTarot getPartie() {
        DIALOG.setVisible(true);
        return DIALOG.partie;
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
    public JLabel getLabelSelectCards() {
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
        hands_.add(dog);
        return hands_;
    }
}
