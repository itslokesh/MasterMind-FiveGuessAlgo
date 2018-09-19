package mastermindhard;

import java.util.ArrayList;
import java.util.Collections;

public class MastermindHard {
    public static String globalColours = "bcdefg";
    
    public static int getIndex(Character c){
        for(int i=0;i<globalColours.length();i++)
            if(globalColours.charAt(i)==c)
                return i;
        return 0;
    }
    
    public static Ball[] shuffle(Ball balls[],int ballIndex[]){
        ArrayList<Character> colors = new ArrayList();
        for(int i=0;i<balls.length;i++)
            colors.add(balls[i].getColor());
        Collections.shuffle(colors);
        for(int i=0;i<balls.length;i++){
            balls[i].setColor(colors.get(i));
        }
        return balls;
    }
    
    public static String printBalls(Ball[] balls){
        String out="";
        for(int i=0;i<balls.length;i++)
            out= out + Character.toString(balls[i].getColor());
        return out;
    }
    
    public static void setBalls(String guess, Ball[] balls ){
        for(int i=0;i<guess.length();i++)
            balls[i].setColor(guess.charAt(i));
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Mastermind Game!!");
        int ballIndex[]=new int[4];
        for(int i=0;i<4;i++)
            ballIndex[i]=i;
        Ball b1=new Ball();
        b1.setColor(globalColours.charAt(ballIndex[0]));
        Ball b2=new Ball();
        b2.setColor(globalColours.charAt(ballIndex[1]));
        Ball b3=new Ball();
        b3.setColor(globalColours.charAt(ballIndex[2]));
        Ball b4=new Ball();
        b4.setColor(globalColours.charAt(ballIndex[3]));
        Ball balls[] =new Ball[4];
        balls[0]=b1;
        balls[1]=b2;
        balls[2]=b3;
        balls[3]=b4;
        Check checker = new Check();
        String comparedFlags=checker.checkBalls(balls);
        System.out.println("Guessed ball:" + printBalls(balls));
        System.out.println("Black:" + checker.blackCount(comparedFlags) + " Whites: " + checker.whiteCount(comparedFlags) + " Trial Num: " + checker.tryCount);
        if(checker.blackCount(comparedFlags)<4){
            Bot bot=new Bot();
            while(true){
                bot.update(balls, checker, comparedFlags, printBalls(balls));
                bot.getAdvice(checker,comparedFlags);
                System.out.println("Guessed ball:" + bot.guess);
                setBalls(bot.guess,balls);
                comparedFlags=checker.checkBalls(balls);
                System.out.println("Black:" + checker.blackCount(comparedFlags) + " Whites: " + checker.whiteCount(comparedFlags) + " Trial Num: " + checker.tryCount);                
                if(checker.blackCount(comparedFlags)==4)
                    break;
            }
        }
        System.out.println("The bot won in the " + (checker.tryCount) + " try!");
        System.out.println("Random placement of balls:"  + checker.getAdminColor());
    }
}
