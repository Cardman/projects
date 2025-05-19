package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntImgCstList implements AbsCrudGeneFormTrCstOpen {


    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsCommonFrame frame;
    private final IdMap<Statistic,ContentGeneComponentModelImg> fields = new IdMap<Statistic,ContentGeneComponentModelImg>();
    private final IdMap<Statistic, AbsButton> buttons = new IdMap<Statistic,AbsButton>();

    public CrudGeneFormEntImgCstList(AbstractProgramInfos _core, FacadeGame _facade, AbsCommonFrame _f) {
        api = _core;
        facadeGame = _facade;
        frame = _f;
    }



    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        fields.clear();
        buttons.clear();
        for (Statistic s: Statistic.getStatisticsWithBoost()) {
//            AbsPanel line_ = api.getCompoFactory().newLineBox();
            ContentGeneComponentModelImg cont_ = new ContentGeneComponentModelImg();
            AbsCustComponent line_ = cont_.gene(api,null);
//            line_.add(cont_.gene(api,null));
            cont_.updateImg(ConverterCommonMapUtil.copyImageArrayBaseSixtyFour(facadeGame.getData().getAnimStatis().getVal(s.getStatName())),api);
            AbsButton but_ = api.getCompoFactory().newPlainButton(s.getStatName());
            but_.addActionListener(new AssociateImgOtherCstEvent(this, s));
            page_.add(line_);
            page_.add(but_);
            fields.addEntry(s, cont_);
            buttons.addEntry(s, but_);
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public IdMap<Statistic, ContentGeneComponentModelImg> getFields() {
        return fields;
    }

    public IdMap<Statistic, AbsButton> getButtons() {
        return buttons;
    }

    public void apply(Statistic _k) {
        facadeGame.getData().getAnimStatis().put(_k.getStatName(), fields.getVal(_k).edited(api));
    }
}
