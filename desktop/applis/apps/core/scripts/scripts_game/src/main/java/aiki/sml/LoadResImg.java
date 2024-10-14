package aiki.sml;

import aiki.db.*;
import aiki.facade.*;
import aiki.sml.init.*;
import code.util.*;
import code.util.core.*;
import aiki.sml.imgs.*;
import aiki.map.levels.enums.*;
import aiki.map.enums.*;
import aiki.game.player.enums.*;
import aiki.util.*;

public final class LoadResImg {

    private LoadResImg(){}
    public static void loadResources(DataBase _d, SexListInt _sexList, String _base) {
        _d.setFrontHeros(new ImageHeroKeys());
        StringMap<ImageArrayBaseSixtyFour> heFr_ = HeFront.im(_base);
        for (EntryCust<String,ImageArrayBaseSixtyFour> e:heFr_.entryList()){
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    CstIgame.SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_.first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getFrontHeros().addEntry(new ImageHeroKey(env_, sex_),
                    e.getValue());
        }
        _d.setBackHeros(new ImageHeroKeys());
        StringMap<ImageArrayBaseSixtyFour> heBk_ = HeBack.im(_base);
        for (EntryCust<String,ImageArrayBaseSixtyFour> e:heBk_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    CstIgame.SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getBackHeros().addEntry(new ImageHeroKey(env_, sex_),
                    e.getValue());
        }
        _d.setOverWorldHeros(new ImageHeroKeys());
        StringMap<ImageArrayBaseSixtyFour> heMi_ = HeMini.im(_base);
        for (EntryCust<String,ImageArrayBaseSixtyFour> e:heMi_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    CstIgame.SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(IndexConstants.SECOND_INDEX));
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getOverWorldHeros().addEntry(new ImageHeroKey(env_, dir_, sex_),
                    e.getValue());
        }
        _d.setImageTmHm(ImageArrayBaseSixtyFour.instance(ImHmTm.im(),_base));
        _d.setStorage(ImageArrayBaseSixtyFour.instance(ImStorage.im(),_base));
        _d.setEndGameImage(ImageArrayBaseSixtyFour.instance(ImEndGame.im(),_base));
        _d.setAnimStatis(AnStatis.im(_base));
        _d.setAnimStatus(AnStatus.im(_base));
        _d.setAnimAbsorb(AnAbs.im(_base));
        _d.setMaxiPkBack(Bk.im(_base));
        _d.setMaxiPkFront(Ft.im(_base));
        _d.setMiniPk(Mn.im(_base));
        _d.setMiniItems(ItIm.im(_base));
        _d.setTypesImages(TypeImg.im(_base));
        _d.setTrainers(TrainerImg.im(_base));
        _d.setPeople(PeopleImg.im(_base));
        _d.setImages(ImgMap.im(_base));
        _d.setImagesTiles(new StringMap<ScreenCoordssInt>());
        _d.setLinks(LinkImg.im(_base));
        _d.setMiniMap(MiniMapImg.im(_base));
        _d.boundsPk();
        _d.setupPseudoImages();
    }
    public static EnvironmentType getEnvByName(String _env) {
        return EnvironmentType.getEnvByName(_env);
    }
    public static Sex getSexByName(String _env, SexListInt _sexList) {
        return Sex.getSexByName(_env,_sexList);
    }
}
