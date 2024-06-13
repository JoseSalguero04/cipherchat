package pp.GUI;

public class Codificado {
    private static final int ALPHABET_SIZE = 27;
    private static final int SPANISH_ALPHABET_SIZE = 30;
    private static final int OFFSET = 65; // ASCII value for 'A'
    private static final int SPACE_VALUE = 27;
    int[][] matrizC;
    int[][] matrizM2;

    private static final char[] SPANISH_ALPHABET = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '
    };

    public static int getCharValue(char c) {
        c = Character.toUpperCase(c);
        for (int i = 0; i < SPANISH_ALPHABET.length; i++) {
            if (SPANISH_ALPHABET[i] == c) {
                return i;
            }
        }
        return SPACE_VALUE; // Return space value for invalid characters
    }

    public static int[][] codificar(int[][] matrizA, int[][] matrizB, String message) {
        // Normalize the message
        message = message.toUpperCase().replaceAll("[ÁÀÂÃÄ]", "A")
                                        .replaceAll("[ÉÈÊË]", "E")
                                        .replaceAll("[ÍÌÎÏ]", "I")
                                        .replaceAll("[ÓÒÔÕÖ]", "O")
                                        .replaceAll("[ÚÙÛÜ]", "U")
                                        .replaceAll("[^A-ZÑ ]", " ");

        int rows = 3;
        int cols = (int) Math.ceil((double) message.length() / rows);
        int[][] matrizM = new int[rows][cols];

        // Fill matrizM with the character values
        int index = 0;
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (index < message.length()) {
                    matrizM[i][j] = getCharValue(message.charAt(index++));
                } else {
                    matrizM[i][j] = SPACE_VALUE; // Fill with spaces if the message is short
                }
            }
        }

        // Debug: Print matrizM
        System.out.println("Matriz M:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrizM[i][j] + " ");
            }
            System.out.println();
        }

        // Perform the encryption A*M + B = C
        int[][] matrizC = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrizC[i][j] = matrizB[i][j];
                for (int k = 0; k < rows; k++) {
                    matrizC[i][j] += matrizA[i][k] * matrizM[k][j];
                }
            }
        }

        printMatrizC(matrizC, rows, cols);
        return matrizC;
    }

    public static int[][] decodificar(int[][] matrizA, int[][] matrizB, int[][] matrizC) {
        int rows = matrizC.length;
        int cols = matrizC[0].length;
        int[][] matrizM2 = new int[rows][cols];
    
        // Perform the decryption A^-1 * (C - B) = M
        double[][] matrizAInverse = invertMatrizA(matrizA);
        int[][] matrizCB = new int[rows][cols]; // C - B
    
        // Subtract B from C
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrizCB[i][j] = matrizC[i][j] - matrizB[i][j];
            }
        }
    
        // Multiply A^-1 with (C - B)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double temp = 0;
                for (int k = 0; k < rows; k++) {
                    temp += matrizAInverse[i][k] * matrizCB[k][j];
                }
                matrizM2[i][j] = (int) Math.round(temp);
            }
        }
    
        // Debug: Print matrizM
        System.out.println("Matriz M:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrizM2[i][j] + " ");
            }
            System.out.println();
        }
        return matrizM2;
    }

    public static double[][] invertMatrizA(int[][] matrizA) {
        int n = matrizA.length;
        double[][] inverse = new double[n][n];
    
        // Create an augmented matrix [A|I]
        double[][] augmented = new double[n][2*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmented[i][j] = matrizA[i][j];
                augmented[i][j+n] = (i == j) ? 1 : 0;
            }
        }
    
        // Perform row operations
        for (int i = 0; i < n; i++) {
            // Find the row with the largest value in column i
            int maxRow = i;
            for (int j = i+1; j < n; j++) {
                if (Math.abs(augmented[j][i]) > Math.abs(augmented[maxRow][i])) {
                    maxRow = j;
                }
            }
    
            // Swap rows i and maxRow
            double[] temp = augmented[i];
            augmented[i] = augmented[maxRow];
            augmented[maxRow] = temp;
    
            // Normalize row i
            double aii = augmented[i][i];
            for (int j = 0; j < 2*n; j++) {
                augmented[i][j] /= aii;
            }
    
            // Zero out the other rows
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double aji = augmented[j][i];
                    for (int k = 0; k < 2*n; k++) {
                        augmented[j][k] -= aji * augmented[i][k];
                    }
                }
            }
        }
    
        // Extract the inverse matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = augmented[i][j+n];
            }
        }
    
        return inverse;
    }

    public static String printDecodificado(int[][] matrizM2, int rows, int cols) {
        StringBuilder message = new StringBuilder();
    
        // Convert each number in matrizM2 back to its corresponding character
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                int value = matrizM2[i][j];
                if (value >= 0 && value < SPANISH_ALPHABET_SIZE) {
                    message.append(SPANISH_ALPHABET[value]);
                } else {
                    message.append(' '); // Append a space for invalid values
                }
            }
        }
    
        // Print the message to the terminal
        System.out.println("Mensaje decodificado: " + message.toString());
    
        return message.toString();
    }


    public void setMatrizC(int[][] matrizC) {
        this.matrizC = matrizC;
    }

    public void setMatrizM2(int[][] matrizM2) {
        this.matrizM2 = matrizM2;
    }

    // After the matrizC is calculated
    public static void printMatrizC(int[][] matrizC, int rows, int cols){
        System.out.println("Matriz C:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrizC[i][j] + " ");
            }
            System.out.println(); // Print a new line after each row
        }
    }

}
