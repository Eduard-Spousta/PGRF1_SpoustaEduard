
import model.Line;
import model.Point;
import model.Polygon;
import raster.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

/**
 * trida pro kresleni na platno: zobrazeni pixelu, ovladani mysi
 *
 * @author PGRF FIM UHK
 * @version 2020
**/
public class CanvasMousePolygon {

    private JFrame frame;
    private JPanel panel;
    private Raster raster;
    private Polygon polygon;
    private PolygonRasterizer lineRasterizer;
    private int x, y;
    private List<Line> lines = new ArrayList<>();

    //NEPODAŘILO SE MI DOŘEŠIT
    public CanvasMousePolygon(int width, int height) {
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new PolygonRasterizer(raster);

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {

                    raster.setPixel(e.getX(), e.getY(), 0xffff00);
                    x = e.getX();
                    y = e.getY();
                }
                if (e.getButton() == MouseEvent.BUTTON2)
                    //img.setRGB(e.getX(), e.getY(), 0xff00ff);
                    raster.setPixel(e.getX(),e.getY(),0xff00ff);
                if (e.getButton() == MouseEvent.BUTTON3)

                    raster.setPixel(e.getX(),e.getY(),0xffffff);
                panel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clear();
                polygon.addPoint(new Point(x,y));
                lineRasterizer.rasterize(polygon);
                panel.repaint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                lineRasterizer.rasterize(x,y,e.getX(),e.getY());
                panel.repaint();
            }
        });
    }


    public void clear() {
        raster.clear();
    }

    void redraw(){
        for (Line line: lines) {
            lineRasterizer.rasterize(polygon);
        }
        panel.repaint();
    }

    public void present(Graphics graphics) {
        BufferedImage img = ((RasterBufferedImage) raster).getImg();
        graphics.drawImage(img, 0, 0, null);
    }

    public void start() {
        clear();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CanvasMousePolygon(900, 600).start());
    }

}


//TEORIE
// ZMACKNU A JEDU -> VYKRESLI SE PRIMKA -> PUSTIM
// ZMACKNU A JEDU -> DELA SE TROJUHELNIK -> SPOJENY JIZ S TEMI 2MI BODY -> PUSTIM
// ZMACKNU A JEDU -> DELA SE N-UHELNIK SPOJENY(4 UHELNIK) - JE SPOJENY S PRVNIM BODEM A POSLEDNIM BODEM CO JSEM VYTVORIL POSLEDNE
// (JAK JSEM PUSTIL TU MYSKU)
// A TAK DÁLE... PRO N-ÚHLŮ


