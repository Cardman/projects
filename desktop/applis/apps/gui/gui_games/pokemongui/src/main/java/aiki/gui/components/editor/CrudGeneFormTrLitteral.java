package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class CrudGeneFormTrLitteral extends CrudGeneFormTrCommon {

    private final StringMap<StringMap<CustList<AbsTxtComponent>>> fields = new StringMap<StringMap<CustList<AbsTxtComponent>>>();

    public CrudGeneFormTrLitteral(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_core, _facade, _sub);
    }
    @Override
    public void initFormAll() {
        AbsPanel content_ = getApi().getCompoFactory().newPageBox();
        AbsPanel page_ = getApi().getCompoFactory().newPageBox();
        StringMap<StringMap<String>> all_ = getFacadeGame().getData().getLitterals();
        fields.clear();
        for (EntryCust<String, StringMap<String>> e: ConverterCommonMapUtil.toEntityLg(all_).entryList()) {
            AbsPanel line_ = getApi().getCompoFactory().newLineBox();
            StringMap<CustList<AbsTxtComponent>> fs_ = ConverterCommonMapUtil.areasList(line_, e.getValue(), getApi());
            page_.add(line_);
            fields.addEntry(e.getKey(),fs_);
        }
        footer(content_, page_);
    }

    public StringMap<StringMap<CustList<AbsTxtComponent>>> getFields() {
        return fields;
    }

    @Override
    public void update() {
        StringMap<StringMap<String>> all_ = getFacadeGame().getData().getLitterals();
        for (EntryCust<String,StringMap<CustList<AbsTxtComponent>>> e: fields.entryList()) {
            for (EntryCust<String, CustList<AbsTxtComponent>> l: e.getValue().entryList()) {
                CustList<String> text_ = new CustList<String>();
                for (AbsTxtComponent t: l.getValue()) {
                    text_.add(t.getText());
                }
                all_.getVal(l.getKey()).set(e.getKey(), StringUtil.join(text_,'\t'));
            }
        }
    }
}
