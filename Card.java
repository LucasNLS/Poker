//Lucas Nicolas Lugones
//lnl2116
//Card.java
// 

public class Card implements Comparable<Card>{
	//Instance variables
	private int suit; 
	private int rank; 
	private char charSuit;
    
    //Constructor
	public Card(int s, int r){
		this.suit = s;
        this.rank = r;
	}
    
    //Constructor for tester
    public Card(char a, int r){
        this.charSuit = a;
        this.rank = r;
        
        //Converts the chars into int
        switch (charSuit){
            case 'a':
                this.suit = 1;
                break;
            case 'c':
                this.suit = 2;   
                break;
            case 'h':
                this.suit = 3; 
                break;
            case 'd':
                this.suit = 4; 
                break;
        }
        
    }    
	
    //Method that compares cards to sort them after
    public int compareTo(Card c){
        if (this.rank < c.rank){
            return -1;
        } else if (this.rank > c.rank){
            return 1;
        } else {
            return 0;
        }
      
    }
	
    //toString method to make the cards easier to read
	public String toString(){
        switch (suit){
            case 1:
                return "ace " + String.valueOf(rank);
            case 2:
                return "clubs " + String.valueOf(rank);             
            case 3:
                return "hearts " + String.valueOf(rank);             
            case 4:
                return "diamonds " + String.valueOf(rank);
        }                
       return "";        
    }
    
    //Accesor method
    public int getRank (){      
        return this.rank;      
    }
    
    //Accesor method
    public int getSuit (){       
        return this.suit;
    }
    
    //Changes value of "A" if paired with king
    public void changeAceValue (){       
        this.rank = 14;       
    }
    
    //Changes value of "A" back to 1
    public void goBackAce (){       
        this.rank = 1;       
    }
	

}
