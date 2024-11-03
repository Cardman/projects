package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public class GeneComponentModelLevelMove implements GeneComponentModel<LevelMove> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltStrSub move;
    private final GeneComponentModelInt level;
    private final SubscribedTranslationList subscribedTranslationList;

    public GeneComponentModelLevelMove(AbstractProgramInfos _core, FacadeGame _f, SubscribedTranslationList _subscription) {
        programInfos = _core;
        facade = _f;
        subscribedTranslationList = _subscription;
        level = new GeneComponentModelInt(_core);
    }

    @Override
    public AbsCustComponent gene() {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        form_.add(level.geneInt());
        move = ConverterCommonMapUtil.buildMvFull(programInfos, facade,subscribedTranslationList);
        form_.add(move.geneEnum());
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
        ids_.addAllElts(move.getSubs());
        return ids_;
    }

    public GeneComponentModelInt getLevel() {
        return level;
    }

    public GeneComponentModelEltStrSub getMove() {
        return move;
    }
}
