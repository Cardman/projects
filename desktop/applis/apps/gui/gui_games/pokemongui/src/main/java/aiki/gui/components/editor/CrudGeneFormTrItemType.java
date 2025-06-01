package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrItemType extends CrudGeneFormTrCommon {

    private final StringMap<StringMap<AbsTextField>> fields = new StringMap<StringMap<AbsTextField>>();
    private final boolean fctMath;

    public CrudGeneFormTrItemType(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub, boolean _f) {
        super(_core, _facade, _sub);
        fctMath = _f;
    }
    @Override
    public void initFormAll() {
        AbsPanel content_ = getApi().getCompoFactory().newPageBox();
        AbsPanel page_ = getApi().getCompoFactory().newPageBox();
        page_.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(getApi().currentLg())).getMapping().getVal(MessagesEditorSelect.IT_TYPE));
        StringMap<StringMap<String>> all_ = all();
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
        StringMap<StringMap<String>> all_ = all();
        for (EntryCust<String,StringMap<AbsTextField>> e: fields.entryList()) {
            for (EntryCust<String, AbsTextField> l: e.getValue().entryList()) {
                all_.getVal(l.getKey()).set(e.getKey(),l.getValue().getText());
            }
        }
        if (fctMath) {
            return;
        }
        getSubscribedTranslations().updateRenamingId(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING,new StringList());
        getSubscribedTranslations().update();
    }

    private StringMap<StringMap<String>> all() {
        if (fctMath) {
            return getFacadeGame().getData().getTranslatedFctMath();
        }
        return getFacadeGame().getData().getTranslatedClassesDescriptions();
    }
}
