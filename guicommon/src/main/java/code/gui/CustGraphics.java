package code.gui;

import java.awt.*;
import java.text.AttributedCharacterIterator;

public final class CustGraphics {
    private Graphics graphics;
    public CustGraphics(Graphics _g) {
        graphics = _g;
    }

    public void translate(int x, int y) {
        graphics.translate(x, y);
    }

    public Color getColor() {
        return graphics.getColor();
    }

    public void setColor(Color c) {
        graphics.setColor(c);
    }

    public void setPaintMode() {
        graphics.setPaintMode();
    }

    public void setXORMode(Color c1) {
        graphics.setXORMode(c1);
    }

    public Font getFont() {
        return graphics.getFont();
    }

    public void setFont(Font font) {
        graphics.setFont(font);
    }

    public FontMetrics getFontMetrics() {
        return graphics.getFontMetrics();
    }

    public FontMetrics getFontMetrics(Font f) {
        return graphics.getFontMetrics(f);
    }

    public Rectangle getClipBounds() {
        return graphics.getClipBounds();
    }

    public void clipRect(int x, int y, int width, int height) {
        graphics.clipRect(x, y, width, height);
    }

    public void setClip(int x, int y, int width, int height) {
        graphics.setClip(x, y, width, height);
    }

    public Shape getClip() {
        return graphics.getClip();
    }

    public void setClip(Shape clip) {
        graphics.setClip(clip);
    }

    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        graphics.copyArea(x, y, width, height, dx, dy);
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

    public void clearRect(int x, int y, int width, int height) {
        graphics.clearRect(x, y, width, height);
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void draw3DRect(int x, int y, int width, int height, boolean raised) {
        graphics.draw3DRect(x, y, width, height, raised);
    }

    public void fill3DRect(int x, int y, int width, int height, boolean raised) {
        graphics.fill3DRect(x, y, width, height, raised);
    }

    public void drawOval(int x, int y, int width, int height) {
        graphics.drawOval(x, y, width, height);
    }

    public void fillOval(int x, int y, int width, int height) {
        graphics.fillOval(x, y, width, height);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        graphics.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        graphics.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        graphics.drawPolyline(xPoints, yPoints, nPoints);
    }

    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        graphics.drawPolygon(xPoints, yPoints, nPoints);
    }

    public void drawPolygon(Polygon p) {
        graphics.drawPolygon(p);
    }

    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        graphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    public void fillPolygon(Polygon p) {
        graphics.fillPolygon(p);
    }

    public void drawString(String str, int x, int y) {
        graphics.drawString(str, x, y);
    }

    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        graphics.drawString(iterator, x, y);
    }

    public void drawChars(char[] data, int offset, int length, int x, int y) {
        graphics.drawChars(data, offset, length, x, y);
    }

    public void drawBytes(byte[] data, int offset, int length, int x, int y) {
        graphics.drawBytes(data, offset, length, x, y);
    }

    public boolean drawImage(Image img, int x, int y) {
        return graphics.drawImage(img, x, y, null);
    }

    public boolean drawImage(Image img, int x, int y, int width, int height) {
        return graphics.drawImage(img, x, y, width, height, null);
    }

    public boolean drawImage(Image img, int x, int y, Color bgcolor) {
        return graphics.drawImage(img, x, y, bgcolor, null);
    }

    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor) {
        return graphics.drawImage(img, x, y, width, height, bgcolor, null);
    }

    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
        return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }

    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor) {
        return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, null);
    }

    public boolean hitClip(int x, int y, int width, int height) {
        return graphics.hitClip(x, y, width, height);
    }

    public Rectangle getClipBounds(Rectangle r) {
        return graphics.getClipBounds(r);
    }
}
