package cards.tarot.beans;

import cards.consts.beans.*;
import cards.tarot.*;
import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.fwd.*;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.*;
public abstract class TarotStandards extends BeanNatCommonLgNames {
    protected static final String TYPE_TAROT_BEAN = "cards.tarot.beans.TarotBean";
    static final String TYPE_RATE = "$Rate";
    private static final String NICKNAMES = "nicknames";
    private static final String BONUS = "bonus";
    private static final String POINTS_TRICKS = "pointsTricks";
    private static final String DIFFERENCE_SCORE = "differenceScore";
    private static final String MINIMUM_POINTS = "minimumPoints";
    private static final String FINAL_POSITION = "finalPosition";
    private static final String POSITION_STRENGTH_CHARACTERS = "positionStrengthCharacters";
    private static final String POSITION_CHARACTERS = "positionCharacters";
    private static final String POSITION_OUDLERS = "positionOudlers";
    private static final String POSITION_DIFF = "positionDiff";
    private static final String SCORE = "score";
    private static final String SUM = "sum";
    private static final String MISERES = "miseres";
    private static final String HANDFULS = "handfuls";
    private static final String STATUS = "status";
    private static final String NICKNAME = "nickname";
    private static final String RATE = "rate";
    private static final String GET_SCORES = "getScores";
    private static final String GET_NICKNAMES = "getNicknames";
    private static final String PLAY_VARIANT_MODE_GAME = "playVariantModeGame";
    private static final String PLAY_CLASSIC_GAME = "playClassicGame";
    private static final String TYPE_BONUSES_PLAYERS = "cards.tarot.beans.BonusesPlayers";
    private static final String TYPE_POINTS_PLAYER_VARIANT_GAME = "cards.tarot.beans.PointsPlayerVariantGame";
    private static final String TYPE_RANKING_PLAYER_VARIANT_GAME = "cards.tarot.beans.RankingPlayerVariantGame";
    private static final String TYPE_SCORES_PLAYERS = "cards.tarot.beans.ScoresPlayers";
    private static final String TYPE_SUM_DECLARING_PLAYER = "cards.tarot.beans.SumDeclaringPlayer";
    private ResultsTarot dataBase;
    private RulesTarot dataBaseRules;
    protected TarotStandards(){
    }
    @Override
    public void buildOther() {
        buildTarotBean();
        buildAddon();
    }
    protected void def() {
        buildSumDeclaringPlayer();
        buildScoresPlayers();
        buildRankingPlayerVariantGame();
        buildPointsPlayerVariantGame();
        buildBonusesPlayers();
        buildLineDeal();
    }
    protected abstract void buildAddon();
    private void buildTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, new TarotBeanNicknames(),null));
        methods_.add( new SpecNatMethod(PLAY_CLASSIC_GAME, PRIM_BOOLEAN, new TarotBeanPlayClassicGame()));
        methods_.add( new SpecNatMethod(PLAY_VARIANT_MODE_GAME, PRIM_BOOLEAN, new TarotBeanPlayVariantModeGame()));
        methods_.add( new SpecNatMethod(GET_NICKNAMES, TYPE_LIST, new TarotBeanGetNicknames()));
        methods_.add( new SpecNatMethod(GET_SCORES, TYPE_LIST, new TarotBeanGetScores()));
        getStds().addEntry(TYPE_TAROT_BEAN, std_);
    }
    private void buildSumDeclaringPlayer(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        fields_.add( new StandardField(SUM, PRIM_INTEGER, new TarotSumDeclaringPlayerSum(),null));
        fields_.add( new StandardField(STATUS, STRING, new TarotSumDeclaringPlayerStatus(),null));
        fields_.add( new StandardField(NICKNAME, STRING, new TarotSumDeclaringPlayerNickname(),null));
        fields_.add( new StandardField(HANDFULS, TYPE_MAP, new TarotSumDeclaringPlayerHandfuls(),null));
        fields_.add( new StandardField(MISERES, TYPE_MAP, new TarotSumDeclaringPlayerMiseres(),null));
        getStds().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
    }
    private void buildScoresPlayers(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        fields_.add( new StandardField(SUM, PRIM_INTEGER, new ScoresPlayersSum(),null));
        fields_.add( new StandardField(NICKNAME, STRING, new ScoresPlayersNickname(),null));
        fields_.add( new StandardField(SCORE, PRIM_INTEGER, new ScoresPlayersScore(),null));
        fields_.add( new StandardField(RATE, TYPE_RATE, new ScoresPlayersRate(),null));
        getStds().addEntry(TYPE_SCORES_PLAYERS, std_);
    }
    private void buildRankingPlayerVariantGame(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, new RankingPlayerVariantGameNickname(),null));
        fields_.add( new StandardField(POSITION_DIFF, PRIM_INTEGER, new RankingPlayerVariantGamePositionDiff(),null));
        fields_.add( new StandardField(POSITION_OUDLERS, PRIM_INTEGER, new RankingPlayerVariantGamePositionOudlers(),null));
        fields_.add( new StandardField(POSITION_CHARACTERS, PRIM_INTEGER, new RankingPlayerVariantGamePositionCharacters(),null));
        fields_.add( new StandardField(POSITION_STRENGTH_CHARACTERS, PRIM_INTEGER, new RankingPlayerVariantGamePositionStrengthCharacters(),null));
        fields_.add( new StandardField(FINAL_POSITION, PRIM_INTEGER, new RankingPlayerVariantGameFinalPosition(),null));
        getStds().addEntry(TYPE_RANKING_PLAYER_VARIANT_GAME, std_);
    }
    private void buildPointsPlayerVariantGame(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, new PointsPlayerVariantGameNickname(),null));
        fields_.add( new StandardField(RATE, PRIM_INTEGER, new PointsPlayerVariantGameRate(),null));
        fields_.add( new StandardField(SCORE, PRIM_INTEGER, new PointsPlayerVariantGameScore(),null));
        fields_.add( new StandardField(MINIMUM_POINTS, PRIM_INTEGER, new PointsPlayerVariantGameMinimumPoints(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE, TYPE_RATE, new PointsPlayerVariantGameDifferenceScore(),null));
        fields_.add( new StandardField(POINTS_TRICKS, TYPE_RATE, new PointsPlayerVariantGamePointsTricks(),null));
        getStds().addEntry(TYPE_POINTS_PLAYER_VARIANT_GAME, std_);
    }
    private void buildBonusesPlayers(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, new BonusesPlayersNickname(),null));
        fields_.add( new StandardField(BONUS, PRIM_INTEGER, new BonusesPlayersBonus(),null));
        getStds().addEntry(TYPE_BONUSES_PLAYERS, std_);
    }
    private void buildLineDeal(){
        LineDealStruct.buildLineDeal(getStds());
    }

    @Override
    public InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, Struct _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack,ip_);
        return new InvokedPageOutput(_dest,res_);
    }

    protected TarotBeanStruct bean(TarotBean _bean, String _lg) {
        _bean.setDataBase(dataBase,dataBaseRules);
        _bean.setLanguage(_lg);
        return (new TarotBeanStruct(_bean));
    }


    public static NatArrayStruct getSumDeclaringPlayerArray(CustList<TarotSumDeclaringPlayer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (TarotSumDeclaringPlayer s:_ls) {
            arr_.set(j_,new TarotSumDeclaringPlayerStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getScoresPlayersArray(CustList<ScoresPlayers> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (ScoresPlayers s:_ls) {
            arr_.set(j_,new ScoresPlayersStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getRankingPlayerVariantGameArray(CustList<RankingPlayerVariantGame> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (RankingPlayerVariantGame s:_ls) {
            arr_.set(j_,new RankingPlayerVariantGameStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getPointsPlayerVariantGameArray(CustList<PointsPlayerVariantGame> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PointsPlayerVariantGame s:_ls) {
            arr_.set(j_,new PointsPlayerVariantGameStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getBonusesPlayersArray(CustList<BonusesPlayers> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (BonusesPlayers s:_ls) {
            arr_.set(j_,new BonusesPlayersStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStringShortTree(StringMap<Short> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String, Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStringIntegerTree(StringMap<Integer> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String, Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new StringStruct(StringUtil.nullToEmpty(e.getKey())),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public void setDataBase(ResultsTarot _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesTarot _dataBase){
        dataBaseRules = _dataBase;
    }

    @Override
    protected AbstractNatBlockBuilder blockBuilder() {
        return new DefNatBlockBuilder();
    }

    @Override
    protected NatRendStackCall newNatRendStackCall() {
        return new NatRendStackCall();
    }
}
