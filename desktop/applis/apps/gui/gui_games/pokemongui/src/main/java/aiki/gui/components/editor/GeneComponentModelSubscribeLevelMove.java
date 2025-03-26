package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.MessagesDataPokemonData;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public final class GeneComponentModelSubscribeLevelMove implements AbsGeneComponentModelSubscribe<LevelMove> {
    private final AbstractProgramInfos programInfos;
    private final FacadeGame facade;
    private GeneComponentModelEltEnumSub<String> move;
    private final GeneComponentModelLong level;
    private final SubscribedTranslationList subscribedTranslationList;
    public GeneComponentModelSubscribeLevelMove(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub) {
        programInfos = _fact;
        facade = _facade;
        subscribedTranslationList = _sub;
        level = new GeneComponentModelLong(_fact);
    }
    @Override
    public AbsCustComponent geneEnum(int _select, int _value) {
        AbsPanel form_ = programInfos.getCompoFactory().newLineBox();
        form_.add(line(MessagesDataPokemonData.M_P_72_LEVEL,level.geneLong()));
        move = ConverterCommonMapUtil.buildMvFull(programInfos, facade,subscribedTranslationList);
        form_.add(line(MessagesDataPokemonData.M_P_72_MOVE,move.geneEnum()));
        return form_;
    }
    private AbsCustComponent line(String _key, AbsCustComponent _input) {
        return SubscribedTranslationList.line(programInfos,MessagesPkBean.PK_DATA,_key,_input);
    }
    @Override
    public LevelMove tryRet() {
        LevelMove lv_ = new LevelMove();
        lv_.setLevel(level.valueLong());
        lv_.setMove(move.tryRet());
        return lv_;
    }

    @Override
    public void setupValue(LevelMove _value) {
        level.valueLong(_value.getLevel());
        move.setupValue(_value.getMove());
    }

    @Override
    public IdList<SubscribedTranslation> getSubs() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(move.getSubs());
        return ids_;
    }

    public GeneComponentModelLong getLevel() {
        return level;
    }

    public GeneComponentModelEltEnumSub<String> getMove() {
        return move;
    }
}
