package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelSubscribeTypesDuo implements AbsGeneComponentModelSubscribe<TypesDuo> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<String> damageType;
    private GeneComponentModelEltEnumSub<String> pokemonType;
    private final SubscribedTranslationList subscribedTranslationList;
    private final String file;
    private final String titleKey;
    private final String titleValue;

    public GeneComponentModelSubscribeTypesDuo(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription, String _file, String _k,String _v) {
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
        damageType = ConverterCommonMapUtil.buildTypeElt(programInfos, facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleKey,damageType.geneEnum()));
        pokemonType = ConverterCommonMapUtil.buildTypeElt(programInfos, facade,subscribedTranslationList);
        form_.add(SubscribedTranslationList.line(programInfos,file,titleValue,pokemonType.geneEnum()));
        if (GeneComponentModelEltStrCom.disable(_select, _value)) {
            damageType.getSelectUniq().getSelect().setEnabled(false);
            pokemonType.getSelectUniq().getSelect().setEnabled(false);
        }
        return form_;
    }


    @Override
    public TypesDuo tryRet() {
        TypesDuo lv_ = new TypesDuo();
        lv_.setDamageType(damageType.tryRet());
        lv_.setPokemonType(pokemonType.tryRet());
        return lv_;
    }

    @Override
    public void setupValue(TypesDuo _value) {
        damageType.setupValue(_value.getDamageType());
        pokemonType.setupValue(_value.getPokemonType());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(damageType.getSubs());
        ids_.addAllElts(pokemonType.getSubs());
        return ids_;
    }

}
