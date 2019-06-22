package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.WindowConstants;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.comboboxes.ComboBoxSuit;
import cards.gui.dialogs.events.AddSuitEvent;
import cards.gui.dialogs.events.RemoveSuitEvent;
import cards.gui.dialogs.events.ValidateDisplayingEvent;
import cards.gui.panels.SuitsScrollableList;
import cards.president.DisplayingPresident;
import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.Ints;
import code.util.StringMap;
import code.util.ints.Listable;

public final class DialogDisplayingPresident extends DialogCards implements DialogDisplaying {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogdisplayingpresident";
    private static final DialogDisplayingPresident DIALOG = new DialogDisplayingPresident();
    private static final String ADD_SUIT = "addSuit";
    private static final String CLOCK_WISE = "clockWise";
    private static final String DEALING = "dealing";
    private static final String ERROR_SUITS = "errorSuits";
    private static final String ERROR_SUITS_TITLE = "errorSuitsTitle";
    private static final String REMOVE_SUIT = "removeSuit";
    private static final String SORT_DECREASING = "sortDecreasing";
    private static final String SORTING = "sorting";
//    private static final String SORTING_BEFORE_PLAYING_CARDS = "sortingBeforePlayingCards";
    private static final String NB_DEALS_DEMO = "nbDealsDemo";
    private static final String VALIDATE = "validate";
    private static final String WISE = "wise";
    private StringMap<String> messages = new StringMap<String>();
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private JCheckBox checkClockwise;
    private SuitsScrollableList orderedSuits;
    private JCheckBox sortByDecreasing;
    private JSpinner nbDealsDemo;
    private ComboBoxSuit listeChoix;

    private DialogDisplayingPresident() {
        setAccessFile(DIALOG_ACCESS);
    }
    public static void setDialogDisplayingPresident(String _titre, MainWindow _fenetre) {
        //super(_titre, _fenetre, true);
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setMain(_fenetre);
        DIALOG.getJt().removeAll();
        DIALOG.setTitle(_titre);
        DIALOG.displayingPresident = _fenetre.getDisplayingPresident();
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        DIALOG.setDialogue(_fenetre);
    }

    private void initMessageName(MainWindow _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = getMessages(_parent,FileConst.FOLDER_MESSAGES_GUI);
    }
    public static DisplayingPresident getDisplaying() {
        DIALOG.setVisible(true);
        return DIALOG.displayingPresident;
    }

    public void setDialogue(MainWindow _window) {
        initMessageName(_window);
        Panel container_=new Panel();
        container_.setLayout(new BorderLayout());
        Panel panneau_=new Panel();
        panneau_.setLayout(new GridLayout(0,2));
        //Sous - panneau Battre les cartes
        EnumList<Suit> liste_=new EnumList<Suit>();
        panneau_.add(new JLabel(messages.getVal(WISE)));
        //Panneau Distribution
        checkClockwise=new JCheckBox(messages.getVal(CLOCK_WISE));
        checkClockwise.setSelected(displayingPresident.getHoraire());
        panneau_.add(checkClockwise);
        getJt().add(messages.getVal(DEALING),panneau_);
        //Panneau Tri avant enchere
        panneau_=new Panel();
        panneau_.setLayout(new GridLayout(0,4));
//        listeChoix=new ComboBoxSuit();
//        for (Suit couleur_:Suit.couleursOrdinaires()) {
//            listeChoix.addItem(couleur_);
//        }
        listeChoix=new ComboBoxSuit();
        EnumMap<Suit,String> trSuit_;
        trSuit_ = new EnumMap<Suit,String>();
        Listable<Suit> ls_ = Suit.couleursOrdinaires();
        String lg_ = _window.getLanguageKey();
        for (Suit couleur_:ls_) {
            trSuit_.add(couleur_, Games.toString(couleur_,lg_));
        }
        listeChoix.refresh(ls_, trSuit_);
        panneau_.add(listeChoix);
        Panel sousPanneauTwo_=new Panel();
        sousPanneauTwo_.setLayout(new GridLayout(0,1));
        LabelButton bouton_=new LabelButton(messages.getVal(ADD_SUIT));
        bouton_.addMouseListener(new AddSuitEvent(this));
        sousPanneauTwo_.add(bouton_);
        bouton_=new LabelButton(messages.getVal(REMOVE_SUIT));
        bouton_.addMouseListener(new RemoveSuitEvent(this, _window));
        sousPanneauTwo_.add(bouton_);
        sortByDecreasing=new JCheckBox(messages.getVal(SORT_DECREASING));
        sortByDecreasing.setSelected(displayingPresident.getDecroissant());
        sousPanneauTwo_.add(sortByDecreasing);
        panneau_.add(sousPanneauTwo_);
        for (Suit chaine_:displayingPresident.getCouleurs()) {
            liste_.add(chaine_);
        }
        orderedSuits=new SuitsScrollableList(liste_,4, _window);
        liste_.clear();
        panneau_.add(orderedSuits);
        //Panneau Tri avant enchere (Atout)
        Panel sousPanneau_=new Panel();
        sousPanneau_.setLayout(new GridLayout(0,1));
        sousPanneau_.add(new JLabel(messages.getVal(NB_DEALS_DEMO)));
        Ints decks_ = new Ints();
        //Panneau Distribution
        for(int b=FileConst.MIN_DEALS;b<=FileConst.MAX_DEALS;b++) {
            decks_.add(b);
        }
        nbDealsDemo = new JSpinner(new SpinnerListModel(decks_.toArray()));
        nbDealsDemo.setValue(displayingPresident.getNbDeals());
        sousPanneau_.add(nbDealsDemo);
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
        if(orderedSuits.nombreDeCouleurs()==4&&listeChoix.getItemCount()==4) {
            orderedSuits.toutSupprimer();
        }
        orderedSuits.ajouterCouleur(listeChoix.getCurrent());
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
                listeChoix.addItemLgKey(couleur_, lg_);
            }
        } else {
            orderedSuits.toutSupprimer();
        }
    }

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    @Override
    public void validateDisplaying() {
        if(orderedSuits.nombreDeCouleurs()<4) {
            ConfirmDialog.showMessage(this, messages.getVal(ERROR_SUITS), messages.getVal(ERROR_SUITS_TITLE), getMain().getLanguageKey(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,messages.getVal(ERROR_SUITS),messages.getVal(ERROR_SUITS_TITLE),JOptionPane.ERROR_MESSAGE);
        } else {
            displayingPresident.setHoraire(checkClockwise.isSelected());
            displayingPresident.setCouleurs(new EnumList<Suit>(orderedSuits.getCouleurs()));
            displayingPresident.setDecroissant(sortByDecreasing.isSelected());
            displayingPresident.setNbDeals((Integer) nbDealsDemo.getValue());
            closeWindow();
        }
    }
}

