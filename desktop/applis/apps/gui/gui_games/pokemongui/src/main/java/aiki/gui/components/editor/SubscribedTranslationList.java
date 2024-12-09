package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationList {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final RenamingIdPhase renamingIdPhase = new RenamingIdPhase();

    private final ModifiedEntitiesRename modifiedEntitiesRenameMid = new ModifiedEntitiesRename();
    private final ModifiedEntitiesRename modifiedEntitiesRenamePref = new ModifiedEntitiesRename();

    private final IdMap<AbsCommonFrame,IdList<SubscribedTranslation>> subscribedTranslations = new IdMap<AbsCommonFrame, IdList<SubscribedTranslation>>();
    private final SubscribedTranslationMessagesFactoryAb factoryAb = new SubscribedTranslationMessagesFactoryAb();
    private final SubscribedTranslationMessagesFactoryIt factoryIt = new SubscribedTranslationMessagesFactoryIt();
    private final SubscribedTranslationMessagesFactoryMv factoryMv = new SubscribedTranslationMessagesFactoryMv();
    private final SubscribedTranslationMessagesFactoryPk factoryPk = new SubscribedTranslationMessagesFactoryPk();
    private final SubscribedTranslationMessagesFactorySt factorySt = new SubscribedTranslationMessagesFactorySt();
    private final SubscribedTranslationMessagesFactoryCa factoryCa = new SubscribedTranslationMessagesFactoryCa();
    private final SubscribedTranslationMessagesFactoryTy factoryTy = new SubscribedTranslationMessagesFactoryTy();
    private final SubscribedTranslationMessagesNbFactory factoryTm = new SubscribedTranslationMessagesNbFactoryTm();
    private final SubscribedTranslationMessagesNbFactory factoryHm = new SubscribedTranslationMessagesNbFactoryHm();
    private final SubscribedTranslationMessagesFactoryCstStat factoryStat = new SubscribedTranslationMessagesFactoryCstStat();
    private final SubscribedTranslationMessagesFactoryCstTargetChoice factoryTarget = new SubscribedTranslationMessagesFactoryCstTargetChoice();
    private final SubscribedTranslationMessagesFactoryCstGender factoryGender = new SubscribedTranslationMessagesFactoryCstGender();
    private final SubscribedTranslationMessagesFactoryCstEnvironmentType factoryEnvironmentType = new SubscribedTranslationMessagesFactoryCstEnvironmentType();
    private final SubscribedTranslationMessagesFactoryMiniPk factoryMiniPk = new SubscribedTranslationMessagesFactoryMiniPk();
    private final SubscribedTranslationMessagesFactoryMaxiBackPk factoryMaxiBackPk = new SubscribedTranslationMessagesFactoryMaxiBackPk();
    private final SubscribedTranslationMessagesFactoryMaxiFrontPk factoryMaxiFrontPk = new SubscribedTranslationMessagesFactoryMaxiFrontPk();
    private final SubscribedTranslationMessagesFactoryImgIt factoryMiniIt = new SubscribedTranslationMessagesFactoryImgIt();
    private final SubscribedTranslationMessagesFactoryImgSt factoryMiniSt = new SubscribedTranslationMessagesFactoryImgSt();
    private final SubscribedTranslationMessagesFactoryImgTy factoryMiniTy = new SubscribedTranslationMessagesFactoryImgTy();
    private final ImgRetrieverLinks imgRetrieverLinks = new ImgRetrieverLinks();

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

    public void updateRenamingId(String _oldId, String _newId, StringList _mids) {
        renamingIdPhase.setOldId(_oldId);
        renamingIdPhase.setNewId(_newId);
        renamingIdPhase.setOldMid("");
        renamingIdPhase.setNewMid("");
        renamingIdPhase.setOldPref("");
        renamingIdPhase.setNewPref("");
        renamingIdPhase.setMids(_mids);
    }

    public void updateRenamingMid(String _oldId, String _newId, StringList _mids) {
        renamingIdPhase.setOldId("");
        renamingIdPhase.setNewId("");
        renamingIdPhase.setOldPref("");
        renamingIdPhase.setNewPref("");
        renamingIdPhase.setOldMid(_oldId);
        renamingIdPhase.setNewMid(_newId);
        renamingIdPhase.setMids(_mids);
    }

    public void updateRenamingPref(String _oldId, String _newId, StringList _mids) {
        renamingIdPhase.setOldId("");
        renamingIdPhase.setNewId("");
        renamingIdPhase.setOldPref(_oldId);
        renamingIdPhase.setNewPref(_newId);
        renamingIdPhase.setOldMid("");
        renamingIdPhase.setNewMid("");
        renamingIdPhase.setMids(_mids);
    }

    public ModifiedEntitiesRename getModifiedEntitiesRenameMid() {
        return modifiedEntitiesRenameMid;
    }

    public ModifiedEntitiesRename getModifiedEntitiesRenamePref() {
        return modifiedEntitiesRenamePref;
    }

    public IdMap<AbsCommonFrame,IdList<SubscribedTranslation>> getSubscribedTranslations() {
        return subscribedTranslations;
    }

    /*

    private void cancel() {
        getFactory().getFactoryAb().setEffect(_v);
        getFactory().getFactoryCa().setEffect(_v);
        getFactory().getFactoryIt().setEffect(_v);
        getFactory().getFactoryMv().setEffect(_v);
        getFactory().getFactoryPk().setEffect(_v);
        getFactory().getFactorySt().setEffect(_v);
        getFactory().getFactoryTy().setEffect(_v);
    }*/
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

    public SubscribedTranslationMessagesFactoryCa getFactoryCa() {
        return factoryCa;
    }

    public SubscribedTranslationMessagesFactoryTy getFactoryTy() {
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

    public SubscribedTranslationMessagesFactoryMiniPk getFactoryMiniPk() {
        return factoryMiniPk;
    }

    public SubscribedTranslationMessagesFactoryMaxiBackPk getFactoryMaxiBackPk() {
        return factoryMaxiBackPk;
    }

    public SubscribedTranslationMessagesFactoryMaxiFrontPk getFactoryMaxiFrontPk() {
        return factoryMaxiFrontPk;
    }

    public SubscribedTranslationMessagesFactoryImgIt getFactoryMiniIt() {
        return factoryMiniIt;
    }

    public SubscribedTranslationMessagesFactoryImgSt getFactoryMiniSt() {
        return factoryMiniSt;
    }

    public SubscribedTranslationMessagesFactoryImgTy getFactoryMiniTy() {
        return factoryMiniTy;
    }

    public ImgRetrieverLinks getImgRetrieverLinks() {
        return imgRetrieverLinks;
    }
}
