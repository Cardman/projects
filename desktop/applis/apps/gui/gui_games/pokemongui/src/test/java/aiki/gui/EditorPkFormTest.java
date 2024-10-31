package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.util.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.util.*;
import code.util.*;
import org.junit.Test;

public final class EditorPkFormTest extends EquallableAikiGuiUtil {

    public static final String P_1 = "P1";
    public static final String T_1 = "T1";
    public static final String T_2 = "T2";
    public static final String T_3 = "T3";
    public static final String P_2 = "P2";
    public static final String P_3 = "P3";

    @Test
    public void pkForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneFormPk c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value(P_1);
        g_.getTypes().setupValue(new CustList<String>(T_1));
        tryClick(c_.getValidAddEdit());
        assertEq(1,facade_.getData().getPokedex().size());
        assertEq(1,c_.getList().size());
        assertEq(P_1,c_.getList().getKey(0));
        assertEq(1,c_.getList().getValue(0).getTypes().size());
        assertEq(T_1,c_.getList().getValue(0).getTypes().get(0));
    }
    @Test
    public void pkForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneFormPk c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value(P_1);
        g_.getTypes().setupValue(new CustList<String>(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        tryClick(c_.getValidAddEdit());
        tryClick((AbsButton) c_.getElements().getComponent(0));
        GeneComponentModelPokemonData gSec_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        assertEq(6,gSec_.getStatistics().size());
        assertEq(50,gSec_.getStatistics().getVal(Statistic.SPEED).getBase().getValue());
        assertEq(25,gSec_.getStatistics().getVal(Statistic.SPEED).getEv().getValue());
    }
    @Test
    public void pkForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneFormPk c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value(P_1);
        g_.getTypes().setupValue(new CustList<String>(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        tryClick(c_.getValidAddEdit());
        tryClick((AbsButton) c_.getElements().getComponent(0));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(40);
        tryClick(c_.getValidAddEdit());
        assertEq(40, facade_.getData().getPokedex().getVal(P_1).getStatistics().getVal(Statistic.SPEED).getBase());
    }
    @Test
    public void pkForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneFormPk c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value(P_1);
        g_.getTypes().setupValue(new CustList<String>(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        tryClick(c_.getValidAddEdit());
        tryClick((AbsButton) c_.getElements().getComponent(0));
        tryClick(c_.getValidRemove());
        assertEq(0, facade_.getData().getPokedex().size());
        assertEq(0, c_.getList().size());
    }
    @Test
    public void pkForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneFormPk c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value(P_1);
        g_.getTypes().setupValue(new CustList<String>(T_1));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_1);
        tryClick(c_.getValidAddEdit());
        tryClick(c_.getAdd());
        c_.getGeneKey().value(P_2);
        g_.getTypes().setupValue(new CustList<String>(T_2));
        g_.getStatistics().getVal(Statistic.SPEED).getBase().setValue(50);
        g_.getStatistics().getVal(Statistic.SPEED).getEv().setValue(25);
        g_.getBaseEvo().setupValue(P_1);
        tryClick(c_.getValidAddEdit());
        tryClick((AbsButton) c_.getElements().getComponent(0));
        tryClick(c_.getValidRemove());
        assertEq(2, facade_.getData().getPokedex().size());
        assertEq(2, c_.getList().size());
    }
    private CrudGeneFormPk crud(AbstractProgramInfos _core, FacadeGame _facade) {
        return GeneComponentModelPokemonData.crud(_core.getFrameFactory().newCommonFrame(), _core,_facade);
    }
    private MockProgramInfos initForms() {
        MockProgramInfos pr_ = build("/__/", "/_/", dbs(0.75));
        pr_.setLanguages(new StringList(EN,FR));
        TranslationsLg en_ = pr_.lg(EN);
        TranslationsLg fr_ = pr_.lg(FR);
        MessagesPkEditor.enTr(MessagesPkEditor.initAppliTr(en_));
        MessagesPkEditor.frTr(MessagesPkEditor.initAppliTr(fr_));
        pr_.setLanguage(EN);
        return pr_;
    }
    private FacadeGame facade(MockProgramInfos _m) {
        FacadeGame facade_ = new FacadeGame();
        facade_.setLanguages(_m.getLanguages());
        facade_.setDisplayLanguages(_m.getDisplayLanguages());
        facade_.setSimplyLanguage(_m.getLanguage());
        facade_.setData(new DataBase(new DefaultGenerator(new CustomSeedGene())));
        facade_.getData().initTranslations();
        facade_.getData().setCombos(Instances.newCombos());
        facade_.getData().setMap(Instances.newDataMap());
        StringMap<String> allTypes_ = new StringMap<String>();
        allTypes_.addEntry(T_1,"t1");
        allTypes_.addEntry(T_2,"t2");
        allTypes_.addEntry(T_3,"t3");
        facade_.getData().getTranslatedTypes().addEntry(_m.getLanguage(), allTypes_);
        facade_.getData().getTranslatedStatistics().addEntry(_m.getLanguage(), new IdMap<Statistic, String>());
        StringMap<String> allPk_ = new StringMap<String>();
        allPk_.addEntry(P_1,"p1");
        allPk_.addEntry(P_2,"p2");
        allPk_.addEntry(P_3,"p3");
        facade_.getData().getTranslatedPokemon().addEntry(_m.getLanguage(), allPk_);
        return facade_;
    }
}
