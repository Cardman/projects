package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.map.characters.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public abstract class AbsContentComponentModelTrainerUniq {
    private final ContentComponentModelTrainer trainerImg = new ContentComponentModelTrainer();
    private final ContentComponentModelSubscribePkTrainer trainer = new ContentComponentModelSubscribePkTrainer();
    private GeneComponentModelInt reward;
    protected AbsContentComponentModelTrainerUniq() {
    }

    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact,AbsCommonFrame _fr, FormLevelGrid _grid) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        selected_.add(trainerImg.effectForm(_core, _fac, _fact));
        IdList<SubscribedTranslation> group_= new IdList<SubscribedTranslation>();
        group_.addAllElts(trainerImg.getMiniFileName().subs());
        group_.addAllElts(trainerImg.getMaxiFileName().subs());
        _grid.subs(group_);
        reward = new GeneComponentModelInt(_core);
        selected_.add(reward.geneInt());
        selected_.add(trainer.form(_core,_fac,_fact,_fr));
        return selected_;
    }
    void buildComEntity() {
        TrainerOneFight e_ = entity();
        e_.setTeam(trainer.getWalk().getList());
        e_.setReward(reward.valueInt());
        trainerImg.buildEntity(e_);
    }

    void feedForm(TrainerOneFight _edited) {
        trainer.getWalk().setupValues(_edited.getTeam());
        reward.valueInt(_edited.getReward());
        trainerImg.feedForm(_edited);
    }
    protected abstract TrainerOneFight entity();

    public ContentComponentModelTrainer getTrainerImg() {
        return trainerImg;
    }

}
