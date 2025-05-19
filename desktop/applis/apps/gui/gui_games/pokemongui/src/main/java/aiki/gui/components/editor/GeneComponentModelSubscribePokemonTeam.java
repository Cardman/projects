package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class GeneComponentModelSubscribePokemonTeam implements AbsGeneComponentModelSubscribe<PokemonTeam> {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private PokemonTeam team = Instances.newPokemonTeam();
    private GeneComponentModelLong reward;
    private final ContentComponentModelSubscribePkTrainer simple = new ContentComponentModelSubscribePkTrainer();
    public GeneComponentModelSubscribePokemonTeam(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        api = _core;
        facadeGame = _fac;
        factory = _fact;
        frame = _f;
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsCompoFactory compoFactory_ = api.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(api.currentLg())).getMapping().getVal(MessagesEditorSelect.PK_TEAM));
        AbsPanel form_ = compoFactory_.newLineBox();
        reward = new GeneComponentModelLong(api);
        form_.add(SubscribedTranslationList.line(api,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_REWARD,reward.geneLong()));
        form_.add(simple.form(api,facadeGame,factory,frame, MessagesDataMapPokemonKey.M_P_34_FOE_TEAM));
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public PokemonTeam tryRet() {
        team.setTeam(simple.getWalk().getList());
        team.setReward(reward.valueLong());
        return team;
    }

    @Override
    public void setupValue(PokemonTeam _value) {
        team = ConverterCommonMapUtil.copyPokemonTeam(_value);
        reward.valueLong(team.getReward());
        simple.getWalk().setupValues(team.getTeam());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        return new IdList<SubscribedTranslation>();
    }

    public ContentComponentModelSubscribePkTrainer getSimple() {
        return simple;
    }
}
