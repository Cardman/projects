package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.DealerItem;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.sml.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.files.*;
import code.mock.*;
import code.stream.*;
import code.util.*;
import org.junit.Test;

public final class EditorMapFormTest extends InitEditorPkForm {
    @Test
    public void values() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        sub_.getFormDataMap().getScreenWidth().setValue(2);
        sub_.getFormDataMap().getScreenHeight().setValue(3);
        sub_.getFormDataMap().getSpaceBetweenLeftAndHeros().setValue(4);
        sub_.getFormDataMap().getSpaceBetweenTopAndHeros().setValue(5);
        sub_.getFormDataMap().getSideLength().setValue(6);
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(2,facade_.getData().getMap().getScreenWidth());
        assertEq(3,facade_.getData().getMap().getScreenHeight());
        assertEq(4,facade_.getData().getMap().getSpaceBetweenLeftAndHeros());
        assertEq(5,facade_.getData().getMap().getSpaceBetweenTopAndHeros());
        assertEq(6,facade_.getData().getMap().getSideLength());
    }
    @Test
    public void pkFirst() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        sub_.getFormDataMap().getFormWildPk().getName().setupValue(P_1);
        sub_.getFormDataMap().getFormWildPk().getAbility().setupValue(A_1);
        sub_.getFormDataMap().getFormWildPk().getItem().setupValue(I_1);
        sub_.getFormDataMap().getFormWildPk().getLevel().valueInt(2);
        sub_.getFormDataMap().getFormWildPk().getGender().setupValue(Gender.NO_GENDER);
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_1,facade_.getData().getMap().getFirstPokemon().getName());
        assertEq(A_1,facade_.getData().getMap().getFirstPokemon().getAbility());
        assertEq(I_1,facade_.getData().getMap().getFirstPokemon().getItem());
        assertEq(2,facade_.getData().getMap().getFirstPokemon().getLevel());
    }
    @Test
    public void unlockCity() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        sub_.getFormDataMap().getUnlockedCity().updateValue(P_1);
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_1,facade_.getData().getMap().getUnlockedCity());
    }
    @Test
    public void unlockCityUp1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        sub_.getFormDataMap().getUnlockedCity().updateValue(P_1);
        CrudGeneFormEntImgFree n_ = crudMiniMap(sub_);
        tryClick(n_.getAllButtons().get(0));
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)n_.getGene();
        g_.getKey().setText(P_3);
        tryClick(n_.getValidAddEdit());
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_3,facade_.getData().getMap().getUnlockedCity());
    }
    @Test
    public void unlockCityUp2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        sub_.getFormDataMap().getUnlockedCity().updateValue(P_1);
        CrudGeneFormEntImgFree n_ = crudMiniMap(sub_);
        tryClick(n_.getAllButtons().get(1));
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)n_.getGene();
        g_.getKey().setText(P_3);
        tryClick(n_.getValidAddEdit());
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_1,facade_.getData().getMap().getUnlockedCity());
    }
    @Test
    public void unlockCityUp3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPairBlock(sub_, P_1, "_2");
        sub_.getFormDataMap().getUnlockedCity().updateValue(P_1);
        CrudGeneFormEntImgFree n_ = crudBlocks(sub_);
        tryClick(n_.getAllButtons().get(0));
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)n_.getGene();
        g_.getKey().setText(P_3);
        tryClick(n_.getValidAddEdit());
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_1,facade_.getData().getMap().getUnlockedCity());
    }
    @Test
    public void unlockCityUp4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPairBlock(sub_, P_1, "_2");
        sub_.getFormDataMap().getUnlockedCity().updateValue(P_1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        CrudGeneFormEntImgFree n_ = crudBlocks(sub_);
        tryClick(n_.getAllButtons().get(0));
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)n_.getGene();
        g_.getKey().setText(P_3);
        tryClick(n_.getValidAddEdit());
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_1,facade_.getData().getMap().getUnlockedCity());
    }
    @Test
    public void unlockCityUp5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPairBlock(sub_, P_1, "_2");
        sub_.getFormDataMap().getUnlockedCity().updateValue(P_1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        CrudGeneFormEntImgFree n_ = crudBlocks(sub_);
        tryClick(n_.getAllButtons().get(0));
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)n_.getGene();
        g_.getKey().setText(P_3);
        tryClick(n_.getValidAddEdit());
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(P_1,facade_.getData().getMap().getUnlockedCity());
    }
    @Test
    public void place1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CITY);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_ROAD);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        City f_ = Instances.newCity();
        f_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0), Direction.RIGHT),newCoords(1,0,0,0));
        facade_.getData().getMap().addPlace(f_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidRemove());
        assertEq(2,facade_.getData().getMap().getPlaces().size());
    }
    @Test
    public void place6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidRemove());
        assertEq(1,facade_.getData().getMap().getPlaces().size());
    }
    @Test
    public void place7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CITY);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("__",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CITY);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void miniMap1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        assertEq(P_1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getFile());
        assertTrue(facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).isHeros());
        assertEq(1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void miniMap2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(2);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        assertEq(P_1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getFile());
        assertTrue(facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).isHeros());
        assertEq(2,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void miniMap3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(2);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getRemove());
        assertEq(0,facade_.getData().getMap().getMiniMap().getList().size());
    }
    @Test
    public void miniMap4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(false);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        assertEq(P_1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getFile());
        assertFalse(facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).isHeros());
        assertEq(1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void miniMap5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(false);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(false);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(2);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        assertEq(P_1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getFile());
        assertFalse(facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).isHeros());
        assertEq(2,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void miniMap6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(2);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getRemove());
        assertEq(0,facade_.getData().getMap().getMiniMap().getList().size());
    }
    @Test
    public void miniMap7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getRemove());
        assertEq(0,facade_.getData().getMap().getMiniMap().getList().size());
    }
    @Test
    public void miniMap8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getMap().addPlace(Instances.newCity());
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(3);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        tryClickTile(sub_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidRemove());
        assertEq(2,sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().getValue());
        assertEq(2,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void miniMap9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getMap().addPlace(Instances.newCity());
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        tryClickTile(sub_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(2));
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidRemove());
        assertEq(1,sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().getValue());
        assertEq(1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void miniMap10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().getMiniMap().addEntry(mini(0,0),Instances.newTileMiniMap());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPair(sub_, P_1, "_1");
        addPair(sub_, P_2, "_2");
        addPair(sub_, P_3, "_3");
        sub_.getFormDataMap().getMiniMapGrid().getCols().setValue(1);
        sub_.getFormDataMap().getMiniMapGrid().getRows().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getApply());
        tryClickTile(sub_);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getFile().updateValue(P_1);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getHeros().setSelected(true);
        sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getPlace().setValue(1);
        tryClick(sub_.getFormDataMap().getMiniMapGrid().getFormMiniMapTile().getMatch());
        assertEq(P_1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getFile());
        assertTrue(facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).isHeros());
        assertEq(1,facade_.getData().getMap().getMiniMap().getVal(mini(1, 1)).getPlace());
    }
    @Test
    public void wild1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(false);
        area(sub_).applyChange();
        tryClick(area(sub_).getMult().getWalk().getCrud().getAdd());
        tryClick(listMult(sub_).getForm().getCrud().getAdd());
        sub(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(listMult(sub_).getForm().getCrud().getValidAddEdit());
        tryClick(area(sub_).getMult().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonList().size());
        assertEq(0,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonFishingList().size());
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonList().get(0).size());
        assertEq(P_2,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonList().get(0).get(0).getName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(0));
        tryClick(area(sub_).getMult().getWalk().getCrud().getAllButtons().get(0));
        tryClick(listMult(sub_).getForm().getCrud().getAllButtons().get(0));
    }
    @Test
    public void wild2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
        AreaApparition a_ = (AreaApparition) pl_.getLevelRoad().getWildPokemonAreas().get(0);
        assertEq(1,a_.getWildPokemon().size());
        assertEq(0,a_.getWildPokemonFishing().size());
        assertEq(P_2,a_.getWildPokemon().get(0).getName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(0));
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAllButtons().get(0));
    }
    @Test
    public void wild3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidRemove());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(0,pl_.getLevelRoad().getWildPokemonAreas().size());
    }
    @Test
    public void wild4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidRemove());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
    }
    @Test
    public void wild5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(1));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidRemove());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
    }
    @Test
    public void wild6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidRemove());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
    }
    @Test
    public void wild7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(1));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidRemove());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
    }
    @Test
    public void wild8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getAreas().getValidRemove());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
    }
    @Test
    public void blocks1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        assertEq(2,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getValue(0).getHeight());
        assertEq(2,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getValue(0).getWidth());
    }
    @Test
    public void blocks4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
        assertEq(2,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getValue(0).getHeight());
        assertEq(2,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getValue(0).getWidth());
    }
    @Test
    public void blocks5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getRows().setValue(1);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getCols().setValue(1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getApplyPrepend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getVal(newPoint(-1,-1)).getWidth());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getVal(newPoint(-1,-1)).getHeight());
    }
    @Test
    public void blocks6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("-1:-1");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getVal(newPoint(0,0)).getWidth());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getVal(newPoint(0,0)).getHeight());
    }
    @Test
    public void blocks7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getRows().setValue(1);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getCols().setValue(1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),sub_.getFormDataMap().getMiniMapGrid().sideTile(),sub_.getFormDataMap().getMiniMapGrid().sideTile());
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(2,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getVal(newPoint(1,1)).getWidth());
        assertEq(2,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().getVal(newPoint(1,1)).getHeight());
    }
    @Test
    public void blocks8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairBlock(sub_, P_1, "_1");
        addPairBlock(sub_, P_2, "_2");
        addPairBlock(sub_, P_3, "_3");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        sub_.getFormDataMap().getSideLength().setValue(6);
        tryClick(sub_.getFormDataMap().getApplyMapModif());
        assertEq(6,facade_.getData().getMap().getSideLength());
    }
    @Test
    public void building1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(false);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getLevel().getBlocks().size());
    }
    @Test
    public void building2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(true);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getLevel().getBlocks().size());
    }
    @Test
    public void building3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(false);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTile());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(0,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
    }
    @Test
    public void building4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(true);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTile());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(0,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
    }
    @Test
    public void gym1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(true);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_LEADER));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_TRAINER));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getLevel().getBlocks().size());
        assertEq(0,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getExitCity().getPoint().getx());
        assertEq(0,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getExitCity().getPoint().gety());
        assertEq(1,((Gym)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGymLeaderCoords().getPoint().getx());
        assertEq(1,((Gym)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGymLeaderCoords().getPoint().gety());
        assertEq(1,((Gym)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGymTrainers().size());
    }
    @Test
    public void gym2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(true);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_LEADER));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.GYM_TILE_TRAINER));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getLevel().getBlocks().size());
        assertFalse(((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getExitCity().isDefined());
        assertFalse(((Gym)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGymLeaderCoords().isDefined());
        assertEq(0,((Gym)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGymTrainers().size());
    }
    @Test
    public void pkCenter1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(false);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_STORAGE));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_GERANT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_SELLER));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getLevel().getBlocks().size());
        assertEq(0,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getExitCity().getPoint().getx());
        assertEq(0,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getExitCity().getPoint().gety());
        assertEq(1,((PokemonCenter)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getStorageCoords().getPoint().getx());
        assertEq(1,((PokemonCenter)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getStorageCoords().getPoint().gety());
        assertEq(2,((PokemonCenter)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGerants().size());
    }
    @Test
    public void pkCenter2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newCity());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getGym().setSelected(false);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getCreateBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_EXIT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_STORAGE));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_GERANT));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getTiles().getVal(MessagesEditorSelect.PC_TILE_SELLER));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevelBuilding().getGrid(),facade_.getData().getMap().getSideLength(), 0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getRemoveTileBuilding());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getLevel().getBlocks().size());
        assertEq(1,((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getLevel().getBlocks().size());
        assertFalse(((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0)).getExitCity().isDefined());
        assertFalse(((PokemonCenter)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getStorageCoords().isDefined());
        assertEq(0,((PokemonCenter)((City)facade_.getData().getMap().getPlace(0)).getBuildings().getVal(newPoint(0,0))).getIndoor().getGerants().size());
    }
    @Test
    public void tile1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getMiniItems().addEntry(I_1,instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_ITEMS));
        sub_.getFormDataMap().getCrudPlace().getRoad().getItems().setupValue(I_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(I_1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getItems().getVal(newPoint(0,0)));
    }
    @Test
    public void tile2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_ITEMS));
        sub_.getFormDataMap().getCrudPlace().getRoad().getItems().setupValue(I_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getItems().size());
    }
    @Test
    public void tile3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getTm().addEntry((short)1,M_1);
        facade_.getData().getTm().addEntry((short)2,M_2);
        facade_.getData().getTm().addEntry((short)3,M_3);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_TM));
        sub_.getFormDataMap().getCrudPlace().getRoad().getTm().setupValue((short) 1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getTm().getVal(newPoint(0,0)));
    }
    @Test
    public void tile4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getTm().addEntry((short)1,M_1);
        facade_.getData().getTm().addEntry((short)2,M_2);
        facade_.getData().getTm().addEntry((short)3,M_3);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_TM));
        sub_.getFormDataMap().getCrudPlace().getRoad().getTm().setupValue((short) 1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getTm().size());
    }
    @Test
    public void tile5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getHm().addEntry((short)1,M_1);
        facade_.getData().getHm().addEntry((short)2,M_2);
        facade_.getData().getHm().addEntry((short)3,M_3);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_HM));
        sub_.getFormDataMap().getCrudPlace().getRoad().getHm().setupValue((short) 1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getHm().getVal(newPoint(0,0)));
    }
    @Test
    public void tile6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getHm().addEntry((short)1,M_1);
        facade_.getData().getHm().addEntry((short)2,M_2);
        facade_.getData().getHm().addEntry((short)3,M_3);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_HM));
        sub_.getFormDataMap().getCrudPlace().getRoad().getHm().setupValue((short) 1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getHm().size());
    }
    @Test
    public void tile7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getTm().addEntry((short)1,M_1);
        facade_.getData().getTm().addEntry((short)2,M_2);
        facade_.getData().getTm().addEntry((short)3,M_3);
        facade_.getData().setImageTmHm(instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_TM));
        sub_.getFormDataMap().getCrudPlace().getRoad().getTm().setupValue((short) 1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getTm().getVal(newPoint(0,0)));
    }
    @Test
    public void tile8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_ITEMS));
        sub_.getFormDataMap().getCrudPlace().getRoad().getItems().setupValue(I_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getItems().size());
    }
    @Test
    public void tile9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getRows().setValue(1);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getCols().setValue(1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_ITEMS));
        sub_.getFormDataMap().getCrudPlace().getRoad().getItems().setupValue(I_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),sub_.getFormDataMap().getMiniMapGrid().sideTile(),sub_.getFormDataMap().getMiniMapGrid().sideTile());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),sub_.getFormDataMap().getMiniMapGrid().sideTile(),sub_.getFormDataMap().getMiniMapGrid().sideTile());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
    }
    @Test
    public void tile10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getRows().setValue(1);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getCols().setValue(1);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_ITEMS));
        sub_.getFormDataMap().getCrudPlace().getRoad().getItems().setupValue(I_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),sub_.getFormDataMap().getMiniMapGrid().sideTile(),sub_.getFormDataMap().getMiniMapGrid().sideTile());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),sub_.getFormDataMap().getMiniMapGrid().sideTile(),sub_.getFormDataMap().getMiniMapGrid().sideTile());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getEdited().size());
    }
    @Test
    public void tile11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        facade_.getData().getMiniPk().addEntry(P_1,instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_LEG_PK));
        sub_.getFormDataMap().getCrudPlace().getRoad().getLegendaryPks().getName().setupValue(P_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(P_1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getLegendaryPks().getVal(newPoint(0,0)).getName());
    }
    @Test
    public void tile12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_LEG_PK));
        sub_.getFormDataMap().getCrudPlace().getRoad().getLegendaryPks().getName().setupValue(P_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getLegendaryPks().size());
    }
    @Test
    public void tile13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_1,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DEALER));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDealerItem().getMiniFileName().getName().setupValue(P_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(P_1,((DealerItem)sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getCharacters().getVal(newPoint(0,0))).getImageMiniFileName());
    }
    @Test
    public void tile14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DEALER));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDealerItem().getMiniFileName().getName().setupValue(P_1);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getCharacters().size());
    }
    @Test
    public void tile15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_TRAINER));
        sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getAdd());
        tryClick(((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getAdd());
        ((GeneComponentModelSubscribePkTrainer)((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getGenePair().getKey()).getFormTrainerPk().getName().setupValue(P_1);
        tryClick(((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getAllButtons().get(0));
        tryClick(((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getCancel());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getCancel());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(P_1,((TrainerMultiFights)sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getCharacters().getVal(newPoint(0,0))).getTeamsRewards().get(0).getTeam().get(0).getName());
    }
    @Test
    public void tile16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_TRAINER));
        sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getAdd());
        tryClick(((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getAdd());
        ((GeneComponentModelSubscribePkTrainer)((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getGenePair().getKey()).getFormTrainerPk().getName().setupValue(P_1);
        tryClick(((GeneComponentModelSubscribePokemonTeam)sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getGenePair().getKey()).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTrainerMultiFights().getTeams().getValidAddEdit());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getCharacters().size());
    }
    @Test
    public void tile17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DUAL));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getCancel());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFight(newPoint(0,0)).getPt().getPoint().getx());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFight(newPoint(0,0)).getPt().getPoint().gety());
    }
    @Test
    public void tile18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DUAL));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),0,0);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFights().contains(newPoint(0,0)));
        assertFalse(sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFight(newPoint(0,0)).getPt().isDefined());
    }
    @Test
    public void tile19() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DUAL));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFights().contains(newPoint(0,0)));
        assertFalse(sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFight(newPoint(0,0)).getPt().isDefined());
    }
    @Test
    public void tile20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DUAL));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),0,facade_.getData().getMap().getSideLength());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFight(newPoint(0,0)).getPt().getPoint().getx());
        assertEq(1,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFight(newPoint(0,0)).getPt().getPoint().gety());
    }
    @Test
    public void tile21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DUAL));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getSecondPt().getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFights().size());
    }
    @Test
    public void tile22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        addPairPerson(sub_,P_2,"_1");
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getTiles().getVal(MessagesEditorSelect.TILE_DUAL));
        sub_.getFormDataMap().getCrudPlace().getRoad().getDualFight().getTrainer().getMiniFileName().getName().setupValue(P_2);
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getRemoveTile());
        assertEq(0,sub_.getFormDataMap().getCrudPlace().getRoad().getEdited().getDualFights().size());
    }
    @Test
    public void cave1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        assertEq(1, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void cave2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidRemove());
        assertEq(0, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void cave3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        assertEq(1, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void cave4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidRemove());
        assertEq(0, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void cave5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Road place_ = Instances.newRoad();
        place_.getLinksWithCaves().put(newPoint(0,0),new Link("",newCoords(1,0,0,0)));
        facade_.getData().getMap().addPlace(place_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(Instances.newLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidRemove());
        assertEq(1,facade_.getData().getMap().getPlace(1).getLevelsList().size());
    }
    @Test
    public void cave6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidAddEdit());
        assertEq(1, facade_.getData().getMap().getPlace(0).getLevelsList().get(0).getBlocks().size());
    }
    @Test
    public void cave7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        assertEq(0, facade_.getData().getMap().getPlace(0).getLevelsList().get(0).getBlocks().size());
    }
    @Test
    public void linkCave1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).getx());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).gety());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().size());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).getx());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).getx());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).gety());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().size());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).getx());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCave4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).getx());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).gety());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().size());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).getx());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCave5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).getx());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).gety());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().size());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).getx());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCave6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getRemoveTileLeft());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().size());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).getx());
        assertEq(1,cave_.getLevels().get(1).getLinksOtherLevels().getKey(0).gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getRemoveTileRight());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).getx());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().getKey(0).gety());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getRemoveTileLeft());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getRemoveTileRight());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getRemoveTileBoth());
        assertEq(0,cave_.getLevels().get(0).getLinksOtherLevels().size());
        assertEq(0,cave_.getLevels().get(1).getLinksOtherLevels().size());
    }
    @Test
    public void linkCave11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        Cave cave_ = Instances.newCave();
        LevelCave first_ = blockLinksLevelCave();
        first_.getItems().addEntry(newPoint(0,0),I_1);
        cave_.getLevels().add(first_);
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        assertFalse(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft().isEnabled());
        assertFalse(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight().isEnabled());
        assertFalse(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth().isEnabled());
    }
    @Test
    public void linkCave12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        LevelCave second_ = blockLinksLevelCave();
        second_.getItems().addEntry(newPoint(1,1),I_1);
        cave_.getLevels().add(second_);
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        assertFalse(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft().isEnabled());
        assertFalse(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight().isEnabled());
        assertFalse(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth().isEnabled());
    }
    @Test
    public void linkCave13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getClose());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getAdd().isEnabled());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd().isEnabled());
    }
    @Test
    public void linkCave14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft());
        assertEq("_",cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getFileName());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameFirst().updateValue("__");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getMatchLinkLeft());
        assertEq("__",cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getFileName());
    }
    @Test
    public void linkCave15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight());
        assertEq("_",cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getFileName());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameSecond().updateValue("__");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getMatchLinkRight());
        assertEq("__",cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getFileName());
    }
    @Test
    public void linkCave16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("___",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileBoth());
        assertEq("_",cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getFileName());
        assertEq("_",cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getFileName());
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameFirst().updateValue("__");
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameSecond().updateValue("___");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getMatchLinkBoth());
        assertEq("__",cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getFileName());
        assertEq("___",cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getFileName());
    }
    @Test
    public void linkCave17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameFirst().updateValue("_");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileLeft());
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameFirst().updateValue("__");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getMatchLinkLeft());
        assertEq("_",cave_.getLevels().get(0).getLinksOtherLevels().getValue(0).getFileName());
    }
    @Test
    public void linkCave18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        facade_.getData().getMap().addPlace(cave_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevel().getGrid(),0,0);
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getLevels().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameSecond().updateValue("_");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getAddTileRight());
        link(sub_.getFormDataMap().getCrudPlace(),0).getLinkFileNameSecond().updateValue("__");
        tryClick(link(sub_.getFormDataMap().getCrudPlace(),0).getMatchLinkRight());
        assertEq("_",cave_.getLevels().get(1).getLinksOtherLevels().getValue(0).getFileName());
    }
    @Test
    public void league1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        assertEq(1, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void league2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidRemove());
        assertEq(0, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void league3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        assertEq(1, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void league4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidRemove());
        assertEq(0, facade_.getData().getMap().getPlace(0).getLevelsList().size());
    }
    @Test
    public void league5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        Road place_ = Instances.newRoad();
        place_.getLinksWithCaves().put(newPoint(0,0),new Link("",newCoords(1,0,0,0)));
        facade_.getData().getMap().addPlace(place_);
        League cave_ = Instances.newLeague();
        cave_.getRooms().add(Instances.newLevelLeague());
        facade_.getData().getMap().addPlace(cave_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidRemove());
        assertEq(1,facade_.getData().getMap().getPlace(1).getLevelsList().size());
    }
    @Test
    public void league6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidAddEdit());
        assertEq(1, facade_.getData().getMap().getPlace(0).getLevelsList().get(0).getBlocks().size());
    }
    @Test
    public void league7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        sub_.getFormDataMap().getCrudPlace().getGene().getName().valueString("_");
        ConverterCommonMapUtil.trigger(sub_.getFormDataMap().getCrudPlace().getGene().getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        assertEq(0, facade_.getData().getMap().getPlace(0).getLevelsList().get(0).getBlocks().size());
    }
    @Test
    public void tileLeague1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        l_.getRooms().add(Instances.newLevelLeague());
        facade_.getData().getMap().addPlace(l_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        ((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getDims());
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getTiles().getVal(MessagesEditorSelect.LEAGUE_TRAINER));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),facade_.getMap().getSideLength(),facade_.getMap().getSideLength());
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getTiles().getVal(MessagesEditorSelect.LEAGUE_ACCESS));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getTrainerCoords().getPoint().getx());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getTrainerCoords().getPoint().gety());
        assertEq(1,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getAccessPoint().getPoint().getx());
        assertEq(1,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getAccessPoint().getPoint().gety());
    }
    @Test
    public void tileLeague2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        l_.getRooms().add(Instances.newLevelLeague());
        facade_.getData().getMap().addPlace(l_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        ((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getDims());
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getTiles().getVal(MessagesEditorSelect.LEAGUE_TRAINER));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),facade_.getMap().getSideLength(),facade_.getMap().getSideLength());
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getTiles().getVal(MessagesEditorSelect.LEAGUE_ACCESS));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getRemoveTile());
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),facade_.getMap().getSideLength(),facade_.getMap().getSideLength());
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getRemoveTile());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidAddEdit());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getTrainerCoords().isDefined());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getAccessPoint().isDefined());
    }
    @Test
    public void tileLeague3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        f_.setNextLevelTarget(newPoint(0,0));
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        Block sBloc_ = Instances.newBlock();
        sBloc_.setHeight((short) 1);
        sBloc_.setWidth((short) 1);
        s_.getBlocks().addEntry(newPoint(0,0), sBloc_);
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(2));
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        ((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getDims());
        tryClicks(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getValidAddEdit());
        assertEq(1,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(1).getBlocks().size());
    }
    @Test
    public void linkNext1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        Block sBloc_ = Instances.newBlock();
        sBloc_.setHeight((short) 1);
        sBloc_.setWidth((short) 1);
        s_.getBlocks().addEntry(newPoint(0,0), sBloc_);
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getLevel().getGrid(),0,0);
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getClose());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getNextLevelTarget().getPoint().getx());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getNextLevelTarget().getPoint().gety());
    }
    @Test
    public void linkNext2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        Block sBloc_ = Instances.newBlock();
        sBloc_.setHeight((short) 1);
        sBloc_.setWidth((short) 1);
        s_.setTrainerCoords(newPoint(0,0));
        s_.getBlocks().addEntry(newPoint(0,0), sBloc_);
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getLevel().getGrid(),0,0);
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getClose());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getNextLevelTarget().isDefined());
    }
    @Test
    public void linkNext3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        Block sBloc_ = Instances.newBlock();
        sBloc_.setHeight((short) 1);
        sBloc_.setWidth((short) 1);
        s_.getBlocks().addEntry(newPoint(0,0), sBloc_);
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getLevel().getGrid(),0,0);
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(1));
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getLevel().getGrid(),0,0);
        tryClick(((CrudGeneFormLevelLeague)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getLinks().getClose());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getRooms().get(0).getNextLevelTarget().isDefined());
    }
    public static ContentComponentModelLevelCaveLinks link(CrudGeneFormEntPlace _c, int _i) {
        return ((CrudGeneFormLevelCave)_c.getLevels().get(_i)).getLinks();
    }
    private static LevelCave blockLinksLevelCave() {
        LevelCave lc_ = Instances.newLevelCave();
        blockLinks(lc_);
        return lc_;
    }
    private static void blockLinks(Level _lv) {
        Block bk_ = Instances.newBlock();
        bk_.setHeight((short) 2);
        bk_.setWidth((short) 2);
        _lv.getBlocks().put(newPoint(0,0), bk_);
    }
    private void tryClickTile(WindowPkEditor _sub) {
        int side_ = _sub.getFormDataMap().getMiniMapGrid().sideTile();
        tryClick(_sub.getFormDataMap().getMiniMapGrid().getGrid(),side_+1,side_+1);
    }
    public static void tryClicks(AbsPaintableLabel _m, int _x, int _y) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        CustList<AbsMouseListenerIntRel> list_ = new CustList<AbsMouseListenerIntRel>(_m.getMouseListenersRel());
        list_.get(0).mouseReleased(new CoreMouseLocation(_x, _y), null, null);
        list_.get(1).mouseReleased(new CoreMouseLocation(_x, _y), null, null);
    }
    public static void tryClicks(AbsButton _m) {
        assertTrue(_m.isVisible());
        assertTrue(_m.isEnabled());
        CustList<AbsActionListener> list_ = new CustList<AbsActionListener>(_m.getActionListeners());
        list_.get(0).action();
        list_.get(1).action();
    }
    private GeneComponentModelSubscribeWildPk sub(WindowPkEditor _w) {
        return (GeneComponentModelSubscribeWildPk) listMult(_w).getForm().getCrud().getGenePair().getKey();
    }

    private GeneComponentModelSubscribeWildPkList listMult(WindowPkEditor _w) {
        return (GeneComponentModelSubscribeWildPkList) area(_w).getMult().getWalk().getCrud().getGenePair().getKey();
    }

    private GeneComponentModelSubscribeWildPk listSimple(WindowPkEditor _w) {
        return (GeneComponentModelSubscribeWildPk) area(_w).getSimple().getWalk().getCrud().getGenePair().getKey();
    }

    private GeneComponentModelSubscribeArea area(WindowPkEditor _w) {
        return (GeneComponentModelSubscribeArea) _w.getFormDataMap().getCrudPlace().getRoad().getAreas().getGenePair().getKey();
    }

    private void addPair(WindowPkEditor _sub, String _k, String _v) {
        CrudGeneFormEntImgFree c_ = crudMiniMap(_sub);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(_k);
        tryLoad(g_, _v);
        tryClick(c_.getValidAddEdit());
        c_.getFrame().getWindowListenersDef().get(0).windowClosing();
    }

    private void addPairBlock(WindowPkEditor _sub, String _k, String _v) {
        CrudGeneFormEntImgFree c_ = crudBlocks(_sub);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(_k);
        tryLoad(g_, _v);
        tryClick(c_.getValidAddEdit());
        c_.getFrame().getWindowListenersDef().get(0).windowClosing();
    }

    private void addPairPerson(WindowPkEditor _sub, String _k, String _v) {
        CrudGeneFormEntImgFree c_ = crudPeople(_sub);
        tryClick(c_.getAdd());
        GeneComponentModelImgFree g_ = (GeneComponentModelImgFree)c_.getGene();
        g_.getKey().setText(_k);
        tryLoad(g_, _v);
        tryClick(c_.getValidAddEdit());
        c_.getFrame().getWindowListenersDef().get(0).windowClosing();
    }
    private CrudGeneFormEntImgFree crudMiniMap(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniMapMenu());
        return _crud.getCrudGeneFormMiniMap();
    }

    private CrudGeneFormEntImgFree crudBlocks(WindowPkEditor _crud) {
        tryClick(_crud.getImgBlocksMenu());
        return _crud.getCrudGeneFormBlocks();
    }

    private CrudGeneFormEntImgFree crudPeople(WindowPkEditor _crud) {
        tryClick(_crud.getImgPeopleMenu());
        return _crud.getCrudGeneFormPeople();
    }
    private FacadeGame facadeAdd(MockProgramInfos _m) {
        messages(_m);
        FacadeGame f_ = facade(_m);
        _m.getFileCoreStream().newFile("/__/").mkdirs();
        StreamTextFile.saveTextFile("/__/_1",imgDoc(new int[][]{new int[]{1}},_m),_m.getStreams());
        StreamTextFile.saveTextFile("/__/_2",imgDoc(new int[][]{new int[]{2}},_m),_m.getStreams());
        StreamTextFile.saveTextFile("/__/_3",imgDoc(new int[][]{new int[]{3}},_m),_m.getStreams());
        return f_;
    }

    private void messages(MockProgramInfos _m) {
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(_m.getTranslations()));
        MessagesGuiFct.enTr(MessagesGuiFct.initAppliTr(_m.getTranslations().getMapping().getVal(EN)));
        MessagesGuiFct.frTr(MessagesGuiFct.initAppliTr(_m.getTranslations().getMapping().getVal(FR)));
    }

}
