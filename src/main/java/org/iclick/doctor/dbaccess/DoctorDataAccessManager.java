package org.iclick.doctor.dbaccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iclick.doctor.utils.Encrypt;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represent the data access layer to access database and execute mysql queries
 */
public class DoctorDataAccessManager {

	private static final Log LOG = LogFactory.getLog(DoctorDataAccessManager.class);
	private static DoctorDataAccessManager instance = new DoctorDataAccessManager();

	/**
	 * Returns doctor DataAccessManager instance
	 * @return DoctorDataAccessManager instance
	 */
	public static DoctorDataAccessManager getInstance() {
		return instance;
	}

	/**
	 * Returns all doctor names. (firstName, LastName)
	 */
	public List<String> getAllDoctorNames() {
		List<String> details = new ArrayList<String>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DbConnectionManager.getInstance().getDbConnection().createStatement();
			String query = "SELECT First_name, Last_name FROM `doctor`";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String firstName = resultSet.getString("First_name");
				String lastName = resultSet.getString("Last_name");
				details.add("Dr. " + firstName + " " + lastName);
			}
		} catch (SQLException e) {
			LOG.error("Database connection error", e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		return details;
	}

	/**
	 * Updates doctor infomation
	 * @param firstName     first name
	 * @param lastName      last name
	 * @param hospital      current hospital of the doctor
	 * @param qualification doctor qualifications
	 * @param mobile1       doctor mobile number
	 * @param mobile2       mobile number 2
	 * @param doctorId      doctor id
	 * @param mobileIdList  current mobile list
	 */
	public void UpdateDoctorInfo(String firstName, String lastName, String hospital, String qualification, String mobile1, String mobile2,
	                             int doctorId, ArrayList<Integer> mobileIdList) {
		List<String> list = new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		list.add(mobile1);
		list.add(mobile2);
		try {
			String query = "UPDATE `doctor` SET `First_name`= ?,`Last_name`= ?,`Hospital`= ?,`Qualifications=? WHERE Doctor_Id = ?";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, hospital);
			preparedStatement.setString(4, qualification);
			preparedStatement.setInt(5, doctorId);
			preparedStatement.executeUpdate();

			int count = mobileIdList.size();
			for (int i = 0; i < count; i++) {
				String query2 = "UPDATE 'doctor_mobile_numbers' SET `mobile_no`=? WHERE Doctor_Id = ? AND `m_Id`= ?";
				preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query2);
				preparedStatement.setString(1, list.get(i));
				preparedStatement.setInt(2, doctorId);
				preparedStatement.setInt(3, mobileIdList.get(i));
				preparedStatement.executeUpdate();
			}
			//if doctor has no mobile added previously
			if (count == 0) {
				String query3 = " INSERT INTO `doctor_mobile_numbers` (`Doctor_Id`,`mobile_no`) VALUES (?,?)";
				preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query3);
				preparedStatement.setInt(1, doctorId);
				preparedStatement.setString(2, mobile1);
				preparedStatement.executeQuery();
				String query4 = " INSERT INTO `doctor_mobile_numbers` (`Doctor_Id`,`mobile_no`) VALUES (?,?)";
				preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query4);
				preparedStatement.setInt(1, doctorId);
				preparedStatement.setString(2, mobile2);
				preparedStatement.executeQuery();
			}
			//if doctor has only one number
			if (count == 1) {
				String query5 = " INSERT INTO `doctor_mobile_numbers` (`Doctor_Id`,`mobile_no`) VALUES (?,?)";
				preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query5);
				preparedStatement.setInt(1, doctorId);
				preparedStatement.setString(2, mobile2);
				preparedStatement.executeQuery();
			}
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
	}

	/**
	 * Register new doctor information
	 * @param firstName      first name
	 * @param lastName       last name
	 * @param hospital       hospital
	 * @param qualification  qualification
	 * @param specialization doctor specialize area
	 */
	public void registerDoctorInfo(String firstName, String lastName, String hospital, String qualification, String specialization) {
		Random rand = new Random();
		int randomNum = rand.nextInt(1000);
		//inserta random number to the lastname to generate a username
		String username = lastName + randomNum;
		String type = "Doctor";
		String password = Encrypt.cryptWithMD5("test123");
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO `user_login` (`User_type`,`Username`,`Password`) VALUES (?,?,?)";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.executeUpdate();

			int userid = getmxUserId();
			String query2 = "INSERT INTO `doctor` (`User_Id`,`First_name`,`Last_name`,`Hospital`,`Qualifications`, `Specialized_Area`)" +
					" VALUES (?,?,?,?,?,?)";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query2);
			preparedStatement.setInt(1, userid);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, hospital);
			preparedStatement.setString(5, qualification);
			preparedStatement.setString(6, specialization);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JDialog(), "Doctor Account Created! Username ='" + username + "'");
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
	}

	/**
	 * Gets doctor information. (firstName, LastName, Hospital, Qualification)
	 * @param doctorId doctor id
	 * @return doctor information list
	 */
	public List<String> getDoctorInfo(int doctorId) {
		List<String> list = new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "Select * From `doctor` WHERE Doctor_Id =" + doctorId;
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("First_name"));
				list.add(resultSet.getString("Last_name"));
				list.add(resultSet.getString("Hospital"));
				list.add(resultSet.getString("Qualifications"));
			}
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		return list;
	}

	/**
	 * Returs doctor password.
	 * @param doctorId doctor ID
	 * @return password
	 */
	public String getDoctorPassword(int doctorId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String password = "null";
		try {
			String query = "Select Password FROM `user_login` where User_Id = (Select User_Id From `doctor` WHERE Doctor_Id =?)";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				password = resultSet.getString("Password");
			}
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		return password;
	}

	/**
	 * update doctor password
	 * @param userId   userID
	 * @param password password
	 */
	public void updateDoctorPassword(int userId, String password) {
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE `user_login` SET Password =? where User_id =?";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		JOptionPane.showMessageDialog(new JDialog(), "Password Updated");
	}

	/**
	 * Returns userId for given doctorID
	 * @param doctorId doctorID
	 * @return ID
	 */
	public int getUserId(int doctorId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int userid = 0;
		try {
			String query = "Select User_Id From `doctor` WHERE Doctor_Id =?";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userid = resultSet.getInt("User_Id");
			}
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		return userid;
	}

	/**
	 * Returns mobile ID list
	 * @param doctorId doctor Id
	 * @return ID list
	 */
	public List<Integer> getmobileIdList(int doctorId) {
		List<Integer> mobileIdList = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM `doctor_mobile_numbers` WHERE Doctor_Id = ?";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mobileIdList.add(resultSet.getInt("m_Id"));
			}
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		return mobileIdList;
	}

	/**
	 * Returns mobile list
	 * @param doctorId doctorId
	 * @return mobile number List
	 */
	public List<Integer> getMobileNoList(int doctorId) {
		List<Integer> mobileNoList = new ArrayList<Integer>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM `doctor_mobile_numbers` WHERE Doctor_Id =?";
			preparedStatement = DbConnectionManager.getInstance().getDbConnection().prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mobileNoList.add(resultSet.getInt("mobile_no"));
			}
		} catch (SQLException e) {
			LOG.error("Mysql error while executing queries", e);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(preparedStatement);
			DbConnectionManager.getInstance().closeDbConnection();
		}
		return mobileNoList;
	}

	public ArrayList<String> getnamestospecificarea(String area) {
		ArrayList<String> details = new ArrayList<>();

		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT First_name, Last_name FROM `doctor` where Specialized_Area = " + "'" + area + "'";
			rs = st.executeQuery(query);
			System.out.println("Records from the database");
			while (rs.next()) {
				firstName = rs.getString("First_name");
				lastName = rs.getString("Last_name");
				details.add("Dr. " + firstName + " " + lastName);

			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return details;
	}

	public ArrayList<String> getdates(int id) {
		ArrayList<String> dates = new ArrayList<>();

		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Time FROM `doctor_availablity` where Doctor_ID = " + id + " group by Time ";
			rs = st.executeQuery(query);
			System.out.println("Records from the database");
			while (rs.next()) {
				dates.add(rs.getString("Time"));
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return dates;
	}

	public ArrayList<String> gettimes(int id, String day) {
		ArrayList<String> dates = new ArrayList<>();

		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Time FROM `doctor_availablity` where Doctor_ID = " + id + " AND Day = " + "'" + day
					+ "'";
			rs = st.executeQuery(query);
			System.out.println("Records from the database");
			while (rs.next()) {
				dates.add(rs.getString("Time"));
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		System.out.println("awaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return dates;
	}

	public int getselecteddDoctorCount(String area) {
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT * FROM `doctor` where Specialized_Area = " + "'" + area + "'";
			rs = st.executeQuery(query);
			doctorCount = 0;
			while (rs.next()) {
				doctorCount++;
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return doctorCount;

	}

	public int getDoctorCount() {
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT * FROM `doctor`";
			rs = st.executeQuery(query);
			doctorCount = 0;
			while (rs.next()) {
				doctorCount++;
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return doctorCount;

	}

	public int getDoctorId(String firstname, String lastname) {
		int id = -1;
		System.out.println("mekata awa");
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Doctor_Id FROM `doctor` where First_name = " + "'" + firstname + "' AND " +
					"Last_name=" +
					" " +
					"" + "'" + lastname + "'";
			rs = st.executeQuery(query);

			while (rs.next()) {
				id = Integer.parseInt(rs.getString("Doctor_Id"));
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		System.out.println(id);
		return id;

	}

	public ArrayList<String> getdatatospecificarea(String area) {
		ArrayList<String> details = new ArrayList<>();

		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT First_name,Last_name,Specialized_Area,Hospital,Day,Time FROM doctor INNER JOIN " +
					"doctor_availablity ON doctor.Doctor_Id=doctor_availablity.Doctor_ID where Specialized_Area = '"
					+ area + "'";
			rs = st.executeQuery(query);
			System.out.println("Records from the database");
			while (rs.next()) {
				firstName = rs.getString("First_name");
				lastName = rs.getString("Last_name");
				details.add("Dr. " + firstName + " " + lastName);
				details.add(rs.getString("Specialized_Area"));
				details.add(rs.getString("Hospital"));
				details.add(rs.getString("Day"));
				details.add(rs.getString("Time"));

			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return details;
	}

	public ArrayList<String> getdatatospecificname(String name) {
		ArrayList<String> details = new ArrayList<>();

		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT First_name,Last_name,Specialized_Area,Hospital,Day,Time FROM doctor INNER JOIN " +
					"doctor_availablity ON doctor.Doctor_Id=doctor_availablity.Doctor_ID where Last_name LIKE '%" +
					name + "%'";
			rs = st.executeQuery(query);
			System.out.println("Records from the database");
			while (rs.next()) {
				firstName = rs.getString("First_name");
				lastName = rs.getString("Last_name");
				details.add("Dr. " + firstName + " " + lastName);
				details.add(rs.getString("Specialized_Area"));
				details.add(rs.getString("Hospital"));
				details.add(rs.getString("Day"));
				details.add(rs.getString("Time"));

			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return details;
	}

	public ArrayList<String> getdatatospecificnameandarea(String area, String name) {
		ArrayList<String> details = new ArrayList<>();

		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT First_name,Last_name,Specialized_Area,Hospital,Day,Time FROM doctor INNER JOIN " +
					"doctor_availablity ON doctor.Doctor_Id=doctor_availablity.Doctor_ID where Last_name LIKE '%" +
					name + "%' AND Specialized_Area = '" + area + "'";
			rs = st.executeQuery(query);
			System.out.println("Records from the database");
			while (rs.next()) {
				firstName = rs.getString("First_name");
				lastName = rs.getString("Last_name");
				details.add("Dr. " + firstName + " " + lastName);
				details.add(rs.getString("Specialized_Area"));
				details.add(rs.getString("Hospital"));
				details.add(rs.getString("Day"));
				details.add(rs.getString("Time"));

			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return details;
	}

	public int getmxUserId() {
		int id = -1;
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT max(User_Id) FROM `user_login`";
			rs = st.executeQuery(query);

			while (rs.next()) {
				id = Integer.parseInt(rs.getString("max(User_Id)"));
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return id;

	}

	public String getDoctorName(int id) {
		String name = null;
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT * FROM `doctor` where Doctor_Id = " + id;
			rs = st.executeQuery(query);

			while (rs.next()) {
				String fname = rs.getString("First_name");
				String lname = rs.getString("Last_name");
				name = fname + " " + lname;
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return name;

	}

	public String getDoctorHospital(int id) {
		String hosname = null;
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT * FROM `doctor` where Doctor_Id = " + id;
			rs = st.executeQuery(query);

			while (rs.next()) {
				hosname = rs.getString("Hospital");

			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return hosname;

	}

	public ArrayList<String> lastappointment(int pid, int did) {
		ArrayList<String> available = new ArrayList<>();
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Date, Time FROM `channelling` where Doctor_Id = " + did + " AND Patient_Id = " +
					pid + " Order by Date DESC, Time DESC Limit 1";
			rs = st.executeQuery(query);

			while (rs.next()) {
				available.add(rs.getString("Date"));
				available.add(rs.getString("Time"));
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return available;

	}

	public ArrayList<String> availabity(int id) {
		ArrayList<String> available = new ArrayList<>();
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Day, Time FROM `doctor_availablity` where Doctor_ID = " + id;
			rs = st.executeQuery(query);

			while (rs.next()) {
				available.add(rs.getString("Day"));
				available.add(rs.getString("Time"));
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return available;

	}

	public int availabitycount(int id) {
		int count = -1;
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Day, Time FROM `doctor_availablity` where Doctor_ID = " + id;
			rs = st.executeQuery(query);
			count = 0;
			while (rs.next()) {
				count++;
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return count;

	}

	public int getdoctorid(int userid) {
		int docid = -1;
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT Doctor_Id FROM `doctor` where User_Id = " + userid;

			rs = st.executeQuery(query);

			while (rs.next()) {
				docid = rs.getInt("Doctor_Id");
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		System.out.println("user Id" + docid);
		return docid;

	}

	public ArrayList<String> getTodaylist(String day) {
		ArrayList<String> details = new ArrayList<>();
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT * FROM `doctor` WHERE Doctor_Id in (SELECT Doctor_Id FROM `doctor_availablity` " +
					"WHERE Day = '" + day + "')";
			rs = st.executeQuery(query);

			System.out.println("Records from the database nn");
			while (rs.next()) {
				details.add(rs.getString("Doctor_Id"));  //0
				details.add(rs.getString("First_name"));  //1
				details.add(rs.getString("Last_name"));  //2
				details.add(rs.getString("Specialized_Area"));//3
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return details;
	}

	public ArrayList<String> getTodaypatientlist(int did, String date) {
		ArrayList<String> details = new ArrayList<>();
		try {
			st = dbCon.getConnection().createStatement();
			String query = "SELECT * FROM `patient` WHERE Patient_Id in (SELECT Patient_Id FROM `channelling` WHERE " +
					"Doctor_Id = " + did + " AND Date='" + date + "')";
			rs = st.executeQuery(query);

			System.out.println("Records from the database nn");
			while (rs.next()) {
				details.add(rs.getString("Patient_Id"));  //0
				details.add(rs.getString("First_name"));  //1
				details.add(rs.getString("Last_name"));  //2
			}

		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
		return details;
	}

	/**
	 * Closing resultset
	 * @param resultSet result set
	 */
	public void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			LOG.error("Failed to close resultSet", e);
		}
	}

	/**
	 * Closing the prepared statement
	 * @param preparedStatement prepared statement
	 */
	public void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			LOG.error("Failed to close preparedStatement", e);
		}
	}

	/**
	 * Closing the executeUpdate type query statements
	 * @param statement statement
	 */
	public void closeStatement(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			LOG.error("Failed to close statement", e);
		}
	}
}
