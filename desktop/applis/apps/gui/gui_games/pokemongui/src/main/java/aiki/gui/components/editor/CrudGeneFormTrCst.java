package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrCst<T> extends CrudGeneFormTrCommon {
    private final SubscribedTranslationMessagesFactoryCommonCst<T> factoryMessage;
    private final IdMap<T,StringMap<AbsTextField>> fields = new IdMap<T, StringMap<AbsTextField>>();

    public CrudGeneFormTrCst(AbstractProgramInfos _core,FacadeGame _facade, SubscribedTranslationList _sub, SubscribedTranslationMessagesFactoryCommonCst<T> _facto) {
        super(_core, _facade, _sub);
        factoryMessage = _facto;
    }
    @Override
    public void initFormAll() {
        AbsPanel content_ = getApi().getCompoFactory().newPageBox();
        AbsPanel page_ = getApi().getCompoFactory().newPageBox();
        page_.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(getApi().currentLg())).getMapping().getVal(MessagesEditorSelect.ENUM_VALUES));
        StringMap<IdMap<T, String>> all_ = factoryMessage.buildMessages(getFacadeGame());
        fields.clear();
        for (EntryCust<T, StringMap<String>> e: new SubscribeBuilderUtil<T>(factoryMessage).toEntityLg(all_).entryList()) {
            AbsPanel line_ = getApi().getCompoFactory().newLineBox();
            StringMap<AbsTextField> fs_ = ConverterCommonMapUtil.fields(line_, e.getValue(), getApi());
            page_.add(line_);
            fields.addEntry(e.getKey(),fs_);
        }
        footer(content_,page_);
    }

    public IdMap<T, StringMap<AbsTextField>> getFields() {
        return fields;
    }

    @Override
    public void update() {
        StringMap<IdMap<T, String>> allId_ = factoryMessage.buildMessages(getFacadeGame());
        for (EntryCust<T,StringMap<AbsTextField>> e: fields.entryList()) {
            for (EntryCust<String, AbsTextField> l: e.getValue().entryList()) {
                allId_.getVal(l.getKey()).set(e.getKey(),l.getValue().getText());
            }
        }
        getSubscribedTranslations().updateRenamingId("","",new StringList());
        getSubscribedTranslations().update();
    }
}
