package posv2.database;

import java.util.HashMap;
import java.util.Map;

public class DataRow {

	private final Map<String, Object> values;

    public DataRow() {

        values = new HashMap<>();
    }

    public void put(String columna,
                    Object valor) {

        values.put(columna, valor);
    }

    public Object get(String columna) {

        return values.get(columna);
    }

    public String getString(String columna) {

        Object value = values.get(columna);

        return value == null
                ? null
                : value.toString();
    }

    public Integer getInt(String columna) {

        Object value = values.get(columna);

        if(value == null) {
            return null;
        }

        return ((Number) value).intValue();
    }

    public Long getLong(String columna) {

        Object value = values.get(columna);

        if(value == null) {
            return null;
        }

        return ((Number) value).longValue();
    }

    public Double getDouble(String columna) {

        Object value = values.get(columna);

        if(value == null) {
            return null;
        }

        return ((Number) value).doubleValue();
    }

    public Boolean getBoolean(String columna) {

        Object value = values.get(columna);

        if(value == null) {
            return null;
        }

        return (Boolean) value;
    }

    public boolean contains(String columna) {

        return values.containsKey(columna);
    }

}
