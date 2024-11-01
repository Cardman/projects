package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public class GeneComponentModelLevelMove implements GeneComponentModel<LevelMove> {
    private final AbstractProgramInfos programInfos;
    private final GeneComponentModelEltStrSub move;
    private final GeneComponentModelInt level;

    public GeneComponentModelLevelMove(AbstractProgramInfos _core, FacadeGame _facade) {
        programInfos = _core;
        move = ConverterCommonMapUtil.buildMvFull(_core, _facade);
        level = new GeneComponentModelInt(_core);
    }

    @Override
    public AbsCustComponent gene() {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        form_.add(level.geneInt());
        form_.add(move.geneEnum(""));
        return form_;
    }

    @Override
    public LevelMove value() {
        LevelMove lv_ = new LevelMove();
        lv_.setLevel((short) level.valueInt());
        lv_.setMove(move.tryRet(""));
        return lv_;
    }

    @Override
    public LevelMove value(LevelMove _v) {
        level.valueInt(_v.getLevel());
        move.setupValue(_v.getMove());
        return null;
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(move.subsMv());
        return ids_;
    }

    public GeneComponentModelInt getLevel() {
        return level;
    }

    public GeneComponentModelEltStrSub getMove() {
        return move;
    }
}
