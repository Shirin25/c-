import java.util.HashMap;
import java.util.ArrayList;
public class test{
    public static void main(String[] args)
    {
        // Hashmapoperation01();
         //Hashmapoperation02("abcdehhjksjf");
         //Hashmapoperation03("abchhskfhs");
         int [] one={1,1,1,2,2,4,5};
         int [] two={1,1,2,2,2,3,4,5};
         //getcommonseries(one,two);
          getcommonseries02(one,two);

    }

    public static void Hashmapoperation01()
    {
        HashMap<String,Integer>map=new HashMap<>();
        map.put("USA",100);
        map.put("PAK",100);
        map.put("NEPAL",80);
        map.put("ITALY",90);
        map.put("INDIA",1000);

        System.out.println(map);
    }
    public static void Hashmapoperation02(String str)
    {
       HashMap<Character,ArrayList<Integer>>map=new HashMap<>();
       for(int i=0;i<str.length();i++)
       {
           char ch =str.charAt(i);
           if(!map.containsKey(ch)) map.put(ch,new ArrayList<>());

           map.get(ch).add(i);



       }

       for(Character ch:map.keySet())
       {
           System.out.println(ch+"->"+map.get(ch));
       }
    }

    public static void Hashmapoperation03(String str)
    {
       HashMap<Character,Integer>map=new HashMap<>();
       for(int i=0;i<str.length();i++)
       {
           char ch =str.charAt(i);
       if(!map.containsKey(ch)) map.put(ch,1);
      else map.put(ch,map.get(ch)+1);

       // map.put(ch,getOrDefault(ch,0)+1);



       }
        for(Character ch:map.keySet())
       {
           System.out.println(ch+"->"+map.get(ch));
       }

       
    }
    public static void getcommonseries(int[] one,int [] two)
    {
        HashMap<Integer,Boolean>map=new HashMap<>();

        for(int ele:one)
        {
            map.put(ele,true);
        }

        for(int ele:two)
        {
            if(map.containsKey(ele))
            {
                System.out.print(ele+" ");
                map.remove(ele);
            }
        }

        System.out.println();
    }

    public static void getcommonseries02(int[] one,int [] two)
    {
        HashMap<Integer,Integer>map=new HashMap<>();

        for(int ele:one)
        {
            map.put(ele,map.getOrDefault(ele,0)+1);
        }

        for(int ele:two)
        {
            if(map.containsKey(ele))
            {
                System.out.print(ele+" ");
                if(map.get(ele)==1) map.remove(ele);
                else map.put(ele,map.get(ele)-1);
            }
        }

        System.out.println();
    }
}
