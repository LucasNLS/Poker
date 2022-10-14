// Lucas Nicolas Lugones
// lnl2116
// Deck.java

import java.util.Random;
public class Deck {
	
    //Instance Variables
	private Card[] cards;
	private int top; 
    private int randomFrom;
    private int randomTo;
    private Card temp;
    private Random r = new Random();
    private int count = 0;
    private int ind = -1;
	
    //Constructor to make 52 cards (a deck)
	public Deck(){    
      cards = new Card[52];
      while (count < 4){  
		for (int i = 0 + count*13; i<13 + 13*count; i++){    
           cards[i] = new Card (count + 1, i + 1 - count*13);          
        }
      count++;         
      }      
	}
    
	//Shuffle method that mixes cards
	public void shuffle(){     
        for (int i = 0; i<100; i++){       
            randomFrom = r.nextInt(52);
            randomTo = r.nextInt(52);
            temp = cards[randomTo];
            cards[randomTo] = cards [randomFrom];
            cards[randomFrom] = temp;           
        }	
	}
	
    //Method that deals cards from top deck
	public Card deal(){	
        ind++;
        return cards[ind]; 
	}

}
