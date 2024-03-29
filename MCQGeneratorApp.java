package harish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class MCQGeneratorApp extends JFrame {

    public static class MCQ {
        private String question;
        private List<String> options;
        private String correctAnswer;

        public MCQ(String question, List<String> options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public List<String> getOptions() {
            return options;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }

    private List<MCQ> questions;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JPanel optionsPanel;
    private int correctAnswers = 0;

    public MCQGeneratorApp(List<MCQ> selectedQuestions) {
        this.questions = selectedQuestions;
        initializeUI();
        displayQuestion();
    }

    private void initializeUI() {
        setTitle("MCQ Generator App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        questionLabel = new JLabel();
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0, 1));

        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            JRadioButton optionButton = new JRadioButton();
            optionsPanel.add(optionButton);
            optionsGroup.add(optionButton);
        }

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButtonClick();
            }
        });
        mainPanel.add(nextButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            MCQ currentQuestion = questions.get(currentQuestionIndex);
            questionLabel.setText(currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            int i = 0;
            for (Component component : optionsPanel.getComponents()) {
                if (component instanceof JRadioButton) {
                    JRadioButton optionButton = (JRadioButton) component;
                    optionButton.setText(options.get(i));
                    optionButton.setSelected(false);
                    i++;
                }
            }
        } else {
            nextButton.setEnabled(false);
            showResult();
        }
    }

    private void handleNextButtonClick() {
        if (optionsGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Please select an option.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String selectedAnswer = getSelectedAnswer();
            MCQ currentQuestion = questions.get(currentQuestionIndex);
            if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
                correctAnswers++;
            }
            optionsGroup.clearSelection();
            currentQuestionIndex++;
            displayQuestion();
        }
    }

    private String getSelectedAnswer() {
        for (Component component : optionsPanel.getComponents()) {
            if (component instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) component;
                if (radioButton.isSelected()) {
                    return radioButton.getText();
                }
            }
        }
        return null;
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "You answered " + correctAnswers + " out of " + questions.size() + " questions correctly.", "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                List<MCQ> allQuestions = generateQuestions();
                List<MCQ> selectedQuestions = selectRandomQuestions(allQuestions, 10);
                MCQGeneratorApp app = new MCQGeneratorApp(selectedQuestions);
                app.setVisible(true);
            }
        });
    }

    public static List<MCQ> generateQuestions() {
        List<MCQ> questions = new ArrayList<>();
     // Add your MCQs here

        questions.add(new MCQ("Who won the FIFA World Cup in 2018?",

                List.of("a) France", "b) Brazil", "c) Germany", "d) Argentina"), "a) France"));       

        questions.add(new MCQ("Which country is considered the birthplace of modern Olympics?",

                List.of("a) Greece", "b) Italy", "c) France", "d) United States"), "a) Greece"));

        
        questions.add(new MCQ("What is the capital of Japan?",

                List.of("a) Tokyo", "b) Beijing", "c) Seoul", "d) Bangkok"), "a) Tokyo"));
        

        questions.add(new MCQ("Who is known as the father of computers?",

                List.of("a) Charles Babbage", "b) Alan Turing", "c) Bill Gates", "d) Steve Jobs"), "a) Charles Babbage"));

        
        questions.add(new MCQ("Which planet is known as the Red Planet?",

                List.of("a) Venus", "b) Mars", "c) Jupiter", "d) Saturn"), "b) Mars"));

        
        questions.add(new MCQ("Who wrote 'Romeo and Juliet'?",

                List.of("a) William Shakespeare", "b) Charles Dickens", "c) Jane Austen", "d) Leo Tolstoy"), "a) William Shakespeare"));

        
        questions.add(new MCQ("What is the chemical symbol for water?",

                List.of("a) Wo", "b) W", "c) H2O", "d) Hy"), "c) H2O"));

        
        questions.add(new MCQ("Which of the following is not a primary color?",

                List.of("a) Red", "b) Green", "c) Yellow", "d) Blue"), "b) Green"));

        
        questions.add(new MCQ("What is the tallest mammal in the world?",

                List.of("a) Elephant", "b) Giraffe", "c) Kangaroo", "d) Hippopotamus"), "b) Giraffe"));

        
        questions.add(new MCQ("Who painted the Mona Lisa?",

                List.of("a) Leonardo da Vinci", "b) Vincent van Gogh", "c) Pablo Picasso", "d) Michelangelo"), "a) Leonardo da Vinci"));

        
        questions.add(new MCQ("What is the largest organ in the human body?",

                List.of("a) Liver", "b) Heart", "c) Skin", "d) Brain"), "c) Skin"));
        

        questions.add(new MCQ("Which gas is most abundant in the Earth's atmosphere?",

                List.of("a) Oxygen", "b) Nitrogen", "c) Carbon Dioxide", "d) Hydrogen"), "b) Nitrogen"));
        

        questions.add(new MCQ("What is the chemical symbol for gold?",

                List.of("a) Au", "b) Ag", "c) Go", "d) Gd"), "a) Au"));

        
        questions.add(new MCQ("Who was the first woman to win a Nobel Prize?",

                List.of("a) Marie Curie", "b) Mother Teresa", "c) Rosalind Franklin", "d) Jane Goodall"), "a) Marie Curie"));


        questions.add(new MCQ("What is the capital of Australia?",

                List.of("a) Sydney", "b) Canberra", "c) Melbourne", "d) Brisbane"), "b) Canberra"));

        
        questions.add(new MCQ("Which country is known as the Land of the Rising Sun?",

                List.of("a) China", "b) South Korea", "c) Japan", "d) Vietnam"), "c) Japan"));
        
        questions.add(new MCQ("Who invented the telephone?",
                List.of("a) Alexander Graham Bell", "b) Thomas Edison", "c) Nikola Tesla", "d) Albert Einstein"), "a) Alexander Graham Bell"));

        questions.add(new MCQ("What is the chemical symbol for iron?",
                List.of("a) Ir", "b) Fe", "c) I", "d) In"), "b) Fe"));

        questions.add(new MCQ("Who wrote 'To Kill a Mockingbird'?",
                List.of("a) Harper Lee", "b) Mark Twain", "c) J.K. Rowling", "d) Ernest Hemingway"), "a) Harper Lee"));

        questions.add(new MCQ("Which country is the largest by land area?",
                List.of("a) Russia", "b) Canada", "c) China", "d) United States"), "a) Russia"));

        questions.add(new MCQ("What is the smallest prime number?",
                List.of("a) 1", "b) 2", "c) 3", "d) 5"), "b) 2"));

        questions.add(new MCQ("Who was the first man to step on the moon?",
                List.of("a) Buzz Aldrin", "b) Neil Armstrong", "c) Yuri Gagarin", "d) Michael Collins"), "b) Neil Armstrong"));

        questions.add(new MCQ("What is the chemical symbol for silver?",
                List.of("a) Si", "b) Ag", "c) S", "d) Sv"), "b) Ag"));

        questions.add(new MCQ("Which animal is known as the 'King of the Jungle'?",
                List.of("a) Lion", "b) Tiger", "c) Elephant", "d) Gorilla"), "a) Lion"));

        questions.add(new MCQ("Who discovered penicillin?",
                List.of("a) Alexander Fleming", "b) Louis Pasteur", "c) Jonas Salk", "d) Edward Jenner"), "a) Alexander Fleming"));
      
        return questions;
    }

    public static List<MCQ> selectRandomQuestions(List<MCQ> questions, int numQuestions) {
        if (questions.size() <= numQuestions) {
            return questions;
        } else {
            List<MCQ> selectedQuestions = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();
            for (int i = 0; i < questions.size(); i++) {
                indexes.add(i);
            }
            Collections.shuffle(indexes);
            for (int i = 0; i < numQuestions; i++) {
                selectedQuestions.add(questions.get(indexes.get(i)));
            }
            return selectedQuestions;
        }
    }
}
