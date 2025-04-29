package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeWeatherType implements AbsGeneComponentModelSubscribe<WeatherType> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<String> move;
    private GeneComponentModelEltEnumSub<String> type;
    private final SubscribedTranslationList subscribedTranslationList;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeWeatherType(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription, String _file, String _k,String _v) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
        file =_file;
        titleKey =_k;
        titleValue =_v;
    }

    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        move = ConverterCommonMapUtil.buildMvFull(programInfos,facade,subscribedTranslationList,new StringMap<String>());
        form_.add(SubscribedTranslationList.line(programInfos,file,titleKey,move.geneEnum()));
        type = ConverterCommonMapUtil.buildTypeElt(programInfos, facade,subscribedTranslationList,new StringMap<String>());
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,type.geneEnum()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            move.getSelectUniq().getSelect().setEnabled(false);
            type.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public WeatherType tryRet() {
        return new WeatherType(move.tryRet(), type.tryRet());
    }

    @Override
    public void setupValue(WeatherType _value) {
        move.setupValue(_value.getWeather());
        type.setupValue(_value.getType());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(move.getSubs());
        ids_.addAllElts(type.getSubs());
        return ids_;
    }

}
