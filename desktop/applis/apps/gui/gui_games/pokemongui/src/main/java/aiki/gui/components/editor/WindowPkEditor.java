package aiki.gui.components.editor;

//import aiki.gui.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
//import code.stream.*;
import code.util.*;

public final class WindowPkEditor extends GroupFrame implements AbsOpenQuit {
    private final SubscribedTranslationList subscriptions;
    private final CrudGeneFormPkTr crudGeneFormPkTr;
    private final CrudGeneFormMvTr crudGeneFormMvTr;
    private final CrudGeneFormTm crudGeneFormTm;
    private final CrudGeneFormPk crudGeneFormPk;
    private final EnabledMenu trsPkMenu = getFrames().getCompoFactory().newMenuItem("0");
    private final EnabledMenu trsMvMenu = getFrames().getCompoFactory().newMenuItem("1");
    private final EnabledMenu tmMenu = getFrames().getCompoFactory().newMenuItem("2");
    private final EnabledMenu pkMenu = getFrames().getCompoFactory().newMenuItem("3");

    public WindowPkEditor(AbstractProgramInfos _list, FacadeGame _facade) {
        super(_list);
        _facade.setLanguages(_list.getLanguages());
        _facade.setDisplayLanguages(_list.getDisplayLanguages());
        _facade.setSimplyLanguage(_list.getLanguage());
        subscriptions = new SubscribedTranslationList(_list, _facade);
        IdList<SubscribedTranslation> subsTrsPk_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTrsPk_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTrsPk_, subsTrsPk_);
        IdList<SubscribedTranslation> subsTrsMv_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTrsMv_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTrsMv_, subsTrsMv_);
        IdList<SubscribedTranslation> subsTm_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTm_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTm_, subsTm_);
        AbsCommonFrame frPk_ = _list.getFrameFactory().newCommonFrame();
        IdList<SubscribedTranslation> subPk_ = new IdList<SubscribedTranslation>();
        subscriptions.getSubscribedTranslations().addEntry(frPk_, subPk_);
        crudGeneFormPkTr = new CrudGeneFormPkTr(_list, _facade,subscriptions, frTrsPk_);
        crudGeneFormMvTr = new CrudGeneFormMvTr(_list, _facade,subscriptions, frTrsMv_);
        crudGeneFormTm = new CrudGeneFormTm(_list, _facade,subscriptions, frTm_);
        crudGeneFormPk = new CrudGeneFormPk(_list, _facade,subscriptions, frPk_);
        AbsMenuBar bar_ = getFrames().getCompoFactory().newMenuBar();
        EnabledMenu file_ = getFrames().getCompoFactory().newMenu("0");
        file_.addMenuItem(trsPkMenu);
        file_.addMenuItem(trsMvMenu);
        file_.addMenuItem(tmMenu);
        file_.addMenuItem(pkMenu);
        bar_.add(file_);
        trsPkMenu.addActionListener(new PkEditorOpenCrudTrEvent(crudGeneFormPkTr,trsPkMenu));
        trsMvMenu.addActionListener(new PkEditorOpenCrudTrEvent(crudGeneFormMvTr,trsMvMenu));
        tmMenu.addActionListener(new PkEditorOpenCrudNbEvent(crudGeneFormTm,tmMenu));
        pkMenu.addActionListener(new PkEditorOpenCrudPkEvent(this));
        getCommonFrame().setJMenuBar(bar_);
        crudGeneFormPkTr.getFrame().addWindowListener(new ReinitMenu(trsPkMenu, subsTrsPk_));
        crudGeneFormMvTr.getFrame().addWindowListener(new ReinitMenu(trsMvMenu, subsTrsMv_));
        crudGeneFormTm.getFrame().addWindowListener(new ReinitMenu(tmMenu, subsTm_));
        crudGeneFormPk.getFrame().addWindowListener(new ReinitMenu(pkMenu, subPk_));
        addWindowListener(new QuittingEvent(this));
        getCommonFrame().setVisible(true);
        getCommonFrame().pack();
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

    public void openCrudPk() {
        crudGeneFormPk.initForm(getFrames());
        pkMenu.setEnabled(false);
    }

    public EnabledMenu getPkMenu() {
        return pkMenu;
    }

    public EnabledMenu getTrsPkMenu() {
        return trsPkMenu;
    }

    public EnabledMenu getTrsMvMenu() {
        return trsMvMenu;
    }

    public EnabledMenu getTmMenu() {
        return tmMenu;
    }

    public CrudGeneFormPk getCrudGeneFormPk() {
        return crudGeneFormPk;
    }

    public CrudGeneFormMvTr getCrudGeneFormMvTr() {
        return crudGeneFormMvTr;
    }

    public CrudGeneFormPkTr getCrudGeneFormPkTr() {
        return crudGeneFormPkTr;
    }

    public CrudGeneFormTm getCrudGeneFormTm() {
        return crudGeneFormTm;
    }
}
