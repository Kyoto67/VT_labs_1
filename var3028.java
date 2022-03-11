
public class var3028 {
	public static void main (String[] args) {
		int[] a = new int[9];
		for (int i = 0; i < 9; i++) {
			a[9-1-i] = 5+i*2 ;
		}
		float[] x = new float[15];
		for (int i = 0; i < 15; i++) {
			double y = (-9) + Math.random() * 13 ;
			float f = (float)y ;
			x[i] = f;
		}
		double[][] answer = new double [9][15];
		for (int i = 0; i<9; i++) {
			System.out.println();
			for (int j=0; j<15; j++) {
				switch (a[i]) {
				case 17:
					answer[i][j] = Math.atan(1/(Math.exp(Math.acos(1/Math.exp(Math.abs(x[j]))))));
					System.out.printf("%.4f",answer[i][j]);
					System.out.print("	");
					break;
				case 7: case 9: case 15: case 21:
					answer[i][j] = Math.log(Math.exp(Math.pow(Math.cos(x[j]), 1.0/3)));
					System.out.println(Math.cos(x[j]));
					System.out.println(Math.pow(Math.cos(x[j]), 1.0/3));
					System.out.println(Math.exp(Math.pow(Math.cos(x[j]), 1.0/3)));
					System.out.println(Math.log(Math.exp(Math.pow(Math.cos(x[j]), 1.0/3))));
					System.out.printf("%.4f",answer[i][j]);
					System.out.print("	");
					break;
				default:
					answer[i][j] = Math.sin(Math.pow((x[j]/(x[j]+0.25)), 2.0/3)) * 0.25;
					System.out.printf("%.4f",answer[i][j]);
					System.out.print("	");
					break;
				}
			}
		}		
	}
}