package aiki.beans.moves;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.dto.MoveLine;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectDamage;

public class MovesBean extends CommonBean {

    @Accessible
    private final String movesBean="web/html/moves/moveline.html";

    @Accessible
    private CustList<MoveLine> moves = new CustList<MoveLine>();

    @Accessible
    private StringList sortedMoves = new StringList();

    @Accessible
    private String category = DataBase.EMPTY_STRING;

    @Accessible
    private StringMap<String> categories = new StringMap<String>();

    @Accessible
    private String typedName = DataBase.EMPTY_STRING;

    @Accessible
    private String typedType = DataBase.EMPTY_STRING;

    @Accessible
    private boolean wholeWord;

    @Accessible
    private String minPower = DataBase.EMPTY_STRING;

    @Accessible
    private String maxPower = DataBase.EMPTY_STRING;

    @Accessible
    private String minAccuracy = DataBase.EMPTY_STRING;

    @Accessible
    private String maxAccuracy = DataBase.EMPTY_STRING;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        moves.clear();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        categories.putAllMap(translationsCategories_);
        categories.put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
        if (!getForms().contains(LEARNT)) {
            StringList moves_ = (StringList) getForms().getVal(MOVES_SET);
            for (String k: moves_) {
                MoveData moveData_ = data_.getMoves().getVal(k);
                MoveLine line_ = new MoveLine();
                line_.setName(k);
                line_.setDisplayName(translationsMoves_.getVal(k));
                StringList types_ = new StringList();
                for (String t: moveData_.getTypes()) {
                    types_.add(translationsTypes_.getVal(t));
                }
                line_.setTypes(types_);
                line_.setPp(moveData_.getPp());
                line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
                line_.setDamageMove(moveData_ instanceof DamagingMoveData);
                String power_ = DataBase.EMPTY_STRING;
                if (line_.isDamageMove()) {
                    DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                    line_.setDirect(damag_.isDirect());
                    EffectDamage eff_ = (EffectDamage) damag_.getEffet(damag_.indexOfPrimaryEffect());
                    power_ = eff_.getPower();
                }
                line_.setPriority(moveData_.getPriority());
                String accuracy_ = moveData_.getAccuracy();
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
                moves.add(line_);
            }
        } else {
            boolean selectedLearn_ = (Boolean) getForms().getVal(LEARNT);
            StringList learntMoves_ = (StringList) getForms().getVal(LEARNT_MOVES);
            for (String k: data_.getMoves().getKeys()) {
                if (learntMoves_.containsObj(k) && !selectedLearn_) {
                    continue;
                }
                if (!learntMoves_.containsObj(k) && selectedLearn_) {
                    continue;
                }
                MoveData moveData_ = data_.getMoves().getVal(k);
                MoveLine line_ = new MoveLine();
                line_.setName(k);
                line_.setDisplayName(translationsMoves_.getVal(k));
                StringList types_ = new StringList();
                for (String t: moveData_.getTypes()) {
                    types_.add(translationsTypes_.getVal(t));
                }
                line_.setTypes(types_);
                line_.setPp(moveData_.getPp());
                line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
                line_.setDamageMove(moveData_ instanceof DamagingMoveData);
                String power_ = DataBase.EMPTY_STRING;
                if (line_.isDamageMove()) {
                    DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                    line_.setDirect(damag_.isDirect());
                    EffectDamage eff_ = (EffectDamage) damag_.getEffet(damag_.indexOfPrimaryEffect());
                    power_ = eff_.getPower();
                }
                line_.setPriority(moveData_.getPriority());
                String accuracy_ = moveData_.getAccuracy();
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

    @Accessible
    private String search() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringList moves_;
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        moves_ = new StringList();
        for (String k: data_.getMoves().getKeys()) {
            String displayName_ = translationsMoves_.getVal(k);
            if (!StringList.match(displayName_, typedName)) {
                continue;
            }
            MoveData moveData_ = data_.getMoves().getVal(k);
            boolean atLeastMatchType_ = false;
            for (String t: moveData_.getTypes()) {
                String displayType_;
                displayType_ = translationsTypes_.getVal(t);
                if (wholeWord) {
                    if (typedType == null) {
                        continue;
                    }
                    if (!StringList.quickEq(displayType_, typedType)) {
                        continue;
                    }
                } else {
                    if (!StringList.match(displayType_, typedType)) {
                        continue;
                    }
                }
                atLeastMatchType_ = true;
            }
            if (!atLeastMatchType_) {
                continue;
            }
            if (!StringList.quickEq(category, DataBase.EMPTY_STRING)) {
                if (!StringList.quickEq(category, moveData_.getCategory())) {
                    continue;
                }
            }
            if (!StringList.quickEq(minAccuracy, DataBase.EMPTY_STRING)) {
                try {
                    Rate accurary_ = new Rate(minAccuracy);
                    try {
                        Rate accuraryLoc_ = new Rate(moveData_.getAccuracy());
                        if (!Rate.greaterEq(accuraryLoc_, accurary_)) {
                            continue;
                        }
                    } catch (RuntimeException _0) {
                        continue;
                    }
                } catch (RuntimeException _0) {
                }
            }
            if (!StringList.quickEq(maxAccuracy, DataBase.EMPTY_STRING)) {
                try {
                    Rate accurary_ = new Rate(maxAccuracy);
                    if (Rate.isValid(moveData_.getAccuracy())) {
                        Rate accuraryLoc_ = new Rate(moveData_.getAccuracy());
                        if (!Rate.lowerEq(accuraryLoc_, accurary_)) {
                            continue;
                        }
                    }
                } catch (RuntimeException _0) {
                }
            }
            if (!StringList.quickEq(minPower, DataBase.EMPTY_STRING)) {
                if (!(moveData_ instanceof DamagingMoveData)) {
                    continue;
                }
                DamagingMoveData damage_ = (DamagingMoveData) moveData_;
                EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
                try {
                    Rate power_ = new Rate(minPower);
                    if (!power_.isZeroOrLt()) {
                        try {
                            Rate powerLoc_ = new Rate(eff_.getPower());
                            if (!Rate.greaterEq(powerLoc_, power_)) {
                                continue;
                            }
                        } catch (RuntimeException _0) {
                            continue;
                        }
                    }
                } catch (RuntimeException _0) {
                }
            }
            if (!StringList.quickEq(maxPower, DataBase.EMPTY_STRING)) {
                if (moveData_ instanceof DamagingMoveData) {
                    DamagingMoveData damage_ = (DamagingMoveData) moveData_;
                    EffectDamage eff_ = (EffectDamage) damage_.getEffet(damage_.indexOfPrimaryEffect());
                    try {
                        Rate power_ = new Rate(maxPower);
                        if (Rate.isValid(eff_.getPower())) {
                            Rate powerLoc_ = new Rate(eff_.getPower());
                            if (!Rate.lowerEq(powerLoc_, power_)) {
                                continue;
                            }
                        }
                    } catch (RuntimeException _0) {
                    }
                }
            }
            moves_.add(k);
        }
        moves_.sortElts(new ComparatorTrStrings(translationsMoves_));
        getForms().put(MOVES_SET, moves_);
        if (moves_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            getForms().put(MOVE, moves_.first());
            return MOVE;
        }
        return MOVES;
    }

    @Accessible
    private String clickLink(Long _number) {
        getForms().put(MOVE,moves.get(_number.intValue()).getName());
        return MOVE;
    }
}
