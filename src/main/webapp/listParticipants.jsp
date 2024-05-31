<%@ page import="java.util.List" %>
<%@ page import="com.zumba.model.Participant" %>
<%@ page import="com.zumba.util.DatabaseUtil" %>

<!DOCTYPE html>
<html>
<head>
    <title>Participants List</title>
</head>
<body>
    <h1>Participants</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Batch ID</th>
        </tr>
        <%
            List<Participant> participants = null; // Initialize with null, will be populated from the database
            // Example: participants = DatabaseUtil.getParticipants(); // Fetch participants from the database
            if (participants != null && !participants.isEmpty()) { // Check if participants is not null and not empty
                for (Participant participant : participants) { // Iterate over participants
        %>
        <tr>
            <td><%= participant.getId() %></td>
            <td><%= participant.getName() %></td>
            <td><%= participant.getEmail() %></td>
            <td><%= participant.getBatchId() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">No participants available</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
