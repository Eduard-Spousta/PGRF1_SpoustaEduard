package raster;

import model.Line;
import model.Polygon;

import java.awt.*;
import java.awt.image.BufferedImage;

//RASTERIZACE PŘES BufferImage
public class LineRasterizerBI extends LineRasterizer{
    public LineRasterizerBI(Raster raster) {
        super(raster);
    }

    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        BufferedImage img = ((RasterBufferedImage)raster).getImg();
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(color));
        gr.drawLine(x1,y1,x2,y2);
    }

    @Override
    public void rasterize(Line line) {

    }
}
