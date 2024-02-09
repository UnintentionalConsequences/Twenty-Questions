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
			overallRoot = add(scanner, overallRoot);
		}
	}
	
	public QuestionNode add(Scanner scanner, QuestionNode root) {
		if(root == null) {
			scanner.nextLine();
			root = new QuestionNode(scanner.nextLine());
			add(scanner, root);
		}
		else {
			String temp = scanner.nextLine();
			if(temp.charAt(0)=='Q') {
				if(root.left==null) {
					root.left = new QuestionNode(scanner.nextLine());
					add(scanner, root.left);
				}
			}
			else {
				if(root.left == null) {
					root.left = new QuestionNode(scanner.nextLine());
				}
			}
			temp = scanner.nextLine();
			if(temp.charAt(0)=='Q') {
				if(root.right==null) {
					root.right = new QuestionNode(scanner.nextLine());
					add(scanner, root.right);
				}
			}
			else {
				if(root.right==null) {
					root.right = new QuestionNode(scanner.nextLine());
				}
			}
		}
		return root;
	}
	
	public void saveQuestions(PrintStream output) {
		QuestionNode root = overallRoot;
		if(root!=null) {
			output.println(preOrder(root));
		}
	}
	
	public String preOrder(QuestionNode root) {
		
		if(root!=null&& root.left==null&& root.right==null) {//leaves
			return "A:\n"+root.data;
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
		Scanner scanner = new Scanner(System.in);
		QuestionNode node = overallRoot;
		QuestionNode prev = overallRoot;
		boolean left;
		System.out.println(node.data+" (y/n)?");
		String ans = scanner.nextLine().trim().toLowerCase();
		if(ans.startsWith("y")) {
			node = node.left;
			left = true;
		}
		else {
			node = node.right;
			left = false;
		}
		while(node.left!=null&&node.right!=null) {
			if(left)
				prev = prev.left;
			else
				prev = prev.right;
			System.out.println(node.data+" (y/n)?");
			ans = scanner.nextLine().trim().toLowerCase();
			if(ans.startsWith("y")) {
				node = node.left;
				left = true;
			}
			else {
				node = node.right;
				left = false;
			}
		}
		String first = node.data;
		System.out.println("I guess that your object is "+first+"!");
		System.out.println("Am I right? (y/n)?");
		String input = scanner.nextLine().toLowerCase().trim();
		if(input.startsWith("y")) {
			System.out.println("Awesome! I win!");
		}
		else {
			System.out.println("Boo! I lose. Please Help me get better!");
			System.out.println("What is your object?");
			input = scanner.nextLine();
			System.out.println("Please give me a yes/no question that distinguishes between "+first+" and "+input);
			String newQues = scanner.nextLine();
			System.out.println("Is the answer \"yes\" for "+input+"? (y/n)? ");
			String quest = scanner.nextLine();
			QuestionNode temp = new QuestionNode(node.data);
			if(prev.left == node)
				prev.left = quest.toLowerCase().trim().startsWith("y")?new QuestionNode(newQues, new QuestionNode(input), new QuestionNode(first)):new QuestionNode(newQues, new QuestionNode(first), new QuestionNode(input));
			else
				prev.right = quest.toLowerCase().trim().startsWith("y")?new QuestionNode(newQues, new QuestionNode(first), new QuestionNode(input)):new QuestionNode(newQues, new QuestionNode(input), new QuestionNode(first));

		}
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
