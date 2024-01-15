package cards.gui.dialogs;


import cards.belote.*;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.GameType;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.StringComboBox;
import cards.gui.dialogs.enums.SaveDealMode;
import cards.gui.dialogs.events.*;
import cards.gui.panels.BeloteCardsScrollableList;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.maths.montecarlo.MonteCarloUtil;
import code.scripts.messages.cards.MessagesEditorCards;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.core.StringUtil;

public final class EditorBelote extends DialogBelote implements SetterSelectedCardList{

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.editorbelote";
    private final EditorCards editorCards;
    private GameBelote partie;
    private BeloteCardsScrollableList stack;
    private final CustList<BeloteCardsScrollableList> hands = new CustList<BeloteCardsScrollableList>();
    private BeloteCardsScrollableList remaining;
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private WindowCards window;

    public EditorBelote(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, new ClosingEditorCards());
        editorCards = new EditorCards(_frameFactory.getTranslations());
        getClos().setEditor(this);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initEditorBelote(WindowCards _fenetre) {
        //super(GameEnum.BELOTE.toString(),_fenetre,_fenetre.getReglesBelote());
        String lg_ = _fenetre.getLanguageKey();
        _fenetre.getEditorBelote().setMain(_fenetre);
        _fenetre.getEditorBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getEditorBelote().getCardDialog().setTitle(GameEnum.BELOTE.toString(lg_));
        _fenetre.getEditorBelote().setReglesBelote(_fenetre.getReglesBelote());
        _fenetre.getEditorBelote().partie = null;
        _fenetre.getEditorBelote().editorCards.setSetToNullGame(true);
        _fenetre.getEditorBelote().editorCards.setPartieSauvegardee(false);
        _fenetre.getEditorBelote().window = _fenetre;
        _fenetre.getEditorBelote().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
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
//    @Override
//    public void closeWindow() {
//        super.closeWindow();
//        if (setToNullGame) {
//            partie = null;
//        }
//    }

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
    public void setDialogue(WindowCardsInt _parent) {
        AbsTabbedPane jt_ = _parent.getCompoFactory().newAbsTabbedPane();
        AbsPanel container_=_parent.getCompoFactory().newBorder();
        initMessageName(_parent);
        //Panneau Distribution
        String lg_ = _parent.getLanguageKey();
        initJt(_parent,getCompoFactory().newSpinner(FileConst.MIN_DEALS,FileConst.MIN_DEALS,FileConst.MAX_DEALS,1),lg_, jt_);
        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        AbsButton bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.NEXT));
        bouton_.addActionListener(new ValidateRulesDealEvent(this, _parent));
        panneau_.add(bouton_);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }
    @Override
    public void validateRulesDeal(WindowCardsInt _parent) {
        validateRules();
        getReglesBelote().getCommon().setNbDeals(getNbGames().getValue());
        distribuer(_parent);
    }
    private String validerEgalite() {
        String fichier_=window.save(getCardDialog());
        if(!fichier_.isEmpty()) {
            validerSauvegarde(fichier_);
        }
        return fichier_;
    }
    public static GameBelote getPartie(EditorBelote _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.partie;
    }

    private void distribuer(WindowCardsInt _parent) {
        getCardDialog().setTitle(editorCards.translate(_parent,MessagesEditorCards.DEALING_CARDS));
        AbsPanel c=_parent.getCompoFactory().newBorder();
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        panneau_.add(getCompoFactory().newPlainLabel(editorCards.translate(_parent,MessagesEditorCards.DEALER)));
//        liste=new StringComboBox(GuiBaseUtil.combo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
//        liste.addItem(nickNames.getPseudo());
        int nbPlayers_ = getReglesBelote().getDealing().getId().getNombreJoueurs();
//        for(String n: nickNames.getPseudosBelote()) {
//            if (liste.getItemCount() == nbPlayers_) {
//                break;
//            }
//            liste.addItem(n);
//        }
//        liste.addItem(editorCards.translate(_parent,MessagesEditorCards.RANDOM));
//        liste.getCombo().repaint();
        panneau_.add(editorCards.buildDealer(window.getPseudosJoueurs().getPseudo(),_parent.getImageFactory(), _parent.getCompoFactory(), _parent.getLanguageKey(), window.getPseudosJoueurs().getPseudosBelote(), nbPlayers_).self());
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_NORTH);
        panneau_=_parent.getCompoFactory().newBorder();
        editorCards.setPanelsCards(_parent.getCompoFactory().newLineBox());
        HandBelote pile_=HandBelote.pileBase();
        pile_.trier(displayingBelote.getDisplaying().getSuits(), displayingBelote.getDisplaying().isDecreasing(), displayingBelote.getOrderBeforeBids());
        BeloteCardsScrollableList plc_=new BeloteCardsScrollableList(_parent, 12,pile_.total(),editorCards.translate(_parent,MessagesEditorCards.DEALING_STACK));

        plc_.setTriBelote(displayingBelote.getDisplaying().getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.getDisplaying().isDecreasing());
        plc_.iniPileBelote(pile_);
        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.clear();
        stack = plc_;
        editorCards.addPanel(plc_);
        editorCards.getPanelsCards().add(plc_.getContainer());
//        hands.add(plc_);
        int firstCards_ = getReglesBelote().getDealing().getFirstCards();
        int lastCards_ = getReglesBelote().getDealing().getRemainingCards();
        plc_=new BeloteCardsScrollableList(_parent, firstCards_,firstCards_,editorCards.translate(_parent,MessagesEditorCards.USER_HAND));

        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        plc_.setTriBelote(displayingBelote.getDisplaying().getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.getDisplaying().isDecreasing());
        editorCards.getPanelsCards().add(plc_.getContainer());
        hands.clear();
        hands.add(plc_);
        editorCards.addPanel(plc_);
//        int i_=0;
//        int nbPlayers_ = getReglesBelote().getRepartition().getNombreJoueurs();
        for(String n: window.getPseudosJoueurs().getPseudosBelote()) {
            if (hands.size() == nbPlayers_) {
                break;
            }
//            if (i_ == nbPlayers_ - 1) {
//                break;
//            }
            String message_ = editorCards.translate(_parent,MessagesEditorCards.PLAYER_HAND);
            message_ = StringUtil.simpleStringsFormat(message_, n);
            plc_=new BeloteCardsScrollableList(_parent, firstCards_,firstCards_,message_);

            plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
            plc_.setTriBelote(displayingBelote.getDisplaying().getSuits(), displayingBelote.getOrderBeforeBids(), displayingBelote.getDisplaying().isDecreasing());
            editorCards.getPanelsCards().add(plc_.getContainer());
            hands.add(plc_);
            editorCards.addPanel(plc_);
//            i_++;
        }
        plc_=new BeloteCardsScrollableList(_parent, lastCards_,lastCards_,editorCards.translate(_parent,MessagesEditorCards.CST_REMAINING));

        plc_.getListe().setListener(new ListenerClickCardsList(editorCards.translate(_parent,MessagesEditorCards.SELECTED_CARDS), this.getEditorCards()));
        editorCards.getPanelsCards().add(plc_.getContainer());
        remaining = plc_;
        editorCards.addPanel(plc_);
        panneau_.add(editorCards.getPanelsCards(),GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel sousPanneau_=_parent.getCompoFactory().newLineBox();
        AbsButton bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.MOVE_CARDS));
        bouton_.addActionListener(new MoveCardsEvent(this));
        sousPanneau_.add(bouton_);
//        listeTwo=new StringComboBox(GuiBaseUtil.combo(_parent.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _parent.getCompoFactory()));
//        listeTwo.addItem(editorCards.translate(_parent,MessagesEditorCards.DEALING_STACK));
//        listeTwo.addItem(editorCards.translate(_parent,MessagesEditorCards.USER_HAND));
//        for(String n: nickNames.getPseudosBelote()) {
//            if (listeTwo.getItemCount() == getReglesBelote().getDealing().getId().getNombreJoueurs() + 1) {
//                break;
//            }
//            String message_ = editorCards.translate(_parent,MessagesEditorCards.PLAYER_HAND);
//            message_ = StringUtil.simpleStringsFormat(message_, n);
//            listeTwo.addItem(message_);
//        }
        StringComboBox handPl_ = editorCards.beginCombo(_parent.getImageFactory(), _parent.getCompoFactory(), _parent.getLanguageKey(), window.getPseudosJoueurs().getPseudosBelote(), getReglesBelote().getDealing().getId().getNombreJoueurs());
        handPl_.addItem(editorCards.translate(_parent,MessagesEditorCards.CST_REMAINING));
        handPl_.getCombo().repaint();
        sousPanneau_.add(handPl_.self());
        sousPanneau_.add(editorCards.buildLabelSelectCard(getCompoFactory(), _parent.getLanguageKey()));
        panneau_.add(sousPanneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_CENTER);
        panneau_=_parent.getCompoFactory().newLineBox();
        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.BACK));
        bouton_.addActionListener(new BackToRulesEvent(this, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.SAVE_WITHOUT_CLOSING));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_WITHOUT_CLOSING, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.SAVE_THEN_PLAY));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_PLAY, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.PLAY_WITHOUT_SAVING));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.PLAY_WITHOUT_SAVING, _parent));
        panneau_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(editorCards.translate(_parent,MessagesEditorCards.SAVE_THEN_CLOSE));
        bouton_.addActionListener(new SavingDealEvent(this, SaveDealMode.SAVE_THEN_CLOSE, _parent));
        panneau_.add(bouton_);
        c.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(c);
        getCardDialog().pack();
    }
    @Override
    public void backToRules(WindowCardsInt _parent) {
        editorCards.setPartieSauvegardee(false);
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
            m.trier(displayingBelote.getDisplaying().getSuits(), displayingBelote.getDisplaying().isDecreasing(), displayingBelote.getOrderBeforeBids());
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
        nombreDeJoueurs_=getReglesBelote().getDealing().getId().getNombreJoueurs();
        byte donneur_ = (byte) editorCards.getListe().getSelectedIndex();
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
        String mes_ = editorCards.translate(lg_,MessagesEditorCards.ERROR_REPARTITION);
        mes_ = StringUtil.simpleNumberFormat(mes_, _plc.taille());
        getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), mes_, editorCards.translate(lg_,MessagesEditorCards.ERROR_REPARTITION_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
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
        for (BeloteCardsScrollableList l: stackHands()) {
            HandBelote cartesSelectionnees_= l.getCartesBeloteSelectionnees();
            m.ajouterCartes(cartesSelectionnees_);
        }
        int numero_= editorCards.getListeTwo().getSelectedIndex();
        BeloteCardsScrollableList panneauSelectionne_= stackHands().get(numero_);
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
            for (BeloteCardsScrollableList l: stackHands()) {
                HandBelote cartesSelectionnees_= l.getCartesBeloteSelectionnees();
                l.supprimerCartesBelote(cartesSelectionnees_);
                l.getListe().forceRefresh();
            }
            if(numero_ != getEditorCards().getAll().size()-1) {
                panneauSelectionne_.ajouterCartesBelote(m);
            } else {
                panneauSelectionne_.ajouterCartesBeloteFin(m);
            }
            panneauSelectionne_.getListe().forceRefresh();
            getEditorCards().getLabelSelectCards().setText(StringUtil.simpleNumberFormat(editorCards.translate(lg_,MessagesEditorCards.SELECTED_CARDS),0));
            getCardDialog().pack();
        } else {
            String mes_ = editorCards.translate(lg_,MessagesEditorCards.ERROR_MOVE);
            mes_ = StringUtil.simpleStringsFormat(mes_, Long.toString(m.total()), Long.toString((long)max_-taille_), editorCards.getListeTwo().getSelectedComboItem());
            getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), mes_, editorCards.translate(lg_,MessagesEditorCards.ERROR_MOVE_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,mes_, getMessages().getVal(ERROR_MOVE_TITLE), JOptionPane.ERROR_MESSAGE);
        }

    }

    public EditorCards getEditorCards() {
        return editorCards;
    }

    public CustList<BeloteCardsScrollableList> stackHands() {
        CustList<BeloteCardsScrollableList> hands_ = new CustList<BeloteCardsScrollableList>();
        hands_.add(stack);
        hands_.addAllElts(hands);
        hands_.add(remaining);
        return hands_;
    }
}
