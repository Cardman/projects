package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.events.DefValidateText;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.StringUtil;

public final class GeneComponentModelTr implements GeneComponentModel<EditedCrudPair<String, StringMap<String>>> {
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final StringMap<AbsTextField> translations = new StringMap<AbsTextField>();
    private GeneComponentModelString key;

    public GeneComponentModelTr(AbstractProgramInfos _core, FacadeGame _facade) {
        this.compoFactory = _core;
        facade = _facade;
    }
    @Override
    public AbsCustComponent gene(int _select) {
        key = new GeneComponentModelString(compoFactory, new StringList(), new DefValidateText());
        getTranslations().clear();
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(key.geneString());
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel form_ = compoFactory_.newLineBox();
        AbsPanel stats_ = compoFactory_.newLineBox();
        for (String s: facade.getData().getLanguages()) {
            AbsTextField txt_ = compoFactory_.newTextField();
            getTranslations().addEntry(s, txt_);
            stats_.add(txt_);
        }
        form_.add(stats_);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String, StringMap<String>> value() {
        StringMap<String> trs_ = new StringMap<String>();
        for (EntryCust<String,AbsTextField> e:getTranslations().entryList()) {
            trs_.addEntry(e.getKey(),e.getValue().getText());
        }
        return new EditedCrudPair<String, StringMap<String>>(key.valueString(),trs_);
    }

    @Override
    public void value(EditedCrudPair<String, StringMap<String>> _v) {
        key.valueString(_v.getKey());
        key.getTextField().setEditable(false);
        for (EntryCust<String,AbsTextField> e:getTranslations().entryList()) {
            e.getValue().setText(StringUtil.nullToEmpty(_v.getValue().getVal(e.getKey())));
        }
    }

    public GeneComponentModelString getKey() {
        return key;
    }

    public StringMap<AbsTextField> getTranslations() {
        return translations;
    }
}
