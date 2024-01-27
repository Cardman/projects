package cards.gui.dialogs;

import cards.consts.DisplayingCommon;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.AddSuitEvent;
import cards.gui.dialogs.events.RemoveSuitEvent;
import cards.gui.dialogs.events.ValidateDisplayingEvent;
import cards.gui.panels.SuitsScrollableList;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.Listable;

public final class DialogDisplayingContent {
    private AbsCustCheckBox checkClockwise;
    private SuitsScrollableList orderedSuits;
    private AbsCustCheckBox sortByDecreasing;
    private ComboBox<Suit> listeChoix;
    private final IdList<Suit> dataMust;
    private DisplayingCommon displayingCommon;
    private AbsPanel center;

    public DialogDisplayingContent(IdList<Suit> _d) {
        this.dataMust = _d;
    }
    public AbsPanel setDialogue(WindowCardsInt _window, DialogDisplaying _dial, DisplayingCommon _d) {
        displayingCommon = _d;
        AbsCompoFactory compoFactory_ = _window.getCompoFactory();
        TranslationsLg lg_ = _window.getFrames().currentLg();
        StringMap<String> messDis_ = Games.getDialogDisplayTr(Games.getAppliTr(lg_)).getMapping();
        AbsPanel container_= compoFactory_.newBorder();
        AbsPanel panneau_= compoFactory_.newGrid(0,2);
        //Sous - panneau Battre les cartes
        IdList<Suit> liste_=new IdList<Suit>();
        panneau_.add(compoFactory_.newPlainLabel(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_WISE)));
        //Panneau Distribution
        checkClockwise=compoFactory_.newCustCheckBox(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_CLOCK_WISE));
        checkClockwise.setSelected(displayingCommon.isClockwise());
        panneau_.add(checkClockwise);
        AbsTabbedPane jt_ = compoFactory_.newAbsTabbedPane();
        jt_.add(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_DEALING),panneau_);
        //Panneau Tri avant enchere
        panneau_= compoFactory_.newGrid(0,4);
        listeChoix=new ComboBox<Suit>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, compoFactory_));
        IdMap<Suit,String> trSuit_;
        trSuit_ = new IdMap<Suit,String>();
        Listable<Suit> ls_ = new IdList<Suit>(dataMust);
        for (Suit couleur_:ls_) {
            trSuit_.addEntry(couleur_, Games.toString(couleur_,lg_));
        }
        listeChoix.refresh(ls_, trSuit_);
        panneau_.add(listeChoix.self());
        AbsPanel sousPanneauTwo_= compoFactory_.newGrid(0,1);
        AbsButton bouton_=compoFactory_.newPlainButton(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_ADD_SUIT));
        bouton_.addActionListener(new AddSuitEvent(_dial));
        sousPanneauTwo_.add(bouton_);
        bouton_=compoFactory_.newPlainButton(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_REMOVE_SUIT));
        bouton_.addActionListener(new RemoveSuitEvent(_dial, _window));
        sousPanneauTwo_.add(bouton_);
        sortByDecreasing=compoFactory_.newCustCheckBox(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_SORT_DECREASING));
        sortByDecreasing.setSelected(displayingCommon.isDecreasing());
        sousPanneauTwo_.add(sortByDecreasing);
        panneau_.add(sousPanneauTwo_);
        for (Suit chaine_: displayingCommon.getSuits()) {
            liste_.add(chaine_);
        }
        orderedSuits=new SuitsScrollableList(liste_,dataMust.size(), _window);
        liste_.clear();
        panneau_.add(orderedSuits.getContainer());
        center = panneau_;
        jt_.add(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_SORTING),panneau_);
        container_.add(jt_,GuiConstants.BORDER_LAYOUT_CENTER);
        bouton_=compoFactory_.newPlainButton(messDis_.getVal(MessagesGuiCards.DIAL_DISPLAY_VALIDATE));
        bouton_.addActionListener(new ValidateDisplayingEvent(_window, _dial));
        container_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
        return container_;
    }

    public AbsPanel getCenter() {
        return center;
    }

    public void addSuit() {
        //Ajouter dans le tri
        Suit current_ = listeChoix.getCurrent();
        if (current_ == null) {
            return;
        }
        if(orderedSuits.nombreDeCouleurs()==dataMust.size()&&listeChoix.getItemCount()==dataMust.size()) {
            orderedSuits.toutSupprimer();
        }
        orderedSuits.ajouterCouleur(current_);
        listeChoix.removeItem(listeChoix.getSelectedIndex());
        listeChoix.getCombo().repaint();
    }
    public void removeSuit(WindowCardsInt _window) {
        TranslationsLg lg_ = _window.getFrames().currentLg();
        //Retirer du tri
        if(orderedSuits.nombreDeCouleurs()<dataMust.size()||listeChoix.getItemCount()<dataMust.size()) {
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
    public void validateDisplaying() {
        if(orderedSuits.nombreDeCouleurs()<dataMust.size()) {
            displayingCommon.setSuits(new IdList<Suit>(dataMust));
        }
        displayingCommon.setClockwise(checkClockwise.isSelected());
        displayingCommon.setSuits(new IdList<Suit>(orderedSuits.getCouleurs()));
        displayingCommon.setDecreasing(sortByDecreasing.isSelected());
//        closeWindow();

    }
    public AbsCustCheckBox getCheckClockwise() {
        return checkClockwise;
    }

    public AbsCustCheckBox getSortByDecreasing() {
        return sortByDecreasing;
    }

    public ComboBox<Suit> getListeChoix() {
        return listeChoix;
    }

    public DisplayingCommon getDisplayingCommon() {
        return displayingCommon;
    }

    public SuitsScrollableList getOrderedSuits() {
        return orderedSuits;
    }
}
