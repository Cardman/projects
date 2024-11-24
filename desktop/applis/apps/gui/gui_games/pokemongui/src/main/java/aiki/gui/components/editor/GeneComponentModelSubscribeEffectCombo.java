package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelSubscribeEffectCombo implements AbsGeneComponentModelSubscribe<EffectCombo> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private GeneComponentModelRate multEvtRateSecEff;
    private GeneComponentModelInt rankIncrementNbRound;
    private CrudGeneFormMonteCarlo<Rate> repeatedRoundsLaw;
    private CrudGeneFormSimpleElementSub<EffectEndRoundFoe> effectEndRound;
    private CrudGeneFormSimpleElementSub<EffectTeam> teamMove;
    private EffectCombo edited;

    public GeneComponentModelSubscribeEffectCombo(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        api = _fact;
        facadeGame = _facade;
        frame = _fr;
        factory = _sub;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        edited = Instances.newEffectCombo();
        AbsPanel form_ = api.getCompoFactory().newLineBox();
        multEvtRateSecEff = new GeneComponentModelRate(api);
        form_.add(multEvtRateSecEff.geneRate(Rate.zero()));
        rankIncrementNbRound = new GeneComponentModelInt(api);
        form_.add(rankIncrementNbRound.geneInt());
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
    @Override
    public EffectCombo tryRet() {
        edited.setMultEvtRateSecEff(multEvtRateSecEff.valueRate());
        edited.setRankIncrementNbRound((short) rankIncrementNbRound.valueInt());
        edited.setRepeatedRoundsLaw(ConverterCommonMapUtil.buildMonteCarloNumber(repeatedRoundsLaw.getList()));
        edited.setEffectEndRound(effectEndRound.getList());
        edited.setTeamMove(teamMove.getList());
        return edited;
    }

    @Override
    public void setupValue(EffectCombo _value) {
        multEvtRateSecEff.valueRate(_value.getMultEvtRateSecEff());
        rankIncrementNbRound.valueInt(_value.getRankIncrementNbRound());
        repeatedRoundsLaw.setupValues(new MapToEntriesListUtil<Rate,LgInt>().build(_value.getRepeatedRoundsLaw()));
        effectEndRound.setupValues(_value.getEffectEndRound());
        teamMove.setupValues(_value.getTeamMove());
        edited = _value;
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

    public CrudGeneFormSimpleElementSub<EffectEndRoundFoe> getEffectEndRound() {
        return effectEndRound;
    }

    public CrudGeneFormSimpleElementSub<EffectTeam> getTeamMove() {
        return teamMove;
    }
}
