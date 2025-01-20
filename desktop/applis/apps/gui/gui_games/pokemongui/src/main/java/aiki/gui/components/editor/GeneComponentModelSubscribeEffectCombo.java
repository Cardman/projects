package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectCombo implements GeneComponentModel<ListEffectCombo> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private CrudGeneFormSimpleElementSub<String> key;
    private GeneComponentModelRate multEvtRateSecEff;
    private GeneComponentModelLong rankIncrementNbRound;
    private CrudGeneFormMonteCarlo<Rate> repeatedRoundsLaw;
    private CrudGeneFormSimpleElementSub<EffectEndRoundFoe> effectEndRound;
    private CrudGeneFormSimpleElementSub<EffectTeam> teamMove;

    public GeneComponentModelSubscribeEffectCombo(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        api = _fact;
        facadeGame = _facade;
        frame = _fr;
        factory = _sub;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactoryMv factoryMv_ = factory.getFactoryMv();
        key = new CrudGeneFormSimpleElementSub<String>(api, facadeGame, factory, frame);
        key.initForm(new DisplayEntryCustSubElementSimpleImpl<String>(factoryMv_,api,facadeGame,new StringMap<String>()),buildPart(api,facadeGame,factoryMv_,new StringMap<String>()));
        AbsPanel form_ = api.getCompoFactory().newLineBox();
        multEvtRateSecEff = new GeneComponentModelRate(api);
        form_.add(multEvtRateSecEff.geneRate());
        rankIncrementNbRound = new GeneComponentModelLong(api);
        form_.add(rankIncrementNbRound.geneLong());
        repeatedRoundsLaw = ConverterCommonMapUtil.buildMcRate(frame,api);
        form_.add(repeatedRoundsLaw.getGroup());
        effectEndRound = new CrudGeneFormSimpleElementSub<EffectEndRoundFoe>(api,facadeGame,factory,frame);
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRoundFoe>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRoundFoe>(new GeneComponentModelSubscribeEffectEndRoundFoe(new GeneComponentModelEffectEndRoundFoe(frame,api,facadeGame,factory))));
        form_.add(effectEndRound.getGroup());
        teamMove = new CrudGeneFormSimpleElementSub<EffectTeam>(api,facadeGame,factory,frame);
        teamMove.initForm(new DisplayEntryCustSubElementEffect<EffectTeam>(),new GeneComponentModelSubscribeFactoryDirect<EffectTeam>(new GeneComponentModelSubscribeEffectTeam(new GeneComponentModelEffectTeam(frame,api,facadeGame,factory))));
        form_.add(teamMove.getGroup());
        return form_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    @Override
    public ListEffectCombo value() {
        EffectCombo edited_ = Instances.newEffectCombo();
        edited_.setMultEvtRateSecEff(multEvtRateSecEff.valueRate());
        edited_.setRankIncrementNbRound(rankIncrementNbRound.valueLong());
        edited_.setRepeatedRoundsLaw(ConverterCommonMapUtil.buildMonteCarloNumber(repeatedRoundsLaw.getList()));
        edited_.setEffectEndRound(effectEndRound.getList());
        edited_.setTeamMove(teamMove.getList());
        return new ListEffectCombo(new StringList(key.getList()),edited_);
    }

    @Override
    public void value(ListEffectCombo _value) {
        EffectCombo combo_ = ConverterCommonMapUtil.copyEffectCombo(_value.getCombo());
        multEvtRateSecEff.valueRate(combo_.getMultEvtRateSecEff());
        rankIncrementNbRound.valueLong(combo_.getRankIncrementNbRound());
        repeatedRoundsLaw.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(combo_.getRepeatedRoundsLaw()));
        effectEndRound.setupValues(combo_.getEffectEndRound());
        teamMove.setupValues(combo_.getTeamMove());
        key.setupValues(_value.getList());
    }

    public CrudGeneFormSimpleElementSub<String> getKey() {
        return key;
    }

    public IdList<SubscribedTranslation> all() {
        return key.subscribeButtons();
    }

    public CrudGeneFormSimpleElementSub<EffectEndRoundFoe> getEffectEndRound() {
        return effectEndRound;
    }

    public CrudGeneFormSimpleElementSub<EffectTeam> getTeamMove() {
        return teamMove;
    }
}
