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
		overallRoot = new QuestionNode(scanner.nextLine());
	}
	
	public void saveQuestions(PrintStream output) {
		QuestionNode root = overallRoot;
		while(root!=null) {
			output.println(preOrder(overallRoot));
		}
	}
	public String preOrder(QuestionNode root) {
		
		if(root!=null&& root.left==null&& root.right==null) {//leaves
			return root.data;
		}
		if(root!=null&& root.left==null&& root.right!=null) {//root with right only
			return root.data+"\n"+preOrder(root.right);
		}
		if(root!=null&& root.left!=null&& root.right==null) {//root with left only
			return root.data+"\n"+preOrder(root.left);
		}
		if(root!=null && root.left!=null && root.right!=null) {//root with left and right
			return(root.data+"\n"+preOrder(root.left)+"\n"+preOrder(root.right));
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
