package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationList {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final IdList<SubscribedTranslation> subscribedTranslations = new IdList<SubscribedTranslation>();

    public SubscribedTranslationList(AbstractProgramInfos _p, FacadeGame _f) {
        this.programInfos = _p;
        this.facadeGame = _f;
    }

    public void update(AbsCommonFrame _frame) {
        for (SubscribedTranslation s: subscribedTranslations) {
            s.update(programInfos,facadeGame);
        }
        _frame.pack();
    }

    public IdList<SubscribedTranslation> getSubscribedTranslations() {
        return subscribedTranslations;
    }
}
