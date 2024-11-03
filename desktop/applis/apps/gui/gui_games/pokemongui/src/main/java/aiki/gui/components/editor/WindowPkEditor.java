package aiki.gui.components.editor;

//import aiki.gui.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.*;
//import code.stream.*;
import code.util.EntryCust;
import code.util.IdList;

public final class WindowPkEditor extends GroupFrame implements AbsOpenQuit {
    private final SubscribedTranslationList subscriptions;
    private final CrudGeneFormPkTr crudGeneFormPkTr;
    private final CrudGeneFormPk crudGeneFormPk;
    private final EnabledMenu trsPkMenu = getFrames().getCompoFactory().newMenuItem("0");
    private final EnabledMenu pkMenu = getFrames().getCompoFactory().newMenuItem("1");

    public WindowPkEditor(AbstractProgramInfos _list, FacadeGame _facade) {
        super(_list);
        _facade.setLanguages(_list.getLanguages());
        _facade.setDisplayLanguages(_list.getDisplayLanguages());
        _facade.setSimplyLanguage(_list.getLanguage());
        subscriptions = new SubscribedTranslationList(_list, _facade);
        IdList<SubscribedTranslation> subsTrsPk_ = new IdList<SubscribedTranslation>();
        AbsCommonFrame frTrsPk_ = _list.getFrameFactory().newCommonFrame();
        subscriptions.getSubscribedTranslations().addEntry(frTrsPk_, subsTrsPk_);
        AbsCommonFrame frPk_ = _list.getFrameFactory().newCommonFrame();
        IdList<SubscribedTranslation> subPk_ = new IdList<SubscribedTranslation>();
        subscriptions.getSubscribedTranslations().addEntry(frPk_, subPk_);
        crudGeneFormPkTr = new CrudGeneFormPkTr(_list, _facade,subscriptions, frTrsPk_);
        crudGeneFormPk = new CrudGeneFormPk(_list, _facade,subscriptions, frPk_);
        AbsMenuBar bar_ = getFrames().getCompoFactory().newMenuBar();
        EnabledMenu file_ = getFrames().getCompoFactory().newMenu("0");
        file_.addMenuItem(trsPkMenu);
        file_.addMenuItem(pkMenu);
        bar_.add(file_);
        trsPkMenu.addActionListener(new PkEditorOpenCrudPkTrEvent(this));
        pkMenu.addActionListener(new PkEditorOpenCrudPkEvent(this));
        getCommonFrame().setJMenuBar(bar_);
        crudGeneFormPkTr.getFrame().addWindowListener(new ReinitMenu(trsPkMenu, subsTrsPk_));
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

    public void openCrudPkTr() {
        crudGeneFormPkTr.initForm(getFrames());
        trsPkMenu.setEnabled(false);
    }

    public EnabledMenu getPkMenu() {
        return pkMenu;
    }

    public EnabledMenu getTrsPkMenu() {
        return trsPkMenu;
    }

    public CrudGeneFormPk getCrudGeneFormPk() {
        return crudGeneFormPk;
    }

    public CrudGeneFormPkTr getCrudGeneFormPkTr() {
        return crudGeneFormPkTr;
    }
}
