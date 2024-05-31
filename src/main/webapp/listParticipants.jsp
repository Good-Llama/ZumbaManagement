<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.zumba.model.Participant" %>
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
            List<Participant> participants = (List<Participant>) request.getAttribute("participants");
            if (participants != null && !participants.isEmpty()) {
                for (Participant participant : participants) {
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
