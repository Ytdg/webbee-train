package org.example.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FormatTable {

    public static String createTable(HashMap<Integer, List<Double>> data) {
        Objects.requireNonNull(data);
        StringBuilder html = new StringBuilder();
        html.append("<table>\n");

        html.append("  <tr>\n");
        html.append("    <th>Input data</th>\n");
        for (Integer key : data.keySet()) {
            html.append("    <th>").append(key).append("</th>\n");
        }
        html.append("  </tr>\n");

        int maxListSize = data.values().stream().mapToInt(List::size).max().orElse(0);
        for (int i = 0; i < maxListSize; i++) {
            html.append("  <tr>\n");
            html.append("    <td>Weight ").append(i + 1).append("</td>\n");
            for (Integer key : data.keySet()) {
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
