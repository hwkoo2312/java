package com.java.ch12;
class Parent {
	void parentMethod() { }

	void parentmethod() {
		// TODO Auto-generated method stub
		
	}
}

class Child extends Parent {
	@Override
	void parentmethod() { } // ���� �޼����� �̸��� �߸�������. 
}
