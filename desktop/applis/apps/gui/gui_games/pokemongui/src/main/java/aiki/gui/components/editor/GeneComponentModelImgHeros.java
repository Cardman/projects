package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import aiki.game.player.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.enums.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelImgHeros implements GeneComponentModel<EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>> {
    private final AbstractProgramInfos compoFactory;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslationList;
    private final ContentGeneComponentModelImg content = new ContentGeneComponentModelImg();
    private GeneComponentModelElt<Sex> sex;
    private GeneComponentModelElt<Direction> direction;
    private GeneComponentModelEltEnumSub<EnvironmentType> environment;
    private final boolean withDirection;
    public GeneComponentModelImgHeros(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _sub, boolean _withDir) {
        compoFactory = _core;
        facadeGame = _fac;
        subscribedTranslationList = _sub;
        withDirection = _withDir;
    }

    @Override
    public AbsCustComponent gene(int _select) {
        StringMap<String> m_ = MessagesPkEditor.getMessagesEditorSelectHerosSexTr(MessagesPkEditor.getAppliTr(compoFactory.currentLg())).getMapping();
        IdMap<Sex,String> s_ = new IdMap<Sex, String>();
        for (EntryCust<String,String> e: m_.entryList()) {
            s_.put(Sex.getSexByName(e.getKey(),facadeGame.getSexList()),e.getValue());
        }
        AbsPanel k_ = compoFactory.getCompoFactory().newPageBox();
        sex = new GeneComponentModelElt<Sex>(compoFactory, s_);
        k_.add(sex.geneEnum());
        if (withDirection) {
            IdMap<Direction,String> d_ = new IdMap<Direction, String>();
            d_.addEntry(Direction.UP,"\u2191");
            d_.addEntry(Direction.DOWN,"\u2193");
            d_.addEntry(Direction.LEFT,"\u2190");
            d_.addEntry(Direction.RIGHT,"\u2192");
            direction = new GeneComponentModelElt<Direction>(compoFactory, d_);
            k_.add(direction.geneEnum());
        }
        environment = ConverterCommonMapUtil.buildEnvironmentType(compoFactory,facadeGame,subscribedTranslationList);
        k_.add(environment.geneEnum());
        if (_select >= 0) {
            sex.getSelect().setEnabled(false);
            if (direction != null) {
                direction.getSelect().setEnabled(false);
            }
            environment.getSelectUniq().getSelect().setEnabled(false);
        }
        return content.gene(compoFactory,k_);
    }

    @Override
    public EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour> value() {
        if (withDirection) {
            return new EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>(new ImageHeroKey(environment.tryRet(),direction.tryRet(),sex.tryRet()), content.edited(compoFactory));
        }
        return new EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour>(new ImageHeroKey(environment.tryRet(),sex.tryRet()), content.edited(compoFactory));
    }

    @Override
    public void value(EditedCrudPair<ImageHeroKey, ImageArrayBaseSixtyFour> _v) {
        environment.setupValue(_v.getKey().getType());
        if (withDirection) {
            direction.setupValue(_v.getKey().getDirection());
        }
        sex.setupValue(_v.getKey().getSex());
        content.updateImg(ConverterCommonMapUtil.copyImageArrayBaseSixtyFour(_v.getValue()),compoFactory);
    }

    public GeneComponentModelElt<Sex> getSex() {
        return sex;
    }

    public GeneComponentModelElt<Direction> getDirection() {
        return direction;
    }

    public GeneComponentModelEltEnumSub<EnvironmentType> getEnvironment() {
        return environment;
    }

    public ContentGeneComponentModelImg getContent() {
        return content;
    }

}
