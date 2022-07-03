package cards.gui.dialogs;


import cards.consts.GameType;
import cards.facade.Nicknames;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.dialogs.enums.SaveDealMode;
import cards.gui.dialogs.events.*;
import cards.gui.panels.CardsScrollableList;
import cards.gui.panels.TarotCardsScrollableList;
import cards.tarot.DealTarot;
import cards.tarot.DisplayingTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.MonteCarloUtil;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class EditorTarot extends DialogTarot implements SetterSelectedCardList {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.editortarot";

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
    private AbsPanel panelsCards;
    private TarotCardsScrollableList stack;
    private final CustList<TarotCardsScrollableList> hands = new CustList<TarotCardsScrollableList>();
    private TarotCardsScrollableList dog;
    private AbsPlainLabel labelSelectCards;
    private int nombreCartesSelectionnees;
    private int nombreCartesSelectionneesPrecedent;
    private StringComboBox liste;
    private Nicknames nickNames;
    private StringComboBox listeTwo;
    private boolean setToNullGame;

    private DisplayingTarot displayingTarot = new DisplayingTarot();
    private WindowCards window;
    public EditorTarot(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initEditorTarot(WindowCards _fenetre) {
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorTarot().setMain(_fenetre);
        _fenetre.getEditorTarot().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getEditorTarot().getCardDialog().setTitle(GameEnum.TAROT.toString(lg_));
        _fenetre.getEditorTarot().setReglesTarot(_fenetre.getReglesTarot());
        _fenetre.getEditorTarot().partie = null;
        _fenetre.getEditorTarot().setToNullGame = true;
        _fenetre.getEditorTarot().nombreCartesSelectionneesPrecedent = 0;
        _fenetre.getEditorTarot().nombreCartesSelectionnees = 0;
        _fenetre.getEditorTarot().partieSauvegardee = false;
        _fenetre.getEditorTarot().window = _fenetre;
        _fenetre.getEditorTarot().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getEditorTarot().nickNames = _fenetre.getPseudosJoueurs();
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
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
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
        getReglesTarot().setNbDeals(getNbGames().getValue());
        distribuer(_parent);
    }
    private void distribuer(WindowCards _parent) {

        getCardDialog().setTitle(getMessages().getVal(DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_;
//        byte nbJ_=(byte) getReglesTarot().getRepartition().getNombreJoueurs();
        byte nbCartesPJ_ = (byte) getReglesTarot().getRepartition().getNombreCartesParJoueur();
        byte nbCartesC_ = (byte) getReglesTarot().getRepartition().getNombreCartesChien();

        HandTarot pile_=HandTarot.pileBase();
        panneau_=_parent.getCompoFactory().newLineBox();
        panneau_.add(getCompoFactory().newPlainLabel(getMessages().getVal(DEALER)));
        liste=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
        liste.addItem(nickNames.getPseudo());
        int nbPlayers_ = getReglesTarot().getRepartition().getNombreJoueurs();
        for(String n: nickNames.getPseudosTarot()) {
            if (liste.getItemCount() == nbPlayers_) {
                break;
            }
            liste.addItem(n);
        }
        liste.addItem(getMessages().getVal(RANDOM));
        panneau_.add(liste.self());
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_NORTH);
        pile_.trier(displayingTarot.getSuits(), displayingTarot.isDecreasing());
        TarotCardsScrollableList plc_=new TarotCardsScrollableList(_parent, nbCartesPJ_,pile_.total(),getMessages().getVal(DEALING_STACK));
        plc_.setTriTarot(displayingTarot.getSuits(), displayingTarot.isDecreasing());
        plc_.iniPileTarot(pile_);
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        panelsCards=_parent.getCompoFactory().newLineBox();
        stack = plc_;
        panelsCards.add(plc_.getContainer());
        plc_=new TarotCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,getMessages().getVal(USER_HAND));
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriTarot(displayingTarot.getSuits(), displayingTarot.isDecreasing());
        panelsCards.add(plc_.getContainer());
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
            message_ = StringUtil.simpleStringsFormat(message_, n);
            plc_=new TarotCardsScrollableList(_parent, nbCartesPJ_,nbCartesPJ_,message_);
            plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
            plc_.setTriTarot(displayingTarot.getSuits(), displayingTarot.isDecreasing());
            panelsCards.add(plc_.getContainer());
            hands.add(plc_);
//            i_++;
        }
        plc_=new TarotCardsScrollableList(_parent, nbCartesC_,nbCartesC_,getMessages().getVal(REMAINING));
        plc_.getListe().setListener(new ListenerClickCardsList(getMessages().getVal(SELECTED_CARDS), this));
        plc_.setTriTarot(displayingTarot.getSuits(), displayingTarot.isDecreasing());
        panelsCards.add(plc_.getContainer());
        dog = plc_;
        panneau_=_parent.getCompoFactory().newBorder();
        panneau_.add(panelsCards,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel sousPanneau_=_parent.getCompoFactory().newLineBox();
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(getMessages().getVal(MOVE_CARDS));
        bouton_.addActionListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
        listeTwo=new StringComboBox(_parent.getFrames().getGeneComboBox().createCombo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
        listeTwo.addItem(getMessages().getVal(DEALING_STACK));
        listeTwo.addItem(getMessages().getVal(USER_HAND));
        for(String n: nickNames.getPseudosTarot()) {
            if (listeTwo.getItemCount() == getReglesTarot().getRepartition().getNombreJoueurs() + 1) {
                break;
            }
            String message_ = getMessages().getVal(PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            listeTwo.addItem(message_);
        }
        listeTwo.addItem(getMessages().getVal(REMAINING));
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
    private void erreur(TarotCardsScrollableList _plc) {
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
        CustList<CardsScrollableList> hands_ = getHands(false);
        for(CardsScrollableList l: hands_) {
//            plc_=(TarotCardsScrollableList)panelsCards.getComponent(i);
            HandTarot m=new HandTarot();
            m.ajouterCartes(((TarotCardsScrollableList) l).valMainTarot());
            m.trier(displayingTarot.getSuits(), displayingTarot.isDecreasing());
            mains_.add(m);
        }
//        nombreDeJoueurs_=nombreDeMains_-1;
        nombreDeJoueurs_=hands_.size();
        byte donneur_ = (byte) liste.getSelectedIndex();
        if (donneur_ == nombreDeJoueurs_) {
//            donneur_=(byte)Math.floor(nombreDeJoueurs_*MonteCarlo.randomDouble());
            donneur_=(byte)MonteCarloUtil.randomLong(nombreDeJoueurs_,getMain().getGenerator());
        }
        DealTarot donne_=new DealTarot(mains_,donneur_);
        partie = new GameTarot(GameType.EDIT,donne_,getReglesTarot());
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
        StreamTextFile.saveTextFile(_s, DocumentWriterTarotUtil.setGameTarot(partie), window.getStreams());
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
            mes_ = StringUtil.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString((long)max_-taille_), listeTwo.getSelectedComboItem());
            getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), mes_, getMessages().getVal(ERROR_MOVE_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,mes_, getMessages().getVal(ERROR_MOVE_TITLE), JOptionPane.ERROR_MESSAGE);
        }


    }
    public static GameTarot getPartie(EditorTarot _dialog) {
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
        hands_.add(dog);
        return hands_;
    }
}
