public class Polynomial{
	public double[] coeff;
	
	public Polynomial() {
		coeff = new double[1];
		coeff[0] = 0;
	}
	
	public Polynomial(double[] set_array) {
		this.coeff = set_array;
	}
	
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
	
	public double evaluate(double val) {
		double result = 0;
		for(int z = 0; z < this.coeff.length; z++) {
			result += this.coeff[z] * (Math.pow(val, z));
		}
		return result;
	}
	
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