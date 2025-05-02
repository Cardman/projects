package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class FormTrainerPk extends FormAbsPk {
    private PkTrainer pkTrainer;
    private GeneComponentModelLsStrSub<String,StringList> moves;
    public FormTrainerPk(AbstractProgramInfos _ed, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _f) {
        super(_ed, _facade, _sub, _f);
    }

    public void feedForm() {
        pkTrainer = Instances.newPkTrainer();
        feedFormQuick();
        moves = ConverterCommonMapUtil.buildMoveList(getApi(),getFacadeGame(),getSubscribedTranslationList());
        getForm().add(line(MessagesDataMapPokemonKey.M_P_34_MOVES,moves.geneEnum()));
    }

    public void feedForm(PkTrainer _wp) {
        pkTrainer = ConverterCommonMapUtil.copyPkTrainer(_wp);
        feedFormQuick(pkTrainer);
        moves.setupValue(pkTrainer.getMoves());
    }

    public void feedSubs(IdList<SubscribedTranslation> _subs) {
        feedSubsQuick(_subs);
        _subs.addAllElts(moves.getSubs());
    }

    public PkTrainer buildEntity() {
        buildEntity(pkTrainer);
        pkTrainer.setMoves(moves.tryRet());
        return getPkTrainer();
    }

    public PkTrainer getPkTrainer() {
        return pkTrainer;
    }
}
