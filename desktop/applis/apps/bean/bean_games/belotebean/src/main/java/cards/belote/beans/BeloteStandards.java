package cards.belote.beans;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.*;
import code.bean.BeanStruct;
import code.bean.RealInstanceStruct;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.DefaultInitialization;
import code.util.*;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleList;

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
    public BeloteStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        StandardClass std_;
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new CustList<StandardMethod>();
        StandardMethod method_;
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(TYPE_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BEAN, MethodModifier.ABSTRACT);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod(PLAY_GAME, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NICKNAMES, params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_SCORES, params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStandards().addEntry(TYPE_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_DETAILS_RESULTS_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BELOTE_BEAN, MethodModifier.NORMAL);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false, std_));
        getStandards().addEntry(TYPE_DETAILS_RESULTS_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_SUM_DECLARING_PLAYER, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.add( new StandardField(DECLARING, TYPE_LIST, false, false, std_));
        fields_.add( new StandardField(SUM, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.add( new StandardField(STATUT, getAliasString(), false, false, std_));
        getStandards().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_DECLARING_PLAYER_VALUE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.add( new StandardField(DECLARING, getAliasString(), false, false, std_));
        fields_.add( new StandardField(VALUE, getAliasPrimInteger(), false, false, std_));
        getStandards().addEntry(TYPE_DECLARING_PLAYER_VALUE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_RESULTS_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BELOTE_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(WIN, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(EQUALITY, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(LOOSE, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(SUCCESSFUL_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MID_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(FAILED_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(SLAM, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABSOLUTE_DIFF, params_, getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        fields_.add( new StandardField(POINTS_ATTAQUE_SANS_PRIME, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(POINTS_ATTAQUE_TEMPORAIRE, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(POINTS_ATTAQUE_DEFINITIF, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(POINTS_DEFENSE_SANS_PRIME, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(POINTS_DEFENSE_TEMPORAIRE, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(POINTS_DEFENSE_DEFINITIF, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(TAKER_NICKNAME, getAliasString(), false, false, std_));
        fields_.add( new StandardField(BID_STRING, getAliasString(), false, false, std_));
        fields_.add( new StandardField(CALLED_PLAYERS_LIST, TYPE_LIST, false, false, std_));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false, std_));
        getStandards().addEntry(TYPE_RESULTS_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_LINE_DEAL, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.add( new StandardField(NUMBER, getAliasPrimInteger(), false, false, std_));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false, std_));
        getStandards().addEntry(TYPE_LINE_DEAL, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_RULES_BELOTE_BEAN, fields_, constructors_, methods_, TYPE_BELOTE_BEAN, MethodModifier.NORMAL);
        fields_.add( new StandardField(CARTES_BATTUES, getAliasString(), false, false, std_));
        fields_.add( new StandardField(DEAL_ALL, getAliasPrimBoolean(), false, false, std_));
        fields_.add( new StandardField(ANNONCES_AUTORISEES, TYPE_LIST, false, false, std_));
        fields_.add( new StandardField(ENCHERES_AUTORISEES, TYPE_LIST, false, false, std_));
        fields_.add( new StandardField(SOUS_COUPE_ADV, getAliasPrimBoolean(), false, false, std_));
        fields_.add( new StandardField(GESTION_COUPE_PARTENAIRE, getAliasString(), false, false, std_));
        fields_.add( new StandardField(REPARTITION, getAliasString(), false, false, std_));
        fields_.add( new StandardField(COMPTE_POINTS_CLASSIQUE, getAliasPrimBoolean(), false, false, std_));
        getStandards().addEntry(TYPE_RULES_BELOTE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_BID_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStandards().addEntry(TYPE_BID_BELOTE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_DECLARES_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStandards().addEntry(TYPE_DECLARES_BELOTE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_RESULTS_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().addEntry(TYPE_RESULTS_BELOTE, std_);
        fields_ = new CustList<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_RULES_BELOTE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().addEntry(TYPE_RULES_BELOTE, std_);
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
                res_.setResult(new DefaultStruct(((DetailsResultsBeloteBean)((RealInstanceStruct)_instance).getInstance()).getDeclaring(), TYPE_LIST));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof SumDeclaringPlayer) {
            if (StringList.quickEq(fieldName_, DECLARING)) {
                res_.setResult(new DefaultStruct(((SumDeclaringPlayer)((RealInstanceStruct)_instance).getInstance()).getDeclaring(), TYPE_LIST));
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
                res_.setResult(new DefaultStruct(instance_.getCalledPlayersList(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, LINES_DEAL)) {
                res_.setResult(new DefaultStruct(instance_.getLinesDeal(), TYPE_LIST));
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
                res_.setResult(DefaultStruct.newListLong(instance_.getScores(), TYPE_LIST));
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
                res_.setResult(BooleanStruct.of(instance_.isDealAll()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ENCHERES_AUTORISEES)) {
                res_.setResult(new DefaultStruct(instance_.getEncheresAutorisees(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SOUS_COUPE_ADV)) {
                res_.setResult(BooleanStruct.of(instance_.isSousCoupeAdv()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ANNONCES_AUTORISEES)) {
                res_.setResult(new DefaultStruct(instance_.getAnnoncesAutorisees(), TYPE_LIST));
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
                res_.setResult(BooleanStruct.of(instance_.isComptePointsClassique()));
                return res_;
            }
        }
        return res_;
    }

    private String getOtherBeanStructClassName(Object _struct) {
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
        if (_struct instanceof Bean) {
            return ((Bean)_struct).getClassName();
        }
        if (_struct instanceof SimpleList) {
            return TYPE_LIST;
        }
        if (_struct instanceof SimpleEntries) {
            return TYPE_MAP;
        }
        if (_struct instanceof SimpleEntry) {
            return TYPE_ENTRY;
        }
        if (_struct instanceof SimpleItr) {
            return TYPE_ITERATOR;
        }
        return getAliasObject();
    }

    @Override
    public Struct wrapStd(Object _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        if (_element instanceof Byte) {
            return new ByteStruct((Byte) _element);
        }
        if (_element instanceof Short) {
            return new ShortStruct((Short) _element);
        }
        if (_element instanceof Character) {
            return new CharStruct((Character) _element);
        }
        if (_element instanceof Integer) {
            return new IntStruct((Integer) _element);
        }
        if (_element instanceof Long) {
            return new LongStruct((Long) _element);
        }
        if (_element instanceof Float) {
            return new FloatStruct((Float) _element);
        }
        if (_element instanceof Double) {
            return new DoubleStruct((Double) _element);
        }
        if (_element instanceof Boolean) {
            return BooleanStruct.of((Boolean) _element);
        }
        if (_element instanceof String) {
            return new StringStruct((String) _element);
        }
        if (_element instanceof StringBuilder) {
            return new StringBuilderStruct((StringBuilder) _element);
        }
        String className_ = getOtherBeanStructClassName(_element);
        return DefaultStruct.newInstance(_element, className_);
    }

    @Override
    protected Struct newId(Object _obj, String _className) {
        return DefaultStruct.newInstance(_obj, _className);
    }

    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (((RealInstanceStruct)_instance).getInstance() instanceof BeloteBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_GAME)) {
                res_.setResult(BooleanStruct.of(((BeloteBean)((RealInstanceStruct)_instance).getInstance()).playGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_NICKNAMES)) {
                res_.setResult(new DefaultStruct(((BeloteBean)((RealInstanceStruct)_instance).getInstance()).getNicknames(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
                res_.setResult(new DefaultStruct(((BeloteBean)((RealInstanceStruct)_instance).getInstance()).getScores(), TYPE_LIST));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), WIN)) {
                res_.setResult(BooleanStruct.of(instance_.win()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), EQUALITY)) {
                res_.setResult(BooleanStruct.of(instance_.equality()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), LOOSE)) {
                res_.setResult(BooleanStruct.of(instance_.loose()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SUCCESSFUL_BID)) {
                res_.setResult(BooleanStruct.of(instance_.successfulBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), MID_BID)) {
                res_.setResult(BooleanStruct.of(instance_.midBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), FAILED_BID)) {
                res_.setResult(BooleanStruct.of(instance_.failedBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SLAM)) {
                res_.setResult(BooleanStruct.of(instance_.slam()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), ABSOLUTE_DIFF)) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
        }
        return res_;
    }
    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        return new ResultErrorStd();
    }

}
