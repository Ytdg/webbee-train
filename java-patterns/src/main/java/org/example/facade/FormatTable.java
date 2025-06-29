package org.example.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Класс реализует представление результатов
 * @author Nikita Bochkov
 * */
public final class FormatTable {

    private FormatTable() {

    }

    /**
     * Метод создает таблицу - строку в формате Html.
     * @param data данные для представления. Ключ - Input data, значение - Weight <- имена в таблице.
     * */
    public static String createTable(HashMap<Double, List<Double>> data) { // can be safe to file as example
        Objects.requireNonNull(data);
        StringBuilder html = new StringBuilder();
        html.append("<table>\n");

        html.append("  <tr>\n");
        html.append("    <th>Input data</th>\n");
        for (Double key : data.keySet()) {
            html.append("    <th>").append(key).append("</th>\n");
        }
        html.append("  </tr>\n");

        int maxListSize = data.values().stream().mapToInt(List::size).max().orElse(0);
        for (int i = 0; i < maxListSize; i++) {
            html.append("  <tr>\n");
            html.append("    <td>Weight ").append(i + 1).append("</td>\n");
            for (Double key : data.keySet()) {
                List<Double> values = data.get(key);
                html.append("    <td>");
                if (i < values.size()) {
                    html.append(String.format("%.1f", values.get(i)));
                }
                html.append("</td>\n");
            }
            html.append("  </tr>\n");
        }
        html.append("</table>\n");
        return html.toString();
    }

}
