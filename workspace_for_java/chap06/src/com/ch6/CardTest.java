package com.ch6;

class Card{
	String kind;
	int number;
	static int width = 100;
	static int height = 250;
}


class CardTest {
	public static void main(String[] args) {
		System.out.println("Card.width = " + Card.width);
		System.out.println("Card.height = " + Card.height);
		
		Card c1 = new Card();
		c1.kind = "HEART";
		c1.number = 7;
		
		Card c2 = new Card();
		c2.kind = "SPADE";
		c2.number = 6;
		
		System.out.println("C1은 " + c1.kind + "," + c1.number + "이다.");
		System.out.println("C2는 " + c1.kind + "," + c2.number + "이다.");
		
		Card.height = 200;
		
		System.out.println(Card.height +","+ Card.width);
	}
}
