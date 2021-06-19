package cards.gui.dialogs;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import cards.belote.DisplayingBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.dialogs.events.AddSuitEvent;
import cards.gui.dialogs.events.RemoveSuitEvent;
import cards.gui.dialogs.events.ValidateDisplayingEvent;
import cards.gui.panels.SuitsScrollableList;
import code.gui.*;
import code.util.EnumList;
import code.util.EnumMap;
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
    private CustCheckBox checkClockwise;
    private SuitsScrollableList orderedSuits;
    private CustCheckBox sortByDecreasing;
    private CustCheckBox sortByTrump;
    private ComboBox<Suit> listeChoix;

    public DialogDisplayingBelote() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void setDialogDisplayingBelote(String _titre, MainWindow _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogDisplayingBelote().setDialogIcon(_fenetre);
        _fenetre.getDialogDisplayingBelote().setMain(_fenetre);
        _fenetre.getDialogDisplayingBelote().getJt().removeAll();
        _fenetre.getDialogDisplayingBelote().setTitle(_titre);
        _fenetre.getDialogDisplayingBelote().displayingBelote = _fenetre.getDisplayingBelote();
        _fenetre.getDialogDisplayingBelote().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogDisplayingBelote().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingBelote().setDialogue(_fenetre);
    }

    private void initMessageName(MainWindow _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), getAccessFile());
    }
    public static DisplayingBelote getDisplaying(DialogDisplayingBelote _dialog) {
        _dialog.setVisible(true);
        return _dialog.displayingBelote;
    }

    public void setDialogue(MainWindow _window) {
        initMessageName(_window);
        Panel container_=Panel.newBorder();
        Panel panneau_=Panel.newGrid(0,2);
        //Sous - panneau Battre les cartes
        EnumList<Suit> liste_=new EnumList<Suit>();
        panneau_.add(new TextLabel(messages.getVal(WISE)));
        //Panneau Distribution
        checkClockwise=new CustCheckBox(messages.getVal(CLOCK_WISE));
        checkClockwise.setSelected(displayingBelote.isClockwise());
        panneau_.add(checkClockwise);
        getJt().add(messages.getVal(DEALING),panneau_);
        //Panneau Tri avant enchere
        panneau_=Panel.newGrid(0,4);
        listeChoix=new ComboBox<Suit>(_window.getFrames().getGeneComboBox().createCombo(new StringList(), -1));
        EnumMap<Suit,String> trSuit_;
        trSuit_ = new EnumMap<Suit,String>();
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
        Panel sousPanneauTwo_=Panel.newGrid(0,1);
        LabelButton bouton_=new LabelButton(messages.getVal(ADD_SUIT));
        bouton_.addMouseListener(new AddSuitEvent(this));
        sousPanneauTwo_.add(bouton_);
        bouton_=new LabelButton(messages.getVal(REMOVE_SUIT));
        bouton_.addMouseListener(new RemoveSuitEvent(this, _window));
        sousPanneauTwo_.add(bouton_);
        sortByDecreasing=new CustCheckBox(messages.getVal(SORT_DECREASING));
        sortByDecreasing.setSelected(displayingBelote.isDecreasing());
        sousPanneauTwo_.add(sortByDecreasing);
        panneau_.add(sousPanneauTwo_);
        for (Suit chaine_:displayingBelote.getSuits()) {
            liste_.add(chaine_);
        }
        orderedSuits=new SuitsScrollableList(liste_,4, _window, _window.getCardFactories().getGeneSuit().create(false));
        liste_.clear();
        panneau_.add(orderedSuits.getContainer());
        //Panneau Tri avant enchere (Atout)
        Panel sousPanneau_=Panel.newGrid(0,1);
        sousPanneau_.add(new TextLabel(messages.getVal(SORTING_BEFORE_PLAYING_CARDS)));
        sortByTrump=new CustCheckBox(messages.getVal(SORTING_TRUMP));
        sortByTrump.setSelected(displayingBelote.getOrderBeforeBids()==Order.TRUMP);
        sousPanneau_.add(sortByTrump);
        panneau_.add(sousPanneau_);
        getJt().add(messages.getVal(SORTING),panneau_);

        container_.add(getJt(),BorderLayout.CENTER);
        bouton_=new LabelButton(messages.getVal(VALIDATE));
        bouton_.addMouseListener(new ValidateDisplayingEvent(this));
        container_.add(bouton_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
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
    public void removeSuit(MainWindow _window) {
        String lg_ = _window.getLanguageKey();
        //Retirer du tri
        if(orderedSuits.nombreDeCouleurs()<4||listeChoix.getItemCount()<4) {
            EnumList<Suit> couleurs_=orderedSuits.getCouleursSelectionnees();
            orderedSuits.supprimerCouleurs(couleurs_);
            for(Suit couleur_:couleurs_) {
                listeChoix.getElements().put(couleur_, Games.toString(couleur_, lg_));
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
            ConfirmDialog.showMessage(this, messages.getVal(ERROR_SUITS), messages.getVal(ERROR_SUITS_TITLE), getMain().getLanguageKey(), JOptionPane.ERROR_MESSAGE, getMain().getConfirmDialog());
            //JOptionPane.showMessageDialog(this,messages.getVal(ERROR_SUITS),messages.getVal(ERROR_SUITS_TITLE),JOptionPane.ERROR_MESSAGE);
        } else {
            displayingBelote.setClockwise(checkClockwise.isSelected());
            displayingBelote.setSuits(new EnumList<Suit>(orderedSuits.getCouleurs()));
            displayingBelote.setDecreasing(sortByDecreasing.isSelected());
            if(sortByTrump.isSelected()) {
                displayingBelote.setOrderBeforeBids(Order.TRUMP);
            } else {
                displayingBelote.setOrderBeforeBids(Order.SUIT);
            }
            closeWindow();
        }
    }

}

