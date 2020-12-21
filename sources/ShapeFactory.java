import java.awt.geom.Point2D;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.Paint;
import java.awt.BasicStroke;
import java.awt.Shape;

// 
// Decompiled by Procyon v0.5.36
// 

public class ShapeFactory
{
    public Shape shape;
    public BasicStroke stroke;
    public Paint paint;
    public int width;
    public int height;
    
    
    enum ShapeType {
        HEXAGON,
        STAR,
        SQUARE,
        TRIANGLE,
        PIE
    }
    
    enum ShapeKind {
        STROKE3,
        STROKE1,
        NONE, 
        STROKE7,
        GRADIENT,
        RED
    }
    
    /**
      * ctor accepts shape type that defines kind of shape and it's paint parameters. e.g. shapeType = 21, kind is 2, paint param is 1.
      * @param shapeType type of shape
      * @param shapeParam kind of shape
      * @throws Error if wrong arguments
    */
    public ShapeFactory(final ShapeType shapeType, final ShapeKind shapeParam) {
        this.width = 25;
        this.height = 25;
        this.stroke = new BasicStroke(3.0f);
        switch (shapeType) {
            case HEXAGON: {
                this.shape = createStar(3, new Point(0, 0), this.width / 2.0, this.width / 2.0);
                break;
            }
            case STAR: {
                this.shape = createStar(5, new Point(0, 0), this.width / 2.0, this.width / 4.0);
                break;
            }
            case SQUARE: {
                this.shape = new Rectangle2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height);
                break;
            }
            case TRIANGLE: {
                final GeneralPath path = new GeneralPath();
                final double tmp_height = Math.sqrt(2.0) / 2.0 * this.height;
                path.moveTo(-this.width / 2, -tmp_height);
                path.lineTo(0.0, -tmp_height);
                path.lineTo(this.width / 2, tmp_height);
                path.closePath();
                this.shape = path;
                break;
            }
            case PIE: {
                this.shape = new Arc2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height, 30.0, 300.0, 2);
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
        switch (shapeParam) {
            case STROKE3: {
                this.stroke = new BasicStroke(3.0f);
                break;
            }
            case STROKE1: 
                this.stroke = new BasicStroke(1.0f);
                break;
            case NONE: {
                break;
            }
            case STROKE7: {
                this.stroke = new BasicStroke(7.0f);
                break;
            }
            case  GRADIENT: {
                this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
                break;
            }
            case RED: {
                this.paint = Color.red;
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
    }
    
    /**
      * method for creating creating star
      * @param arms Arms count
      * @param center Center of star
      * @param rOuter Length of edge
      * @param rInner Distance bettwen center and edge point
      * @return Created shape 
    */  
    private static Shape createStar(final int arms, final Point center, final double rOuter, final double rInner) {
        final double angle = 3.141592653589793 / arms;
        final GeneralPath path = new GeneralPath();
        for (int i = 0; i < 2 * arms; ++i) {
            final double r = ((i & 0x1) == 0x0) ? rOuter : rInner;
            final Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            }
            else {
                path.lineTo(p.getX(), p.getY());
            }
        }
        path.closePath();
        return path;
    }
}
