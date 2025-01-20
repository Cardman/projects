package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeBoostHpRate implements AbsGeneComponentModelSubscribe<BoostHpRate> {
    private final AbstractProgramInfos programInfos;
    private GeneComponentModelLong boost;
    private GeneComponentModelRate hpRate;

    public GeneComponentModelSubscribeBoostHpRate(AbstractProgramInfos _core) {
        programInfos = _core;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        boost = new GeneComponentModelLong(programInfos);
        form_.add(boost.geneLong());
        hpRate = new GeneComponentModelRate(programInfos);
        form_.add(hpRate.geneRate());
        return form_;
    }


    @Override
    public BoostHpRate tryRet() {
        return new BoostHpRate(boost.valueLong(), hpRate.valueRate());
    }

    @Override
    public void setupValue(BoostHpRate _value) {
        boost.valueLong(_value.getBoost());
        hpRate.valueRate(_value.getHpRate());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
