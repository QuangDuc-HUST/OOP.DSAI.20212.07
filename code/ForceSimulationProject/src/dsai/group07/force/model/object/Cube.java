package dsai.group07.force.model.object;

import dsai.group07.force.model.vector.Force;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cube extends MainObject {

	private DoubleProperty size = new SimpleDoubleProperty(MAX_SIZE);
	public static final double MAX_SIZE = 1.0;
	public static final double MIN_SIZE = 0.1;

	public Cube() throws Exception {
		super();
	}

	public Cube(double mass) throws Exception {
		super(mass);
	}

	public Cube(double mass, double size) throws Exception {
		this(mass);
		setSize(size);
	}

	public DoubleProperty sizeProperty() {
		return size;
	}

	public double getSize() {
		return size.get();
	}

	public void setSize(double size) throws Exception {
		if (size < MIN_SIZE) {
			setSize(MIN_SIZE);
			throw new Exception("Cube's size must > " + MIN_SIZE + " and <= " + MAX_SIZE);
		} else if (size > MAX_SIZE) {
			setSize(MAX_SIZE);
			throw new Exception("Cube's size must > " + MIN_SIZE + " and <= " + MAX_SIZE);
		} else {
			this.size.setValue(size);
		}
	}

	

}
