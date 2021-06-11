	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;
	import java.util.Random;
	import java.util.Scanner;

	public class TicTacToe {
		
		static ArrayList<Integer> playerAllPlacedSymbols = new ArrayList<Integer>();
		static ArrayList<Integer> cpuAllPlacedSymbols = new ArrayList<Integer>();

		public static void main(String[] args) {	
			char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
					{'-', '+', '-', '+', '-'},
					{' ', '|', ' ', '|', ' '}, 
					{'-', '+', '-', '+', '-'}, 
					{' ', '|', ' ', '|', ' '}};
			
			printGameBoard(gameBoard);		
				
			while(true) {			
				Scanner scan = new Scanner(System.in);	//constructor	
				System.out.println("Enter your placement (1-9)");
				int playerInput = scan.nextInt(); //payers input
				
				// to prevent player from writing over existing symbols:
				while(playerAllPlacedSymbols.contains(playerInput) || cpuAllPlacedSymbols.contains(playerInput)) {
					System.out.println("No,no, no. Can not place there!");
					playerInput = scan.nextInt();
				}
				
				placePiece(gameBoard, playerInput, "player");
				
				
				printGameBoard(gameBoard);
				
				System.out.println(); // extra line 
				System.out.println(); // extra line 
				
				String result = checkWinner(); //check winner after player move
				if(result.length() >0) {
					System.out.println(result);
					break;
				}
				
				Random rand = new Random();
				int cpuInput = 1+rand.nextInt(9); //cpu's input
				// to prevent cpu from writing over existing symbols:
				while(playerAllPlacedSymbols.contains(cpuInput) || cpuAllPlacedSymbols.contains(cpuInput)) {
					cpuInput = 1+rand.nextInt(9);
				}
				placePiece(gameBoard, cpuInput, "cpu");
				
				
				printGameBoard(gameBoard);
				
				result = checkWinner(); //check winner after cpu move
				if(result.length() >0) {
					System.out.println(result);
					break;
				}
				
				
			}
		}
		
		public static void printGameBoard(char[][] gameBoard) { //printing an empty board
			for(char[] row : gameBoard) {
				for(char c : row) {
					System.out.print(c);
				}
				System.out.println();
				
			}
			
		}
		
		public static void placePiece(char[][] gameBoard, int posOnBoard, String user) {
			
			char symbol = ' ';
			
			if(user.equals("player")) {
				symbol = 'X';
				playerAllPlacedSymbols.add(posOnBoard);
			} else if(user.equals("cpu")) {
				symbol = 'O';
				cpuAllPlacedSymbols.add(posOnBoard);
			}
			
			switch(posOnBoard) {
				case 1:
					gameBoard[0][0] = symbol;
					break;
				case 2:
					gameBoard[0][2] = symbol;
					break;
				case 3:
					gameBoard[0][4] = symbol;
				  	break;
				case 4:
					gameBoard[2][0] = symbol;
					break;
				case 5:
					gameBoard[2][2] = symbol;
					break;
				case 6:
					gameBoard[2][4] = symbol;
					break;
				case 7:
					gameBoard[4][0] = symbol;
					break;
				case 8:
					gameBoard[4][2] = symbol;
					break;
				case 9:
					gameBoard[4][4] = symbol;
					break;    
				default:
					break;
			 
			} 
		}
		
		public static String checkWinner() { //lists of possible winning combinations
			
			List topRow = Arrays.asList(1, 2, 3);
			List midRow = Arrays.asList(4, 5, 6);
			List botRow = Arrays.asList(7, 8, 9);
			List leftCol = Arrays.asList(1, 4, 7);
			List midCol = Arrays.asList(2, 5, 8);
			List rightCol = Arrays.asList(3, 6, 9);
			List cross1 = Arrays.asList(1, 5, 9);
			List cross2 = Arrays.asList(7, 5, 3);
			
			List<List> winning = new ArrayList<List>();		
			winning.add(topRow);
			winning.add(midRow);
			winning.add(botRow);
			winning.add(leftCol);
			winning.add(midCol);
			winning.add(rightCol);
			winning.add(cross1);
			winning.add(cross2);

			for(List l : winning) {  //return result
				if(playerAllPlacedSymbols.containsAll(l)) {
					return "Winner Winner Chicken Dinner!";
				}else if(cpuAllPlacedSymbols.containsAll(l)) {
					return "CPU winns. FATALITY!";
				}
			}
			if(playerAllPlacedSymbols.size() + cpuAllPlacedSymbols.size() == 9) { //return draw result
				return "It's a draw";
			}else {
				return ""; 
			}
			
		}

	}


