package neural_network;

import java.util.List;

import matrix.Matrix;

public class NeuralNetwork {
	Matrix weights_input_hidden, weights_hidden_output, bias_hidden, bias_output;    
    double learning_rate = 0.01;
    
    public NeuralNetwork(int i, int h, int o) {
    	weights_input_hidden = new Matrix(h, i);
    	weights_hidden_output = new Matrix(o, h);
        
    	bias_hidden = new Matrix(h, 1);
    	bias_output = new Matrix(o, 1);   
    }
    
    public List<Double> predict(double[] X) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_input_hidden, input);
        hidden.add(bias_hidden);
        hidden.sigmoid();
        
        Matrix output = Matrix.multiply(weights_hidden_output, hidden);
        output.add(bias_output);
        output.sigmoid();
        
        return output.toArray();
    }
    
    public void train(double [] X, double [] Y) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_input_hidden, input);
        hidden.add(bias_hidden);
        hidden.sigmoid();
        
        Matrix output = Matrix.multiply(weights_hidden_output, hidden);
        output.add(bias_output);
        output.sigmoid();
        
        Matrix target = Matrix.fromArray(Y);
        
        Matrix error = Matrix.subtract(target, output);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(learning_rate);
        
        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix who_delta =  Matrix.multiply(gradient, hidden_T);
        
        weights_hidden_output.add(who_delta);
        bias_output.add(gradient);
        
        Matrix who_T = Matrix.transpose(weights_hidden_output);
        Matrix hidden_errors = Matrix.multiply(who_T, error);
        
        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(learning_rate);
        
        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);
        
        weights_input_hidden.add(wih_delta);
        bias_hidden.add(h_gradient);
    }
    
    public void fit(double[][]X, double[][]Y, int epochs) {
        for(int i = 0; i < epochs; i++) {    
            int sampleN = (int) (Math.random() * X.length);
            this.train(X[sampleN], Y[sampleN]);
        }
    }
}
