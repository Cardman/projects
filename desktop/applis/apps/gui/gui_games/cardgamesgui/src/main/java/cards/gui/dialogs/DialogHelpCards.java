package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import code.gui.AbsButton;
import code.gui.AbsCommonFrame;
import code.gui.ComboBox;
import code.gui.GuiBaseUtil;
import code.gui.files.ClosingFileFrameEvent;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.ints.Listable;

public abstract class DialogHelpCards {

    private final AbstractProgramInfos frames;
    private final AbsCommonFrame absDialog;
    private final AbsCompoFactory compo;
    private AbsButton validateButton;

    protected DialogHelpCards(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        absDialog = WindowCards.frame(_fact);
        frames = _fact;
        absDialog.addWindowListener(new ClosingFileFrameEvent(absDialog,_modal));
        compo = _fact.getCompoFactory();
    }

    public ComboBox<MixCardsChoice> build(WindowCardsInt _window, MixCardsChoice _m) {
        TranslationsLg lg_ = getFrames().currentLg();
        ComboBox<MixCardsChoice> mixChoice_=new ComboBox<MixCardsChoice>(GuiBaseUtil.combo(_window.getImageFactory(), new StringList(), -1, _window.getCompoFactory()));
        Listable<MixCardsChoice> mix_ = new IdList<MixCardsChoice>(allMixCardsChoice());
        IdMap<MixCardsChoice, String> trMix_ = new IdMap<MixCardsChoice, String>();
        for (MixCardsChoice choix_: mix_) {
            trMix_.put(choix_, Games.toString(choix_,lg_));
        }
        mixChoice_.refresh(mix_, trMix_);
        mixChoice_.setSelectedItem(_m);
        return mixChoice_;
    }

    public static MixCardsChoice[] allMixCardsChoice() {
        return new MixCardsChoice[]{MixCardsChoice.EACH_DEAL,MixCardsChoice.EACH_LAUNCHING,MixCardsChoice.ONCE_ONLY,MixCardsChoice.NEVER};
    }
    public AbsCompoFactory getCompoFactory() {
        return getCompo();
    }
    public void setTitleDialog(WindowCardsInt _fenetre, String _title) {
        absDialog.setIconImage(_fenetre.getCommonFrame().getImageIconFrame());
        absDialog.setLocationRelativeTo(_fenetre.getCommonFrame());
        absDialog.setTitle(_title);
    }

    public void closeWindow() {
        absDialog.setVisible(false);
        absDialog.getPane().removeAll();
    }
    public AbsCommonFrame getAbsDialog() {
        return absDialog;
    }

    public AbsCompoFactory getCompo() {
        return compo;
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public AbsButton getValidateButton() {
        return validateButton;
    }

    public void setValidateButton(AbsButton _v) {
        this.validateButton = _v;
    }

    public AbsCommonFrame getCardDialog() {
        return getAbsDialog();
    }
}
