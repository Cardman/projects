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
    @Test
    public void pkForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneForm<String, PokemonData> c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value("P1");
        g_.getTypes().setupValue(new CustList<String>("T1"));
        tryClick(c_.getValidAddEdit());
        assertEq(1,c_.getList().size());
        assertEq("P1",c_.getList().getKey(0));
        assertEq(1,c_.getList().getValue(0).getTypes().size());
        assertEq("T1",c_.getList().getValue(0).getTypes().get(0));
    }
    @Test
    public void pkForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        CrudGeneForm<String, PokemonData> c_ = crud(pr_, facade_);
        GeneComponentModelPokemonData g_ = (GeneComponentModelPokemonData)c_.getGeneValue();
        tryClick(c_.getAdd());
        c_.getGeneKey().value("P1");
        g_.getTypes().setupValue(new CustList<String>("T1"));
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
        GeneComponentModelEltGenderRepartition ge_ = new GeneComponentModelEltGenderRepartition(pr_, new IdMap<GenderRepartition, String>(), new CustList<GenderRepartition>());
        ge_.geneEnum(GenderRepartition.MIXED);
        assertEq(GenderRepartition.MIXED.getGenderRep(), ge_.tryRet().getGenderRep());
    }
    private CrudGeneForm<String, PokemonData> crud(AbstractProgramInfos _core, FacadeGame _facade) {
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
        StringMap<String> allTypes_ = new StringMap<String>();
        allTypes_.addEntry("T1","t1");
        allTypes_.addEntry("T2","t2");
        allTypes_.addEntry("T3","t3");
        facade_.getData().getTypes().add("T1");
        facade_.getData().getTypes().add("T2");
        facade_.getData().getTypes().add("T3");
        facade_.getData().getTranslatedTypes().addEntry(_m.getLanguage(), allTypes_);
        facade_.getData().getTranslatedStatistics().addEntry(_m.getLanguage(), new IdMap<Statistic, String>());
        StringMap<String> allPk_ = new StringMap<String>();
        allPk_.addEntry("P1","p1");
        allPk_.addEntry("P2","p2");
        allPk_.addEntry("P3","p3");
        facade_.getData().getTranslatedPokemon().addEntry(_m.getLanguage(), allPk_);
        return facade_;
    }
}
