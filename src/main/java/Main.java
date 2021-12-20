import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Database.connectDB();
            Database.initializeTransactionTable();
//            Database.putDataInTransactionTable(parseCsvFile());
            System.out.println(Database.getAvgTransactions());
            System.out.println(Database.getMinAndMaxTransaction());
            Diagram chart = new Diagram(Database.getSumTransactions());
            chart.pack();
            chart.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<TransactionData> parseCsvFile() {
        ArrayList<TransactionData> transactions = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/transactions.csv"))) {
            reader.readLine();
            while (reader.ready()) {
                String[] data = reader.readLine().split(",");
                double amount = data[2].equals("") ? 0 : Double.parseDouble(data[2]);
                int year = Integer.parseInt(data[1].split("\\.")[0]);
                int month = Integer.parseInt(data[1].split("\\.")[1]);
                transactions.add(new TransactionData(
                        data[1],
                        data[5],
                        year,
                        month,
                        amount
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
