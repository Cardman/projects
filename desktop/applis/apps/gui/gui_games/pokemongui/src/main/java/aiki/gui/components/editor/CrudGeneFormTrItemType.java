package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrItemType implements AbsCrudGeneFormTrCst {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslations;
    private final AbsCommonFrame frame;
    private final StringMap<StringMap<AbsTextField>> fields = new StringMap<StringMap<AbsTextField>>();
    private AbsButton changeValues;

    public CrudGeneFormTrItemType(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        api = _core;
        subscribedTranslations = _sub;
        facadeGame = _facade;
        frame = _core.getFrameFactory().newCommonFrame();
    }
    @Override
    public void initForm() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        StringMap<StringMap<String>> all_ = facadeGame.getData().getTranslatedClassesDescriptions();
        fields.clear();
        for (EntryCust<String, StringMap<String>> e: ConverterCommonMapUtil.toEntityLg(all_).entryList()) {
            AbsPanel line_ = api.getCompoFactory().newLineBox();
            StringMap<AbsTextField> fs_ = ConverterCommonMapUtil.fields(line_, e.getValue(), api);
            page_.add(line_);
            fields.addEntry(e.getKey(),fs_);
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        changeValues = api.getCompoFactory().newPlainButton("\u2611");
        changeValues.setForeground(GuiConstants.GREEN);
        changeValues.addActionListener(new CrudGeneFormTrCstEvent(this));
        content_.add(changeValues);
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public StringMap<StringMap<AbsTextField>> getFields() {
        return fields;
    }

    public AbsButton getChangeValues() {
        return changeValues;
    }

    @Override
    public void update() {
        StringMap<StringMap<String>> all_ = facadeGame.getData().getTranslatedClassesDescriptions();
        for (EntryCust<String,StringMap<AbsTextField>> e: fields.entryList()) {
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
