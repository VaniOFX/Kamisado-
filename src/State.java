import java.util.Arrays;

public class State implements java.io.Serializable  {
	
	private Position currentInitial;
	private Position lastPieceOn;
	/**
	 * 
	 */
	private static final long serialVersionUID = 755892029592219797L;
	private Piece[][] pieces;
	
	public State(){
		
		initPieces();
	
	}
	
	public State(Piece[][] pieces, Move move){
		this.pieces = pieces;
		Position initial = move.getInitial();
		Position target = move.getTarget();
		lastPieceOn = target;
		pieces[target.getPosX()][target.getPosY()] = pieces[initial.getPosX()][initial.getPosY()];
		pieces[initial.getPosX()][initial.getPosY()] = null;
	}
	
	public State(Piece[][] pieces){
		this.pieces = pieces;
	}
	
	private void initPieces(){
		// Initialize pieces
		pieces = new Piece[8][8];
		pieces[0][0] = new Piece(Color.BLACK, Color.ORANGE);
		pieces[0][1] = new Piece(Color.BLACK, Color.BLUE);
		pieces[0][2] = new Piece(Color.BLACK, Color.PURPLE);
		pieces[0][3] = new Piece(Color.BLACK, Color.PINK);
		pieces[0][4] = new Piece(Color.BLACK, Color.YELLOW);
		pieces[0][5] = new Piece(Color.BLACK, Color.RED);
		pieces[0][6] = new Piece(Color.BLACK, Color.GREEN);
		pieces[0][7] = new Piece(Color.BLACK, Color.BROWN);
		
		pieces[7][0] = new Piece(Color.WHITE, Color.BROWN);
		pieces[7][1] = new Piece(Color.WHITE, Color.GREEN);
		pieces[7][2] = new Piece(Color.WHITE, Color.RED);
		pieces[7][3] = new Piece(Color.WHITE, Color.YELLOW);
		pieces[7][4] = new Piece(Color.WHITE, Color.PINK);
		pieces[7][5] = new Piece(Color.WHITE, Color.PURPLE);
		pieces[7][6] = new Piece(Color.WHITE, Color.BLUE);
		pieces[7][7] = new Piece(Color.WHITE, Color.ORANGE);
	}
	
	
	public State move(Move move){
		
		return new State(pieces,move);
	}

	
	public Piece getPiece(Position pos){
		return pieces[pos.getPosX()][pos.getPosY()];
	}
	
	public Position getPiecePosition(Color playerColor, Color pieceColor){
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j] != null){
					if(pieces[i][j].getColor() == pieceColor 
							&& pieces[i][j].getPlayerColor() == playerColor){
						return new Position(i, j);
					}
				}
			}
		}
		return null;
	}
	
	public void printState(){
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j]== null){
					System.out.print("|____|");
				}else{
					System.out.print("|"+ pieces[i][j].getPlayerColor().toString().charAt(0)+","+ pieces[i][j].getColor().toString().substring(0, 2)+"|");
				}
			}
			System.out.println();
		}
	}

	public Position getCurrentInitial() {
		return currentInitial;
	}

	public void setCurrentInitial(Position currentInitial) {
		this.currentInitial = currentInitial;
	}
	
	public Piece[][] getPieces(){
		Piece [][] piecesCopy = new Piece[8][];
		for(int i = 0; i < 8; i++)
		    piecesCopy[i] = pieces[i].clone();
		return piecesCopy;
	}

	public Position getLastPieceOn() {
		return lastPieceOn;
	}

	public void setLastPieceOn(Position lastPieceOn) {
		this.lastPieceOn = lastPieceOn;
	}

}


