package aiki.gui.components.editor;

import aiki.comparators.ComparatorMiniMapCoords;
import aiki.db.*;
import aiki.facade.*;
import aiki.map.util.*;
import code.gui.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class FormMiniMapGrid {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsScrollPane container;
    private final AbsCommonFrame frame;
    private final SubscribedTranslationList translationList;
    private final FormMiniMapTile formMiniMapTile = new FormMiniMapTile();
    private AbsSpinner rows;
    private AbsSpinner cols;
    private AbsScrollPane element;
    private AbsButton apply;
    private int rowsCount;
    private int colsCount;
    private final IdList<SubscribedTranslation> translations = new IdList<SubscribedTranslation>();
    private final TreeMap<MiniMapCoords,AbsPaintableLabel> tiles = new TreeMap<MiniMapCoords,AbsPaintableLabel>(new ComparatorMiniMapCoords());
    public FormMiniMapGrid(AbstractProgramInfos _a, FacadeGame _f, AbsScrollPane _c, AbsCommonFrame _fr, SubscribedTranslationList _i) {
        api = _a;
        facadeGame = _f;
        container = _c;
        frame = _fr;
        translationList = _i;
    }
    public void setupGridDims() {
        rowsCount = NumberUtil.max(facadeGame.getMapHeight(),1);
        colsCount = NumberUtil.max(facadeGame.getMapWidth(),1);
    }

    public void addCells() {
        rowsCount += rows.getValue();
        colsCount += cols.getValue();
        setupGrid(true);
    }
    public void setupGrid(boolean _p) {
        AbsCompoFactory c_ = api.getCompoFactory();
        AbsPanel form_ = c_.newPageBox();
        rows = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form_.add(rows);
        cols = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        form_.add(cols);
        apply = c_.newPlainButton("_");
        apply.addActionListener(new AddCellsMiniMapCoordsListEvent(this));
        form_.add(apply);
        tiles.clear();
        AbsPanel grid_ = c_.newGrid(rowsCount, colsCount);
        int max_ = sideTile();
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < colsCount; j++) {
                MiniMapCoords key_ = new MiniMapCoords((short) j, (short) i);
                TileMiniMap info_ = facadeGame.getData().getMap().getMiniMap().getVal(key_);
                AbsPaintableLabel tile_ = c_.newAbsPaintableLabel();
                tile_.addMouseListener(new FormTileMiniMapEvent(this,j,i));
                AbstractImage icon_ = tryCenter(info_,max_);
                tile_.setIcon(api.getImageFactory(), icon_);
                grid_.add(tile_);
                tiles.put(key_,tile_);
            }
        }
        form_.add(grid_);
        element = c_.newAbsScrollPane();
        form_.add(element);
        container.setViewportView(form_);
        if (_p) {
            frame.pack();
        }
    }

    private AbstractImage tryCenter(TileMiniMap _e, int _max) {
        int[][] img_ = facadeGame.getData().getMiniMap(_e.getFile());
        if (img_.length == 0) {
            return api.getImageFactory().newImageArgb(_max, _max);
        }
        return ConverterGraphicBufferedImage.centerImage(api.getImageFactory(), img_, _max);
    }
    public void view(int _x, int _y) {
        formMiniMapTile.build(api,facadeGame,translationList.getImgRetrieverMiniMapSub(),this,_x,_y);
        FormDataMap.baseSelectImage(formMiniMapTile.getFile());
        IdList<SubscribedTranslation> subs_ = translationList.getSubscribedTranslations().getVal(frame);
        subs_.removeAllElements(translations);
        IdList<SubscribedTranslation> next_ = formMiniMapTile.getFile().subs();
        next_.add(new SubscribedTranslationRemovePlace(formMiniMapTile.getPlace()));
        subs_.addAllElts(next_);
        translations.addAllElts(next_);
        MiniMapCoordsTile e_ = facadeGame.getData().getMap().getMiniMap().getEntryByKey(new MiniMapCoords((short) _x, (short) _y));
        if (e_ != null) {
            formMiniMapTile.feedForm(e_.getTileMap());
            formMiniMapTile.getFile().updateValue(e_.getTileMap().getFile());
            formMiniMapTile.getHeros().setSelected(e_.getTileMap().isHeros());
            formMiniMapTile.getPlace().setValue(e_.getTileMap().getPlace());
        } else {
            formMiniMapTile.feedForm();
        }
        element.setViewportView(formMiniMapTile.getForm());
        frame.pack();
    }

    public int sideTile() {
        int max_ = facadeGame.getData().getMap().getSideLength();
        for (ImageArrayBaseSixtyFour i: facadeGame.getData().getMiniMap().values()) {
            max_ = NumberUtil.max(i.getImage().length,max_);
            max_ = NumberUtil.max(i.getImage()[0].length,max_);
        }
        return max_;
    }

    public FormMiniMapTile getFormMiniMapTile() {
        return formMiniMapTile;
    }

    public AbsSpinner getCols() {
        return cols;
    }

    public AbsSpinner getRows() {
        return rows;
    }

    public AbsButton getApply() {
        return apply;
    }

    public TreeMap<MiniMapCoords,AbsPaintableLabel> getTiles() {
        return tiles;
    }
}
