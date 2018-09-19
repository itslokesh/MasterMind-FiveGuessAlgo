/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermindhard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import static mastermindhard.MastermindHard.globalColours;

/**
 *
 * @author psg-skava
 */
public class Bot {
    public ArrayList<String> allCodes;
    public ArrayList<String> state;
    public String guess;
    public String bestGuess;
    int maxValue=6;
    int numDigits=4;
    int numTries=6;
    int totalPossibilities=(int) Math.pow(maxValue, numDigits);
    Bot(){
        state=new ArrayList<>();
        allCodes=new ArrayList<>();
        setInitState();
        guess=new String();
        bestGuess=new String();
    }
    public void setInitState(){
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                for(int k=0;k<6;k++){
                    for(int l=0;l<6;l++){
                        String s="";
                        s=Character.toString(MastermindHard.globalColours.charAt(i))
                                + Character.toString(MastermindHard.globalColours.charAt(j))
                                + Character.toString(MastermindHard.globalColours.charAt(k))
                                + Character.toString(MastermindHard.globalColours.charAt(l));
                        state.add(s);
                        allCodes.add(s);
                    }
                }
            }
        }
       // System.out.println(state.size());
       // System.out.println(allCodes.size());
    }
    
    public ArrayList<String> intersection(ArrayList<String> a,ArrayList<String> b){
        ArrayList<String> intersection=new ArrayList<String>();
        for(int i=0,j=0;i<a.size() && j<b.size();){
            int val=a.get(i).compareTo(b.get(j));
            if(val==0){
                intersection.add(a.get(i));
                i++;
                j++;
            }
            else if(val<0){
                i++;
            }
            else{
                j++;
            }
        }        
        return intersection;
    }
    
    public void update(Ball[] balls,Check check, String comparedFlags,String code){
        HashMap<String,ArrayList<String>> allPossibilities=getPossibilities(check,comparedFlags, code);
        ArrayList<String> newState=allPossibilities.get(comparedFlags);
        Collections.sort(newState);
        Collections.sort(state);
        state=intersection(newState,state);
    }
    
    public void getAdvice(Check check, String comparedFlags){
        int minMaxPossibilities=totalPossibilities;
        int maxPossibilities=0;
        ArrayList<String> minCode=new ArrayList<String>();
        for(int i=0;i<allCodes.size();i++){
            HashMap<String,ArrayList<String>> allPossibilities=getPossibilities(check,comparedFlags,allCodes.get(i));
            ArrayList<String> keys=new ArrayList<String>(allPossibilities.keySet());
            for(int j=0;j<keys.size();j++){
                ArrayList<String> currentPossibility=allPossibilities.get(keys.get(j));
                int intersectionLength=intersection(currentPossibility, state).size();
                if(intersectionLength>maxPossibilities){
                    maxPossibilities=intersectionLength;
                }
            }
            if(maxPossibilities<minMaxPossibilities){
                minMaxPossibilities=maxPossibilities;
                minCode.clear();
                minCode.add(allCodes.get(i));
            }
            else if(maxPossibilities==minMaxPossibilities){
                minCode.add(allCodes.get(i));
            }
        }
        ArrayList<String> intersection=intersection(minCode, state);
//                System.out.println(intersection.size());

        //might sort and get the min
        bestGuess=minCode.get(0);
        if(intersection.size()>0){
            bestGuess=intersection.get(0);
        }
        guess=bestGuess;
    }
    public int getIndex(Character c){
        for(int i=0;i<globalColours.length();i++)
            if(globalColours.charAt(i)==c)
                return i;
        return 0;
    }
    
    public String checkBalls(String a1,String b1) {
        String s = "";
        String inp=a1;
        String tempAdmin=b1;
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
    public HashMap<String,ArrayList<String>> getPossibilities(Check check,String comparedFlags,String code){
        HashMap<String,ArrayList<String>> m=new HashMap<>();
        for(int i=0;i<allCodes.size()-1;i++){
            String flags=checkBalls(allCodes.get(i),code);
            if(m.containsKey(flags)){
                ArrayList<String> temp=new ArrayList<String>(m.get(flags));
                temp.add(allCodes.get(i));
                m.remove(flags);
                m.put(flags, temp);
            }
            else{
                ArrayList<String> temp=new ArrayList<String>();
                temp.add(allCodes.get(i));
                m.put(flags, temp);
            }
        }
        return m;
    }
}
