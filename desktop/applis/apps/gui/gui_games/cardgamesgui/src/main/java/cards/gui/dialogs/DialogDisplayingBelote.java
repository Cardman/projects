package cards.gui.dialogs;





import cards.belote.DisplayingBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.AddSuitEvent;
import cards.gui.dialogs.events.RemoveSuitEvent;
import cards.gui.dialogs.events.ValidateDisplayingEvent;
import cards.gui.panels.SuitsScrollableList;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Listable;

public final class DialogDisplayingBelote extends DialogCards implements DialogDisplaying {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogdisplayingbelote";
    private static final String ADD_SUIT = "addSuit";
    private static final String CLOCK_WISE = "clockWise";
    private static final String DEALING = "dealing";
    private static final String ERROR_SUITS = "errorSuits";
    private static final String ERROR_SUITS_TITLE = "errorSuitsTitle";
    private static final String REMOVE_SUIT = "removeSuit";
    private static final String SORT_DECREASING = "sortDecreasing";
    private static final String SORTING = "sorting";
    private static final String SORTING_BEFORE_PLAYING_CARDS = "sortingBeforePlayingCards";
    private static final String SORTING_TRUMP = "sortingTrump";
    private static final String VALIDATE = "validate";
    private static final String WISE = "wise";
    private StringMap<String> messages = new StringMap<String>();
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private AbsCustCheckBox checkClockwise;
    private SuitsScrollableList orderedSuits;
    private AbsCustCheckBox sortByDecreasing;
    private AbsCustCheckBox sortByTrump;
    private ComboBox<Suit> listeChoix;

    public DialogDisplayingBelote(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void setDialogDisplayingBelote(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogDisplayingBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogDisplayingBelote().setMain(_fenetre);
        _fenetre.getDialogDisplayingBelote().getJt().removeAll();
        _fenetre.getDialogDisplayingBelote().getCardDialog().setTitle(_titre);
        _fenetre.getDialogDisplayingBelote().displayingBelote = _fenetre.getDisplayingBelote();
        _fenetre.getDialogDisplayingBelote().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogDisplayingBelote().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingBelote().setDialogue(_fenetre);
    }

    private void initMessageName(WindowCardsInt _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), getCardDialog().getAccessFile());
    }
    public static DisplayingBelote getDisplaying(DialogDisplayingBelote _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.displayingBelote;
    }

    public void setDialogue(WindowCardsInt _window) {
        initMessageName(_window);
        AbsPanel container_=_window.getCompoFactory().newBorder();
        AbsPanel panneau_=_window.getCompoFactory().newGrid(0,2);
        //Sous - panneau Battre les cartes
        IdList<Suit> liste_=new IdList<Suit>();
        panneau_.add(getCompoFactory().newPlainLabel(messages.getVal(WISE)));
        //Panneau Distribution
        checkClockwise=getCompoFactory().newCustCheckBox(messages.getVal(CLOCK_WISE));
        checkClockwise.setSelected(displayingBelote.getDisplaying().isClockwise());
        panneau_.add(checkClockwise);
        getJt().add(messages.getVal(DEALING),panneau_);
        //Panneau Tri avant enchere
        panneau_=_window.getCompoFactory().newGrid(0,4);
        listeChoix=new ComboBox<Suit>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1, _window.getCompoFactory()));
        IdMap<Suit,String> trSuit_;
        trSuit_ = new IdMap<Suit,String>();
        Listable<Suit> ls_ = Suit.couleursOrdinaires();
        String lg_ = _window.getLanguageKey();
        for (Suit couleur_:ls_) {
            trSuit_.addEntry(couleur_, Games.toString(couleur_,lg_));
        }
        listeChoix.refresh(ls_, trSuit_);
//        for (Suit couleur_:Suit.couleursOrdinaires()) {
//            listeChoix.addItem(couleur_);
//        }
        panneau_.add(listeChoix.self());
        AbsPanel sousPanneauTwo_=_window.getCompoFactory().newGrid(0,1);
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(messages.getVal(ADD_SUIT));
        bouton_.addActionListener(new AddSuitEvent(this));
        sousPanneauTwo_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(messages.getVal(REMOVE_SUIT));
        bouton_.addActionListener(new RemoveSuitEvent(this, _window));
        sousPanneauTwo_.add(bouton_);
        sortByDecreasing=getCompoFactory().newCustCheckBox(messages.getVal(SORT_DECREASING));
        sortByDecreasing.setSelected(displayingBelote.getDisplaying().isDecreasing());
        sousPanneauTwo_.add(sortByDecreasing);
        panneau_.add(sousPanneauTwo_);
        for (Suit chaine_: displayingBelote.getDisplaying().getSuits()) {
            liste_.add(chaine_);
        }
        orderedSuits=new SuitsScrollableList(liste_,4, _window);
        liste_.clear();
        panneau_.add(orderedSuits.getContainer());
        //Panneau Tri avant enchere (Atout)
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid(0,1);
        sousPanneau_.add(getCompoFactory().newPlainLabel(messages.getVal(SORTING_BEFORE_PLAYING_CARDS)));
        sortByTrump=getCompoFactory().newCustCheckBox(messages.getVal(SORTING_TRUMP));
        sortByTrump.setSelected(displayingBelote.getOrderBeforeBids()==Order.TRUMP);
        sousPanneau_.add(sortByTrump);
        panneau_.add(sousPanneau_);
        getJt().add(messages.getVal(SORTING),panneau_);

        container_.add(getJt(),GuiConstants.BORDER_LAYOUT_CENTER);
        bouton_=getCompoFactory().newPlainButton(messages.getVal(VALIDATE));
        bouton_.addActionListener(new ValidateDisplayingEvent(this));
        container_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }

    @Override
    public void addSuit() {
        Suit current_ = listeChoix.getCurrent();
        if (current_ == null) {
            return;
        }
        if(orderedSuits.nombreDeCouleurs()==4&&listeChoix.getItemCount()==4) {
            orderedSuits.toutSupprimer();
        }
        orderedSuits.ajouterCouleur(current_);
        listeChoix.removeItem(listeChoix.getSelectedIndex());
    }

    @Override
    public void removeSuit(WindowCardsInt _window) {
        String lg_ = _window.getLanguageKey();
        //Retirer du tri
        if(orderedSuits.nombreDeCouleurs()<4||listeChoix.getItemCount()<4) {
            IdList<Suit> couleurs_=orderedSuits.getCouleursSelectionnees();
            orderedSuits.supprimerCouleurs(couleurs_);
            for(Suit couleur_:couleurs_) {
                listeChoix.addItem(couleur_, Games.toString(couleur_, lg_));
            }
        } else {
            orderedSuits.toutSupprimer();
        }
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    @Override
    public void validateDisplaying() {
        if(orderedSuits.nombreDeCouleurs()<4) {
            getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), messages.getVal(ERROR_SUITS), messages.getVal(ERROR_SUITS_TITLE), getMain().getLanguageKey(), GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,messages.getVal(ERROR_SUITS),messages.getVal(ERROR_SUITS_TITLE),JOptionPane.ERROR_MESSAGE);
        } else {
            displayingBelote.getDisplaying().setClockwise(checkClockwise.isSelected());
            displayingBelote.getDisplaying().setSuits(new IdList<Suit>(orderedSuits.getCouleurs()));
            displayingBelote.getDisplaying().setDecreasing(sortByDecreasing.isSelected());
            if(sortByTrump.isSelected()) {
                displayingBelote.setOrderBeforeBids(Order.TRUMP);
            } else {
                displayingBelote.setOrderBeforeBids(Order.SUIT);
            }
            closeWindow();
        }
    }

}

