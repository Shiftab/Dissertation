package sudoku;

import jade.lang.acl.ACLMessage;
import control.BasicStart;

public class test {

	/**
	 * sudoku sudoku
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(ACLMessage.ACCEPT_PROPOSAL);
		System.out.println(ACLMessage.AGREE);
		System.out.println(ACLMessage.CFP);
		System.out.println(ACLMessage.CANCEL);
		System.out.println(ACLMessage.CONFIRM);
		System.out.println(ACLMessage.DISCONFIRM);
		System.out.println(ACLMessage.FAILURE);
		System.out.println(ACLMessage.INFORM);
		System.out.println(ACLMessage.INFORM_IF);
		System.out.println(ACLMessage.INFORM_REF);
		System.out.println(ACLMessage.NOT_UNDERSTOOD);
		System.out.println(ACLMessage.PROPAGATE);
		System.out.println(ACLMessage.PROPOSE);
		System.out.println(ACLMessage.PROXY);
		System.out.println(ACLMessage.QUERY_IF);
		System.out.println(ACLMessage.QUERY_REF);
		System.out.println(ACLMessage.REFUSE);
		System.out.println(ACLMessage.REJECT_PROPOSAL);
		System.out.println(ACLMessage.REQUEST);
		System.out.println(ACLMessage.REQUEST_WHEN);
		System.out.println(ACLMessage.REQUEST_WHENEVER);
		System.out.println(ACLMessage.SUBSCRIBE);
		System.out.println(ACLMessage.UNKNOWN);
		/*new BasicStart();
		int[][] problem = { { 6, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 9, 5, 6, 0, 3, 7, 0, 0 }, { 4, 1, 0, 0, 0, 0, 0, 6, 3 },
				{ 0, 0, 9, 4, 7, 0, 5, 8, 0 }, { 0, 7, 0, 0, 9, 0, 0, 1, 0 },
				{ 0, 4, 1, 0, 6, 8, 3, 0, 0 }, { 9, 5, 0, 0, 0, 0, 0, 3, 8 },
				{ 0, 0, 2, 9, 0, 6, 4, 5, 0 }, { 0, 0, 0, 0, 0, 2, 0, 0, 0 } };

		// Sudoku.solveSudoku(problem);
		Coordinate ans = null;
		Sudoku s = new Sudoku(problem);
		do {
			ans = s.nextNumber(null);
			if (ans != null) {
				problem[ans.getX()][ans.getY()] = ans.getVal();
				System.out.println("\n X:" + ans.getX() + " Y:" + ans.getY()
						+ " val:" + ans.getVal() + "\n\n");
				s.refresh(problem);
			} else {
				s.print();
				break;
			}
		} while (ans != null);
*/
	}

}
