package cards.president.beans;

import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.Bean;
import code.bean.nat.*;
import code.bean.nat.exec.opers.NatStdFctOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.util.*;
import code.util.core.StringUtil;

public final class PresidentStandards extends BeanNatLgNames {

    private static final String SAME_AMOUNT = "sameAmount";
    private static final String NB_CARDS_PER_PLAYER_MAX = "nbCardsPerPlayerMax";
    private static final String NB_CARDS_PER_PLAYER_MIN = "nbCardsPerPlayerMin";
    private static final String NB_STACKS = "nbStacks";
    private static final String NB_PLAYERS = "nbPlayers";
    private static final String LOOSER_STARTS_FIRST = "looserStartsFirst";
    private static final String SWITCH_CARDS = "switchCards";
    private static final String LOOSING_IF_FINISH_BY_BEST_CARDS = "loosingIfFinishByBestCards";
    private static final String HAS_TO_PLAY = "hasToPlay";
    private static final String POSSIBLE_REVERSING = "possibleReversing";
    private static final String EQUALTY = "equalty";
    private static final String CARTES_BATTUES = "cartesBattues";
    private static final String SCORES = "scores";
    private static final String NUMBER = "number";
    private static final String LINES_DEAL = "linesDeal";
    private static final String NICKNAMES = "nicknames";
    private static final String TYPE_RULES_PRESIDENT = "RulesPresident";
    private static final String TYPE_RESULTS_PRESIDENT = "ResultsPresident";
    private static final String TYPE_RULES_PRESIDENT_BEAN = "cards.president.beans.RulesPresidentBean";
    private static final String TYPE_LINE_DEAL = "cards.president.beans.LineDeal";
    private static final String TYPE_PRESIDENT_BEAN = "cards.president.beans.PresidentBean";
    private RulesPresident dataBaseRules;
    private ResultsPresident dataBase;
    public PresidentStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new SpecialNatClass(TYPE_PRESIDENT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, false, false));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_PRESIDENT_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, getAliasObject());
        fields_.add( new StandardField(NUMBER, getPrimInt(), false, false));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RULES_PRESIDENT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, getAliasString(), false, false));
        fields_.add( new StandardField(EQUALTY, getAliasString(), false, false));
        fields_.add( new StandardField(POSSIBLE_REVERSING, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(HAS_TO_PLAY, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(LOOSING_IF_FINISH_BY_BEST_CARDS, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(SWITCH_CARDS, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(LOOSER_STARTS_FIRST, getAliasPrimBoolean(), false, false));
        fields_.add( new StandardField(NB_PLAYERS, getPrimInt(), false, false));
        fields_.add( new StandardField(NB_STACKS, getPrimInt(), false, false));
        fields_.add( new StandardField(NB_CARDS_PER_PLAYER_MIN, getPrimInt(), false, false));
        fields_.add( new StandardField(NB_CARDS_PER_PLAYER_MAX, getPrimInt(), false, false));
        params_ = new StringList();
        method_ = new SpecNatMethod(SAME_AMOUNT, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add(method_);
        getStds().addEntry(TYPE_RULES_PRESIDENT_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RESULTS_PRESIDENT, fields_, methods_, getAliasObject());
        getStds().addEntry(TYPE_RESULTS_PRESIDENT, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RULES_PRESIDENT, fields_, methods_, getAliasObject());
        getStds().addEntry(TYPE_RULES_PRESIDENT, std_);
    }

    @Override
    public void beforeDisplaying(Struct _arg) {
        ((PresidentBeanStruct)_arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        _rendStack.clearPages();
        return BeanNatCommonLgNames.getRes(rendDocumentBlock_,_conf, this, _ctx, _rendStack);
    }

    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_method.getName(), TYPE_PRESIDENT_BEAN)) {
            PresidentBean details_ = new PresidentBean();
            details_.setClassName(TYPE_PRESIDENT_BEAN);
            res_.setResult(new PresidentBeanStruct(details_));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RULES_PRESIDENT_BEAN)) {
            RulesPresidentBean details_ = new RulesPresidentBean();
            details_.setClassName(TYPE_RULES_PRESIDENT_BEAN);
            res_.setResult(new PresidentBeanStruct(details_));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (_instance instanceof PresidentLineDealStruct) {
            PresidentLineDeal instance_ = ((PresidentLineDealStruct)_instance).getLineDeal();
            if (StringUtil.quickEq(fieldName_, SCORES)) {
                res_.setResult(getLongArray(instance_.getScores()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NUMBER)) {
                res_.setResult(new IntStruct(instance_.getNumber()));
                return res_;
            }
            return res_;
        }
        if (((PresidentBeanStruct)_instance).getInstance() instanceof PresidentBean) {
            if (StringUtil.quickEq(fieldName_, NICKNAMES)) {
                res_.setResult(getStringArray(((PresidentBean)((PresidentBeanStruct)_instance).getInstance()).getNicknames()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, LINES_DEAL)) {
                res_.setResult(getLineDealArray(((PresidentBean)((PresidentBeanStruct)_instance).getInstance()).getLinesDeal()));
                return res_;
            }
        }
        if (((PresidentBeanStruct)_instance).getInstance() instanceof RulesPresidentBean) {
            RulesPresidentBean rules_ = (RulesPresidentBean) ((PresidentBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(fieldName_, NB_PLAYERS)) {
                res_.setResult(new IntStruct(rules_.getNbPlayers()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NB_STACKS)) {
                res_.setResult(new IntStruct(rules_.getNbStacks()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NB_CARDS_PER_PLAYER_MIN)) {
                res_.setResult(new IntStruct(rules_.getNbCardsPerPlayerMin()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NB_CARDS_PER_PLAYER_MAX)) {
                res_.setResult(new IntStruct(rules_.getNbCardsPerPlayerMax()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, CARTES_BATTUES)) {
                res_.setResult(new StringStruct(rules_.getCartesBattues()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, EQUALTY)) {
                res_.setResult(new StringStruct(rules_.getEqualty()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POSSIBLE_REVERSING)) {
                res_.setResult(BooleanStruct.of(rules_.isPossibleReversing()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, HAS_TO_PLAY)) {
                res_.setResult(BooleanStruct.of(rules_.isHasToPlay()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, LOOSING_IF_FINISH_BY_BEST_CARDS)) {
                res_.setResult(BooleanStruct.of(rules_.isLoosingIfFinishByBestCards()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SWITCH_CARDS)) {
                res_.setResult(BooleanStruct.of(rules_.isSwitchCards()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, LOOSER_STARTS_FIRST)) {
                res_.setResult(BooleanStruct.of(rules_.isLooserStartsFirst()));
                return res_;
            }
        }
        return res_;
    }

    public static ArrayStruct getLineDealArray(CustList<PresidentLineDeal> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LINE_DEAL));
        int j_ = 0;
        for (PresidentLineDeal s:_ls) {
            arr_.set(j_,new PresidentLineDealStruct(s, TYPE_LINE_DEAL));
            j_++;
        }
        return arr_;
    }

    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (((PresidentBeanStruct)_instance).getInstance() instanceof RulesPresidentBean) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), SAME_AMOUNT)) {
                res_.setResult(BooleanStruct.of(((RulesPresidentBean)((PresidentBeanStruct)_instance).getInstance()).sameAmount()));
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
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        ResultErrorStd res_ = getOtherResultBean(_ctx, id_, args_);
        Struct strBean_ = res_.getResult();
        PresidentBeanStruct str_ = (PresidentBeanStruct) strBean_;
        Bean bean_ = str_.getBean();
        if (bean_ instanceof PresidentBean) {
            ((PresidentBean)bean_).setDataBase(dataBase);
        }
        if (bean_ instanceof RulesPresidentBean) {
            ((RulesPresidentBean)bean_).setDataBase(dataBaseRules);
        }
        bean_.setLanguage(_language);
        bean_.setScope(_bean.getScope());
        return strBean_;
    }
    public void setDataBase(ResultsPresident _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesPresident _dataBase){
        dataBaseRules = _dataBase;
    }
}
