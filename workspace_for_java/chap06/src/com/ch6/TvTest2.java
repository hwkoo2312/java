package com.ch6;



class TvTest2 {
	public static void main(String[] args) {
		Tv t = new Tv();
		
		t.chn = 10;
		t.chnDown();
		System.out.println("현재 채널 = " + t.chn);
	}
}
