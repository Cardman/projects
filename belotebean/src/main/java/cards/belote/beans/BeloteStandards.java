package cards.belote.beans;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.formathtml.util.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanStruct;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class BeloteStandards extends BeanLgNames {

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
    public BeloteStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        StringMap<StandardField> fields_;
        fields_ = new StringMap<StandardField>();
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(TYPE_BELOTE_BEAN, fields_, constructors_, methods_, getBean(), MethodModifier.ABSTRACT);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod(PLAY_GAME, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NICKNAMES, params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_SCORES, params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        getStandards().put(TYPE_BELOTE_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_DETAILS_RESULTS_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BELOTE_BEAN, MethodModifier.NORMAL);
        fields_.put(DECLARING, new StandardField(DECLARING, getCustList(), false, false, std_));
        getStandards().put(TYPE_DETAILS_RESULTS_BELOTE_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_SUM_DECLARING_PLAYER, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(DECLARING, new StandardField(DECLARING, getCustList(), false, false, std_));
        fields_.put(SUM, new StandardField(SUM, getAliasPrimInteger(), false, false, std_));
        fields_.put(NICKNAME, new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.put(STATUT, new StandardField(STATUT, getAliasString(), false, false, std_));
        getStandards().put(TYPE_SUM_DECLARING_PLAYER, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_DECLARING_PLAYER_VALUE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(DECLARING, new StandardField(DECLARING, getAliasString(), false, false, std_));
        fields_.put(VALUE, new StandardField(VALUE, getAliasPrimInteger(), false, false, std_));
        getStandards().put(TYPE_DECLARING_PLAYER_VALUE, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RESULTS_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BELOTE_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(WIN, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(EQUALITY, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(LOOSE, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SUCCESSFUL_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MID_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FAILED_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SLAM, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABSOLUTE_DIFF, params_, getAliasPrimInteger(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        fields_.put(POINTS_ATTAQUE_SANS_PRIME, new StandardField(POINTS_ATTAQUE_SANS_PRIME, getAliasPrimInteger(), false, false, std_));
        fields_.put(POINTS_ATTAQUE_TEMPORAIRE, new StandardField(POINTS_ATTAQUE_TEMPORAIRE, getAliasPrimInteger(), false, false, std_));
        fields_.put(POINTS_ATTAQUE_DEFINITIF, new StandardField(POINTS_ATTAQUE_DEFINITIF, getAliasPrimInteger(), false, false, std_));
        fields_.put(POINTS_DEFENSE_SANS_PRIME, new StandardField(POINTS_DEFENSE_SANS_PRIME, getAliasPrimInteger(), false, false, std_));
        fields_.put(POINTS_DEFENSE_TEMPORAIRE, new StandardField(POINTS_DEFENSE_TEMPORAIRE, getAliasPrimInteger(), false, false, std_));
        fields_.put(POINTS_DEFENSE_DEFINITIF, new StandardField(POINTS_DEFENSE_DEFINITIF, getAliasPrimInteger(), false, false, std_));
        fields_.put(DIFFERENCE_SCORE_TAKER, new StandardField(DIFFERENCE_SCORE_TAKER, getAliasPrimInteger(), false, false, std_));
        fields_.put(TAKER_NICKNAME, new StandardField(TAKER_NICKNAME, getAliasString(), false, false, std_));
        fields_.put(BID_STRING, new StandardField(BID_STRING, getAliasString(), false, false, std_));
        fields_.put(CALLED_PLAYERS_LIST, new StandardField(CALLED_PLAYERS_LIST, getCustList(), false, false, std_));
        fields_.put(LINES_DEAL, new StandardField(LINES_DEAL, getCustList(), false, false, std_));
        getStandards().put(TYPE_RESULTS_BELOTE_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_LINE_DEAL, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NUMBER, new StandardField(NUMBER, getAliasPrimInteger(), false, false, std_));
        fields_.put(SCORES, new StandardField(SCORES, getCustList(), false, false, std_));
        getStandards().put(TYPE_LINE_DEAL, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RULES_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BELOTE_BEAN, MethodModifier.NORMAL);
        fields_.put(CARTES_BATTUES, new StandardField(CARTES_BATTUES, getAliasString(), false, false, std_));
        fields_.put(DEAL_ALL, new StandardField(DEAL_ALL, getAliasPrimBoolean(), false, false, std_));
        fields_.put(ANNONCES_AUTORISEES, new StandardField(ANNONCES_AUTORISEES, getCustList(), false, false, std_));
        fields_.put(ENCHERES_AUTORISEES, new StandardField(ENCHERES_AUTORISEES, getCustList(), false, false, std_));
        fields_.put(SOUS_COUPE_ADV, new StandardField(SOUS_COUPE_ADV, getAliasPrimBoolean(), false, false, std_));
        fields_.put(GESTION_COUPE_PARTENAIRE, new StandardField(GESTION_COUPE_PARTENAIRE, getAliasString(), false, false, std_));
        fields_.put(REPARTITION, new StandardField(REPARTITION, getAliasString(), false, false, std_));
        fields_.put(COMPTE_POINTS_CLASSIQUE, new StandardField(COMPTE_POINTS_CLASSIQUE, getAliasPrimBoolean(), false, false, std_));
        getStandards().put(TYPE_RULES_BELOTE_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_BID_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put(TYPE_BID_BELOTE, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_DECLARES_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put(TYPE_DECLARES_BELOTE, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RESULTS_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(TYPE_RESULTS_BELOTE, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RULES_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(TYPE_RULES_BELOTE, std_);
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), TYPE_DETAILS_RESULTS_BELOTE_BEAN)) {
            DetailsResultsBeloteBean details_ = new DetailsResultsBeloteBean();
            details_.setClassName(TYPE_DETAILS_RESULTS_BELOTE_BEAN);
            res_.setResult(new BeanStruct(details_));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), TYPE_RESULTS_BELOTE_BEAN)) {
            ResultsBeloteBean details_ = new ResultsBeloteBean();
            details_.setClassName(TYPE_RESULTS_BELOTE_BEAN);
            res_.setResult(new BeanStruct(details_));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), TYPE_RULES_BELOTE_BEAN)) {
            RulesBeloteBean details_ = new RulesBeloteBean();
            details_.setClassName(TYPE_RULES_BELOTE_BEAN);
            res_.setResult(new BeanStruct(details_));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (((RealInstanceStruct)_instance).getInstance() instanceof DetailsResultsBeloteBean) {
            if (StringList.quickEq(fieldName_, DECLARING)) {
                res_.setResult(new StdStruct(((DetailsResultsBeloteBean)((RealInstanceStruct)_instance).getInstance()).getDeclaring(), getCustList()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof SumDeclaringPlayer) {
            if (StringList.quickEq(fieldName_, DECLARING)) {
                res_.setResult(new StdStruct(((SumDeclaringPlayer)((RealInstanceStruct)_instance).getInstance()).getDeclaring(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SUM)) {
                res_.setResult(new IntStruct(((SumDeclaringPlayer)((RealInstanceStruct)_instance).getInstance()).getSum()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(((SumDeclaringPlayer)((RealInstanceStruct)_instance).getInstance()).getNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, STATUT)) {
                res_.setResult(new StringStruct(((SumDeclaringPlayer)((RealInstanceStruct)_instance).getInstance()).getStatut()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof DeclaringPlayerValue) {
            if (StringList.quickEq(fieldName_, DECLARING)) {
                res_.setResult(new StringStruct(((DeclaringPlayerValue)((RealInstanceStruct)_instance).getInstance()).getDeclaring()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, VALUE)) {
                res_.setResult(new IntStruct(((DeclaringPlayerValue)((RealInstanceStruct)_instance).getInstance()).getValue()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, POINTS_ATTAQUE_SANS_PRIME)) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueSansPrime()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_ATTAQUE_TEMPORAIRE)) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueTemporaire()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_ATTAQUE_DEFINITIF)) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueDefinitif()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_DEFENSE_SANS_PRIME)) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseSansPrime()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_DEFENSE_TEMPORAIRE)) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseTemporaire()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_DEFENSE_DEFINITIF)) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseDefinitif()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DIFFERENCE_SCORE_TAKER)) {
                res_.setResult(new IntStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, BID_STRING)) {
                res_.setResult(new StringStruct(instance_.getBidString()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, TAKER_NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getTakerNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, CALLED_PLAYERS_LIST)) {
                res_.setResult(new StdStruct(instance_.getCalledPlayersList(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, LINES_DEAL)) {
                res_.setResult(new StdStruct(instance_.getLinesDeal(), getCustList()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof LineDeal) {
            LineDeal instance_ = (LineDeal) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, NUMBER)) {
                res_.setResult(new IntStruct(instance_.getNumber()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SCORES)) {
                res_.setResult(StdStruct.newListLong(instance_.getScores(), getCustList()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof RulesBeloteBean) {
            RulesBeloteBean instance_ = (RulesBeloteBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, CARTES_BATTUES)) {
                res_.setResult(new StringStruct(instance_.getCartesBattues()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DEAL_ALL)) {
                res_.setResult(new BooleanStruct(instance_.isDealAll()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ENCHERES_AUTORISEES)) {
                res_.setResult(new StdStruct(instance_.getEncheresAutorisees(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SOUS_COUPE_ADV)) {
                res_.setResult(new BooleanStruct(instance_.isSousCoupeAdv()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ANNONCES_AUTORISEES)) {
                res_.setResult(new StdStruct(instance_.getAnnoncesAutorisees(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, GESTION_COUPE_PARTENAIRE)) {
                res_.setResult(new StringStruct(instance_.getGestionCoupePartenaire()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, REPARTITION)) {
                res_.setResult(new StringStruct(instance_.getRepartition()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, COMPTE_POINTS_CLASSIQUE)) {
                res_.setResult(new BooleanStruct(instance_.isComptePointsClassique()));
                return res_;
            }
        }
        return res_;
    }
    @Override
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof DeclaringPlayerValue) {
            return TYPE_DECLARING_PLAYER_VALUE;
        }
        if (_struct instanceof SumDeclaringPlayer) {
            return TYPE_SUM_DECLARING_PLAYER;
        }
        if (_struct instanceof LineDeal) {
            return TYPE_LINE_DEAL;
        }
        if (_struct instanceof DeclaresBelote) {
            return TYPE_DECLARES_BELOTE;
        }
        if (_struct instanceof BidBelote) {
            return TYPE_BID_BELOTE;
        }
        return getAliasObject();
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (((RealInstanceStruct)_instance).getInstance() instanceof BeloteBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_GAME)) {
                res_.setResult(new BooleanStruct(((BeloteBean)((RealInstanceStruct)_instance).getInstance()).playGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_NICKNAMES)) {
                res_.setResult(new StdStruct(((BeloteBean)((RealInstanceStruct)_instance).getInstance()).getNicknames(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
                res_.setResult(new StdStruct(((BeloteBean)((RealInstanceStruct)_instance).getInstance()).getScores(), getCustList()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), WIN)) {
                res_.setResult(new BooleanStruct(instance_.win()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), EQUALITY)) {
                res_.setResult(new BooleanStruct(instance_.equality()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), LOOSE)) {
                res_.setResult(new BooleanStruct(instance_.loose()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SUCCESSFUL_BID)) {
                res_.setResult(new BooleanStruct(instance_.successfulBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), MID_BID)) {
                res_.setResult(new BooleanStruct(instance_.midBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), FAILED_BID)) {
                res_.setResult(new BooleanStruct(instance_.failedBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SLAM)) {
                res_.setResult(new BooleanStruct(instance_.slam()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), ABSOLUTE_DIFF)) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
        }
        return res_;
    }
}
