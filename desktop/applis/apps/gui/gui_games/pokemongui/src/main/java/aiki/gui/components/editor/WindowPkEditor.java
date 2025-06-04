package aiki.gui.components.editor;

//import aiki.gui.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.status.*;
import aiki.game.params.enums.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
//import code.stream.*;
import code.util.*;

public final class WindowPkEditor extends GroupFrame implements AbsOpenQuit {
    private final SubscribedTranslationList subscriptions;
    private final CrudGeneFormTr crudGeneFormAbTr;
    private final CrudGeneFormTr crudGeneFormItTr;
    private final CrudGeneFormTr crudGeneFormMvTr;
    private final CrudGeneFormTr crudGeneFormPkTr;
    private final CrudGeneFormTr crudGeneFormStTr;
    private final CrudGeneFormTr crudGeneFormCaTr;
    private final CrudGeneFormTr crudGeneFormTyTr;
    private final CrudGeneFormTrItemType crudGeneFormClTr;
    private final CrudGeneFormTrItemType crudGeneFormFctTr;
    private final CrudGeneFormTrCstParamListString<ExpType> crudGeneFormExpTypeTr;
    private final CrudGeneFormTrCstLaw crudGeneFormDifficultyModelLawTr;
    private final CrudGeneFormTrCstParamListString<DifficultyWinPointsFight> crudGeneFormDifficultyWinPointsFightTr;
    private final CrudGeneFormTrCstList crudGeneFormTrCstList;
    private final CrudGeneFormTrOtherCstList crudGeneFormTrOtherCstList;
    private final CrudGeneFormTrCstNumList crudGeneFormTrCstNumList;
    private final CrudGeneFormNb crudGeneFormTm;
    private final CrudGeneFormNb crudGeneFormHm;
    private final CrudGeneFormTrCst<Statistic> crudGeneFormCstStat;
    private final CrudGeneFormTrCst<TargetChoice> crudGeneFormCstTarget;
    private final CrudGeneFormTrCst<Gender> crudGeneFormCstGender;
    private final CrudGeneFormTrCst<EnvironmentType> crudGeneFormCstEnvironmentType;
    private final CrudGeneFormTrCst<DifficultyModelLaw> crudGeneFormCstDifficultyModelLaw;
    private final CrudGeneFormTrCst<DifficultyWinPointsFight> crudGeneFormCstDifficultyWinPointsFight;
    private final CrudGeneFormTrCst<SelectedBoolean> crudGeneFormCstSelectedBoolean;
    private final CrudGeneFormEnt<AbilityData> crudGeneFormAb;
    private final CrudGeneFormEnt<Item> crudGeneFormIt;
    private final CrudGeneFormEnt<MoveData> crudGeneFormMv;
    private final CrudGeneFormEnt<PokemonData> crudGeneFormPk;
    private final CrudGeneFormEnt<Status> crudGeneFormSt;
    private final CrudGeneFormCombos crudGeneFormCombos;
    private final CrudGeneFormTypes crudGeneFormTypes;
    private final CrudGeneFormEntImg crudGeneFormMiniPk;
    private final CrudGeneFormEntImg crudGeneFormMaxiBackPk;
    private final CrudGeneFormEntImg crudGeneFormMaxiFrontPk;
    private final CrudGeneFormEntImg crudGeneFormMiniIt;
    private final CrudGeneFormEntImg crudGeneFormMiniSt;
    private final CrudGeneFormEntImg crudGeneFormMiniTy;
    private final CrudGeneFormEntImgFree crudGeneFormLinks;
    private final CrudGeneFormEntImgFree crudGeneFormPeople;
    private final CrudGeneFormEntImgFree crudGeneFormTrainers;
    private final CrudGeneFormEntImgFree crudGeneFormBlocks;
    private final CrudGeneFormEntImgFree crudGeneFormMiniMap;
    private final CrudGeneFormEntImgType crudGeneFormTypeColor;
    private final CrudGeneFormEntImgUniq crudGeneFormImgOther;
    private final CrudGeneFormEntImgCstList crudGeneFormCstList;
    private final CrudGeneFormEntImgHeros crudGeneFormEntImgHerosMini;
    private final CrudGeneFormEntImgHeros crudGeneFormEntImgHerosFront;
    private final CrudGeneFormEntImgHeros crudGeneFormEntImgHerosBack;
    private final StringMap<String> mappingMenus = MessagesPkEditor.getMessagesEditorSelectMenusTr(MessagesPkEditor.getAppliTr(getFrames().currentLg())).getMapping();
    private final EnabledMenu trsAbMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_AB));
    private final EnabledMenu trsItMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_IT));
    private final EnabledMenu trsMvMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_MV));
    private final EnabledMenu trsPkMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_PK));
    private final EnabledMenu trsStMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_ST));
    private final EnabledMenu trsCaMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_CA));
    private final EnabledMenu trsTyMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_TY));
    private final EnabledMenu trsClMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_CL));
    private final EnabledMenu trsFctMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_FCT));
    private final EnabledMenu trsExpTypeMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_ET));
    private final EnabledMenu trsDifficultyModelLawMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_DIFF_MODEL));
    private final EnabledMenu trsDifficultyWinPointsFightMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_DIFF_WIN));
    private final EnabledMenu trsConstMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_CONST));
    private final EnabledMenu trsOtherConstMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_CONST_OTHER));
    private final EnabledMenu trsNumConstMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT_CONST_NUM));
    private final EnabledMenu tmMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TM_HM_0));
    private final EnabledMenu hmMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TM_HM_1));
    private final EnabledMenu abMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_AB));
    private final EnabledMenu itMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_IT));
    private final EnabledMenu mvMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_MV));
    private final EnabledMenu pkMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_PK));
    private final EnabledMenu stMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_ST));
    private final EnabledMenu combosMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_CO));
    private final EnabledMenu typesMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.ENT_TY));
    private final EnabledMenu trsCstStatMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_STAT));
    private final EnabledMenu trsCstTargetMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_TARGET));
    private final EnabledMenu trsCstGenderMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_GENDER));
    private final EnabledMenu trsCstEnvironmentTypeMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_ENV));
    private final EnabledMenu trsCstDifficultyModelLawMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_DIFF_MODEL));
    private final EnabledMenu trsCstDifficultyWinPointsFightMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_DIFF_WIN));
    private final EnabledMenu trsCstSelectedBooleanMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.TRS_CST_SEL));
    private final EnabledMenu imgMiniPkMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_PK_MINI));
    private final EnabledMenu imgMaxiBackPkMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_PK_BACK));
    private final EnabledMenu imgMaxiFrontPkMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_PK_FRONT));
    private final EnabledMenu imgMiniItMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_IT));
    private final EnabledMenu imgMiniStMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_ST));
    private final EnabledMenu imgMiniTyMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_TY));
    private final EnabledMenu imgLinksMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_LK));
    private final EnabledMenu imgPeopleMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_PEOPLE));
    private final EnabledMenu imgTrainersMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_TR));
    private final EnabledMenu imgBlocksMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_BK));
    private final EnabledMenu imgMiniMapMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_MAP));
    private final EnabledMenu imgTypeColorMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_TYPE));
    private final EnabledMenu imgOtherMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_OTHER));
    private final EnabledMenu imgCstListMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_BOOST));
    private final EnabledMenu imgHerosMiniMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_HEROS_MINI));
    private final EnabledMenu imgHerosFrontMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_HEROS_FRONT));
    private final EnabledMenu imgHerosBackMenu = getFrames().getCompoFactory().newMenuItem(mappingMenus.getVal(MessagesEditorSelect.IMG_HEROS_BACK));
    private final FormDataMap formDataMap;
    private final FacadeGame facade = ConverterCommonMapUtil.facadeInit(getFrames());

    public WindowPkEditor(AbstractProgramInfos _list) {
        super(_list);
        facade.setData(ConverterCommonMapUtil.newData(_list,facade));
        facade.updateTrs();
        subscriptions = new SubscribedTranslationList(_list, facade);
        IdList<SubscribedTranslation> subsTm_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTm_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTm_, subsTm_);
        IdList<SubscribedTranslation> subsHm_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frHm_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frHm_, subsHm_);
        crudGeneFormAbTr = buildTr(_list, facade,trsAbMenu,subscriptions.getFactoryAb());
        crudGeneFormItTr = buildTr(_list, facade,trsItMenu,subscriptions.getFactoryIt());
        crudGeneFormMvTr = buildTr(_list, facade,trsMvMenu,subscriptions.getFactoryMv());
        crudGeneFormPkTr = buildTr(_list, facade,trsPkMenu,subscriptions.getFactoryPk());
        crudGeneFormStTr = buildTr(_list, facade,trsStMenu,subscriptions.getFactorySt());
        crudGeneFormCaTr = buildTr(_list, facade,trsCaMenu,subscriptions.getFactoryCa());
        crudGeneFormTyTr = buildTr(_list, facade,trsTyMenu,subscriptions.getFactoryTy());
        crudGeneFormClTr = new CrudGeneFormTrItemType(_list, facade,subscriptions, false);
        events(crudGeneFormClTr.getFrame(), trsClMenu, crudGeneFormClTr, new IdList<SubscribedTranslation>());
        crudGeneFormFctTr = new CrudGeneFormTrItemType(_list, facade,subscriptions, true);
        events(crudGeneFormFctTr.getFrame(), trsFctMenu, crudGeneFormFctTr, new IdList<SubscribedTranslation>());
        crudGeneFormExpTypeTr = new CrudGeneFormTrCstParamListString<ExpType>(_list, facade, new NumStrIdRetrieverExpType());
        events(crudGeneFormExpTypeTr.getFrame(), trsExpTypeMenu, crudGeneFormExpTypeTr, new IdList<SubscribedTranslation>());
        crudGeneFormDifficultyModelLawTr = new CrudGeneFormTrCstLaw(_list, facade);
        events(crudGeneFormDifficultyModelLawTr.getFrame(), trsDifficultyModelLawMenu, crudGeneFormDifficultyModelLawTr, new IdList<SubscribedTranslation>());
        crudGeneFormDifficultyWinPointsFightTr = new CrudGeneFormTrCstParamListString<DifficultyWinPointsFight>(_list, facade,new NumStrIdRetrieverDifficultyWinPointsFight());
        events(crudGeneFormDifficultyWinPointsFightTr.getFrame(), trsDifficultyWinPointsFightMenu, crudGeneFormDifficultyWinPointsFightTr, new IdList<SubscribedTranslation>());
        crudGeneFormTrCstList = new CrudGeneFormTrCstList(_list, facade, subscriptions);
        events(crudGeneFormTrCstList.getFrame(), trsConstMenu, crudGeneFormTrCstList, new IdList<SubscribedTranslation>());
        crudGeneFormTrOtherCstList = new CrudGeneFormTrOtherCstList(_list, facade);
        events(crudGeneFormTrOtherCstList.getFrame(), trsOtherConstMenu, crudGeneFormTrOtherCstList, new IdList<SubscribedTranslation>());
        crudGeneFormTrCstNumList = new CrudGeneFormTrCstNumList(_list, facade,subscriptions);
        events(crudGeneFormTrCstNumList.getFrame(), trsNumConstMenu, crudGeneFormTrCstNumList, new IdList<SubscribedTranslation>());
        crudGeneFormCstStat = new CrudGeneFormTrCst<Statistic>(_list,facade,subscriptions,subscriptions.getFactoryStat());
        events(crudGeneFormCstStat.getFrame(), trsCstStatMenu, crudGeneFormCstStat, new IdList<SubscribedTranslation>());
        crudGeneFormCstTarget = new CrudGeneFormTrCst<TargetChoice>(_list,facade,subscriptions,subscriptions.getFactoryTarget());
        events(crudGeneFormCstTarget.getFrame(), trsCstTargetMenu, crudGeneFormCstTarget, new IdList<SubscribedTranslation>());
        crudGeneFormCstGender = new CrudGeneFormTrCst<Gender>(_list,facade,subscriptions,subscriptions.getFactoryGender());
        events(crudGeneFormCstGender.getFrame(), trsCstGenderMenu, crudGeneFormCstGender, new IdList<SubscribedTranslation>());
        crudGeneFormCstEnvironmentType = new CrudGeneFormTrCst<EnvironmentType>(_list,facade,subscriptions,subscriptions.getFactoryEnvironmentType());
        events(crudGeneFormCstEnvironmentType.getFrame(), trsCstEnvironmentTypeMenu, crudGeneFormCstEnvironmentType, new IdList<SubscribedTranslation>());
        crudGeneFormCstDifficultyModelLaw = new CrudGeneFormTrCst<DifficultyModelLaw>(_list,facade,subscriptions,subscriptions.getFactoryDifficultyModelLaw());
        events(crudGeneFormCstDifficultyModelLaw.getFrame(), trsCstDifficultyModelLawMenu, crudGeneFormCstDifficultyModelLaw, new IdList<SubscribedTranslation>());
        crudGeneFormCstDifficultyWinPointsFight = new CrudGeneFormTrCst<DifficultyWinPointsFight>(_list,facade,subscriptions,subscriptions.getFactoryDifficultyWinPointsFight());
        events(crudGeneFormCstDifficultyWinPointsFight.getFrame(), trsCstDifficultyWinPointsFightMenu, crudGeneFormCstDifficultyWinPointsFight, new IdList<SubscribedTranslation>());
        crudGeneFormCstSelectedBoolean = new CrudGeneFormTrCst<SelectedBoolean>(_list,facade,subscriptions,subscriptions.getFactorySelectedBoolean());
        events(crudGeneFormCstSelectedBoolean.getFrame(), trsCstSelectedBooleanMenu, crudGeneFormCstSelectedBoolean, new IdList<SubscribedTranslation>());
        crudGeneFormTm = new CrudGeneFormNb(_list, facade,subscriptions, frTm_,subscriptions.getFactoryTm(), true);
        crudGeneFormHm = new CrudGeneFormNb(_list, facade,subscriptions, frHm_,subscriptions.getFactoryHm(), false);
        crudGeneFormAb = new CrudGeneFormEntBuilder<AbilityData>().build(_list,facade,subscriptions, abMenu, subscriptions.getFactoryAb());
        crudGeneFormIt = new CrudGeneFormEntBuilder<Item>().build(_list,facade,subscriptions, itMenu, subscriptions.getFactoryIt());
        crudGeneFormMv = new CrudGeneFormEntBuilder<MoveData>().build(_list,facade,subscriptions, mvMenu, subscriptions.getFactoryMv());
        crudGeneFormPk = new CrudGeneFormEntBuilder<PokemonData>().build(_list, facade,subscriptions, pkMenu, subscriptions.getFactoryPk());
        crudGeneFormSt = new CrudGeneFormEntBuilder<Status>().build(_list, facade,subscriptions, stMenu, subscriptions.getFactorySt());
        crudGeneFormCombos = buildCombos(_list, facade, combosMenu);
        crudGeneFormTypes = buildTypes(_list, facade, typesMenu);
        crudGeneFormMiniPk = buildImg(_list, facade, imgMiniPkMenu,subscriptions.getFactoryMiniPk());
        crudGeneFormMaxiBackPk = buildImg(_list, facade, imgMaxiBackPkMenu,subscriptions.getFactoryMaxiBackPk());
        crudGeneFormMaxiFrontPk = buildImg(_list, facade, imgMaxiFrontPkMenu,subscriptions.getFactoryMaxiFrontPk());
        crudGeneFormMiniIt = buildImg(_list, facade, imgMiniItMenu,subscriptions.getFactoryMiniIt());
        crudGeneFormMiniSt = buildImg(_list, facade, imgMiniStMenu,subscriptions.getFactoryMiniSt());
        crudGeneFormMiniTy = buildImg(_list, facade, imgMiniTyMenu,subscriptions.getFactoryMiniTy());
        crudGeneFormLinks = buildImgFree(_list, facade, imgLinksMenu,subscriptions.getImgRetrieverLinks());
        crudGeneFormPeople = buildImgFree(_list, facade, imgPeopleMenu,subscriptions.getImgRetrieverPeople());
        crudGeneFormTrainers = buildImgFree(_list, facade, imgTrainersMenu,subscriptions.getImgRetrieverTrainers());
        crudGeneFormBlocks = buildImgFree(_list, facade, imgBlocksMenu,subscriptions.getImgRetrieverBlocks());
        crudGeneFormMiniMap = buildImgFree(_list, facade, imgMiniMapMenu,subscriptions.getImgRetrieverMiniMap());
        crudGeneFormTypeColor = buildImgTypeColor(_list, facade, imgTypeColorMenu);
        crudGeneFormImgOther = buildImgOther(_list, facade, imgOtherMenu);
        crudGeneFormCstList = buildImgCstList(_list, facade, imgCstListMenu);
        crudGeneFormEntImgHerosMini = buildImgHeros(_list, facade, imgHerosMiniMenu,true,false);
        crudGeneFormEntImgHerosFront = buildImgHeros(_list, facade, imgHerosFrontMenu, false, true);
        crudGeneFormEntImgHerosBack = buildImgHeros(_list, facade, imgHerosBackMenu, false, false);
        AbsMenuBar bar_ = getFrames().getCompoFactory().newMenuBar();
//        EnabledMenu file_ = getFrames().getCompoFactory().newMenu("0");
        EnabledMenu trs_ = getFrames().getCompoFactory().newMenu(mappingMenus.getVal(MessagesEditorSelect.TRS_ENT));
        trs_.addMenuItem(trsAbMenu);
        trs_.addMenuItem(trsItMenu);
        trs_.addMenuItem(trsMvMenu);
        trs_.addMenuItem(trsPkMenu);
        trs_.addMenuItem(trsStMenu);
        trs_.addMenuItem(trsCaMenu);
        trs_.addMenuItem(trsTyMenu);
        trs_.addMenuItem(trsClMenu);
        trs_.addMenuItem(trsFctMenu);
        trs_.addMenuItem(trsExpTypeMenu);
        trs_.addMenuItem(trsDifficultyModelLawMenu);
        trs_.addMenuItem(trsDifficultyWinPointsFightMenu);
        trs_.addMenuItem(trsConstMenu);
        trs_.addMenuItem(trsOtherConstMenu);
        trs_.addMenuItem(trsNumConstMenu);
        bar_.add(trs_);
        EnabledMenu tmHm_ = getFrames().getCompoFactory().newMenu(mappingMenus.getVal(MessagesEditorSelect.TM_HM));
        tmHm_.addMenuItem(tmMenu);
        tmHm_.addMenuItem(hmMenu);
        bar_.add(tmHm_);
        EnabledMenu ent_ = getFrames().getCompoFactory().newMenu(mappingMenus.getVal(MessagesEditorSelect.ENT));
        ent_.addMenuItem(abMenu);
        ent_.addMenuItem(itMenu);
        ent_.addMenuItem(mvMenu);
        ent_.addMenuItem(pkMenu);
        ent_.addMenuItem(stMenu);
        ent_.addMenuItem(combosMenu);
        ent_.addMenuItem(typesMenu);
        bar_.add(ent_);
        EnabledMenu trsCst_ = getFrames().getCompoFactory().newMenu(mappingMenus.getVal(MessagesEditorSelect.TRS_CST));
        trsCst_.addMenuItem(trsCstStatMenu);
        trsCst_.addMenuItem(trsCstTargetMenu);
        trsCst_.addMenuItem(trsCstGenderMenu);
        trsCst_.addMenuItem(trsCstEnvironmentTypeMenu);
        trsCst_.addMenuItem(trsCstDifficultyModelLawMenu);
        trsCst_.addMenuItem(trsCstDifficultyWinPointsFightMenu);
        trsCst_.addMenuItem(trsCstSelectedBooleanMenu);
        bar_.add(trsCst_);
        EnabledMenu img_ = getFrames().getCompoFactory().newMenu(mappingMenus.getVal(MessagesEditorSelect.IMG));
        img_.addMenuItem(imgMiniPkMenu);
        img_.addMenuItem(imgMaxiBackPkMenu);
        img_.addMenuItem(imgMaxiFrontPkMenu);
        img_.addMenuItem(imgMiniItMenu);
        img_.addMenuItem(imgMiniStMenu);
        img_.addMenuItem(imgMiniTyMenu);
        img_.addMenuItem(imgLinksMenu);
        img_.addMenuItem(imgPeopleMenu);
        img_.addMenuItem(imgTrainersMenu);
        img_.addMenuItem(imgBlocksMenu);
        img_.addMenuItem(imgMiniMapMenu);
        img_.addMenuItem(imgTypeColorMenu);
        img_.addMenuItem(imgCstListMenu);
        img_.addMenuItem(imgHerosMiniMenu);
        img_.addMenuItem(imgHerosFrontMenu);
        img_.addMenuItem(imgHerosBackMenu);
        img_.addMenuItem(imgOtherMenu);
        bar_.add(img_);
        tmMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormTm,tmMenu));
        hmMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormHm,hmMenu));
        getCommonFrame().setJMenuBar(bar_);
        crudGeneFormTm.getFrame().addWindowListener(new ReinitMenu(tmMenu, subsTm_));
        crudGeneFormHm.getFrame().addWindowListener(new ReinitMenu(hmMenu, subsHm_));
        addWindowListener(new QuittingEvent(this));
        IdList<SubscribedTranslation> main_ = new IdList<SubscribedTranslation>();
        subscriptions.getSubscribedTranslations().addEntry(getCommonFrame(),main_);
        formDataMap = new FormDataMap(this,facade,subscriptions, main_);
        formDataMap.updateValues();
        getCommonFrame().setContentPane(formDataMap.getForm());
        getCommonFrame().setVisible(true);
        getCommonFrame().pack();
    }
    public void dataBase(DataBase _db) {
        facade.setData(_db);
        facade.updateTrs();
        formDataMap.displayFirstPk();
        getCommonFrame().pack();
    }

    public CrudGeneFormCombos buildCombos(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormCombos crud_ = new CrudGeneFormCombos(_list, _facade, subscriptions, e_.getKey());
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }
    public CrudGeneFormTypes buildTypes(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormTypes crud_ = new CrudGeneFormTypes(_list, _facade, subscriptions, e_.getKey());
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }
    public CrudGeneFormEntImg buildImg(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu, SubscribedTranslationMessagesFactoryCommonInt<ImageArrayBaseSixtyFour> _factory) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormEntImg crud_ = new CrudGeneFormEntImg(_list, _facade, subscriptions, e_.getKey(), _factory);
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }
    public CrudGeneFormEntImgFree buildImgFree(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu, ImgRetriever _factory) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormEntImgFree crud_ = new CrudGeneFormEntImgFree(_list, _facade, subscriptions, e_.getKey(), _factory);
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }

    public CrudGeneFormEntImgType buildImgTypeColor(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormEntImgType crud_ = new CrudGeneFormEntImgType(_list, _facade, subscriptions, e_.getKey());
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }

    public CrudGeneFormEntImgUniq buildImgOther(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormEntImgUniq crud_ = new CrudGeneFormEntImgUniq(_list, _facade, subscriptions, e_.getKey());
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }

    public CrudGeneFormEntImgCstList buildImgCstList(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormEntImgCstList crud_ = new CrudGeneFormEntImgCstList(_list, _facade, e_.getKey());
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }

    public CrudGeneFormEntImgHeros buildImgHeros(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu, boolean _withDir, boolean _front) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormEntImgHeros crud_ = new CrudGeneFormEntImgHeros(_list, _facade, subscriptions, e_.getKey(), _withDir, _front);
        events(crud_.getFrame(), _menu, crud_, e_.getValue());
        return crud_;
    }
    private CrudGeneFormTr buildTr(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu, SubscribedTranslationMessagesFactoryCommon _factor) {
        EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> e_ = build(_list);
        CrudGeneFormTr out_ = new CrudGeneFormTr(_list, _facade, subscriptions, e_.getKey(), _factor);
        events(out_.getFrame(), _menu, out_, e_.getValue());
        return out_;
    }
    private EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>> build(AbstractProgramInfos _list) {
        IdList<SubscribedTranslation> subs_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTrs_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTrs_, subs_);
        return new EditedCrudPair<AbsCommonFrame, IdList<SubscribedTranslation>>(frTrs_,subs_);
    }

    private static void events(AbsCommonFrame _frame, EnabledMenu _menu, AbsCrudGeneFormTrCstOpen _crud, IdList<SubscribedTranslation> _ls) {
        _frame.addWindowListener(new ReinitMenu(_menu, _ls));
        _menu.addActionListener(new PkEditorOpenCrudTrCstEvent(_crud, _menu));
    }

    @Override
    public void changeLanguage(String _language) {
//        AbstractProgramInfos infos_ = getFrames();
//        String value_ = StringUtil.nullToEmpty(_language);
//        StreamLanguageUtil.saveLanguage(WindowAiki.getTempFolder(getFrames()), value_,infos_.getStreams());
        setLanguageKey(_language);
        pack();
    }

    @Override
    public String getApplicationName() {
        return MessagesPkEditor.PK_EDITOR;
    }

    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        for (EntryCust<AbsCommonFrame, IdList<SubscribedTranslation>> e: subscriptions.getSubscribedTranslations().entryList()) {
            e.getKey().setVisible(false);
            e.getValue().clear();
        }
        GuiBaseUtil.trEx(this, getFrames());
    }

    public EnabledMenu getAbMenu() {
        return abMenu;
    }

    public EnabledMenu getItMenu() {
        return itMenu;
    }

    public EnabledMenu getMvMenu() {
        return mvMenu;
    }

    public EnabledMenu getPkMenu() {
        return pkMenu;
    }

    public EnabledMenu getStMenu() {
        return stMenu;
    }

    public EnabledMenu getCombosMenu() {
        return combosMenu;
    }

    public EnabledMenu getTypesMenu() {
        return typesMenu;
    }

    public EnabledMenu getTrsAbMenu() {
        return trsAbMenu;
    }

    public EnabledMenu getTrsItMenu() {
        return trsItMenu;
    }

    public EnabledMenu getTrsMvMenu() {
        return trsMvMenu;
    }

    public EnabledMenu getTrsPkMenu() {
        return trsPkMenu;
    }

    public EnabledMenu getTrsStMenu() {
        return trsStMenu;
    }

    public EnabledMenu getTrsCaMenu() {
        return trsCaMenu;
    }

    public EnabledMenu getTrsTyMenu() {
        return trsTyMenu;
    }

    public EnabledMenu getTrsClMenu() {
        return trsClMenu;
    }

    public EnabledMenu getTrsFctMenu() {
        return trsFctMenu;
    }

    public EnabledMenu getTrsExpTypeMenu() {
        return trsExpTypeMenu;
    }

    public EnabledMenu getTrsDifficultyModelLawMenu() {
        return trsDifficultyModelLawMenu;
    }

    public EnabledMenu getTrsDifficultyWinPointsFightMenu() {
        return trsDifficultyWinPointsFightMenu;
    }

    public EnabledMenu getTrsConstMenu() {
        return trsConstMenu;
    }

    public EnabledMenu getTrsOtherConstMenu() {
        return trsOtherConstMenu;
    }

    public EnabledMenu getTrsNumConstMenu() {
        return trsNumConstMenu;
    }

    public EnabledMenu getTmMenu() {
        return tmMenu;
    }

    public EnabledMenu getHmMenu() {
        return hmMenu;
    }

    public EnabledMenu getTrsCstStatMenu() {
        return trsCstStatMenu;
    }

    public EnabledMenu getTrsCstTargetMenu() {
        return trsCstTargetMenu;
    }

    public EnabledMenu getTrsCstGenderMenu() {
        return trsCstGenderMenu;
    }

    public EnabledMenu getTrsCstEnvironmentTypeMenu() {
        return trsCstEnvironmentTypeMenu;
    }

    public EnabledMenu getTrsCstDifficultyModelLawMenu() {
        return trsCstDifficultyModelLawMenu;
    }

    public EnabledMenu getTrsCstDifficultyWinPointsFightMenu() {
        return trsCstDifficultyWinPointsFightMenu;
    }

    public EnabledMenu getTrsCstSelectedBooleanMenu() {
        return trsCstSelectedBooleanMenu;
    }

    public EnabledMenu getImgMiniPkMenu() {
        return imgMiniPkMenu;
    }

    public EnabledMenu getImgMaxiBackPkMenu() {
        return imgMaxiBackPkMenu;
    }

    public EnabledMenu getImgMaxiFrontPkMenu() {
        return imgMaxiFrontPkMenu;
    }

    public EnabledMenu getImgMiniItMenu() {
        return imgMiniItMenu;
    }

    public EnabledMenu getImgMiniStMenu() {
        return imgMiniStMenu;
    }

    public EnabledMenu getImgMiniTyMenu() {
        return imgMiniTyMenu;
    }

    public EnabledMenu getImgLinksMenu() {
        return imgLinksMenu;
    }

    public EnabledMenu getImgPeopleMenu() {
        return imgPeopleMenu;
    }

    public EnabledMenu getImgTrainersMenu() {
        return imgTrainersMenu;
    }

    public EnabledMenu getImgBlocksMenu() {
        return imgBlocksMenu;
    }

    public EnabledMenu getImgMiniMapMenu() {
        return imgMiniMapMenu;
    }

    public EnabledMenu getImgTypeColorMenu() {
        return imgTypeColorMenu;
    }

    public EnabledMenu getImgOtherMenu() {
        return imgOtherMenu;
    }

    public EnabledMenu getImgCstListMenu() {
        return imgCstListMenu;
    }

    public EnabledMenu getImgHerosMiniMenu() {
        return imgHerosMiniMenu;
    }

    public EnabledMenu getImgHerosFrontMenu() {
        return imgHerosFrontMenu;
    }

    public EnabledMenu getImgHerosBackMenu() {
        return imgHerosBackMenu;
    }

    public CrudGeneFormEnt<AbilityData> getCrudGeneFormAb() {
        return crudGeneFormAb;
    }

    public CrudGeneFormEnt<Item> getCrudGeneFormIt() {
        return crudGeneFormIt;
    }

    public CrudGeneFormEnt<MoveData> getCrudGeneFormMv() {
        return crudGeneFormMv;
    }

    public CrudGeneFormEnt<PokemonData> getCrudGeneFormPk() {
        return crudGeneFormPk;
    }

    public CrudGeneFormEnt<Status> getCrudGeneFormSt() {
        return crudGeneFormSt;
    }

    public CrudGeneFormCombos getCrudGeneFormCombos() {
        return crudGeneFormCombos;
    }

    public CrudGeneFormTypes getCrudGeneFormTypes() {
        return crudGeneFormTypes;
    }

    public CrudGeneFormTr getCrudGeneFormAbTr() {
        return crudGeneFormAbTr;
    }

    public CrudGeneFormTr getCrudGeneFormItTr() {
        return crudGeneFormItTr;
    }

    public CrudGeneFormTr getCrudGeneFormMvTr() {
        return crudGeneFormMvTr;
    }

    public CrudGeneFormTr getCrudGeneFormPkTr() {
        return crudGeneFormPkTr;
    }

    public CrudGeneFormTr getCrudGeneFormStTr() {
        return crudGeneFormStTr;
    }

    public CrudGeneFormTr getCrudGeneFormCaTr() {
        return crudGeneFormCaTr;
    }

    public CrudGeneFormTr getCrudGeneFormTyTr() {
        return crudGeneFormTyTr;
    }

    public CrudGeneFormTrItemType getCrudGeneFormClTr() {
        return crudGeneFormClTr;
    }

    public CrudGeneFormTrItemType getCrudGeneFormFctTr() {
        return crudGeneFormFctTr;
    }

    public CrudGeneFormTrCstParamListString<ExpType> getCrudGeneFormExpTypeTr() {
        return crudGeneFormExpTypeTr;
    }

    public CrudGeneFormTrCstLaw getCrudGeneFormDifficultyModelLawTr() {
        return crudGeneFormDifficultyModelLawTr;
    }

    public CrudGeneFormTrCstParamListString<DifficultyWinPointsFight> getCrudGeneFormDifficultyWinPointsFightTr() {
        return crudGeneFormDifficultyWinPointsFightTr;
    }

    public CrudGeneFormTrCstList getCrudGeneFormTrCstList() {
        return crudGeneFormTrCstList;
    }

    public CrudGeneFormTrOtherCstList getCrudGeneFormTrOtherCstList() {
        return crudGeneFormTrOtherCstList;
    }

    public CrudGeneFormTrCstNumList getCrudGeneFormTrCstNumList() {
        return crudGeneFormTrCstNumList;
    }

    public CrudGeneFormNb getCrudGeneFormTm() {
        return crudGeneFormTm;
    }

    public CrudGeneFormNb getCrudGeneFormHm() {
        return crudGeneFormHm;
    }

    public CrudGeneFormTrCst<Statistic> getCrudGeneFormCstStat() {
        return crudGeneFormCstStat;
    }

    public CrudGeneFormTrCst<TargetChoice> getCrudGeneFormCstTarget() {
        return crudGeneFormCstTarget;
    }

    public CrudGeneFormTrCst<Gender> getCrudGeneFormCstGender() {
        return crudGeneFormCstGender;
    }

    public CrudGeneFormTrCst<EnvironmentType> getCrudGeneFormCstEnvironmentType() {
        return crudGeneFormCstEnvironmentType;
    }

    public CrudGeneFormTrCst<DifficultyModelLaw> getCrudGeneFormCstDifficultyModelLaw() {
        return crudGeneFormCstDifficultyModelLaw;
    }

    public CrudGeneFormTrCst<DifficultyWinPointsFight> getCrudGeneFormCstDifficultyWinPointsFight() {
        return crudGeneFormCstDifficultyWinPointsFight;
    }

    public CrudGeneFormTrCst<SelectedBoolean> getCrudGeneFormCstSelectedBoolean() {
        return crudGeneFormCstSelectedBoolean;
    }

    public CrudGeneFormEntImg getCrudGeneFormMiniPk() {
        return crudGeneFormMiniPk;
    }

    public CrudGeneFormEntImg getCrudGeneFormMaxiBackPk() {
        return crudGeneFormMaxiBackPk;
    }

    public CrudGeneFormEntImg getCrudGeneFormMaxiFrontPk() {
        return crudGeneFormMaxiFrontPk;
    }

    public CrudGeneFormEntImg getCrudGeneFormMiniIt() {
        return crudGeneFormMiniIt;
    }

    public CrudGeneFormEntImg getCrudGeneFormMiniSt() {
        return crudGeneFormMiniSt;
    }

    public CrudGeneFormEntImg getCrudGeneFormMiniTy() {
        return crudGeneFormMiniTy;
    }

    public CrudGeneFormEntImgFree getCrudGeneFormLinks() {
        return crudGeneFormLinks;
    }

    public CrudGeneFormEntImgFree getCrudGeneFormPeople() {
        return crudGeneFormPeople;
    }

    public CrudGeneFormEntImgFree getCrudGeneFormTrainers() {
        return crudGeneFormTrainers;
    }

    public CrudGeneFormEntImgFree getCrudGeneFormBlocks() {
        return crudGeneFormBlocks;
    }

    public CrudGeneFormEntImgFree getCrudGeneFormMiniMap() {
        return crudGeneFormMiniMap;
    }

    public CrudGeneFormEntImgType getCrudGeneFormTypeColor() {
        return crudGeneFormTypeColor;
    }

    public CrudGeneFormEntImgUniq getCrudGeneFormImgOther() {
        return crudGeneFormImgOther;
    }

    public CrudGeneFormEntImgCstList getCrudGeneFormCstList() {
        return crudGeneFormCstList;
    }

    public CrudGeneFormEntImgHeros getCrudGeneFormEntImgHerosMini() {
        return crudGeneFormEntImgHerosMini;
    }

    public CrudGeneFormEntImgHeros getCrudGeneFormEntImgHerosFront() {
        return crudGeneFormEntImgHerosFront;
    }

    public CrudGeneFormEntImgHeros getCrudGeneFormEntImgHerosBack() {
        return crudGeneFormEntImgHerosBack;
    }

    public FormDataMap getFormDataMap() {
        return formDataMap;
    }

    public FacadeGame getFacade() {
        return facade;
    }
}
