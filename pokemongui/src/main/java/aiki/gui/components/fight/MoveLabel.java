package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

import code.images.ConverterBufferedImage;
import code.util.NatTreeMap;
import code.util.StringList;
import aiki.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.UsesOfMove;
import aiki.game.fight.ChosenMoveInfos;

public class MoveLabel extends JLabel {

    private static final String SPACE = " ";

    private static final String RATIO = "/";

    private String move;

    private ChosenMoveInfos infos;

    private boolean selected;

    private NatTreeMap<String,Color> colorsTypes;

    public MoveLabel(ChosenMoveInfos _infos, String _move, FacadeGame _facade) {
        colorsTypes = new NatTreeMap<String,Color>();
        move = _move;
        infos = _infos;
        UsesOfMove uses_ = infos.getUses();
        String usesStr_ = StringList.concat(Long.toString(uses_.getCurrent()),RATIO,Long.toString(uses_.getMax()));
        StringList types_ = new StringList();
        for (String t: infos.getTypes()) {
            String type_ = _facade.translateType(t);
            String rgb_ = _facade.getData().getTypesColors().getVal(t);
            Color c_ = ConverterBufferedImage.getColor(rgb_, DataBase.SEPARATOR_RGB);
            colorsTypes.put(type_, c_);
            types_.add(type_);
        }
        setText(StringList.concat(move,SPACE,types_.join(SPACE),SPACE,usesStr_));
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
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        if (infos.isUsable()) {
            _g.setColor(Color.BLACK);
        } else {
            _g.setColor(Color.GRAY);
        }
        _g.drawString(getText(), 0, 10);
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
