<%@ page import="java.util.List" %>
<%@ page import="com.zumba.model.Batch" %>
<%@ page import="com.zumba.util.DatabaseUtil" %>

<!DOCTYPE html>
<html>
<head>
    <title>Batches List</title>
</head>
<body>
    <h1>Batches</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Timing</th>
        </tr>
        <%
            List<Batch> batches = null; // Initialize with null, will be populated from the database
            // Example: batches = DatabaseUtil.getBatches(); // Fetch batches from the database
            if (batches != null && !batches.isEmpty()) { // Check if batches is not null and not empty
                for (Batch batch : batches) { // Iterate over batches
        %>
        <tr>
            <td><%= batch.getId() %></td>
            <td><%= batch.getName() %></td>
            <td><%= batch.getTiming() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">No batches available</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
