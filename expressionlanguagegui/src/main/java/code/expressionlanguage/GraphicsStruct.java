package code.expressionlanguage;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustGraphics;

import java.awt.*;

public final class GraphicsStruct implements Struct {
    private CustGraphics graphics;
    public GraphicsStruct(CustGraphics _g) {
        graphics = _g;
    }

    public Color getColor() {
        return graphics.getColor();
    }

    public void setColor(Color c) {
        graphics.setColor(c);
    }

    public Font getFont() {
        return graphics.getFont();
    }

    public void setFont(Font font) {
        graphics.setFont(font);
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(x, y, width, height);
    }

    public void drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
    }

    public void drawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    public void fillOval(int x, int y, int width, int height) {
        graphics.fillOval(x, y, width, height);
    }

    public void drawPolygon(int[] xPoints, int[] yPoints) {
        if (xPoints.length != yPoints.length) {
            return;
        }
        graphics.drawPolygon(xPoints, yPoints, xPoints.length);
    }

    public void fillPolygon(int[] xPoints, int[] yPoints) {
        if (xPoints.length != yPoints.length) {
            return;
        }
        graphics.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    public void drawString(String str, int x, int y) {
        graphics.drawString(str, x, y);
    }

    public void drawImage(Image img, int x, int y) {
        graphics.drawImage(img, x, y, null);
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesGui) _contextEl.getStandards()).getAliasGraphics();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof GraphicsStruct)) {
            return false;
        }
        return graphics == ((GraphicsStruct)_other).graphics;
    }
}
