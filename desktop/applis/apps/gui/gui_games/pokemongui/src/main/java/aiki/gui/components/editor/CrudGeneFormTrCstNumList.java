package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormTrCstNumList implements AbsCrudGeneFormTrCstOpen {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsCommonFrame frame;
    private final StringMap<GeneComponentModelRate> fields = new StringMap<GeneComponentModelRate>();

    public CrudGeneFormTrCstNumList(AbstractProgramInfos _core, FacadeGame _facade) {
        api = _core;
        facadeGame = _facade;
        frame = _core.getFrameFactory().newCommonFrame();
    }

    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        fields.clear();
        for (int i = 0; i <= 20; i++) {
            AbsPanel line_ = api.getCompoFactory().newLineBox();
            GeneComponentModelRate field_ = new GeneComponentModelRate(api);
            String key_ = Long.toString(i);
            line_.add(field_.geneRate(facadeGame.getData().constNum(key_)));
            field_.getTextRate().addActionListener(new RenameCstNumEvent(this, key_, field_));
            page_.add(line_);
            fields.addEntry(key_, field_);
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public StringMap<GeneComponentModelRate> getFields() {
        return fields;
    }

    public void apply(String _k, GeneComponentModelRate _f) {
        facadeGame.getData().getConstNum().put(_k, _f.valueRate());
    }
}
