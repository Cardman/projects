package aiki.gui;

import aiki.facade.*;
import aiki.gui.components.editor.*;
import aiki.instances.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.sml.*;
import code.gui.files.*;
import code.mock.*;
import code.stream.*;
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
    public void place1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getPlaceKind(),MessagesEditorSelect.PLACE_CITY);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getPlaceKind(),MessagesEditorSelect.PLACE_ROAD);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getPlaceKind(),MessagesEditorSelect.PLACE_CAVE);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("_",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getPlaceKind(),MessagesEditorSelect.PLACE_LEAGUE);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("_");
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
        ConverterCommonMapUtil.trigger(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getPlaceKind(),MessagesEditorSelect.PLACE_CITY);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("__");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        assertEq("__",facade_.getData().getMap().getPlace(0).getName());
    }
    @Test
    public void place8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAdd());
        ConverterCommonMapUtil.trigger(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getPlaceKind(),MessagesEditorSelect.PLACE_CITY);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("_");
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getName().valueString("__");
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(false);
        area(sub_).applyChange();
        tryClick(area(sub_).getMult().getWalk().getCrud().getAdd());
        tryClick(listMult(sub_).getForm().getCrud().getAdd());
        sub(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(listMult(sub_).getForm().getCrud().getValidAddEdit());
        tryClick(area(sub_).getMult().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonList().size());
        assertEq(0,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonFishingList().size());
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonList().get(0).size());
        assertEq(P_2,pl_.getLevelRoad().getWildPokemonAreas().get(0).getWildPokemonList().get(0).get(0).getName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(0));
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        Road pl_ = (Road) facade_.getData().getMap().getPlace(0);
        assertEq(1,pl_.getLevelRoad().getWildPokemonAreas().size());
        AreaApparition a_ = (AreaApparition) pl_.getLevelRoad().getWildPokemonAreas().get(0);
        assertEq(1,a_.getWildPokemon().size());
        assertEq(0,a_.getWildPokemonFishing().size());
        assertEq(P_2,a_.getWildPokemon().get(0).getName());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(0));
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAllButtons().get(0));
    }
    @Test
    public void wild3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidRemove());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidRemove());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(1));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidRemove());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(1);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidRemove());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(1));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidRemove());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getIndexApparition().setValue(1);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAdd());
        area(sub_).getSingle().setSelected(true);
        area(sub_).applyChange();
        tryClick(area(sub_).getSimple().getWalk().getCrud().getAdd());
        listSimple(sub_).getFormWildPk().getName().setupValue(P_2);
        tryClick(area(sub_).getSimple().getWalk().getCrud().getValidAddEdit());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getValidAddEdit());
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getValidRemove());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(0,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        assertEq(2,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getValue(0).getHeight());
        assertEq(2,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getValue(0).getWidth());
    }
    @Test
    public void blocks4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
        assertEq(2,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getValue(0).getHeight());
        assertEq(2,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getValue(0).getWidth());
    }
    @Test
    public void blocks5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getRows().setValue(1);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getCols().setValue(1);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getApplyPrepend());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getVal(newPoint(-1,-1)).getWidth());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getVal(newPoint(-1,-1)).getHeight());
    }
    @Test
    public void blocks6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims().setText("-1:-1");
        enterTextField(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getVal(newPoint(0,0)).getWidth());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getVal(newPoint(0,0)).getHeight());
    }
    @Test
    public void blocks7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getRows().setValue(1);
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getCols().setValue(1);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getApplyAppend());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),sub_.getFormDataMap().getMiniMapGrid().sideTile(),sub_.getFormDataMap().getMiniMapGrid().sideTile());
        ((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims().setText("2:2");
        enterTextField(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getDims());
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(2,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getVal(newPoint(1,1)).getWidth());
        assertEq(2,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().getVal(newPoint(1,1)).getHeight());
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
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getMatch());
        assertEq(1,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
    }
    @Test
    public void blocks9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getMap().addPlace(Instances.newRoad());
        WindowPkEditor sub_ = window(pr_, facade_);
        tryClick(sub_.getFormDataMap().getCrudPlace().getAllButtons().get(0));
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getGrid(),0,0);
        tryClick(((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getFormBlockTile().getRemove());
        assertEq(0,((GeneComponentModelPlace)sub_.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevel().getEdited().size());
    }
    private void tryClickTile(WindowPkEditor _sub) {
        int side_ = _sub.getFormDataMap().getMiniMapGrid().sideTile();
        tryClick(_sub.getFormDataMap().getMiniMapGrid().getGrid(),side_+1,side_+1);
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
        return (GeneComponentModelSubscribeArea) ((GeneComponentModelPlace) _w.getFormDataMap().getCrudPlace().getGene()).getRoad().getLevelWithWild().getAreas().getGenePair().getKey();
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
    private CrudGeneFormEntImgFree crudMiniMap(WindowPkEditor _crud) {
        tryClick(_crud.getImgMiniMapMenu());
        return _crud.getCrudGeneFormMiniMap();
    }

    private CrudGeneFormEntImgFree crudBlocks(WindowPkEditor _crud) {
        tryClick(_crud.getImgBlocksMenu());
        return _crud.getCrudGeneFormBlocks();
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
