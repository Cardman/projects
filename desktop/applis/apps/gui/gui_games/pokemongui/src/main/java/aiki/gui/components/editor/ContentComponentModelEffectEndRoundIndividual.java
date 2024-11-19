package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectEndRoundIndividual {
    private GeneComponentModelRate deleteAllStatus;
    private GeneComponentModelRate recoilDamage;
    private GeneComponentModelRate healHp;
    private CrudGeneFormSimpleFormSub<String,Rate> healHpByOwnerTypes;
    private CrudGeneFormSimpleFormSub<String,Rate> multDamageStatus;
    private GeneComponentModelEltEnumSub<String> userStatusEndRound;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        deleteAllStatus = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(deleteAllStatus.geneRate(Rate.zero()));
        recoilDamage = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(recoilDamage.geneRate(Rate.zero()));
        healHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(healHp.geneRate(Rate.zero()));
        healHpByOwnerTypes = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        healHpByOwnerTypes.initForm(new DisplayEntryCustSubImpl<String>(_core.getFactory().getFactoryTy(), new StringMap<String>()),_core.getFactory().getFactoryTy().buildMessages(_core.getProgramInfos(),_core.getFacadeGame()), buildPart(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory().getFactoryTy(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())));
        selected_.add(healHpByOwnerTypes.getGroup());
        multDamageStatus = new CrudGeneFormSimpleFormSub<String,Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multDamageStatus.initForm(new DisplayEntryCustSubImpl<String>(_core.getFactory().getFactorySt(), new StringMap<String>()),_core.getFactory().getFactorySt().buildMessages(_core.getProgramInfos(),_core.getFacadeGame()), buildPart(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())));
        selected_.add(multDamageStatus.getGroup());
        userStatusEndRound = ConverterCommonMapUtil.buildStatus(_core.getProgramInfos(), _core.getFacadeGame(),_core.getFactory(),ConverterCommonMapUtil.defKeyEmpty(" "));
        selected_.add(userStatusEndRound.geneEnum());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundIndividual _edited) {
        _edited.setDeleteAllStatus(deleteAllStatus.valueRate());
        _edited.setRecoilDamage(recoilDamage.valueRate());
        _edited.setHealHp(healHp.valueRate());
        _edited.setHealHpByOwnerTypes(ConverterCommonMapUtil.buildStringMapRate(healHpByOwnerTypes.getList()));
        _edited.setMultDamageStatus(ConverterCommonMapUtil.buildStringMapRate(multDamageStatus.getList()));
        _edited.setUserStatusEndRound(userStatusEndRound.tryRet());
    }
    void feedForm(EffectEndRoundIndividual _edited) {
        deleteAllStatus.valueRate(_edited.getDeleteAllStatus());
        recoilDamage.valueRate(_edited.getRecoilDamage());
        healHp.valueRate(_edited.getHealHp());
        healHpByOwnerTypes.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getHealHpByOwnerTypes()));
        multDamageStatus.setupValues(new MapToEntriesListUtil<String,Rate>().build(_edited.getMultDamageStatus()));
        userStatusEndRound.setupValue(_edited.getUserStatusEndRound());
    }

    public GeneComponentModelEltEnumSub<String> getUserStatusEndRound() {
        return userStatusEndRound;
    }
}
