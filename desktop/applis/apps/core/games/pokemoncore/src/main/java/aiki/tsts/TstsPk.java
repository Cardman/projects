package aiki.tsts;

import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.fight.util.TypesDuo;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import code.images.ImageCsv;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class TstsPk {
    private TstsPk() {
    }

    public static StringList csvImg(String _w, int _nb, String _value) {
        StringList nothing_ = new StringList(_w);
        for (int i = 0; i < _nb; i++) {
            nothing_.add(_value);
        }
        return nothing_;
    }

    public static void commonImage(CustList<String> _keys, String _img, StringMap<int[][]> _dest) {
        for (String p: _keys) {
            _dest.addEntry(p, ImageCsv.getImageByString(_img));
        }
    }

    public static void heroInit(DataBase _data) {
        StringList herosBottom_ = new StringList("-2","-2");
        int iHeros_ = 0;
        int iHerosBis_ = 0;
        for (EnvironmentType e: EnvironmentType.all()) {
            if (e == EnvironmentType.ROCK) {
                continue;
            }
            for (Direction d: Direction.all()) {
                for (Sex s: Sex.all()) {
                    ImageHeroKey key_;
                    key_ = new ImageHeroKey(e, d, s);
                    StringList herosTop_ = csvImg("2", 2, Long.toString(iHeros_));
                    herosTop_.addAllElts(herosBottom_);
                    _data.getOverWorldHeros().addEntry(key_, ImageCsv.getImageByString(StringUtil.join(herosTop_, ";")));
                    iHeros_++;
                }
            }
            for (Sex s: Sex.all()) {
                ImageHeroKey key_;
                key_ = new ImageHeroKey(e, s);
                StringList herosTop_ = new StringList("2");
                for (int i = 0; i < 2; i++) {
                    herosTop_.add(Long.toString(iHerosBis_));
                    iHerosBis_++;
                }
                herosTop_.addAllElts(herosBottom_);
                _data.getBackHeros().addEntry(key_, ImageCsv.getImageByString(StringUtil.join(herosTop_, ";")));
                _data.getFrontHeros().addEntry(key_, ImageCsv.getImageByString(StringUtil.join(herosTop_, ";")));
                iHerosBis_++;
            }
        }
    }

    public static void typesColorsInit(DataBase _data) {
        StringList moveTypes_ = new StringList();
        for (TypesDuo t : _data.getTableTypes().getKeys()) {

            moveTypes_.add(t.getDamageType());
        }
        moveTypes_.removeDuplicates();
        int p_ = 1;
        for (String p: moveTypes_) {
            String str_ = Long.toString(p_);
            _data.getTypesColors().addEntry(p, StringUtil.concat(str_,DataBase.SEPARATOR_RGB, str_,DataBase.SEPARATOR_RGB, str_));
            _data.getTypesImages().addEntry(p, ImageCsv.getImageByString(StringUtil.concat("2", DataBase.SEPARATOR_RGB, str_, DataBase.SEPARATOR_RGB, str_, DataBase.SEPARATOR_RGB, str_, DataBase.SEPARATOR_RGB, str_)));
            p_++;
        }
    }

    public static StringList csvImg(String _w, int _nb, StringList _list) {
        StringList voieThree_ = new StringList(_w);
        for (int i = 0; i < _nb; i++) {
            voieThree_.addAllElts(_list);
        }
        return voieThree_;
    }

}
