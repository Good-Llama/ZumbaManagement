package servlets;

import dao.ParticipantDAO;
import model.Participant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ParticipantServlet")
public class ParticipantServlet extends HttpServlet {
    private ParticipantDAO participantDAO;

    public void init() {
        participantDAO = new ParticipantDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Participant> participants = participantDAO.getAllParticipants();
        request.setAttribute("participants", participants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listParticipants.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int batchId = Integer.parseInt(request.getParameter("batchId"));
        Participant participant = new Participant();
        participant.setName(name);
        participant.setEmail(email);
        participant.setBatchId(batchId);
        participantDAO.saveParticipant(participant);
        response.sendRedirect("ParticipantServlet");
    }
}
