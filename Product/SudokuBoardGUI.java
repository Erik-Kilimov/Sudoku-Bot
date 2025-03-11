import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public abstract class SudokuBoardGUI extends JFrame implements ActionListener {
    private JTextField[][] board;
    private static final int boardSize = 9;

    public SudokuBoardGUI() {
        super("Sudoku Board");

        board = new JTextField[boardSize][boardSize];
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new JTextField();
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                boardPanel.add(board[i][j]);
                // alternating colors to separate each 3 by 3 square
                int k = i / 3;
                int l = j / 3;
                if ((k + l) % 2 == 0) {
                    board[i][j].setBackground(Color.ORANGE);
                } else {
                    board[i][j].setBackground(Color.GREEN);
                }
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> solveSudoku());

        JButton newButton = new JButton("New");
        newButton.addActionListener(e -> clearBoard());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(newButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> loadFile());
        buttonPanel.add(loadButton);

        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(e -> displayHint());
        buttonPanel.add(hintButton);
    }

    private void resetBoard() {

    }

    private void solveSudoku() {
        Sudoku s = new Sudoku();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                String value = board[i][j].getText();
                if (!value.isEmpty()) {
                    try {
                        s.grid[i][j] = Integer.parseInt(value);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    s.grid[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                String value = board[i][j].getText();
                if (!value.isEmpty()) {
                    try {
                        int num = Integer.parseInt(value);
                        // check for duplicates in the same row
                        for (int k = 0; k < boardSize; k++) {
                            if (k != j && board[i][k].getText().equals(value)) {
                                JOptionPane.showMessageDialog(this, "Duplicate value in the same row.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        // check for duplicates in the same column
                        for (int k = 0; k < boardSize; k++) {
                            if (k != i && board[k][j].getText().equals(value)) {
                                JOptionPane.showMessageDialog(this, "Duplicate value in the same column.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        // check for duplicates in the same 3 by 3 square
                        int rowOffset = (i / 3) * 3;
                        int colOffset = (j / 3) * 3;
                        for (int k = rowOffset; k < rowOffset + 3; k++) {
                            for (int l = colOffset; l < colOffset + 3; l++) {
                                if ((k != i || l != j) && board[k][l].getText().equals(value)) {
                                    JOptionPane.showMessageDialog(this, "Duplicate value in the same 3 by 3 square.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }
                        }
                        s.grid[i][j] = num;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    s.grid[i][j] = 0;
                }
            }
        }
        boolean solved = Solver.solve(s);

        if (solved) {
            LoadBoard(s);
        }
    }
    private void displayHint() {
        Sudoku s = new Sudoku();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                String value = board[i][j].getText();
                if (!value.isEmpty()) {
                    try {
                        s.grid[i][j] = Integer.parseInt(value);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    s.grid[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                String value = board[i][j].getText();
                if (!value.isEmpty()) {
                    try {
                        int num = Integer.parseInt(value);
                        // check for duplicates in the same row
                        for (int k = 0; k < boardSize; k++) {
                            if (k != j && board[i][k].getText().equals(value)) {
                                JOptionPane.showMessageDialog(this, "Duplicate value in the same row.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        // check for duplicates in the same column
                        for (int k = 0; k < boardSize; k++) {
                            if (k != i && board[k][j].getText().equals(value)) {
                                JOptionPane.showMessageDialog(this, "Duplicate value in the same column.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        // check for duplicates in the same 3 by 3 square
                        int rowOffset = (i / 3) * 3;
                        int colOffset = (j / 3) * 3;
                        for (int k = rowOffset; k < rowOffset + 3; k++) {
                            for (int l = colOffset; l < colOffset + 3; l++) {
                                if ((k != i || l != j) && board[k][l].getText().equals(value)) {
                                    JOptionPane.showMessageDialog(this, "Duplicate value in the same 3 by 3 square.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }
                        }
                        s.grid[i][j] = num;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    s.grid[i][j] = 0;
                }
            }
        }
        boolean solved = Solver.solve(s);
        Random rand = new Random();
        int i = rand.nextInt(9);
        int j = rand.nextInt(9);
        String value = board[i][j].getText();
        board[i][j].setText(String.valueOf(s.grid[i][j]));
        board[i][j].setBackground(Color.RED);
        if (!value.isEmpty()) {
            try {
                s.grid[i][j] = Integer.parseInt(value);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            s.grid[i][j] = 0;
        }
    }
    private void clearBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j].setText("");
                int k = i / 3;
                int l = j / 3;
                if ((k + l) % 2 == 0) {
                    board[i][j].setBackground(Color.ORANGE);
                } else {
                    board[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    public void LoadBoard(Sudoku s) {
        // update the board with values
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (s.grid[i][j] != 0) {
                    board[i][j].setText(String.valueOf(s.grid[i][j]));
                }
            }
        }
    }
    private void loadFile() {
        JFileChooser fileReader = new JFileChooser();
        int result = fileReader.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileReader.getSelectedFile();
            try (Scanner scanner = new Scanner(file)) {
                int[][] grid = new int[boardSize][boardSize];
                int row = 0;
                while (scanner.hasNextLine() && row < boardSize) {
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    for (int column = 0; column < boardSize; column++) {
                        grid[row][column] = Integer.parseInt(values[column]);
                    }
                    row++;
                }
                Sudoku s = new Sudoku();
                s.grid = grid;
                LoadBoard(s);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid file format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}