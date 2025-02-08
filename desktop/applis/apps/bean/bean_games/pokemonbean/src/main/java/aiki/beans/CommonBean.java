package aiki.beans;

import aiki.beans.fight.TrPkMoveTarget;
import aiki.beans.game.ImgPkPlayer;
import aiki.comparators.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
import aiki.util.Coords;
import code.bean.Bean;
import code.bean.nat.StringMapObjectBase;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.MessagesFightFight;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.util.ints.Countable;

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
    protected NatStringTreeMap<Rate> map(StringMap<Rate> _input, StringMap<String> _translated) {
        NatStringTreeMap< Rate> multDamageTypesMoves_;
        multDamageTypesMoves_ = new NatStringTreeMap< Rate>();
        for (String m: _input.getKeys()) {
            multDamageTypesMoves_.put(_translated.getVal(m), _input.getVal(m));
        }
        return multDamageTypesMoves_;
    }

    protected static CustList<TranslatedKey> listTrStringsAb(CustList<String> _input, DataBase _db, String _lg) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildAb(_db.getTranslatedAbilities().getVal(_lg),s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsIt(CustList<String> _input, DataBase _db, String _lg) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildIt(_db,_db.getTranslatedItems().getVal(_lg),s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsMv(CustList<String> _input, DataBase _db, String _lg) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildMv(_db.getTranslatedMoves().getVal(_lg),s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsPk(CustList<String> _input, DataBase _db, String _lg) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildPk(_db.getTranslatedPokemon().getVal(_lg),s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsSt(CustList<String> _input, DataBase _db, String _lg) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(buildSt(_db.getTranslatedStatus().getVal(_lg),s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    protected static CustList<TranslatedKey> listTrStringsTy(CustList<String> _input, DataBase _db, String _lg) {
        CustList<TranslatedKey> res_ = new CustList<TranslatedKey>();
        for (String s: _input) {
            res_.add(build(_db.getTranslatedTypes().getVal(_lg),s));
        }
        res_.sortElts(new ComparingTranslatedKey());
        return res_;
    }

    public static TranslatedKey build(AbsMap<String,String> _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getVal(_k)));
    }
    public static TranslatedKey buildAb(AbsMap<String,String> _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getVal(_k)),new RedirectAb(_k,""), PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, CST_ABILITY);
    }
    public static TranslatedKey buildIt(DataBase _db,AbsMap<String,String> _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getVal(_k)),new RedirectIt(_k,"",_db.getItem(_k)), "", CST_ITEM);
    }
    public static TranslatedKey buildMv(AbsMap<String,String> _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getVal(_k)),new RedirectMv(_k,""), PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML, CST_MOVE);
    }
    public static TranslatedKey buildPk(AbsMap<String,String> _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getVal(_k)),new RedirectPk(_k,""), PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML, CST_PK);
    }
    public static TranslatedKey buildSt(AbsMap<String,String> _tr, String _k) {
        return new TranslatedKey(_k,StringUtil.nullToEmpty(_tr.getVal(_k)),new RedirectSt(_k,""), PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML, CST_STATUS);
    }
    public static TranslatedKey buildEnv(AbsMap<EnvironmentType,String> _tr, EnvironmentType _k) {
        return new TranslatedKey(_k.getEnvName(),StringUtil.nullToEmpty(_tr.getVal(_k)));
    }
    public static TranslatedKey buildSi(AbsMap<Statistic,String> _tr, Statistic _k) {
        return new TranslatedKey(_k.getStatName(),StringUtil.nullToEmpty(_tr.getVal(_k)));
    }
    public static TranslatedKey buildGender(AbsMap<Gender,String> _tr, Gender _k) {
        return new TranslatedKey(_k.getGenderName(),StringUtil.nullToEmpty(_tr.getVal(_k)));
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
    protected String tryRedirect(TranslatedKey _tk) {
        return AbsRedirect.tryRedirect(this,_tk.getRedirect(),_tk.getKeyForm(),_tk.getDest());
    }
    protected String tryRedirectAb(String _name) {
        return tryRedirectAb(CST_ABILITY,_name, PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,"");
    }
    protected String tryRedirectAb(String _key, String _name, String _target, String _def) {
        return AbsRedirect.tryRedirect(this,new RedirectAb(_name,_def),_key,_target);
    }
    protected String tryRedirectIt(String _name) {
        return tryRedirectIt(CST_ITEM,_name,"","");
    }
    protected String tryRedirectIt(String _key, String _name, String _target, String _def) {
        return AbsRedirect.tryRedirect(this,new RedirectIt(_name,_def,getDataBase().getItem(_name)),_key,_target);
    }
    protected String tryRedirectMv(String _name) {
        return tryRedirectMv(CST_MOVE,_name, PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,"");
    }
    protected String tryRedirectMv(String _key, String _name, String _target, String _def) {
        return AbsRedirect.tryRedirect(this,new RedirectMv(_name,_def),_key,_target);
    }
    protected String tryRedirectSt(String _name) {
        return tryRedirectSt(CST_STATUS,_name, PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,"");
    }
    protected String tryRedirectSt(String _key, String _name, String _target, String _def) {
        return AbsRedirect.tryRedirect(this,new RedirectSt(_name,_def),_key,_target);
    }

    public StringMapObjectBase getBaseForms() {
        return baseForms;
    }

    public void setBaseForms(StringMapObjectBase _base) {
        this.baseForms = _base;
    }

    protected void displayStringList(String _file, CustList<String> _list, String _key) {
        builder.breakLine();
        display(_file, _list, _key);
        displayStringList(_list);
    }

    protected void displayStringList(CustList<String> _list) {
        for (String i: _list) {
            nextPart();
            builder.initLine();
            paintMetaLabelDisk();
            builder.formatMessageDir(i);
            builder.feedParents();
            builder.breakLine();
        }
    }

    protected void displayTrainerPlaceNamesList(String _file, CustList<TrainerPlaceNames> _list, String _key) {
        builder.breakLine();
        display(_file, _list, _key);
        displayTrainerPlaceNamesList(_list);
    }

    protected void displayTrainerPlaceNamesList(CustList<TrainerPlaceNames> _list) {
        for (TrainerPlaceNames i: _list) {
            nextPart();
            builder.initLine();
            paintMetaLabelDisk();
            builder.formatMessageDir(i.getTrainer()+" - "+i.getPlace());
            builder.feedParents();
            builder.breakLine();
        }
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
    }

    public void buildPkList(String _file, String _key, CustList<ImgPkPlayer> _list) {
        builder.initPage();
        display(_file, _list, _key);
        buildPkList(_list);
    }

    public void buildPkList(CustList<ImgPkPlayer> _list) {
        for (ImgPkPlayer i: _list) {
            nextPart();
            initLine();
            paintMetaLabelDisk();
            builder.addImg(i.getImage());
            builder.formatMessageDir(i.getKey().getTranslation());
            builder.feedParents();
            builder.breakLine();
        }
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

    public void display(String _file, Countable _ls, String _key) {
        if (!_ls.isEmpty()) {
            builder.formatMessage(getAppName(),_file,_key);
            builder.breakLine();
        }
    }
    public void headerCols(String _file, Countable _ls, String... _cols) {
        if (!_ls.isEmpty()) {
            builder.colCount(_cols.length);
            for (String h_ : _cols) {
                headerCol(_file, h_);
            }
        }
    }
    public void displayEmpty(String _file, String _value, String _key) {
        if (_value.isEmpty()) {
            formatMessage(_file,_key);
        }
    }
    public void displayNotEmpty(String _file, String _value, String _key) {
        if (!_value.isEmpty()) {
            formatMessage(_file,_key,_value);
        }
    }
    public void displayBoolFull(String _file, int _value, String _one, String _two) {
        if (_value == CommonBean.TRUE_VALUE) {
            formatMessage(_file,_one);
        } else {
            formatMessage(_file,_two);
        }
    }
    public void displayBoolFalse(String _file, int _value, String _key, String... _values) {
        displayBool(_file,_value,CommonBean.FALSE_VALUE,_key,_values);
    }
    public void displayBoolTrue(String _file, int _value, String _key, String... _values) {
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
        builder.formatMessageDirCts(_txt);
    }

    public void feedParentsCts() {
        builder.feedParentsCts();
    }

    public void feedParents() {
        builder.feedParents();
    }

    public void displayTrPkMoveTarget(String _file, TrPkMoveTarget _value) {
        formatMessageDirCts(_value.getMoveTarget().getMove());
        if (_value.getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
            formatMessageCts(_file, MessagesFightFight.M_P_90_ALLY_CHOICES_FOE);
        } else {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_PLAYER);
        }
        if (_value.getMoveTarget().getTarget().getPosition() != Fighter.BACK) {
            formatMessageDirCts(Long.toString(_value.getMoveTarget().getTarget().getPosition()));
            formatMessageDirCts(_value.getTranslation());
        } else {
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
            formatMessageCts(_file,MessagesFightFight.M_P_90_ALLY_CHOICES_NO);
        }
    }

    public void displayActivityOfMoveEnabled(String _file, ActivityOfMove _value, String _one, String _two) {
        if (_value.isEnabled()) {
            formatMessageCts(_file,_one);
        } else {
            formatMessageCts(_file,_two);
        }
    }
    public void displayActivityOfMoveNbRound(String _file, ActivityOfMove _value, String _key) {
        if (_value.isIncrementCount()) {
            formatMessageDirCts(Long.toString(_value.getNbTurn()));
        } else {
            formatMessageCts(_file,_key);
        }
    }
    public void headerCol(String _file, String _key) {
        String txt_ = builder.formatMessageRend(getAppName(), _file, _key);
        builder.formatMessageDirCtsHeader(txt_);
    }
    public void breakLine() {
        builder.breakLine();
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