package aiki.beans;

import aiki.beans.facade.simulation.enums.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.instances.Instances;
import aiki.map.levels.enums.*;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.*;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.MiniMapCoordsList;
import aiki.map.util.TileMiniMap;
import code.bean.nat.*;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.opers.*;
import code.bean.nat.fwd.opers.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.sml.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class WelcomeBeanTest extends InitDbWelcome {
    @Test
    public void display1() {
        NaSt bean_ = beanWelcome(feedDb());
        displaying(bean_);
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void display2() {
        NaSt bean_ = beanWelcome(feedDbBase());
        displaying(bean_);
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void allMoves1() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertFalse(forms(bean_).contains(CST_LEARNT));
        assertEq(0,forms(bean_).getValMoveData(CST_MOVES_SET).size());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void allMoves2() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(displaying(beanWelcome(feedDb())))));
    }
    @Test
    public void learntMoves() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,navigateLearntMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertTrue(forms(bean_).contains(CST_LEARNT));
        assertTrue(forms(bean_).getValBool(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void notLearntMoves() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_MOVES_MOVES_HTML,navigateNotLearntMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertTrue(forms(bean_).contains(CST_LEARNT));
        assertFalse(forms(bean_).getValBool(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void abilities() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML,navigateAbilities(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_ABILITIES_SET));
        assertTrue(forms(bean_).getValAbilityData(CST_ABILITIES_SET).isEmpty());
    }
    @Test
    public void items() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMS_HTML,navigateItems(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_ITEMS_SET));
        assertTrue(forms(bean_).getValItemData(CST_ITEMS_SET).isEmpty());
    }
    @Test
    public void pokedex() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_POKEDEX_HTML,navigatePokedex(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_POKEMON_SET));
        assertTrue(forms(bean_).getValPokemonData(CST_POKEMON_SET).isEmpty());
    }
    @Test
    public void status() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_STATUS_STATUS_HTML,navigateStatus(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_STATUS_SET));
        assertTrue(forms(bean_).getValStatusData(CST_STATUS_SET).isEmpty());
    }
    @Test
    public void simulation() {
        NaSt bean_ = beanWelcome(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,navigateSimulation(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_SIMULATION_STATE));
        assertSame(SimulationSteps.DIFF,forms(bean_).getValSimStep(CST_SIMULATION_STATE));
    }
    @Test
    public void init() {
        FacadeGame f_ = facade();
        f_.getData().initializeMembers();
        f_.getData().getTranslatedEnvironment().addEntry(EN,new IdMap<EnvironmentType, String>());
        f_.getData().getTranslatedEnvironment().addEntry(FR,new IdMap<EnvironmentType, String>());
        f_.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
        f_.getData().getTranslatedBooleans().addEntry(FR,new IdMap<SelectedBoolean, String>());
        f_.getData().getTranslatedGenders().addEntry(EN,new IdMap<Gender, String>());
        f_.getData().getTranslatedGenders().addEntry(FR,new IdMap<Gender, String>());
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        f_.getData().getTranslatedStatistics().addEntry(FR,new IdMap<Statistic, String>());
        f_.getData().getTranslatedTargets().addEntry(EN,new IdMap<TargetChoice, String>());
        f_.getData().getTranslatedTargets().addEntry(FR,new IdMap<TargetChoice, String>());
        f_.getData().getTranslatedFctMath().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedFctMath().addEntry(FR,new StringMap<String>());
        f_.getMap().setMiniMap(new MiniMapCoordsList());
        f_.getMap().setUnlockedCity("__");
        f_.getMap().getMiniMap().addEntry(new MiniMapCoords(0,0), tile());
        f_.getMap().getMiniMap().addEntry(new MiniMapCoords(0,1), tile());
        f_.getMap().getMiniMap().addEntry(new MiniMapCoords(1,0), tile());
        f_.getMap().getMiniMap().addEntry(new MiniMapCoords(1,1), tile());
        f_.getMap().setPlaces(new CustList<Place>());
        f_.getMap().addPlace(Instances.newRoad());
        f_.getMap().setBegin(newCoords(0,0,0,0));
        f_.getData().getMiniMap().addEntry("_",instance(1));
        f_.getData().getMiniMap().addEntry("__",instance(1));
        f_.getData().completeMembers("___",Instances.newPokemonData());
        f_.getData().getMaxiPkFront().addEntry("___",instance(1));
        WildPk wi_ = Instances.newWildPk();
        wi_.setName("___");
        wi_.setAbility("____");
        f_.getMap().setFirstPokemon(wi_);
        f_.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedPokemon().addEntry(FR,new StringMap<String>());
        f_.getData().getTranslatedPokemon().getVal(EN).addEntry("___","___");
        f_.getData().getTranslatedPokemon().getVal(FR).addEntry("___","___");
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().addEntry(FR,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry("____","____");
        f_.getData().getTranslatedAbilities().getVal(FR).addEntry("____","____");
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().addEntry(FR,new StringMap<String>());
        f_.updateTrs();
        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
        builtMessages_.addEntry(EN,new TranslationsAppli());
        builtMessages_.addEntry(FR,new TranslationsAppli());
//        StringMap<String> builtOther_ = CssInit.ms();
        PkData pk_ = new PkData();
        StringMap<Document> b_ = PagesInit.build();
        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new DataGameInit(), b_,new StringMap<String>(),builtMessages_);
        nav_.setLanguage(EN);
        pk_.setDataBase(f_);
        pk_.initializeRendSessionDoc(nav_);
        pk_.getPage();
        assertFalse(nav_.getHtmlText().isEmpty());
//        goToPage(pk_,nav_,0);
        NatRendStackCall r_ = new NatRendStackCall();
        NatImportingPageForm l_ = new NatImportingPageForm();
        NatRendReadWrite rend_ = new NatRendReadWrite();
        rend_.setDocument(b_.getValue(0));
        rend_.setWrite(b_.getValue(0).createElement(""));
        l_.setRendReadWrite(rend_);
        r_.addPage(l_);
        NatExecTextPart txt_ = new NatExecTextPart();
        txt_.setOpExp(new CustList<CustList<NatExecOperationNode>>());
        txt_.getTexts().add("");
//        r_.getFormParts().initFormsSpec();
//        new NatRendTitledAnchor(b_.getValue(0).getDocumentElement(),new StringMap<NatExecTextPart>(),new StringMap<NatExecTextPart>(),new CustList<NatExecOperationNode>(),new StringList(),new StringMap<String>(), txt_).processEl(new NatConfigurationCore(), r_);
//        new NatRendInactiveAnchor(b_.getValue(0).getDocumentElement(),new StringMap<NatExecTextPart>(),new StringMap<NatExecTextPart>()).processEl(new NatConfigurationCore(), r_);
        Element radi_ = b_.getValue(0).createElement("");
        radi_.setAttribute("type","radio");
        CustList<NatExecOperationNode> exps2_ = new CustList<NatExecOperationNode>();
        NatAnaFieldOperationContent fc_ = new NatAnaFieldOperationContent();
        fc_.setIntermediate(true);
        exps2_.add(new NatSettableFieldOperation(true,0,new NatExecFieldOperationContent(fc_),new NatExecSettableOperationContent(new NatAnaSettableOperationContent())));
//        NatFieldUpdates up_ = new NatFieldUpdates();
//        new NatRendInput(radi_,new StringMap<NatExecTextPart>(),new StringMap<NatExecTextPart>(),exps2_, null, up_).processEl(new NatConfigurationCore(), r_);
//        PokemonStandards.getStructToBeValidatedPrim(new StringList(""),PokemonStandards.PRIM_BOOLEAN);
//        PokemonStandards.getStructToBeValidatedPrim(new StringList(PokemonStandards.ON),PokemonStandards.PRIM_BOOLEAN);
        CustList<NatExecOperationNode> exps_ = new CustList<NatExecOperationNode>();
        exps_.add(new NatSettableFieldOperation(true,0,new NatExecFieldOperationContent(new NatAnaFieldOperationContent()),new NatExecSettableOperationContent(new NatAnaSettableOperationContent())));
//        PokemonStandards.redir(NaNu.NULL_VALUE,new StringList(""), exps_,new StringList(""),r_);
//        PokemonStandards.getStructToBeValidated(new StringList(""),PokemonStandards.TYPE_RATE);
        LongMap<LongTreeMap<NodeContainer>> form_ = new LongMap<LongTreeMap<NodeContainer>>();
        LongTreeMap<NodeContainer> c_ = new LongTreeMap<NodeContainer>();
        NodeContainer v_ = new NodeContainer();
        v_.getNodeInformation().setValue(new StringList(""));
        c_.addEntry(0L, v_);
        form_.addEntry(0L, c_);
//        v_.getNodeInformation().setValidator("rate_validator");
//        pk_.getNatPage().setContainers(form_);
//        pk_.getPage().setUrl(0);
//        pk_.processRendFormRequest(nav_,nav_.getDocument().getDocumentElement());
//        v_.setOpsWrite(new CstNatCaller(""));
        CustList<LongTreeMap<NodeContainer>> st_ = new CustList<LongTreeMap<NodeContainer>>();
        st_.add(c_);
        LongTreeMap<NodeContainer> second_ = new LongTreeMap<NodeContainer>();
        NodeContainer w_ = new NodeContainer();
        w_.getNodeInformation().setValue(new StringList(""));
//        w_.setOpsWrite(new CstNatCaller(""));
        second_.addEntry(0L, w_);
        st_.add(second_);
//        r_.getFormParts().getInputs().add(0L);
//        up_.setRad(true);
//        NatRendElementForm.prStack(new NatConfigurationCore(),b_.getValue(0).createElement(""),up_,new NatFetchedObjs(NaNu.NULL_VALUE, st_), r_,"");
//        NatRendElementForm.prStack(new NatConfigurationCore(),b_.getValue(0).createElement(""),up_,new NatFetchedObjs(NaNu.NULL_VALUE, st_), r_,"");
        NatExecTextPart txtPart_ = new NatExecTextPart();
        txtPart_.setOpExp(new CustList<CustList<NatExecOperationNode>>());
        txtPart_.getOpExp().add(exps_);
        txtPart_.getTexts().add("");
//        NatRendElementForm.renderAltListNat(txtPart_,r_);
//        NatRendElementForm.nullValueToEmpty();
        NatDotOperation root_ = new NatDotOperation(1);
        root_.appendChild(new NatSettableFieldOperation(false,0,new NatExecFieldOperationContent(new NatAnaFieldOperationContent()),new NatExecSettableOperationContent(new NatAnaSettableOperationContent())));
        PokemonBeanStruct.castDottedTo(root_);
        StringMapObject s_ = new StringMapObject();
        s_.put("0",0);
        s_.put("1",false);
        s_.put("2","");
        s_.put("3", Rate.zero());
        s_.put("4", new StringList());
        s_.put("5", new NaStSt(""));
        s_.put("8", 0L);
        assertTrue(s_.containsBase("0"));
        assertTrue(s_.containsBase("1"));
        assertTrue(s_.containsBase("2"));
        assertTrue(s_.containsBase("3"));
        assertTrue(s_.containsBase("4"));
        assertTrue(s_.containsBase("5"));
        assertTrue(s_.containsBase("8"));
        assertFalse(s_.containsBase("6"));
        assertEq(0,s_.getValInt("7"));
        assertEq(0,s_.getValLong("9"));
        s_.removeKeyBase("1");
//        NatImgAttr n_ = new NatImgAttr("");
//        n_.setAnim(new CustList<int[][]>());
//        n_.copy();
        assertEq(0,NaImgSt.tryGet(null).length);
        WelcomeBean w2_ = new WelcomeBean();
        MockBeanBuilderHelper tmp_ = new MockBeanBuilderHelper();
        tmp_.setFacade(f_);
        w2_.setBuilder(tmp_);
        Translations trs_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA,MessagesPkBean.enData());
        trs_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA,MessagesPkBean.frData());
        trs_.getMapping().addEntry(FR, fr_);
        w2_.getBuilder().setTranslations(trs_);
        w2_.build(f_);
        pk_.initBeans(null,EN);
    }
    @Test
    public void alt() {
        StringList list_ = new StringList();
        list_.add("_");
        list_.add("__");
        StringList a_ = PokemonBeanStruct.arg(list_);
        assertEq(1,a_.size());
        assertEq("__",a_.get(0));
    }

    private TileMiniMap tile() {
        TileMiniMap tile_ = Instances.newTileMiniMap();
        tile_.setFile("_");
        return tile_;
    }

//    private void goToPage(PkData _pk, NatNavigation _nav, int _nb) {
//        _pk.getNatPage().setUrl(_nb);
//        _pk.execute(false, _nav);
//    }

}
