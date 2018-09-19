package mastermindhard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static mastermindhard.MastermindHard.globalColours;

public class Check {
    private ArrayList<Ball> admin;
    private ArrayList<Character> ret;
    int tryCount;
    
    public int getIndex(Character c){
        for(int i=0;i<globalColours.length();i++)
            if(globalColours.charAt(i)==c)
                return i;
        return 0;
    }
    
    public void printAdminBalls(){
        for(int i=0;i<admin.size();i++)
            System.out.print(admin.get(i).getColor());
        System.out.println();
    }
    
    public Check() {
        tryCount=0;
        String c = "bcdefg";
        ArrayList<Character> color = new ArrayList();
        admin=new ArrayList<>();
        ret=new ArrayList<>();
        int i;
        for(i = 0; i < 6; ++i) {
            color.add(new Character(c.charAt(i)));
        }
        Collections.shuffle(color);
        for(i = 0; i < 4; ++i) {
          Ball temp=new Ball(color.get(i));
          admin.add(temp);
          ret.add(temp.getColor());
        }
        System.out.print("Actual Combination of balls: ");
        printAdminBalls();
    }

    public String checkBalls(Ball[] balls) {
        String s = "";
        String inp="";
        for(int i=0;i<balls.length;i++)
            inp=inp.concat(Character.toString(balls[i].getColor()));
        String tempAdmin=new String("");
        for(int i=0;i<4;i++)
            tempAdmin=tempAdmin.concat(Character.toString(admin.get(i).getColor()));
        tryCount++;
        int i=0;
        while(i<inp.length()){
            if(inp.charAt(i)==tempAdmin.charAt(i)){
                s = s + "B";
                if(i+1==inp.length()){
                    inp=inp.substring(0, i);
                    tempAdmin=tempAdmin.substring(0,i);
                }
                else{
                    inp=inp.substring(0,i) + inp.substring(i+1);
                    tempAdmin=tempAdmin.substring(0,i)+ tempAdmin.substring(i+1);
                }
            }
            else
                i++;
        }
        i=0;
        int a[]=new int[6];
        int b[]=new int[6];
        for(i=0;i<inp.length();i++){
            a[getIndex(inp.charAt(i))]++;
            b[getIndex(tempAdmin.charAt(i))]++;
        }
        for(i=0;i<6;i++){
            if(a[i]!=0 && b[i]!=0){
                for(int j=0;j<Math.min(a[i], b[i]);j++)
                    s+="W";
            }
        }
        return s;
    }
    public String checkBalls(String z) {
        String s = "";
        String inp=z;
        String tempAdmin=new String();
        for(int i=0;i<4;i++)
            tempAdmin=tempAdmin.concat(Character.toString(admin.get(i).getColor()));
        tryCount++;
        int i=0;
        while(i<inp.length()){
            if(inp.charAt(i)==tempAdmin.charAt(i)){
                s = s + "B";
                if(i+1==inp.length()){
                    inp=inp.substring(0, i);
                    tempAdmin=tempAdmin.substring(0,i);
                }
                else{
                    inp=inp.substring(0,i) + inp.substring(i+1);
                    tempAdmin=tempAdmin.substring(0,i)+ tempAdmin.substring(i+1);
                }
            }
            else
                i++;
        }
        i=0;
        int a[]=new int[6];
        int b[]=new int[6];
        for(i=0;i<inp.length();i++){
            a[getIndex(inp.charAt(i))]++;
            b[getIndex(tempAdmin.charAt(i))]++;
        }
        for(i=0;i<6;i++){
            if(a[i]!=0 && b[i]!=0){
                for(int j=0;j<Math.min(a[i], b[i]);j++)
                    s+="W";
            }
        }
        if(s.length()>0){
            char tempArray[] = s.toCharArray(); 
            Arrays.sort(tempArray); 
            return new String(tempArray); 
        }
        return s;
    }
    
    public int blackCount(String s){
        int count=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)=='B')
                count++;
        return count;
    }
    
    public int whiteCount(String s){
        int count=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)=='W')
                count++;
        return count;
    }
    
    public String getAdminColor(){
        return ret.toString();
    }
}
