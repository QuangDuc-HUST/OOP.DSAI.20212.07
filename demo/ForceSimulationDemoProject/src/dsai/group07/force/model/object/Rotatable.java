package dsai.group07.force.model.object;

import dsai.group07.force.model.vector.Force;
import javafx.beans.property.DoubleProperty;
//import javafx.geometry.HorizontalDirection;

public interface Rotatable {
	
	DoubleProperty angAccProperty();
	double getAngAcc();
	void setAngAcc(double angAcc);
	void updateAngAcc(Force force) throws Exception;
	
	DoubleProperty angVelProperty();
	double getAngVel();
	void setAngVel(double angVel);
	void updateAngVel(double angAcc, double t);
	
	DoubleProperty angleProperty();
	double getAngle();
	void setAngle(double angle);
	void updateAngle(double angVel, double t);
	
	DoubleProperty radiusProperty();
	double getRadius();
	void setRadius(double radius) throws Exception;
	
	void applyForceInTimeRotate(Force force, double t) throws Exception;
	
	//void upAngAcc(HorizontalDirection fric);
	//void upAngVel(HorizontalDirection fric);
}
