package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.UsesOfMove;
import aiki.game.fight.ChosenMoveInfos;
import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.images.ConverterBufferedImage;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class MoveLabel extends PaintableLabel {

    private static final String SPACE = " ";

    private static final String RATIO = "/";

    private String move;

    private ChosenMoveInfos infos;

    private boolean selected;

    private NatStringTreeMap<Color> colorsTypes;

    private String text;

    public MoveLabel(ChosenMoveInfos _infos, String _move, FacadeGame _facade) {
        colorsTypes = new NatStringTreeMap<Color>();
        move = _move;
        infos = _infos;
        UsesOfMove uses_ = infos.getUses();
        String usesStr_ = StringList.concat(Long.toString(uses_.getCurrent()),RATIO,Long.toString(uses_.getMax()));
        StringList types_ = new StringList();
        for (String t: infos.getTypes()) {
            String type_ = _facade.translateType(t);
            String rgb_ = _facade.getData().getTypesColors().getVal(t);
            Color c_ = new Color(ConverterBufferedImage.getIntColor(rgb_, DataBase.SEPARATOR_RGB));
            colorsTypes.put(type_, c_);
            types_.add(type_);
        }
        text = StringList.concat(move,SPACE, StringList.join(types_, SPACE),SPACE,usesStr_);
        setOpaque(true);
        setPreferredSize(new Dimension(150, 20));
    }

    public void setSelected(String _move) {
        selected = StringList.quickEq(infos.getName(), _move);
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        if (infos.isUsable()) {
            _g.setColor(Color.BLACK);
        } else {
            _g.setColor(Color.GRAY);
        }
        _g.drawString(text, 0, 10);
        int x_ = 0;
        for (String t: colorsTypes.getKeys()) {
            _g.setColor(colorsTypes.getVal(t));
            int xLeft_ = x_;
            _g.fillRect(xLeft_, 10, 20, 10);
            x_ += 20;
        }
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
