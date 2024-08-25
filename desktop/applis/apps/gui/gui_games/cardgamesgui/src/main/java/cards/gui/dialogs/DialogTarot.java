package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.IdMap;
import code.util.StringMap;

public abstract class DialogTarot extends DialogHelpCards {
    private final DialogTarotContent dialogTarotContent;

    protected DialogTarot(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        dialogTarotContent = new DialogTarotContent(_frameFactory);
    }
//    public DialogTarot(String _titre, MainWindow _fenetre, RulesTarot _rules) {
//        super(_titre, _fenetre, true);
//        setReglesTarot(_rules);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected AbsTabbedPane initJt(AbsSpinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window) {
        return dialogTarotContent.initJt(_nbGames, _enabledChangingNbPlayers, _nbPlayers, _window);
    }

    public DialogTarotContent getDialogTarotContent() {
        return dialogTarotContent;
    }

    public ComboBox<MixCardsChoice> getListeChoix() {
        return dialogTarotContent.getListeChoix();
    }

    public ComboBoxEnumCards<EndDealTarot> getListeChoixTwo() {
        return dialogTarotContent.getListeChoixTwo();
    }

    public ComboBoxEnumCards<ModeTarot> getListeChoixThree() {
        return dialogTarotContent.getListeChoixThree();
    }

    public ComboBoxEnumCards<DealingTarot> getListeChoixFour() {
        return dialogTarotContent.getListeChoixFour();
    }

    public ComboBoxEnumCards<Handfuls> getListeChoixFive() {
        return dialogTarotContent.getListeChoixFive();
    }

    public AbsSpinner getNbJoueurs() {
        return dialogTarotContent.getNbJoueurs();
    }

    public AbsCustCheckBox getAllowPlayCalledSuit() {
        return dialogTarotContent.getAllowPlayCalledSuit();
    }

    public AbsCustCheckBox getDiscardAfterCall() {
        return dialogTarotContent.getDiscardAfterCall();
    }

    public AbsSpinner getNbAtoutsPoignee() {
        return dialogTarotContent.getNbAtoutsPoignee();
    }

    public IdMap<BidTarot,AbsCustCheckBox> getBids() {
        return dialogTarotContent.getBids();
    }

    public IdMap<Miseres,AbsCustCheckBox> getMiseres() {
        return dialogTarotContent.getMiseres();
    }

    public AbsButton getBoutonPoignees() {
        return dialogTarotContent.getBoutonPoignees();
    }

    public String translate(String _k) {
        return translates().getVal(_k);
    }
    public StringMap<String> translates() {
        return MessagesCardGames.getDialogTarotTr(MessagesCardGames.getAppliTr(getFrames().currentLg())).getMapping();
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window);

    protected abstract AbsButton validatingButton();

    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    public void validateRules() {
        dialogTarotContent.validateRules();
    }

    public RulesTarot getReglesTarot() {
        return dialogTarotContent.getReglesTarot();
    }

    protected void setReglesTarot(RulesTarot _reglesTarot) {
        dialogTarotContent.setReglesTarot(_reglesTarot);
    }

    public AbsSpinner getNbGames() {
        return dialogTarotContent.getNbGames();
    }


}
