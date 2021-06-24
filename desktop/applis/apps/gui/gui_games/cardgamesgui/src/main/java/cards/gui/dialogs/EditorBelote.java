package cards.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.*;

import cards.belote.DealBelote;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
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
import code.gui.*;
import code.maths.montecarlo.MonteCarloUtil;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class EditorBelote extends DialogBelote implements SetterSelectedCardList{

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.editorbelote";
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
    private static final String CST_REMAINING = "remaining";
    private static final String SAVE_THEN_CLOSE = "saveThenClose";
    private static final String SAVE_THEN_PLAY = "saveThenPlay";
    private static final String SAVE_WITHOUT_CLOSING = "saveWithoutClosing";
    private static final String SELECTED_CARDS = "selectedCards";
    private static final String USER_HAND = "userHand";
    private static final String EMPTY_STRING = "";
    private boolean partieSauvegardee;
    private GameBelote partie;
    private int nombreCartesSelectionnees;
    private Panel panelsCards;
    private BeloteCardsScrollableList stack;
    private final CustList<BeloteCardsScrollableList> hands = new CustList<BeloteCardsScrollableList>();
    private BeloteCardsScrollableList remaining;
    private TextLabel labelSelectCards;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private MainWindow window;
    private boolean setToNullGame;

    public EditorBelote() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initEditorBelote(MainWindow _fenetre) {
        //super(GameEnum.BELOTE.toString(),_fenetre,_fenetre.getReglesBelote());
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorBelote().setMain(_fenetre);
        _fenetre.getEditorBelote().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getEditorBelote().setTitle(GameEnum.BELOTE.toString(lg_));
        _fenetre.getEditorBelote().setReglesBelote(_fenetre.getReglesBelote());
        _fenetre.getEditorBelote().partie = null;
        _fenetre.getEditorBelote().setToNullGame = true;
        _fenetre.getEditorBelote().nombreCartesSelectionneesPrecedent = 0;
        _fenetre.getEditorBelote().nombreCartesSelectionnees = 0;
        _fenetre.getEditorBelote().partieSauvegardee = false;
        _fenetre.getEditorBelote().window = _fenetre;
        _fenetre.getEditorBelote().setLocationRelativeTo(_fenetre);
        _fenetre.getEditorBelote().nickNames = _fenetre.getPseudosJoueurs();
        _fenetre.getEditorBelote().displayingBelote = _fenetre.getDisplayingBelote();
        _fenetre.getEditorBelote().setDialogue(_fenetre);
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
    public void setDialogue(MainWindow _parent) {
        getJt().removeAll();
        Panel container_=Panel.newBorder();
        initMessageName(_parent);
        //Panneau Distribution
        String lg_ = _parent.getLanguageKey();
        initJt(_parent,new Spinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),lg_);
        container_.add(getJt(),BorderLayout.CENTER);
        Panel panneau_=Panel.newLineBox();
        LabelButton bouton_=new LabelButton(getMessages().getVal(NEXT));
        bouton_.addMouseListener(new ValidateRulesDealEvent(this, _parent));
        panneau_.add(bouton_);
        container_.add(panneau_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
    }
    @Override
    public void validateRulesDeal(MainWindow _parent) {
        validateRules();
        getReglesBelote().setNombreParties(getNbGames().getValue());
        distribuer(_parent);
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
    public static GameBelote getPartie(EditorBelote _dialog) {
        _dialog.setVisible(true);
        return _dialog.partie;
    }

    private void distribuer(MainWindow _parent) {
        setTitle(getMessages().getVal(DEALING_CARDS));
        Panel c=Panel.newBorder();
        Panel panneau_=Panel.newLineBox();
        panneau_.add(new TextLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0));
        liste.addItem(nickNames.getPseudo());
        int nbPlayers_ = getReglesBelote().getRepartition().getNombreJoueurs();
        for(String n: nickNames.getPseudosBelote()) {
            if (liste.getItemCount() == nbPlayers_) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(getMessages().getVal(RANDOM));
        panneau_.add(liste.self());
        c.add(panneau_,BorderLayout.NORTH);
        panneau_=Panel.newBorder();
        panelsCards=Panel.newLineBox();
        HandBelote pile_=HandBelote.pileBase();
        pile_.trier(displayingBelote.getSuits(), displayingBelote.isDecreasing(), displayingBelote.getOrderBeforeBids());
        BeloteCardsScrollableList plc_=new BeloteCardsScrollableList(12,pile_.total(),getMessages().getVal(DEALING_STACK), _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
        plc_.initSelectionCarteBelote(_parent);
        plc_.setTriBelote(displayingBelote.getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.isDecreasing());
        plc_.iniPileBelote(pile_);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        stack = plc_;
        panelsCards.add(plc_.getContainer());
//        hands.add(plc_);
        int firstCards_ = getReglesBelote().getRepartition().getFirstCards();
        int lastCards_ = getReglesBelote().getRepartition().getRemainingCards();
        plc_=new BeloteCardsScrollableList(firstCards_,firstCards_,getMessages().getVal(USER_HAND), _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
        plc_.initSelectionCarteBelote(_parent);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriBelote(displayingBelote.getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.isDecreasing());
        panelsCards.add(plc_.getContainer());
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
            message_ = StringUtil.simpleStringsFormat(message_, n);
            plc_=new BeloteCardsScrollableList(firstCards_,firstCards_,message_, _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
            plc_.initSelectionCarteBelote(_parent);
            plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriBelote(displayingBelote.getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.isDecreasing());
            panelsCards.add(plc_.getContainer());
            hands.add(plc_);
//            i_++;
        }
        plc_=new BeloteCardsScrollableList(lastCards_,lastCards_,getMessages().getVal(CST_REMAINING), _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
        plc_.initSelectionCarteBelote(_parent);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards.add(plc_.getContainer());
        remaining = plc_;
        panneau_.add(panelsCards,BorderLayout.CENTER);
        Panel sousPanneau_=Panel.newLineBox();
        LabelButton bouton_=new LabelButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addMouseListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0));
        listeTwo.addItem(getMessages().getVal(DEALING_STACK));
        listeTwo.addItem(getMessages().getVal(USER_HAND));
        for(String n: nickNames.getPseudosBelote()) {
            if (listeTwo.getItemCount() == getReglesBelote().getRepartition().getNombreJoueurs() + 1) {
                break;
            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        listeTwo.addItem(getMessages().getVal(CST_REMAINING));
        sousPanneau_.add(listeTwo.self());
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
        nombreCartesSelectionnees=0;
        nombreCartesSelectionneesPrecedent=0;
        partieSauvegardee=false;
        setDialogue(_parent);
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

        CustList<HandBelote> mains_=new CustList<HandBelote>();
        for (BeloteCardsScrollableList l: hands) {
            HandBelote m=new HandBelote();
            m.ajouterCartes(l.valMainBelote());
            m.setOrdre(displayingBelote.getOrderBeforeBids());
            m.trier(displayingBelote.getSuits(), displayingBelote.isDecreasing(), displayingBelote.getOrderBeforeBids());
            mains_.add(m);
        }
        HandBelote m=new HandBelote();
        m.ajouterCartes(remaining.valMainBelote());
        m.setOrdre(displayingBelote.getOrderBeforeBids());
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
            donneur_=(byte)MonteCarloUtil.randomLong(nombreDeJoueurs_,getMain().getGenerator());
        }
        DealBelote donne_=new DealBelote(mains_,donneur_);
        partie = new GameBelote(GameType.EDIT,donne_,getReglesBelote());

    }
    /**Lorsqu'on veut sauvegarder une partie*/
    private void validerSauvegarde(String _s) {
        StreamTextFile.saveTextFile(_s, DocumentWriterBeloteUtil.setGameBelote(partie), window.getStreams());
    }
    private void erreur(BeloteCardsScrollableList _plc) {
        String lg_ = getMain().getLanguageKey();
        String mes_ = getMessages().getVal(ERROR_REPARTITION);
        mes_ = StringUtil.simpleNumberFormat(mes_, _plc.taille());
        ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_REPARTITION_TITLE), lg_, JOptionPane.ERROR_MESSAGE, getMain().getConfirmDialog());
        //JOptionPane.showMessageDialog(this,mes_,getMessages().getVal(ERROR_REPARTITION_TITLE), JOptionPane.ERROR_MESSAGE);
    }
    @Override
    public void deplacerCartes() {
        String lg_ = getMain().getLanguageKey();
//        int nombreDeMains_=panelsCards.getComponentCount();

        HandBelote m=new HandBelote(displayingBelote.getOrderBeforeBids());
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
            mes_ = StringUtil.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString((long)max_-taille_), listeTwo.getSelectedComboItem());
            ConfirmDialog.showMessage(this, mes_, getMessages().getVal(ERROR_MOVE_TITLE), lg_, JOptionPane.ERROR_MESSAGE, getMain().getConfirmDialog());
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
        hands_.add(remaining);
        return hands_;
    }
}
