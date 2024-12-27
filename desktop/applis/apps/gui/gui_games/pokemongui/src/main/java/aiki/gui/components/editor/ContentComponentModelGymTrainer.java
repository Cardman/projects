package aiki.gui.components.editor;

import aiki.instances.*;
import aiki.map.characters.*;

public final class ContentComponentModelGymTrainer extends AbsContentComponentModelTrainerUniq {
    private GymTrainer edited = Instances.newGymTrainer();
    @Override
    protected TrainerOneFight entity() {
        return edited;
    }

    GymTrainer buildEntity() {
        buildComEntity();
        return edited;
    }
    void feedFormSub(GymTrainer _edited) {
        edited = ConverterCommonMapUtil.copyGymTrainer(_edited);
        feedForm(edited);
    }

}
