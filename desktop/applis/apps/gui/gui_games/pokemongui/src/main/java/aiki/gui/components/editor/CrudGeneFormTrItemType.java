package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrItemType extends CrudGeneFormTrCommon {

    private final StringMap<StringMap<AbsTextField>> fields = new StringMap<StringMap<AbsTextField>>();

    public CrudGeneFormTrItemType(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_core, _facade, _sub);
    }
    @Override
    public void initForm() {
        AbsPanel content_ = getApi().getCompoFactory().newPageBox();
        AbsPanel page_ = getApi().getCompoFactory().newPageBox();
        StringMap<StringMap<String>> all_ = getFacadeGame().getData().getTranslatedClassesDescriptions();
        fields.clear();
        for (EntryCust<String, StringMap<String>> e: ConverterCommonMapUtil.toEntityLg(all_).entryList()) {
            AbsPanel line_ = getApi().getCompoFactory().newLineBox();
            StringMap<AbsTextField> fs_ = ConverterCommonMapUtil.fields(line_, e.getValue(), getApi());
            page_.add(line_);
            fields.addEntry(e.getKey(),fs_);
        }
        footer(content_, page_);
    }

    public StringMap<StringMap<AbsTextField>> getFields() {
        return fields;
    }

    @Override
    public void update() {
        StringMap<StringMap<String>> all_ = getFacadeGame().getData().getTranslatedClassesDescriptions();
        for (EntryCust<String,StringMap<AbsTextField>> e: fields.entryList()) {
            for (EntryCust<String, AbsTextField> l: e.getValue().entryList()) {
                all_.getVal(l.getKey()).set(e.getKey(),l.getValue().getText());
            }
        }
        getSubscribedTranslations().updateRenamingId("","",new StringList());
        getSubscribedTranslations().update();
    }

}
