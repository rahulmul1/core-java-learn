package com.ims.base.javalangex;

/**
 * THis example is created only to show that we have to override the equals and
 * hashcode method.
 */
public class JavaCloneTest {

	public static void main(String args[]) {

		Rectangle rec = new Rectangle(30, 60);

		Rectangle copy = null;
		try {
			copy = rec.clone();

		} catch (CloneNotSupportedException ex) {
		}

		System.out.println("Original object"+rec);
		System.out.println("cloned object"+copy);
		
		// testing properties of object returned by clone method in Java
		System.out.println("copy != rec : " + (copy != rec));
		System.out.println("copy.getClass() == rec.getClass() : " + (copy.getClass() == rec.getClass()));
		System.out.println("copy.equals(rec) : " + copy.equals(rec));
		
		// Updating fields in original object
		rec.setHeight(100);
		rec.setWidth(45);
		System.out.println("Original object"+rec);
		System.out.println("cloned object"+copy);
		

	}

}

class Rectangle implements Cloneable {
	private int width;
	private int height;

	public Rectangle(int w, int h) {
		width = w;
		height = h;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int area() {
		return width * height;
	}

	@Override
	public String toString() {
		return String.format("Rectangle [width: %d, height: %d, area: %d]",
				width, height, area());
	}

	@Override
	protected Rectangle clone() throws CloneNotSupportedException {
		return (Rectangle) super.clone();
	}

	/**
	 * We need to override these two methods for clonning.
	 * 
	 * Comment the below 2 methods and see the result of 
	 * copy.equals(rec) : false.
	 * This  is coz the default equal methods in object class behaves like the '=='
	 * and returns false if both the references dont refer to the same object.
	 * 
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Rectangle other = (Rectangle) obj;
		if (this.width != other.width) {
			return false;
		}
		if (this.height != other.height) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + this.width;
		hash = 47 * hash + this.height;
		return hash;
	}

}
