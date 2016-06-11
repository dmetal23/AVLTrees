/* 
 * Programmed by: Daniel Rojas
 * 06/10/16 
 * Cal State Northridge 
 */

class AVLNode <E extends Comparable<E> >   
{   
	private E  item;
	private AVLNode<E>  parent;
	private AVLNode<E>  left;   
	private AVLNode<E>  right;  
	
	public AVLNode (E item) 
	{  
		this.item = item;
		parent = null;
		left = null;
		right = null;
	}  
	
	public AVLNode(E item, AVLNode<E> parent, AVLNode<E> left, AVLNode<E> right) 
	{
		this.item = item;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	public E getItem()
	{
		return this.item;
	}
	
	public AVLNode<E> getParent()
	{
		return this.parent;
	}
		
	public AVLNode<E> getLeft()
	{
		return left;
	}
		
	public AVLNode<E> getRight()
	{
		return right;
	}
			
	public void setItem(E item)
	{
		this.item = item;
	}
		
	public void setParent(AVLNode<E> parent)
	{
		this.parent = parent;
	}
		
	public void setLeft(AVLNode<E> left)
	{
		this.left = left;
	}
		
	public void setRight(AVLNode<E> right)
	{
		this.right = right;
	}
		
	public void toString(AVLNode<E> print)
	{
		System.out.println("Item: " + item);
	}
}


public  class AVLTree< E extends Comparable<E> >
{
	int size = 0;
	boolean right_child;
    private AVLNode<E> root;
        
	/* --------- Public Methods ---*/

	public AVLTree(){}  
	
	public boolean isEmpty()
	{
		return (this.root.getItem() == null);
	}
	
	public int size() 
	{
		return size;
	}    
	
    public int height_calc(AVLNode<E> root) 
    {
		if (root == null)
			return 0;
		else
		{
			int height_Left = height_calc(root.getLeft());
			int height_Right = height_calc(root.getRight());
		
			return max( height_Left +1 , height_Right +1 );
		}
    }
   
	public int height()  
	{
		if (size == 0)
			return 0;
		else
		return height_calc(root);
	}   
	
	public boolean search (E item)   
	{
		return (search_item(item) != null);		
	}
	
	public void insert (E  item) 
	{ 
		if (root == null)
		{
			root = new AVLNode<E>(item);
			size++;
		}
		else 
		{
			AVLNode<E> par = null;
			AVLNode<E> p = root;
			
			while (p != null)
			{
				if (item.compareTo(p.getItem()) < 0)
				{
					par = p;
					p = p.getLeft();
				}
				else  
				{
					par = p;
					p = p.getRight();
				}
			}
			
			AVLNode<E> w = new AVLNode<E>(item, par, null, null);
			if( item.compareTo(par.getItem()) < 0)
			{
				par.setLeft(w);
			}
			else 
			{
				par.setRight(w);
			}			
			size ++;
			balance();
		}
	}   
	
	public boolean delete (E  item )
	{
	   AVLNode<E> temp_root = root;

	   if (size == 0 || !search(item)  )
		   return false;
	   
	   if (size == 1 && temp_root.getItem().compareTo(item) == 0)
	   {
		   temp_root.setItem(null);
		   temp_root = null;
		   size--;
		   return true;
	   }
	   
		while ( temp_root != null )
		{		
			if (item.compareTo(temp_root.getItem()) < 0)
				temp_root = temp_root.getLeft();
			else if (item.compareTo(temp_root.getItem()) == 0)
					break;
			else 
				temp_root = temp_root.getRight();
			if (temp_root.getItem().compareTo(item) == 0) 				
					break; 
		}	

		if (size == 1 && height_calc(temp_root) == 1)
		{
				root.setItem(null);
		}
		
		if (size == 2 && height_calc(temp_root) == 2)
		{
			if (temp_root.getLeft() != null)
			{
				root = temp_root.getLeft(); 
				root.setLeft(null);
			}
			else if (temp_root.getRight() != null)
			{
				root = temp_root.getRight(); 
				root.setRight(null);
			}
				size--;
				return true;	
		}
		
		if (delete_leaf(temp_root, item))
			return true;
		
		if (delete_parent_with_left_child(temp_root, item))
			return true;
		
		if (delete_parent_with_right_child(temp_root, item))
			return true;
		
		if (delete_parent_with_two_children(temp_root, item))
			return true;
			
		return false;		
	} 
		
	public boolean isHeightBalanced() 
	{
		return (isHeightBalanced_test(root));
	}  
	
	public void printPreorder() 
	{  
		if (size == 0)
			System.out.println("Tree is empty.");
		else
		{
			preorder_recursion(root);
			System.out.println();
		}
	}
	
	
	public void printInorder() 
	{  	
		if (size == 0)
		System.out.println("Tree is empty.");
		
		else
		{
			inorder_recursion(root);
			System.out.println();	
		}
	} 

	private void balance()
	{
		AVLNode<E> temp_root = root;
		balance_it(temp_root);
	}
	
	private void balance_it(AVLNode<E> temp_root)
	{
		while (!isHeightBalanced_test(temp_root))
		{
			while (!isHeightBalanced_test(temp_root))
			{
				if (!isHeightBalanced_test(temp_root.getLeft()))
					temp_root = temp_root.getLeft();
				
				else if(!isHeightBalanced_test(temp_root.getRight()))
					temp_root = temp_root.getRight();
				
				else break;
			}
			
			int rightChild = height_calc(temp_root.getRight());
			int leftChild = height_calc(temp_root.getLeft());
			if (rightChild > leftChild)
			{
				temp_root = temp_root.getRight();
			}
			else 
				temp_root = temp_root.getLeft();
			
			rightChild = height_calc(temp_root.getRight());
			leftChild = height_calc(temp_root.getLeft());
			if (rightChild > leftChild)
			{
				temp_root = temp_root.getRight();
			}
			else if (rightChild == leftChild)
			{
				if (temp_root.getRight().getItem().compareTo(temp_root.getLeft().getItem()) == 0 )
					temp_root = temp_root.getRight();
				else
					temp_root = temp_root.getLeft();
			}
			else 
				temp_root = temp_root.getLeft();
			
			triNodeRestructure(temp_root);

		}
	}
	
	private AVLNode<E> triNodeRestructure(AVLNode<E> x)
	   {
	      AVLNode<E> y = x.getParent();
	      AVLNode<E> z = x.getParent().getParent();

	      AVLNode<E> ggparent = z.getParent();  //may be null
	      
	      AVLNode<E> a,b,c;
	     AVLNode<E> t1,t2, t3, t4;
	    
	         
	      if( x == y.getLeft() && y == z.getLeft())
	      {  
	         a =x; b = y; c =z;
	         t1 = x.getLeft();
	         t2 =  x.getRight();
	         t3 = y.getRight();
	         t4 = z.getRight();
	            
	      }
	      else if( x==y.getRight() && y == z.getLeft())
	      {
	         a = y; b =x; c = z;
	         t1 = y.getLeft();
	         t2 = x.getLeft();
	         t3 = x.getRight();
	         t4 = z.getRight();
	            
	      }
	      else if(x == y.getRight() && y == z.getRight())
	      {
	         a=z; b=y; c=x;
	         t1 = z.getLeft(); 
	         t2 = y.getLeft();
	         t3 = x.getLeft();
	         t4 = x.getRight();
	            
	            
	      }
	      else if (x == y.getLeft() && y == z.getRight())
	      {
	         a =z; b = x; c = y;
	         t1 = z.getLeft();
	        t2 = x.getLeft();
	         t3 = x.getRight();
	         t4 = y.getRight();
	      }
	      else
	      {
	         a = b = c = null;
	         t1 = t2 =t3 = t4 = null;
	      }
	           
	      if(ggparent == null)
	      {
	         root = b;
	         b.setParent(null);
	      }
	      else if(z == ggparent.getLeft())
	      {
	        ggparent.setLeft(b);
	         b.setParent(ggparent);
	      }
	      else
	      {
	         ggparent.setRight(b);
	         b.setParent(ggparent);
	      }
	   
	      b.setLeft(a);
	      a.setParent(b);
	      b.setRight(c);
	      c.setParent(b);
	      a.setLeft(t1);
	      if ( t1 != null)
	         t1.setParent(a);
	      a.setRight(t2);
	      if (t2!= null)
	         t2.setParent(a);
	      c.setLeft( t3);
	      if ( t3 != null)
	         t3.setParent(c);
	      c.setRight(t4);
	      if ( t4 != null)
	         t4.setParent(c);
	                   
	      return b;
	   }

	private boolean delete_leaf( AVLNode<E> temp_root, E item)
	{
		if (temp_root.getLeft() == null && temp_root.getRight() == null) //LEAF NODE DELETION
		{	
			if (temp_root.getParent().getRight().getItem().compareTo(item) == 0) //is deleted leaf to 
				temp_root.getParent().setRight(null);
			else temp_root.getParent().setLeft(null);
			temp_root.setParent(null);
			temp_root.setItem(null);
			size--;
			balance();
			return true;
		}
		return false;
	}
	
	private boolean delete_left_leaf( AVLNode<E> temp_root, E item)
	{
		if (temp_root.getLeft() == null && temp_root.getRight() == null) 
		{	
			temp_root.getParent().setLeft(null);
			temp_root.setParent(null);
			temp_root.setItem(null);
			size--;
			balance();
			return true;
		}
		return false;
	}
	
	private boolean delete_right_leaf( AVLNode<E> temp_root, E item)
	{
		if (temp_root.getLeft() == null && temp_root.getRight() == null) 
		{	
			temp_root.getParent().setRight(null);
			temp_root.setParent(null);
			temp_root.setItem(null);
			size--;
			balance();
			return true;
		}
		return false;
	}
	
	private void preorder_recursion(AVLNode<E> root)
	{
		if (root == null) 
			 	return;

			System.out.print(root.getItem() + "  ");
			 preorder_recursion(root.getLeft());
			 preorder_recursion(root.getRight());
			 return;
	}
	
	
	
	
	
	private void inorder_recursion(AVLNode<E> root)
	{
		if (root == null) 
		{
		 	return;
		}
		else	
		{
			 inorder_recursion (root.getLeft());
			 System.out.print(root.getItem() + "  ");
			 inorder_recursion (root.getRight());
		}
	}
	
	
	private boolean isHeightBalanced_test(AVLNode<E> temp_root)
	{
		if ( temp_root == null || size == 0)
	        return true;
		else
		 {
		         int a = height_calc(temp_root.getLeft());
		         int b = height_calc( temp_root.getRight());
		         if ( Math.abs( a-b) > 1 )
		                return false;
		          else
			        return ( isHeightBalanced_test(temp_root.getLeft()) && 
			        		isHeightBalanced_test(temp_root.getRight()));
		}
	}
	
	private boolean delete_parent_with_two_children( AVLNode <E> temp_root, E item)
	{
		if(temp_root.getRight() != null && temp_root.getLeft() != null)
		{
			   AVLNode<E> replacement_node = temp_root;
			   right_child = true;
			   if (replacement_node.getRight().getItem() != null)
				   replacement_node = replacement_node.getRight();
			   		while (replacement_node.getLeft() != null)
			   		{
			   			right_child = false;
			   			replacement_node = replacement_node.getLeft();
			   		}
			   		if(replacement_node.getItem().compareTo(temp_root.getItem()) == 0)
			   		{
			   			if (right_child)
			   				replacement_node.getParent().setRight(replacement_node.getRight());
			   			else 
			   			{
			   				if(replacement_node.getRight() == null)
				   				replacement_node.getParent().setLeft(null);
			   				else
			   				{
			   					replacement_node.getParent().setLeft(replacement_node.getRight());
			   					replacement_node.getRight().setParent(replacement_node.getParent());
			   				}
			   			}
			   			replacement_node.setItem(null);
			   			replacement_node = null;
			   			balance();
			   			size--;
			   			return true;
			   		}
			   	temp_root.setItem(replacement_node.getItem());
			   	
			   	if(!right_child || replacement_node.getParent().getItem().compareTo(replacement_node.getItem()) > 0)
			   	{
					if (delete_left_leaf(replacement_node, item))
						return true;
			   	}
				else if( right_child || replacement_node.getParent().getItem().compareTo(replacement_node.getItem()) <= 0)
				{
					if (delete_right_leaf(replacement_node, item)) 
						return true;
				}

				
				if (delete_parent_with_right_child(replacement_node, item))
					return true; 	   	
			   	
		}		   
		return false;
	}

	private boolean delete_parent_with_right_child(AVLNode <E> temp_root, E item)
	{
		if(temp_root.getRight() != null && temp_root.getLeft() == null)
		{
			if (right_child || temp_root.getParent().getRight().getItem().compareTo(item) == 0)
				temp_root.getParent().setRight(temp_root.getRight());
			else temp_root.getParent().setLeft(temp_root.getRight());
			temp_root.getRight().setParent(temp_root.getParent());
			temp_root.setItem(null);
			size--;
			balance();
			return true;
		}
		return false;
	}

	private boolean delete_parent_with_left_child(AVLNode <E> temp_root, E item)
	{
		if(temp_root.getLeft() != null && temp_root.getRight() == null)
		{

			if (temp_root.getParent().getRight().getItem().compareTo(item) == 0)
				temp_root.getParent().setRight(temp_root.getLeft()); 
			else temp_root.getParent().setLeft(temp_root.getLeft());
			
			
			temp_root.getLeft().setParent(temp_root.getParent());
			temp_root.setItem(null);
			size--;
			balance();
			return true;
		}
		return false;
	}
	
	private E search_item(E item)
	{
		if (this.root == null)
		return null;
		
		AVLNode<E> temp_root = root;
		
		while (temp_root != null)
		{
			if (temp_root.getItem() == item )
				return item;
			
			else if (item.compareTo(temp_root.getItem()) >= 0)
				temp_root = temp_root.getRight();
			
			else temp_root = temp_root.getLeft();	
		}
		return null;
	}
	
	
	private int max (int i, int j)
	{
		if (i > j)
			return i;
		else 
			return j;
	}

	
}

