package cards.gui.dialogs;

import cards.belote.RulesBelote;
import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.MixCardsChoice;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import code.gui.AbsCustCheckBox;
import code.gui.AbsSpinner;
import code.gui.AbsTabbedPane;
import code.gui.ComboBox;
import code.gui.initialize.AbstractProgramInfos;
import code.util.IdMap;

public abstract class DialogBelote extends DialogHelpCards {

    private final DialogBeloteContent dialogBeloteContent;

    protected DialogBelote(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        dialogBeloteContent = new DialogBeloteContent(_frameFactory);
    }
//    public DialogBelote(String _titre, MainWindow _fenetre, RulesBelote _rulesBelote) {
//        super(_titre, _fenetre, true);
//        setReglesBelote(_rulesBelote);
//        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    }

    protected AbsTabbedPane initJt(WindowCardsInt _window, AbsSpinner _nbGames) {
        return dialogBeloteContent.initJt(_window, _nbGames);
    }
    public String translate(String _k) {
        return dialogBeloteContent.translate(_k);
    }
    public static BeloteTrumpPartner[] allBeloteTrumpPartner() {
        return new BeloteTrumpPartner[]{BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,BeloteTrumpPartner.OVERTRUMP_ONLY,BeloteTrumpPartner.UNDERTRUMP_ONLY,BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP};
    }

    /**Met en place le contenu de la boite de dialogue
    Pour les jeux et les joueurs on a besoin d'onglets pour utiliser moins de place sur l'ecran*/
    public abstract void setDialogue(WindowCardsInt _parent);
    /**Enregistre les informations dans une variable et ferme la boite de dialogue*/
    public void validateRules() {
        dialogBeloteContent.validateRules();
    }

    public AbsCustCheckBox getDealAll() {
        return dialogBeloteContent.getDealAll();
    }

    public ComboBox<MixCardsChoice> getListeChoix() {
        return dialogBeloteContent.getListeChoix();
    }

    public ComboBoxEnumCards<BeloteTrumpPartner> getListChoiceTwo() {
        return dialogBeloteContent.getListChoiceTwo();
    }

    public AbsCustCheckBox getClassic() {
        return dialogBeloteContent.getClassic();
    }

    public AbsCustCheckBox getUnderTrumpingFoe() {
        return dialogBeloteContent.getUnderTrumpingFoe();
    }

    public IdMap<BidBelote, AbsCustCheckBox> getBids() {
        return dialogBeloteContent.getBids();
    }

    public IdMap<DeclaresBelote, AbsCustCheckBox> getDeclares() {
        return dialogBeloteContent.getDeclares();
    }

    public RulesBelote getReglesBelote() {
        return dialogBeloteContent.getReglesBelote();
    }

    protected void setReglesBelote(RulesBelote _reglesBelote) {
        dialogBeloteContent.setReglesBelote(_reglesBelote);
    }

    public AbsSpinner getNbGames() {
        return dialogBeloteContent.getNbGames();
    }

}
