package com.ch6;

class Tv{
	String color;
	boolean power;
	int chn;
	
	void power() {
		power = !power;
	}
	
	void chnUp() {
		chn += 1;
	}
	
	void chnDown() {
		chn -= 1;
	}
}


class TvTest {
	public static void main(String[] args) {
		Tv t = new Tv();
		
		t.chn = 10;
		t.chnDown();
		System.out.println("현재 채널 = " + t.chn);
	}
}
