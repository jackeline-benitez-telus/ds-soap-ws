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
			throw new IllegalArgumentException("OperaÃ§Ã£o '" + op
					+ "' nÃ£o reconhecida. Informa '+', '-', '*' ou '/'.");
		}
	}

	@WebMethod
	public String sayHello(String name){
    	return "Say Hello to " + name;
	}
	
	@WebMethod
	public String HexTo(String hex){
		String newHex=hex.substring(1,7);
		
		/*---------------TO RGB---------------*/
		double r,g,b;		
		r = Integer.valueOf(newHex.substring(0,2),16);
		g = Integer.valueOf(newHex.substring(2,4),16);
		b = Integer.valueOf(newHex.substring(4,6),16);
		
		/*---------------TO CMYK---------------*/
		double k,c,m,y,max,r2,g2,b2;
		r2=(r/255);
		g2=(g/255);
		b2=(b/255);
		max=Math.max(Math.max(r2,g2),b2);
		
		k=1-max;
		c= (1-r2-k)/(1-k);
		m=(1-g2-k)/(1-k);
		y=(1-b2-k)/(1-k);
		
		/*---------------TO HSV---------------*/
		double min,dif,h,s,v;
		min=Math.min(Math.min(r2,g2),b2);
		dif=max-min;
		
		h=-1;
		if(dif==0) {
			h=0;
		}else if(max==r2) {
			if(g2>=b2) {
				h=(60*((g2-b2)/dif)+0) % 360;
			}else if(g2<b2) {
				h=(60*((g2-b2)/dif)+360) % 360;
			}
		}else if(max==g2) {
			h=(60*((b2-r2)/dif)+120) % 360;
		}else if(max==b2) {
			h=(60*((r2-g2)/dif)+240) % 360;
		}else {
			h=0;
		}
		
		
		if(max==0) {
			s=0;
		}else {
			s=(dif/max);
		}
		
		
		v=max;
		
		/*---------------TO HSL---------------*/
		double l,s2;
		
		l=(max+min)/2;
		
		if(dif==0) {
			s2=0;
		}else {
			s2=dif / (1 - Math.abs(2*l-1));
		}
		
		
    	return "\nHEX:" + hex 
    			+"\nRGB: "+r+","+g+","+b
    			+"\nCMYK: "+Math.round(c*100)+"%, "+Math.round(m*100)+"%, "+Math.round(y*100)+"%, "+Math.round(k*100)+"%"
    			+"\nHSV: "+ Math.round(h) +"º, "+ Math.round(s*100) +"%, "+ Math.round(v*100) +"%" 
    			+"\nHSl: "+ Math.round(h) +"º, "+ Math.round(s2*100) +"%, "+ Math.round(l*100) +"%" ;
	}
}
