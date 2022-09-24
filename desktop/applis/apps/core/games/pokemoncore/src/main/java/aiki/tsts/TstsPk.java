package aiki.tsts;

import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.Fighter;
import aiki.game.fight.MoveTeamPosition;
import aiki.game.player.enums.Sex;
import aiki.map.enums.Direction;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.CommonParam;
import code.images.ImageCsv;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
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

    public static int nbBeatTrainersVa(CustList<BoolVal> _map, BoolVal _taken) {
        int n_ = IndexConstants.FIRST_INDEX;
        for (BoolVal e: _map) {
            if (e == _taken) {
                n_++;
            }
        }
        return n_;
    }

    public static StringList moves(StringMap<BoolVal> _map, BoolVal _learn) {
        StringList moves_;
        moves_ = new StringList();
        for (EntryCust<String,BoolVal> e: _map.entryList()) {
            if (e.getValue() == _learn) {
                moves_.add(e.getKey());
            }
        }
        return moves_;
    }
    public  static int getNbStatusRelatByRounds(Fighter _f, short _nbRounds) {
        int i_ = IndexConstants.SIZE_EMPTY;
        for (CommonParam<MoveTeamPosition, Short> e: _f.getStatusRelat().entryList()) {
            if (NumberUtil.eq(e.getValue(), _nbRounds)) {
                i_++;
            }
        }
        return i_;
    }
}
