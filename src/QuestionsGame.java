import java.io.PrintStream;
import java.util.Scanner;

// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

public class QuestionsGame {
	
	QuestionNode overallRoot;
	
	public QuestionsGame(String object) {
		overallRoot = new QuestionNode(object); 
	}
	
	public QuestionsGame(Scanner scanner) {
		while(scanner.hasNextLine()) {
			add(scanner, overallRoot);
		}
	}
	
	public void add(Scanner scanner, QuestionNode root) {
		if(scanner.hasNext()) {
			String qA = scanner.nextLine(); //find question or answer
			if(root == null) {
				root = new QuestionNode(scanner.nextLine());
				add(scanner, root);
			}
			if(root.left==null) {
				if (qA.equals("Q:")){
					root.left = new QuestionNode(scanner.nextLine());
					add(scanner, root.left);
				}
				else {
					root.left = new QuestionNode(scanner.nextLine());
					add(scanner, root);
				}
			}
			else if(root.right==null) {
				if (qA.equals("A:")){
					root.right = new QuestionNode(scanner.nextLine());
					add(scanner, root.left);
				}
				else {
					root.right = new QuestionNode(scanner.nextLine());
					add(scanner, root);
				}
			}
			/*else if((root.left.data.contains("?") || root.left == null) && qA.equals("Q:")) { //if left of root is nothing or not an answer  and we are putting in a question
				root.left = new QuestionNode(scanner.nextLine());
				add(scanner, root.left);
			}
			else if(root.left == null && qA.equals("A:")) { //if left of root is nothing and we are putting in an answer
				root.left = new QuestionNode(scanner.nextLine());
				add(scanner, root);
			}
			*/
			else if(root.left != null && qA.equals("Q:")) { //if left is full and we are putting in a question
				root.right = new QuestionNode(scanner.nextLine());
				add(scanner, root.right);
			}
			else if(root.left == null && qA.equals("A:")) { //if left of root is full and we are putting in an answer
				root.left = new QuestionNode(scanner.nextLine());
				add(scanner, root);
			}
			else if(!root.left.data.contains("?")  && !root.right.data.contains("?")) {	//if left and right are answers
				add(scanner, overallRoot.right);
			}
		}
	}
	
	/*public QuestionNode add(Scanner scanner, QuestionNode root) {
		if(root == null) {
			return new QuestionNode(scanner.nextLine()+scanner.nextLine());
		}
		String temp = scanner.nextLine()+scanner.nextLine();
		if(temp.charAt(0)=='Q')) {
			if(root.left!=null) {
				root.left = new QuestionNode(temp);
				add(scanner, root.left);
			}
			else {
				root.right = new QuestionNode(temp);
				add(scanner, root.right);
			}
		}
		return new QuestionNode(temp);
	}
	*/
	
	public void saveQuestions(PrintStream output) {
		QuestionNode root = overallRoot;
		if(root!=null) {
			output.println(preOrder(root));
		}
	}
	public String preOrder(QuestionNode root) {
		
		if(root!=null&& root.left==null&& root.right==null) {//leaves
			return "A:\n"+root.data+"\n";
		}
		if(root!=null&& root.left==null&& root.right!=null) {//root with right only
			return "Q:\n"+root.data+"\n"+preOrder(root.right);
		}
		if(root!=null&& root.left!=null&& root.right==null) {//root with left only
			return "Q:\n"+root.data+"\n"+preOrder(root.left);
		}
		if(root!=null && root.left!=null && root.right!=null) {//root with left and right
			return("Q:\n"+root.data+"\n"+preOrder(root.left)+"\n"+preOrder(root.right));
		}
		else
			return "";
	}
    
	public void play() {
		
	}

    public static class QuestionNode {
    	
        public final String data;
        public QuestionNode left;
        public QuestionNode right;
        
        public QuestionNode(String data) {
        	this(data, null, null);
        }
        
        public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        	this.data = data;
        	this.left = left;
        	this.right = right;
        }
    }
}
