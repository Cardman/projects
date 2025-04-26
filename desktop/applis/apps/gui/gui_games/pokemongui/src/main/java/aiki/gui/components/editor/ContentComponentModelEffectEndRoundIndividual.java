package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.MessagesDataEndroundIndividual;
import code.scripts.pages.aiki.MessagesPkBean;
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
        selected_.add(line(_core,MessagesDataEndroundIndividual.M_P_6_DELETE_ALL_STATUS_INTRO,deleteAllStatus.geneRate()));
        recoilDamage = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundIndividual.M_P_6_RECOIL_DAMAGE_INTRO,recoilDamage.geneRate()));
        healHp = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_INTRO,healHp.geneRate()));
        healHpByOwnerTypes = new CrudGeneFormSimpleFormSub<String, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        healHpByOwnerTypes.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactoryTy(), _core.getProgramInfos(), _core.getFacadeGame(),new StringMap<String>()), buildPart(_core, _core.getFactory().getFactoryTy(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE);
        selected_.add(line(_core,MessagesDataEndroundIndividual.M_P_6_HEAL_HP_BY_OWNER_TYPES,healHpByOwnerTypes.getGroup()));
        multDamageStatus = new CrudGeneFormSimpleFormSub<String,Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        multDamageStatus.initFormWithVal(new DisplayEntryCustSubElementImpl<String, Rate>(_core.getFactory().getFactorySt(), _core.getProgramInfos(), _core.getFacadeGame(),new StringMap<String>()), buildPart(_core, _core.getFactory().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),MessagesPkBean.ENDROUND_INDIVIDUAL,MessagesDataEndroundIndividual.M_P_6_MULT_DAMAGE_STATUS_KEY,MessagesDataEndroundIndividual.M_P_6_MULT_DAMAGE_STATUS_VALUE);
        selected_.add(line(_core,MessagesDataEndroundIndividual.M_P_6_MULT_DAMAGE_STATUS,multDamageStatus.getGroup()));
        userStatusEndRound = ConverterCommonMapUtil.buildStatus(_core.getProgramInfos(), _core.getFacadeGame(),_core.getFactory(),ConverterCommonMapUtil.defKeyEmpty(" "));
        selected_.add(line(_core,MessagesDataEndroundIndividual.M_P_6_USER_STATUS,userStatusEndRound.geneEnum()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.ENDROUND_INDIVIDUAL, _key,_input);
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
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

    public CrudGeneFormSimpleFormSub<String, Rate> getHealHpByOwnerTypes() {
        return healHpByOwnerTypes;
    }

    public CrudGeneFormSimpleFormSub<String, Rate> getMultDamageStatus() {
        return multDamageStatus;
    }
}
