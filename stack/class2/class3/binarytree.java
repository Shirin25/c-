import java.util.ArrayList;
import java.util.HashMap;
public class binarytree{
    public static class Node{
        int data=0;
        Node left=null;
        Node right=null;

        Node()
        {

        }


        Node(int data)
        {
            this.data=data;
        }

        Node(int data,Node left,Node right)
        {
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }
    static int idx=0;
    public static Node createtree(int[] arr)
    {
        if(idx==arr.length||arr[idx]==-1)
        {
            idx++;
            return null;
        }

        Node node=new Node(arr[idx]);
        idx++;
        node.left=createtree(arr);
        node.right=createtree(arr);

        return node;
    }
    public static void display(Node node)
    {
        if(node==null) return;
        String ans="";
        

        ans+=node.left!=null?node.left.data:".";
        ans+="->"+node.data+"<-";
        ans+=node.right!=null?node.right.data:".";

        System.out.println(ans);


         display(node.left);
         display(node.right);


    }

    public static int size(Node node)
    {
        if(node==null)
        {
            return 0;
        }

        int count=0;
        count+=size(node.left);
        count+=size(node.right);
        return count+1;

    }

    public static int height(Node node)
    {
        if(node==null)
        {
            return -1;
        }

        int lh=0;
        int rh=0;
         lh+=height(node.left);
        rh+=height(node.right);

        return (Math.max(lh,rh))+1;

    

    }

    public static boolean finddata(Node node,int data)
    {
        if(node==null) return false;

        if(node.data==data) return true;

        boolean res=false;
        res=res||finddata(node.left,data);
        res=res||finddata(node.right,data);

        return res;
    }

    public static ArrayList<Node> nodetoRootpath(Node node,int data)
    {
        if(node==null)
        return null;

        if(node.data==data)
        {
            ArrayList<Node> base=new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left=nodetoRootpath(node.left,data);
        {
            if(left!=null)
            {
                left.add(node);
                return left;
            }
        }

        ArrayList<Node> right=nodetoRootpath(node.right,data);
        {
            if(right!=null)
            {
                right.add(node);
                return right;
            }
        }

        return null;
    }

    public static Node lca_01(Node node,int data1,int data2)
    {
        ArrayList<Node>list1=nodetoRootpath(node,data1);
        ArrayList<Node>list2=nodetoRootpath(node,data2);

        if(list1==null||list2==null)
        return null;
            int i=list1.size()-1;
            int j=list2.size()-1;
            Node prev=null;
            while(i>=0&&j>=0)
            {
                if(list1.get(i).data!=list2.get(j).data)
                {
                    break;
                }
                prev=list1.get(i);
                i--;
                j--;
            }
            

        
        return prev;

    }



    public static int diameter(Node node)
    {
        if(node==null)
        return 0;

        int ld=diameter(node.left);
        int rd=diameter(node.right);

        int lh=height(node.left);
        int rh=height(node.right);

        return Math.max(Math.max(ld,rd),lh+rh+2);
    }

    public static int[] diameter_02(Node node)
    {
        if(node==null) return new int[]{0,-1};
         int []ld=diameter_02(node.left);
         int[]rd=diameter_02(node.right);

         int []ans=new int[2];

         ans[0]=Math.max(Math.max(ld[0],rd[0]),ld[1]+rd[1]+2);

         ans[1]=Math.max(ld[1],rd[1])+1;


         return ans;
         
    }
   static int maxDia=0;
    public static int diameter_03(Node node)
    {
        if(node==null) return-1;
        int lh=diameter_03(node.left);
        int rh=diameter_03(node.right);

        maxDia=Math.max(maxDia,lh+rh+2);
        return Math.max(lh,rh)+1;
    }

    static int maxSum=(int) -1e7;

    public static int leaftonode(Node node)
    {
        if(node==null)
        return (int) -1e7;

        if(node.left==null&&node.right==null)
        return node.data;

        int leftSum=leaftonode(node.left);
        int rightSum=leaftonode(node.right);

        if(node.left!=null&node.right!=null)
        {
            maxSum=Math.max(maxSum,leftSum+rightSum+node.data);
        }
       int ans=0;
        if(node.left==null)
         ans= rightSum+node.data;

        if(node.right==null)
         ans=leftSum+node.data;

        if(node.left!=null&&node.right!=null)
        ans= Math.max(leftSum,rightSum)+node.data;

        return ans;


    }

    public static int maxPathSum(Node node)
    {
        if(node==null)
        return 0;

        

        int leftSum=leaftonode(node.left);
        int rightSum=leaftonode(node.right);
        int Sidemax=Math.max(leftSum,rightSum)+node.data;

        
        
            maxSum=Math.max(Math.max(maxSum,Sidemax),Math.max(leftSum+rightSum+node.data,node.data));

            return Math.max(Sidemax,node.data);
        

        
    }

    static int ans=0;
    public static void pathIIISum(Node node,int tar,int prefixSum,HashMap<Integer,Integer>map)
    {
        if(node==null)
        {
            return;
        }

        prefixSum+=node.data;

        ans+=map.getOrDefault(prefixSum-tar,0);
        map.put(prefixSum,map.getOrDefault(prefixSum,0)+1);

        pathIIISum(node.left,tar,prefixSum,map);
        pathIIISum(node.right,tar,prefixSum,map);

        map.put(prefixSum,map.getOrDefault(prefixSum,1)-1);
    }
//-1:need camera;
//0:I am camera;
//1:don't need camera;
    static int cameracount=0;
    public static int camera_(Node node)
    {
        if(node==null)
        return 1;
        
        int left=camera_(node.left);
        int right=camera_(node.right);

        if(left==-1||right==-1)
        {
            cameracount++;
            return 0;
        }

        if(left==0||right==0)
        {
            return 1;
        }
        return -1;

    }

    public static Node constructBST(int[] arr,int si,int ei)
    {
        if(si>ei)
        return null;

        int mid=(si+ei)>>1;

        Node node=new Node(arr[mid]);

        node.left=constructBST(arr,si,mid-1);
        node.right=constructBST(arr,mid+1,ei);
        
        return node;

    }

    public static boolean findinBST(Node node,int data)
    {
       while(node!=null)
       {
           if(node.data==data)
           return true;

           else if(data<node.data)
           {
               node=node.left;
           }


           else if(data>node.data)
           {
               node=node.right;
           }

       }

       return false;
    }
    public static class pair{
        int size=0;
        int height=0;
        boolean find=false;


        int ceil=Integer.MAX_VALUE;
        int floor=Integer.MIN_VALUE;

        Node pred=null;
        Node succ=null;
        Node prev=null;
    }

        public static void allSolution(Node node,int level,int data,pair p)
        {
            if(node==null)
                return;

            p.height=Math.max(p.height,level);
            p.size++;
            p.find=p.find||node.data==data;

            if(data<node.data)
            {
                p.ceil=Math.min(p.ceil,node.data);
            }

            if(node.data<data)
            {
                p.floor=Math.max(p.floor,node.data);
            }
            
            if(node.data==data && p.pred==null)
            {
                p.pred=p.prev;
               
            }

            if(p.prev!=null && p.prev.data==data && p.succ==null)
            {
                p.succ=node;
            }
            p.prev=node;
            allSolution(node.left,level+1,data,p);
            allSolution(node.right,level+1,data,p);
        }

        public static void BSTPredSucc(Node node, int data) {
        Node pred = null;
        Node succ = null;

        while (node != null) {
            if (node.data > data) {
                succ = node;
                node = node.left;
            } else if (node.data < data) {
                pred = node;
                node = node.right;
            } else {
                Node temp = node;
                if (node.right != null) {
                    node = node.right;
                    while (node.left != null) {
                        node = node.left;
                    }
                    succ = node;
                }

                node = temp;
                if (node.left != null) {
                    node = node.left;
                    while (node.right != null) {
                        node = node.right;
                    }
                    pred = node;
                }

                break;
            }
        }
    }

    // view Set.==================================

    public static void lineWiseLevelOrder(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            System.out.print("Level: " + level + " -> ");

            while (size-- > 0) {
                Node rnode = que.removeFirst();
                System.out.print(rnode.data + ", ");

                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }

                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }

            level++;
            System.out.println();
        }
        System.out.println();
    }

    public static void leftView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().data + " ");
            while (size-- > 0) {
                Node rnode = que.removeFirst();

                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }

                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }

        }
        System.out.println();
    }

    public static void rightView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() != 0) {
            int size = que.size();
            Node prev = null;
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                prev = rnode;
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }

                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }

            System.out.print(prev.data + " ");

        }
        System.out.println();
    }

    public static void topView(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() != 0) {
            int size = que.size();
            System.out.print(que.getFirst().data + " ");
            Node prev = null;
            int count = 0;
            while (size-- > 0) {
                Node rnode = que.removeFirst();
                prev = rnode;
                count++;
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }

                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
            }
            if (count > 1)
                System.out.print(prev.data + " ");
            System.out.println();

        }
        System.out.println();
    }

    public static void verticalOrderPrint(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();

        queN.addLast(node);
        queI.addLast(0);

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int min = 0;
        int max = 0;

        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();

                min = Math.min(verticalLevel, min);
                max = Math.max(verticalLevel, max);

                if (!map.containsKey(verticalLevel))
                    map.put(verticalLevel, new ArrayList<>());

                map.get(verticalLevel).add(rnode.data);

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }

        for (int i = min; i <= max; i++) {
            System.out.println(map.get(i));
        }

        System.out.println();
    }

    public static void verticalOrderPrint_02(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();

        int[] widthA = new int[2];
        width(node, 0, widthA);

        queN.addLast(node);
        queI.addLast(-widthA[0]);

        ArrayList<Integer>[] ans = new ArrayList[widthA[1] - widthA[0] + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<>();
        }

        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();

                ans[verticalLevel].add(rnode.data);

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

        System.out.println();
    }

    public static void verticalOrderSum_02(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();

        int[] widthA = new int[2];
        width(node, 0, widthA);

        queN.addLast(node);
        queI.addLast(-widthA[0]);

        int[] ans = new int[widthA[1] - widthA[0] + 1];

        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();

                ans[verticalLevel] += rnode.data;

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

        System.out.println();
    }

    public static void BottomView(Node node) {
        LinkedList<Node> queN = new LinkedList<>();
        LinkedList<Integer> queI = new LinkedList<>();

        int[] widthA = new int[2];
        width(node, 0, widthA);

        queN.addLast(node);
        queI.addLast(-widthA[0]);

        int[] ans = new int[widthA[1] - widthA[0] + 1];

        while (queN.size() != 0) {
            int size = queN.size();
            while (size-- > 0) {
                Node rnode = queN.removeFirst();
                Integer verticalLevel = queI.removeFirst();

                ans[verticalLevel] = rnode.data;

                if (rnode.left != null) {
                    queN.addLast(rnode.left);
                    queI.addLast(verticalLevel - 1);
                }

                if (rnode.right != null) {
                    queN.addLast(rnode.right);
                    queI.addLast(verticalLevel + 1);
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

        System.out.println();
    }

   

    public static void width(Node node, int level, int[] ans) {
        if (node == null)
            return;
        ans[0] = Math.min(ans[0], level);
        ans[1] = Math.max(ans[1], level);

        width(node.left, level - 1, ans);
        width(node.right, level + 1, ans);

    }


    public static void viewSet(Node node) {
        lineWiseLevelOrder(node);
        // leftView(node);
        // rightView(node);
        // topView(node);
         // verticalOrderPrint(node);
        verticalOrderPrint_02(node);
        // verticalOrderSum_02(node);
        BottomView(node);
    }
    public static void main(String[] args)
    {
        //int []arr={10,20,40,60,-1,-1,70,-1,-1,50,80,-1,-1,-1,30,90,-1,110,150,-1,-1,-1,100,120,-1,-1,-1};
        int[] arr={2,6,10,25,26,27,39,40,52,67,72};
        //Node node=createtree(arr);
        Node node=constructBST(arr,0,arr.length-1);
        display(node);
        //System.out.println(findinBST(node,40));
        //int result=leaftonode(node);
        //System.out.println(maxSum);
        //System.out.println(size(node));
       // System.out.println(height(node));
       //System.out.println(finddata(node,82));
       pair p=new pair();
       allSolution(node,0,10,p);
       System.out.println("pred"+" "+p.pred.data);
       System.out.println("succ"+" "+p.succ.data);
       System.out.println("ceil"+" "+ p.ceil);
       System.out.println("floor"+" "+p.floor);
       System.out.println("height"+" "+p.height);
       System.out.println("size"+" "+p.size);

       
       
    //    Node p=lca_01(node,70,30);
    //    System.out.println(p.data);
       
       
        
    }

}