package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import cards.belote.DealBelote;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
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
import cards.gui.panels.BeloteCardsScrollableList;
import cards.gui.panels.CardsScrollableList;
import code.gui.ConfirmDialog;
import code.gui.FileSaveDialog;
import code.gui.LabelButton;
import code.maths.montecarlo.AbMonteCarlo;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.ConstFiles;
import code.util.consts.Constants;

public final class EditorBelote extends DialogBelote implements SetterSelectedCardList{

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.EditorBelote";
    private static final EditorBelote DIALOG = new EditorBelote();
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
    private GameBelote partie;
    private int nombreCartesSelectionnees;
    private JPanel panelsCards;
    private BeloteCardsScrollableList stack;
    private CustList<BeloteCardsScrollableList> hands = new CustList<BeloteCardsScrollableList>();
    private BeloteCardsScrollableList remaining;
    private JLabel labelSelectCards;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private MainWindow window;
    private boolean setToNullGame;

    private EditorBelote() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initEditorBelote(MainWindow _fenetre) {
        //super(GameEnum.BELOTE.toString(),_fenetre,_fenetre.getReglesBelote());
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setTitle(GameEnum.BELOTE.toString());
        DIALOG.setReglesBelote(_fenetre.getReglesBelote());
        DIALOG.partie = null;
        DIALOG.setToNullGame = true;
        DIALOG.nombreCartesSelectionneesPrecedent = 0;
        DIALOG.nombreCartesSelectionnees = 0;
        DIALOG.partieSauvegardee = false;
        DIALOG.window = _fenetre;
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.nickNames = _fenetre.getPseudosJoueurs();
        DIALOG.displayingBelote = _fenetre.getDisplayingBelote();
        DIALOG.setDialogue();
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
//        erreur((BeloteCardsScrollableList)panelsCards.getComponent(0));
        erreur(stack);
    }
    @Override
    public void setDialogue() {
        getJt().removeAll();
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        initMessageName();
        Numbers<Integer> decks_ = new Numbers<Integer>();
        //Panneau Distribution
        for(int b=FileConst.MIN_DEALS;b<=FileConst.MAX_DEALS;b++) {
            decks_.add(b);
        }
        initJt(new JSpinner(new SpinnerListModel(decks_.toArray())));
        container_.add(getJt(),BorderLayout.CENTER);
        JPanel panneau_=new JPanel();
        LabelButton bouton_=new LabelButton(getMessages().getVal(NEXT));
        bouton_.addMouseListener(new ValidateRulesDealEvent(this));
        panneau_.add(bouton_);
        container_.add(panneau_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
    }
    @Override
    public void validateRulesDeal() {
        validateRules();
        getReglesBelote().setNombreParties((Integer) getNbGames().getValue());
        distribuer();
    }
    private String validerEgalite() {
        if (window.isSaveHomeFolder()) {
            FileSaveDialog.setFileSaveDialog(this, Constants.getLanguage(), true, FileConst.GAME_EXT, ConstFiles.getHomePath(), FileConst.EXCLUDED);
        } else {
            FileSaveDialog.setFileSaveDialog(this, Constants.getLanguage(), true, FileConst.GAME_EXT, EMPTY_STRING, FileConst.EXCLUDED);
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
    public static GameBelote getPartie() {
        DIALOG.setVisible(true);
        return DIALOG.partie;
    }

    private void distribuer() {
        setTitle(getMessages().getVal(DEALING_CARDS));
        Container c=new Container();
        c.setLayout(new BorderLayout());
        JPanel panneau_=new JPanel();

        panneau_=new JPanel();
        panneau_.add(new JLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox();
        liste.addItem(nickNames.getPseudo());
        int nbPlayers_ = getReglesBelote().getRepartition().getNombreJoueurs();
        for(String n: nickNames.getPseudosBelote()) {
            if (liste.getItemCount() == nbPlayers_) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(getMessages().getVal(RANDOM));
        panneau_.add(liste);
        c.add(panneau_,BorderLayout.NORTH);
        panneau_=new JPanel();
        panneau_.setLayout(new BorderLayout());
        panelsCards=new JPanel();
        HandBelote pile_=HandBelote.pileBase();
        pile_.trier(displayingBelote.getCouleurs(), displayingBelote.getDecroissant(), displayingBelote.getOrdreAvantEncheres());
        BeloteCardsScrollableList plc_=new BeloteCardsScrollableList(12,pile_.total(),getMessages().getVal(DEALING_STACK));
        plc_.initSelectionCarteBelote();
        plc_.setTriBelote(displayingBelote.getCouleurs(), displayingBelote.getOrdreAvantEncheres(), displayingBelote.getDecroissant());
        plc_.iniPileBelote(pile_);
        plc_.getListe().addListSelectionListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        stack = plc_;
        panelsCards.add(plc_);
//        hands.add(plc_);
        int firstCards_ = getReglesBelote().getRepartition().getFirstCards();
        int lastCards_ = getReglesBelote().getRepartition().getRemainingCards();
        plc_=new BeloteCardsScrollableList(firstCards_,firstCards_,getMessages().getVal(USER_HAND));
        plc_.initSelectionCarteBelote();
        plc_.getListe().addListSelectionListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriBelote(displayingBelote.getCouleurs(), displayingBelote.getOrdreAvantEncheres(), displayingBelote.getDecroissant());
        panelsCards.add(plc_);
        hands.clear();
        hands.add(plc_);
//        int i_=0;
//        int nbPlayers_ = getReglesBelote().getRepartition().getNombreJoueurs();
        for(String n: nickNames.getPseudosBelote()) {
            if (hands.size() == nbPlayers_) {
                break;
            }
//            if (i_ == nbPlayers_ - 1) {
//                break;
//            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringList.simpleStringsFormat(message_, n);
            plc_=new BeloteCardsScrollableList(firstCards_,firstCards_,message_);
            plc_.initSelectionCarteBelote();
            plc_.getListe().addListSelectionListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriBelote(displayingBelote.getCouleurs(), displayingBelote.getOrdreAvantEncheres(), displayingBelote.getDecroissant());
            panelsCards.add(plc_);
            hands.add(plc_);
//            i_++;
        }
        plc_=new BeloteCardsScrollableList(lastCards_,lastCards_,getMessages().getVal(REMAINING));
        plc_.initSelectionCarteBelote();
        plc_.getListe().addListSelectionListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards.add(plc_);
        remaining = plc_;
        panneau_.add(panelsCards,BorderLayout.CENTER);
        JPanel sousPanneau_=new JPanel();
        LabelButton bouton_=new LabelButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addMouseListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox();
        listeTwo.addItem(getMessages().getVal(DEALING_STACK));
        listeTwo.addItem(getMessages().getVal(USER_HAND));
        for(String n: nickNames.getPseudosBelote()) {
            if (listeTwo.getItemCount() == getReglesBelote().getRepartition().getNombreJoueurs() + 1) {
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
        panneau_=new JPanel();
        bouton_=new LabelButton(getMessages().getVal(BACK));
        bouton_.addMouseListener(new BackToRulesEvent(this));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(SAVE_WITHOUT_CLOSING));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.SAVE_WITHOUT_CLOSING));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(SAVE_THEN_PLAY));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_PLAY));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(PLAY_WITHOUT_SAVING));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.PLAY_WITHOUT_SAVING));
        panneau_.add(bouton_);
        bouton_=new LabelButton(getMessages().getVal(SAVE_THEN_CLOSE));
        bouton_.addMouseListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_CLOSE));
        panneau_.add(bouton_);
        c.add(panneau_,BorderLayout.SOUTH);
        setContentPane(c);
        pack();
    }
    @Override
    public void backToRules() {
        nombreCartesSelectionnees=0;
        nombreCartesSelectionneesPrecedent=0;
        partieSauvegardee=false;
        setDialogue();
    }
    @Override
    public void cancelDeal() {
        partie = null;
    }
    @Override
    public void setPartie() {
//        BeloteCardsScrollableList plc_;
//        int nombreDeMains_=panelsCards.getComponentCount();
        int nombreDeJoueurs_;

        EqList<HandBelote> mains_=new EqList<HandBelote>();
        for (BeloteCardsScrollableList l: hands) {
            HandBelote m=new HandBelote();
            m.ajouterCartes(l.valMainBelote());
            m.setOrdre(displayingBelote.getOrdreAvantEncheres());
            m.trier(displayingBelote.getCouleurs(), displayingBelote.getDecroissant(), displayingBelote.getOrdreAvantEncheres());
            mains_.add(m);
        }
        HandBelote m=new HandBelote();
        m.ajouterCartes(remaining.valMainBelote());
        m.setOrdre(displayingBelote.getOrdreAvantEncheres());
        mains_.add(m);
//        for(int i=1;i<nombreDeMains_;i++) {
//            plc_=(BeloteCardsScrollableList)panelsCards.getComponent(i);
//            HandBelote m=new HandBelote();
//            m.ajouterCartes(plc_.valMainBelote());
//            m.setOrdre(displayingBelote.getOrdreAvantEncheres());
//            if(i<nombreDeMains_-1) {//On trie toutes les mains sauf le talon car l'ordre des cartes au talon est important
//                m.trier(displayingBelote.getCouleurs(), displayingBelote.getDecroissant(), displayingBelote.getOrdreAvantEncheres());
//            }
//            mains_.add(m);
//        }
//        nombreDeJoueurs_=nombreDeMains_-1;
        nombreDeJoueurs_=getHands(false).size();
        byte donneur_ = (byte) liste.getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//          donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)AbMonteCarlo.randomInt(nombreDeJoueurs_);
        }
        DealBelote donne_=new DealBelote(mains_,donneur_);
        partie = new GameBelote(GameType.EDIT,donne_,getReglesBelote());

    }
    /**Lorsqu'on veut sauvegarder une partie*/
    private void validerSauvegarde(String _s) {
        StreamTextFile.saveObject(_s, partie);
    }
    private void erreur(BeloteCardsScrollableList _plc) {
        String mes_ = getMessages().getVal(ERROR_REPARTITION);
        mes_ = StringList.simpleNumberFormat(mes_, _plc.taille());
        ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_REPARTITION_TITLE), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
        //JOptionPane.showMessageDialog(this,mes_,getMessages().getVal(ERROR_REPARTITION_TITLE), JOptionPane.ERROR_MESSAGE);
    }
    @Override
    public void deplacerCartes() {
//        int nombreDeMains_=panelsCards.getComponentCount();

        HandBelote m=new HandBelote(displayingBelote.getOrdreAvantEncheres());
//        for (int i = List.FIRST_INDEX;i<nombreDeMains_;i++) {
//            HandBelote cartesSelectionnees_=((BeloteCardsScrollableList)panelsCards.getComponent(i)).getCartesBeloteSelectionnees();
//            m.ajouterCartes(cartesSelectionnees_);
//        }
        for (CardsScrollableList l: getHands(true)) {
            BeloteCardsScrollableList c_ = (BeloteCardsScrollableList) l;
            HandBelote cartesSelectionnees_= c_.getCartesBeloteSelectionnees();
            m.ajouterCartes(cartesSelectionnees_);
        }
        int numero_=listeTwo.getSelectedIndex();
        BeloteCardsScrollableList panneauSelectionne_=(BeloteCardsScrollableList) getHands(true).get(numero_);
        //(BeloteCardsScrollableList)panelsCards.getComponent(numero_);
//        BeloteCardsScrollableList panneau2_;
        int taille_= panneauSelectionne_.taille();
        int max_=panneauSelectionne_.getMax();
        if(taille_+m.total()<max_+1) {
//            for (int i = List.FIRST_INDEX;i<nombreDeMains_;i++) {
//                panneau2_=(BeloteCardsScrollableList)panelsCards.getComponent(i);
//                HandBelote cartesSelectionnees_=panneau2_.getCartesBeloteSelectionnees();
//                panneau2_.supprimerCartesBelote(cartesSelectionnees_);
//            }
            for (CardsScrollableList l: getHands(true)) {
                BeloteCardsScrollableList c_ = (BeloteCardsScrollableList) l;
                HandBelote cartesSelectionnees_=c_.getCartesBeloteSelectionnees();
                c_.supprimerCartesBelote(cartesSelectionnees_);
            }
            if(numero_ != getHands(false).size()) {
                panneauSelectionne_.ajouterCartesBelote(m);
            } else {
                panneauSelectionne_.ajouterCartesBeloteFin(m);
            }
            nombreCartesSelectionnees=0;
        } else {
            String mes_ = getMessages().getVal(ERROR_MOVE);
            mes_ = StringList.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString(max_-taille_), listeTwo.getSelectedComboItem());
            ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_MOVE_TITLE), Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,mes_, getMessages().getVal(ERROR_MOVE_TITLE), JOptionPane.ERROR_MESSAGE);
        }

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
    public JPanel getPanelsCards() {
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
        hands_.add(remaining);
        return hands_;
    }
}
