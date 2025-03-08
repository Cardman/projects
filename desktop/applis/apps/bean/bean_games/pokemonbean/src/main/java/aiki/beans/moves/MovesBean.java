package aiki.beans.moves;

import aiki.beans.*;
import aiki.beans.facade.dto.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.moves.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class MovesBean extends WithFilterBean {
//    static final String MOVES_BEAN=AikiBeansMovesStd.WEB_HTML_MOVES_MOVE_LINE_HTML;
    private IntBeanChgSubmit updateValues;
    private final CustList<MoveLine> moves = new CustList<MoveLine>();
    private final CustList<TranslatedKey> sortedMoves = new CustList<TranslatedKey>();

    public MovesBean() {
        setAppName(MessagesPkBean.APP_BEAN_DATA);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataMovesMoves.M_P_71_TITLE));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_INDEX);
//        initPage();
        initFormMv(false);
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_OK));
        getUpdateValues().addEvt(new MovesBeanSearch(this));
        feedParents();
        new BeanDisplayListGrid<MoveLine>(new BeanDisplayMoveLine()).displayGrid(this,getMoves(),MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_MOVES,MessagesDataMovesMoves.M_P_71_NAME_H,MessagesDataMovesMoves.M_P_71_PP_H,MessagesDataMovesMoves.M_P_71_TYPES_H,MessagesDataMovesMoves.M_P_71_CAT_H,MessagesDataMovesMoves.M_P_71_DAMAG_H,MessagesDataMovesMoves.M_P_71_DIREC_H,MessagesDataMovesMoves.M_P_71_PRIO_H,MessagesDataMovesMoves.M_P_71_ACCURACY,MessagesDataMovesMoves.M_P_71_CONST_POWER);
//        for (MoveLine p: getMoves()) {
//            formatMessageDirCts(p.getTranslatedKey());
//            formatMessageDirCts(Long.toString(p.getPp()));
//            formatMessageDirCts(StringUtil.join(p.getTypes()," - "));
//            formatMessageDirCts(p.getCategory());
//            if (p.isDamageMove()) {
//                formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_DAMAGING);
//            } else {
//                formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_STATUS);
//            }
//            if (!p.isDamageMove()) {
//                formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_STATUS_INDIRECT);
//            } else if (p.isDirect()) {
//                formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_DAMAGING_DIRECT);
//            } else {
//                formatMessageCts(MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_DAMAGING_INDIRECT);
//            }
//            formatMessageDirCts(Long.toString(p.getPriority()));
//            formatMessageDirCts(p.getAccuracy());
//            formatMessageDirCts(p.getPower());
//        }
//        feedParents();
//        feedParents();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML),MessagesPkBean.MOVES,MessagesDataMovesMoves.M_P_71_INDEX);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.MOVES).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        bools();
        DataBase data_ = getDataBase();
        moves.clear();
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        DictionaryComparator<String,String> sort_ = DictionaryComparatorUtil.buildCatsData(data_,getLanguage());
        StringMap<String> translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        sort_.putAllMap(translationsCategories_);
        getCategories().putAllMap(sort_);
        getCategories().put(DataBase.EMPTY_STRING, DataBase.EMPTY_STRING);
        AbsMap<TranslatedKey, MoveData> moves_ = getForms().getValMoveData(CST_MOVES_SET);
        for (EntryCust<TranslatedKey, MoveData> k: moves_.entryList()) {
            MoveData moveData_ = k.getValue();
            MoveLine line_ = buildLine(translationsTypes_, translationsCategories_, k.getKey(), moveData_,getDataBase());
            moves.add(line_);
        }
//        if (!getForms().contains(CST_LEARNT)) {
//            AbsMap<String,MoveData> moves_ = getForms().getValMoveData(CST_MOVES_SET);
//            for (EntryCust<String, MoveData> k: moves_.entryList()) {
//                MoveData moveData_ = k.getValue();
//                MoveLine line_ = buildLine(translationsMoves_, translationsTypes_, translationsCategories_, k.getKey(), moveData_);
//                moves.add(line_);
//            }
//        } else {
//            boolean selectedLearn_ = getForms().getValBool(CST_LEARNT);
//            AbsMap<String,MoveData> learntMoves_ = getForms().getValMoveData(CST_LEARNT_MOVES);
//            CustList<String> list_ = learntMoves_.getKeys();
//            for (EntryCust<String, MoveData> k: data_.getMoves().entryList()) {
//                if (StringUtil.contains(list_, k.getKey()) && !selectedLearn_ || !StringUtil.contains(list_, k.getKey()) && selectedLearn_) {
//                    continue;
//                }
//                MoveLine line_ = buildLine(translationsMoves_, translationsTypes_, translationsCategories_, k.getKey(), k.getValue());
//                moves.add(line_);
//            }
//        }
        sortedMoves.clear();
        for (MoveLine l: moves) {
            sortedMoves.add(l.getTranslatedKey());
        }
//        escapeInputs();

    }

    public static SelectLineMove buildLine(StringMap<String> _translationsTypes, StringMap<String> _translationsCategories, TranslatedKey _k, MoveData _moveData, DataBase _dataBase) {
        SelectLineMove line_ = new SelectLineMove();
        line(_translationsTypes, _translationsCategories, _k, _moveData, line_, _dataBase);
        return line_;
    }

    public static void line(StringMap<String> _translationsTypes, StringMap<String> _translationsCategories, TranslatedKey _k, MoveData _moveData, MoveLine _line, DataBase _dataBase) {
        _line.setTranslatedKey(_k);
        _line.setDisplayName(_k.getTranslation());
        StringList types_ = new StringList();
        for (String t: _moveData.getTypes()) {
            types_.add(_translationsTypes.getVal(t));
        }
        _line.setTypes(types_);
        _line.setPp(_moveData.getPp());
        _line.setCategory(_translationsCategories.getVal(_dataBase.getCategory(_moveData)));
        _line.setDamageMove(_moveData instanceof DamagingMoveData);
        _line.setDirect(direct(_moveData));
        _line.setPriority(_moveData.getPriority());
        String accuracy_ = _moveData.getAccuracy();
        _line.setAccuracy(validOrEmpty(accuracy_));
        String power_ = power(_moveData);
        _line.setPower(validOrEmpty(power_));
    }

    public String search() {
        DataBase data_ = getDataBase();
//        StringMap<String> translationsMoves_;
//        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        AbsMap<TranslatedKey, MoveData> moves_;
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
        getForms().putMoves(CST_MOVES_SET, moves_);
        if (moves_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return tryRedirect(moves_.firstKey());
        }
        return PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML;
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
        return tryRedirect(moves.get(_number).getTranslatedKey());
    }

    public CustList<MoveLine> getMoves() {
        return moves;
    }

}