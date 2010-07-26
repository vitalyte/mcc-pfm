/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic;

/**
 *
 * @author Alximik
 */
public class Point {
    private double x;
    private double y;

    public Point() {
    }
    public Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX()
    {
       return x;
    }
    public Double getXDouble()
    {
       return x;
    }
    public double getY()
    {
       return y;
    }

    public void setX(double x)
    {
       this.x = x;
    }
    public void setY(double y)
    {
       this.y = y;
    }
    public static double Distance (Point p1, Point p2){
        double distance =0.0;
        distance = Math.sqrt( Math.pow(p1.getX()-p2.getX(),2)+
                Math.pow(p1.getY()-p2.getY(),2));
        return distance;
    }
    public Point mostLeftP(Point obj2){
        Point left;
        double lefttX = Math.min(this.getX(),obj2.getX());
        if (this.getX() <= lefttX ) {
            left = this;
        }else{
            left = obj2;
        }
        return left;
    }

}
