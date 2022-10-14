//Lucas Nicolas Lugones
//lnl2116
//Game.java

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	
    //Instance variables
	private Player p;
	private Deck cards;
    private Scanner in = new Scanner(System.in);
    private int dec;
    private int bet;
    private ArrayList<Card> testHand = new ArrayList<Card>();
    private int testing;
    private char rank;
    private int intRank;
    private Card testCard;
    private String value;
    private Card[] arrayHand = new Card[5];
    private String comb = "";
    private double points = 0.0;
    private int counting;
	private int sum;
    private Card[] copyA = new Card[5];
    private int repeat;
    private int fourCheck;
    private int threeCheck;
	private double bankroll = 0.0;
    private int gameDecision = 1;
    
    //Constructor for tester
	public Game(String[] testHand){
        for (int i = 0; i < 5; i ++){
            value = testHand[i].substring (1);      
            this.intRank = Integer.parseInt(value);     
            this.testCard = new Card (testHand[i].charAt(0), intRank);                       
            this.testHand.add(testCard);           
        }        
        this.testing = 1;		
	}
	
    //Constructor
	public Game(){
        System.out.println("What is your bankroll?: ");
        bankroll = in.nextDouble();
        this.testing = 0;	
	}
	
    //Method to play
	public void play(){
		
        //Chooses between tester and regular game
        if (testing == 0){
            while (gameDecision == 1) {
                System.out.println("Bankroll: " + bankroll);
                System.out.println("Welcome to video poker! How much would you like to bet between 1 and 5 tokens?");
                this.bet = in.nextInt();    
                p = new Player();
                p.bets(this.bet);      
                p.printCards();
        
                for (int i = 0; i<5; i++){         
                dec = getDecision(p, i);         
                    if (dec == 0){                 
                       discard(p, i);             
                    }       
                }      
                p.printCards();    
                ArrayList<Card> test = new ArrayList<Card>();
                for (int i = 0; i < 5; i++){
              
                      test.add(p.getCard(i));
              
                }
                System.out.println(checkHand(test)); 
                p.winnings(points);        
                System.out.println("You won!: " + p.getWinnings(points));
                bankroll = p.getBankroll() + bankroll;          
                System.out.println("Do you want to keep playing?. 1 for yes, 0 for no");
                gameDecision = in.nextInt();       
            }     
        } else if (testing == 1){
              System.out.println("Welcome to video poker! How much would you like to bet between 1 and 5 tokens?");
              this.bet = in.nextInt(); 
              p = new Player(testHand);
              p.bets(this.bet);    
              p.printCards();     
          for (int i = 0; i<5; i++){       
               dec = getDecision(p, i);           
               if (dec == 0){               
                   discard(p, i);             
               }            
          }       
          p.printCards();           
          ArrayList<Card> test = new ArrayList<Card>();
          for (int i = 0; i < 5; i++){           
              test.add(p.getCard(i));             
          }        
          System.out.println(checkHand(test)); 
          p.winnings(points);         
          System.out.println("You won!: " + p.getWinnings(points));             
         }      
     }
	
    //Method that checks hands to see if they can be paired
	public String checkHand(ArrayList<Card> hand){        
        points = 0.0;
        counting = 0;        
        for (int i = 0; i < hand.size(); i++){            
            this.arrayHand[i] = hand.get(i);           
        }
        Arrays.sort(arrayHand);
        
        //Goes through each pair to see what the hand scored
        if (isRoyalFlush(arrayHand) == true){           
            return "Royal Flush";            
        } else if (isStraightFlush(arrayHand) == true){            
            return "Straight Flush";
        } else if (isFourOfKind(arrayHand) == true) {           
            return "Four of a Kind";           
        } else if (isFullHouse(arrayHand) == true) {          
            return "Full House";      
        } else if (isFlush(arrayHand) == true) {           
            return "Flush";           
        } else if (isStraight(arrayHand) == true) {          
            return "Straight";           
        } else if (isThreeOfKind(arrayHand) == true) {          
            return "Three of a Kind";       
        } else if (isTwoPairs(arrayHand) == true) {               
            return "Two Pairs";
        } else if (isOnePair(arrayHand) == true) {          
            return "One Pair";      
        } else{          
            return "No Pair";
        }
	}
    
    //Checks if hand is royalflush
    public boolean isRoyalFlush (Card[] a){
        for (int i = 0; i < 5; i++){           
            copyA[i] = a[i];          
            if (copyA[i].getRank() == 1){               
                copyA[i].changeAceValue();                
            }         
        }
        Arrays.sort(copyA);
        this.counting = 0;
        this.sum = 0;      
        for (int i = 0; i <5; i++){        
            sum = sum + copyA[i].getRank();           
        }
        for (int i = 1; i < 5; i++){        
            if (copyA[i].getRank() - copyA[i-1].getRank() == 1 && copyA[i].getSuit() == copyA[i-1].getSuit()) {
                 counting++;                   
            }           
        }         
          for (int i = 0; i < 5; i++){           
              if (copyA[i].getRank() == 14){               
                copyA[i].goBackAce();                
          }
                      
        }
        Arrays.sort(copyA);
      
        if (counting == 4){
            points = 250.0;
            return true;
        } else {
            return false;
        }
             
    }
    
    //Method checks for straight flush
    public boolean isStraightFlush (Card[] a) {       
        Card[] copyA = new Card[5];     
        for (int i = 0; i < 5; i++){           
            copyA[i] = a[i];           
        }
        
        this.counting = 0;
        for (int i = 1; i < 5; i++){          
            if (((copyA[i].getRank() - copyA[i-1].getRank())) == 1 && copyA[i].getSuit() == copyA[i-1].getSuit()){
                counting++;                             
            }           
        }
        
        if (counting == 4){
            points = 50.0;
            return true;
        } else {
            
            for (int i = 1; i<5; i++){             
                if (copyA[i].getRank() == 1) {                 
                    copyA[i].changeAceValue();                 
                }              
            }
            Arrays.sort(copyA);        
            counting = 0;          
            for (int i = 1; i < 5; i++){          
                if (((copyA[i].getRank() - copyA[i-1].getRank())) == 1 && copyA[i].getSuit() == copyA[i-1].getSuit()) {             
                    counting++;               
                }            
            }            
            for (int i = 0; i < 5; i++){            
                if (copyA[i].getRank() == 14){               
                    copyA[i].goBackAce();                 
                } 
            }
            Arrays.sort(copyA);
            
            if (counting == 4){              
                points = 50.0;
                return true;              
            } else {        
                return false;
            }              
        }      
    }
    
    //Method checks for Four of a kind    
    public boolean isFourOfKind(Card[] a) {   
        this.fourCheck = 0;       
        for (int i = 0; i < 5; i++){            
            copyA[i] = a[i];            
        }        
        this.counting = 0;
        for (int i = 1; i < 5; i++){           
             if (copyA[i].getRank() == copyA[i-1].getRank()) {               
                this.counting++;
                this.fourCheck++;               
             } else {                
                 this.fourCheck = 0;
             }           
        }
        if (counting >= 3 && fourCheck == 3){
            points = 25.0;
            return true;
        } else {                  
         return false;
        }          
    }
    
    //Method checks for full house
    public boolean isFullHouse(Card[] a){          
        for (int i = 0; i < 5; i++){        
            copyA[i] = a[i];         
        }      
        this.counting = 0;
        for (int i = 1; i < 5; i++){           
            if (copyA[i].getRank() == copyA[i-1].getRank()) {               
                counting++;
            }          
        }       
        if (counting == 3){
            points = 6;
            return true;
        } else {
            return false;
            }            
    }
    
    //Method checks for flush
   public boolean isFlush(Card[] a){                
        for (int i = 0; i < 5; i++){            
            copyA[i] = a[i];            
        }        
        this.counting = 0;
        for (int i = 1; i < 5; i++){            
            if ( copyA[i].getSuit() == copyA[i-1].getSuit()) {                
                counting++;                
            }           
        }     
        if (counting == 4){
            points = 5.0;
            return true;
        } else {           
            return false;
        }            
    }
    //Method checks if its straight
    public boolean isStraight(Card[] a){            
        for (int i = 0; i < 5; i++){           
            copyA[i] = a[i];           
        }       
        this.counting = 0;
        for (int i = 1; i < 5; i++){           
            if ((copyA[i].getRank() - copyA[i-1].getRank()) == 1 ) {               
                counting++;               
            }           
        }       
        if (counting == 4){
            points = 4.0;
            return true;
        } else {          
            for (int i = 1; i<5; i++){             
                if (copyA[i].getRank() == 1) {                  
                    copyA[i].changeAceValue();                   
                }               
            }
            Arrays.sort(copyA);           
            counting = 0;           
            for (int i = 1; i < 5; i++){          
                if ((copyA[i].getRank() - copyA[i-1].getRank()) == 1) {              
                    counting++;               
                }           
            }            
            for (int i = 0; i < 5; i++){                      
            if (copyA[i].getRank() == 14){               
                copyA[i].goBackAce();                 
            }                       
            }
           Arrays.sort(copyA);           
            if (counting == 4){               
                points = 4.0;
                return true;               
            } else {               
                return false;
            }                 
        }        
    }
    
    //Method checks for three of a kind
    public boolean isThreeOfKind(Card[] a){        
        this.threeCheck = 0;        
        for (int i = 0; i < 5; i++){            
            copyA[i] = a[i];            
        }        
        this.counting = 0;
        for (int i = 1; i < 5; i++){           
            if (copyA[i].getRank() == copyA[i-1].getRank()) {                
                counting++;
                threeCheck++;               
            } else {               
                threeCheck = 0;                
            }           
            if (counting == 2 && threeCheck == 2){               
                points = 3.0;
                return true;               
            }          
        }        
        return false;       
    }
    
    //Method checks for pairs
    public boolean isTwoPairs(Card[] a){              
        for (int i = 0; i < 5; i++){
             copyA[i] = a[i];          
        }       
        this.counting = 0;
        for (int i = 1; i < 5; i++){           
            if (copyA[i].getRank() == copyA[i-1].getRank()) {               
                counting++;               
            }           
        }      
        if (counting == 2){
            points = 2.0;
            return true;
        } else {         
             return false;
        }            
    }
    
    //Method checks for pair
    public boolean isOnePair(Card[] a){             
        for (int i = 0; i < 5; i++){            
            copyA[i] = a[i];            
        }        
        this.counting = 0;
        for (int i = 1; i < 5; i++){            
            if (copyA[i].getRank() == copyA[i-1].getRank()) {               
                counting++;               
            }          
        }       
        if (counting == 1){
            points = 1.0;
            return true;
        } else {               
            return false;
        }                    
    }
    
    //Method gets decision to see if cards are discarded
    public int getDecision (Player play, int count){        
        System.out.println("Do you want to keep this card?: " + play.printCard(count));
        System.out.println("Insert 1 for yes, or 0 for no");
        int decision = in.nextInt();       
        return decision;        
    }	
    
    //Method to discard from test hand
    public void testDiscard (Player play, int count){      
        play.removeCard(play.getCard(count));
        play.addCount(count);
        play.addCard(play.testAddCard());               
    }
    
    //Method to discard from regular hand
    public void discard (Player play, int count){        
        play.removeCard(play.getCard(count));
        play.addCount(count);
        play.addCard(play.dealCard());        
    }

}
