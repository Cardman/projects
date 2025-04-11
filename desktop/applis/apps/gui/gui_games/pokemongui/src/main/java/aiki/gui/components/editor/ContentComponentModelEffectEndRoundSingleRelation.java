package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectEndRoundSingleRelation {
    private CrudGeneFormSimpleFormSub<Long,Rate> rateDamageFunctionOfNbRounds;
    private CrudGeneFormMonteCarlo<Rate> lawForEnablingEffect;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        rateDamageFunctionOfNbRounds = new CrudGeneFormSimpleFormSub<Long, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        rateDamageFunctionOfNbRounds.initForm(new DisplayKeyOnlyLongRate(), new ComparingLongKey<Rate>(), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())),formatTxt(_core,MessagesDataEndroundSinglerelation.M_P_10_SUFFERED_RD),formatTxt(_core,MessagesDataEndroundSinglerelation.M_P_10_SUFFERED_RATE));
        selected_.add(line(_core, MessagesDataEndroundSinglerelation.M_P_10_SUFFERED,rateDamageFunctionOfNbRounds.getGroup()));
        lawForEnablingEffect = ConverterCommonMapUtil.buildMcRate(_core.getFrame(), _core.getProgramInfos(),formatTxt(_core,MessagesDataEndroundSinglerelation.M_P_10_LAW_RD),formatTxt(_core,MessagesDataEndroundSinglerelation.M_P_10_LAW_VALUE));
        selected_.add(line(_core,MessagesDataEndroundSinglerelation.M_P_10_LAW,lawForEnablingEffect.getGroup()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return SubscribedTranslationList.lineDir(_core.getProgramInfos(),formatTxt(_core,_key),_input);
    }

    private String formatTxt(AbsGeneComponentModelEffect _core,String _key) {
        return SubscribedTranslationList.formatTxt(_core.getProgramInfos(), MessagesPkBean.ENDROUND_SINGLERELATION, _key);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectEndRoundSingleRelation _edited) {
        _edited.setRateDamageFunctionOfNbRounds(ConverterCommonMapUtil.buildLongMapRate(rateDamageFunctionOfNbRounds.getList()));
        _edited.setLawForEnablingEffect(ConverterCommonMapUtil.buildMonteCarloNumber(lawForEnablingEffect.getList()));
    }
    void feedForm(EffectEndRoundSingleRelation _edited) {
        rateDamageFunctionOfNbRounds.setupValues(new MapToEntriesListUtil<Long,Rate>().build(_edited.getRateDamageFunctionOfNbRounds()));
        lawForEnablingEffect.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(_edited.getLawForEnablingEffect()));
    }

    public CrudGeneFormSimpleFormSub<Long, Rate> getRateDamageFunctionOfNbRounds() {
        return rateDamageFunctionOfNbRounds;
    }
}
