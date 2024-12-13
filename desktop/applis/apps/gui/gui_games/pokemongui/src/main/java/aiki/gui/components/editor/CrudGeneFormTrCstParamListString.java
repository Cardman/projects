package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrCstParamListString<K> implements AbsCrudGeneFormTrCstOpen,CrudGeneFormTrCstParamList<K> {
    private final AbstractProgramInfos api;
    private final AbsCommonFrame frame;
    private final FacadeGame facadeGame;
    private final IdMap<K,GeneComponentModelText> fields = new IdMap<K,GeneComponentModelText>();
    private final IdMap<K,AbsButton> buttons = new IdMap<K,AbsButton>();
    private final NumStrIdRetriever<K> keyRet;

    public CrudGeneFormTrCstParamListString(AbstractProgramInfos _core, FacadeGame _f, NumStrIdRetriever<K> _k) {
        api = _core;
        frame = _core.getFrameFactory().newCommonFrame();
        facadeGame = _f;
        keyRet = _k;
    }

    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        fields.clear();
        buttons.clear();
        int i_ = 0;
        for (EntryCust<K, String> k: keyRet.infos(facadeGame).entryList()) {
            AbsPanel line_ = api.getCompoFactory().newLineBox();
            GeneComponentModelText field_ = new GeneComponentModelText(api);
            AbsCustComponent compo_ = field_.geneString();
            field_.valueString(k.getValue());
            line_.add(compo_);
            AbsButton but_ = api.getCompoFactory().newPlainButton(Long.toString(i_));
            but_.addActionListener(new RenameCstParamEvent<K>(this, k.getKey()));
            line_.add(but_);
            page_.add(line_);
            fields.addEntry(k.getKey(), field_);
            buttons.addEntry(k.getKey(), but_);
            i_++;
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public IdMap<K,GeneComponentModelText> getFields() {
        return fields;
    }

    public IdMap<K, AbsButton> getButtons() {
        return buttons;
    }

    @Override
    public void apply(K _k) {
        keyRet.infos(facadeGame).put(_k, fields.getVal(_k).valueString());
    }
}
