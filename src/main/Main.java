package main;

import java.util.List;

import neural_network.NeuralNetwork;

public class Main {

	static double [][] X = {
            {0,0},
            {1,0},
            {0,1},
            {1,1}
    };
	
	static double [][] Y = { // XOR output
            {0},
            {1},
            {1},
            {0}
    };

	public static void main(String[] args) {
		NeuralNetwork nn = new NeuralNetwork(2, 10, 1);
		nn.fit(X, Y, 500000);
		
		double [][] input = {{0,0},{0,1},{1,0},{1,1}};
		List<Double> output;
		
		for(double d[]:input) {
		    output = nn.predict(d);
		    System.out.println(output.toString());
		}
		// Output
		// [0.09822298990353093]
		// [0.8757877124658147]
		// [0.8621529792837699]
		// [0.16860984858200806]
	}
}
