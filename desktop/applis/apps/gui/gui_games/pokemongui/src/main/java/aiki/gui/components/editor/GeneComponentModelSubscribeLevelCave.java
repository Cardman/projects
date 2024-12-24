package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.levels.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;

public final class GeneComponentModelSubscribeLevelCave {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList factory;
    private final AbsCommonFrame frame;
    private final CrudGeneFormLevel cave;
    private LevelCave edited = Instances.newLevelCave();
    private final ContentComponentModelLevelWithWild wild = new ContentComponentModelLevelWithWild();
    public GeneComponentModelSubscribeLevelCave(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact, CrudGeneFormLevel _c) {
        api = _core;
        facadeGame = _fac;
        factory = _fact;
        frame = _f;
        cave = _c;
    }
    public AbsCustComponent geneEnum() {
        AbsCompoFactory compoFactory_ = api.getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        AbsCustComponent compo_ = wild.form(api, facadeGame, factory, frame);
        edited = Instances.newLevelCave();
        Points<Block> blocks_ = edited.getBlocks();
        wild.setupGridDims(blocks_,(short) cave.getSelectedPlace(), (byte) cave.getSelectedLevel(),cave.getPlace(),edited);
        wild.getAreas().setBlocks(blocks_);
        sc_.setViewportView(compo_);
        page_.add(sc_);
        return page_;
    }

    public LevelCave tryRet() {
        edited.setBlocks(wild.getLevel().getEdited());
        edited.setWildPokemonAreas(wild.getAreas().getList());
        return edited;
    }

    public void setupValue(LevelCave _value) {
        Points<Block> blocks_ = ConverterCommonMapUtil.copyPointsBlock(_value.getBlocks());
        wild.setupGridDims(blocks_,(short) cave.getSelectedPlace(), (byte) cave.getSelectedLevel(),cave.getPlace(),_value);
        wild.getAreas().setBlocks(blocks_);
        edited = _value;
    }


    public ContentComponentModelLevelWithWild getWild() {
        return wild;
    }
}
