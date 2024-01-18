package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ClosingEditorCards;
import code.gui.AbsDialog;
import code.gui.ComboBox;
import code.gui.GuiBaseUtil;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringList;
import code.util.ints.Listable;

/**
    */

abstract class DialogCards {

    private final AbstractProgramInfos frames;
    private final AbsCompoFactory compoFactory;
    private WindowCardsInt main;
    private final AbsDialog cardDialog;
    private final ClosingEditorCards clos;

    protected DialogCards(AbstractProgramInfos _frameFactory, ClosingEditorCards _ch) {
        frames = _frameFactory;
        if (_ch != null) {
            clos = _ch;
            cardDialog = _frameFactory.getFrameFactory().newDialog(_ch);
        } else {
            clos = new ClosingEditorCards();
            cardDialog = _frameFactory.getFrameFactory().newDialog();
        }
        compoFactory = _frameFactory.getCompoFactory();
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public ComboBox<MixCardsChoice> build(WindowCardsInt _window, MixCardsChoice _m) {
        String lg_ = _window.getLanguageKey();
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

    public ClosingEditorCards getClos() {
        return clos;
    }

    public AbsCompoFactory getCompoFactory() {
        return compoFactory;
    }

    public AbsDialog getCardDialog() {
        return cardDialog;
    }

    public void setMain(WindowCardsInt _main) {
        main = _main;
    }
    public WindowCardsInt getMain() {
        return main;
    }
    public void closeWindow() {
        cardDialog.closeWindow();
        cardDialog.getPane().removeAll();
    }

}
