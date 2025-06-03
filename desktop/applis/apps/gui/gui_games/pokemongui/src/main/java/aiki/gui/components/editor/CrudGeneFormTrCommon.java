package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public abstract class CrudGeneFormTrCommon implements AbsCrudGeneFormTrCst {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslations;
    private final AbsCommonFrame frame;
    private AbsButton changeValues;

    protected CrudGeneFormTrCommon(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        api = _core;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        frame = _core.getFrameFactory().newCommonFrame();
    }

    protected void footer(AbsPanel _content, AbsPanel _page) {
        _content.add(api.getCompoFactory().newAbsScrollPane(_page));
        changeValues = api.getCompoFactory().newPlainButton("\u21BB");
        changeValues.setForeground(GuiConstants.GREEN);
        changeValues.addActionListener(new CrudGeneFormTrCstEvent(this));
        _content.add(changeValues);
        frame.setContentPane(_content);
        frame.setVisible(true);
        frame.pack();
    }

    public AbstractProgramInfos getApi() {
        return api;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    public SubscribedTranslationList getSubscribedTranslations() {
        return subscribedTranslations;
    }

    public AbsButton getChangeValues() {
        return changeValues;
    }
}
