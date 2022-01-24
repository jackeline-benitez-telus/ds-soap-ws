package org.jugvale.ola.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class CalculadoraWS {

    @WebMethod
	public double fazerOp(@WebParam(name = "num1") double a
						, @WebParam(name = "num2") double b
						, @WebParam(name = "op") String op) {
		switch (op) {
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			return a / b;
		default:
			throw new IllegalArgumentException("Operação '" + op
					+ "' não reconhecida. Informa '+', '-', '*' ou '/'.");
		}
	}

	@WebMethod
	public String sayHello(String name){
    	return "Say Hello to " + name;
	}
	
	@WebMethod
	public String HexTo(String hex){
		String newHex=hex.substring(1,7);
		
		/*TO RGB*/
		double r,g,b;		
		r = Integer.valueOf(newHex.substring(0,2),16);
		g = Integer.valueOf(newHex.substring(2,4),16);
		b = Integer.valueOf(newHex.substring(4,6),16);
		
		/*TO CMYK*/
		double k,c,m,y,max,r2,g2,b2;
		r2=(r/255);
		g2=(g/255);
		b2=(b/255);
		max=Math.max(Math.max(r2,g2),b2);
		
		k=1-max;
		c= (1-r2-k)/(1-k);
		m=(1-g2-k)/(1-k);
		y=(1-b2-k)/(1-k);
		
    	return "\nHEX:" + hex 
    			+"\nRGB: "+r+","+g+","+b
    			+"\nCMYK: "+Math.round(c*100)+"%, "+Math.round(m*100)+"%, "+Math.round(y*100)+"%, "+Math.round(k*100)+"%";
	}
}
