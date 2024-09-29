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
    public static void loadResources(DataBase _d, SexListInt _sexList, String _base) {
        _d.setFrontHeros(new ImageHeroKeys());
        StringMap<String> heFr_ = HeFront.im();
        for (EntryCust<String,String> e:heFr_.entryList()){
            StringList keyStrings_ = StringUtil.splitStrings(e.getKey(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = getEnvByName(keyStrings_.first());
            Sex sex_ = getSexByName(keyStrings_.last(),_sexList);
            _d.getFrontHeros().addEntry(new ImageHeroKey(env_, sex_),
                    ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE), _base));
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
                    ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE), _base));
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
                    ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE), _base));
        }
        _d.setImageTmHm(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(ImHmTm.im(),MessagesDataBaseConstants.BASE), _base));
        _d.setStorage(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(ImStorage.im(),MessagesDataBaseConstants.BASE), _base));
        _d.setEndGameImage(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(ImEndGame.im(),MessagesDataBaseConstants.BASE), _base));
        feedImgs(AnStatis.im(), _d.getAnimStatis(), _base);
        feedImgs(AnStatus.im(), _d.getAnimStatus(), _base);
        _d.setAnimAbsorb(ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(AnAbs.im().firstValue(),MessagesDataBaseConstants.BASE), _base));
        _d.setMaxiPkBack(new StringMap<ImageArrayBaseSixtyFour>());
        feedImgs(Bk.im(), _d.getMaxiPkBack(), _base);
        _d.setMaxiPkFront(new StringMap<ImageArrayBaseSixtyFour>());
        feedImgs(Ft.im(), _d.getMaxiPkFront(), _base);
        _d.setMiniPk(new StringMap<ImageArrayBaseSixtyFour>());
        feedImgs(Mn.im(), _d.getMiniPk(), _base);
        _d.setMiniItems(new StringMap<ImageArrayBaseSixtyFour>());
        feedImgs(ItIm.im(), _d.getMiniItems(), _base);
        _d.setTypesImages(new StringMap<ImageArrayBaseSixtyFour>());
        feedImgs(TypeImg.im(), _d.getTypesImages(), _base);
        _d.setTrainers(new StringMap<ImageArrayBaseSixtyFour>());
        _d.setPeople(new StringMap<ImageArrayBaseSixtyFour>());
        _d.setImages(new StringMap<ImageArrayBaseSixtyFour>());
        _d.setImagesTiles(new StringMap<ScreenCoordssInt>());
        _d.setLinks(new StringMap<ImageArrayBaseSixtyFour>());
        _d.setMiniMap(new StringMap<ImageArrayBaseSixtyFour>());
        feedImgs(TrainerImg.im(), _d.getTrainers(), _base);
        feedImgs(PeopleImg.im(), _d.getPeople(), _base);
        feedImgs(ImgMap.im(), _d.getImages(), _base);
        feedImgs(LinkImg.im(), _d.getLinks(), _base);
        feedImgs(MiniMapImg.im(), _d.getMiniMap(), _base);
        _d.boundsPk();
        _d.setupPseudoImages();
    }

    private static void feedImgs(StringMap<String> _imgs, StringMap<ImageArrayBaseSixtyFour> _dest, String _base) {
        for (EntryCust<String,String> e: _imgs.entryList()) {
            _dest.addEntry(e.getKey(), ImageArrayBaseSixtyFour.instance(BaseSixtyFourUtil.getImageByString(e.getValue(),MessagesDataBaseConstants.BASE),_base));
        }
    }
    public static EnvironmentType getEnvByName(String _env) {
        return EnvironmentType.getEnvByName(_env);
    }
    public static Sex getSexByName(String _env, SexListInt _sexList) {
        return Sex.getSexByName(_env,_sexList);
    }
}
