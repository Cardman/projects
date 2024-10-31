package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.StringUtil;

public final class GeneComponentModelTr implements GeneComponentModel<StringMap<String>> {
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facade;
    private final StringMap<AbsTextField> translations = new StringMap<AbsTextField>();
    public GeneComponentModelTr(AbstractProgramInfos _core, FacadeGame _facade) {
        this.compoFactory = _core;
        facade = _facade;
    }
    @Override
    public AbsCustComponent gene() {
        getTranslations().clear();
        AbsCompoFactory compoFactory_ = compoFactory.getCompoFactory();
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
        return sc_;
    }

    @Override
    public StringMap<String> value() {
        StringMap<String> trs_ = new StringMap<String>();
        for (EntryCust<String,AbsTextField> e:getTranslations().entryList()) {
            trs_.addEntry(e.getKey(),e.getValue().getText());
        }
        return trs_;
    }

    @Override
    public StringMap<String> value(StringMap<String> _v) {
        for (EntryCust<String,AbsTextField> e:getTranslations().entryList()) {
            e.getValue().setText(StringUtil.nullToEmpty(_v.getVal(e.getKey())));
        }
        return null;
    }

    public StringMap<AbsTextField> getTranslations() {
        return translations;
    }
}
