package aiki.sml;

import aiki.db.*;
import aiki.facade.*;
import code.images.*;
import code.util.*;
import code.util.core.*;
import aiki.sml.imgs.*;
import aiki.map.levels.enums.*;
import aiki.map.enums.*;
import aiki.game.player.enums.*;
import aiki.util.*;

public final class LoadResImg {

    private static final String SEPARATOR_KEY_HEROS = ";";

    private LoadResImg(){}
    public static void loadResources(DataBase _d, SexListInt _sexList) {
        _d.setFrontHeros(new ImageHeroKeys());
        StringMap<String> heFr_ = HeFront.im();
        for (EntryCust<String,String> e:heFr_.entryList()){
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_.first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getFrontHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE));
        }
        _d.setBackHeros(new ImageHeroKeys());
        StringMap<String> heBk_ = HeBack.im();
        for (EntryCust<String,String> e:heBk_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getBackHeros().addEntry(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE));
        }
        _d.setOverWorldHeros(new ImageHeroKeys());
        StringMap<String> heMi_ = HeMini.im();
        for (EntryCust<String,String> e:heMi_.entryList()) {
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(IndexConstants.SECOND_INDEX));
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getOverWorldHeros().addEntry(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE));
        }
        _d.setImageTmHm(BaseSixtyFourUtil.getImageByString(ImHmTm.im(),MessagesDataBaseConstants.BASE));
        _d.setStorage(BaseSixtyFourUtil.getImageByString(ImStorage.im(),MessagesDataBaseConstants.BASE));
        _d.setEndGameImage(BaseSixtyFourUtil.getImageByString(ImEndGame.im(),MessagesDataBaseConstants.BASE));
        feedImgs(AnStatis.im(), _d.getAnimStatis());
        feedImgs(AnStatus.im(), _d.getAnimStatus());
        _d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(AnAbs.im().firstValue(),MessagesDataBaseConstants.BASE));
        _d.setMaxiPkBack(new StringMap<int[][]>());
        feedImgs(Bk.im(), _d.getMaxiPkBack());
        _d.setMaxiPkFront(new StringMap<int[][]>());
        feedImgs(Ft.im(), _d.getMaxiPkFront());
        _d.setMiniPk(new StringMap<int[][]>());
        feedImgs(Mn.im(), _d.getMiniPk());
        _d.setMiniItems(new StringMap<int[][]>());
        feedImgs(ItIm.im(), _d.getMiniItems());
        _d.setTypesImages(new StringMap<int[][]>());
        feedImgs(TypeImg.im(), _d.getTypesImages());
        _d.setTrainers(new StringMap<int[][]>());
        _d.setPeople(new StringMap<int[][]>());
        _d.setImages(new StringMap<int[][]>());
        _d.setImagesTiles(new StringMap<ScreenCoordssInt>());
        _d.setLinks(new StringMap<int[][]>());
        _d.setMiniMap(new StringMap<int[][]>());
        feedImgs(TrainerImg.im(), _d.getTrainers());
        feedImgs(PeopleImg.im(), _d.getPeople());
        feedImgs(ImgMap.im(), _d.getImages());
        feedImgs(LinkImg.im(), _d.getLinks());
        feedImgs(MiniMapImg.im(), _d.getMiniMap());
        _d.boundsPk();
        _d.setupPseudoImages();
    }

    private static void feedImgs(StringMap<String> _imgs, StringMap<int[][]> _dest) {
        for (EntryCust<String,String> e: _imgs.entryList()) {
            _dest.addEntry(e.getKey(), BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE));
        }
    }
    public static EnvironmentType getEnvByName(String _env) {
        return EnvironmentType.getEnvByName(_env);
    }
    public static Sex getSexByName(String _env, SexListInt _sexList) {
        return Sex.getSexByName(_env,_sexList);
    }
}
