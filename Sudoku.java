public class Sudoku {

    public static void main(String[] args) {
        int[][] sudoku =  {
                {0, 0, 0, 6, 0, 9, 4, 0, 2},
                {1, 4, 0, 8, 0, 7, 3, 9, 0},
                {5, 0, 0, 0, 0, 0, 8, 0, 0},
                {9, 0, 7, 0, 3, 0, 5, 2, 0},
                {6, 0, 0, 4, 0, 5, 0, 7, 0},
                {0, 0, 0, 0, 9, 2, 6, 0, 0},
                {0, 3, 0, 0, 0, 0, 2, 6, 0},
                {0, 0, 6, 5, 0, 0, 9, 8, 3},
                {0, 0, 0, 0, 6, 0, 0, 0, 0}
        };

        Sudoku plantilla = new Sudoku(sudoku);
        plantilla.resultadoSudoku();
    }



    private int[][] tablero;
    public Sudoku(int[][] tablero) {
        this.tablero = tablero;
    }

    public void resultadoSudoku() {
        if (solucionadorSudoku(0, 0, 1)) {
            recorrerMaztriz(tablero, 0, 0);
        } else {
            System.out.println("No hay solución para este sudoku.");
        }
    }


    private boolean validacionFila(int fila, int numero, int indice) {
        if(indice==9){
            return false;
        }
        if(tablero[fila][indice] == numero){
            return true;
        }
        return validacionFila(fila,numero,indice+1);
    }

    private boolean validacionColumna(int columna, int numero, int indice) {
        if(indice==9){
            return false;
        }
        if(tablero[indice][columna] == numero){
            return true;
        }
        return validacionColumna(columna, numero, indice+1);
    }

    private boolean validacionBloque(int fila, int columna, int numero) {
        int filaLocal = fila - fila % 3;
        int columnalocal = columna - columna % 3;
        for (int i = filaLocal; i < filaLocal + 3; i++) {
            for (int j = columnalocal; j < columnalocal + 3; j++) {
                if (tablero[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int fila, int columna, int numero) {
        return !(validacionFila(fila, numero,0) || validacionColumna(columna, numero,0) || validacionBloque(fila, columna, numero));
    }

    private boolean solucionadorSudoku(int fila, int columna, int numero) {
        if (fila == 9) {
            return true;
        }
        //2
        if (columna == 9) {
            return solucionadorSudoku(fila + 1, 0, numero);
        }
        //3
        if (tablero[fila][columna] != 0) {
            return solucionadorSudoku(fila, columna + 1, numero);
        }
        if (numero <= 9) {
            //4
            if (isValid(fila, columna, numero)) {
                tablero[fila][columna] = numero;
                if (solucionadorSudoku(fila, columna + 1, 1)) {
                    return true;
                } else {
                    tablero[fila][columna] = 0;
                    return solucionadorSudoku(fila, columna, numero + 1);
                }
            } else {
                return solucionadorSudoku(fila, columna, numero + 1);
            }
        }
        return false;
    }


    private static void recorrerMaztriz(int[][] solver, int i, int j) {
        if (i <= solver.length - 1) {
            if (j <= solver[i].length - 1) {
                System.out.print(solver[i][j]);
                if (j == solver[i].length - 1) {
                    j = 0;
                    i++;
                    System.out.println();
                } else {
                    j++;
                }
                recorrerMaztriz(solver, i, j);
            }
        }
    }
}


