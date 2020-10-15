package code.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class CustGraphics {
    private Graphics graphics;
    public CustGraphics(Graphics _g) {
        graphics = _g;
    }

    public void translate(int _x, int _y) {
        graphics.translate(_x, _y);
    }

    public Color getColor() {
        return graphics.getColor();
    }

    public void setColor(Color _c) {
        graphics.setColor(_c);
    }

    public void setPaintMode() {
        graphics.setPaintMode();
    }

    public void setXORMode(Color _c1) {
        graphics.setXORMode(_c1);
    }

    public Font getFont() {
        return graphics.getFont();
    }

    public void setFont(Font _font) {
        graphics.setFont(_font);
    }

    public void clipRect(int _x, int _y, int _width, int _height) {
        graphics.clipRect(_x, _y, _width, _height);
    }

    public void setClip(int _x, int _y, int _width, int _height) {
        graphics.setClip(_x, _y, _width, _height);
    }

    public void copyArea(int _x, int _y, int _width, int _height, int _dx, int _dy) {
        graphics.copyArea(_x, _y, _width, _height, _dx, _dy);
    }

    public void drawLine(int _x1, int _y1, int _x2, int _y2) {
        graphics.drawLine(_x1, _y1, _x2, _y2);
    }

    public void fillRect(int _x, int _y, int _width, int _height) {
        graphics.fillRect(_x, _y, _width, _height);
    }

    public void drawRect(int _x, int _y, int _width, int _height) {
        graphics.drawRect(_x, _y, _width, _height);
    }

    public void clearRect(int _x, int _y, int _width, int _height) {
        graphics.clearRect(_x, _y, _width, _height);
    }

    public void drawRoundRect(int _x, int _y, int _width, int _height, int _arcWidth, int _arcHeight) {
        graphics.drawRoundRect(_x, _y, _width, _height, _arcWidth, _arcHeight);
    }

    public void fillRoundRect(int _x, int _y, int _width, int _height, int _arcWidth, int _arcHeight) {
        graphics.fillRoundRect(_x, _y, _width, _height, _arcWidth, _arcHeight);
    }

    public void draw3DRect(int _x, int _y, int _width, int _height, boolean _raised) {
        graphics.draw3DRect(_x, _y, _width, _height, _raised);
    }

    public void fill3DRect(int _x, int _y, int _width, int _height, boolean _raised) {
        graphics.fill3DRect(_x, _y, _width, _height, _raised);
    }

    public void drawOval(int _x, int _y, int _width, int _height) {
        graphics.drawOval(_x, _y, _width, _height);
    }

    public void fillOval(int _x, int _y, int _width, int _height) {
        graphics.fillOval(_x, _y, _width, _height);
    }

    public void drawArc(int _x, int _y, int _width, int _height, int _startAngle, int _arcAngle) {
        graphics.drawArc(_x, _y, _width, _height, _startAngle, _arcAngle);
    }

    public void fillArc(int _x, int _y, int _width, int _height, int _startAngle, int _arcAngle) {
        graphics.fillArc(_x, _y, _width, _height, _startAngle, _arcAngle);
    }

    public void drawPolyline(int[] _xPoints, int[] _yPoints, int _nPoints) {
        graphics.drawPolyline(_xPoints, _yPoints, _nPoints);
    }

    public void drawPolygon(int[] _xPoints, int[] _yPoints, int _nPoints) {
        graphics.drawPolygon(_xPoints, _yPoints, _nPoints);
    }

    public void fillPolygon(int[] _xPoints, int[] _yPoints, int _nPoints) {
        graphics.fillPolygon(_xPoints, _yPoints, _nPoints);
    }

    public void drawString(String _str, int _x, int _y) {
        graphics.drawString(_str, _x, _y);
    }

    public void drawChars(char[] _data, int _offset, int _length, int _x, int _y) {
        graphics.drawChars(_data, _offset, _length, _x, _y);
    }

    public void drawBytes(byte[] _data, int _offset, int _length, int _x, int _y) {
        graphics.drawBytes(_data, _offset, _length, _x, _y);
    }

    public boolean drawImage(BufferedImage _img, int _x, int _y) {
        return graphics.drawImage(_img, _x, _y, null);
    }

    public boolean hitClip(int _x, int _y, int _width, int _height) {
        return graphics.hitClip(_x, _y, _width, _height);
    }

    public void dispose() {
        graphics.dispose();
    }
}
