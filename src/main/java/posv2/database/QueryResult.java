package posv2.database;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {

    private final List<DataRow> rows;

    public QueryResult() {

        this.rows =
                new ArrayList<>();
    }

    public void addRow(
            DataRow row) {

        rows.add(row);
    }

    public List<DataRow> getRows() {

        return rows;
    }

    public DataRow first() {

        if(rows.isEmpty()) {

            return null;
        }

        return rows.get(0);
    }

    public boolean isEmpty() {

        return rows.isEmpty();
    }

    public int size() {

        return rows.size();
    }
}