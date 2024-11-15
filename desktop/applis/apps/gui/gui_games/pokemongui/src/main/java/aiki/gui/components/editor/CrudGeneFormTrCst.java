package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrCst<T> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslations;
    private final SubscribedTranslationMessagesFactoryCst<T> factoryMessage;
    private final AbsCommonFrame frame;
    private final IdMap<T,StringMap<AbsTextField>> fields = new IdMap<T, StringMap<AbsTextField>>();
    private AbsButton changeValues;

    public CrudGeneFormTrCst(AbstractProgramInfos _core,FacadeGame _facade, SubscribedTranslationList _sub, SubscribedTranslationMessagesFactoryCst<T> _facto) {
        api = _core;
        factoryMessage = _facto;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        frame = _core.getFrameFactory().newCommonFrame();
    }
    public void initForm() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        StringMap<IdMap<T, String>> all_ = factoryMessage.buildMessages(facadeGame);
        fields.clear();
        for (EntryCust<T, StringMap<String>> e: new SubscribeBuilderUtil<T>(factoryMessage).toEntityLg(all_).entryList()) {
            AbsPanel line_ = api.getCompoFactory().newLineBox();
            StringMap<AbsTextField> fs_ = new StringMap<AbsTextField>();
            for (EntryCust<String, String> l: e.getValue().entryList()) {
                AbsTextField txt_ = api.getCompoFactory().newTextField(l.getValue());
                line_.add(txt_);
                fs_.addEntry(l.getKey(),txt_);
            }
            page_.add(line_);
            fields.addEntry(e.getKey(),fs_);
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        changeValues = api.getCompoFactory().newPlainButton("\u2611");
        changeValues.setForeground(GuiConstants.GREEN);
        changeValues.addActionListener(new CrudGeneFormTrCstEvent<T>(this));
        content_.add(changeValues);
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public IdMap<T, StringMap<AbsTextField>> getFields() {
        return fields;
    }

    public AbsButton getChangeValues() {
        return changeValues;
    }

    public void update() {
        StringMap<IdMap<T, String>> all_ = factoryMessage.buildMessages(facadeGame);
        for (EntryCust<T,StringMap<AbsTextField>> e: fields.entryList()) {
            for (EntryCust<String, AbsTextField> l: e.getValue().entryList()) {
                all_.getVal(l.getKey()).set(e.getKey(),l.getValue().getText());
            }
        }
        subscribedTranslations.update();
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }
}
