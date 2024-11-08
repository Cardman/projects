package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationList {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final IdMap<AbsCommonFrame,IdList<SubscribedTranslation>> subscribedTranslations = new IdMap<AbsCommonFrame, IdList<SubscribedTranslation>>();
    private final SubscribedTranslationMessagesFactoryAb factoryAb = new SubscribedTranslationMessagesFactoryAb();
    private final SubscribedTranslationMessagesFactoryIt factoryIt = new SubscribedTranslationMessagesFactoryIt();
    private final SubscribedTranslationMessagesFactoryMv factoryMv = new SubscribedTranslationMessagesFactoryMv();
    private final SubscribedTranslationMessagesFactoryPk factoryPk = new SubscribedTranslationMessagesFactoryPk();
    private final SubscribedTranslationMessagesFactory factoryTy = new SubscribedTranslationMessagesFactoryTy();
    private final SubscribedTranslationMessagesNbFactory factoryTm = new SubscribedTranslationMessagesNbFactoryTm();
    private final SubscribedTranslationMessagesNbFactory factoryHm = new SubscribedTranslationMessagesNbFactoryHm();

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

    public SubscribedTranslationMessagesFactoryAb getFactoryAb() {
        return factoryAb;
    }

    public SubscribedTranslationMessagesFactoryIt getFactoryIt() {
        return factoryIt;
    }

    public SubscribedTranslationMessagesFactoryMv getFactoryMv() {
        return factoryMv;
    }

    public SubscribedTranslationMessagesFactoryPk getFactoryPk() {
        return factoryPk;
    }

    public SubscribedTranslationMessagesFactory getFactoryTy() {
        return factoryTy;
    }

    public SubscribedTranslationMessagesNbFactory getFactoryTm() {
        return factoryTm;
    }

    public SubscribedTranslationMessagesNbFactory getFactoryHm() {
        return factoryHm;
    }
}
