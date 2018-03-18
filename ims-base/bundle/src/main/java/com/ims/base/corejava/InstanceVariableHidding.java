package com.ims.base.corejava;

public class InstanceVariableHidding {
	int a;
	int b;
	String name = "Instance Var.";

	void someMethod() {
		String name = "Local Var.";
		System.out.println(name); // Local Var.
		System.out.println(this.name); // Instance Var.
	}

	InstanceVariableHidding(int a) {

		a = a; // wrong:hiddes instance var
		// this.a = a; //correct
	}

	public static void main(String[] args) {
		InstanceVariableHidding obj = new InstanceVariableHidding(10);
		System.out.println(obj.a);
		obj.someMethod();
	}
}
