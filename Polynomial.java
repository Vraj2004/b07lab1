import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class Polynomial {
	public double [] coeff;
	public int [] exponents;

	public Polynomial() {
		this.coeff = null ;
		this.exponents = null;
	}

	public Polynomial(double[] new_coeff, int[] new_exponents){
		this.coeff = new double[new_coeff.length];
        this.exponents = new int[new_exponents.length];
        for (int i = 0; i < new_coeff.length; i++) {
            this.coeff[i] = new_coeff[i];
            this.exponents[i] = new_exponents[i];
        }
        if (this.coeff.length == 0) {
            this.coeff = null;
            this.exponents = null;
		}
	}
	
	public Polynomial(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		reader.close();

		String[] terms;
		terms = line.split("(?=[+-])");

		double[] parsedCoefficients = new double[terms.length];
		int[] parsedExponents = new int[terms.length];

		String term;

		for (int i = 0; i < terms.length; i++) {
			term = terms[i];
			int caretIndex = term.indexOf('x');

			if (caretIndex != -1) 
			{
				parsedCoefficients[i] = Double.parseDouble(term.substring(0, caretIndex));
				parsedExponents[i] = Integer.parseInt(term.substring(caretIndex + 1));
			} 
			else
			{
				parsedCoefficients[i] = Double.parseDouble(term);
				parsedExponents[i] = 0;
			}
		}

		this.coeff = parsedCoefficients;
		this.exponents = parsedExponents;
	}
	public Polynomial add(Polynomial p) {
		{
			int total_len = highest_degree(p.exponents) + 1;
			if(this.coeff == null){
				return p;
			}
			if(p.coeff == null){
				return this;
			}
			double[] new_coeff = new double[total_len];
			int[] new_exponents = new int[total_len];
			int position;
	
			for (int i = 0; i < this.coeff.length; i++)
			{
				position=this.exponents[i];
				new_coeff[position] += this.coeff[i];
				new_exponents[position] = this.exponents[i];
			}
	
			for (int i = 0; i < p.coeff.length; i++) 
			{
				position=p.exponents[i];
				new_coeff[position] += p.coeff[i];
				new_exponents[position] = p.exponents[i];
			}
	
			return simplified(new_coeff, new_exponents, total_len);
		}
	}
	public Polynomial simplified(double [] coefficients, int [] exps, int length) {
		int total_len = 0;
		double [] simple_coeff = new double [length];
		int [] simple_exps = new int [length];
		int i;

		for(i = 0; i < length; i++) {
			if(coefficients[i] != 0) {
				simple_coeff[total_len] = coefficients[i];
				simple_exps[total_len] = exps[i];
				total_len++;

			}
		}
		simple_coeff = Arrays.copyOf(simple_coeff, total_len);
		simple_exps = Arrays.copyOf(simple_exps, total_len);
		return new Polynomial(simple_coeff, simple_exps);
 	}
 	public double evaluate(double val) {
 		double result = 0;
		if(this.coeff == null) {
			return 0;
		}
 		int i;

 		for (i = 0; i < this.coeff.length; i++) {
 			result += this.coeff[i] * (Math.pow(val, this.exponents[i]));
 		}
 		return result;
 	}
 	public boolean hasRoot(double val) {
 		double result = this.evaluate(val);
 		if(result == 0){
 			return true;
 		}
 		else {
 			return false;
 		}
 	}
 	public Polynomial multiply(Polynomial p) {
 		int total_len = highest_degree(p.exponents) + 1;

		double[] new_coeff = new double[total_len];
		int[] new_exponents = new int[total_len];

		for (int i = 0; i < this.exponents.length; i++) 
		{
			for (int j = 0; j < p.exponents.length; j++) 
			{
				int new_pow = this.exponents[i] + p.exponents[j];
				new_coeff[new_pow] += (this.coeff[i] * p.coeff[j]);
				new_exponents[new_pow] = new_pow;
			}
		}

		return simplified(new_coeff, new_exponents, total_len);
 	}

 	public int highest_degree(int[] exp) {
		int highestPow1 = 0;
		int highestPow2 = 0;
		for (int x = 0; x < this.exponents.length; x++)
		{
			if (this.exponents[x] > highestPow1)
			{
				highestPow1 = this.exponents[x];
			}
		}
		for (int x = 0; x < exp.length; x++)
		{
			if (exp[x] > highestPow2)
			{
				highestPow2 = exp[x];
			}
		}
		return highestPow1 + highestPow2;
	}
	public void saveToFile(String fileName) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		for (int i = 0; i < this.coeff.length; i++)
			{
				if (this.coeff[i] != 0) 
				{
					if (coeff[i] > 0 && i != 0) 
					{
						writer.write("+");
					}
					writer.write(Double.toString(this.coeff[i]));

					if(this.exponents[i] != 0)
					{
						writer.write("x");
						if(this.exponents[i] != 1)
						{
							writer.write(Integer.toString(this.exponents[i]));
						}
					}
				}
			}
			writer.close(); 
	}

 	
}