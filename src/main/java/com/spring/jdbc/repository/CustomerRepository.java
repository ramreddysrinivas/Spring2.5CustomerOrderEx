package com.spring.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.spring.jdbc.domain.Customer;
import com.spring.jdbc.util.DataBaceConnectionUtil;

public class CustomerRepository implements ICustomerRepository {

	@Override
	public String saveCustomer(Customer customer) {
		String message =null;
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DataBaceConnectionUtil.getConnection();
			pstmt=con.prepareStatement("insert into Customer(cId,cName,cEmailId)values(?,?,?)");
			pstmt.setInt(1,customer.getcId());
			pstmt.setString(2,customer.getcName());
			pstmt.setString(3,customer.getcEmailId());
			pstmt.executeUpdate();
			message= "inserted successfully";
			
		}catch(Exception    ex){
			message= "Exception occured while inserting customer" + ex;
		}finally{
			try {
				DataBaceConnectionUtil.closePstmt(pstmt);
				DataBaceConnectionUtil.closeConnection(con);
			} catch (SQLException e) {
				message= "Exception occured while closing resources ";
			}
			
		}
	
		return message;
	}

	

}
