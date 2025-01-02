package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.sml.*;
import aiki.util.*;
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
        sBloc_.setHeight(1);
        sBloc_.setWidth(1);
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
        sBloc_.setHeight(1);
        sBloc_.setWidth(1);
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
        sBloc_.setHeight(1);
        sBloc_.setWidth(1);
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
        sBloc_.setHeight(1);
        sBloc_.setWidth(1);
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
    @Test
    public void linkPlaceCave1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }

    @Test
    public void linkPlaceCave2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkPlaceCave3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkPlaceCave4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkPlaceCave5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkPlaceCave6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getRemoveTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkPlaceCave7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getRemoveTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkPlaceCave8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getRemoveTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkPlaceCave9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getRemoveTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkPlaceCave10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getRemoveTileBoth());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkPlaceCave11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        basic(facade_);
        Cave cave_ = ((Cave) facade_.getMap().getPlace(1));
        LevelCave first_ = cave_.getLevels().get(1);
        first_.getItems().addEntry(newPoint(1,1),I_1);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth().isEnabled());
    }
    @Test
    public void linkPlaceCave12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        basic(facade_);
        Road road_ = ((Road) facade_.getMap().getPlace(0));
        LevelRoad first_ = road_.getLevelRoad();
        first_.getItems().addEntry(newPoint(0,0),I_1);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth().isEnabled());
    }
    @Test
    public void linkPlaceCave13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getClose());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getAdd().isEnabled());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd().isEnabled());
    }
    @Test
    public void linkPlaceCave14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        assertEq("_",road_.getLinksWithCaves().getValue(0).getFileName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameFirst().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getMatchLinkLeft());
        assertEq("__",road_.getLinksWithCaves().getValue(0).getFileName());
    }
    @Test
    public void linkPlaceCave15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight());
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq("_",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameSecond().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getMatchLinkRight());
        assertEq("__",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
    }
    @Test
    public void linkPlaceCave16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("___",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileBoth());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq("_",road_.getLinksWithCaves().getValue(0).getFileName());
        assertEq("_",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameFirst().updateValue("__");
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameSecond().updateValue("___");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getMatchLinkBoth());
        assertEq("__",road_.getLinksWithCaves().getValue(0).getFileName());
        assertEq("___",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
    }
    @Test
    public void linkPlaceCave17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameFirst().updateValue("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileLeft());
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameFirst().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getMatchLinkLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        assertEq("_",road_.getLinksWithCaves().getValue(0).getFileName());
    }
    @Test
    public void linkPlaceCave18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(last(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameSecond().updateValue("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getAddTileRight());
        sub_.getFormDataMap().getCrudPlace().getLinks().getLinkFileNameSecond().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinks().getMatchLinkRight());
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq("_",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
    }
    @Test
    public void linkCavePlace1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }

    @Test
    public void linkCavePlace2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkCavePlace3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAllButtons().get(0));
        tryClicks(((CrudGeneFormLevelCave)sub_.getFormDataMap().getCrudPlace().getLevels().get(0)).getGenePair().getWild().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getCancel());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCavePlace4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCavePlace5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCavePlace6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getRemoveTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(1,road_.getLinksWithCaves().size());
        assertEq(0,road_.getLinksWithCaves().getKey(0).getx());
        assertEq(0,road_.getLinksWithCaves().getKey(0).gety());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getLevelIndex());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().getx());
        assertEq(1,road_.getLinksWithCaves().getValue(0).getCoords().getLevel().getPoint().gety());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkCavePlace7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getRemoveTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(1,cave_.getLinksWithOtherPlaces().getList().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().getx());
        assertEq(0,cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkCavePlace8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getRemoveTileLeft());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkCavePlace9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getRemoveTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkCavePlace10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getRemoveTileBoth());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq(0,road_.getLinksWithCaves().size());
        assertEq(0,cave_.getLinksWithOtherPlaces().getList().size());
    }
    @Test
    public void linkCavePlace11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        basic(facade_);
        Cave cave_ = ((Cave) facade_.getMap().getPlace(1));
        LevelCave first_ = cave_.getLevels().get(1);
        first_.getItems().addEntry(newPoint(1,1),I_1);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth().isEnabled());
    }
    @Test
    public void linkCavePlace12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        basic(facade_);
        Road road_ = ((Road) facade_.getMap().getPlace(0));
        LevelRoad first_ = road_.getLevelRoad();
        first_.getItems().addEntry(newPoint(0,0),I_1);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight().isEnabled());
        assertFalse(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth().isEnabled());
    }
    @Test
    public void linkCavePlace13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getClose());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getAdd().isEnabled());
        assertTrue(sub_.getFormDataMap().getCrudPlace().getLevels().get(0).getAdd().isEnabled());
    }
    @Test
    public void linkCavePlace14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft());
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq("_",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameFirst().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getMatchLinkLeft());
        assertEq("__",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
    }
    @Test
    public void linkCavePlace15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        assertEq("_",road_.getLinksWithCaves().getValue(0).getFileName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameSecond().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getMatchLinkRight());
        assertEq("__",road_.getLinksWithCaves().getValue(0).getFileName());
    }
    @Test
    public void linkCavePlace16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("___",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileBoth());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq("_",road_.getLinksWithCaves().getValue(0).getFileName());
        assertEq("_",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameFirst().updateValue("___");
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameSecond().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getMatchLinkBoth());
        assertEq("__",road_.getLinksWithCaves().getValue(0).getFileName());
        assertEq("___",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
    }
    @Test
    public void linkCavePlace17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameFirst().updateValue("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileLeft());
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameFirst().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getMatchLinkLeft());
        Cave cave_ = (Cave) facade_.getMap().getPlace(1);
        assertEq("_",cave_.getLinksWithOtherPlaces().getVal(newLevelPoint(1,1, 1)).getFileName());
    }
    @Test
    public void linkCavePlace18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        basic(facade_);
        facade_.getData().getLinks().addEntry("_",instance(new int[1][1]));
        facade_.getData().getLinks().addEntry("__",instance(new int[1][1]));
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(lastRev(sub_));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsPlace().get(0).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getLevelsCave().get(1).getGrid(),facade_.getData().getMap().getSideLength(),facade_.getData().getMap().getSideLength());
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameSecond().updateValue("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getAddTileRight());
        sub_.getFormDataMap().getCrudPlace().getLinksRev().getLinkFileNameSecond().updateValue("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getLinksRev().getMatchLinkRight());
        InitializedPlace road_ = (InitializedPlace) facade_.getMap().getPlace(0);
        assertEq("_",road_.getLinksWithCaves().getValue(0).getFileName());
    }
    @Test
    public void linkLeague1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        f_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        s_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevels().get(2).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getClose());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getBegin().getPoint().getx());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getBegin().getPoint().gety());
        assertEq(1,((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().getNumberPlace());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().getLevel().getLevelIndex());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().getLevel().getPoint().getx());
        assertEq(0,((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().getLevel().getPoint().gety());
    }
    @Test
    public void linkLeague2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        f_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        f_.setTrainerCoords(newPoint(0,0));
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        s_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getItems().addEntry(newPoint(0,0),I_1);
        facade_.getData().getMap().addPlace(road_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevels().get(2).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getClose());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getBegin().isDefined());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().isValid());
    }
    @Test
    public void linkLeague3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        f_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        s_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        l_.getRooms().add(s_);
        facade_.getData().getMap().addPlace(l_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevels().get(2).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getClose());
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevels().get(2).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getClose());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getBegin().isDefined());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().isValid());
    }
    @Test
    public void linkLeague4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMiniItems().addEntry(I_1, instance(new int[1][1]));
        League l_ = Instances.newLeague();
        LevelLeague f_ = Instances.newLevelLeague();
        f_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        f_.setTrainerCoords(newPoint(0,0));
        l_.getRooms().add(f_);
        LevelLeague s_ = Instances.newLevelLeague();
        s_.getBlocks().addEntry(newPoint(0,0), newBlock(2,2, EnvironmentType.ROAD,"",-1));
        l_.getRooms().add(s_);
        l_.setBegin(newPoint(0,0));
        l_.setAccessCoords(newCoords(1,0,0,0));
        facade_.getData().getMap().addPlace(l_);
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getItems().addEntry(newPoint(0,0),I_1);
        facade_.getData().getMap().addPlace(road_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevel().getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getLevels().get(2).getGrid(),0,0);
        tryClick(sub_.getFormDataMap().getCrudPlace().getLeagueLink().getClose());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getBegin().isDefined());
        assertFalse(((League)facade_.getData().getMap().getPlace(0)).getAccessCoords().isValid());
    }
    @Test
    public void joinPlaces1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),3*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),3*side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),3*side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),3*side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(1, crudJoin(sub_).getRight());
        selectEvent(0, crudJoin(sub_).getLeft());
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),3*side(facade_));
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(1, crudJoin(sub_).getRight());
        selectEvent(0, crudJoin(sub_).getLeft());
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),side(facade_));
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),3*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(1, crudJoin(sub_).getRight());
        selectEvent(0, crudJoin(sub_).getLeft());
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),3*side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(1, crudJoin(sub_).getRight());
        selectEvent(0, crudJoin(sub_).getLeft());
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),3*side(facade_),2*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),side(facade_)+1,side(facade_)+1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),side(facade_)+1,side(facade_)+1);
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.UP));
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(1,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(1,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(1,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),1,1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_)+1,2*side(facade_)+1);
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.UP));
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),1,2*side(facade_)+1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_)+1,1);
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.UP));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),side(facade_)+1,side(facade_)+1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),side(facade_)+1,side(facade_)+1);
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.UP));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(1,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(1,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(1,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),side(facade_)+1,side(facade_)+1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),side(facade_)+1,side(facade_)+1);
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.DOWN));
        assertFalse(crudJoin(sub_).getJoinPlacesButton().isEnabled());
    }
    @Test
    public void joinPlaces14() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),0);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),4*side(facade_)-1);
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces15() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),4*side(facade_)-1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),0);
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces16() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),0,2*side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),4*side(facade_)-1,2*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces17() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),4*side(facade_)-1,2*side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),0,2*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces18() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),0,0);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),3*side(facade_)-1,3*side(facade_)-1);
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.UP));
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces19() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),0,3*side(facade_)-1);
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),3*side(facade_)-1,0);
        tryClick(crudJoin(sub_).getSelDirLeftButtons().getVal(Direction.DOWN));
        tryClick(crudJoin(sub_).getSelDirRightButtons().getVal(Direction.UP));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        assertEq(1,saved(facade_,0).size());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void joinPlaces20() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(-1, crudJoin(sub_).getLeft());
        selectEvent(-1, crudJoin(sub_).getRight());
        assertFalse(crudJoin(sub_).getJoinPlacesButton().isEnabled());
    }
    @Test
    public void joinPlaces21() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinImpair(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(0, crudJoin(sub_).getRight());
        assertFalse(crudJoin(sub_).getJoinPlacesButton().isEnabled());
    }
    @Test
    public void joinPlaces22() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoin(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),3*side(facade_));
        tryClick(crudJoin(sub_).getJoinPlacesButton());
        selectEvent(0, crudJoin(sub_).getLeft());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getLevelLeft().getGrid(),2*side(facade_),side(facade_));
        tryClick(crudJoin(sub_).getLevelRight().getGrid(),2*side(facade_),3*side(facade_));
        assertFalse(crudJoin(sub_).getJoinPlacesButton().isEnabled());
        assertEq(1,saved(facade_,0).size());
        assertEq(2,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,1).size());
        assertEq(2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void unjoinPlaces1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwice(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(0, crudJoin(sub_).getLeft());
        tryClick(crudJoin(sub_).getUnjoinPlacesButton());
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getUnjoinPlacesButton());
        assertEq(0,saved(facade_,0).size());
        assertEq(1,saved(facade_,1).size());
        assertEq(1,saved(facade_,2).size());
    }
    @Test
    public void unjoinPlaces2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwice(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(joinPlaces(sub_));
        selectEvent(1, crudJoin(sub_).getRight());
        tryClick(crudJoin(sub_).getUnjoinPlacesButton());
        selectEvent(0, crudJoin(sub_).getLeft());
        tryClick(crudJoin(sub_).getUnjoinPlacesButton());
        assertEq(0,saved(facade_,0).size());
        assertEq(1,saved(facade_,1).size());
        assertEq(1,saved(facade_,2).size());
    }
    @Test
    public void editAfterJoin1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwice(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getCols().setValue(2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),4*side(facade_),0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),4*side(facade_),2*side(facade_));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(2,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(5,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(1).getPlaceInterConnect().getDir().getDirName());
        assertEq(2,saved(facade_,1).getList().get(1).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,2).size());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,2).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,2).getList().get(0).getCoords().getNumberPlace());
        assertEq(5,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void editAfterJoin2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwiceVert(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getCols().setValue(2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),side(facade_),0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),side(facade_),2*side(facade_));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(2,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(2,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(1).getPlaceInterConnect().getDir().getDirName());
        assertEq(2,saved(facade_,1).getList().get(1).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,2).size());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,2).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,2).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void editAfterJoin3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwiceHoriz(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getCols().setValue(2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,side(facade_));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),2*side(facade_),side(facade_));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(2,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(1).getPlaceInterConnect().getDir().getDirName());
        assertEq(2,saved(facade_,1).getList().get(1).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,2).size());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,2).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,2).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void editAfterJoin4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwice(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getCols().setValue(2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getApplyPrepend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),0,2*side(facade_));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(-2,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(2,saved(facade_,1).size());
        assertEq(-2,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(1).getPlaceInterConnect().getDir().getDirName());
        assertEq(2,saved(facade_,1).getList().get(1).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,2).size());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,2).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,2).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().gety());
    }
    @Test
    public void editAfterJoin5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinFourth(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(1));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getCols().setValue(2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getApplyAppend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),4*side(facade_),0);
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getGrid(),4*side(facade_),2*side(facade_));
        sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getCity().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(4,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(5,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().getx());
        assertEq(2,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(1).getPlaceInterConnect().getDir().getDirName());
        assertEq(2,saved(facade_,1).getList().get(1).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().gety());


        assertEq(1,saved(facade_,1).getList().get(2).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(2).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,1).getList().get(2).getPlaceInterConnect().getDir().getDirName());
        assertEq(3,saved(facade_,1).getList().get(2).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(2).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(2).getCoords().getLevel().getPoint().gety());

        assertEq(2,saved(facade_,1).getList().get(3).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(3).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,1).getList().get(3).getPlaceInterConnect().getDir().getDirName());
        assertEq(4,saved(facade_,1).getList().get(3).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,1).getList().get(3).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(3).getCoords().getLevel().getPoint().gety());

        assertEq(1,saved(facade_,2).size());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,2).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,2).getList().get(0).getCoords().getNumberPlace());
        assertEq(5,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(2,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().gety());

        assertEq(1,saved(facade_,3).size());
        assertEq(3,saved(facade_,3).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,3).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_DOWN,saved(facade_,3).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,3).getList().get(0).getCoords().getNumberPlace());
        assertEq(1,saved(facade_,3).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,3).getList().get(0).getCoords().getLevel().getPoint().gety());

        assertEq(1,saved(facade_,4).size());
        assertEq(0,saved(facade_,4).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,4).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_UP,saved(facade_,4).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,4).getList().get(0).getCoords().getNumberPlace());
        assertEq(2,saved(facade_,4).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,4).getList().get(0).getCoords().getLevel().getPoint().gety());

    }
    @Test
    public void editAfterJoin6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildJoinTwice(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(2));
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getCols().setValue(2);
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getApplyPrepend());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,0);
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClicks(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getGrid(),0,2*side(facade_));
        sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(sub_.getFormDataMap().getCrudPlace().getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq(1,saved(facade_,0).size());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,0).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,0).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,0).getList().get(0).getCoords().getNumberPlace());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,0).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(2,saved(facade_,1).size());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,1).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,1).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(0,saved(facade_,1).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,1).getList().get(0).getCoords().getLevel().getPoint().gety());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().getx());
        assertEq(3,saved(facade_,1).getList().get(1).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_RIGHT,saved(facade_,1).getList().get(1).getPlaceInterConnect().getDir().getDirName());
        assertEq(2,saved(facade_,1).getList().get(1).getCoords().getNumberPlace());
        assertEq(-2,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().getx());
        assertEq(0,saved(facade_,1).getList().get(1).getCoords().getLevel().getPoint().gety());
        assertEq(1,saved(facade_,2).size());
        assertEq(-2,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().getx());
        assertEq(0,saved(facade_,2).getList().get(0).getPlaceInterConnect().getSource().gety());
        assertEq(DataBase.DEF_DIR_LEFT,saved(facade_,2).getList().get(0).getPlaceInterConnect().getDir().getDirName());
        assertEq(1,saved(facade_,2).getList().get(0).getCoords().getNumberPlace());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().getx());
        assertEq(3,saved(facade_,2).getList().get(0).getCoords().getLevel().getPoint().gety());
    }

    @Test
    public void accessCondition1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildAccess(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(crudAccessButton(sub_));
        tryClick(crudAccess(sub_).getLevels().get(0).getGrid(),0,0);
        tryToggle((AbsCustCheckBox) crudAccess(sub_).getTrainersForm().getComponent(0));
        tryClick(crudAccess(sub_).getValidateAccess());
        tryClick(crudAccess(sub_).getClose());
        assertEq(1,facade_.getMap().getAccessCondition().size());
        assertEq(1,facade_.getMap().getAccessCondition().getVal(newCoords(0,0,0,0)).size());
        assertTrue(crudAccessButton(sub_).isEnabled());
    }

    @Test
    public void accessCondition2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildAccess(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(crudAccessButton(sub_));
        tryClick(crudAccess(sub_).getLevels().get(0).getGrid(),0,0);
        tryToggle((AbsCustCheckBox) crudAccess(sub_).getTrainersForm().getComponent(0));
        tryClick(crudAccess(sub_).getValidateAccess());
        tryClick(crudAccess(sub_).getClose());
        tryClick(crudAccessButton(sub_));
        tryClick(crudAccess(sub_).getClearAccess());
        tryClick(crudAccess(sub_).getClose());
        assertEq(0,facade_.getMap().getAccessCondition().size());
        assertTrue(crudAccessButton(sub_).isEnabled());
    }

    @Test
    public void accessCondition3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildAccess(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(crudAccessButton(sub_));
        tryClick(crudAccess(sub_).getLevels().get(0).getGrid(),0,0);
        tryToggle((AbsCustCheckBox) crudAccess(sub_).getTrainersForm().getComponent(0));
        tryClick(crudAccess(sub_).getValidateAccess());
        tryClick(crudAccess(sub_).getClose());
        tryClick(crudAccessButton(sub_));
        tryClick(crudAccess(sub_).getLevels().get(0).getGrid(),0,0);
        tryToggle((AbsCustCheckBox) crudAccess(sub_).getTrainersForm().getComponent(0));
        tryClick(crudAccess(sub_).getValidateAccess());
        tryClick(crudAccess(sub_).getClose());
        assertEq(0,facade_.getMap().getAccessCondition().size());
        assertTrue(crudAccessButton(sub_).isEnabled());
    }
    @Test
    public void beginGame() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        buildAccess(facade_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(crudBeginButton(sub_));
        tryClick(crudBegin(sub_).getLevels().get(0).getGrid(),0,0);
        tryClick(crudBegin(sub_).getClose());
        assertEq(0,facade_.getMap().getBegin().getNumberPlace());
        assertEq(0,facade_.getMap().getBegin().getLevel().getLevelIndex());
        assertEq(0,facade_.getMap().getBegin().getLevel().getPoint().getx());
        assertEq(0,facade_.getMap().getBegin().getLevel().getPoint().gety());
    }
    private ContentComponentModelAccessCondition crudBegin(WindowPkEditor _sub) {
        return _sub.getFormDataMap().getCrudPlace().getBeginGame();
    }

    private AbsButton crudBeginButton(WindowPkEditor _sub) {
        return _sub.getFormDataMap().getCrudPlace().getBeginGameButton();
    }
    private ContentComponentModelAccessCondition crudAccess(WindowPkEditor _sub) {
        return _sub.getFormDataMap().getCrudPlace().getAccessCondition();
    }

    private AbsButton crudAccessButton(WindowPkEditor _sub) {
        return _sub.getFormDataMap().getCrudPlace().getAccessConditionButton();
    }
    private static PlaceInterConnects saved(FacadeGame _fac, int _i) {
        return ((InitializedPlace)_fac.getMap().getPlace(_i)).getSavedlinks();
    }
    private void selectEvent(int _index, GeneComponentModelLs<EditedCrudPair<Coords, InitializedPlace>> _part) {
        _part.getSelect().select(_index);
        _part.getSelect().events();
    }

    private ContentComponentModelUniqLevelLinks crudJoin(WindowPkEditor _sub) {
        return _sub.getFormDataMap().getCrudPlace().getJoinPlaces();
    }

    private void buildAccess(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        DualFight firstDual_ = Instances.newDualFight();
        firstDual_.setPt(newPoint(0,2));
        road_.getLevelRoad().getDualFights().addEntry(newPoint(0,3), firstDual_);
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        Gym firstLeader_ = Instances.newGym();
        firstLeader_.getIndoor().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        firstLeader_.getIndoor().setGymLeaderCoords(newPoint(1,1));
        city_.getBuildings().addEntry(newPoint(0,0), firstLeader_);
        city_.getBuildings().addEntry(newPoint(1,1),Instances.newGym());
        city_.getBuildings().addEntry(newPoint(2,2),Instances.newPokemonCenter());
        _facade.getMap().addPlace(city_);
        Cave cave_ = Instances.newCave();
        LevelCave levelCave_ = Instances.newLevelCave();
        levelCave_.getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        levelCave_.getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        levelCave_.getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        levelCave_.getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        DualFight dual_ = Instances.newDualFight();
        dual_.setPt(newPoint(2,0));
        levelCave_.getDualFights().addEntry(newPoint(3,0), dual_);
        cave_.getLevels().add(levelCave_);
        _facade.getMap().addPlace(cave_);
        League league_ = Instances.newLeague();
        league_.setBegin(newPoint(1,1));
        LevelLeague levelLeague_ = Instances.newLevelLeague();
        levelLeague_.getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        levelLeague_.getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        levelLeague_.getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        levelLeague_.getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        league_.getRooms().add(levelLeague_);
        _facade.getMap().addPlace(league_);
        _facade.getMap().addPlace(Instances.newLeague());
    }
    private void buildJoin(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(city_);
        _facade.getMap().addPlace(Instances.newCave());
    }

    private void buildJoinImpair(FacadeGame _facade) {
        _facade.getMap().setSideLength(3);
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,1),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(1,0),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(1,1),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(1,2),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,1),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,1),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,2),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(1,0),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(1,1),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(1,2),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,0),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,1),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,2),newBlock(1, 1,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(city_);
        _facade.getMap().addPlace(Instances.newCave());
    }

    private void buildJoinTwice(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(city_);
        Road road2_ = Instances.newRoad();
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road2_);
        _facade.getMap().addPlace(Instances.newCave());
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,3),Direction.RIGHT),newCoords(1,0,0,0));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,3,3));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,3),Direction.RIGHT),newCoords(2,0,0,0));
        road2_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(1,0,3,3));
    }

    private void buildJoinTwiceVert(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(2, 1,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,2),newBlock(2, 1,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(city_);
        Road road2_ = Instances.newRoad();
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road2_);
        _facade.getMap().addPlace(Instances.newCave());
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,3),Direction.RIGHT),newCoords(1,0,0,0));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,3,3));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,3),Direction.RIGHT),newCoords(2,0,0,0));
        road2_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(1,0,0,3));
    }

    private void buildJoinTwiceHoriz(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(1, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,0),newBlock(1, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(city_);
        Road road2_ = Instances.newRoad();
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road2_);
        _facade.getMap().addPlace(Instances.newCave());
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,3),Direction.DOWN),newCoords(1,0,0,0));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.UP),newCoords(0,0,3,3));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,0),Direction.DOWN),newCoords(2,0,0,0));
        road2_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.UP),newCoords(1,0,3,0));
    }

    private void buildJoinFourth(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road_);
        City city_ = Instances.newCity();
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        city_.getLevelOutdoor().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(city_);
        Road road2_ = Instances.newRoad();
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road2_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road2_);

        Road road3_ = Instances.newRoad();
        road3_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road3_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road3_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road3_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road3_);

        Road road4_ = Instances.newRoad();
        road4_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road4_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road4_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        road4_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),newBlock(2, 2,EnvironmentType.ROAD,ROAD, -1));
        _facade.getMap().addPlace(road4_);

        _facade.getMap().addPlace(Instances.newCave());
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,3),Direction.RIGHT),newCoords(1,0,0,0));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,3,3));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,2),Direction.RIGHT),newCoords(2,0,0,0));
        road2_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(1,0,3,2));

        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(1,0),Direction.UP),newCoords(3,0,3,3));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,3),Direction.DOWN),newCoords(4,0,0,0));
        road3_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(3,3),Direction.DOWN),newCoords(1,0,1,0));
        road4_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.UP),newCoords(1,0,2,3));
    }
    private Cave basic(FacadeGame _facade) {
        Road road_ = Instances.newRoad();
        _facade.getData().getMap().addPlace(road_);
        Cave cave_ = Instances.newCave();
        cave_.getLevels().add(blockLinksLevelCave());
        cave_.getLevels().add(blockLinksLevelCave());
        _facade.getData().getMap().addPlace(cave_);
        return cave_;
    }

    private AbsButton joinPlaces(WindowPkEditor _sub) {
        CustList<AbsButton> allButtons_ = _sub.getFormDataMap().getCrudPlace().getAllButtons();
        return allButtons_.get(allButtons_.size() - 3);
    }

    private AbsButton last(WindowPkEditor _sub) {
        CustList<AbsButton> allButtons_ = _sub.getFormDataMap().getCrudPlace().getAllButtons();
        return allButtons_.get(allButtons_.size() - 2);
    }

    private AbsButton lastRev(WindowPkEditor _sub) {
        return _sub.getFormDataMap().getCrudPlace().getAllButtons().last();
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
        bk_.setHeight(2);
        bk_.setWidth(2);
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
    static int side(FacadeGame _f) {
        return _f.getMap().getSideLength();
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
