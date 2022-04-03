package cards.belote.beans;

import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import code.bean.nat.*;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatStdFctOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
public final class BeloteStandards extends BeanNatCommonLgNames {
    private static final String COMPTE_POINTS_CLASSIQUE = "comptePointsClassique";
    private static final String REPARTITION = "repartition";
    private static final String GESTION_COUPE_PARTENAIRE = "gestionCoupePartenaire";
    private static final String SOUS_COUPE_ADV = "sousCoupeAdv";
    private static final String ENCHERES_AUTORISEES = "encheresAutorisees";
    private static final String ANNONCES_AUTORISEES = "annoncesAutorisees";
    private static final String DEAL_ALL = "dealAll";
    private static final String CARTES_BATTUES = "cartesBattues";
    private static final String SCORES = "scores";
    private static final String NUMBER = "number";
    private static final String LINES_DEAL = "linesDeal";
    private static final String CALLED_PLAYERS_LIST = "calledPlayersList";
    private static final String BID_STRING = "bidString";
    private static final String TAKER_NICKNAME = "takerNickname";
    private static final String DIFFERENCE_SCORE_TAKER = "differenceScoreTaker";
    private static final String POINTS_DEFENSE_DEFINITIF = "pointsDefenseDefinitif";
    private static final String POINTS_DEFENSE_TEMPORAIRE = "pointsDefenseTemporaire";
    private static final String POINTS_DEFENSE_SANS_PRIME = "pointsDefenseSansPrime";
    private static final String POINTS_ATTAQUE_DEFINITIF = "pointsAttaqueDefinitif";
    private static final String POINTS_ATTAQUE_TEMPORAIRE = "pointsAttaqueTemporaire";
    private static final String POINTS_ATTAQUE_SANS_PRIME = "pointsAttaqueSansPrime";
    private static final String ABSOLUTE_DIFF = "absoluteDiff";
    private static final String SLAM = "slam";
    private static final String FAILED_BID = "failedBid";
    private static final String MID_BID = "midBid";
    private static final String SUCCESSFUL_BID = "successfulBid";
    private static final String LOOSE = "loose";
    private static final String EQUALITY = "equality";
    private static final String WIN = "win";
    private static final String VALUE = "value";
    private static final String STATUT = "statut";
    private static final String NICKNAME = "nickname";
    private static final String SUM = "sum";
    private static final String DECLARING = "declaring";
    private static final String GET_SCORES = "getScores";
    private static final String GET_NICKNAMES = "getNicknames";
    private static final String PLAY_GAME = "playGame";
    private static final String TYPE_RULES_BELOTE_BEAN = "cards.belote.beans.RulesBeloteBean";
    private static final String TYPE_LINE_DEAL = "cards.belote.beans.LineDeal";
    private static final String TYPE_RESULTS_BELOTE_BEAN = "cards.belote.beans.ResultsBeloteBean";
    private static final String TYPE_DECLARING_PLAYER_VALUE = "cards.belote.beans.DeclaringPlayerValue";
    private static final String TYPE_SUM_DECLARING_PLAYER = "cards.belote.beans.SumDeclaringPlayer";
    private static final String TYPE_DETAILS_RESULTS_BELOTE_BEAN = "cards.belote.beans.DetailsResultsBeloteBean";
    private static final String TYPE_BELOTE_BEAN = "cards.belote.beans.BeloteBean";
    private ResultsBelote dataBase;
    private RulesBelote dataBaseRules;
    public BeloteStandards(){
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeloteBean();
        buildResultsBeloteBean();
        buildLineDeal();
        buildSumDeclaringPlayer();
        buildDeclaringPlayerValue();
        buildDetailsResultsBeloteBean();
        buildRulesBeloteBean();
    }
    private void buildBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_BELOTE_BEAN, fields_, methods_, TYPE_BEAN);
        methods_.add( new SpecNatMethod(PLAY_GAME, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new BeloteBeanPlayGame()));
        methods_.add( new SpecNatMethod(GET_NICKNAMES, TYPE_LIST, false, MethodModifier.NORMAL,new BeloteBeanGetNicknames()));
        methods_.add( new SpecNatMethod(GET_SCORES, TYPE_LIST, false, MethodModifier.NORMAL,new BeloteBeanGetScores()));
        getStds().addEntry(TYPE_BELOTE_BEAN, std_);
    }
    private void buildResultsBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RESULTS_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(POINTS_ATTAQUE_SANS_PRIME, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsAttaqueSansPrime(),null));
        fields_.add( new StandardField(POINTS_ATTAQUE_TEMPORAIRE, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsAttaqueTemporaire(),null));
        fields_.add( new StandardField(POINTS_ATTAQUE_DEFINITIF, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsAttaqueDefinitif(),null));
        fields_.add( new StandardField(POINTS_DEFENSE_SANS_PRIME, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsDefenseSansPrime(),null));
        fields_.add( new StandardField(POINTS_DEFENSE_TEMPORAIRE, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsDefenseTemporaire(),null));
        fields_.add( new StandardField(POINTS_DEFENSE_DEFINITIF, PRIM_INTEGER, false, false,new ResultsBeloteBeanPointsDefenseDefinitif(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, PRIM_INTEGER, false, false,new ResultsBeloteBeanDifferenceScoreTaker(),null));
        fields_.add( new StandardField(BID_STRING, STRING, false, false,new ResultsBeloteBeanBidString(),null));
        fields_.add( new StandardField(TAKER_NICKNAME, STRING, false, false,new ResultsBeloteBeanTakerNickname(),null));
        fields_.add( new StandardField(CALLED_PLAYERS_LIST, TYPE_LIST, false, false,new ResultsBeloteBeanCalledPlayersList(),null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false,new ResultsBeloteBeanLinesDeal(),null));
        methods_.add( new SpecNatMethod(WIN, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanWin()));
        methods_.add( new SpecNatMethod(EQUALITY, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanEquality()));
        methods_.add( new SpecNatMethod(LOOSE, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanLoose()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanSuccessfulBid()));
        methods_.add( new SpecNatMethod(MID_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanMidBid()));
        methods_.add( new SpecNatMethod(FAILED_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanFailedBid()));
        methods_.add( new SpecNatMethod(SLAM, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsBeloteBeanSlam()));
        methods_.add( new SpecNatMethod(ABSOLUTE_DIFF, PRIM_INTEGER, false, MethodModifier.NORMAL,new ResultsBeloteBeanAbsoluteDiff()));
        getStds().addEntry(TYPE_RESULTS_BELOTE_BEAN, std_);
    }
    private void buildLineDeal(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NUMBER, PRIM_INTEGER, false, false,new BeloteLineDealNumber(),null));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false,new BeloteLineDealScores(),null));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
    }
    private void buildSumDeclaringPlayer(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_SUM_DECLARING_PLAYER, fields_, methods_, OBJECT);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false,new BeloteSumDeclaringPlayerDeclaring(),null));
        fields_.add( new StandardField(SUM, PRIM_INTEGER, false, false,new BeloteSumDeclaringPlayerSum(),null));
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new BeloteSumDeclaringPlayerNickname(),null));
        fields_.add( new StandardField(STATUT, STRING, false, false,new BeloteSumDeclaringPlayerStatut(),null));
        getStds().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
    }
    private void buildDeclaringPlayerValue(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_DECLARING_PLAYER_VALUE, fields_, methods_, OBJECT);
        fields_.add( new StandardField(DECLARING, STRING, false, false,new DeclaringPlayerValueDeclaring(),null));
        fields_.add( new StandardField(VALUE, PRIM_INTEGER, false, false,new DeclaringPlayerValueValue(),null));
        getStds().addEntry(TYPE_DECLARING_PLAYER_VALUE, std_);
    }
    private void buildDetailsResultsBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_DETAILS_RESULTS_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false,new DetailsResultsBeloteBeanDeclaring(),null));
        getStds().addEntry(TYPE_DETAILS_RESULTS_BELOTE_BEAN, std_);
    }
    private void buildRulesBeloteBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RULES_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, false, false,new RulesBeloteBeanCartesBattues(),null));
        fields_.add( new StandardField(DEAL_ALL, PRIM_BOOLEAN, false, false,new RulesBeloteBeanDealAll(),null));
        fields_.add( new StandardField(ENCHERES_AUTORISEES, TYPE_LIST, false, false,new RulesBeloteBeanEncheresAutorisees(),null));
        fields_.add( new StandardField(SOUS_COUPE_ADV, PRIM_BOOLEAN, false, false,new RulesBeloteBeanSousCoupeAdv(),null));
        fields_.add( new StandardField(ANNONCES_AUTORISEES, TYPE_LIST, false, false,new RulesBeloteBeanAnnoncesAutorisees(),null));
        fields_.add( new StandardField(GESTION_COUPE_PARTENAIRE, STRING, false, false,new RulesBeloteBeanGestionCoupePartenaire(),null));
        fields_.add( new StandardField(REPARTITION, STRING, false, false,new RulesBeloteBeanRepartition(),null));
        fields_.add( new StandardField(COMPTE_POINTS_CLASSIQUE, PRIM_BOOLEAN, false, false,new RulesBeloteBeanComptePointsClassique(),null));
        getStds().addEntry(TYPE_RULES_BELOTE_BEAN, std_);
    }
    @Override
    public void beforeDisplaying(Struct _arg) {
        ((BeloteBeanStruct)_arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        RendDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_conf, _rendStack);
    }

    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        Struct strBean_ = getOtherResultBean(id_, args_);
        return update(_language, (BeloteBeanStruct) strBean_);
    }

    private BeloteBeanStruct update(String _language, BeloteBeanStruct _str) {
        BeloteBean bean_ = _str.getInstance();
        bean_.setDataBase(dataBase,dataBaseRules);
        bean_.setLanguage(_language);
        return _str;
    }

    public static ArrayStruct getSumDeclaringPlayerArray(CustList<BeloteSumDeclaringPlayer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_SUM_DECLARING_PLAYER));
        int j_ = 0;
        for (BeloteSumDeclaringPlayer s:_ls) {
            arr_.set(j_,new BeloteSumDeclaringPlayerStruct(s, TYPE_SUM_DECLARING_PLAYER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getDeclaringPlayerValueArray(CustList<DeclaringPlayerValue> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_DECLARING_PLAYER_VALUE));
        int j_ = 0;
        for (DeclaringPlayerValue s:_ls) {
            arr_.set(j_,new DeclaringPlayerValueStruct(s, TYPE_DECLARING_PLAYER_VALUE));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getLineDealArray(CustList<BeloteLineDeal> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LINE_DEAL));
        int j_ = 0;
        for (BeloteLineDeal s:_ls) {
            arr_.set(j_,new BeloteLineDealStruct(s, TYPE_LINE_DEAL));
            j_++;
        }
        return arr_;
    }

    public Struct getOtherResultBean(ConstructorId _method, Struct... _args) {
        if (StringUtil.quickEq(_method.getName(), TYPE_DETAILS_RESULTS_BELOTE_BEAN)) {
            DetailsResultsBeloteBean details_ = new DetailsResultsBeloteBean();
            details_.setClassName(TYPE_DETAILS_RESULTS_BELOTE_BEAN);
            return(new BeloteBeanStruct(details_));
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RESULTS_BELOTE_BEAN)) {
            ResultsBeloteBean details_ = new ResultsBeloteBean();
            details_.setClassName(TYPE_RESULTS_BELOTE_BEAN);
            return(new BeloteBeanStruct(details_));
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RULES_BELOTE_BEAN)) {
            RulesBeloteBean details_ = new RulesBeloteBean();
            details_.setClassName(TYPE_RULES_BELOTE_BEAN);
            return(new BeloteBeanStruct(details_));
        }
        return NullStruct.NULL_VALUE;
    }

    public void setDataBase(ResultsBelote _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesBelote _dataBase){
        dataBaseRules = _dataBase;
    }
}
