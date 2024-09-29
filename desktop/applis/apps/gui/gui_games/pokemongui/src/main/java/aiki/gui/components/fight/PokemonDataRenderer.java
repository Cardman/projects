package aiki.gui.components.fight;


import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.gui.components.walk.IntTileRender;
import code.gui.AbsCustCellRenderGene;
import code.gui.ColorsGroupList;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.util.core.NumberUtil;

public class PokemonDataRenderer implements AbsCustCellRenderGene<String> {

    private final IntTileRender render;
    private int sideLength;
    private int height;

    private final FacadeGame facade;

    private String noEvo;

    private String name;

    private boolean selected;

    private AbstractImage pkImage;
    private final AbstractImageFactory fact;
    private int maxWidthName;

    public PokemonDataRenderer(AbstractImageFactory _fact, FacadeGame _facade, String _noEvo, IntTileRender _tileRender) {
        facade = _facade;
        noEvo = _noEvo;
        fact = _fact;
        render = _tileRender;
    }

    public void initWidth(int _width) {
        sideLength = facade.getMap().getSideLength();
        maxWidthName = _width;
    }
    public void setNoEvo(String _noEvo) {
        noEvo = _noEvo;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        if (!_info.isEmpty()) {
            name = facade.translatePokemon(_info);
            int[][] img_ = facade.getData().getMiniPk().getVal(_info).getImage();
//            pkImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            pkImage = render.render(fact,img_, sideLength, sideLength);
        } else {
            name = DataBase.EMPTY_STRING;
            pkImage = null;
        }
        height = NumberUtil.max(_lab.getRealSize() + 2, sideLength);
        int w_ = NumberUtil.max(100,sideLength+maxWidthName);
        AbstractImage i_ = fact.newImageRgb(w_, height);
        i_.setFont(_lab);
        paintComponent(i_, w_);
        return i_;
    }

    public void paintComponent(AbstractImage _g, int _w) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, _w - 1, getHeight() - 1);
        if (pkImage != null) {
            _g.drawImage(pkImage, 0, 0);
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(name, sideLength, getHeight() - 2);
        } else {
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(noEvo, 0, getHeight() - 2);
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, _w - 1, getHeight() - 1);
        }
    }

    public String getNoEvo() {
        return noEvo;
    }

    public int getHeight() {
        return height;
    }

}
