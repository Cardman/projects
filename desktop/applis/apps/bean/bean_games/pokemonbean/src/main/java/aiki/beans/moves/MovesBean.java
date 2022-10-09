package aiki.beans.moves;

import aiki.beans.CommonBean;
import aiki.beans.facade.dto.MoveLine;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectDamage;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public class MovesBean extends CommonBean {
    static final String MOVES_BEAN="web/html/moves/moveline.html";
    private final CustList<MoveLine> moves = new CustList<MoveLine>();
    private final StringList sortedMoves = new StringList();
    private final StringMap<String> categories = new StringMap<String>();

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        moves.clear();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        categories.putAllMap(translationsCategories_);
        categories.put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
        if (!getForms().contains(CST_LEARNT)) {
            StringList moves_ = getForms().getValList(CST_MOVES_SET);
            for (String k: moves_) {
                MoveData moveData_ = data_.getMoves().getVal(k);
                MoveLine line_ = buildLine(translationsMoves_, translationsTypes_, translationsCategories_, k, moveData_);
                moves.add(line_);
            }
        } else {
            boolean selectedLearn_ = getForms().getValBool(CST_LEARNT);
            StringList learntMoves_ = getForms().getValList(CST_LEARNT_MOVES);
            for (EntryCust<String, MoveData> k: data_.getMoves().entryList()) {
                if (StringUtil.contains(learntMoves_, k.getKey()) && !selectedLearn_ || !StringUtil.contains(learntMoves_, k.getKey()) && selectedLearn_) {
                    continue;
                }
                MoveLine line_ = buildLine(translationsMoves_, translationsTypes_, translationsCategories_, k.getKey(), k.getValue());
                moves.add(line_);
            }
        }
        sortedMoves.clear();
        for (MoveLine l: moves) {
            sortedMoves.add(l.getName());
        }
        escapeInputs();

    }

    public static SelectLineMove buildLine(StringMap<String> _translationsMoves, StringMap<String> _translationsTypes, StringMap<String> _translationsCategories, String _k, MoveData _moveData) {
        SelectLineMove line_ = new SelectLineMove();
        line(_translationsMoves, _translationsTypes, _translationsCategories, _k, _moveData, line_);
        line_.setSelected(false);
        return line_;
    }

    public static void line(StringMap<String> _translationsMoves, StringMap<String> _translationsTypes, StringMap<String> _translationsCategories, String _k, MoveData _moveData, MoveLine _line) {
        _line.setName(_k);
        _line.setDisplayName(_translationsMoves.getVal(_k));
        StringList types_ = new StringList();
        for (String t: _moveData.getTypes()) {
            types_.add(_translationsTypes.getVal(t));
        }
        _line.setTypes(types_);
        _line.setPp(_moveData.getPp());
        _line.setCategory(_translationsCategories.getVal(_moveData.getCategory()));
        String power_ = DataBase.EMPTY_STRING;
        if (_moveData instanceof DamagingMoveData) {
            DamagingMoveData damag_ = (DamagingMoveData) _moveData;
            _line.setDirect(damag_.isDirect());
            EffectDamage eff_ = (EffectDamage) damag_.getEffet(damag_.indexOfPrimaryEffect());
            power_ = eff_.getPower();
            _line.setDamageMove(true);
        } else {
            _line.setDamageMove(false);
        }
        _line.setPriority(_moveData.getPriority());
        String accuracy_ = _moveData.getAccuracy();
        if (Rate.isValid(accuracy_)) {
            _line.setAccuracy(accuracy_);
        } else {
            _line.setAccuracy(DataBase.EMPTY_STRING);
        }
        if (Rate.isValid(power_)) {
            _line.setPower(power_);
        } else {
            _line.setPower(DataBase.EMPTY_STRING);
        }
    }

    public String search() {
        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
//        StringMap<String> translationsTypes_;
//        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = movesAmong(data_.getMoves());
//        for (EntryCust<String, MoveData> k: data_.getMoves().entryList()) {
//            String displayName_ = translationsMoves_.getVal(k.getKey());
//            if (!StringUtil.match(displayName_, getTypedName())) {
//                continue;
//            }
//            MoveData moveData_ = k.getValue();
//            if (atLeastMatchType(translationsTypes_, moveData_.getTypes()) && (StringUtil.quickEq(getTypedCategory(), DataBase.EMPTY_STRING) || StringUtil.quickEq(getTypedCategory(), moveData_.getCategory())) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
//                moves_.add(k.getKey());
//            }
//        }
//        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        getForms().put(CST_MOVES_SET, moves_);
        if (moves_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_MOVE, moves_.first());
            return CST_MOVE;
        }
        return CST_MOVES;
    }
//    private boolean excludeByAccuracy(MoveData _move) {
//        if (Rate.isValid(getMinAccuracy())) {
//            String accuraryStr_ = _move.getAccuracy();
//            if (!Rate.isValid(accuraryStr_) || !Rate.greaterEq(new Rate(accuraryStr_), new Rate(getMinAccuracy()))) {
//                return true;
//            }
//        }
//        if (Rate.isValid(getMaxAccuracy())) {
//            String accuraryStr_ = _move.getAccuracy();
//            return Rate.isValid(accuraryStr_) && !Rate.lowerEq(new Rate(accuraryStr_), new Rate(getMaxAccuracy()));
//        }
//        return false;
//    }
//    private boolean excludeByPower(MoveData _move) {
//        if (Rate.isValid(getMinPower())) {
//            if (!(_move instanceof DamagingMoveData)) {
//                return true;
//            }
//            DamagingMoveData damage_ = (DamagingMoveData) _move;
//            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
//            Rate power_ = new Rate(getMinPower());
//            if (!power_.isZeroOrLt() && (!Rate.isValid(eff_.getPower()) || !Rate.greaterEq(new Rate(eff_.getPower()), power_))) {
//                return true;
//            }
//        }
//        if (Rate.isValid(getMaxPower()) && _move instanceof DamagingMoveData) {
//            DamagingMoveData damage_ = (DamagingMoveData) _move;
//            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
//            Rate power_ = new Rate(getMaxPower());
//            return Rate.isValid(eff_.getPower()) && !Rate.lowerEq(new Rate(eff_.getPower()), power_);
//        }
//        return false;
//    }
    public String clickLink(int _number) {
        getForms().put(CST_MOVE,moves.get(_number).getName());
        return CST_MOVE;
    }

    public StringMap<String> getCategories() {
        return categories;
    }

    public CustList<MoveLine> getMoves() {
        return moves;
    }

    public StringList getSortedMoves() {
        return sortedMoves;
    }
}