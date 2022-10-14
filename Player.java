//Lucas Nicolas Lugones
//lnl2116
//Player.java
import java.util.ArrayList;
public class Player {
	
	//Instance Variables	
	private ArrayList<Card> hand; 
	private double bankroll;
    private double bet;
    private Deck deck;
    private int count;
    private int repeat;
    
    //Constructor method
	public Player(){		
	    hand = new ArrayList<Card>();
        deck = new Deck();
        deck.shuffle();
        for (int i = 0; i<5; i++){     
            hand.add(deck.deal());
        }
	}
    
    //Constructor method for tester
    public Player(ArrayList<Card> a){       
        hand = new ArrayList<Card>();
        for (int i = 0; i < 5; i++){
            hand.add(a.get(i));      
        }
        deck = new Deck ();
        deck.shuffle();      
    }
    
    //Method that add cards to hand with tester
    public Card testAddCard(){
        repeat = 0;
        while (repeat == 0){          
            Card newCard = this.deck.deal();
                for (int i = 0; i < 5; i ++){              
                    if (hand.get(i).getRank() == newCard.getRank() && hand.get(i).getSuit() == newCard.getSuit()){                   
                        repeat++;               
                    }            
                }        
                if (repeat == 0 ){           
                    return newCard;            
                } else {           
                    repeat = 0;
                }         
        }   
        return null;     
    }
    
    //Method that adds cards to hand
	public void addCard(Card c){
	    hand.add(count, c);
	}
    
    //Method that removes cards from hand
	public void removeCard(Card c){
	    hand.remove(c);
        }
    
    //Instantiates bets
    public void bets(double amt){
         this.bet = amt;
        }

    //Adds wins
    public void winnings(double odds){
            this.bankroll = this.bankroll + (odds * bet) - bet;     
        }
    
    //Accesor method for wins
    public double getWinnings(double odds){     
           return (odds * this.bet);
        }
    
    //Accesor method for bankroll
    public double getBankroll(){     
            return this.bankroll;
        }
    
    //Prints cards for user to see
    public void printCards(){      
        for (int i = 0; i < 5; i++){           
        System.out.println(hand.get(i).toString());
        System.out.println("--------------------------");           
        }       
    }
    
    //Prints one card
    public String printCard(int count){        
        return hand.get(count).toString();        
    }
    
    //Deals card from the top of the deck to add to hand
    public Card dealCard (){       
        Card newCard = this.deck.deal();
        return newCard;       
    }
    
    //Gets card
    public Card getCard(int count){       
        return hand.get(count);        
    }
    
    //Instantiates count to add card
    public void addCount(int count){       
        this.count = count;       
    }

}


