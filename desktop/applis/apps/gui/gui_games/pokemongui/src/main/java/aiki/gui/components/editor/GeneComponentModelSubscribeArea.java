package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.levels.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeArea implements AbsGeneComponentModelSubscribe<AbsAreaApparition>,ChangeableFormType {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private AbsAreaApparition area;
    private AbsCustCheckBox single;
    private GeneComponentModelInt avgNbSteps;
    private final ContentComponentModelSubscribeAreaSimple simple = new ContentComponentModelSubscribeAreaSimple();
    private final ContentComponentModelSubscribeAreaMult mult = new ContentComponentModelSubscribeAreaMult();
    public GeneComponentModelSubscribeArea(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        api = _core;
        facadeGame = _fac;
        factory = _fact;
        frame = _f;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsCompoFactory compoFactory_ = api.getCompoFactory();
        single = compoFactory_.newCustCheckBox();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        AbsPanel form_ = compoFactory_.newLineBox();
        avgNbSteps = new GeneComponentModelInt(api);
        form_.add(avgNbSteps.geneInt());
        form_.add(single);
        form_.add(simple.form(api,facadeGame,factory,frame));
        form_.add(mult.form(api,facadeGame,factory,frame));
        sc_.setViewportView(form_);
        page_.add(sc_);
        single.setSelected(false);
        applyChange();
        single.addActionListener(new ChangeMoveKindEvent(this));
        return page_;
    }

    @Override
    public void applyChange() {
        simple.display(single.isSelected());
        mult.display(!single.isSelected());
        if (single.isSelected()) {
            area = Instances.newAreaApparition();
        } else {
            area = new MultAreaApparition();
        }
        frame.pack();
    }

    @Override
    public AbsAreaApparition tryRet() {
        if (area instanceof MultAreaApparition) {
            mult.buildEntity((MultAreaApparition)area);
        }
        if (area instanceof AreaApparition) {
            simple.buildEntity((AreaApparition)area);
        }
        area.setAvgNbSteps((short) avgNbSteps.valueInt());
        return area;
    }

    @Override
    public void setupValue(AbsAreaApparition _value) {
        area = ConverterCommonMapUtil.copyArea(_value);
        avgNbSteps.valueInt(area.getAvgNbSteps());
        if (area instanceof MultAreaApparition) {
            mult.feedForm((MultAreaApparition)area);
        }
        if (area instanceof AreaApparition) {
            simple.feedForm((AreaApparition)area);
        }
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

    public AbsCustCheckBox getSingle() {
        return single;
    }

    public ContentComponentModelSubscribeAreaMult getMult() {
        return mult;
    }

    public ContentComponentModelSubscribeAreaSimple getSimple() {
        return simple;
    }
}
