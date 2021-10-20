package raster;

import model.Line;
import model.Polygon;

public class LineRasterizerFill extends LineRasterizer {
    public LineRasterizerFill(Raster raster) {
        super(raster);
    }

    //TRIVIÁLNI algoritmus pro zadání úsečky pomocí bodů
    /*
        VYCHÁZÍME ZE VZOREČKU:
        y = f(x) = kx + q
        KDE: k je smernice přímky, q vyjadřuje posunutí po ose y
        k=(y2-Y1)/(x2-x1)
     */

    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        float k = (float) (y2 - y1) / (float) (x2 - x1);

        float q = y1 - k * x1;
        for (int x = x1; x <= x2; x++) {
            int y = (int) (k * x + q);
            raster.setPixel(x, y, 0xffff00);
        }
    }

    @Override
    public void rasterize(Line line) {

    }

}
