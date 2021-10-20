package raster;

import model.Line;
import model.Point;
import model.Polygon;

import java.util.List;

public abstract class LineRasterizer {
    Raster raster;
    public LineRasterizer(Raster raster){
        this.raster = raster;
    }

    public void rasterize(int x1, int y1, int x2, int y2){
        rasterize(x1, y1, x2, y2, 0xffffff);
    }

    public void rasterize(int x1, int y1, int x2, int y2, int color){

    }

    public abstract void rasterize(Line line);
}
