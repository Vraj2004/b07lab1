public class Polynomial{
	//Part i: Creating a double array named coeff
	public double[] coeff;
	
	//Part ii: Creating the no argument constructor
	//Sets the polynomial to zero 
	public Polynomial() {
		coeff = new double[1];
		coeff[0] = 0;
	}
	//Part iii: Creating constructor to set coefficients accordingly
	public Polynomial(double[] set_array) {
		this.coeff = set_array;
	}
	//Part iv: Creating a method add
	public Polynomial add(Polynomial p) {
		Polynomial q = new Polynomial();
		int p_length = p.coeff.length;
				
		if (p_length > this.coeff.length) {
			q.coeff = p.coeff;
			for(int x = 0; x < this.coeff.length; x++) {
						q.coeff[x] += this.coeff[x];
					}
		} else {
			q.coeff = this.coeff;
			for(int y = 0; y < p_length; y++) {
				q.coeff[y] += p.coeff[y];
			}
		}
		
		return q;
	}
	//Part v: Creating a method evaluate
	public double evaluate(double val) {
		double result = 0;
		for(int z = 0; z < this.coeff.length; z++) {
			result += this.coeff[z] * (Math.pow(val, z));
		}
		return result;
	}
	//Part vi: Creating a method called hasRoot
	public boolean hasRoot(double val) {
		double result = this.evaluate(val);
		if(result == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
}