public class Node {
    public String data;
    public Node left;
    public Node right;
    public Node(String dataIn){
        this.data = dataIn;
        this.left = null;
        this.right = null;
    }

    //-------------------------------------------------------Creating Tree ------------------------------------------------------------
    public void createTree(Node n ,Node top){
        Node curr = top;
        int compareVal = curr.getData().compareTo(n.getData());
        boolean valid = false;
        while(!valid){// while statement ends once a. a value is assigned to the next word or b. that word is repeated
            
            if( compareVal < 0 ){ //n is right to top

                if(curr.right == null){//// if left of the current root is null then node will be places there
                    curr.right = n;
                    valid = true;
                }else{
                    curr = curr.right;
                    compareVal =  curr.getData().compareTo(n.getData());
                }

            }else if( compareVal > 0 ){// n is left to top

                if(curr.left == null){// if left of the current root is null then node will be places there
                    curr.left = n;
                    valid = true;
                }else{
                    curr = curr.left;
                    compareVal =  curr.getData().compareTo(n.getData());
                }

            }else if ( compareVal == 0){// n is the same as top
                valid = true; //Loop is terminated no value to add
            }
        }

    }
    //----------------------------------------------------------- Insertion ------------------------------------------------------------
    public void insert(String wordIn, Node top){
        Node curr = top;
        int compareVal = curr.getData().compareTo(wordIn);
        boolean valid = false;
        while(!valid){// while statement ends once a. value is assigned to the next word or b. that word is repeated
            
            if( compareVal < 0 ){ //n is right to top

                if(curr.right == null){
                    curr.right = new Node(wordIn);
                    System.out.println(wordIn+" Has been added to Dictionary");
                    valid = true;
                }else{
                    curr = curr.right;
                    compareVal =  curr.getData().compareTo(wordIn);
                }
            }else if( compareVal > 0 ){// n is left to top

                if(curr.left == null){
                    curr.left = new Node(wordIn);
                    System.out.println(wordIn+" Has been added to Dictionary");
                    valid = true;
                }else{
                    curr = curr.left;
                    compareVal =  curr.getData().compareTo(wordIn);
                }
            }else if ( compareVal == 0){// n is the same as top

                System.out.print("\n"+curr.getData()+" is already in Dictionary");
                valid = true; //Loop is terminated 
            }
        }

    }
    //-------------------------------------------------- Search -----------------------------------------------------------------
    public boolean search(String wordIn, Node top){
        Node curr = top;
        int compareVal = curr.getData().compareTo(wordIn);
        boolean valid = false;
        while(!valid){// while statement ends once a. a value is assigned to the next word or b. that word is repeated
           
            if( compareVal < 0 ){ //n is right to top

                if(curr.right == null){
                    System.out.print("\n"+wordIn+" is not in the Dictionary");
                    valid = true;
                }else{
                    curr = curr.right;
                    compareVal =  curr.getData().compareTo(wordIn);
                }
            }else if( compareVal > 0 ){// n is left to top

                if(curr.left == null){
                    System.out.print("\n"+wordIn+" is not in the Dictionary ");
                    valid = true;
                }else{
                    curr = curr.left;
                    compareVal =  curr.getData().compareTo(wordIn);
                }
            }else if ( compareVal == 0){// n is the same as top

                System.out.print("\n"+curr.getData()+" is in Dictionary");
                return true; //Loop is terminated 
            }

        }
        return false;
    }
    //---------------------------------------------------- Delete ------------------------------------------------------------
    public Node delete( Node valToDelete, Node root ){
        if( root == null ){
            System.out.println(valToDelete.getData()+" Is not in Dictionary to delete");
            return root;  // value to Delete is not in Binary tree
        }
        int compareResult = valToDelete.data.compareTo( root.data );
        if( compareResult < 0 ){
            root.left = delete( valToDelete, root.left );

        }else if( compareResult > 0 ){
            root.right = delete( valToDelete, root.right );

        }else if( root.left != null && root.right != null ){ // Two children
            root.data = findSuccesor( root.right ).data;
            root.right = delete( root , root.right );

        }else{
            if( root.left != null){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return root;
    }
    private Node findSuccesor( Node curr ){

        if( curr == null ){
            return null;

        }else if( curr.left == null ){
            return curr;
        }
        return findSuccesor( curr.left );
    }
     //------------------------------------------------------- Get Height of Tree ------------------------------------------------------------
    public int getHeight(Node root){
        if(root == null){return -1;}
        int left = 1 + getHeight(root.left);
        int right = 1 + getHeight(root.right);
        if( left > right){
            return left;
        }else{
            return right;
        }
    }

    //------------------------------------------------------- Get Data and Ordering of tree ------------------------------------------------------------
    public String getData(){
        return data;
    }

    public void inOrder(Node n){
        if(  n != null ){
            inOrder(n.left);
            System.out.print(n.data+" ");
            inOrder(n.right);
        }
    }
    public void preOrder(Node n){
        if( n != null){
            System.out.print(n.data+" ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }
    public void postOrder(Node n){
        if( n != null){
            postOrder(n.left);
            postOrder(n.right);
            System.out.print(n.data+" ");
        }
    }
}
