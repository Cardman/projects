package cards.gui.dialogs;
import java.awt.BorderLayout;



import cards.belote.DealBelote;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.GameType;
import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
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
import code.gui.initialize.AbstractProgramInfos;
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
    private AbsPanel panelsCards;
    private BeloteCardsScrollableList stack;
    private final CustList<BeloteCardsScrollableList> hands = new CustList<BeloteCardsScrollableList>();
    private BeloteCardsScrollableList remaining;
    private AbsPlainLabel labelSelectCards;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private WindowCards window;
    private boolean setToNullGame;

    public EditorBelote(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initEditorBelote(WindowCards _fenetre) {
        //super(GameEnum.BELOTE.toString(),_fenetre,_fenetre.getReglesBelote());
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorBelote().setMain(_fenetre);
        _fenetre.getEditorBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getEditorBelote().getCardDialog().setTitle(GameEnum.BELOTE.toString(lg_));
        _fenetre.getEditorBelote().setReglesBelote(_fenetre.getReglesBelote());
        _fenetre.getEditorBelote().partie = null;
        _fenetre.getEditorBelote().setToNullGame = true;
        _fenetre.getEditorBelote().nombreCartesSelectionneesPrecedent = 0;
        _fenetre.getEditorBelote().nombreCartesSelectionnees = 0;
        _fenetre.getEditorBelote().partieSauvegardee = false;
        _fenetre.getEditorBelote().window = _fenetre;
        _fenetre.getEditorBelote().getCardDialog().setLocationRelativeTo(_fenetre);
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
    public void setDialogue(WindowCards _parent) {
        getJt().removeAll();
        AbsPanel container_=_parent.getCompoFactory().newBorder();
        initMessageName(_parent);
        //Panneau Distribution
        String lg_ = _parent.getLanguageKey();
        initJt(_parent,getCompoFactory().newSpinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),lg_);
        container_.add(getJt(),BorderLayout.CENTER);
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(getMessages().getVal(NEXT));
        bouton_.addActionListener(new ValidateRulesDealEvent(this, _parent));
        panneau_.add(bouton_);
        container_.add(panneau_,BorderLayout.SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }
    @Override
    public void validateRulesDeal(WindowCards _parent) {
        validateRules();
        getReglesBelote().setNombreParties(getNbGames().getValue());
        distribuer(_parent);
    }
    private String validerEgalite() {
        String lg_ = window.getLanguageKey();
        if (window.isSaveHomeFolder()) {
            FileSaveDialog.setFileSaveDialog(window,getCardDialog(), lg_, true, FileConst.GAME_EXT, window.getFrames().getHomePath(), window.getFrames().getHomePath(), window, FileConst.EXCLUDED);
        } else {
            FileSaveDialog.setFileSaveDialog(window,getCardDialog(), lg_, true, FileConst.GAME_EXT, EMPTY_STRING, window.getFrames().getHomePath(), window, FileConst.EXCLUDED);
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
        _dialog.getCardDialog().setVisible(true);
        return _dialog.partie;
    }

    private void distribuer(WindowCards _parent) {
        getCardDialog().setTitle(getMessages().getVal(DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        panneau_.add(getCompoFactory().newPlainLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
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
        panneau_=_parent.getCompoFactory().newBorder();
        panelsCards=_parent.getCompoFactory().newLineBox();
        HandBelote pile_=HandBelote.pileBase();
        pile_.trier(displayingBelote.getSuits(), displayingBelote.isDecreasing(), displayingBelote.getOrderBeforeBids());
        BeloteCardsScrollableList plc_=new BeloteCardsScrollableList(_parent.getCompoFactory(), 12,pile_.total(),getMessages().getVal(DEALING_STACK), _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
        plc_.initSelectionCarteBelote(_parent);
        plc_.setTriBelote(displayingBelote.getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.isDecreasing());
        plc_.iniPileBelote(pile_);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        stack = plc_;
        panelsCards.add(plc_.getContainer());
//        hands.add(plc_);
        int firstCards_ = getReglesBelote().getRepartition().getFirstCards();
        int lastCards_ = getReglesBelote().getRepartition().getRemainingCards();
        plc_=new BeloteCardsScrollableList(_parent.getCompoFactory(), firstCards_,firstCards_,getMessages().getVal(USER_HAND), _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
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
            plc_=new BeloteCardsScrollableList(_parent.getCompoFactory(), firstCards_,firstCards_,message_, _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
            plc_.initSelectionCarteBelote(_parent);
            plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriBelote(displayingBelote.getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.isDecreasing());
            panelsCards.add(plc_.getContainer());
            hands.add(plc_);
//            i_++;
        }
        plc_=new BeloteCardsScrollableList(_parent.getCompoFactory(), lastCards_,lastCards_,getMessages().getVal(CST_REMAINING), _parent.getCardFactories().getGeneBelote().create(_parent.getImageFactory(),false));
        plc_.initSelectionCarteBelote(_parent);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards.add(plc_.getContainer());
        remaining = plc_;
        panneau_.add(panelsCards,BorderLayout.CENTER);
        AbsPanel sousPanneau_=_parent.getCompoFactory().newLineBox();
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addActionListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
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
        labelSelectCards = getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(getMessages().getVal(SELECTED_CARDS),nombreCartesSelectionnees));
        sousPanneau_.add(labelSelectCards);
        panneau_.add(sousPanneau_,BorderLayout.SOUTH);
        c.add(panneau_,BorderLayout.CENTER);
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
        c.add(panneau_,BorderLayout.SOUTH);
        getCardDialog().setContentPane(c);
        getCardDialog().pack();
    }
    @Override
    public void backToRules(WindowCards _parent) {
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
        ConfirmDialog.showMessage(getCardDialog(), mes_, getMessages().getVal(ERROR_REPARTITION_TITLE), lg_, GuiConstants.ERROR_MESSAGE, getMain().getConfirmDialog());
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
            ConfirmDialog.showMessage(getCardDialog(), mes_, getMessages().getVal(ERROR_MOVE_TITLE), lg_, GuiConstants.ERROR_MESSAGE, getMain().getConfirmDialog());
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
        hands_.add(remaining);
        return hands_;
    }
}
