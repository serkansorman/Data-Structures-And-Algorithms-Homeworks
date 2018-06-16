package part1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * General tree elemanlarının binary treede depolandığı bir tree yapısı
 * @param <E> Tree yi oluşturan elemanların tipi
 */
public class GeneralTree<E> extends BinaryTree<E> {

    public GeneralTree(){
        super();
    }

    private GeneralTree(Node<E> root){
        super(root);
    }

    /**
     * Tree ye gelen childı parentın soluna ekler kardes node ise sağa ekler
     * @param parentItem Child eklenecek parent item
     * @param childItem Parentın soluna eklenecek olan child item
     * @return true eger child basarıyla eklenirse.
     */
    public boolean add(E parentItem, E childItem) {
        if(root == null) {
            root = new Node<>(parentItem);
            root.left = new Node<>(childItem);
            return true;
        }
        StringBuilder sb = new StringBuilder();
        return levelOrderSearch(parentItem,sb) != null && add(root, parentItem, childItem);
    }

    /**
     * Recursive add in yapıldığı private add methodu
     * @param node  üzerinde gezilmeye başlanan node
     * @param parentItem Child eklenecek parent item
     * @param childItem Parentın soluna eklenecek olan child item
     * @return true eger child basarıyla eklenirse.
     */
    private boolean add(Node node,E parentItem,E childItem){

        //Parent bulunduğunda solu boş ise child eklenir değilse mevcut childın sağına kardeş olarak eklenir
        if(node.data.equals(parentItem)) {
            if(node.left == null)
                node.left = new Node<>(childItem);
            else{
                Node tempNode = node.left;
                while(tempNode.right != null)
                    tempNode = tempNode.right;
                tempNode.right = new Node<>(childItem);
            }
            return true;
        }

        //Parent bulunana kadar dallanmaya devam eder.
        if(node.left != null)
            add(node.left,parentItem,childItem);
        if(node.right != null)
            add(node.right,parentItem,childItem);

        return true;
    }

    /**
     * Elemanı tree içinde level order olarak arar ve gezilen nodeları string buildera ekler
     * @param target aranan elemean
     * @param sb Gezilen nodeların eklendiği string
     * @return Aranan elemanın nodeu return edilir yoksa null return edilir.
     */
    public Node levelOrderSearch(E target,StringBuilder sb){
        if(root == null || root.data.equals(target))
            return root;
        Queue<Node> queue = new LinkedList<>();
        sb.append(root.toString()+" ");
        return levelOrderSearch(root.left,target,queue,sb);
    }

    /**
     * Elemanın recursive olarak level order aranması
     * @param node üzerinde gezilmeye başlanan node
     * @param target aranan eleman
     * @param queue Her leveldeki nodeların eklendiği queue
     * @param sb  Gezilen nodeların eklendiği string
     * @return Aranan elemanın nodeu return edilir yoksa null return edilir.
     */
    private Node levelOrderSearch(Node node,E target,Queue<Node> queue,StringBuilder sb){                  //General tree level order search

        //Kardeş nodelar queuya eklenir
        Node tempNode;
        if(node != null){
            tempNode = node;
            while (tempNode != null){
                if(!queue.contains(tempNode))
                    queue.add(tempNode);
                tempNode = tempNode.right;
            }
        }

        //Queudaki her nodeun çocuğu varsa yeni queuya eklenir
        Queue<Node> newQueue = new LinkedList();
        while(!queue.isEmpty()){
            tempNode = queue.peek();
            if(tempNode.left != null) {
                newQueue.add(tempNode.left);
                Node tempSibling = tempNode.left.right;
                while (tempSibling != null) {
                    newQueue.add(tempSibling);
                    tempSibling = tempSibling.right;
                }
            }
            // Çocukları yeni queuya eklenen nodelar eski queudan çıkarılır ve aranan node ile karşılaştırılır
            Node targetNode = queue.poll();
            sb.append(targetNode.toString() + " ");
            if(targetNode.data.equals(target))
                return targetNode;
        }
        //Son kalınan node üzerinden aynı işlemler tekrarlanmak üzere recurive call yapılır
        tempNode = newQueue.peek();
        if(queue.isEmpty() && tempNode != null)
            return levelOrderSearch(tempNode,target,newQueue,sb);
        return null;
    }

    /**
     * Elemanı tree içinde post order olarak arar ve gezilen nodeları string buildera ekler
     * @param target aranan eleman
     * @param sb Gezilen nodeların eklendiği string
     * @return Aranan elemanın nodeu return edilir yoksa null return edilir.
     */
    public Node postOrderSearch(E target,StringBuilder sb){
        if(root == null || root.data.equals(target))
            return root;
        Queue<Node> queue = new LinkedList<>();
        return postOrderSearch(root.left,target,queue,sb);
    }

    /**
     * Elemanın recursive olarak post order aranması
     * @param node üzerinde gezilmeye başlanan node
     * @param target aranan eleman
     * @param queue Kardeş nodeların tutulduğu queue
     * @param sb   Gezilen nodeların eklendiği string
     * @return  Aranan elemanın nodeu return edilir yoksa null return edilir.
     */
    private Node postOrderSearch (Node node,E target,Queue<Node> queue,StringBuilder sb){

        Node tempNode;
        Node sendNode = null;
        Stack<Node> stack = new Stack<>();
        if(node != null){
            tempNode = node;
            while (tempNode != null){ // Nodun  çocukları varsa önce stacke eklenir
                Node childNode = tempNode;
                while (childNode.left != null) {
                    if(!queue.contains(childNode.left))
                        stack.add(childNode.left);
                    childNode = childNode.left;
                }
                while (!stack.isEmpty()) { // Çocuklar stackten sondan alınarak queuya eklenir
                    Node siblingNode = stack.pop();
                    checkAndAdd(siblingNode,queue);
                    addSiblings(queue,siblingNode);
                }
                checkAndAdd(tempNode,queue);  // Son olarak tüm bu çocukların parentı queuya eklenir
                if(tempNode != null)
                    sendNode = tempNode;
                tempNode = tempNode.right;
            }
        }

        //Son kalınan node ile recursive call yapılır
        if(sendNode.left != null)
            return postOrderSearch(sendNode.left,target,queue,sb);

        //Aranan node tüm nodelar ile karşılaştırılır
        queue.add(root);
        for(Node result : queue) {
            sb.append(result+" ");
            if (result.data.equals(target))
                return result;
        }
        return null;
    }

    /**
     * Node queue nin içinde yoksa ekler
     * @param node queue içinde varlığı kontrol edilecek node
     * @param queue  kardeş nodeların tutulduğu queue
     */
    private void checkAndAdd(Node node,Queue<Node> queue){
        if(!queue.contains(node))
            queue.add(node);
    }

    /**
     * Kardeş nodeları queue ya ekler
     * @param queue kardeş nodeların tutulduğu queue
     * @param node  kardeşleri queue ya eklenecekn node
     */
    private void addSiblings(Queue<Node> queue,Node node){
        Node tempNode = node;
        while(tempNode != null) {
            if (tempNode.right != null && !queue.contains(tempNode.right)) {
                if(tempNode.right.left != null)
                    queue.add(tempNode.right.left);
                queue.add(tempNode.right);
            }
            tempNode = tempNode.right;
        }
    }


    /**
     * Tree nin üzerinde preorder gezinerek general formdaki halini string buildera ekler
      * @param node üzerinde gezilecek node
     *  @param depth her node için bırakılacak intendi belirler
     * @param sb Gezilen nodeların eklendiği string
     */
    private void preOrderTraverse(Node<E> node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth, sb); // Siblinglerin General tree ye göre aynı hizada olmasını sağlamak için sadece depth yollanır.
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root,1,sb);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        GeneralTree<E> tree = (GeneralTree<E>) obj;
        return tree.toString().equals(this.toString());
    }
}
