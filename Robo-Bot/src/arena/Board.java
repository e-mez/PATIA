package arena;

import decision.Decisions;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

public class Board {
	private Camera camera;
	private ArrayList<Pallet> pallets;
	
	public Board() throws SocketException {
		super();
		
		this.camera = new Camera();
		this.pallets = new ArrayList<Pallet>();
		
		for (int i = 0; i < 9; i++) {
			this.pallets.add(new Pallet(i));
		}		
		
	}
	
	public void updatePalletsCoordinates() throws IOException {
		pallets = camera.getPalletCoordinates(pallets);
	}
		
	public static void main(String args[]) throws IOException {
		Board board = new Board();
		board.updatePalletsCoordinates();
		System.out.println("board.pallets");
		for (int i = 0; i < board.pallets.size(); i++)
			System.out.println(board.pallets.get(i).toString());
		
		Pallet p = Decisions.choosePallet(25, 105, board.pallets);
		
		System.out.println("\n\n" + p.toString());
		
		
		/*
		Pallet [x=125, y=46, id=0]
				Pallet [x=61, y=15, id=1]
				Pallet [x=51, y=295, id=2]
				Pallet [x=129, y=301, id=3]
				Pallet [x=0, y=0, id=4]
				Pallet [x=0, y=0, id=5]
				Pallet [x=0, y=0, id=6]
				Pallet [x=0, y=0, id=7]
				Pallet [x=0, y=0, id=8]
*/
		
	}
	

}
