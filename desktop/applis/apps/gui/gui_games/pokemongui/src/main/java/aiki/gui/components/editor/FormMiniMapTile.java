package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.util.*;
import code.gui.*;
import code.gui.initialize.*;

public final class FormMiniMapTile {
    private GeneComponentModelImgSelect file;
    private AbsSpinner place;
    private AbsCustCheckBox heros;
    private AbsButton match;
    private AbsButton remove;
    private AbsPanel form;
    private TileMiniMap edited;
    public void build(AbstractProgramInfos _api, FacadeGame _f, SubscribedTranslationMessagesFactoryImgName _i, FormMiniMapGrid _grid, int _x, int _y) {
        AbsCompoFactory c_ = _api.getCompoFactory();
        form = c_.newPageBox();
        file = new GeneComponentModelImgSelect(_api,_f,_i);
        form.add(file.gene());
        place = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form.add(place);
        heros = c_.newCustCheckBox();
        form.add(heros);
        match = c_.newPlainButton("\u2611");
        match.setForeground(GuiConstants.GREEN);
        match.addActionListener(new ApplyTileMiniMapEvent(_grid,this,_f,_x,_y, false));
        form.add(match);
        remove = c_.newPlainButton("\u2611");
        remove.addActionListener(new ApplyTileMiniMapEvent(_grid,this,_f,_x,_y, true));
        form.add(remove);
    }
    public void feedForm(TileMiniMap _e) {
        edited = ConverterCommonMapUtil.copyTileMiniMap(_e);
        file.updateValue(edited.getFile());
        heros.setSelected(edited.isHeros());
        place.setValue(edited.getPlace());
    }
    public void feedForm() {
        edited = Instances.newTileMiniMap();
    }
    public TileMiniMap buildEntity() {
        edited.setPlace((short) getPlace().getValue());
        edited.setHeros(getHeros().isSelected());
        edited.setFile(getFile().getName().tryRet());
        return edited;
    }

    public GeneComponentModelImgSelect getFile() {
        return file;
    }

    public AbsSpinner getPlace() {
        return place;
    }

    public AbsCustCheckBox getHeros() {
        return heros;
    }

    public AbsButton getMatch() {
        return match;
    }

    public AbsButton getRemove() {
        return remove;
    }

    public AbsPanel getForm() {
        return form;
    }
}
