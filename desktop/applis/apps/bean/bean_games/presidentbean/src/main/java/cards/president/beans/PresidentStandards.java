package cards.president.beans;

import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.Bean;
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

public final class PresidentStandards extends BeanNatCommonLgNames {

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
        buildPresidentBean();
        buildLineDeal();
        buildRulesPresidentBean();
    }

    private void buildPresidentBean() {
        CustList<StandardField> fields_;
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        std_ = new SpecialNatClass(TYPE_PRESIDENT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, false, false, new PresidentBeanNicknames(), null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false, new PresidentBeanLinesDeal(), null));
        getStds().addEntry(TYPE_PRESIDENT_BEAN, std_);
    }

    private void buildLineDeal() {
        CustList<StandardField> fields_ = new CustList<StandardField>();
        CustList<SpecNatMethod> methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NUMBER, PRIM_INTEGER, false, false, new LineDealNumber(),null));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false, new LineDealScores(),null));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
    }

    private void buildRulesPresidentBean() {
        CustList<StandardField> fields_ = new CustList<StandardField>();
        CustList<SpecNatMethod> methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RULES_PRESIDENT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, false, false, new RulesPresidentBeanCartesBattues(),null));
        fields_.add( new StandardField(EQUALTY, STRING, false, false, new RulesPresidentBeanEqualty(),null));
        fields_.add( new StandardField(POSSIBLE_REVERSING, PRIM_BOOLEAN, false, false, new RulesPresidentBeanPossibleReversing(),null));
        fields_.add( new StandardField(HAS_TO_PLAY, PRIM_BOOLEAN, false, false, new RulesPresidentBeanHasToPlay(),null));
        fields_.add( new StandardField(LOOSING_IF_FINISH_BY_BEST_CARDS, PRIM_BOOLEAN, false, false, new RulesPresidentBeanLoosingIfFinishByBestCards(),null));
        fields_.add( new StandardField(SWITCH_CARDS, PRIM_BOOLEAN, false, false, new RulesPresidentBeanSwitchCards(),null));
        fields_.add( new StandardField(LOOSER_STARTS_FIRST, PRIM_BOOLEAN, false, false, new RulesPresidentBeanLooserStartsFirst(),null));
        fields_.add( new StandardField(NB_PLAYERS, PRIM_INTEGER, false, false, new RulesPresidentBeanNbPlayers(),null));
        fields_.add( new StandardField(NB_STACKS, PRIM_INTEGER, false, false, new RulesPresidentBeanNbStacks(),null));
        fields_.add( new StandardField(NB_CARDS_PER_PLAYER_MIN, PRIM_INTEGER, false, false, new RulesPresidentBeanNbCardsPerPlayerMin(),null));
        fields_.add( new StandardField(NB_CARDS_PER_PLAYER_MAX, PRIM_INTEGER, false, false, new RulesPresidentBeanNbCardsPerPlayerMax(),null));
        methods_.add(new SpecNatMethod(SAME_AMOUNT, PRIM_BOOLEAN, false, MethodModifier.NORMAL, new RulesPresidentBeanSameAmount()));
        getStds().addEntry(TYPE_RULES_PRESIDENT_BEAN, std_);
    }

    @Override
    public void beforeDisplaying(Struct _arg) {
        ((PresidentBeanStruct)_arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        RendDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_conf, _rendStack);
    }

    public Struct getOtherResultBean(ConstructorId _method, Struct... _args) {
        if (StringUtil.quickEq(_method.getName(), TYPE_PRESIDENT_BEAN)) {
            PresidentBean details_ = new PresidentBean();
            details_.setClassName(TYPE_PRESIDENT_BEAN);
            return (new PresidentBeanStruct(details_));
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RULES_PRESIDENT_BEAN)) {
            RulesPresidentBean details_ = new RulesPresidentBean();
            details_.setClassName(TYPE_RULES_PRESIDENT_BEAN);
            return(new PresidentBeanStruct(details_));
        }
        return NullStruct.NULL_VALUE;
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

    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        Struct strBean_ = getOtherResultBean(id_, args_);
        return update(_language, (PresidentBeanStruct) strBean_);
    }

    private PresidentBeanStruct update(String _language, PresidentBeanStruct _str) {
        Bean bean_ = _str.getBean();
        if (bean_ instanceof PresidentBean) {
            ((PresidentBean)bean_).setDataBase(dataBase);
        }
        if (bean_ instanceof RulesPresidentBean) {
            ((RulesPresidentBean)bean_).setDataBase(dataBaseRules);
        }
        bean_.setLanguage(_language);
        return _str;
    }

    public void setDataBase(ResultsPresident _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesPresident _dataBase){
        dataBaseRules = _dataBase;
    }
}
