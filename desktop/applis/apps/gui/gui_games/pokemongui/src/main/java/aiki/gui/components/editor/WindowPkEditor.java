package aiki.gui.components.editor;

//import aiki.gui.*;
import aiki.facade.*;
import aiki.fight.abilities.AbilityData;
import aiki.fight.items.Item;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
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
    private final CrudGeneFormTr crudGeneFormTyTr;
    private final CrudGeneFormNb crudGeneFormTm;
    private final CrudGeneFormNb crudGeneFormHm;
    private final CrudGeneFormEnt<AbilityData> crudGeneFormAb;
    private final CrudGeneFormEnt<Item> crudGeneFormIt;
    private final CrudGeneFormEnt<MoveData> crudGeneFormMv;
    private final CrudGeneFormEnt<PokemonData> crudGeneFormPk;
    private final EnabledMenu trsAbMenu = getFrames().getCompoFactory().newMenuItem("0");
    private final EnabledMenu trsItMenu = getFrames().getCompoFactory().newMenuItem("1");
    private final EnabledMenu trsMvMenu = getFrames().getCompoFactory().newMenuItem("2");
    private final EnabledMenu trsPkMenu = getFrames().getCompoFactory().newMenuItem("3");
    private final EnabledMenu trsTyMenu = getFrames().getCompoFactory().newMenuItem("4");
    private final EnabledMenu tmMenu = getFrames().getCompoFactory().newMenuItem("5");
    private final EnabledMenu hmMenu = getFrames().getCompoFactory().newMenuItem("6");
    private final EnabledMenu abMenu = getFrames().getCompoFactory().newMenuItem("7");
    private final EnabledMenu itMenu = getFrames().getCompoFactory().newMenuItem("8");
    private final EnabledMenu mvMenu = getFrames().getCompoFactory().newMenuItem("9");
    private final EnabledMenu pkMenu = getFrames().getCompoFactory().newMenuItem("10");

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
        crudGeneFormPkTr = buildTr(_list, _facade,trsPkMenu,subscriptions.getFactoryPk());
        crudGeneFormMvTr = buildTr(_list, _facade,trsMvMenu,subscriptions.getFactoryMv());
        crudGeneFormTyTr = buildTr(_list, _facade,trsTyMenu,subscriptions.getFactoryTy());
        crudGeneFormTm = new CrudGeneFormNb(_list, _facade,subscriptions, frTm_,subscriptions.getFactoryTm());
        crudGeneFormHm = new CrudGeneFormNb(_list, _facade,subscriptions, frHm_,subscriptions.getFactoryHm());
        crudGeneFormAb = new CrudGeneFormEntBuilder<AbilityData>().build(_list,_facade,subscriptions, abMenu, subscriptions.getFactoryAb());
        crudGeneFormIt = new CrudGeneFormEntBuilder<Item>().build(_list,_facade,subscriptions, itMenu, subscriptions.getFactoryIt());
        crudGeneFormMv = new CrudGeneFormEntBuilder<MoveData>().build(_list,_facade,subscriptions, mvMenu, subscriptions.getFactoryMv());
        crudGeneFormPk = new CrudGeneFormEntBuilder<PokemonData>().build(_list, _facade,subscriptions, pkMenu, subscriptions.getFactoryPk());
        AbsMenuBar bar_ = getFrames().getCompoFactory().newMenuBar();
        EnabledMenu file_ = getFrames().getCompoFactory().newMenu("0");
        file_.addMenuItem(trsAbMenu);
        file_.addMenuItem(trsItMenu);
        file_.addMenuItem(trsMvMenu);
        file_.addMenuItem(trsPkMenu);
        file_.addMenuItem(trsTyMenu);
        file_.addMenuItem(tmMenu);
        file_.addMenuItem(hmMenu);
        file_.addMenuItem(abMenu);
        file_.addMenuItem(itMenu);
        file_.addMenuItem(mvMenu);
        file_.addMenuItem(pkMenu);
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

    public EnabledMenu getTrsTyMenu() {
        return trsTyMenu;
    }

    public EnabledMenu getTmMenu() {
        return tmMenu;
    }

    public EnabledMenu getHmMenu() {
        return hmMenu;
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

    public CrudGeneFormTr getCrudGeneFormTyTr() {
        return crudGeneFormTyTr;
    }

    public CrudGeneFormNb getCrudGeneFormTm() {
        return crudGeneFormTm;
    }

    public CrudGeneFormNb getCrudGeneFormHm() {
        return crudGeneFormHm;
    }
}
