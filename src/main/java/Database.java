import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/transactions.db");
        statement = connection.createStatement();
    }

    public static void disconnectDB() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void initializeTransactionTable() throws SQLException {
        statement.execute(
                "CREATE TABLE IF NOT EXISTS Transactions " +
                "( " +
                "    period TEXT, " +
                "    units  TEXT, " +
                "    year   INTEGER, " +
                "    month  INTEGER, " +
                "    amount FLOAT " +
                ");"
        );
    }

    public static void putDataInTransactionTable(ArrayList<TransactionData> transactions) {
        transactions.forEach(t -> {
            try {
                statement.execute(String.format(
                        "INSERT INTO Transactions (period, units, year, month, amount) VALUES ('%s', '%s', '%s', '%s', '%s');",
                        t.getPeriod(), t.getUnits(), t.getYear(), t.getMonth(), t.getAmount())
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static Map<String, Double> getSumTransactions() throws SQLException {
        ResultSet rs = statement.executeQuery(
                "SELECT month, SUM(amount) AS sum " +
                "FROM Transactions " +
                "WHERE year = 2020 " +
                "GROUP BY month;"
        );

        Map<String, Double> result = new HashMap<>();
        int monthNumber;
        double sum;

        while (rs.next()) {
            monthNumber = Integer.parseInt(rs.getString("month"));
            sum = Double.parseDouble(rs.getString("sum"));
            result.put(getMonth(monthNumber), sum);
        }

        return result;
    }

    private static String getMonth(int month) {
        String[] months = {
                "January", "February",
                "March", "April",
                "May", "June",
                "July", "August",
                "September", "October",
                "November", "December"};
        return months[month-1];
    }

    public static String getAvgTransactions() throws SQLException {
        ResultSet rs = statement.executeQuery(
                "SELECT period, COUNT(amount) AS countTransactions, AVG(amount) AS averageAmount " +
                "FROM Transactions " +
                "WHERE units = 'Dollars' AND amount != 0 " +
                "GROUP BY period;"
        );

        StringBuilder result = new StringBuilder();
        String period;
        int countTransactions;
        double averageAmount;

        result.append("\nКоличество переводов и средний перевод в долларах за период:\n");
        while (rs.next()) {
            period = rs.getString("period");
            countTransactions = Integer.parseInt(rs.getString("countTransactions"));
            averageAmount = Double.parseDouble(rs.getString("averageAmount"));

            result.append(String.format("Период '%s', количество транзакций '%d', средняя величина транзакции '%f'\n",
                    period, countTransactions, averageAmount));
        }

        return result.toString();
    }

    public static String getMinAndMaxTransaction() throws SQLException {
        ResultSet rs = statement.executeQuery(
                "SELECT year, MAX(amount) AS max, MIN(amount) AS min " +
                "FROM Transactions " +
                "WHERE year IN (2014, 2016, 2020) AND amount != 0 AND units = 'Dollars' " +
                "GROUP BY year;"
        );

        StringBuilder result = new StringBuilder();
        String year;
        double maxAmount;
        double minAmount;

        result.append("\nМинимальные и максимальные транзакции за 2014, 2016 и 2020 год:\n");
        while (rs.next()) {
            year = rs.getString("year");
            maxAmount = Double.parseDouble(rs.getString("max"));
            minAmount = Double.parseDouble(rs.getString("min"));

            result.append(String.format("За %s год, максимальный перевод = %f, минимальный = %f\n",
                    year, maxAmount, minAmount)
            );
        }

        return result.toString();
    }
}
