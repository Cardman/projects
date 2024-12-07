package aiki.gui.components.editor;

//import aiki.gui.*;
import aiki.facade.*;
import aiki.fight.abilities.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.status.*;
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
    private final CrudGeneFormTrCstList crudGeneFormTrCstList;
    private final CrudGeneFormTrOtherCstList crudGeneFormTrOtherCstList;
    private final CrudGeneFormNb crudGeneFormTm;
    private final CrudGeneFormNb crudGeneFormHm;
    private final CrudGeneFormTrCst<Statistic> crudGeneFormCstStat;
    private final CrudGeneFormTrCst<TargetChoice> crudGeneFormCstTarget;
    private final CrudGeneFormTrCst<Gender> crudGeneFormCstGender;
    private final CrudGeneFormTrCst<EnvironmentType> crudGeneFormCstEnvironmentType;
    private final CrudGeneFormEnt<AbilityData> crudGeneFormAb;
    private final CrudGeneFormEnt<Item> crudGeneFormIt;
    private final CrudGeneFormEnt<MoveData> crudGeneFormMv;
    private final CrudGeneFormEnt<PokemonData> crudGeneFormPk;
    private final CrudGeneFormEnt<Status> crudGeneFormSt;
    private final CrudGeneFormCombos crudGeneFormCombos;
    private final CrudGeneFormTypes crudGeneFormTypes;
    private final EnabledMenu trsAbMenu = getFrames().getCompoFactory().newMenuItem("0_0");
    private final EnabledMenu trsItMenu = getFrames().getCompoFactory().newMenuItem("0_1");
    private final EnabledMenu trsMvMenu = getFrames().getCompoFactory().newMenuItem("0_2");
    private final EnabledMenu trsPkMenu = getFrames().getCompoFactory().newMenuItem("0_3");
    private final EnabledMenu trsStMenu = getFrames().getCompoFactory().newMenuItem("0_4");
    private final EnabledMenu trsCaMenu = getFrames().getCompoFactory().newMenuItem("0_5");
    private final EnabledMenu trsTyMenu = getFrames().getCompoFactory().newMenuItem("0_6");
    private final EnabledMenu trsClMenu = getFrames().getCompoFactory().newMenuItem("0_7");
    private final EnabledMenu trsConstMenu = getFrames().getCompoFactory().newMenuItem("0_8");
    private final EnabledMenu trsOtherConstMenu = getFrames().getCompoFactory().newMenuItem("0_9");
    private final EnabledMenu tmMenu = getFrames().getCompoFactory().newMenuItem("1_0");
    private final EnabledMenu hmMenu = getFrames().getCompoFactory().newMenuItem("1_1");
    private final EnabledMenu abMenu = getFrames().getCompoFactory().newMenuItem("2_0");
    private final EnabledMenu itMenu = getFrames().getCompoFactory().newMenuItem("2_1");
    private final EnabledMenu mvMenu = getFrames().getCompoFactory().newMenuItem("2_2");
    private final EnabledMenu pkMenu = getFrames().getCompoFactory().newMenuItem("2_3");
    private final EnabledMenu stMenu = getFrames().getCompoFactory().newMenuItem("2_4");
    private final EnabledMenu combosMenu = getFrames().getCompoFactory().newMenuItem("2_5");
    private final EnabledMenu typesMenu = getFrames().getCompoFactory().newMenuItem("2_6");
    private final EnabledMenu trsCstStatMenu = getFrames().getCompoFactory().newMenuItem("3_0");
    private final EnabledMenu trsCstTargetMenu = getFrames().getCompoFactory().newMenuItem("3_1");
    private final EnabledMenu trsCstGenderMenu = getFrames().getCompoFactory().newMenuItem("3_2");
    private final EnabledMenu trsCstEnvironmentTypeMenu = getFrames().getCompoFactory().newMenuItem("3_3");

    public WindowPkEditor(AbstractProgramInfos _list, FacadeGame _facade) {
        super(_list);
        _facade.setLanguages(_list.getLanguages());
        _facade.setDisplayLanguages(_list.getDisplayLanguages());
        _facade.setSimplyLanguage(_list.getLanguage());
        subscriptions = new SubscribedTranslationList(_list, _facade);
        IdList<SubscribedTranslation> subsTm_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTm_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTm_, subsTm_);
        IdList<SubscribedTranslation> subsHm_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frHm_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frHm_, subsHm_);
        crudGeneFormAbTr = buildTr(_list, _facade,trsAbMenu,subscriptions.getFactoryAb());
        crudGeneFormItTr = buildTr(_list, _facade,trsItMenu,subscriptions.getFactoryIt());
        crudGeneFormMvTr = buildTr(_list, _facade,trsMvMenu,subscriptions.getFactoryMv());
        crudGeneFormPkTr = buildTr(_list, _facade,trsPkMenu,subscriptions.getFactoryPk());
        crudGeneFormStTr = buildTr(_list, _facade,trsStMenu,subscriptions.getFactorySt());
        crudGeneFormCaTr = buildTr(_list, _facade,trsCaMenu,subscriptions.getFactoryCa());
        crudGeneFormTyTr = buildTr(_list, _facade,trsTyMenu,subscriptions.getFactoryTy());
        crudGeneFormClTr = new CrudGeneFormTrItemType(_list, _facade,subscriptions);
        trsClMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormClTr,trsClMenu));
        crudGeneFormClTr.getFrame().addWindowListener(new ReinitMenu(trsClMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormTrCstList = new CrudGeneFormTrCstList(_list, _facade, subscriptions);
        trsConstMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormTrCstList,trsConstMenu));
        crudGeneFormTrCstList.getFrame().addWindowListener(new ReinitMenu(trsConstMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormTrOtherCstList = new CrudGeneFormTrOtherCstList(_list, _facade);
        trsOtherConstMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormTrOtherCstList,trsOtherConstMenu));
        crudGeneFormTrOtherCstList.getFrame().addWindowListener(new ReinitMenu(trsOtherConstMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormCstStat = new CrudGeneFormTrCst<Statistic>(_list,_facade,subscriptions,subscriptions.getFactoryStat());
        trsCstStatMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormCstStat,trsCstStatMenu));
        crudGeneFormCstStat.getFrame().addWindowListener(new ReinitMenu(trsCstStatMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormCstTarget = new CrudGeneFormTrCst<TargetChoice>(_list,_facade,subscriptions,subscriptions.getFactoryTarget());
        trsCstTargetMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormCstTarget,trsCstTargetMenu));
        crudGeneFormCstTarget.getFrame().addWindowListener(new ReinitMenu(trsCstTargetMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormCstGender = new CrudGeneFormTrCst<Gender>(_list,_facade,subscriptions,subscriptions.getFactoryGender());
        trsCstGenderMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormCstGender,trsCstGenderMenu));
        crudGeneFormCstGender.getFrame().addWindowListener(new ReinitMenu(trsCstGenderMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormCstEnvironmentType = new CrudGeneFormTrCst<EnvironmentType>(_list,_facade,subscriptions,subscriptions.getFactoryEnvironmentType());
        trsCstEnvironmentTypeMenu.addActionListener(new PkEditorOpenCrudTrCstEvent(crudGeneFormCstEnvironmentType,trsCstEnvironmentTypeMenu));
        crudGeneFormCstEnvironmentType.getFrame().addWindowListener(new ReinitMenu(trsCstEnvironmentTypeMenu, new IdList<SubscribedTranslation>()));
        crudGeneFormTm = new CrudGeneFormNb(_list, _facade,subscriptions, frTm_,subscriptions.getFactoryTm());
        crudGeneFormHm = new CrudGeneFormNb(_list, _facade,subscriptions, frHm_,subscriptions.getFactoryHm());
        crudGeneFormAb = new CrudGeneFormEntBuilder<AbilityData>().build(_list,_facade,subscriptions, abMenu, subscriptions.getFactoryAb());
        crudGeneFormIt = new CrudGeneFormEntBuilder<Item>().build(_list,_facade,subscriptions, itMenu, subscriptions.getFactoryIt());
        crudGeneFormMv = new CrudGeneFormEntBuilder<MoveData>().build(_list,_facade,subscriptions, mvMenu, subscriptions.getFactoryMv());
        crudGeneFormPk = new CrudGeneFormEntBuilder<PokemonData>().build(_list, _facade,subscriptions, pkMenu, subscriptions.getFactoryPk());
        crudGeneFormSt = new CrudGeneFormEntBuilder<Status>().build(_list, _facade,subscriptions, stMenu, subscriptions.getFactorySt());
        crudGeneFormCombos = buildCombos(_list, _facade,subscriptions,combosMenu);
        crudGeneFormTypes = buildTypes(_list, _facade,subscriptions,typesMenu);
        AbsMenuBar bar_ = getFrames().getCompoFactory().newMenuBar();
        EnabledMenu file_ = getFrames().getCompoFactory().newMenu("0");
        EnabledMenu trs_ = getFrames().getCompoFactory().newMenu("0");
        trs_.addMenuItem(trsAbMenu);
        trs_.addMenuItem(trsItMenu);
        trs_.addMenuItem(trsMvMenu);
        trs_.addMenuItem(trsPkMenu);
        trs_.addMenuItem(trsStMenu);
        trs_.addMenuItem(trsCaMenu);
        trs_.addMenuItem(trsTyMenu);
        trs_.addMenuItem(trsClMenu);
        trs_.addMenuItem(trsConstMenu);
        trs_.addMenuItem(trsOtherConstMenu);
        file_.addMenuItem(trs_);
        EnabledMenu tmHm_ = getFrames().getCompoFactory().newMenu("1");
        tmHm_.addMenuItem(tmMenu);
        tmHm_.addMenuItem(hmMenu);
        file_.addMenuItem(tmHm_);
        EnabledMenu ent_ = getFrames().getCompoFactory().newMenu("2");
        ent_.addMenuItem(abMenu);
        ent_.addMenuItem(itMenu);
        ent_.addMenuItem(mvMenu);
        ent_.addMenuItem(pkMenu);
        ent_.addMenuItem(stMenu);
        ent_.addMenuItem(combosMenu);
        ent_.addMenuItem(typesMenu);
        file_.addMenuItem(ent_);
        EnabledMenu trsCst_ = getFrames().getCompoFactory().newMenu("3");
        trsCst_.addMenuItem(trsCstStatMenu);
        trsCst_.addMenuItem(trsCstTargetMenu);
        trsCst_.addMenuItem(trsCstGenderMenu);
        trsCst_.addMenuItem(trsCstEnvironmentTypeMenu);
        file_.addMenuItem(trsCst_);
        bar_.add(file_);
        tmMenu.addActionListener(new PkEditorOpenCrudNbEvent(crudGeneFormTm,tmMenu, true));
        hmMenu.addActionListener(new PkEditorOpenCrudNbEvent(crudGeneFormHm,hmMenu, false));
        getCommonFrame().setJMenuBar(bar_);
        crudGeneFormTm.getFrame().addWindowListener(new ReinitMenu(tmMenu, subsTm_));
        crudGeneFormHm.getFrame().addWindowListener(new ReinitMenu(hmMenu, subsHm_));
        addWindowListener(new QuittingEvent(this));
        getCommonFrame().setVisible(true);
        getCommonFrame().pack();
    }
    public CrudGeneFormCombos buildCombos(AbstractProgramInfos _list, FacadeGame _facade, SubscribedTranslationList _subscriptions, EnabledMenu _menu) {
        AbsCommonFrame fr_ = _list.getFrameFactory().newCommonFrame();
        IdList<SubscribedTranslation> subsCombos_ = new IdList<SubscribedTranslation>();
        _subscriptions.getSubscribedTranslations().addEntry(fr_, subsCombos_);
        CrudGeneFormCombos crud_ = new CrudGeneFormCombos(_list, _facade, _subscriptions, fr_);
        crud_.getFrame().addWindowListener(new ReinitMenu(_menu, subsCombos_));
        _menu.addActionListener(new PkEditorOpenCrudCombosEvent(crud_,_menu));
        return crud_;
    }
    public CrudGeneFormTypes buildTypes(AbstractProgramInfos _list, FacadeGame _facade, SubscribedTranslationList _subscriptions, EnabledMenu _menu) {
        AbsCommonFrame fr_ = _list.getFrameFactory().newCommonFrame();
        IdList<SubscribedTranslation> subsTypes_ = new IdList<SubscribedTranslation>();
        _subscriptions.getSubscribedTranslations().addEntry(fr_, subsTypes_);
        CrudGeneFormTypes crud_ = new CrudGeneFormTypes(_list, _facade, _subscriptions, fr_);
        crud_.getFrame().addWindowListener(new ReinitMenu(_menu, subsTypes_));
        _menu.addActionListener(new PkEditorOpenCrudTypesEvent(crud_,_menu));
        return crud_;
    }
    private CrudGeneFormTr buildTr(AbstractProgramInfos _list, FacadeGame _facade, EnabledMenu _menu, SubscribedTranslationMessagesFactory _factor) {
        IdList<SubscribedTranslation> subsTrs_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTrs_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTrs_, subsTrs_);
        CrudGeneFormTr out_ = new CrudGeneFormTr(_list, _facade, subscriptions, frTrs_, _factor);
        _menu.addActionListener(new PkEditorOpenCrudTrEvent(out_,_menu));
        out_.getFrame().addWindowListener(new ReinitMenu(_menu, subsTrs_));
        return out_;
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

    public EnabledMenu getTrsConstMenu() {
        return trsConstMenu;
    }

    public EnabledMenu getTrsOtherConstMenu() {
        return trsOtherConstMenu;
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

    public CrudGeneFormTrCstList getCrudGeneFormTrCstList() {
        return crudGeneFormTrCstList;
    }

    public CrudGeneFormTrOtherCstList getCrudGeneFormTrOtherCstList() {
        return crudGeneFormTrOtherCstList;
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
}
