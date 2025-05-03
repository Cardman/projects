package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelGymLeader extends AbsContentComponentModelTrainerUniq {
    private GymLeader edited = Instances.newGymLeader();
    private GeneComponentModelSubscribeString name;
    private GeneComponentModelEltEnumSub<Integer> tm;
    @Override
    protected TrainerOneFight entity() {
        return edited;
    }
    AbsPanel effectFormLeader(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, AbsCommonFrame _fr, FormLevelGrid _grid) {
        AbsPanel pan_ = effectForm(_core, _fac, _fact, _fr, _grid);
        name = new GeneComponentModelSubscribeString(_core,_fac);
        pan_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_NAME_TR,name.geneEnum()));
        tm = ConverterCommonMapUtil.buildTm(_core,_fac,_fact);
        pan_.add(SubscribedTranslationList.line(_core,MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_TM,tm.geneEnum()));
        return pan_;
    }

    GymLeader buildEntity() {
        buildComEntity();
        edited.setTm(tm.tryRet());
        edited.setName(name.tryRet());
        return edited;
    }
    void feedFormSub(GymLeader _edited) {
        edited = ConverterCommonMapUtil.copyGymLeader(_edited);
        feedForm(edited);
        tm.setupValue(edited.getTm());
        name.setupValue(edited.getName());
    }

}
