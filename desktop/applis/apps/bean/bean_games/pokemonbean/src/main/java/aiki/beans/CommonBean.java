package aiki.beans;

import aiki.beans.effects.EffectWhileSendingBean;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.game.ImgPkPlayer;
import aiki.beans.map.elements.TranslatedPkElements;
import aiki.beans.moves.effects.*;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.fight.ActivityOfMove;
import aiki.map.levels.Level;
import aiki.map.levels.enums.*;
import aiki.map.places.Place;
import aiki.map.pokemon.enums.*;
import aiki.map.util.MiniMapCoordsTileInts;
import aiki.util.Coords;
import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.*;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.*;
import code.util.ints.*;

public abstract class CommonBean extends Bean implements WithFacade,WithForms {
    public static final int FALSE_VALUE = 0;
    public static final int TRUE_VALUE = 1;
    public static final String GET_IMAGE = "getImage";
    public static final String CLICK_NAME = "clickName";
    public static final String GET_NAME = "getName";
    public static final String GET_GENDER = "getGender";
    public static final String CLICK_ABILITY = "clickAbility";
    public static final String GET_ABILITY = "getAbility";
    public static final String CLICK_ITEM = "clickItem";
    public static final String GET_ITEM = "getItem";
    public static final String CLICK_MOVE = "clickMove";
    public static final String GET_MOVE = "getMove";
//    protected static final String CST_ABILITIES = "abilities";
    protected static final String CST_ABILITIES_SET = "abilities_set";
    protected static final String CST_ABILITY = "ability";
    protected static final String CST_ALLY = "ally";
    protected static final String CST_AREA = "area";
    protected static final String CST_BALL = "ball";
    protected static final String CST_BERRY = "berry";
    protected static final String CST_BOOST = "boost";
    protected static final String CST_COMBO = "combo";
    protected static final String CST_NAMES = "names";
    protected static final String CST_SIMULATION = "simulation";
    protected static final String CST_SIMULATION_STATE = "simulation_state";
//    protected static final String CST_CURRENT_TILE = "current_tile";
//    protected static final String CST_DEALER = "dealer";
    protected static final String CST_DUAL = "dual";
//    protected static final String CST_EVO_ITEM = "evo_item";
//    protected static final String CST_EVO_STONE = "evo_stone";
    protected static final String CST_EVOLVINGITEM = "evolvingitem";
    protected static final String CST_EVOLVINGSTONE = "evolvingstone";
    protected static final String CST_FOSSIL = "fossil";
//    protected static final String CST_FROM_LIST = "from_list";
    protected static final String CST_HEALINGHP = "healinghp";
    protected static final String CST_HEALINGHPSTATUS = "healinghpstatus";
    protected static final String CST_HEALINGITEM = "healingitem";
    protected static final String CST_HEALINGPP = "healingpp";
    protected static final String CST_HEALINGSTATUS = "healingstatus";
//    protected static final String CST_INSIDE = "inside";
    protected static final String CST_ITEM = "item";
    protected static final String CST_ITEMFORBATTLE = "itemforbattle";
    protected static final String CST_ITEMS = "items";
    protected static final String CST_ITEMS_SET = "items_set";
    protected static final String CST_LEARNT = "learnt";
    protected static final String CST_LEARNT_MOVES = "learnt_moves";
    protected static final String CST_LEG_PK = "leg_pk";
//    protected static final String CST_LEVEL = "level";
//    protected static final String CST_LEVEL_MAP = "level_map";
//    protected static final String CST_LEVEL_MAP_INDEX = "level_map_index";
    protected static final String CST_MOVE = "move";
    protected static final String CST_MOVES = "moves";
    protected static final String CST_MOVES_SET = "moves_set";
    protected static final String CST_MOVES_EDIT_SET = "moves_edit_set";
//    protected static final String CST_OTHER_ITEM = "other_item";
//    protected static final String CST_OTHER_WEATHER = "other_weather";
    protected static final String CST_PK = "pk";
//    protected static final String CST_PLACE_MAP_INDEX = "place_map_index";
//    protected static final String CST_POKEMON = "pokemon";
    protected static final String CST_POKEMON_SET = "pokemon_set";
//    protected static final String CST_PROPONE_LINK = "propone_link";
    protected static final String CST_COORDS = "coords";
    protected static final String CST_NO_FIGHT = "no_fight";
    protected static final String CST_POKEMON_SET_SIMU = "pokemon_set_simu";
    protected static final String CST_POKEMON_ADDED = "pokemon_added";
    protected static final String CST_POKEMON_EDIT = "pokemon_edit";
    protected static final String CST_POKEMON_INDEX_EDIT = "pokemon_index_edit";
    protected static final String CST_POKEMON_NAME_EDIT = "pokemon_name_edit";
    protected static final String CST_POKEMON_LEVEL_EDIT = "pokemon_level_edit";
    protected static final String CST_POKEMON_EXPERIENCE = "pokemon_experience";
    protected static final String CST_POKEMON_HAPPINESS = "pokemon_happiness";
    protected static final String CST_POKEMON_HP = "pokemon_hp";
    protected static final String CST_POKEMON_EV_VAR = "pokemon_ev_";
    protected static final String CST_HEAL_EDIT_PK = "heal_edit_pk";
    protected static final String CST_CATCHING_BALL = "catching_ball";
    protected static final String CST_ITEM_EDIT = "item_edit";
    protected static final String CST_POKEMON_MOVES_EDIT = "pokemon_moves_edit";
    protected static final String CST_POKEMON_ABILITY_EDIT = "pokemon_ability_edit";
    protected static final String CST_POKEMON_GENDER_EDIT = "pokemon_gender_edit";
    protected static final String CST_POKEMON_FOE = "pokemon_foe";
    protected static final String CST_ADDING_TRAINER_PK = "adding_pk";
//    protected static final String CST_POKEMON_ITEM_EDIT = "pokemon_item_edit";
    protected static final String CST_ITEMS_SET_EDIT = "items_set_edit";
//    protected static final String CST_VALIDATE_TRAINER_PK = "validate_trainer_pk";
//    protected static final String CST_ADD_POKEMON_PLAYER = "add_pokemon_player";
    protected static final String CST_EDIT_POKEMON_PLAYER = "edit_pokemon_player";
    protected static final String CST_IS_POKEMON_PLAYER_MOVES = "is_pokemon_player_moves";
    protected static final String CST_PK_NAME = "pk_name";
    /**exception naming*/
//    protected static final String CST_PROPONE_LINK_VAR = "propone_link_";
//    protected static final String CST_PROPONE_TILE = "propone_tile";
    protected static final String CST_REPEL = "repel";
//    protected static final String CST_SEE_AREA = "see_area";
//    protected static final String CST_SELLER = "seller";
    protected static final String CST_SELLINGITEM = "sellingitem";
    protected static final String CST_STATUS = "status";
    protected static final String CST_STATUS_SET = "status_set";
//    protected static final String CST_TRAINER = "trainer";
    protected static final String CST_PERSON = "person";
    protected static final String CST_TRAINER_MULTI_FIGHT = "trainer_multi_fight";
//    protected static final String CST_TRAINER_ONE_FIGHT = "trainer_one_fight";
    protected static final String CST_SPACE = " ";
    protected static final String CST_SEP_DASH = " - ";
    protected static final String CST_CENT = "100";
//    protected static final char CST_UNDERSCORE = '_';
    protected static final String CST_LEFT_BRACE = "{";
    protected static final String CST_RIGHT_BRACE = "}";
    protected static final String CST_QUOTED_LEFT_BRACE = "'{";
    protected static final String CST_QUOTED_RIGHT_BRACE = "}'";
    protected static final String CST_QUOTE = "'";
    protected static final String CST_ESCAPED_QUOTE = "''";
    protected static final char CST_LEFT_PAR = '(';
    protected static final char CST_RIGHT_PAR = ')';
    protected static final char CST_PIPE_CHAR = '|';

    private StringMapObjectBase baseForms;

    private FacadeGame dataBase;
    private IntBeanBuilderHelper builder;
    private String appName = "";
//    private String baseEncode;

    protected void fwd(CommonBean _b) {
        _b.setAppName(getAppName());
        _b.setDataBase(db());
        _b.setForms(new StringMapObject());
        _b.getForms().putAllMap(getForms());
        _b.setLanguage(getLanguage());
        _b.setBuilder(getBuilder());
    }

    public static int toInt(BoolVal _b) {
        if (_b == BoolVal.TRUE) {
            return TRUE_VALUE;
        }
        return FALSE_VALUE;
    }
    public static int toInt(boolean _b) {
        if (_b) {
            return TRUE_VALUE;
        }
        return FALSE_VALUE;
    }
    public static boolean toBool(int _i) {
        return _i == TRUE_VALUE;
    }
    public static Rate rateTrue(MonteCarloBoolean _law) {
        if (_law.isZero()) {
            return Rate.zero();
        }
        return _law.normalizedRate(BoolVal.TRUE);
    }
    public void disTranslatedPkElements(TranslatedPkElements _tr) {
        addImg(_tr.getImage());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_NAME);
        formatMessageDir(_tr.getName());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_GENDER);
        formatMessageDir(_tr.getGender());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_LEVEL);
        formatMessageDir(Long.toString(_tr.getLevel()));
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ABILITY);
        formatMessageDir(_tr.getAbility());
        formatMessage(MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ITEM);
        formatTrKey(_tr.getItem(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_ITEM_NO,"");
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _tr.getMoves(),MessagesPkBean.NPC,MessagesDataMapPokemonKey.M_P_34_MOVES);
    }

    public static void feedForms(int _indexOne, int _indexTwo, StringMapObject _forms) {
        Coords c_ = new Coords();
        c_.setNumberPlace(_indexOne);
        c_.getLevel().setLevelIndex(_indexTwo);
        _forms.put(CST_COORDS, c_);
//        _forms.put(CST_PROPONE_LINK, false);
//        _forms.put(CST_PROPONE_TILE, false);
//        _forms.put(CST_SEE_AREA, false);
//        for (Direction d: Direction.all()) {
//            _forms.putDir(StringUtil.concat(CST_PROPONE_LINK_VAR,d.getDirName()), BoolVal.FALSE);
//        }
    }
    protected void eff(EffectBean _sub) {
        _sub.buildSubEffPre();
        target(_sub.getEffect().getTargetChoice(),MessagesPkBean.EFF,MessagesDataEff.M_P_36_TARGETS_ADJ_ADV,MessagesDataEff.M_P_36_TARGETS_ADJ_MULT,MessagesDataEff.M_P_36_TARGETS_ADJ_UNIQ,MessagesDataEff.M_P_36_TARGETS_ALLIE,MessagesDataEff.M_P_36_TARGETS_ALLIES,MessagesDataEff.M_P_36_TARGETS_ANY_FOE,MessagesDataEff.M_P_36_TARGETS_AUTRE_UNIQ,MessagesDataEff.M_P_36_TARGETS_GLOBALE,MessagesDataEff.M_P_36_TARGETS_LANCEUR,MessagesDataEff.M_P_36_TARGETS_PSEUDO_GLOBALE,MessagesDataEff.M_P_36_TARGETS_TOUS_ADV,MessagesDataEff.M_P_36_TARGETS_UNIQUE_IMPORTE);
        displayStringList(_sub.getReasons(), MessagesPkBean.EFF, MessagesDataEff.M_P_36_REASONS);
        mapVarsInit(_sub.getMapVarsFail());
        displayBoolTrue(toInt(_sub.getNeedSuccessFirstEffect()), MessagesPkBean.EFF, MessagesDataEff.M_P_36_NEED_SUCESS);
        _sub.buildSubEff();
    }

    protected EffectWhileSendingBean displaySend(EffectWhileSendingWithStatistic _s) {
        EffectWhileSendingBean send_ = new EffectWhileSendingBean();
        send_.setBuilder(getBuilder());
        send_.setAppName(getAppName());
        send_.setForms(getForms());
        send_.setFacade(getFacade());
        send_.setLanguage(getLanguage());
        send_.setEffect(_s);
        send_.beforeDisplaying();
        formatMessage(MessagesPkBean.SENDING,MessagesDataSending.M_P_84_EFFECT);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER_2);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER_3);
        displayBoolTrue(toInt(send_.getDisableWeather()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_DISABLE_WEATHER_4);
        formatTrKey(send_.getEnabledWeather(),MessagesPkBean.SENDING,"",MessagesDataSending.M_P_84_WEATHER);
        displayBoolTrue(toInt(send_.getCopyingAbility()),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_COPY_AB);
        displayIntDef(send_.getMultWeight(),MessagesPkBean.SENDING,MessagesDataSending.M_P_84_WEIGHT);
        if (send_.getStatistic()) {
            effStatis(send_.getEffectStatisticCommon());
        }
        return send_;
    }


    protected void formatTrKey(TranslatedKey _key,String _file,String _empty, String _fill) {
        if (!_key.getKey().isEmpty()) {
            formatMessage(_file,_fill);
            formatMessageDir(_key);
        } else {
            formatMessage(_file,_empty);
        }
//        displayNotEmpty(_file,_key.getTranslation(),_fill);
//        displayEmpty(_file,_key.getTranslation(),_empty);
//        formatMessageDir(_key);
    }

    protected void procMoveChoiceRestrictionType(MoveChoiceRestrictionType _value, MoveChoiceRestrictionType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_RESTRICTION,_key);
        }
    }
    protected void procExchangeType(ExchangeType _value, ExchangeType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHABILITIES,_key);
        }
    }
    protected void procExchangeType(MoveItemType _value, MoveItemType _cst, String _key) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHITEMS,_key);
        }
    }

    protected void procPointViewChangementType(PointViewChangementType _value, PointViewChangementType _cst, String _key, String... _values) {
        if (_value == _cst) {
            formatMessage(MessagesPkBean.EFF_SWITCHPOINTVIEW,_key,_values);
        }
    }
    protected void procExchangeType(ExchangeType _value, ExchangeType _cst, CustList<TranslatedKey> _addedTypes, CustList<TranslatedKey> _constTypes, String _key) {
        if (_value == _cst) {
            displayBoolTrue(1-NumberUtil.signum(_addedTypes.size()), MessagesPkBean.EFF_SWITCHTYPES, _key);
            new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,_constTypes,1-NumberUtil.signum(_addedTypes.size()),MessagesPkBean.EFF_SWITCHTYPES,"");
        }
    }
    protected void effStatis(EffectStatisticCommon _sub) {
        if (!_sub.randomStatis()) {
            displayBoolFull(toInt(_sub.isAlwaysEnabled()), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_ALWAYS_ENABLED,MessagesDataEffstatis.M_P_58_RATE_ENABLED,_sub.getEvtRate().toNumberString(),_sub.getEvtRatePerCent());
        }
        if (_sub.notEmptyVarBoost()) {
            if (_sub.randomStatis()) {
                new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(),new BeanDisplayStatRankRate(true,true)).displayGrid(this,_sub.getStatisVarRank(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST,MessagesDataEffstatis.M_P_58_FAIL,MessagesDataEffstatis.M_P_58_RATE_EVENT);
            } else {
                new BeanDisplayMap<TranslatedKey,StatRankRate>(new BeanDisplayTranslatedKey(),new BeanDisplayStatRankRate(true,false)).displayGrid(this,_sub.getStatisVarRank(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_VAR_STATIS_RANK,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_BOOST,MessagesDataEffstatis.M_P_58_FAIL);
            }
            mapVarsInit(_sub.getMapVarsStatistics());
        }
        if (!_sub.getSwapBoostStatis().isEmpty()) {
            new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,_sub.getSwapBoostStatis(),MessagesPkBean.EFF_STATIS,MessagesDataEffstatis.M_P_58_SWAP_BOOST,MessagesDataEffstatis.M_P_58_STATISTIC,MessagesDataEffstatis.M_P_58_FAIL);
            mapVarsInit(_sub.getMapVarsStatistics());
        }
        display(_sub.getCancelLowStat(), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_CANCEL_LOW_STAT,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCancelLowStat());
        display(_sub.getCancelChgtStat(), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_CANCEL_CHGT_STAT,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCancelChgtStat());
        display(_sub.getCopyBoost(), MessagesPkBean.EFF_STATIS, MessagesDataEffstatis.M_P_58_COPY_BOOST,Long.toString(_sub.getDefaultBoost()));
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getCopyBoost());
    }

    public void element(String _file,String _key, String... _values) {
        element(0,_file,_key,_values);
    }

    public void element(int _i,String _file,String _key, String... _values) {
        getBuilder().setIndent(getBuilder().getIndent()+_i);
        initLine();
        getBuilder().indent();
        paintMetaLabelDisk();
        formatMessage(_file, _key, _values);
        feedParents();
        getBuilder().setIndent(getBuilder().getIndent()-_i);
    }

    public void element(Countable _c,String _file,String _key, String... _values) {
        element(0,_c,_file,_key,_values);
    }

    public void element(int _i,Countable _c,String _file,String _key, String... _values) {
        getBuilder().setIndent(getBuilder().getIndent()+_i);
        initLine();
        getBuilder().indent();
        paintMetaLabelDisk();
        display(_c,_file, _key, _values);
        feedParents();
        getBuilder().setIndent(getBuilder().getIndent()-_i);
    }

    public void elementOrd(String _file,String _key, String... _values) {
        elementOrd(0,_file,_key,_values);
    }

    public void elementOrd(int _i,String _file,String _key, String... _values) {
        getBuilder().setIndent(getBuilder().getIndent()+_i);
        initLine();
        getBuilder().indent();
        getBuilder().paintNb(getBuilder().getOrderedLists().last()+1);
        formatMessage(_file, _key, _values);
        getBuilder().getOrderedLists().set(getBuilder().getOrderedLists().getLastIndex(),getBuilder().getOrderedLists().last()+1);
        feedParents();
        getBuilder().setIndent(getBuilder().getIndent()-_i);
    }

    protected void mapVarsInit(AbsMap<String,String> _m) {
        new BeanDisplayList<EntryCust<String,String>>(new BeanDisplayVars()).display(this, _m.getList());
//        for (EntryCust<String,String> e: _m.entryList()) {
//            initLine();
//            paintMetaLabelDisk();
//            formatMessageDir(e.getKey()+" : "+e.getValue());
//            feedParents();
//        }
    }

    public static int width(MiniMapCoordsTileInts _miniMap) {
//        int w_ = 0;
//        int y_ = _miniMap.getKey(w_).getYcoords();
//        while (_miniMap.isValidIndex(w_) && _miniMap.getKey(w_).getYcoords() != y_+1) {
//            w_++;
//        }
        return width(new MiniSecondCoordMapper(_miniMap));
    }

    public static int width(IntSecondCoordMapper _miniMap) {
        int len_ = _miniMap.length();
        Ints values_ = new Ints();
        int first_ = 0;
        for (int i = 0; i < len_; i++) {
            if (i == 0) {
                first_ = _miniMap.sec(i);
            }
            values_.add(_miniMap.sec(i));
        }
        int w_ = 0;
        int y_ = first_;
        while (values_.isValidIndex(w_) && values_.get(w_) != y_+1) {
            w_++;
        }
        return w_;
    }
    public boolean isMultiLayer(CustList<PlaceIndex> _pls,int _index) {
        return layers(_pls,_index).size() > IndexConstants.SECOND_INDEX;
    }
    public CustList<Level> layers(CustList<PlaceIndex> _pls,int _index) {
        Place pl_ = _pls.get(_index).getPlace();
        return pl_.getLevelsList();
    }
    protected DictionaryComparator<TranslatedKey, Rate> map(StringMap<Rate> _input) {
        DictionaryComparator<TranslatedKey, Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new DictionaryComparator<TranslatedKey, Rate>(new ComparingTranslatedKey());
        for (String m: _input.getKeys()) {
            multDamageTypesMoves_.put(buildTy(getFacade(),m), _input.getVal(m));
        }
        return multDamageTypesMoves_;
    }

    protected static CustList<TranslatedKey> listTrStringsAb(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildAb(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsIt(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildIt(_db, s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsMv(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildMv(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsPk(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildPk(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSi(CustList<Statistic> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (Statistic s: _input) {
            res_.add(buildSi(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSt(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildSt(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsTy(CustList<String> _input, FacadeGame _db) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildTy(_db,s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }
    public static TranslatedKey buildCa(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedCategories().getVal(_k)));
    }
    public static TranslatedKey buildTy(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedTypes().getVal(_k)));
    }
    public static TranslatedKey buildAb(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedAbilities().getVal(_k)),new RedirectAb(_k,""), PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, CST_ABILITY);
    }
    public static TranslatedKey buildIt(FacadeGame _db, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_db.getTranslatedItems().getVal(_k)),new RedirectIt(_k,"",_db.getData().getItem(_k)), "", CST_ITEM);
    }
    public static TranslatedKey buildMv(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedMoves().getVal(_k)),new RedirectMv(_k,""), PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML, CST_MOVE);
    }
    public static TranslatedKey buildPk(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedPokemon().getVal(_k)),new RedirectPk(_k,""), PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML, CST_PK);
    }
    public static TranslatedKey buildSt(FacadeGame _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getTranslatedStatus().getVal(_k)),new RedirectSt(_k,""), PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML, CST_STATUS);
    }
    public static TranslatedKey buildEnv(FacadeGame _tr, EnvironmentType _k) {
        return new TranslatedKey(_k.getEnvName(),StringUtil.nullToEmpty(_tr.getTranslatedEnvironment().getVal(_k)));
    }
    public static TranslatedKey buildSi(FacadeGame _tr, Statistic _k) {
        return new TranslatedKey(_k.getStatName(),StringUtil.nullToEmpty(_tr.getTranslatedStatistics().getVal(_k)));
    }
    public static TranslatedKey buildGender(FacadeGame _tr, Gender _k) {
        return new TranslatedKey(_k.getGenderName(),StringUtil.nullToEmpty(_tr.getTranslatedGenders().getVal(_k)));
    }
    public DataBase getDataBase() {
        return db().getData();
    }

    @Override
    public FacadeGame db() {
        return getFacade();
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        setFacade(_dataBase);
    }

    public FacadeGame getFacade() {
        return dataBase;
    }
    public void setFacade(FacadeGame _f) {
        dataBase = _f;
    }
//
//    public String getBaseEncode() {
//        return baseEncode;
//    }

//    public void setBaseEncode(String _p) {
//        this.baseEncode = _p;
//    }

    public StringMapObject getForms() {
        return (StringMapObject) getBaseForms();
    }

    public void setForms(StringMapObject _forms) {
        setBaseForms(_forms);
    }

    public static boolean inRange(long _value, long _min, long _max) {
        return _value >= _min && _value <= _max;
    }
    protected static String escapedStringQuote(String _string) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(CST_QUOTE, CST_ESCAPED_QUOTE);
        map_.put(CST_LEFT_BRACE, StringUtil.concat(CST_QUOTED_LEFT_BRACE, CST_QUOTE));
        map_.put(CST_RIGHT_BRACE, StringUtil.concat(CST_QUOTE, CST_QUOTED_RIGHT_BRACE));
        return StringUtil.formatBasic(_string, map_, false);
    }
    protected static StringList getFormattedReasons(DataBase _data, String _reasons, String _language) {
        return getFormattedReasons(_data,getReasons(_reasons),_language);
    }
    protected static StringList getFormattedReasons(DataBase _data, StringList _reasons, String _language) {
//      Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList reasons_;
        reasons_ = new StringList();
        for (String f: _reasons) {
            String formula_ = _data.getFormula(f, _language);
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(EGT, E_GT);
//            formula_ = formula_.replace(ELT, E_LT);
            reasons_.add(formula_);
        }
        return reasons_;
    }

    protected static NatStringTreeMap<String> getMapVarsFail(DataBase _data, String _fail, String _language) {
        NatStringTreeMap<String> mapVars_ = _data.getDescriptions(_fail, _language);
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap<String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        return mapVarsFail_;
    }

    protected static StringList getReasons(String _booleanString) {
        StringList reasons_;
        reasons_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        int iPostSep_ = IndexConstants.FIRST_INDEX;
        int nbLeftPar_ = 0;
        int nbRightPar_ = 0;
        while (i_ < _booleanString.length()) {
            if (_booleanString.charAt(i_) == CST_LEFT_PAR) {
                nbLeftPar_++;
            }
            if (_booleanString.charAt(i_) == CST_RIGHT_PAR) {
                nbRightPar_++;
            }
            if (_booleanString.charAt(i_) == CST_PIPE_CHAR && nbLeftPar_ == nbRightPar_) {
                reasons_.add(_booleanString.substring(iPostSep_, i_));
                iPostSep_ = i_ + 1;
            }
            i_++;
        }
        if (iPostSep_ < _booleanString.length()) {
            reasons_.add(_booleanString.substring(iPostSep_));
        }
        return reasons_;
    }

    protected static String validOrEmpty(String _str) {
        if (Rate.isValid(_str)) {
            return _str;
        }
        return DataBase.EMPTY_STRING;
    }
    public String tryRedirect(TranslatedKey _tk) {
        return AbsRedirect.tryRedirect(this,_tk.getRedirect(),_tk.getKeyForm(),_tk.getDest());
    }

    public StringMapObjectBase getBaseForms() {
        return baseForms;
    }

    public void setBaseForms(StringMapObjectBase _base) {
        this.baseForms = _base;
    }

    protected void displayStringList(CustList<String> _list, String _file, String _key, String... _values) {
        display(_list, _file, _key,_values);
        displayStringList(_list);
    }

    protected void displayStringList(CustList<String> _list) {
        new BeanDisplayList<String>(new BeanDisplayString()).display(this,_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list, String _file, String _key) {
        display(_list, _file, _key);
        displayTrainerPlaceNamesList(_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list) {
        new BeanDisplayList<TrainerPlaceNames>(new BeanDisplayTrainerPlaceNames()).display(this,_list);
//        for (TrainerPlaceNames i: _list) {
//            builder.initLine();
//            paintMetaLabelDisk();
//            builder.formatMessageDir(i.getTrainer()+" - "+i.getPlace());
//            builder.feedParents();
//        }
    }
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
    }

    protected void init(FacadeGame _facade, StringMapObject _form) {
        setDataBase(_facade);
        setForms(_form);
        setLanguage(_facade.getLanguage());
        beforeDisplaying();
    }

    public TranslationsFile file(String _file) {
        return builder.file(getAppName(),_file);
    }

    public void nextPart() {
        builder.nextPart();
    }

    public void addImg(int[][] _img) {
        builder.addImg(_img);
    }

    public void paintMetaLabelDisk() {
        builder.paintMetaLabelDisk();
    }

    public void formatMessageAnc(IntBeanAction _e,String _file, String _key, String... _values) {
        builder.formatMessageAnc(getAppName(),_e,_file,_key,_values);
        nextPart();
    }

    public void buildPkList(CustList<ImgPkPlayer> _list, String _file, String _key) {
        builder.initPage();
        display(_list, _file, _key);
        buildPkList(_list);
        builder.feedParents();
    }

    public void buildPkList(CustList<ImgPkPlayer> _list) {
        new BeanDisplayList<ImgPkPlayer>(new BeanDisplayImgPkPlayer()).display(this,_list);
    }

    public void setTitledBorder(String _title){
        builder.setTitledBorder(_title);
    }
    public void initLine() {
        builder.initLine();
    }

    public void initGrid() {
        builder.initGrid();
    }

    public void initPage() {
        builder.initPage();
    }

    public void displayHead(Countable _info, String _file, String _keyTitle, String... _cols) {
        display(_info, _file, _keyTitle);
        initGrid();
        headerCols(_info, _file, _cols);
    }

    public void displayHeadParam(Countable _info, String[] _values, String _file, String _keyTitle, String... _cols) {
        display(_info, _file, _keyTitle,_values);
        initGrid();
        headerCols(_info, _file, _cols);
    }

    public void display(Countable _ls, String _file, String _key, String... _values) {
        if (!_ls.isEmpty() && !_key.isEmpty()) {
            builder.formatMessage(getAppName(),_file,_key,_values);
            builder.breakNext();
        }
    }
    public void headerCols(Countable _ls, String _file, String... _cols) {
        if (!_ls.isEmpty()) {
            builder.colCount(_cols.length);
            for (String h_ : _cols) {
                headerCol(_file, h_);
            }
        }
    }
    public void displayEmpty(String _value, String _file, String _key) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key);
        }
    }
    public void displayEmpty(Countable _value, String _file, String _key, String... _values) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayNotEmpty(String _value, String _file, String _key) {
        if (!_value.isEmpty()) {
            formatMessage(_file,_key,_value);
        }
    }
    public void displayBoolFull(int _value, String _file, String _one, String _two) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two);
        }
    }

    public void displayBoolFull(int _value, String _file, String _one, String _two, String... _values) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one,_values);
        } else {
            formatMessage(_file,_two,_values);
        }
    }

    public void displayIntDef(long _value, String _file, String _one, String _two) {
        if (_value == 0) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two,Long.toString(_value));
        }
    }

    public void displayIntDef(long _value, String _file, String _one) {
        displayIntDef(Long.toString(_value), _file, _one);
    }

    public void displayIntDef(Rate _value, String _file, String _one) {
        displayIntDef(_value.toNumberString(), _file, _one);
    }

    public void displayIntDef(String _value, String _file, String _one) {
        if (!StringUtil.quickEq(_value,"0")) {
            formatMessage(_file,_one,_value);
        }
    }
    public void target(TargetChoice _target, String _file, String... _poss) {
        if (_target == TargetChoice.ADJ_ADV) {
            formatMessage(_file,_poss[0]);
        }
        if (_target == TargetChoice.ADJ_MULT) {
            formatMessage(_file,_poss[1]);
        }
        if (_target == TargetChoice.ADJ_UNIQ) {
            formatMessage(_file,_poss[2]);
        }
        if (_target == TargetChoice.ALLIE) {
            formatMessage(_file,_poss[3]);
        }
        if (_target == TargetChoice.ALLIES) {
            formatMessage(_file,_poss[4]);
        }
        if (_target == TargetChoice.ANY_FOE) {
            formatMessage(_file,_poss[5]);
        }
        if (_target == TargetChoice.AUTRE_UNIQ) {
            formatMessage(_file,_poss[6]);
        }
        if (_target == TargetChoice.GLOBALE) {
            formatMessage(_file,_poss[7]);
        }
        if (_target == TargetChoice.LANCEUR) {
            formatMessage(_file,_poss[8]);
        }
        if (_target == TargetChoice.PSEUDO_GLOBALE) {
            formatMessage(_file,_poss[9]);
        }
        if (_target == TargetChoice.TOUS_ADV) {
            formatMessage(_file,_poss[10]);
        }
        if (_target == TargetChoice.UNIQUE_IMPORTE) {
            formatMessage(_file,_poss[11]);
        }
    }
    public void displayBoolFalse(int _value, String _file, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.FALSE_VALUE,_key,_values);
    }
    public void displayBoolTrue(int _value, String _file, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.TRUE_VALUE,_key,_values);
    }
    public void displayBool(String _file, int _value, int _car, String _key, String... _values) {
        if (_value == _car) {
            formatMessage(_file,_key,_values);
        }
    }
    public void displayBool(int _value, int _car, int[][] _key) {
        if (_value == _car) {
            addImg(_key);
        }
    }
    public void formatMessage(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(getAppName(),_file, _key, _values);
        builder.formatMessageDir(txt_);
    }

    public void formatMessageCts(String _file, String _key, String... _values) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key, _values);
        builder.formatMessageDirCts(txt_);
        builder.nextPart();
    }

    public void formatMessageIndent(String _file, String _key, String... _values) {
        if (getBuilder().getIndent() == 0) {
            formatMessage(_file, _key, _values);
            return;
        }
        initLine();
        getBuilder().indent();
        String txt_ = formatMessageRend(_file, _key, _values);
        formatMessageDir(txt_);
        feedParents();
    }

    public void formatMessageDirIndent(String _txt) {
        initLine();
        getBuilder().indent();
        paintMetaLabelDisk();
        formatMessageDir(_txt);
        feedParents();
    }

    public String formatMessageRend(String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(builder.file(getAppName(),_file).getMapping().getVal(_key), _values);
    }

    public void formatMessageDirAnc(String _txt, IntBeanAction _b) {
        builder.formatMessageDir(_txt, _b);
    }

    public void formatMessageDir(String _txt) {
        builder.formatMessageDir(_txt);
    }

    public void formatMessageDirCts(String _txt) {
        formatMessageDirCts(new TranslatedKey(_txt,_txt));
    }

    public void formatMessageDirCts(TranslatedKey _txt) {
        if (ent(_txt)) {
            builder.formatMessageDirCts(_txt.getTranslation(),new EntityClickFormEvent(this,_txt));
        } else {
            builder.formatMessageDirCts(_txt.getTranslation());
        }
    }

    public void formatMessageDir(TranslatedKey _txt) {
        if (ent(_txt)) {
            builder.formatMessageDir(_txt.getTranslation(),new EntityClickFormEvent(this,_txt));
        } else {
            builder.formatMessageDir(_txt.getTranslation());
        }
    }
    private boolean ent(TranslatedKey _txt) {
        return !_txt.getKey().isEmpty() && _txt.getRedirect() != null;
    }

    public void feedParentsCts() {
        builder.feedParentsCts();
    }

    public void feedParents() {
        builder.feedParents();
    }

    public void displayActivityOfMoveEnabled(ActivityOfMove _value,String _one, String _two) {
        if (_value.isEnabled()) {
            formatMessageDirCts(_one);
        } else {
            formatMessageDirCts(_two);
        }
    }
    public void displayActivityOfMoveNbRound(ActivityOfMove _value, String _key) {
        if (_value.isIncrementCount()) {
            formatMessageDirCts(Long.toString(_value.getNbTurn()));
        } else {
            formatMessageDirCts(_key);
        }
    }
    public void headerCol(String _file, String _key) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key);
        builder.formatMessageDirCtsHeader(txt_);
    }
    public IntBeanBuilderHelper getBuilder() {
        return builder;
    }

    public void setBuilder(IntBeanBuilderHelper _b) {
        this.builder = _b;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String _a) {
        this.appName = _a;
    }
}