package Main.java.com.bouncy_mehdich.sever.Controllers;

import Main.java.com.bouncy_mehdich.sever.DB.CertainJobDAO;
import Main.java.com.bouncy_mehdich.sever.DB.JobDAO;
import Main.java.com.bouncy_mehdich.sever.Models.CertainJobs;

import java.sql.SQLException;

public class JobController {
    CertainJobDAO jobDAO;

    public JobController() {
        jobDAO = new CertainJobDAO();
    }
    public void addCertainJob(String userID, CertainJobs certainJobs) throws SQLException {
        jobDAO.addCertainJob(userID, certainJobs);
    }
}
