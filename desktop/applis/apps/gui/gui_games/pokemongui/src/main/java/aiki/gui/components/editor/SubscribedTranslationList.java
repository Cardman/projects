package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.effects.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public final class SubscribedTranslationList {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facadeGame;
    private final RenamingIdPhase renamingIdPhase = new RenamingIdPhase();
    private final RenamingImgNamePhase renamingImgNamePhase = new RenamingImgNamePhase();
    private final RemovingPlacePhase removingPlacePhase = new RemovingPlacePhase();

    private final ModifiedEntitiesRename modifiedEntitiesRename = new ModifiedEntitiesRename();

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
    private final SubscribedTranslationMessagesFactoryCstDifficultyModelLaw factoryDifficultyModelLaw = new SubscribedTranslationMessagesFactoryCstDifficultyModelLaw();
    private final SubscribedTranslationMessagesFactoryCstDifficultyWinPointsFight factoryDifficultyWinPointsFight = new SubscribedTranslationMessagesFactoryCstDifficultyWinPointsFight();
    private final SubscribedTranslationMessagesFactoryCstSelectedBoolean factorySelectedBoolean = new SubscribedTranslationMessagesFactoryCstSelectedBoolean();
    private final SubscribedTranslationMessagesFactoryMiniPk factoryMiniPk = new SubscribedTranslationMessagesFactoryMiniPk();
    private final SubscribedTranslationMessagesFactoryMaxiBackPk factoryMaxiBackPk = new SubscribedTranslationMessagesFactoryMaxiBackPk();
    private final SubscribedTranslationMessagesFactoryMaxiFrontPk factoryMaxiFrontPk = new SubscribedTranslationMessagesFactoryMaxiFrontPk();
    private final SubscribedTranslationMessagesFactoryImgIt factoryMiniIt = new SubscribedTranslationMessagesFactoryImgIt();
    private final SubscribedTranslationMessagesFactoryImgSt factoryMiniSt = new SubscribedTranslationMessagesFactoryImgSt();
    private final SubscribedTranslationMessagesFactoryImgTy factoryMiniTy = new SubscribedTranslationMessagesFactoryImgTy();
    private final ImgRetrieverLinks imgRetrieverLinks = new ImgRetrieverLinks();
    private final ImgRetrieverPeople imgRetrieverPeople = new ImgRetrieverPeople();
    private final ImgRetrieverTrainers imgRetrieverTrainers = new ImgRetrieverTrainers();
    private final ImgRetrieverBlocks imgRetrieverBlocks = new ImgRetrieverBlocks();
    private final ImgRetrieverMiniMap imgRetrieverMiniMap = new ImgRetrieverMiniMap();
    private final ImgFieldRetrieverAnimAbsorb imgFieldRetrieverAnimAbsorb = new ImgFieldRetrieverAnimAbsorb();
    private final ImgFieldRetrieverEndGame imgFieldRetrieverEndGame = new ImgFieldRetrieverEndGame();
    private final ImgFieldRetrieverStorage imgFieldRetrieverStorage = new ImgFieldRetrieverStorage();
    private final ImgFieldRetrieverTmHm imgFieldRetrieverTmHm = new ImgFieldRetrieverTmHm();
    private final SubscribedTranslationMessagesFactoryImgName imgRetrieverMiniMapSub = new SubscribedTranslationMessagesFactoryImgName(imgRetrieverMiniMap);
    private final SubscribedTranslationMessagesFactoryImgName imgRetrieverBlocksSub = new SubscribedTranslationMessagesFactoryImgName(imgRetrieverBlocks);
    private final SubscribedTranslationMessagesFactoryImgName imgRetrieverLinksSub = new SubscribedTranslationMessagesFactoryImgName(imgRetrieverLinks);
    private final SubscribedTranslationMessagesFactoryImgName imgRetrieverMiniSub = new SubscribedTranslationMessagesFactoryImgName(imgRetrieverPeople);
    private final SubscribedTranslationMessagesFactoryImgName imgRetrieverMaxiSub = new SubscribedTranslationMessagesFactoryImgName(imgRetrieverTrainers);
    private FormLevelGrid formLevelGridUniq;

    public SubscribedTranslationList(AbstractProgramInfos _p, FacadeGame _f) {
        this.programInfos = _p;
        this.facadeGame = _f;
    }

    public void update() {
        for (EntryCust<AbsCommonFrame,IdList<SubscribedTranslation>> f: subscribedTranslations.entryList()) {
            for (SubscribedTranslation s: f.getValue()) {
                s.update(programInfos,facadeGame, this);
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
        renamingImgNamePhase.setOldId("");
        renamingImgNamePhase.setNewId("");
        renamingImgNamePhase.setRetriever(null);
    }

    public void updateRenamingMid(String _oldId, String _newId, StringList _mids) {
        renamingIdPhase.setOldId("");
        renamingIdPhase.setNewId("");
        renamingIdPhase.setOldPref("");
        renamingIdPhase.setNewPref("");
        renamingIdPhase.setOldMid(_oldId);
        renamingIdPhase.setNewMid(_newId);
        renamingIdPhase.setMids(_mids);
        renamingImgNamePhase.setOldId("");
        renamingImgNamePhase.setNewId("");
        renamingImgNamePhase.setRetriever(null);
    }

    public void updateRenamingPref(String _oldId, String _newId, StringList _mids) {
        renamingIdPhase.setOldId("");
        renamingIdPhase.setNewId("");
        renamingIdPhase.setOldPref(_oldId);
        renamingIdPhase.setNewPref(_newId);
        renamingIdPhase.setOldMid("");
        renamingIdPhase.setNewMid("");
        renamingIdPhase.setMids(_mids);
        renamingImgNamePhase.setOldId("");
        renamingImgNamePhase.setNewId("");
        renamingImgNamePhase.setRetriever(null);
    }

    public void updateRenamingFileName(String _oldId, String _newId, ImgRetriever _ir) {
        renamingIdPhase.setOldId("");
        renamingIdPhase.setNewId("");
        renamingIdPhase.setOldMid("");
        renamingIdPhase.setNewMid("");
        renamingIdPhase.setOldPref("");
        renamingIdPhase.setNewPref("");
        renamingIdPhase.setMids(new StringList());
        renamingImgNamePhase.setOldId(_oldId);
        renamingImgNamePhase.setNewId(_newId);
        renamingImgNamePhase.setRetriever(_ir);
    }
    public void effect(Effect _v) {
        getModifiedEntitiesRename().setEffect(_v);
        getFactoryAb().setEffect(_v);
        getFactoryCa().setEffect(_v);
        getFactoryIt().setEffect(_v);
        getFactoryMv().setEffect(_v);
        getFactoryPk().setEffect(_v);
        getFactorySt().setEffect(_v);
        getFactoryTy().setEffect(_v);
    }

    public void effectEndRoundAbility(EffectEndRound _v) {
        getModifiedEntitiesRename().setEffectEndRoundAbility(_v);
        getFactoryAb().setEffectEndRoundAbility(_v);
        getFactoryCa().setEffectEndRoundAbility(_v);
        getFactoryIt().setEffectEndRoundAbility(_v);
        getFactoryMv().setEffectEndRoundAbility(_v);
        getFactoryPk().setEffectEndRoundAbility(_v);
        getFactorySt().setEffectEndRoundAbility(_v);
        getFactoryTy().setEffectEndRoundAbility(_v);
    }
    public void effectEndRoundCombo(EffectEndRound _v) {
        getModifiedEntitiesRename().setEffectEndRoundCombo(_v);
        getFactoryAb().setEffectEndRoundCombo(_v);
        getFactoryCa().setEffectEndRoundCombo(_v);
        getFactoryIt().setEffectEndRoundCombo(_v);
        getFactoryMv().setEffectEndRoundCombo(_v);
        getFactoryPk().setEffectEndRoundCombo(_v);
        getFactorySt().setEffectEndRoundCombo(_v);
        getFactoryTy().setEffectEndRoundCombo(_v);
    }
    public void effectEndRoundItem(EffectEndRound _v) {
        getModifiedEntitiesRename().setEffectEndRoundItem(_v);
        getFactoryAb().setEffectEndRoundItem(_v);
        getFactoryCa().setEffectEndRoundItem(_v);
        getFactoryIt().setEffectEndRoundItem(_v);
        getFactoryMv().setEffectEndRoundItem(_v);
        getFactoryPk().setEffectEndRoundItem(_v);
        getFactorySt().setEffectEndRoundItem(_v);
        getFactoryTy().setEffectEndRoundItem(_v);
    }
    public void effectEndRoundStatus(EffectEndRound _v) {
        getModifiedEntitiesRename().setEffectEndRoundStatus(_v);
        getFactoryAb().setEffectEndRoundStatus(_v);
        getFactoryCa().setEffectEndRoundStatus(_v);
        getFactoryIt().setEffectEndRoundStatus(_v);
        getFactoryMv().setEffectEndRoundStatus(_v);
        getFactoryPk().setEffectEndRoundStatus(_v);
        getFactorySt().setEffectEndRoundStatus(_v);
        getFactoryTy().setEffectEndRoundStatus(_v);
    }
    public void effectSendingAbility(EffectWhileSendingWithStatistic _v) {
        getModifiedEntitiesRename().setEffectSendingAbility(_v);
        getFactoryAb().setEffectSendingAbility(_v);
        getFactoryCa().setEffectSendingAbility(_v);
        getFactoryIt().setEffectSendingAbility(_v);
        getFactoryMv().setEffectSendingAbility(_v);
        getFactoryPk().setEffectSendingAbility(_v);
        getFactorySt().setEffectSendingAbility(_v);
        getFactoryTy().setEffectSendingAbility(_v);
    }

    public void effectSendingItem(EffectWhileSendingWithStatistic _v) {
        getModifiedEntitiesRename().setEffectSendingItem(_v);
        getFactoryAb().setEffectSendingItem(_v);
        getFactoryCa().setEffectSendingItem(_v);
        getFactoryIt().setEffectSendingItem(_v);
        getFactoryMv().setEffectSendingItem(_v);
        getFactoryPk().setEffectSendingItem(_v);
        getFactorySt().setEffectSendingItem(_v);
        getFactoryTy().setEffectSendingItem(_v);
    }

    public void itemForBattle(Item _v) {
        getModifiedEntitiesRename().setItemForBattle(_v);
        getFactoryAb().setItemForBattle(_v);
        getFactoryCa().setItemForBattle(_v);
        getFactoryIt().setItemForBattle(_v);
        getFactoryMv().setItemForBattle(_v);
        getFactoryPk().setItemForBattle(_v);
        getFactorySt().setItemForBattle(_v);
        getFactoryTy().setItemForBattle(_v);
    }

    public static AbsCustComponent line(AbstractProgramInfos _api,String _file,String _key, AbsCustComponent _input) {
        return lineDir(_api, formatTxt(_api, _file, _key),_input);
    }

    public static String formatTxt(AbstractProgramInfos _api, String _file, String _key) {
        TranslationsFile val_ = _api.currentLg().getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().getVal(_file);
        if (val_ == null) {
            return "";
        }
        return StringUtil.nullToEmpty(val_.getMapping().getVal(_key));
    }

    public static AbsCustComponent lineDir(AbstractProgramInfos _api,String _txt, AbsCustComponent _input) {
        AbsCompoFactory compoFactory_ = _api.getCompoFactory();
        AbsPanel line_ = compoFactory_.newLineBox();
        line_.add(compoFactory_.newPlainLabel(_txt));
        line_.add(_input);
        return line_;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public FormLevelGrid getFormLevelGridUniq() {
        return formLevelGridUniq;
    }

    public void setFormLevelGridUniq(FormLevelGrid _f) {
        this.formLevelGridUniq = _f;
    }

    public RenamingIdPhase getRenamingIdPhase() {
        return renamingIdPhase;
    }

    public RenamingImgNamePhase getRenamingImgNamePhase() {
        return renamingImgNamePhase;
    }

    public RemovingPlacePhase getRemovingPlacePhase() {
        return removingPlacePhase;
    }

    public ModifiedEntitiesRename getModifiedEntitiesRename() {
        return modifiedEntitiesRename;
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

    public SubscribedTranslationMessagesFactoryCstDifficultyModelLaw getFactoryDifficultyModelLaw() {
        return factoryDifficultyModelLaw;
    }

    public SubscribedTranslationMessagesFactoryCstDifficultyWinPointsFight getFactoryDifficultyWinPointsFight() {
        return factoryDifficultyWinPointsFight;
    }

    public SubscribedTranslationMessagesFactoryCstSelectedBoolean getFactorySelectedBoolean() {
        return factorySelectedBoolean;
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

    public ImgRetrieverPeople getImgRetrieverPeople() {
        return imgRetrieverPeople;
    }

    public ImgRetrieverTrainers getImgRetrieverTrainers() {
        return imgRetrieverTrainers;
    }

    public ImgRetrieverBlocks getImgRetrieverBlocks() {
        return imgRetrieverBlocks;
    }

    public ImgRetrieverMiniMap getImgRetrieverMiniMap() {
        return imgRetrieverMiniMap;
    }

    public ImgFieldRetrieverAnimAbsorb getImgFieldRetrieverAnimAbsorb() {
        return imgFieldRetrieverAnimAbsorb;
    }

    public ImgFieldRetrieverEndGame getImgFieldRetrieverEndGame() {
        return imgFieldRetrieverEndGame;
    }

    public ImgFieldRetrieverStorage getImgFieldRetrieverStorage() {
        return imgFieldRetrieverStorage;
    }

    public ImgFieldRetrieverTmHm getImgFieldRetrieverTmHm() {
        return imgFieldRetrieverTmHm;
    }

    public SubscribedTranslationMessagesFactoryImgName getImgRetrieverMiniMapSub() {
        return imgRetrieverMiniMapSub;
    }

    public SubscribedTranslationMessagesFactoryImgName getImgRetrieverBlocksSub() {
        return imgRetrieverBlocksSub;
    }

    public SubscribedTranslationMessagesFactoryImgName getImgRetrieverLinksSub() {
        return imgRetrieverLinksSub;
    }

    public SubscribedTranslationMessagesFactoryImgName getImgRetrieverMiniSub() {
        return imgRetrieverMiniSub;
    }

    public SubscribedTranslationMessagesFactoryImgName getImgRetrieverMaxiSub() {
        return imgRetrieverMaxiSub;
    }
}
