package sierpinski_Ian;

/*********************************************************************************************
 * Program Name:		Sierpinski01
 * Description:			This program creates a panel that displays a triangle which can be
 * 						divided into a number of smaller triangles in a particular pattern
 * 						known as Sierpinski's triangle.
 * Author				Ian Ritter
 * Date Created:		05/08/18
 *********************************************************************************************/

// importing packages
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sierpinski {

    public static class STPanel extends JPanel implements MouseListener {

        public STPanel(){

            addMouseListener(this);
        }


        private static final long serialVersionUID = 1L;

        //************************Settings************************
        //Adjust this number to change the order (of magnitude).
        private int order = 0;
        //(1) polygons and (2) outlines.
        private static int style = 1;
        //Change the color of the output.
        private static Color color = Color.BLACK;
        //********************************************************



        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            //Set the three default triangles vertices to fit the window.
            Point p1 = new Point(getWidth()/2, 10);
            Point p2 = new Point(10, getHeight() - 10);
            Point p3 = new Point(getWidth() - 10, getHeight() - 10);
            //Draw the triangle(s) from the base triangle.
            displayTriangles(g, order, p1, p2, p3);

        }

        private static void displayTriangles(Graphics g, int order, Point p1, Point p2, Point p3) {

            //If the order is set to zero, then only the base triangle will be drawn.
            if (order == 0) {

                if (style == 1) {
                    //Create a new polygon object, add the triangles points and fill with color.
                    Polygon p = new Polygon();
                    // set the color of
                    g.setColor(color);
                    p.addPoint(p1.x, p1.y);
                    p.addPoint(p2.x, p2.y);
                    p.addPoint(p3.x, p3.y);
                    g.fillPolygon(p);
                }else if (style == 2) {
                    //Use this code in place of the polygon code if you want outlines only.
                    g.setColor(color);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    g.drawLine(p1.x, p1.y, p3.x, p3.y);
                    g.drawLine(p2.x, p2.y, p3.x, p3.y);

                }
                //If the order is set to anything higher than zero, then the recursive method will
                //create the appropriate number of inner triangles.
            }else {

                //Calculate the midpoints of each of the three sides of the current triangle.
                Point p12 = midpoint(p1, p2);
                Point p23 = midpoint(p2, p3);
                Point p31 = midpoint(p3, p1);

                //This is the recursive part. The order is reduced by one and this method is called
                //again. If the order is still not zero, then this method is called again, and so on.
                //Once the end of the chain is reached, the triangles are drawn in reverse order until
                //order is finally equal to zero again and the recursion stops.
                displayTriangles(g, order -1, p1, p12, p31);
                displayTriangles(g, order - 1, p12, p2, p23);
                displayTriangles(g, order - 1, p31, p23, p3);
            }
        }

        //This method calculates the midpoint of the side of a triangle.
        private static Point midpoint(Point p1, Point p2) {
            return new Point(((p1.x + p2.x)/2), (p1.y + p2.y)/2);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            order++;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }
        public void mousePressed(MouseEvent e){

        }
        public void mouseReleased(MouseEvent e){

        }
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        STPanel panel = new STPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(800, 400);
        frame.setSize(400, 400);
        frame.setVisible(true);

    }
}