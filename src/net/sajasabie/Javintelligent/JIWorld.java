package net.sajasabie.Javintelligent;

import java.util.Random;



public class JIWorld {
	
	char map[][] = new char[100][100];
	double currentPos[] = new double[2];
	double endPos[] = new double[2];
	int turns = 0;
	int round = 0;
	Random theSeed = new Random();
	JIObject theBot;
	
	public JIWorld() {
		Random theSeed = new Random();
		genWorld(theSeed.nextInt());
		theBot = new JIObject();
	}
	
	public JIWorld(int seed) {
	
	}
	
	public JIWorld(char Fx, char Fy, char Sx, char Sy) {

	}
	
	
	
	
	private void genWorld(int seed) {
		Random theGen = new Random(seed);
		System.out.println(seed);
		//else Random theGen = new Random(seed);
		
		currentPos[0] = theGen.nextDouble();
		currentPos[1] = theGen.nextDouble();
		
		map[(int)(currentPos[0]*100)][(int)(currentPos[1]*100)] = '+';
		
		endPos[0] = theGen.nextDouble();
		endPos[1] = theGen.nextDouble();
		
		map[(int)(endPos[0]*100)][(int)(endPos[1]*100)] = '*';
		
	}
	
	public void onStep() {
		theBot.update();
		theBot.change = move(theBot);
	}
	
	public double move(JIObject theBot) {
		double Mx = theBot.dP[0]; 
		double My = theBot.dP[1];
		System.out.println(Mx + " " + My);
		
		
		if(Mx*Mx < .01*.01 || My*My < .01*.01 || Mx*Mx > 1.0 || My*My > 1.0) {
			theBot.error = JIErrors.NONE;
			return 2.0;
		}
		double oldDist = Math.sqrt((currentPos[0]-endPos[0])*(currentPos[0]-endPos[0]) + (currentPos[1]-endPos[1])*(currentPos[0]-endPos[0]));
		
		
		map[(int)(currentPos[0]*100)][(int)(currentPos[1]*100)] = 0;
		currentPos[0] += Mx;
		currentPos[1] += My;
		if(currentPos[0] > 1.0 || currentPos[0] < 0.0 || currentPos[1] > 1.0 || currentPos[1] < 0.0) {
			currentPos[0] -= Mx;
			currentPos[1] -= My;
			map[(int)(currentPos[0]*100)][(int)(currentPos[1]*100)] = '+';
			return 3.0;
		}
		
		map[(int)(currentPos[0]*100)][(int)(currentPos[1]*100)] = '+';
		
		
		return oldDist - Math.sqrt((currentPos[0]-endPos[0])*(currentPos[0]-endPos[0]) + (currentPos[1]-endPos[1])*(currentPos[0]-endPos[0]));
	}
	
	public String toString() {
		String toOut = "";
		for(int i = 0; i < 100; i++) {
			for(int j = 0;j<100;j++) {
				if(map[i][j] != 0)toOut += map[i][j];
				else toOut += '-';
			}
			toOut += "\r";
		}
		toOut += "Current position:\r\t";
		toOut += currentPos[0];
		toOut += "\r\t";
		toOut += currentPos[1];
		toOut += "\rGoal:\r\t";
		toOut += endPos[0];
		toOut += "\r\t";
		toOut += endPos[1];
		return toOut;
	}

}

