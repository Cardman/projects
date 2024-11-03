package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationList {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final IdMap<AbsCommonFrame,IdList<SubscribedTranslation>> subscribedTranslations = new IdMap<AbsCommonFrame, IdList<SubscribedTranslation>>();
    private final SubscribedTranslationMessagesFactory factoryIt = new SubscribedTranslationMessagesFactoryIt();
    private final SubscribedTranslationMessagesFactory factoryMv = new SubscribedTranslationMessagesFactoryMv();
    private final SubscribedTranslationMessagesFactory factoryPk = new SubscribedTranslationMessagesFactoryPk();
    private final SubscribedTranslationMessagesFactory factoryTy = new SubscribedTranslationMessagesFactoryTy();

    public SubscribedTranslationList(AbstractProgramInfos _p, FacadeGame _f) {
        this.programInfos = _p;
        this.facadeGame = _f;
    }

    public void update() {
        for (EntryCust<AbsCommonFrame,IdList<SubscribedTranslation>> f: subscribedTranslations.entryList()) {
            for (SubscribedTranslation s: f.getValue()) {
                s.update(programInfos,facadeGame);
            }
            f.getKey().pack();
        }
    }

    public IdMap<AbsCommonFrame,IdList<SubscribedTranslation>> getSubscribedTranslations() {
        return subscribedTranslations;
    }

    public SubscribedTranslationMessagesFactory getFactoryIt() {
        return factoryIt;
    }

    public SubscribedTranslationMessagesFactory getFactoryMv() {
        return factoryMv;
    }

    public SubscribedTranslationMessagesFactory getFactoryPk() {
        return factoryPk;
    }

    public SubscribedTranslationMessagesFactory getFactoryTy() {
        return factoryTy;
    }
}
