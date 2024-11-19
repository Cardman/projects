package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;

public final class ContentComponentModelEffectEndRoundSingleRelation {
    private CrudGeneFormSimpleFormSub<Long,Rate> rateDamageFunctionOfNbRounds;
    private CrudGeneFormMonteCarlo<Rate> lawForEnablingEffect;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        rateDamageFunctionOfNbRounds = new CrudGeneFormSimpleFormSub<Long, Rate>(_core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(), _core.getFrame());
        rateDamageFunctionOfNbRounds.initForm(new DisplayKeyOnlyLongRate(), new ComparingLongKey<Rate>(), new GeneComponentModelSubscribeFactoryDirect<Long>(new GeneComponentModelSubscribeLong(_core.getProgramInfos())), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(_core.getProgramInfos())));
        selected_.add(rateDamageFunctionOfNbRounds.getGroup());
        lawForEnablingEffect = buildMcRate(_core.getFrame(), _core.getProgramInfos());
        selected_.add(lawForEnablingEffect.getGroup());
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private CrudGeneFormMonteCarlo<Rate> buildMcRate(AbsCommonFrame _f, AbstractProgramInfos _core) {
        CrudGeneFormMonteCarlo<Rate> out_ = new CrudGeneFormMonteCarlo<Rate>(_core, new ComparingRateKey<LgInt>());
        out_.setFrame(_f);
        out_.initForm();
        out_.initFormKeys(new RateLgIntDisplayEntryCust(),new GeneComponentModelEventRate(_core), new ComparingRateKey<LgInt>());
        return out_;
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
