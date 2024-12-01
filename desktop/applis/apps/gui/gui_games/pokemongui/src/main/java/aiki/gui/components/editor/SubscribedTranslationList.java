package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationList {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final RenamingIdPhase renamingIdPhase = new RenamingIdPhase();
    private final IdMap<AbsCommonFrame,IdList<SubscribedTranslation>> subscribedTranslations = new IdMap<AbsCommonFrame, IdList<SubscribedTranslation>>();
    private final SubscribedTranslationMessagesFactoryAb factoryAb = new SubscribedTranslationMessagesFactoryAb();
    private final SubscribedTranslationMessagesFactoryIt factoryIt = new SubscribedTranslationMessagesFactoryIt();
    private final SubscribedTranslationMessagesFactoryMv factoryMv = new SubscribedTranslationMessagesFactoryMv();
    private final SubscribedTranslationMessagesFactoryPk factoryPk = new SubscribedTranslationMessagesFactoryPk();
    private final SubscribedTranslationMessagesFactorySt factorySt = new SubscribedTranslationMessagesFactorySt();
    private final SubscribedTranslationMessagesFactory factoryCa = new SubscribedTranslationMessagesFactoryCa();
    private final SubscribedTranslationMessagesFactory factoryTy = new SubscribedTranslationMessagesFactoryTy();
    private final SubscribedTranslationMessagesNbFactory factoryTm = new SubscribedTranslationMessagesNbFactoryTm();
    private final SubscribedTranslationMessagesNbFactory factoryHm = new SubscribedTranslationMessagesNbFactoryHm();
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat = new SubscribedTranslationMessagesFactoryCstStat();
    private final SubscribedTranslationMessagesFactoryCstTargetChoice factoryTarget = new SubscribedTranslationMessagesFactoryCstTargetChoice();
    private final SubscribedTranslationMessagesFactoryCstGender factoryGender = new SubscribedTranslationMessagesFactoryCstGender();
    private final SubscribedTranslationMessagesFactoryCstEnvironmentType factoryEnvironmentType = new SubscribedTranslationMessagesFactoryCstEnvironmentType();

    public SubscribedTranslationList(AbstractProgramInfos _p, FacadeGame _f) {
        this.programInfos = _p;
        this.facadeGame = _f;
    }

    public void update() {
        for (EntryCust<AbsCommonFrame,IdList<SubscribedTranslation>> f: subscribedTranslations.entryList()) {
            for (SubscribedTranslation s: f.getValue()) {
                s.update(programInfos,facadeGame,renamingIdPhase);
            }
            f.getKey().pack();
        }
    }

    public void updateRenaming(String _oldId, String _newId, StringList _mids) {
        renamingIdPhase.setOldId(_oldId);
        renamingIdPhase.setNewId(_newId);
        renamingIdPhase.setMids(_mids);
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

    public SubscribedTranslationMessagesFactorySt getFactorySt() {
        return factorySt;
    }

    public SubscribedTranslationMessagesFactory getFactoryCa() {
        return factoryCa;
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

    public SubscribedTranslationMessagesFactoryCstStat getFactoryStat() {
        return factoryStat;
    }

    public SubscribedTranslationMessagesFactoryCstTargetChoice getFactoryTarget() {
        return factoryTarget;
    }

    public SubscribedTranslationMessagesFactoryCstGender getFactoryGender() {
        return factoryGender;
    }

    public SubscribedTranslationMessagesFactoryCstEnvironmentType getFactoryEnvironmentType() {
        return factoryEnvironmentType;
    }
}
