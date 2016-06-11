public class AVLTreeTest
{
  public static void printStats(AVLTree tree)
  {
      System.out.println("CHECK BALANCE : " + tree.isHeightBalanced() );
      System.out.println("CHECK HEIGHT: " + tree.height());
      System.out.println("CHECK NODE: " + tree.size());
  }
  
   @SuppressWarnings("unchecked")
public static void main(String[] args)
   {

      System.out.println("Programmed by: Daniel Rojas");
      System.out.println("_________TEST CASES_________");
      
      AVLTree<Integer> tree = new AVLTree<Integer>();
      int [] numbers = { 10,70, 20,30,40,50,60};
      int[] delnumbers = { 40, 60, 70, 35}; 
      int[] b= { 20, 30, 50, 10, 40};
      
      System.out.println("//////////////////     Test # 1     //////////////////");
      
      for(int k = 0; k < numbers.length; k++)
            tree.insert(numbers[k]);
      
      System.out.println("INORDER PRINT:");
      tree.printInorder();
      
      System.out.println("\nPREORDER PRINT:");
      tree.printPreorder();
      System.out.println("\n");
   
      printStats(tree);
            
      System.out.println("//////////////////     Test # 2     //////////////////");
      int z = 89;
      System.out.println( z + " is in tree? " + tree.search(z));
      z = 50;
      System.out.println( z + " is in tree? " + tree.search(z));
      
      System.out.println("//////////////////     Test # 3     //////////////////");

      for(int k = 0; k < delnumbers.length; k++)
      {
    	  System.out.println("Delete: " + delnumbers[k]);
          tree.delete(delnumbers[k]);
          
          System.out.println("\nPREORDER PRINT:");
          tree.printPreorder();
      }
      

      System.out.println("\nPREORDER PRINT:");
      tree.printPreorder();
      System.out.println("\n");

      printStats(tree);

      System.out.println("//////////////////     Test # 4     //////////////////");
      for(int k = 0; k < b.length; k++)
      {
          System.out.println(tree.delete(b[k]) + "  " );
          System.out.println("\nPREORDER PRINT:");
          tree.printPreorder();
      }
      System.out.println();
      tree.printInorder();
      tree.printPreorder();
      printStats(tree);
      
      System.out.println("//////////////////     Test # 5     //////////////////");
      AVLTree tree4 = new AVLTree();
      
      System.out.println("New tree3 size: " + tree4.size());
      int k = 100;
      while( tree4.size() != 10)
      {
          tree4.insert(k);
          k++;
      	System.out.println("HEIGHT:" + tree4.height()); 
      }
      
      System.out.println("//////////////////     Test # 6     //////////////////");
      System.out.println("Delete all values using while loop:");
      k = 100;
      while( !tree4.isEmpty())
      {
         tree4.delete(k);
         k++;
         System.out.println("NOW THAT ITS DELETED, PRINT: ");
         tree4.printPreorder();
         System.out.println("SIZE: " + tree4.size());
         System.out.println(tree4.height() + "/"  +tree4.isHeightBalanced() + "  ");
      }
      System.out.println();
      
      System.out.println("//////////////////     Test # 7     //////////////////");
      AVLTree tree3 = new AVLTree();
      
      System.out.println("New tree3 size: " + tree3.size());
      while( tree3.size() != 10)
      {
          tree3.insert(100);
      	System.out.println("HEIGHT:" + tree3.height()); 
      }

      System.out.println("INORDER PRINT:");
      tree3.printInorder();
      
      System.out.println("tree3 size after inserts: " + tree3.size());

      tree3.delete(100);
      
      System.out.println("tree3 size after delete 100: " + tree3.size());
      
      System.out.println("INORDER PRINT:");
      tree3.printInorder();
      System.out.println("\nPREORDER PRINT:");
      tree3.printPreorder();
      System.out.println("\n\n");
      printStats(tree3);
      
      System.out.println("//////////////////     Test # 8     //////////////////");
      System.out.println("Delete all values using while loop:");
      while( !tree3.isEmpty())
      {
         tree3.delete(100);
         System.out.println("NOW THAT ITS DELETED, PRINT: ");
         tree3.printPreorder();
         System.out.println("SIZE: " + tree3.size());
         System.out.println(tree3.height() + "/"  +tree3.isHeightBalanced() + "  ");
      }
      System.out.println();
         
    
      System.out.println("//////////////////     Test # 9     //////////////////");
      AVLTree tree2 = new AVLTree();
      int n = 5000;
      for( int j = 0; j < n; j++)
         tree2.insert(  (int) (Math.random() * 100) );
      printStats(tree2);
      System.out.println( "log2(" + n + ")  = " + Math.log(n)/Math.log(2));
      
      /* System.out.println("//////////////////     Test # 10     //////////////////");
      while(tree2.search(50))
      {
    	  tree2.delete(50); 
      }
      System.out.println(" 50 is still in the tree? " + tree2.search(50));
      printStats(tree2);    */           
 }
}