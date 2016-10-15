import javax.swing.*;

/**
 * Created by zhengwu on 10/15/16.
 */
public class FAFSAGUI {
    public static void main(String[] args) {
        boolean continueusing;
        boolean isAccepted;
        boolean registered;
        boolean hasSocialSecurity;
        boolean validResidenceStatus;
        boolean dependent;
        String age;
        String credithours;
        String income;
        String parentincome;

        do {
            JOptionPane.showMessageDialog(null, "Welcome to the FAFSA!", "Welcome", 1);

            int selectedOption0 = JOptionPane.showConfirmDialog(null, "Have you accepted into a degree or certificate program?", "Program Acceptance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selectedOption0 == JOptionPane.NO_OPTION) {
                isAccepted = false;
            }
            else {
                isAccepted = true;
            }
            int register = JOptionPane.showConfirmDialog(null, "Are you registered for the selective service?", "Selective service", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (register == JOptionPane.NO_OPTION) {
                registered = false;
            }
            else {
                registered = true;
            }
            int selectedOption1 = JOptionPane.showConfirmDialog(null, "Do you have a social security number?", "Social Security number", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selectedOption1 == JOptionPane.NO_OPTION) {
                hasSocialSecurity = false;
            }
            else {
                hasSocialSecurity = true;
            }
            int selectedOption2 = JOptionPane.showConfirmDialog(null, "Do you have a valid residency status?", "Residency status", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (selectedOption2 == JOptionPane.NO_OPTION) {
                validResidenceStatus = false;
            }
            else {
                validResidenceStatus = true;
            }
            boolean passed = false;
            do {
                // Create a drop-down menu dialog with various options
                // showInputDialog(params...) returns an Object so we cast to a String
                age = (String) JOptionPane.showInputDialog(null, "How old are you? "
                        , "age", JOptionPane.PLAIN_MESSAGE);

                // Compare the user-input with the expected input
                // Display the corresponding information message
                if (Integer.parseInt(age) > 0) {
                    passed = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Age can not be negative number.", "Error Age", JOptionPane.ERROR_MESSAGE);
                }
            } while (!passed);

            boolean passed1 = false;
            do {
                // Create a drop-down menu dialog with various options
                // showInputDialog(params...) returns an Object so we cast to a String
                credithours = (String) JOptionPane.showInputDialog(null, "How many credit hours you are taking? "
                        , "credit hours", JOptionPane.PLAIN_MESSAGE);

                // Compare the user-input with the expected input
                // Display the corresponding information message
                if (Integer.parseInt(credithours) <= 24 && Integer.parseInt(credithours) >= 1) {
                    passed1 = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Credit hours must between 1-24 inclusive.", "credit hour error", JOptionPane.ERROR_MESSAGE);
                }
            } while (!passed1);

            boolean passed2 = false;
            do {
                // Create a drop-down menu dialog with various options
                // showInputDialog(params...) returns an Object so we cast to a String
                income = (String) JOptionPane.showInputDialog(null, "What's your yearly income?? "
                        , "Yearly Income", JOptionPane.PLAIN_MESSAGE);

                // Compare the user-input with the expected input
                // Display the corresponding information message
                if (Integer.parseInt(income) > 0) {
                    passed2 = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Income can't be negative.", "Error: student income", JOptionPane.ERROR_MESSAGE);
                }
            } while (!passed2);

            boolean passed3 = false;
            do {
                // Create a drop-down menu dialog with various options
                // showInputDialog(params...) returns an Object so we cast to a String
                parentincome = (String) JOptionPane.showInputDialog(null, "What's your parents yearly income?? "
                        , "parents Yearly Income", JOptionPane.PLAIN_MESSAGE);

                // Compare the user-input with the expected input
                // Display the corresponding information message
                if (Integer.parseInt(income) > 0) {
                    passed3 = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Income can't be negative.", "Error: parents income", JOptionPane.ERROR_MESSAGE);
                }
            } while (!passed3);

            int dependency = JOptionPane.showConfirmDialog(null, "Are you dependent?", "Dependency", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dependency == JOptionPane.NO_OPTION)
                dependent = false;
            else
                dependent = true;
            double dincome = Double.parseDouble(income);
            double dparentincome = Double.parseDouble(parentincome);

            String[] options = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};
            String standing = (String) JOptionPane.showInputDialog(null, "What's your current class standing? ",
                    "Class standing", JOptionPane.PLAIN_MESSAGE, null, options, null);
            FAFSA fafsa = new FAFSA(isAccepted, registered, hasSocialSecurity, validResidenceStatus, dependent, Integer.parseInt(age), Integer.parseInt(credithours), dincome, dparentincome, standing);

            double loans = fafsa.calcStaffordLoan();
            double grants = fafsa.calcFederalGrant();
            double workstudy = fafsa.calcWorkStudy();
            double total = loans + grants + workstudy;


            String resultMessage = "Loans:$" + loans + "\n" + "Grants:$" + loans + "\n" + "WorkStudy:$" + workstudy + "\n" + "----------------\n" + "Total:$" + total;
            JOptionPane.showMessageDialog(null, resultMessage, "FAFSA result", 1);

            int useagain = JOptionPane.showConfirmDialog(null, "Would you like to complete another application?", "Continue", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (useagain == JOptionPane.YES_OPTION) {
                continueusing = true;
            }
            else {
                continueusing = false;
            }
        }
        while (continueusing);
        JOptionPane.showMessageDialog(null,"Thanks for using FAFSA,bye!","bye",1);
    }
}
