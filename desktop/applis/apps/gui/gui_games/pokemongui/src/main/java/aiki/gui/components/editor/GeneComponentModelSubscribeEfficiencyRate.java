package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeEfficiencyRate implements AbsGeneComponentModelSubscribe<EfficiencyRate> {
    private final AbstractProgramInfos programInfos;
    private GeneComponentModelRate eff;
    private GeneComponentModelRate hpRate;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeEfficiencyRate(AbstractProgramInfos _core, String _f, String _k,String _v) {
        programInfos = _core;
        file =_f;
        titleKey =_k;
        titleValue =_v;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        eff = new GeneComponentModelRate(programInfos);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleKey,eff.geneRate()));
        hpRate = new GeneComponentModelRate(programInfos);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,hpRate.geneRate()));
        return form_;
    }


    @Override
    public EfficiencyRate tryRet() {
        return new EfficiencyRate(eff.valueRate(), hpRate.valueRate());
    }

    @Override
    public void setupValue(EfficiencyRate _value) {
        eff.valueRate(_value.getEff());
        hpRate.valueRate(_value.getHpRate());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

}
