package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import code.gui.*;
import code.gui.initialize.*;

public final class ContentComponentModelTrainerLeague extends AbsContentComponentModelTrainerUniq {
    private TrainerLeague edited = Instances.newTrainerLeague();
    private GeneComponentModelSubscribeString name;
    @Override
    protected TrainerOneFight entity() {
        return edited;
    }
    AbsPanel effectFormLeader(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr, FormLevelGrid _grid) {
        AbsPanel pan_ = effectForm(_core, _fac, _fact, _fr, _grid);
        name = new GeneComponentModelSubscribeString(_core,_fac);
        pan_.add(name.geneEnum());
        return pan_;
    }

    TrainerLeague buildEntity() {
        buildComEntity();
        edited.setName(name.tryRet());
        return edited;
    }
    void feedFormSub(TrainerLeague _edited) {
        edited = ConverterCommonMapUtil.copyTrainerLeague(_edited);
        feedForm(edited);
        name.setupValue(edited.getName());
    }

}
