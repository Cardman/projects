package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeBoostHpRate implements AbsGeneComponentModelSubscribe<BoostHpRate> {
    private final AbstractProgramInfos programInfos;
    private GeneComponentModelLong boost;
    private GeneComponentModelRate hpRate;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeBoostHpRate(AbstractProgramInfos _core, String _f, String _k,String _v) {
        programInfos = _core;
        file =_f;
        titleKey =_k;
        titleValue =_v;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        boost = new GeneComponentModelLong(programInfos);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleKey,boost.geneLong()));
        hpRate = new GeneComponentModelRate(programInfos);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,hpRate.geneRate()));
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
