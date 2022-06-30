package dsai.group07.force.model.surface;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Surface {
	
	DoubleProperty staCoef = new SimpleDoubleProperty();
	DoubleProperty kiCoef = new SimpleDoubleProperty();
	public static final double MAX_STA_COEF = 1.0;
	public static final double STEP_COEF = 0.001;
	
	public Surface() {}
	
	public Surface(double staCoef) throws Exception {
		setStaCoef(staCoef);
		setKiCoef(staCoef / 2);
	}
	
	public Surface(double staCoef, double kiCoef) throws Exception {
		setStaCoef(staCoef);
		setKiCoef(kiCoef);
	}
	
	public DoubleProperty staCoefProperty() {
		return staCoef;
	}
	
	public double getStaCoef() {
		return staCoef.get();
	}
	
	public DoubleProperty kiCoefProperty() {
		return kiCoef;
	}
	
	public double getKiCoef() {
		return kiCoef.get();
	}
	
	public void setStaCoef(double staCoef) throws Exception {
		if (staCoef < 0) {
            this.staCoef.setValue(0);
			throw new Exception("Static friction coefficient must be >= 0 and <= " + MAX_STA_COEF);
		} else if (staCoef > MAX_STA_COEF) {
            this.staCoef.setValue(MAX_STA_COEF);
			throw new Exception("Static friction coefficient must be >= 0 and <= " + MAX_STA_COEF);
        } else if (staCoef == 0) {
			kiCoef.setValue(0);
			this.staCoef.setValue(0);
			System.out.println("Kinetic friction coefficient is setted to " + String.format("%.3f", getKiCoef()));
			System.out.println("Static friction coefficient is setted to " + String.format("%.3f", getStaCoef()));
		} else if (staCoef <= getKiCoef()) {
            this.staCoef.setValue(Math.min(getKiCoef() + STEP_COEF, MAX_STA_COEF));
			throw new Exception("Static friction coefficient must be > kinetic friction coefficient: " + String.format("%.3f", getKiCoef()));
		} else {
			this.staCoef.setValue(staCoef);
			System.out.println("Static friction coefficient is setted to " + String.format("%.3f", getStaCoef()));
		}
	}
	
	public void setKiCoef(double kiCoef) throws Exception {
		if (kiCoef < 0) {
            this.kiCoef.setValue(0);
			throw new Exception("Kinetic friction coefficient must be >= 0 and <= " + MAX_STA_COEF);
		} else if (kiCoef > MAX_STA_COEF) {
            this.kiCoef.setValue(MAX_STA_COEF);
            throw new Exception("Static friction coefficient must be >= 0 and <= " + MAX_STA_COEF);
		} else if (getStaCoef() <= kiCoef) {
            this.kiCoef.setValue(getStaCoef() - STEP_COEF);
			throw new Exception("Kinetic friction coefficient must be < static friction coefficient: " + String.format("%.3f", getStaCoef()));
		} else {
			this.kiCoef.setValue(kiCoef);
			System.out.println("Kinetic friction coefficient is setted to " + String.format("%.3f", getKiCoef()));
		}
	}
	
}
