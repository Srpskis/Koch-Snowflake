/*********************************************************************************************
 * Program Name:		Koch Snowflake
 * Description:			This program creates a panel that displays a triangle which can be
 * 						divided into a number of smaller triangles in a particular pattern
 * 						known as the Koch Snowflake.
 * Author				Ana Taylor
 * Date Created:		06/06/18
 *********************************************************************************************/

package koch;

// importing packages
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// extends JPanel
public class KochPanel extends JPanel {

    // DATA MEMBERS

    private static final long serialVersionUID = 1L;
    //sets the value of WIDTH and HEIGHT according to the display dimension
    public static final int WIDTH = DisplayPanel.WIDTH - 25;
    public static final int HEIGHT = DisplayPanel.HEIGHT - 75;
    //in order for the entire snow flake to be shown on the panel, the size of the base triangle must be reduced
    //side stands for the lengths of the sides of the base case equilateral triangle
    public static final double SIDE = 0.6 * (WIDTH - 50);
    // stands for the height of the base case equilateral triangle
    public static final double THEIGHT = SIDE/(2*Math.tan(Math.toRadians(30)));
    //represents the number of clicks on the button
    private int level;
    // buttons declaration
    private JButton incrementButton;
    private JButton decrementButton;
    // label declaration
    private JLabel label;
    // sub panel declaration
    public JPanel subPanel;
    // points declaration
    private Point2D.Double p1, p2, p3;

    public KochPanel() {

        // setting level value
        level = 1;
        // create a sub panel
        subPanel = new JPanel();
        // create label
        label = new JLabel("n: " + level);
        // create buttons
        incrementButton = new JButton("+");
        decrementButton = new JButton("-");
        //adds an anonymous listener to incrementButton
        incrementButton.addActionListener(new ActionListener() {
            //implements the actionPerformed method in ActionListener
            public void actionPerformed(ActionEvent event) {
                //increments the level each time the button is clicked
                level++;
                //updates the label
                label.setText("n = " + level);
            }
        });
        //adds an anonymous listener to decrementButton
        decrementButton.addActionListener(new ActionListener() {
            //implements the actionPerformed method in ActionListener
            public void actionPerformed(ActionEvent event) {
                //only decrements the level if it is greater than 1
                if(level > 1) {
                    //decrements the level each time the button is clicked
                    level--;
                    //updates the label
                    label.setText("n = " + level);
                }
            }
        });
        //adds the buttons and label to the sub-panel since it is where they should be displayed
        subPanel.add(incrementButton);
        subPanel.add(decrementButton);
        subPanel.add(label);
        // call this method to display the base triangle
        setBase();
    }
    // this is a recursive method to add lines and draw triangles
    public void drawEdges(Graphics2D pen, int level, Point2D.Double p1, Point2D.Double p2) {
        // for initial level 1
        if(level == 1) {
            Line2D.Double line = new Line2D.Double(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            pen.draw(line);
         // for every other case
        }else {
            // call midpoint method and pass the two points to get the midpoint
            Point2D.Double midPoint12 = midPoint(p1, p2);
            // dx and dy values are used for shifting. its 1/3 of the side of equilateral triangle
            double dx = Math.abs(p1.getX() - p2.getX())/3.0;
            double dy = Math.abs(p2.getY() - p1.getY())/3.0;
            // create other points for storing
            Point2D.Double p4;
            Point2D.Double p5;
            Point2D.Double p6;
            // checking the first general case
            // where the x value of passed p1 is greater than the x value of passed p2.
            if(p1.getX() > p2.getX()) {
                // if y value of p1 is equal to y value of p2
                if(p1.getY() == p2.getY()) {
                    // assign the new values to points
                    p4 = new Point2D.Double(p1.getX() - dx, p1.getY());
                    p5 = new Point2D.Double(midPoint12.getX(), midPoint12.getY() - dx/(2*Math.tan(Math.toRadians(30))));
                    p6 = new Point2D.Double(p1.getX() - 2*dx, p1.getY());
                    //calls method recursively on each side
                    drawEdges(pen, level-1, p1, p4);
                    drawEdges(pen, level-1, p4, p5);
                    drawEdges(pen, level-1, p5, p6);
                    drawEdges(pen, level-1, p6, p2);
                 // if y value of p1 is less than y value of p2
                }else if(p1.getY() < p2.getY()) {
                    // assign the new values to points
                    p4 = new Point2D.Double(p1.getX() - dx, p1.getY() + dy);
                    p5 = new Point2D.Double(midPoint12.getX() - dy * Math.cos(Math.toRadians(30)), p1.getY() + dy);
                    p6 = new Point2D.Double(p1.getX() - 2*dx, p1.getY() + 2*dy);
                    //calls method recursively on each side
                    drawEdges(pen, level-1, p1, p4);
                    drawEdges(pen, level-1, p4, p5);
                    drawEdges(pen, level-1, p5, p6);
                    drawEdges(pen, level-1, p6, p2);
                 // this handles if y value of p1 is greater than y value of p2
                }else {
                    // assign the new values to points
                    p4 = new Point2D.Double(p1.getX() - dx, p1.getY() - dy);
                    p5 = new Point2D.Double(midPoint12.getX() + dy * Math.cos(Math.toRadians(30)), p1.getY() - 2*dy);
                    p6 = new Point2D.Double(p1.getX() - 2*dx, p1.getY() - 2*dy);
                    //calls method recursively on each side
                    drawEdges(pen, level-1, p1, p4);
                    drawEdges(pen, level-1, p4, p5);
                    drawEdges(pen, level-1, p5, p6);
                    drawEdges(pen, level-1, p6, p2);
                }
                // checking the second general case
                // where the x value of passed p1 is less than the x value of passed p2.
            }else {
                // if y value of p1 is equal to y value of p2
                if(p1.getY() == p2.getY()) {
                    // assign the new values to points
                    p4 = new Point2D.Double(p1.getX() + dx, p1.getY());
                    p5 = new Point2D.Double(midPoint12.getX(), midPoint12.getY() + dx/(2*Math.tan(Math.toRadians(30))));
                    p6 = new Point2D.Double(p1.getX() + 2*dx, p1.getY());
                    //calls method recursively on each side
                    drawEdges(pen, level-1, p1, p4);
                    drawEdges(pen, level-1, p4, p5);
                    drawEdges(pen, level-1, p5, p6);
                    drawEdges(pen, level-1, p6, p2);

                // if the y value of p1 is less than the y value of p2
                }else if (p1.getY() < p2.getY()) {
                    // assign the new values to points
                    p4 = new Point2D.Double(p1.getX() + dx, p1.getY() + dy);
                    p5 = new Point2D.Double(midPoint12.getX() - dy * Math.cos(Math.toRadians(30)), p1.getY() + 2*dy);
                    p6 = new Point2D.Double(p1.getX() + 2*dx, p1.getY() + 2*dy);
                    //calls method recursively on each side
                    drawEdges(pen, level-1, p1, p4);
                    drawEdges(pen, level-1, p4, p5);
                    drawEdges(pen, level-1, p5, p6);
                    drawEdges(pen, level-1, p6, p2);
                // this handles if the y value of point p1 is greater than the y value of p2
                }else {
                    // assign the new values to points
                    p4 = new Point2D.Double(p1.getX() + dx, p1.getY() - dy);
                    p5 = new Point2D.Double(midPoint12.getX() + dy * Math.cos(Math.toRadians(30)), p1.getY() - dy);
                    p6 = new Point2D.Double(p1.getX() + 2*dx, p1.getY() - 2*dy);
                    //calls method recursively on each side
                    drawEdges(pen, level-1, p1, p4);
                    drawEdges(pen, level-1, p4, p5);
                    drawEdges(pen, level-1, p5, p6);
                    drawEdges(pen, level-1, p6, p2);
                }
            }
        }
        // repaint after each iteration
        repaint();
    }

    //helper method midPoint that calculates the midpoint of two points
    public Point2D.Double midPoint(Point2D.Double p1, Point2D.Double p2) {
        return new Point2D.Double((p1.getX()+p2.getX())/2, (p1.getY()+p2.getY())/2);
    }

    //overrides the paintComponent method of JPanel
    public void paintComponent(Graphics g) {
        // graphics object casted to Graphics2D
        Graphics2D pen = (Graphics2D) g;
        // calling drawEdges method to handle the base triangle
        drawEdges(pen, level, p1, p2);
        drawEdges(pen, level, p2, p3);
        drawEdges(pen, level, p3, p1);

    }
    // setting the base triable
    public void setBase() {
        //the x coordinates of the three base points
        double[] x = {WIDTH/2 + 10, WIDTH/2 - SIDE/2 + 10, WIDTH/2 + SIDE/2 + 10};
        //the y coordinates of the three base points
        double[] y = {25, 25 + THEIGHT, 25 + THEIGHT};
        //initializes the points
        p1 = new Point2D.Double(x[0], y[0]);
        p2 = new Point2D.Double(x[1], y[1]);
        p3 = new Point2D.Double(x[2], y[2]);
    }

}
