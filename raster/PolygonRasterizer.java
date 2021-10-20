package raster;

import model.Point;
import model.Polygon;

import java.util.List;
import model.Point;

public class PolygonRasterizer {
    public PolygonRasterizer(Raster raster) {

    }

    public void rasterize(Polygon polygon){
        List<Point> points = polygon.getPoints();
        for(int i = 0; i < points.size(); i++){
            int x1 = points.get(i).getX();
            int y1 = points.get(i).getY();
            int x2 = points.get((i+1)%points.size()).getX();
            int y2 = points.get((i+1)%points.size()).getY();
            rasterize(x1,y1,x2,y2);
        }
    }

    public void rasterize(int x1, int y1, int x2, int y2) {
    }
}
