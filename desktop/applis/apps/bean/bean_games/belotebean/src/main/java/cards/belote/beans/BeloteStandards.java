package cards.belote.beans;

import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import code.bean.nat.*;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.util.*;
import code.util.core.StringUtil;

public final class BeloteStandards extends BeanNatLgNames {

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
    private static final String TYPE_RULES_BELOTE = "RulesBelote";
    private static final String TYPE_RESULTS_BELOTE = "ResultsBelote";
    private static final String TYPE_DECLARES_BELOTE = "$DeclaresBelote";
    private static final String TYPE_BID_BELOTE = "$BidBelote";
    private static final String TYPE_RULES_BELOTE_BEAN = "cards.belote.beans.RulesBeloteBean";
    private static final String TYPE_LINE_DEAL = "cards.belote.beans.LineDeal";
    private static final String TYPE_RESULTS_BELOTE_BEAN = "cards.belote.beans.ResultsBeloteBean";
    private static final String TYPE_DECLARING_PLAYER_VALUE = "cards.belote.beans.DeclaringPlayerValue";
    private static final String TYPE_SUM_DECLARING_PLAYER = "cards.belote.beans.SumDeclaringPlayer";
    private static final String TYPE_DETAILS_RESULTS_BELOTE_BEAN = "cards.belote.beans.DetailsResultsBeloteBean";
    private static final String TYPE_BELOTE_BEAN = "cards.belote.beans.BeloteBean";
    private ResultsBelote dataBase;
    private RulesBelote dataBaseRules;
    public BeloteStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new SpecialNatClass(TYPE_BELOTE_BEAN, fields_, methods_, TYPE_BEAN);
        StringList params_;
        params_ = new StringList();
        method_ = new SpecNatMethod(PLAY_GAME, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NICKNAMES, params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_SCORES, params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_DETAILS_RESULTS_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_DETAILS_RESULTS_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_SUM_DECLARING_PLAYER, fields_, methods_, getAliasObject());
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false));
        fields_.add( new StandardField(SUM, getPrimInt(), false, false));
        fields_.add( new StandardField(NICKNAME, getAliasString(), false, false));
        fields_.add( new StandardField(STATUT, getAliasString(), false, false));
        getStds().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_DECLARING_PLAYER_VALUE, fields_, methods_, getAliasObject());
        fields_.add( new StandardField(DECLARING, getAliasString(), false, false));
        fields_.add( new StandardField(VALUE, getPrimInt(), false, false));
        getStds().addEntry(TYPE_DECLARING_PLAYER_VALUE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RESULTS_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(WIN, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(EQUALITY, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(LOOSE, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SUCCESSFUL_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(MID_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(FAILED_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SLAM, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(ABSOLUTE_DIFF, params_, getPrimInt(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        fields_.add( new StandardField(POINTS_ATTAQUE_SANS_PRIME, getPrimInt(), false, false));
        fields_.add( new StandardField(POINTS_ATTAQUE_TEMPORAIRE, getPrimInt(), false, false));
        fields_.add( new StandardField(POINTS_ATTAQUE_DEFINITIF, getPrimInt(), false, false));
        fields_.add( new StandardField(POINTS_DEFENSE_SANS_PRIME, getPrimInt(), false, false));
        fields_.add( new StandardField(POINTS_DEFENSE_TEMPORAIRE, getPrimInt(), false, false));
        fields_.add( new StandardField(POINTS_DEFENSE_DEFINITIF, getPrimInt(), false, false));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, getPrimInt(), false, false));
        fields_.add( new StandardField(TAKER_NICKNAME, getAliasString(), false, false));
        fields_.add( new StandardField(BID_STRING, getAliasString(), false, false));
        fields_.add( new StandardField(CALLED_PLAYERS_LIST, TYPE_LIST, false, false));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_RESULTS_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, getAliasObject());
        fields_.add( new StandardField(NUMBER, getPrimInt(), false, false));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RULES_BELOTE_BEAN, fields_, methods_, TYPE_BELOTE_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, getAliasString(), false, false));
        fields_.add( new StandardField(DEAL_ALL, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(ANNONCES_AUTORISEES, TYPE_LIST, false, false));
        fields_.add( new StandardField(ENCHERES_AUTORISEES, TYPE_LIST, false, false));
        fields_.add( new StandardField(SOUS_COUPE_ADV, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(GESTION_COUPE_PARTENAIRE, getAliasString(), false, false));
        fields_.add( new StandardField(REPARTITION, getAliasString(), false, false));
        fields_.add( new StandardField(COMPTE_POINTS_CLASSIQUE, getAliasPrimBoolean(), false, false));
        getStds().addEntry(TYPE_RULES_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_BID_BELOTE, fields_, methods_, getAliasObject());
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_BID_BELOTE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_DECLARES_BELOTE, fields_, methods_, getAliasObject());
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_DECLARES_BELOTE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RESULTS_BELOTE, fields_, methods_, getAliasObject());
        getStds().addEntry(TYPE_RESULTS_BELOTE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RULES_BELOTE, fields_, methods_, getAliasObject());
        getStds().addEntry(TYPE_RULES_BELOTE, std_);
    }
    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        ((BeloteBeanStruct)_arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        _rendStack.clearPages();
        return RendBlock.getRes(rendDocumentBlock_,_conf, this, _ctx, _rendStack, _dest);
    }

    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_method.getName(), TYPE_DETAILS_RESULTS_BELOTE_BEAN)) {
            DetailsResultsBeloteBean details_ = new DetailsResultsBeloteBean();
            details_.setClassName(TYPE_DETAILS_RESULTS_BELOTE_BEAN);
            res_.setResult(new BeloteBeanStruct(details_));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RESULTS_BELOTE_BEAN)) {
            ResultsBeloteBean details_ = new ResultsBeloteBean();
            details_.setClassName(TYPE_RESULTS_BELOTE_BEAN);
            res_.setResult(new BeloteBeanStruct(details_));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RULES_BELOTE_BEAN)) {
            RulesBeloteBean details_ = new RulesBeloteBean();
            details_.setClassName(TYPE_RULES_BELOTE_BEAN);
            res_.setResult(new BeloteBeanStruct(details_));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (_instance instanceof BeloteLineDealStruct) {
            BeloteLineDeal instance_ = ((BeloteLineDealStruct)_instance).getLineDeal();
            if (StringUtil.quickEq(fieldName_, NUMBER)) {
                res_.setResult(new IntStruct(instance_.getNumber()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SCORES)) {
                res_.setResult(getLongArray(instance_.getScores()));
                return res_;
            }
            return res_;
        }
        if (_instance instanceof BeloteSumDeclaringPlayerStruct) {
            BeloteSumDeclaringPlayer instance_ = ((BeloteSumDeclaringPlayerStruct)_instance).getSumDeclaringPlayer();
            if (StringUtil.quickEq(fieldName_, DECLARING)) {
                res_.setResult(getDeclaringPlayerValueArray(instance_.getDeclaring()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SUM)) {
                res_.setResult(new IntStruct(instance_.getSum()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, STATUT)) {
                res_.setResult(new StringStruct(instance_.getStatut()));
                return res_;
            }
            return res_;
        }
        if (_instance instanceof DeclaringPlayerValueStruct) {
            DeclaringPlayerValue instance_ = ((DeclaringPlayerValueStruct)_instance).getDeclaringPlayerValue();
            if (StringUtil.quickEq(fieldName_, DECLARING)) {
                res_.setResult(new StringStruct(instance_.getDeclaring()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, VALUE)) {
                res_.setResult(new IntStruct(instance_.getValue()));
                return res_;
            }
            return res_;
        }
        if (((BeloteBeanStruct)_instance).getInstance() instanceof DetailsResultsBeloteBean) {
            if (StringUtil.quickEq(fieldName_, DECLARING)) {
                res_.setResult(getSumDeclaringPlayerArray(((DetailsResultsBeloteBean)((BeloteBeanStruct)_instance).getInstance()).getDeclaring()));
                return res_;
            }
        }
        if (((BeloteBeanStruct)_instance).getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(fieldName_, POINTS_ATTAQUE_SANS_PRIME)) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueSansPrime()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_ATTAQUE_TEMPORAIRE)) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueTemporaire()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_ATTAQUE_DEFINITIF)) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueDefinitif()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_DEFENSE_SANS_PRIME)) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseSansPrime()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_DEFENSE_TEMPORAIRE)) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseTemporaire()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_DEFENSE_DEFINITIF)) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseDefinitif()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DIFFERENCE_SCORE_TAKER)) {
                res_.setResult(new IntStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, BID_STRING)) {
                res_.setResult(new StringStruct(instance_.getBidString()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, TAKER_NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getTakerNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, CALLED_PLAYERS_LIST)) {
                res_.setResult(getStringArray(instance_.getCalledPlayersList()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, LINES_DEAL)) {
                res_.setResult(getLineDealArray(instance_.getLinesDeal()));
                return res_;
            }
        }

        if (((BeloteBeanStruct)_instance).getInstance() instanceof RulesBeloteBean) {
            RulesBeloteBean instance_ = (RulesBeloteBean) ((BeloteBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(fieldName_, CARTES_BATTUES)) {
                res_.setResult(new StringStruct(instance_.getCartesBattues()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DEAL_ALL)) {
                res_.setResult(BooleanStruct.of(instance_.isDealAll()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ENCHERES_AUTORISEES)) {
                res_.setResult(getStringArray(instance_.getEncheresAutorisees()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SOUS_COUPE_ADV)) {
                res_.setResult(BooleanStruct.of(instance_.isSousCoupeAdv()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ANNONCES_AUTORISEES)) {
                res_.setResult(getStringArray(instance_.getAnnoncesAutorisees()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, GESTION_COUPE_PARTENAIRE)) {
                res_.setResult(new StringStruct(instance_.getGestionCoupePartenaire()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, REPARTITION)) {
                res_.setResult(new StringStruct(instance_.getRepartition()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, COMPTE_POINTS_CLASSIQUE)) {
                res_.setResult(BooleanStruct.of(instance_.isComptePointsClassique()));
                return res_;
            }
        }
        return res_;
    }

    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (((BeloteBeanStruct)_instance).getInstance() instanceof BeloteBean) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), PLAY_GAME)) {
                res_.setResult(BooleanStruct.of(((BeloteBeanStruct)_instance).getInstance().playGame()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), GET_NICKNAMES)) {
                res_.setResult(getStringArray(((BeloteBeanStruct)_instance).getInstance().getNicknames()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
                res_.setResult(getLongsArray(((BeloteBeanStruct)_instance).getInstance().getScores()));
                return res_;
            }
        }
        if (((BeloteBeanStruct)_instance).getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) ((BeloteBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(_method.getConstraints().getName(), WIN)) {
                res_.setResult(BooleanStruct.of(instance_.win()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), EQUALITY)) {
                res_.setResult(BooleanStruct.of(instance_.equality()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), LOOSE)) {
                res_.setResult(BooleanStruct.of(instance_.loose()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), SUCCESSFUL_BID)) {
                res_.setResult(BooleanStruct.of(instance_.successfulBid()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), MID_BID)) {
                res_.setResult(BooleanStruct.of(instance_.midBid()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), FAILED_BID)) {
                res_.setResult(BooleanStruct.of(instance_.failedBid()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), SLAM)) {
                res_.setResult(BooleanStruct.of(instance_.slam()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), ABSOLUTE_DIFF)) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
        }
        return res_;
    }
    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        return new ResultErrorStd();
    }

    public String getPrimInt() {
        return getContent().getPrimTypes().getAliasPrimInteger();
    }
    protected Struct newSimpleBean(String _language, BeanInfo _bean, ContextEl _ctx, StackCall _stackCall) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = ExecHelper.getObjects(Argument.toArgArray(new CustList<Argument>()));
        ResultErrorStd res_ = getOtherResultBean(_ctx, id_, args_);
        Struct strBean_ = res_.getResult();
        BeloteBeanStruct str_ = (BeloteBeanStruct) strBean_;
        BeloteBean bean_ = str_.getBean();
        bean_.setDataBase(dataBase,dataBaseRules);
        bean_.setLanguage(_language);
        bean_.setScope(_bean.getScope());
        return strBean_;
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

    public void setDataBase(ResultsBelote _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesBelote _dataBase){
        dataBaseRules = _dataBase;
    }
}
