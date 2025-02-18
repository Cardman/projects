package aiki.beans;

import aiki.beans.abilities.*;
import aiki.beans.facade.dto.*;
import aiki.beans.facade.map.dto.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.solution.dto.*;
import aiki.beans.game.*;
import aiki.beans.map.elements.*;
import aiki.beans.pokemon.LevelMoveTranslatedKey;
import aiki.beans.pokemon.StringStatBaseEv;
import aiki.comparators.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.*;
import aiki.fight.enums.*;
import aiki.fight.status.effects.*;
import aiki.fight.util.*;
import aiki.game.params.enums.*;
import aiki.map.levels.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.util.*;
import code.bean.nat.*;
import code.bean.nat.analyze.*;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.opers.*;
import code.bean.nat.exec.variables.*;
import code.bean.nat.fwd.*;
import code.maths.*;
import code.sml.*;
import code.util.*;
import code.util.core.*;
public abstract class PokemonStandards extends BeanNatCommonLgNames implements BeanNatCommonLgNamesForm {

    public static final String ON = SetupableAnalyzingDoc.ON;

    public static final String EMPTY_STRING = "";

    public static final String TYPE_TYPE_DAMAGE_BOOST = "aiki.fight.util.TypeDamageBoost";
    public static final String TYPE_EFFICIENCY_RATE = "aiki.fight.util.EfficiencyRate";
    public static final String TYPE_BOOST_HP_RATE = "aiki.fight.util.BoostHpRate";
    public static final String TYPE_PK_TRAINER = "aiki.map.pokemon.PkTrainer";
    public static final String TYPE_POKEMON = "aiki.map.pokemon.Pokemon";
    public static final String TYPE_AREA_APPARITION = "aiki.map.levels.AreaApparition";
    public static final String TYPE_WILD_PK = "aiki.map.pokemon.WildPk";
    public static final String TYPE_PLACE = "aiki.map.places.Place";
    public static final String TYPE_TYPES_DUO = "aiki.fight.util.TypesDuo";
    public static final String TYPE_CATEGORY_MULT = "aiki.fight.util.CategoryMult";
    public static final String TYPE_LEVEL_MOVE = "aiki.fight.util.LevelMove";
    public static final String TYPE_POKEMON_PLAYER = "aiki.map.pokemon.PokemonPlayer";
    public static final String TYPE_EFFECT_PARTNER_STATUS = "aiki.fight.status.effects.EffectPartnerStatus";
    public static final String TYPE_EFFECT_WHILE_SENDING = "aiki.fight.effects.EffectWhileSending";
    public static final String TYPE_ALLY = "aiki.map.characters.Ally";
    public static final String TYPE_TEMP_TRAINER = "aiki.map.characters.TempTrainer";
//    public static final String TYPE_TRAINER_ONE_FIGHT = "aiki.map.characters.TrainerOneFight";
    public static final String TYPE_TRAINER = "aiki.map.characters.Trainer";
//    public static final String TYPE_PERSON = "aiki.map.characters.Person";
//    public static final String TYPE_FULL_RATE_VALIDATOR = "aiki.beans.validators.RateValidator";
//    public static final String TYPE_FULL_POSITIVE_RATE_VALIDATOR = "aiki.beans.validators.PositiveRateValidator";
//    public static final String TYPE_FULL_SHORT_VALIDATOR = "aiki.beans.validators.ShortValidator";
//    public static final String TYPE_FULL_UNSELECTED_RADIO = "aiki.beans.validators.UnselectedRadio";
//    public static final String TYPE_RATE_VALIDATOR = "RateValidator";
//    public static final String TYPE_POSITIVE_RATE_VALIDATOR = "PositiveRateValidator";
//    public static final String TYPE_SHORT_VALIDATOR = "ShortValidator";
//    public static final String TYPE_UNSELECTED_RADIO = "UnselectedRadio";
    private static final String IS_ZERO = "isZero";
    private static final String IS_ZERO_OR_GT = "isZeroOrGt";
    private static final String ABS_NB = "absNb";

    private static final String REF_TAG = "#";


    private FacadeGame dataBase;
//    private String baseEncode;

//    private final StringMap<Validator> validators = new StringMap<Validator>();
    private NatHtmlPage natPage;

    protected PokemonStandards(){
//        getValidators().addEntry("positive_rate_validator",new PositiveRateValidator());
//        getValidators().addEntry("rate_validator",new RateValidator());
//        getValidators().addEntry("short_validator",new ShortValidator());
//        getValidators().addEntry("selected_radio",new UnselectedRadio());
    }
    @Override
    public void buildOther() {
        buildAddon();
        buildRate(this);
        buildLgInt(this);
//        buildRateValidator(this);
//        buildPositiveRateValidator(this);
//        buildShortValidator(this);
//        buildUnselectedRadio(this);
    }
    protected abstract void buildAddon();
    private static void buildRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(IS_ZERO,BeanNatCommonLgNames.PRIM_BOOLEAN, new RateIsZero()));
        methods_.add( new SpecNatMethod(IS_ZERO_OR_GT,BeanNatCommonLgNames.PRIM_BOOLEAN, new RateIsZeroOrGt()));
        methods_.add( new SpecNatMethod(ABS_NB,BeanNatCommonLgNames.TYPE_RATE, new RateAbsNb()));
        _std.getStds().addEntry(TYPE_RATE, type_);
    }
    private static void buildLgInt(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_LG_INT, type_);
    }
//    private static void buildRateValidator(PokemonStandards _std){
//        CustList<StandardField> fields_=new CustList<StandardField>();
//        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
//        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, TYPE_VALIDATOR);
//        _std.getStds().addEntry(TYPE_RATE_VALIDATOR, type_);
//    }
//    private static void buildPositiveRateValidator(PokemonStandards _std){
//        CustList<StandardField> fields_=new CustList<StandardField>();
//        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
//        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, TYPE_VALIDATOR);
//        _std.getStds().addEntry(TYPE_POSITIVE_RATE_VALIDATOR, type_);
//    }
//    private static void buildShortValidator(PokemonStandards _std){
//        CustList<StandardField> fields_=new CustList<StandardField>();
//        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
//        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, TYPE_VALIDATOR);
//        _std.getStds().addEntry(TYPE_SHORT_VALIDATOR, type_);
//    }
//    private static void buildUnselectedRadio(PokemonStandards _std){
//        CustList<StandardField> fields_=new CustList<StandardField>();
//        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
//        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, TYPE_VALIDATOR);
//        _std.getStds().addEntry(TYPE_UNSELECTED_RADIO, type_);
//    }

    @Override
    public HtmlPageInt getPage() {
        return getNatPage();
    }


    public NatHtmlPage getNatPage() {
        return natPage;
    }

    public void setNatPage(NatHtmlPage _nat) {
        natPage = _nat;
    }
    public void processRendFormRequest(NatNavigation _nav) {
        NatRendStackCallAdv st_ = new NatRendStackCallAdv();
        st_.setCurrent(this);
        st_.clearPages();
        st_.setDocument(_nav.getDocument());
        NatImportingPageAbs ip_ = new NatImportingPageForm();
        st_.addPage(ip_);
//        long lg_ = natPage.getUrl();
//        Document doc_ = _nav.getDocument();
        //retrieving form that is submitted
        natPage.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop

//        StringMap<Message> map_ = validateAll(natPage);
//        StringMap<String> errors_ = new StringMap<String>();
//        StringMap<StringList> errorsArgs_ = new StringMap<StringList>();
//        _nav.feedErr(map_, errors_, errorsArgs_);
        //begin deleting previous errors
//        _nav.delPrevious(doc_, _elt);
        //end deleting previous errors
//        if (!errors_.isEmpty()) {
//            _nav.processRendFormErrors(_elt, lg_, errors_, errorsArgs_, getPage());
//            st_.clearPages();
//            return;
//        }
        //Setting values for bean
        updateRendBean(natPage);
        st_.clearPages();

        //invoke application
        processRendAnchorRequest(_nav, st_);
    }

//    public StringMap<Message> validateAll(NatHtmlPage _htmlPage) {
//        LongMap<LongTreeMap<NatNodeContainer>> containersMap_;
//        containersMap_ = _htmlPage.getContainers();
//        long lg_ = _htmlPage.getUrl();
//        StringMap<Message> map_ = new StringMap<Message>();
//        for (EntryCust<Long, NatNodeContainer> e: containersMap_.getVal(lg_).entryList()) {
//            NodeContainer nCont_ = e.getValue();
//            NodeInformations nInfos_ = nCont_.getNodeInformation();
//            String valId_ = nInfos_.getValidator();
//            String id_ = nInfos_.getId();
//            Message messageTr_ = validate(nCont_,valId_);
//            if (messageTr_ != null) {
//                map_.put(id_, messageTr_);
//            }
//        }
//        return map_;
//    }

    public static void updateRendBean(NatHtmlPage _htmlPage) {
        LongMap<LongTreeMap<NatNodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        LongTreeMap< NatNodeContainer> containers_ = containersMap_.getVal(lg_);
        for (EntryCust<Long, NatNodeContainer> e: containers_.entryList()) {
            NatNodeContainer nCont_ = e.getValue();
            if (!nCont_.getNodeInformation().isEnabled()) {
                continue;
            }
            NaSt res_ = convert(nCont_);
            setRendObject(e.getValue(), res_);
        }
    }


    public static NaSt convert(NatNodeContainer _container) {
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getNodeInformation().getValue();
        return getStructToBeValidated(values_, className_);
    }

    public static NaSt getStructToBeValidated(StringList _values, String _className) {
        String value_ = NavigationCore.oneElt(_values);
        if (StringUtil.quickEq(_className,TYPE_RATE)) {
            return new RtSt(RtSt.convertToRate(str(value_)));
        }
        if (StringUtil.quickEq(_className, STRING)) {
            return new NaStSt(value_);
        }
        return getStructToBeValidatedPrim(_values, _className);
    }
    private static NaSt str(String _value) {
        if (!Rate.isValid(_value)) {
            return NaNu.NULL_VALUE;
        }
        return new RtSt(new Rate(_value));
    }
    public NatDocumentBlock getRender(String _one) {
        return getRenders().getVal(StringUtil.getFirstToken(_one, REF_TAG));
    }
    public String getDest(String _one) {
        return _one;
    }

    public static void setRendObject(NatNodeContainer _nodeContainer,
                                     NaSt _attribute) {
        NaSt obj_ = _nodeContainer.getAllObject();
        NatCaller wr_ = _nodeContainer.getOpsWrite();
        wr_.re(obj_,new NaSt[]{_attribute});
    }
    public static NaSt redirect(NatHtmlPage _htmlPage, NatRendStackCall _rendStack, NaSt _gl){
        NaSt ret_;
        if (_htmlPage.isForm()) {
            int url_ = (int)_htmlPage.getUrl();
            StringList varNames_ = _htmlPage.getFormsVars().get(url_);
            CustList<NatExecOperationNode> exps_ = _htmlPage.getCallsFormExps().get(url_);
            StringList args_ = _htmlPage.getFormsArgs().get(url_);
            ret_ = redir(_gl, varNames_, exps_, args_, _rendStack);
        } else {
            int url_ = (int)_htmlPage.getUrl();
            StringList varNames_ = _htmlPage.getAnchorsVars().get(url_);
            CustList<NatExecOperationNode> exps_ = _htmlPage.getCallsExps().get(url_);
            StringList args_ = _htmlPage.getAnchorsArgs().get(url_);
            ret_= redir(_gl, varNames_, exps_, args_, _rendStack);
        }
        return ret_;
    }
    public static void setGlobalArgumentStruct(NaSt _obj, NatRendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().setGlobalArgumentStruct(_obj);
    }

    private static NaSt used(NatHtmlPage _htmlPage) {
        if (_htmlPage.isForm()) {
            int url_ = (int)_htmlPage.getUrl();
            return _htmlPage.getStructsForm().get(url_);
        } else {
            int url_ = (int)_htmlPage.getUrl();
            return _htmlPage.getStructsAnc().get(url_);
        }
    }

    public static NaSt redir(NaSt _bean, StringList _varNames, CustList<NatExecOperationNode> _exps, StringList _args, NatRendStackCall _rendStackCall) {
        NatImportingPageAbs ip_ = _rendStackCall.getLastPage();
        int s_ = _varNames.size();
        for (int i = 0; i< s_; i++) {
            ip_.putValueVar(_varNames.get(i), new VariableWrapperNat(new NaNbSt(NumberUtil.parseLongZero(_args.get(i)))));
        }
        NaSt globalArgument_ = _rendStackCall.getLastPage().getGlobalArgument();
        setGlobalArgumentStruct(_bean, _rendStackCall);
        NaSt argument_ = getAllArgs(_exps, _rendStackCall).lastValue().getArgument();
        setGlobalArgumentStruct(globalArgument_, _rendStackCall);
        for (String n: _varNames) {
            ip_.removeRefVar(n);
        }
        return argument_;
    }

//    public Message validate(NodeContainer _cont, String _validatorId) {
//        Validator validator_ = validators.getVal(_validatorId);
//        if (validator_ == null) {
//            return null;
//        }
//        StringList v_ = _cont.getValue();
//        return validator_.validate(v_);
//    }
    @Override
    public InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, NaSt _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = new NatImportingPageForm();
        _rendStack.addPage(ip_);
        StringMapObject stringMapObject_ = new StringMapObject();
        stringMapObject_.putAllMapGene(form(_bean));
        String currentBeanName_;
        NatDocumentBlock rendDocumentBlock_ = getRender(_dest);
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        NaSt bean_ = getBeanOrNull(currentBeanName_);
        setForms(stringMapObject_, bean_);
        _rendStack.clearPages();
        ((NatRendStackCallAdv)_rendStack).getFormParts().initFormsSpec();
        String res_ = getRes(rendDocumentBlock_, _conf, _rendStack,ip_);
        ((NatRendStackCallAdv)_rendStack).getHtmlPage().set(((NatRendStackCallAdv)_rendStack).getFormParts());
        setNatPage(((NatRendStackCallAdv)_rendStack).getHtmlPage());
        return new InvokedPageOutput(getDest(_dest),res_);
    }

    static StringMapObjectBase form(NaSt _bean) {
        if (_bean instanceof PokemonBeanStruct) {
            return ((PokemonBeanStruct)_bean).getForms();
        }
        return new StringMapObjectBase();
    }

    public void processRendAnchorRequest(NatNavigation _nav) {
        NatRendStackCall rendStackCall_ = new NatRendStackCallAdv();
        rendStackCall_.setCurrent(this);
        processRendAnchorRequest(_nav, rendStackCall_);
    }
    public void processRendAnchorRequest(NatNavigation _nav, NatRendStackCall _rendStack) {
//        if (_ancElt == null) {
//            return;
//        }
//        Configuration session_ = _nav.getSession();
//        String actionCommand_ = _ancElt.getAttribute(StringUtil.concat(session_.getPrefix(),session_.getRendKeyWords().getAttrCommand()));
        _rendStack.clearPages();
        NatImportingPageAbs ip_ = new NatImportingPageForm();
        _rendStack.addPage(ip_);
//        int indexPoint_ = actionCommand_.indexOf(BeanLgNames.DOT);
//        String beanName_ = actionCommand_
//                .substring(0, indexPoint_);
//        Struct bean_ = getBeanOrNull(beanName_);
//        setGlobalArgumentStruct(bean_, _rendStack);
        NaSt gl_ = used(natPage);
        NaSt return_ = redirect(natPage, _rendStack, gl_);
        //        String urlDest_ = _currentUrl;
//        if (_ret != NullStruct.NULL_VALUE) {
//            StringMap<String> cases_ = _navigation.getVal(_concat);
//            String ca_ = BeanNatCommonLgNames.processString(_ret);
//            if (cases_ == null) {
////                if (ca_.isEmpty()) {
////                    return _currentUrl;
////                }
//                return ca_;
//            }
//            urlDest_ = cases_.getVal(ca_);
//            if (urlDest_ == null) {
//                urlDest_ = _currentUrl;
//            }
//        }
//        return urlDest_;
        String urlDest_ = BeanNatCommonLgNames.processString(return_);
        proc(_nav, _rendStack, urlDest_, gl_);
    }

//    public StringMap<Validator> getValidators() {
//        return validators;
//    }


    public void setForms(StringMapObject _forms, NaSt _bean) {
        ((WithForms) ((PokemonBeanStruct) _bean).getBean()).setForms(_forms);
    }

    @Override
    public BeanNatCommonLgNames setBeanForms(NaSt _mainBean, NaSt _called) {
        fwd((PokemonBeanStruct) _mainBean, (PokemonBeanStruct) _called);
        return this;
    }

    public static void fwd(PokemonBeanStruct _mainBean, PokemonBeanStruct _called) {
        StringMapObject forms_ = ((WithForms) _called.getBean()).getForms();
        StringMapObject formsMap_ = ((WithForms) _mainBean.getBean()).getForms();
        forms_.putAllMap(formsMap_);
    }

    public void execute(boolean _form, NatNavigation _navigation) {
        if (_form) {
            processRendFormRequest(_navigation);
        } else {
            processRendAnchorRequest(_navigation);
        }
    }
    public static NaSt getStructToBeValidatedPrim(StringList _values, String _className) {
        if (StringUtil.quickEq(_className,PRIM_BOOLEAN)) {
            return NaBoSt.of(StringUtil.quickEq(_values.first(),ON));
        }
        return new NaNbSt(NumberUtil.parseLongZero(_values.first()));
    }


//    public static PkTrainer toPkTrainer(Struct _inst) {
//        if (!(_inst instanceof PkStruct)) {
//            return Instances.newPkTrainer();
//        }
//        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
//        if (instance_ instanceof PkTrainer) {
//            return (PkTrainer) instance_;
//        }
//        return Instances.newPkTrainer();
//    }
//
//    public static WildPk toWildPk(Struct _inst) {
//        if (!(_inst instanceof PkStruct)) {
//            return Instances.newWildPk();
//        }
//        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
//        if (instance_ instanceof WildPk) {
//            return (WildPk) instance_;
//        }
//        return Instances.newWildPk();
//    }
//
//    public static PokemonPlayer toPokemonPlayer(Struct _inst) {
//        if (!(_inst instanceof PkStruct)) {
//            return Instances.newPokemonPlayer();
//        }
//        Pokemon instance_ = ((PkStruct)_inst).getWildPk();
//        if (instance_ instanceof PokemonPlayer) {
//            return (PokemonPlayer) instance_;
//        }
//        return Instances.newPokemonPlayer();
//    }

    public PokemonBeanStruct bean(CommonBean _bean, String _lg) {
        _bean.setDataBase(dataBase);
//        _bean.setBaseEncode(baseEncode);
        _bean.setForms(new StringMapObject());
        _bean.setLanguage(_lg);
        return new PokemonBeanStruct(_bean);
    }

    public PokemonBeanStruct beanDiffCommon(String _language) {
        return bean(new DifficultyCommonBean(), _language);
    }
    public static NatArrayStruct getBigNatMap(AbsMap<String, AbsBasicTreeMap<Rate, Rate>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, AbsBasicTreeMap<Rate, Rate>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),getRateRate(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getBigNatMapSta(AbsMap<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey().getKey()),getStaByte(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStaBoost(AbsMap<TranslatedKey, BoostHpRate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKey, BoostHpRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new BoostHpRateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcStr(AbsMap<MiniMapCoords, int[][]> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MiniMapCoords, int[][]> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new NaImgSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPtStr(AbsMap<Point, int[][]> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Point, int[][]> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new NaImgSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getEffRateStr(AbsMap<TranslatedKey, EfficiencyRate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKey, EfficiencyRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey().getKey()),new EfficiencyRateStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcByteMap(AbsMap<TranslatedKeyPair, Long> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKeyPair, Long> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new NaNbSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getCatMultRateMap(AbsMap<TranslatedKeyPair, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKeyPair, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new CategoryMultStruct(new CategoryMult(e.getKey().getFirst().getTranslation(),NumberUtil.parseInt(e.getKey().getSecond().getKey()))),new RtSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWcRateMap(AbsMap<TranslatedKeyPair, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKeyPair, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new RtSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWeatherTypeRateMap(AbsMap<TranslatedKeyPair, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKeyPair, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new TypesDuoStruct(new TypesDuo(e.getKey().getFirst().getTranslation(),e.getKey().getSecond().getTranslation())),new RtSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrListStaList(AbsMap<TranslatedKey, CustList<TranslatedKey>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKey, CustList<TranslatedKey>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey().getKey()),getValues(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getShStrList(AbsMap<Long, CustList<TranslatedKey>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Long, CustList<TranslatedKey>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),getKeys(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getIntIntMap(AbsMap<Integer, Integer> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Integer, Integer> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new NaNbSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrInts(AbsMap<TranslatedKey, Ints> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKey, Ints> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey().getKey()),getIntArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrTpDam(AbsMap<TranslatedKey, TypeDamageBoost> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<TranslatedKey, TypeDamageBoost> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey().getKey()),new TypeDamageBoostStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getPlLevWildPkDto(AbsMap<PlaceLevel, CustList<WildPokemonDto>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<PlaceLevel, CustList<WildPokemonDto>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,getWildPkDto(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWildPkDto(CustList<WildPokemonDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (WildPokemonDto s:_ls) {
            arr_.set(j_,new WildPokemonDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getSteDto(CustList<StepDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (StepDto s:_ls) {
            arr_.set(j_,new StepDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkPlDto(CustList<PokemonPlayerDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonPlayerDto s:_ls) {
            arr_.set(j_,new PokemonPlayerDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkTrDto(CustList<PokemonTrainerDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonTrainerDto s:_ls) {
            arr_.set(j_,new PokemonTrainerDtoStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getLvMv(CustList<LevelMoveTranslatedKey> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (LevelMoveTranslatedKey s:_ls) {
            arr_.set(j_,new LevelMoveStruct(s.lm()));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPlTr(CustList<PlaceTrainerDto> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PlaceTrainerDto s:_ls) {
            arr_.set(j_,new PlaceTrainerDtoStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getTypesDuo(CustList<TranslatedKeyPair> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (TranslatedKeyPair s:_ls) {
            arr_.set(j_,new TypesDuoStruct(new TypesDuo(s.getFirst().getKey(),s.getSecond().getKey())));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStatistic(CustList<StringStatBaseEv> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (StringStatBaseEv e:_map) {
            arr_.set(j_,new NaStSt(e.getName().getTranslation()));
            j_++;
        }
        return arr_;
    }
//    public static NatArrayStruct getPkTeam(CustList<PokemonTeam> _ls) {
//        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
//        int j_ = 0;
//        for (PokemonTeam s:_ls) {
//            arr_.set(j_,new PokemonTeamStruct(s));
//            j_++;
//        }
//        return arr_;
//    }
//    public static NatArrayStruct getSiSa(CustList<StatisticStatus> _ls) {
//        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
//        int j_ = 0;
//        for (StatisticStatus s:_ls) {
//            arr_.set(j_,new StatisticStatusStruct(s));
//            j_++;
//        }
//        return arr_;
//    }
    public static NatArrayStruct getPlInd(CustList<PlaceIndex> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PlaceIndex s:_ls) {
            arr_.set(j_,new PlaceIndexStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getWildPkArray(CustList<WildPk> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (WildPk s:_ls) {
            arr_.set(j_,new PkStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkPlayerArray(CustList<PokemonPlayer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonPlayer s:_ls) {
            arr_.set(j_,new PkStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkTrainerArray(CustList<TranslatedPkElements> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (TranslatedPkElements s:_ls) {
            arr_.set(j_,new PkStruct(s.getTrained()));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getPkLine(CustList<PokemonLine> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (PokemonLine s:_ls) {
            arr_.set(j_,new PkLineStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getItLine(CustList<ItemLine> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (ItemLine s:_ls) {
            arr_.set(j_,new ItLineStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getMvLine(CustList<MoveLine> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getRdMvLine(CustList<RadioLineMove> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getSelectLineMove(CustList<SelectLineMove> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static NaSt newMoveLine(MoveLine _s) {
//        if (_s instanceof SelectLineMove) {
//            return new MvLineStruct(_s);
//        }
//        if (_s instanceof RadioLineMove) {
//            return new MvLineStruct(_s);
//        }
        return new MvLineStruct(_s);
    }
    public static NatArrayStruct getEffPartStat(CustList<EffectPartnerStatus> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (EffectPartnerStatus s:_ls) {
            arr_.set(j_,new EffectPartnerStatusStruct(s));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getEvLine(AbsMap<Statistic, EvLine> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Statistic, EvLine> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new EvLineStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStr(AbsMap<String, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey())),new NaStSt(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStr(DictionaryComparator<TranslatedKey, TranslatedKey> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,TranslatedKey> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),new NaStSt(StringUtil.nullToEmpty(e.getValue().getTranslation())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStrKey(DictionaryComparator<TranslatedKey, TranslatedKey> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,TranslatedKey> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),new NaStSt(StringUtil.nullToEmpty(e.getValue().getKey())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStrOnly(DictionaryComparator<TranslatedKey, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),new NaStSt(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrStr(NatStringTreeMap<String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey())),new NaStSt(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getKeys(CustList<TranslatedKey> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (TranslatedKey e: _map){
            arr_.set(i_,new NaStSt(e.getKey()));
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getValues(CustList<TranslatedKey> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (TranslatedKey e: _map){
            arr_.set(i_,new NaStSt(e.getTranslation()));
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrRate(AbsMap<String, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey())),new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrRate(DictionaryComparator<TranslatedKey, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrRateVal(DictionaryComparator<TranslatedKey, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getTranslation())),new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrLongStatKey(DictionaryComparator<TranslatedKey, StatRankRate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,StatRankRate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),new RtSt(e.getValue().getRate()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrLongStat(DictionaryComparator<TranslatedKey, StatRankRate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,StatRankRate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getTranslation())),new NaNbSt(e.getValue().getRank()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrLongVal(DictionaryComparator<TranslatedKey, Long> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,Long> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getTranslation())),new NaNbSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrLong(DictionaryComparator<TranslatedKey, Long> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,Long> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),new NaNbSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStaByte(DictionaryComparator<TranslatedKey, Long> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey, Long> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new NaNbSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStaStr(DictionaryComparator<TranslatedKey, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey, String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new NaStSt(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStaRate(AbsMap<TranslatedKey, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey, Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(NaNu.NULL_VALUE,new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getIntStr(AbsMap<Integer, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Integer,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new NaStSt(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getIntStr(IntTreeMap<TranslatedKey> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Integer,TranslatedKey> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new NaStSt(StringUtil.nullToEmpty(e.getValue().getTranslation())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLongStr(AbsMap<Long, TranslatedKey> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Long,TranslatedKey> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new NaStSt(StringUtil.nullToEmpty(e.getValue().getKey())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLgIntRate(AbsMap<LgInt, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<LgInt,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new LgSt(e.getKey()),new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLongRate(AbsMap<Long, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Long,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getRateRate(AbsMap<Rate, Rate> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<Rate,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new RtSt(e.getKey()),new RtSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrStrList(DictionaryComparator<TranslatedKey, CustList<TranslatedKey>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<TranslatedKey,CustList<TranslatedKey>> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey().getKey())),getKeys(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static NatArrayStruct getLayers(CustList<Level> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }
    public static NatArrayStruct getEnd(CustList<EndRoundMainElements> _map) {
        int len_ = _map.size();
        return arrId(len_);
    }

    public static NatArrayStruct arrId(int _len) {
        NatArrayStruct arr_ = new NatArrayStruct(_len);
        for (int i = 0; i < _len; i++) {
            arr_.set(i,NaNu.NULL_VALUE);
        }
        return arr_;
    }
    public static NatArrayStruct getStrList(CustList<CustList<TranslatedKey>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (CustList<TranslatedKey> e: _map){
            arr_.set(i_,getKeys(e));
            i_++;
        }
        return arr_;
    }

    public static NatArrayStruct getIntArray(CustList<Integer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (Integer s:_ls) {
            arr_.set(j_,new NaNbSt(s));
            j_++;
        }
        return arr_;
    }

    public static SelectedBoolean getBoolByName(String _env) {
        return SelectedBoolean.getBoolByName(_env);
    }
    public static DifficultyModelLaw getModelByName(String _env) {
        return DifficultyModelLaw.getModelByName(_env);
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        return DifficultyWinPointsFight.getDiffWonPtsByName(_env);
    }
    public static Gender getGenderByName(String _env) {
        return Gender.getGenderByName(_env);
    }

    public FacadeGame getDataBase() {
        return dataBase;
    }

    public void setDataBase(FacadeGame _dataBase){
        dataBase = _dataBase;
    }

//    public void setBaseEncode(String _p) {
//        this.baseEncode = _p;
//    }

    @Override
    protected AbstractNatBlockBuilder blockBuilder() {
        return new AdvNatBlockBuilder();
    }

    @Override
    protected NatRendStackCall newNatRendStackCall() {
        return new NatRendStackCallAdv();
    }
}
