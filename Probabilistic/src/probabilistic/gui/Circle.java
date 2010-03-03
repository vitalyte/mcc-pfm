/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic.gui;

/**
 *
 * @author Alximik
 */
public class Circle {
    Point center;
    double radius=0.0;

    public Circle(Point center, double radius) {
        this.center = new Point(center);
        this.radius = radius;
    }
    public Point getCenter()
    {
       return center;
    }
    public void setCenter(Point center)
    {
       this.center = new Point(center);
    }
    public Circle() {

    }
    public double getRadius()
    {
        return radius;
    }
    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public boolean IsPointInsideCircle(Point p)
    {
        boolean isInsideCircle = false;
        // перевірка, чи лежить точка всередині круга
        if (Point.Distance(this.center, p)<= this.radius)
        // якщо відстань від центра кола до заданої точки
        // не більша радіуса
            isInsideCircle = true;
        return isInsideCircle;
    }
}
