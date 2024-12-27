package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelTrainerMultiFights {
    private TrainerMultiFights edited = Instances.newTrainerMultiFights();
    private final ContentComponentModelTrainer trainer = new ContentComponentModelTrainer();
    private CrudGeneFormSimpleElement<PokemonTeam> teams;
    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact,AbsCommonFrame _fr, FormLevelGrid _grid) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        selected_.add(trainer.effectForm(_core, _fac, _fact));
        IdList<SubscribedTranslation> group_= new IdList<SubscribedTranslation>();
        group_.addAllElts(trainer.getMiniFileName().subs());
        group_.addAllElts(trainer.getMaxiFileName().subs());
        _grid.subs(group_);
        teams = new CrudGeneFormSimpleElement<PokemonTeam>(_core,_fac,_fact,_fr);
        teams.initForm(new DisplayEntryCustSubElementEffect<PokemonTeam>(),_core,new GeneComponentModelSubscribeFactoryDirect<PokemonTeam>(new GeneComponentModelSubscribePokemonTeam(_fr,_core,_fac,_fact)));
        selected_.add(teams.getGroup());
        return selected_;
    }
    TrainerMultiFights buildEntity() {
        trainer.buildEntity(edited);
        edited.setTeamsRewards(teams.getList());
        return edited;
    }
    void feedForm(TrainerMultiFights _edited) {
        edited = ConverterCommonMapUtil.copyTrainerMultiFights(_edited);
        trainer.feedForm(edited);
        teams.setupValues(edited.getTeamsRewards());
    }

    public ContentComponentModelTrainer getTrainer() {
        return trainer;
    }

    public CrudGeneFormSimpleElement<PokemonTeam> getTeams() {
        return teams;
    }

}
