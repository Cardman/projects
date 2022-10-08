package aiki.beans.moves;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.MoveLine;
import aiki.beans.pokemon.PokedexBean;
import aiki.comparators.DictionaryComparatorUtil;
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
    private String category = DataBase.EMPTY_STRING;
    private final StringMap<String> categories = new StringMap<String>();
    private String typedName = DataBase.EMPTY_STRING;
    private String typedType = DataBase.EMPTY_STRING;
    private boolean wholeWord;
    private String minPower = DataBase.EMPTY_STRING;
    private String maxPower = DataBase.EMPTY_STRING;
    private String minAccuracy = DataBase.EMPTY_STRING;
    private String maxAccuracy = DataBase.EMPTY_STRING;

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
        typedName = escapedStringQuote(typedName);
        typedType = escapedStringQuote(typedType);
        minPower = escapedStringQuote(minPower);
        maxPower = escapedStringQuote(maxPower);
        minAccuracy = escapedStringQuote(minAccuracy);
        maxAccuracy = escapedStringQuote(maxAccuracy);
    }

    private MoveLine buildLine(StringMap<String> _translationsMoves, StringMap<String> _translationsTypes, StringMap<String> _translationsCategories, String _k, MoveData _moveData) {
        MoveLine line_ = new MoveLine();
        line_.setName(_k);
        line_.setDisplayName(_translationsMoves.getVal(_k));
        StringList types_ = new StringList();
        for (String t: _moveData.getTypes()) {
            types_.add(_translationsTypes.getVal(t));
        }
        line_.setTypes(types_);
        line_.setPp(_moveData.getPp());
        line_.setCategory(_translationsCategories.getVal(_moveData.getCategory()));
        String power_ = DataBase.EMPTY_STRING;
        if (_moveData instanceof DamagingMoveData) {
            DamagingMoveData damag_ = (DamagingMoveData) _moveData;
            line_.setDirect(damag_.isDirect());
            EffectDamage eff_ = (EffectDamage) damag_.getEffet(damag_.indexOfPrimaryEffect());
            power_ = eff_.getPower();
            line_.setDamageMove(true);
        } else {
            line_.setDamageMove(false);
        }
        line_.setPriority(_moveData.getPriority());
        String accuracy_ = _moveData.getAccuracy();
        if (Rate.isValid(accuracy_)) {
            line_.setAccuracy(accuracy_);
        } else {
            line_.setAccuracy(DataBase.EMPTY_STRING);
        }
        if (Rate.isValid(power_)) {
            line_.setPower(power_);
        } else {
            line_.setPower(DataBase.EMPTY_STRING);
        }
        return line_;
    }

    public String search() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = new StringList();
        for (EntryCust<String, MoveData> k: data_.getMoves().entryList()) {
            String displayName_ = translationsMoves_.getVal(k.getKey());
            if (!StringUtil.match(displayName_, typedName)) {
                continue;
            }
            MoveData moveData_ = k.getValue();
            if (PokedexBean.atLeastMatchType(translationsTypes_, wholeWord, typedType, moveData_.getTypes()) && (StringUtil.quickEq(category, DataBase.EMPTY_STRING) || StringUtil.quickEq(category, moveData_.getCategory())) && !excludeByAccuracy(moveData_) && !excludeByPower(moveData_)) {
                moves_.add(k.getKey());
            }
        }
        moves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        getForms().put(CST_MOVES_SET, moves_);
        if (moves_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(CST_MOVE, moves_.first());
            return CST_MOVE;
        }
        return CST_MOVES;
    }
    private boolean excludeByAccuracy(MoveData _move) {
        if (Rate.isValid(minAccuracy)) {
            String accuraryStr_ = _move.getAccuracy();
            if (!Rate.isValid(accuraryStr_) || !Rate.greaterEq(new Rate(accuraryStr_), new Rate(minAccuracy))) {
                return true;
            }
        }
        if (Rate.isValid(maxAccuracy)) {
            String accuraryStr_ = _move.getAccuracy();
            return Rate.isValid(accuraryStr_) && !Rate.lowerEq(new Rate(accuraryStr_), new Rate(maxAccuracy));
        }
        return false;
    }
    private boolean excludeByPower(MoveData _move) {
        if (Rate.isValid(minPower)) {
            if (!(_move instanceof DamagingMoveData)) {
                return true;
            }
            DamagingMoveData damage_ = (DamagingMoveData) _move;
            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
            Rate power_ = new Rate(minPower);
            if (!power_.isZeroOrLt() && (!Rate.isValid(eff_.getPower()) || !Rate.greaterEq(new Rate(eff_.getPower()), power_))) {
                return true;
            }
        }
        if (Rate.isValid(maxPower) && _move instanceof DamagingMoveData) {
            DamagingMoveData damage_ = (DamagingMoveData) _move;
            EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
            Rate power_ = new Rate(maxPower);
            return Rate.isValid(eff_.getPower()) && !Rate.lowerEq(new Rate(eff_.getPower()), power_);
        }
        return false;
    }
    public String clickLink(int _number) {
        getForms().put(CST_MOVE,moves.get(_number).getName());
        return CST_MOVE;
    }

    public void setTypedName(String _typedName) {
        typedName = _typedName;
    }

    public String getTypedName() {
        return typedName;
    }

    public StringMap<String> getCategories() {
        return categories;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String _category) {
        category = _category;
    }

    public void setTypedType(String _typedType) {
        typedType = _typedType;
    }

    public String getTypedType() {
        return typedType;
    }

    public void setWholeWord(boolean _wholeWord) {
        wholeWord = _wholeWord;
    }

    public boolean getWholeWord() {
        return wholeWord;
    }

    public void setMinAccuracy(String _minAccuracy) {
        minAccuracy = _minAccuracy;
    }

    public String getMinAccuracy() {
        return minAccuracy;
    }

    public void setMaxAccuracy(String _maxAccuracy) {
        maxAccuracy = _maxAccuracy;
    }

    public String getMaxAccuracy() {
        return maxAccuracy;
    }

    public void setMinPower(String _minPower) {
        minPower = _minPower;
    }

    public String getMinPower() {
        return minPower;
    }

    public void setMaxPower(String _maxPower) {
        maxPower = _maxPower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public CustList<MoveLine> getMoves() {
        return moves;
    }

    public StringList getSortedMoves() {
        return sortedMoves;
    }
}