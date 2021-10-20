package raster;

import model.Line;
import model.Polygon;

public class LineRasterizerDDA extends LineRasterizer{
    public LineRasterizerDDA(Raster raster) {
        super(raster);
    }

    //DDA algoritmus
    /*
        - MÁME BODY x1, x2, y1, y2
        - spočítáme delta x a delta y
        - sopocitavame dx = dx/size -> zjistujeme, zda je 1 ci jine cislo
        - math signum -> zjistujem 1 0 -1
        - dle parametru vytvorime cykly pro jednotlive qudranty napr x>1 a pod...
        --> vykresleni usecky

        VYHODA: algoritmus mi osobne prijde jedodussi na pochopeni
        NEVYHODY:
        - musi se neustale zaokrohlovat nebo pretypovavat
        - je pomerne dlouhy
        - neni efektivni

    */
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int x, y, size = 0, xK = 0, yK = 0;
        float dy, dx;
        dx = x2 - x1;
        dy = y2 - y1;

        if (Math.abs(dx) > Math.abs(dy)) {
            dx = size;
        } else {
            dy = size;
        }
        if (dx == 1) {

        }
        dx = dx / (float) size;
        dy = dy / (float) size;

        //-1 0 1
        x = (int) (x1 + 0.5 * Math.signum(dx));
        y = (int) (y1 + 0.5 * Math.signum(dy));

        for (int i = 1; i <= size; i++){
            raster.setPixel(x, y, 0xff0000);
            x+=dx;
            y+=dy;
        }
    }

    @Override
    public void rasterize(Line line) {

    }

}
