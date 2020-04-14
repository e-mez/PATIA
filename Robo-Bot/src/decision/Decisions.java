package decision;

import java.util.ArrayList;

import arena.Pallet;

public class Decisions {
	public static Pallet choosePallet(int x, int y, ArrayList<Pallet> pallets) {
		Pallet chosenPallet = null;
		
		int n = pallets.size();
		double dist = Double.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			Pallet p = pallets.get(i);
			
			if (p.getX() != 0 && p.getY() != 0) {
				int xDiff = p.getX() - x, yDiff = p.getY() - y;
				double xDiffSquared = Math.pow(xDiff, 2), yDiffSquared = Math.pow(yDiff, 2);
				
				double currDist = Math.sqrt(xDiffSquared + yDiffSquared);
				System.out.println(p.toString() + " " + currDist);
				if (currDist < dist) {
					dist = currDist;
					chosenPallet = p;
				}
			}
			
		}		
		
		return chosenPallet;		
	}
}
