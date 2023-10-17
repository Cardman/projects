package aiki.gui.components.fight;



import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import code.gui.*;
import code.gui.images.*;

public class PokemonDataRenderer implements AbsCustCellRenderGene<String> {

    private final int sideLength;
    private int height;

    private final FacadeGame facade;

    private String noEvo;

    private String name;

    private boolean selected;

    private AbstractImage pkImage;
    private final AbstractImageFactory fact;

    public PokemonDataRenderer(AbstractImageFactory _fact,FacadeGame _facade, String _noEvo) {
        facade = _facade;
        sideLength = facade.getMap().getSideLength();
        noEvo = _noEvo;
        fact = _fact;
    }

    public void setNoEvo(String _noEvo) {
        noEvo = _noEvo;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        if (!_info.isEmpty()) {
            name = facade.translatePokemon(_info);
            int[][] img_ = facade.getData().getMiniPk().getVal(_info);
            pkImage = ConverterGraphicBufferedImage.decodeToImage(fact,img_);
            height = pkImage.getHeight();
        } else {
            name = DataBase.EMPTY_STRING;
            pkImage = null;
            height = sideLength;
        }
        AbstractImage i_ = fact.newImageRgb(100, height);
        paintComponent(i_);
        return i_;
    }

    public AbstractImageFactory getImageFactory() {
        return fact;
    }

    public void paintComponent(AbstractImage _g) {
        if (!name.isEmpty()) {
            _g.drawImage(pkImage, 0, 0);
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(name, sideLength, getHeight());
        } else {
            _g.setColor(GuiConstants.WHITE);
            _g.fillRect(0, 0, 100 - 1, getHeight() - 1);
            _g.setColor(GuiConstants.BLACK);
            _g.drawString(noEvo, 0, getHeight());
        }
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, 100 - 1, getHeight() - 1);
        }
    }

    public int getHeight() {
        return height;
    }

}
