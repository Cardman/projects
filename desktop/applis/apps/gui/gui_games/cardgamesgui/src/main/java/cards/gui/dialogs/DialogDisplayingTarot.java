package cards.gui.dialogs;





import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.AddSuitEvent;
import cards.gui.dialogs.events.RemoveSuitEvent;
import cards.gui.dialogs.events.ValidateDisplayingEvent;
import cards.gui.panels.SuitsScrollableList;
import cards.tarot.DisplayingTarot;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Listable;

public final class DialogDisplayingTarot extends DialogCards implements DialogDisplaying {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogdisplayingtarot";

    private static final String ADD_SUIT = "addSuit";
    private static final String CLOCK_WISE = "clockWise";
    private static final String DEALING = "dealing";
    private static final String ERROR_SUITS = "errorSuits";
    private static final String ERROR_SUITS_TITLE = "errorSuitsTitle";
    private static final String REMOVE_SUIT = "removeSuit";
    private static final String SORT_DECREASING = "sortDecreasing";
    private static final String SORTING = "sorting";
    private static final String VALIDATE = "validate";
    private static final String WISE = "wise";
    private StringMap<String> messages = new StringMap<String>();
    private DisplayingTarot displayingTarot;
    private AbsCustCheckBox checkClockwise;
    private SuitsScrollableList orderedSuits;
    private AbsCustCheckBox sortByDecreasing;
    private ComboBox<Suit> listeChoix;

    public DialogDisplayingTarot(AbstractProgramInfos _frameFactory) {
        super(_frameFactory, null);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void setDialogDisplayingTarot(String _titre, WindowCardsInt _fenetre) {
        //super(_titre, _fenetre, true);
        _fenetre.getDialogDisplayingTarot().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogDisplayingTarot().setMain(_fenetre);
        _fenetre.getDialogDisplayingTarot().getCardDialog().setTitle(_titre);
        _fenetre.getDialogDisplayingTarot().displayingTarot = _fenetre.getDisplayingTarot();
        _fenetre.getDialogDisplayingTarot().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
//        _fenetre.getDialogDisplayingTarot().getCardDialog().setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        _fenetre.getDialogDisplayingTarot().setDialogue(_fenetre);
    }

    private void initMessageName(WindowCardsInt _parent) {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), getCardDialog().getAccessFile());
    }
    public static DisplayingTarot getDisplaying(DialogDisplayingTarot _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.displayingTarot;
    }

    public void setDialogue(WindowCardsInt _window) {
        initMessageName(_window);
        AbsPanel container_=_window.getCompoFactory().newBorder();
        AbsPanel panneau_=_window.getCompoFactory().newGrid(0,2);
        //Panneau Battre les cartes
        IdList<Suit> liste_=new IdList<Suit>();
        panneau_.add(getCompoFactory().newPlainLabel(messages.getVal(WISE)));
        //Panneau Distribution
        checkClockwise=getCompoFactory().newCustCheckBox(messages.getVal(CLOCK_WISE));
        checkClockwise.setSelected(displayingTarot.getDisplaying().isClockwise());
        panneau_.add(checkClockwise);
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
        jt_.add(messages.getVal(DEALING),panneau_);
        //Panneau Tri
        AbsPanel sousPanneau_=_window.getCompoFactory().newGrid(0,3);
        listeChoix=new ComboBox<Suit>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, _window.getCompoFactory()));
        IdMap<Suit,String> trSuit_;
        trSuit_ = new IdMap<Suit,String>();
        Listable<Suit> ls_ = new IdList<Suit>(Suit.toutesCouleurs());
        TranslationsLg lg_ = getFrames().currentLg();
        for (Suit couleur_:ls_) {
            if (couleur_ == Suit.UNDEFINED) {
                continue;
            }
            trSuit_.addEntry(couleur_, Games.toString(couleur_,lg_));
        }
        listeChoix.refresh(ls_, trSuit_);
//        for(Suit couleur_:Suit.values()) {
//            if (couleur_ == Suit.UNDEFINED) {
//                continue;
//            }
//            listeChoix.addItem(couleur_);
//        }
        sousPanneau_.add(listeChoix.self());
        AbsPanel sousPanneauTwo_=_window.getCompoFactory().newGrid(0,1);
        AbsButton bouton_=getCompoFactory().newPlainButton(messages.getVal(ADD_SUIT));
        bouton_.addActionListener(new AddSuitEvent(this));
        sousPanneauTwo_.add(bouton_);
        bouton_=getCompoFactory().newPlainButton(messages.getVal(REMOVE_SUIT));
        bouton_.addActionListener(new RemoveSuitEvent(this, _window));
        sousPanneauTwo_.add(bouton_);
        sortByDecreasing=getCompoFactory().newCustCheckBox(messages.getVal(SORT_DECREASING));
        sortByDecreasing.setSelected(displayingTarot.getDisplaying().isDecreasing());
        sousPanneauTwo_.add(sortByDecreasing);
        sousPanneau_.add(sousPanneauTwo_);
        for (Suit chaine_: displayingTarot.getDisplaying().getSuits()) {
            liste_.add(chaine_);
        }
        orderedSuits=new SuitsScrollableList(liste_,5, _window);
        liste_.clear();
        sousPanneau_.add(orderedSuits.getContainer());
        jt_.add(messages.getVal(SORTING),sousPanneau_);
        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
        bouton_=getCompoFactory().newPlainButton(messages.getVal(VALIDATE));
        bouton_.addActionListener(new ValidateDisplayingEvent(this));
        container_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }
    @Override
    public void addSuit() {
        //Ajouter dans le tri
        Suit current_ = listeChoix.getCurrent();
        if (current_ == null) {
            return;
        }
        if(orderedSuits.nombreDeCouleurs()==5&&listeChoix.getItemCount()==5) {
            orderedSuits.toutSupprimer();
        }
        orderedSuits.ajouterCouleur(current_);
        listeChoix.removeItem(listeChoix.getSelectedIndex());
        listeChoix.getCombo().repaint();
    }
    @Override
    public void removeSuit(WindowCardsInt _window) {
        TranslationsLg lg_ = getFrames().currentLg();
        //Retirer du tri
        if(orderedSuits.nombreDeCouleurs()<5||listeChoix.getItemCount()<5) {
            IdList<Suit> couleurs_=orderedSuits.getCouleursSelectionnees();
            orderedSuits.supprimerCouleurs(couleurs_);
            for (Suit couleur_:couleurs_) {
                listeChoix.addItem(couleur_, Games.toString(couleur_, lg_));
            }
            listeChoix.getCombo().repaint();
        } else {
            orderedSuits.toutSupprimer();
        }
    }
    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    @Override
    public void validateDisplaying() {
        if(orderedSuits.nombreDeCouleurs()<5) {
            getMain().getFrames().getMessageDialogAbs().input(getCardDialog(), messages.getVal(ERROR_SUITS), messages.getVal(ERROR_SUITS_TITLE), GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,messages.getVal(ERROR_SUITS),messages.getVal(ERROR_SUITS_TITLE),JOptionPane.ERROR_MESSAGE);
        } else {
            displayingTarot.getDisplaying().setClockwise(checkClockwise.isSelected());
            displayingTarot.getDisplaying().setSuits(new IdList<Suit>(orderedSuits.getCouleurs()));
            displayingTarot.getDisplaying().setDecreasing(sortByDecreasing.isSelected());
            closeWindow();
        }

    }

}
